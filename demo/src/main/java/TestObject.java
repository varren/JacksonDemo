import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestObject {

    private int id;
    private String title;

    @JsonCreator
    public TestObject(@JsonProperty("id") int id, @JsonProperty("title") String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TestObject: (id: "+ id +" title: "+ title+")";
    }
}
