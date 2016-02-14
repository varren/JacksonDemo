import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.String;
import java.util.ArrayList;
import java.util.Locale;

@JsonRootName(value = "name")
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public class I18NProperty<T> extends ArrayList<I18NProperty.Content<T>>{

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
        this.add(c);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        String delim = "";
        for(I18NProperty.Content elem: this) {
            res.append(delim);
            delim = ", ";
            res.append(String.valueOf(elem));

        }
        res.append("]");

        return res.toString();
    }
}
