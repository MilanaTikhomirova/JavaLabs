import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LibraryGUI extends JFrame {
    private JList<String> authorsList;
    private JList<String> booksList;
    private JList<String> ordersList;
    private JTextField surnameField;
    private JTextField nameField;
    private JButton orderButton;
    private JPanel mainPanel;
    private JLabel mainLabel;
    private Author[] authors;
    private DefaultListModel<String> booksListModel;
    private DefaultListModel<String> ordersListModel;
    private DefaultListModel<String> authorsListModel;

    public LibraryGUI(){
        super("Библиотека");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.orderButton.addActionListener(new OrderButtonActionListener());
        this.authorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.authorsList.setPrototypeCellValue("Увеличенный");
        this.authorsListModel = new DefaultListModel<>();
        this.booksListModel = new DefaultListModel<String>();
        this.ordersListModel = new DefaultListModel<>();
        this.ordersList.setModel(ordersListModel);
        this.booksList.setModel(booksListModel);
        this.authorsList.setModel(authorsListModel);
        this.authorsList.addMouseListener(new AuthorsListDoubleClickListener());
        this.booksList.addMouseListener(new BooksListDoubleClickListener());
        this.ordersList.addMouseListener(new OrdersListDoubleClick());

        authorsInitialization();
        this.pack();
    }

    class BooksListDoubleClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
                OrderedBook orderedBook = new OrderedBook(authorsList.getSelectedValue(), booksList.getSelectedValue());
                ordersListModel.addElement(orderedBook.toString());
            }
        }
    }
    class AuthorsListDoubleClickListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
                booksListModel.removeAllElements();
                for (Author author:authors) {
                    if(author.getAuthor().equals(authorsList.getSelectedValue())){
                        for(String str: author.getBookTitles())
                            booksListModel.addElement(str);
                    }
                }
            }
        }
    }
    class OrdersListDoubleClick extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
                ordersListModel.remove(ordersList.getSelectedIndex());
            }
        }
    }


    class OrderButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!validateTextBoxes())
                return;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            stringBuilder.append(surnameField.getText());
            stringBuilder.append(" ");
            stringBuilder.append(nameField.getText());
            stringBuilder.append("\n");

            for(int i = 0; i<ordersListModel.getSize();i++){
                stringBuilder.append(ordersListModel.get(i));
                stringBuilder.append("; ");
            }
            stringBuilder.append("\n");
            try {
                writeOrderToFile(stringBuilder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private boolean validateTextBoxes(){
        if(this.nameField.getText().equals("") || this.surnameField.getText().equals(""))
            return false;
        return true;

    }
    private void writeOrderToFile(String order) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("orders.txt"),"rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(order);
        randomAccessFile.close();
    }

    private void authorsInitialization(){
        authors = new Author[2];
        authors[0]= new Author("George Orwell", new String[]{"1984", " Animal Farm", "Coming Up for Air", "Keep the Aspidistra Flying", "A Clergyman's Daughter"});
        authors[1]= new Author("Leo Tolstoy", new String[]{"Anna Karenina", "War and Peace", "The Death of Ivan Ilyich", "The Kingdom of God Is Within You", "Resurrection"});
        for(Author author:authors){
            authorsListModel.addElement(author.getAuthor());
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LibraryGUI libraryGUI = new LibraryGUI();
        libraryGUI.setVisible(true);
    }
}
