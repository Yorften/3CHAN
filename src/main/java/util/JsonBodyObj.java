package util;

import java.io.BufferedReader;
import java.io.IOException;

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

        this.requestBody = requestBodyBuilder.toString().trim();
    }

    public String getParameter(String param){


        return "";
    }


    public String getRequestBody() {
      return this.requestBody;
    }
    public void setRequestBody(String value) {
      this.requestBody = value;
    }
}
