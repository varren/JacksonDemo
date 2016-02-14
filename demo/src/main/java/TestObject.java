import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestObject {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonCreator
    public TestObject() {
    }

    @Override
    public String toString() {
        return "TestObject(id=" + id + ", title=" + title + ")";
    }
}
