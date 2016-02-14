import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import wrapperddemo.I18NPropertyWrapper;

import java.io.IOException;

public class Main {
    private static final String s = "{\"name\": {\"en\": \"foo\", \"jp\": \"bar\"}}";
    private static final String s2 = "{\"name\": {\"en\": {\"title\":\"foo\", \"id\":1}, \"jp\": {\"title\":\"bar\", \"id\":2}}}";

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        runMainDemo(mapper);
        runWrapperTest(mapper);
    }



    public static void runMainDemo(ObjectMapper mapper){
        try {
            I18NPropertyDemo1<String> sweOut1 = mapper.readValue(s, new TypeReference<I18NPropertyDemo1<String>>() {});
            I18NPropertyDemo1<TestObject> sweOut2 = mapper.readValue(s2, new TypeReference<I18NPropertyDemo1<TestObject>>() {});

            System.out.println("\n--- JSON to JAVA DEMO1 sweOut1---\n" + sweOut1);
            System.out.println("\n--- JSON to JAVA DEMO1 sweOut2---\n" + sweOut2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runWrapperTest(ObjectMapper mapper) {
        try {
            I18NPropertyWrapper<String> wrapper1 = mapper.readValue(s, new TypeReference<I18NPropertyWrapper<String>>() {});
            I18NPropertyWrapper<TestObject> wrapper2 = mapper.readValue(s2, new TypeReference<I18NPropertyWrapper<TestObject>>() {});

            System.out.println("\n--- JSON to JAVA DEMO2 sweOut1---\n" + wrapper1.getNames());
            System.out.println("\n--- JSON to JAVA DEMO2 sweOut2---\n" + wrapper2.getNames());
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
