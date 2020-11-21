import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

public class GardenGUI extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton editButton;
    private JButton sortButton;
    private JTextField findTextField;
    private JTextField nameTextField;
    private JTextField familyTextField;
    private JTextField genusTextField;
    private JTextField ageTextField;
    private JTextField kindTextField;
    private JButton backButton;
    private JButton saveButton;
    private Garden garden;
    private Plant plantTemp;
    private int temp;

    private Object[] columnHeaders = new String[]{"Name","Family","Genus","Kind","Age"};
    private DefaultTableModel tableModel;

    public GardenGUI(){
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    writeToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        garden = new Garden();
        backButton.addActionListener(new BackButtonActionListener());
        findButton.addActionListener(new FindButtonActionListener());
        deleteButton.addActionListener(new DeleteButtonActionListener());
        addButton.addActionListener(new AddButtonActionListener());
        editButton.addActionListener(new EditButtonActionListener());
        saveButton.addActionListener(new SaveButtonActionListener());
        sortButton.addActionListener(new SortButtonActionListener());
        try {
            createTable();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
      GardenGUI gardenGUI = new GardenGUI();
      gardenGUI.setVisible(true);
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("garden.txt"));
        FileHeader header;
        header = (FileHeader) inputStream.readObject();
        for(int i=0;i<header.getCount();i++){
            garden.getPlants().add((Plant)inputStream.readObject());
        }
        inputStream.close();
    }

    public void writeToFile() throws IOException {
        FileHeader header = new FileHeader();
        header.setCount(garden.getPlants().size());
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("garden.txt"));
        outputStream.writeObject(header);
        for(Plant plant:garden.getPlants()){
            outputStream.writeObject(plant);
        }
    }

    public void createTable() throws IOException, ClassNotFoundException {
        readFromFile();
        table1.setRowSelectionAllowed(true);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(columnHeaders);
        if(garden.getPlants().size()>0)
            for (Plant plant: garden.getPlants()){
                tableModel.addRow(plant.toObject());
            }
        table1.setModel(tableModel);
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }


    class SortButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            garden.sort();
            tableModel.getDataVector().removeAllElements();
            for (Plant plant: garden.getPlants()){
                tableModel.addRow(plant.toObject());
            }
        }
    }

    class SaveButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!validateTextBoxes())
                return;
            Plant plant = new Plant();
            plant.setName(nameTextField.getText());
            plant.setFamily(familyTextField.getText());
            plant.setKind(kindTextField.getText());
            plant.setGenus(genusTextField.getText());
            plant.setAge(Integer.parseInt(ageTextField.getText()));
            plantToRow(getTemp(), plant.toObject());
            garden.editPlant(garden.getPlants().get(getTemp()), plant);
            clearTextFields();
        }
    }

    public boolean validateTextBoxes(){
        if(nameTextField.getText().equals("") || familyTextField.getText().equals("")
                || genusTextField.getText().equals("") || kindTextField.getText().equals("") || ageTextField.getText().equals(""))
            return false;
        try{
            int i = Integer.parseInt(ageTextField.getText());
        }catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    class EditButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int selected = table1.getSelectedRow();
            Plant p = new Plant(plantFromRow(selected));
            setTemp(selected);
            nameTextField.setText(p.getName());
            familyTextField.setText(p.getFamily());
            genusTextField.setText(p.getGenus());
            kindTextField.setText(p.getKind());
            ageTextField.setText(String.valueOf(p.getAge()));
        }
    }

    public void plantToRow(int row, Object[] objects){
            tableModel.setValueAt(objects[0], row, 0);
            tableModel.setValueAt(objects[1], row, 1);
            tableModel.setValueAt(objects[2], row, 2);
            tableModel.setValueAt(objects[3], row, 3);
            tableModel.setValueAt(objects[4], row, 4);

    }

    public Plant plantFromRow(int selected){
        Plant p = new Plant();
        p.setName((String) tableModel.getValueAt(selected, 0));
        p.setFamily((String) tableModel.getValueAt(selected,1));
        p.setGenus((String) tableModel.getValueAt(selected,2));
        p.setKind((String) tableModel.getValueAt(selected, 3));
        p.setAge((Integer) tableModel.getValueAt(selected, 4));
        return p;
    }

    public void clearTextFields(){
        nameTextField.setText("");
        familyTextField.setText("");
        ageTextField.setText("");
        genusTextField.setText("");
        kindTextField.setText("");
    }
    class AddButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Plant plant = new Plant();
            if(ageTextField.getText().equals("") || familyTextField.getText().equals("") || nameTextField.getText().equals("")
                    || kindTextField.getText().equals("") || genusTextField.getText().equals(""))
                return;
            plant.setAge(Integer.parseInt(ageTextField.getText()));
            plant.setFamily(familyTextField.getText());
            plant.setName(nameTextField.getText());
            plant.setGenus(genusTextField.getText());
            plant.setKind(kindTextField.getText());

            garden.addPlant(plant);
            tableModel.addRow(plant.toObject());

            clearTextFields();
        }
    }
    class DeleteButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int selected = table1.getSelectedRow();
            Plant p = new Plant(plantFromRow(selected));

            garden.getPlants().remove(selected);
            tableModel.removeRow(selected);
        }
    }
    class BackButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.getDataVector().removeAllElements();
            for (Plant plant: garden.getPlants()){
                tableModel.addRow(plant.toObject());
            }
            tableModel.fireTableDataChanged();
        }
    }
    class FindButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(findTextField.getText().equals(""))
                return;
            int age;
            try {
                age = Integer.parseInt(findTextField.getText());
            }catch (NumberFormatException ex){
                return;
            }
            LinkedList<Plant> plants = garden.findByAge(age);
            tableModel.getDataVector().removeAllElements();
            tableModel.fireTableDataChanged();
            for(Plant plant: plants){
                tableModel.addRow(plant.toObject());
            }
            findTextField.setText("");
        }
    }
}
