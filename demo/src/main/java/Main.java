import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {
    private static final String simpleDemo = "{\"en\": \"foo\", \"jp\": \"bar\"}";
    private static final String simpleDemoWithName = "{\"name\": {\"en\": \"foo\", \"jp\": \"bar\"}}";
    private static final String customObjDemo = "{\"name\": {\"en\": {\"title\":\"foo\", \"id\":1}, \"jp\": {\"title\":\"bar\", \"id\":2}}}";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // SIMPLE Test {"en": "foo", "jp": "bar"} for I18NProperty
            I18NProperty<String> sweOut1 = mapper.readValue(simpleDemo, new TypeReference<I18NProperty<String>>() {});
            String jsonInString = mapper.writeValueAsString(sweOut1);
            Log(simpleDemo,sweOut1,jsonInString);
        } catch (IOException e) {e.printStackTrace();}


        try {
            // SIMPLE Test {"name": {"en": "foo", "jp": "bar"}} for ParentObj.I18NProperty
            TestParentObj parentObj = mapper.readValue(simpleDemoWithName, new TypeReference<TestParentObj<String>>() {});
            String jsonInString = mapper.writeValueAsString(parentObj);
            Log(simpleDemoWithName,parentObj,jsonInString);
        } catch (IOException e) {e.printStackTrace();}


        try {
            // SIMPLE Test {"name": {"en": {}, "jp": {}}} for ParentObj.I18NProperty
            TestParentObj parentObj = mapper.readValue(customObjDemo, new TypeReference<TestParentObj<TestObject>>() {});
            String jsonInString = mapper.writeValueAsString(parentObj);
            Log(customObjDemo,parentObj,jsonInString);
        } catch (IOException e) {e.printStackTrace();}


        // mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        // will remove {"name":...} part from json
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);


        try {
            // SIMPLE Test {"name": {"en": "foo", "jp": "bar"}}
            I18NProperty<String> sweOut1 = mapper.readValue(simpleDemoWithName, new TypeReference<I18NProperty<String>>() {});
            String jsonInString = mapper.writeValueAsString(sweOut1);
            Log(simpleDemoWithName,sweOut1,jsonInString);
        } catch (IOException e) {e.printStackTrace();}

        try {
            // SIMPLE Test {"name": {"en": {}, "jp": {}}}
            I18NProperty<TestObject> sweOut2 = mapper.readValue(customObjDemo, new TypeReference<I18NProperty<TestObject>>() {});
            String jsonInString = mapper.writeValueAsString(sweOut2);
            Log(customObjDemo,sweOut2,jsonInString);

        } catch (IOException e) {e.printStackTrace();}
    }

    private static void Log(String inJson, Object object, String outJson){
        System.out.println("\n--- FROM JSON: " + inJson);
        System.out.println("----> TO JAVA: " + object);
        System.out.println("----> TO JSON: " + outJson);
    }
}
