package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonBodyObj {
    private static final Logger logger = LoggerFactory.getLogger(JsonBodyObj.class);

    private String requestBody;

    public JsonBodyObj() {

    }

    public JsonBodyObj(String requestBody) {
        this.requestBody = requestBody;
    }

    public JsonBodyObj(HttpServletRequest req) throws IOException {
        StringBuilder requestBodyBuilder = new StringBuilder();
        String line;

        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestBodyBuilder.append(line);
            }
        }

        this.requestBody = requestBodyBuilder.toString().replaceAll("\\s+", " ").trim();
    }

    /**
     * Retrieves the value of a parameter from a JSON-like requestBody.
     * 
     * This function supports both single-level parameters (e.g., "commentId")
     * and nested parameters up to 2 levels deep (e.g., "author.firstName").
     * If a parameter is more than 2 levels deep (e.g., "author.article.name"),
     * it will throw an IllegalArgumentException.
     * 
     * Beware this is not a fully functioning raw JSON parser.
     * One of the many limitations if the value of a key has '}' inside it, the
     * parser
     * will stop there and return everything before it as the value.
     * 
     * @param param the parameter to retrieve (supports nested keys using dot
     *              notation).
     * @return the value of the specified parameter, or null if the parameter is not
     *         found.
     * @throws IllegalArgumentException if the parameter is more than 2 levels deep.
     * @throws NoSuchElementException   if the specified parameter does not exist in
     *                                  the requestBody.
     */

    public String getParameter(String param) throws IllegalArgumentException, NoSuchElementException {
        String value = null;

        // This part retrieves value of a nested key (ex: getParameter("author.name"))

        logger.info("test : " + param.contains(".") + "Param value : " + param);

        if (param.contains(".")) {
            String[] sub_params = param.split("\\.");

            if (sub_params.length > 2) {
                logger.error("Parameter cannot be more than 2 levels deep.");
                throw new IllegalArgumentException("Parameter cannot be more than 2 levels deep.");
            }

            String parent_param = sub_params[0];
            int searchIndex = requestBody.indexOf("\"" + parent_param + "\"");

            if (searchIndex != -1) {
                int startIndex = requestBody.indexOf("{", searchIndex);
                int endIndex = -1;
                int braceCount = 0;

                for (int i = startIndex; i < requestBody.length(); i++) {
                    if (requestBody.charAt(i) == '{') {
                        braceCount++;
                    } else if (requestBody.charAt(i) == '}') {
                        braceCount--;
                    }

                    if (braceCount == 0) {
                        endIndex = i;
                        break;
                    }
                }

                if (endIndex != -1) {
                    // +0 and +1 to inclue curly braces {}
                    String child_params = requestBody.substring(startIndex, endIndex + 1).trim();
                    logger.info("child_params : " + child_params);

                    JsonBodyObj childJsonBodyObj = new JsonBodyObj(child_params);

                    return childJsonBodyObj.getParameter(sub_params[1]);
                } else {
                    logger.error("Closing brace for \"" + parent_param + "\" not found.");
                    throw new NoSuchElementException("Closing brace for \"" + parent_param + "\" not found.");
                }
            } else {
                logger.error("Parameter \"" + parent_param + "\" not found.");
                throw new NoSuchElementException("Parameter \"" + parent_param + "\" not found.");
            }
        }

        // This part retrieves value of a single key (ex: getParameter("name"))

        int searchIndex = requestBody.indexOf("\"" + param + "\"");
        if (searchIndex != -1) {
            int braceCountLeft = 0;
            int braceCountRight = 0;

            logger.info("request body : " + requestBody);

            for (int i = searchIndex; i < requestBody.length(); i++) {
                if (requestBody.charAt(i) == '{') {
                    braceCountLeft++;
                } else if (requestBody.charAt(i) == '}') {
                    braceCountRight++;
                }
            }

            if (braceCountRight != braceCountLeft + 1) {
                logger.error(
                        "The parameter \"" + param + "\" is a parent parameter and refers to a nested object.");
                throw new IllegalArgumentException(
                        "The parameter \"" + param + "\" is a parent parameter and refers to a nested object.");
            }

            value = requestBody
                    .substring(searchIndex + param.length() + 3, requestBody.indexOf("}", searchIndex))
                    .replaceAll("\"", "");
            logger.info("value looking for } : " + value);
            if (value.contains(":")) {
                value = requestBody
                        .substring(searchIndex + param.length() + 3, requestBody.indexOf(",", searchIndex))
                        .replaceAll("\"", "");
            }

            logger.info("value of key " + param + " is : " + value);

        } else {
            logger.error("Parameter \"" + param + "\" not found.");
            throw new NoSuchElementException("Parameter \"" + param + "\" not found.");
        }

        return value;
    }

    // -------- Getter Setter ------------//

    public String getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(String value) {
        this.requestBody = value.replaceAll("\\s+", " ").trim();
    }

}
