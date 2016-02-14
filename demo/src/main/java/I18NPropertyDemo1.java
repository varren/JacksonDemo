import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.String;
import java.util.ArrayList;
import java.util.Locale;

@JsonRootName(value = "name")
public  class I18NPropertyDemo1<T> {

    private ArrayList<Content<T>> names = new ArrayList<I18NPropertyDemo1.Content<T>>();

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
        for(I18NPropertyDemo1.Content<T> elem: this.names) {
            res.append(delim);
            delim = ", ";
            res.append(String.valueOf(elem));

        }
        res.append("]");

        return res.toString();
    }
}
