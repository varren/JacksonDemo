

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

public class Demo {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String s = "{\"name\": {\"en\": \"foo\", \"jp\": \"bar\"}}";
        String s2 = "{\"name\": {\"en\": {\"title\":\"foo\", \"id\":1}, \"jp\": {\"title\":\"bar\", \"id\":2}}}";

        I18NProperty<String> sweOut1 = null;
        I18NProperty<TestObject> sweOut2 = null;

        try {
            sweOut1 = mapper.readValue(s, new TypeReference<I18NProperty<String>>() {});
            sweOut2 = mapper.readValue(s2, new TypeReference<I18NProperty<TestObject>>() {});
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JSON to JAVA sweOut1---\n" + sweOut1);
        System.out.println("\n--- JSON to JAVA sweOut2---\n" + sweOut2);
    }




    @JsonRootName(value = "name")
    public static class I18NProperty<T> {

        private ArrayList<Content<T>> names = new ArrayList<I18NProperty.Content<T>>();

        public static class Content<T> {
            public Locale i18n;
            public T val;

            @Override
            public String toString() {
                return "Locale: "+ i18n.toString() +" value: "+ String.valueOf(val);
            }
        }

        @JsonAnySetter
        public void set(String key, T value) {
            Content<T> c = new Content<T>();
            c.i18n = new Locale(key);
            c.val = value;
            names.add(c);
        }

        public ArrayList<Content<T>> getNames() {
            return names;
        }

        public void setNames(ArrayList<Content<T>> names) {
            this.names = names;
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
