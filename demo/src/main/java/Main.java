import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {
    private static final String s = "{\"name\": {\"en\": \"foo\", \"jp\": \"bar\"}}";
    private static final String s2 = "{\"name\": {\"en\": {\"title\":\"foo\", \"id\":1}, \"jp\": {\"title\":\"bar\", \"id\":2}}}";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        try {
            I18NProperty<String> sweOut1 = mapper.readValue(s, new TypeReference<I18NProperty<String>>() {});
            I18NProperty<TestObject> sweOut2 = mapper.readValue(s2, new TypeReference<I18NProperty<TestObject>>() {});

            System.out.println("\n--- JSON to JAVA DEMO1 sweOut1---\n" + sweOut1 +" "+  sweOut1.size());
            System.out.println("--- JSON to JAVA DEMO1 sweOut2---\n" + sweOut2+" "+  sweOut2.size());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
