import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int errors = 0;
        try {
            FileWorker fileWorker = new FileWorker();
            fileWorker.selectionMenu();
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            errors++;
        }
    }
}
