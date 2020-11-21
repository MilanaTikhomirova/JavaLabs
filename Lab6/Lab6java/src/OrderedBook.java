import java.io.Serializable;

public class OrderedBook implements Serializable {
    private String authorName;
    private String title;

    public OrderedBook(){}

    public OrderedBook(String authorName, String title){
        this.setAuthorName(authorName);
        this.setTitle(title);
    }
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getAuthorName() + " " + getTitle();
    }
}
