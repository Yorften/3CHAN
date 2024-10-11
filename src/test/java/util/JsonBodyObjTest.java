package util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JsonBodyObjTest {

    JsonBodyObj bodyObj;

    @Before
    public void setUp() {
        bodyObj = new JsonBodyObj();

        bodyObj.setRequestBody("{\r\n" + //
                "    \"commentId\": 31,\r\n" + //
                "    \"author\": {\r\n" + //
                "        \"firstName\": \"John\",\r\n" + //
                "        \"lastName\": \"Doe\",\r\n" + //
                "        \"age\": 30,\r\n" + //
                "        \"isActive\": true\r\n" + //
                "    },\r\n" + //
                "    \"content\": \"This is a sample comment.\",\r\n" + //
                "    \"tags\": [\"example\", \"test\", \"comment\"],\r\n" + //
                "    \"likes\": 15,\r\n" + //
                "    \"nested\": {\r\n" + //
                "        \"key1\": \"value1\",\r\n" + //
                "        \"key2\": \"value2\"\r\n" + //
                "    }\r\n" + //
                "}\r\n" + //
                "");
    }

    public boolean isTrimmed(String str) {
        if (str == null) {
            return false;
        }
        return str.equals(str.replaceAll("\\s+", " ").trim());
    }

    @Test
    public void isRequestBodyTrimmed() {
        System.out.println(bodyObj.getRequestBody());
        assertTrue(isTrimmed(bodyObj.getRequestBody()));
    }

    @Test
    public void getParameterTest() {
        String commentIdParam = bodyObj.getParameter("commentId");
        assertTrue(commentIdParam.equals("31"));
    }

}
