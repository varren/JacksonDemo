package wrapperddemo;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Locale;

@JsonRootName(value = "name")
public class I18NPropertyWrapper<T> {

    public I18NPropertyDemo2<T> getNames() {
        return names;
    }

    public void setNames(I18NPropertyDemo2<T> names) {
        this.names = names;
    }

    private I18NPropertyDemo2<T> names = new I18NPropertyDemo2<T>();

    @JsonAnySetter
    public void set(String key, T value) {
        I18NPropertyDemo2.Content<T> c = new I18NPropertyDemo2.Content<T>();
        c.i18n = new Locale(key);
        c.val = value;
        names.add(c);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        String delim = "";
        for(I18NPropertyDemo2.Content<T> elem: this.names) {
            res.append(delim);
            delim = ", ";
            res.append(String.valueOf(elem));

        }
        res.append("]");

        return res.toString();
    }
}
