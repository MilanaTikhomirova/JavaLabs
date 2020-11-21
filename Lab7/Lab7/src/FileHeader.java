import java.io.Serializable;

public class FileHeader implements Serializable {
    private String header = "created by Milana Tikhomirova";
    private int count;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return getHeader() + "\n" + getCount() + "\n";
    }
}
