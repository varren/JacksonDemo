import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestParentObj<T> {

    @JsonProperty("name")
    public I18NProperty<T> name;

    @JsonCreator
    public TestParentObj() {
    }

    @Override
    public String toString() {
        return "ParentObj: (name=" + String.valueOf(name) + ")";
    }
}
