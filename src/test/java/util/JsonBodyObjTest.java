package util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class JsonBodyObjTest {

    JsonBodyObj bodyObj;

    @Before
    public void setUp() {
        bodyObj = new JsonBodyObj();

        bodyObj.setRequestBody(
                "{\"commentId\":31,\"author\":{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30,\"isActive\":true},\"content\":\"This is a sample comment.\",\"likes\":15,\"nested\":{\"key1\":\"value1\",\"key2\":\"value2\"}}");
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
        String commentIdParam = bodyObj.getParameter("content");
        assertTrue(commentIdParam.equals("This is a sample comment."));
    }

    @Test
    public void getNestedParameterTest() {
        String nestedParam = bodyObj.getParameter("nested.key2");
        assertTrue(nestedParam.equals("value2"));

    }

}
