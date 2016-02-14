import com.fasterxml.jackson.annotation.*;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
// Will need to set mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
// for your ObjectMapper to make @JsonRootName(value = "name") work
@JsonRootName(value = "name")

//Need shape=JsonFormat.Shape.OBJECT to suppress default array deserialization behavior
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class I18NProperty<T> extends ArrayList<I18NProperty.Content<T>> {

    public static class Content<T> {
        public Locale i18n;
        public T val;

        @Override
        public String toString() {
            return "Content(i18n=" + i18n.toString() + " val=" + String.valueOf(val) + ")";
        }
    }

    @JsonAnySetter
    public void set(String key, T value) {
        Content<T> c = new Content<T>();
        c.i18n = new Locale(key);
        c.val = value;
        this.add(c);
    }

    @JsonAnyGetter
    public Map<String,T> getProperties() {
        Map<String,T> result = new HashMap<String, T>();
        for(Content<T> elem: this){
            result.put(elem.i18n.getLanguage(), elem.val);
        }
        return result;
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
