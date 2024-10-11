package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class JsonBodyObj {

    private String requestBody;

    public JsonBodyObj() {

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

    public String getParameter(String param) {

        int searchIndex = requestBody.indexOf(param);

        String value = requestBody.substring(searchIndex + param.length() + 3, requestBody.indexOf(",", searchIndex)).replaceAll("\"", "");

        System.out.println("the value of param " + param + " is : " + value);

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
