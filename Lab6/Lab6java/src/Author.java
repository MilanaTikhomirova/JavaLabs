import java.io.Serializable;

public class Author implements Serializable {
    private String author;
    private String[] bookTitles;

    public Author(){}

    public Author(String author, String[] bookTitles) {
        this.setAuthor(author);
        this.setBookTitles(bookTitles);
    }

    public String[] getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(String[] bookTitles) {
        this.bookTitles = bookTitles;
    }

    @Override
    public String toString() {
        StringBuilder author = new StringBuilder();
        author.append(getAuthor() + "\nBooks: ");
        for(int i =0; i<getBookTitles().length; i++) {
            author.append(getBookTitles()[i]);
        }
        return author.toString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
