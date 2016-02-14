import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.String;import java.lang.System;
import java.util.ArrayList;
import java.util.Locale;

public class DemoWithWrapper {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String s = "{\"name\": {\"en\": \"foo\", \"jp\": \"bar\"}}";
        String s2 = "{\"name\": {\"en\": {\"title\":\"foo\", \"id\":1}, \"jp\": {\"title\":\"bar\", \"id\":2}}}";


        I18NProperty<String> sweOut1 = null;
        I18NProperty<TestObject> sweOut2 = null;
        try {
            I18NPropertyWrapper<String> wrapper1 = mapper.readValue(s, new TypeReference<I18NPropertyWrapper<String>>() {});
            sweOut1 = wrapper1.names;
            I18NPropertyWrapper<TestObject> wrapper2 = mapper.readValue(s2, new TypeReference<I18NPropertyWrapper<TestObject>>() {});
            sweOut2 = wrapper2.names;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JSON to JAVA sweOut1---\n" + sweOut1);
        System.out.println("\n--- JSON to JAVA sweOut2---\n" + sweOut2);
    }

    public static class I18NProperty<T>  extends ArrayList<I18NProperty.Content<T>>{
        public static class Content<T> {
            public Locale i18n;
            public T val;

            @Override
            public String toString() {
                return "Locale: "+ i18n.toString() +" value: "+ String.valueOf(val);
            }
        }
    }

    @JsonRootName(value = "name")
    public static class I18NPropertyWrapper<T> {
        private I18NProperty<T> names = new I18NProperty<T>();

        @JsonAnySetter
        public void set(String key, T value) {
            I18NProperty.Content<T> c = new I18NProperty.Content<T>();
            c.i18n = new Locale(key);
            c.val = value;
            names.add(c);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("[");
            String delim = "";
            for(I18NProperty.Content<T> elem: this.names) {
                res.append(delim);
                delim = ", ";
                res.append(String.valueOf(elem));

            }
            res.append("]");

            return res.toString();
        }
    }
}