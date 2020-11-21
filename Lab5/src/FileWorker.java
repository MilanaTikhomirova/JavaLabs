import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FileWorker {
    private String data;
    private static String inputFile = "text.txt";
    private static String outputFile = "text.txt";;
    private String selector;
    private RandomAccessFile randomAccessFile;
    private BufferedReader bufferedReader;

    public FileWorker() throws UnsupportedEncodingException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in, "Utf8"));
    }

    public void selectionMenu() throws IOException {
        while (true){
            printSelectionMenu();
            selector = bufferedReader.readLine();
            if(selector.compareTo("1") == 0)
                writeToFile();
            if(selector.compareTo("2") == 0)
                editMenu();
            if(selector.compareTo("3") == 0){
                readTextFromFile();
                System.out.println(changeWords());
            }
            if(selector.compareTo("4") == 0)
                return;

        }

    }

    public void editMenu() throws IOException {
        printEditMenu();
        selector = bufferedReader.readLine();
        if(selector.compareTo("1") == 0)
            writeToStart();
        if(selector.compareTo("2") == 0)
            writeToEnd();
        if(selector.compareTo("3") == 0)
            writeToRandomPosition();
        if(selector.compareTo("4") == 0)
            return;
    }

    public void printSelectionMenu(){
        System.out.println("1. Ввести текст и записать его в файл");
        System.out.println("2. Редактировать текст в файле");
        System.out.println("3. Прочитать текст из файла и выполнить задание");
        System.out.println("4. Выход");
    }

    public void printEditMenu(){
        System.out.println("1. Добавить в начало файла");
        System.out.println("2. Добавить в конец файла");
        System.out.println("3. Добавить в произвольное место");
        System.out.println("4. Назад");
    }

    public String getDataFromConsole() throws IOException {
        System.out.println("Введите текст");
        return bufferedReader.readLine();
    }

    public void writeToFile() throws IOException {
        data = getDataFromConsole();
        randomAccessFile = new RandomAccessFile(new File(outputFile), "rw");
        randomAccessFile.writeBytes(data);
        randomAccessFile.close();
    }

    public void writeToStart() throws IOException {
        String temp = getDataFromConsole();
        randomAccessFile = new RandomAccessFile(new File(outputFile), "rw");
        data = randomAccessFile.readLine();
        randomAccessFile.seek(0);
        randomAccessFile.writeBytes(temp);
        randomAccessFile.seek(temp.length());
        randomAccessFile.writeBytes(data);
        randomAccessFile.close();
    }

    public void writeToEnd() throws IOException {
        data = getDataFromConsole();
        randomAccessFile = new RandomAccessFile(new File(outputFile),"rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(data);
        randomAccessFile.close();
    }

    public void writeToRandomPosition() throws IOException {
        String temp = getDataFromConsole();
        System.out.println("Введите позицию");
        int position = Integer.parseInt(bufferedReader.readLine());
        randomAccessFile = new RandomAccessFile(new File(outputFile), "rw");
        randomAccessFile.seek(position);
        data = randomAccessFile.readLine();
        randomAccessFile.seek(position);
        randomAccessFile.writeBytes(temp);
        randomAccessFile.writeBytes(data);
        randomAccessFile.close();
    }

    public void readTextFromFile() throws IOException {
        randomAccessFile = new RandomAccessFile(new File(inputFile), "r");
        data = randomAccessFile.readLine();
        randomAccessFile.close();
    }

    public String changeWords(){
        StringTokenizer tokenizer = new StringTokenizer(data);
        int count = tokenizer.countTokens();
        String[] tokens = new String[count];
        for(int i = 0; i < count; i++){
            tokens[i] = tokenizer.nextToken();
        }
        String temp;
        for(int i = 0; i < count-1; i+=2){
            temp = tokens[i];
            tokens[i] = tokens[i+1];
            tokens[i+1] = temp;
        }
        String newString = "";
        for(int i=0;i<count;i++){
            newString += tokens[i] + " ";
        }
        return newString;
    }



}
