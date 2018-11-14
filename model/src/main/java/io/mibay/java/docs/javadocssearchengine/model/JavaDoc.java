package io.mibay.java.docs.javadocssearchengine.model;

public class JavaDoc {

    private String id;
    private String path;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JavaDoc{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
