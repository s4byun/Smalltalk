package home.smalltalk.models;

public class Course {

    private String id;
    private String name;
    private String description;
    private String url;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int i) {
        index = i;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url= url; }
}
