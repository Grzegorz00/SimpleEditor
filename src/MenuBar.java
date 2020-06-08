import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuBar extends JMenuBar implements ActionListener {

    private JMenuItem[] arrFile;
    private JMenuItem[] arrEdit;
    private JMenuItem[] arrOptions;
    private JMenuItem[] arrAddresses;
    private JMenuItem[] arrColorItemsFor;
    private JMenuItem[] arrColorItemsBack;
    private JMenuItem[] arrFontSizeItems;

    // variable used in "File" menu
    private String filePath = null;

    // variables used in "Edit" and "Options" menu
    private String[] addressesString;
    private Color[] colors;
    private int[] fontSize;

    // these classes will be initialise in the constructor
    StatusBar statusBar;
    EditorTextArea editorTextArea;
    JFrame myJFrame;

    public MenuBar(StatusBar statusBar, EditorTextArea editorTextArea, JFrame myJFrame){

        //adding main classes
        this.statusBar = statusBar;
        this.editorTextArea = editorTextArea;
        this.myJFrame = myJFrame;

        fillStringAddresses();

        /*         Creating all menus         */

        //File item
        // all menus and submenus
        JMenu menu = new JMenu("File");
        createFileItems();
        addJMenuItems(menu, arrFile, true);
        add(menu);

        //Edit item
        menu = new JMenu("Edit");
        createEditItems();
        addJMenuItems(menu, arrEdit, false);
        add(menu);

        //Options item
        menu = new JMenu("Options");
        createOptionsItems();
        addJMenuItems(menu, arrOptions, false);
        add(menu);
    }

    private void fillStringAddresses() {
        addressesString = new String[]{
                "//work adress",
                "//home adress",
                "//school adress"
        };
    }

    /*    Creates and fills all menus in the MenuBar    */
    //main creating methods
    //adding actonListener's
    private void createFileItems(){

        //creating new items
        JMenuItem item0 = new JMenuItem("Open",'O');
        JMenuItem item1 = new JMenuItem("Save",'S');
        JMenuItem item2 = new JMenuItem("Save As",'a');
        JMenuItem item3 = new JMenuItem("Exit",'x');

        // setting shortcuts
        item0.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        item1.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        item2.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        item3.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        //adding items to the arrey
        arrFile = new JMenuItem[]{
            item0,
            item1,
            item2,
            item3
        };

        // adding actions
        for (JMenuItem item : arrFile)
            item.addActionListener(this);

    }

    private void createEditItems() {

        //creating items for submenu "Adresses"
        JMenuItem item00 = new JMenuItem("Work",'W');
        JMenuItem item01 = new JMenuItem("Home",'H');
        JMenuItem item02 = new JMenuItem("School",'S');

        // setting shortcuts in submenu
        item00.setAccelerator(KeyStroke.getKeyStroke("ctrl shift W"));
        item01.setAccelerator(KeyStroke.getKeyStroke("ctrl shift H"));
        item02.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));

        //adding array with submenu items
        arrAddresses = new JMenuItem[]{
                item00,
                item01,
                item02
        };

        // adding actions
        for (JMenuItem item : arrAddresses)
            item.addActionListener(this);

        //adding menu item for "edit" menu
        JMenu item0 = new JMenu("Adresses");
        addJMenuItems(item0, arrAddresses,false);

        //adding items to the "edit" menu
        arrEdit = new JMenuItem[]{item0};
    }

    private void createOptionsItems(){

        arrColorItemsFor = makeAllColorItemsArray(); // filling the "allColorItems" array with colors
        JMenu item0 = new JMenu("Foreground"); // creating submenu with foreground colors
        addJMenuItems(item0,arrColorItemsFor, false); // adding colors from "allColorItems" array to the "item0" submenu

        arrColorItemsBack = makeAllColorItemsArray();
        JMenu item1 = new JMenu("Background");
        addJMenuItems(item1,arrColorItemsBack, false);

        makeAllFontSizeItemArray();
        JMenu item2 = new JMenu("Font size");
        addJMenuItems(item2,arrFontSizeItems, false);

        // adding actions
        for (JMenuItem item : arrColorItemsFor)
            item.addActionListener(this);
        for (JMenuItem item : arrColorItemsBack)
            item.addActionListener(this);
        for (JMenuItem item : arrFontSizeItems)
            item.addActionListener(this);

        //adding items do the "options" menu
        arrOptions = new JMenuItem[]{
                item0,
                item1,
                item2
        };
    }

    //additional methods
    public JMenuItem[] makeAllColorItemsArray(){
        String[] colorsString = new String[]{
                "Green",
                "Orange",
                "Red",
                "Black",
                "White",
                "Yellow",
                "Blue"
        };
        colors = new Color[]{
                Color.green,
                Color.orange,
                Color.red,
                Color.black,
                Color.white,
                Color.yellow,
                Color.blue
        };

        JMenuItem[] arrColorItems =  new JRadioButtonMenuItem[colors.length];

        for (int i = 0; i < colors.length; i++){
            arrColorItems[i] = new JRadioButtonMenuItem(colorsString[i], new ColorButtonIcon(colors[i]));
            arrColorItems[i].setForeground(colors[i]);
        }

        ButtonGroup buttonGroup = new ButtonGroup();
        for(JMenuItem item : arrColorItems){
            buttonGroup.add(item);
        }
        return arrColorItems;
    }

    public void makeAllFontSizeItemArray(){

        fontSize = new int[9];
        for(int i = 0; i < fontSize.length; i++){
            fontSize[i] = 8 + i*2;
        }

        arrFontSizeItems = new JMenuItem[9];
        for(int i = 0; i < arrFontSizeItems.length; i++){
            arrFontSizeItems[i] = new JMenuItem(fontSize[i] + " pts");
            arrFontSizeItems[i].setFont(getFont().deriveFont((float)fontSize[i]));
        }

    }

    public void addJMenuItems(JMenu menu, JMenuItem[] arrMI, boolean separator){
        for(int i = 0; i< arrMI.length; i++) {
            if(separator && i == arrMI.length - 1){
                JSeparator jSeparator = new JSeparator();
                jSeparator.setBackground(Color.RED);
                menu.add(jSeparator);
            }
            menu.add(arrMI[i]);
        }
    }

    /*    Action Performed methods    */
    @Override
    public void actionPerformed(ActionEvent e) {

        arrFileActions(e);
        arrEditActions(e);
        arrOptionsActions(e);

    }

    public void arrFileActions(ActionEvent e){
        Object source = e.getSource();


        if(source == arrFile[0]){
            // Open file
            openFile();
            myJFrame.setTitle("Simple editor - " + filePath);
        } else if(source == arrFile[1]){
            // Save file
            savingFile(false);
            myJFrame.setTitle("Simple editor - " + filePath);
        } else if(source == arrFile[2]){
            // Save as file
            savingFile(true);
            myJFrame.setTitle("Simple editor - " + filePath);
        }
        if(source == arrFile[3]){
            //Exit
            myJFrame.dispose();
        }
    }

    private void arrEditActions(ActionEvent e) {
        Object source = e.getSource();

        for(int i = 0; i < arrAddresses.length; i++) {
            if (source == arrAddresses[i]) {
                editorTextArea.insertTextCarrot(addressesString[i]);
                statusBar.setFileStatus("modified");
                i = addressesString.length;
            }
        }

    }

    private void arrOptionsActions(ActionEvent e){
        Object source = e.getSource();

        //foreground actions
        for(int i = 0; i < arrColorItemsFor.length; i++) {
            if (source == arrColorItemsFor[i]) {
                editorTextArea.textArea.setForeground(colors[i]);
                statusBar.setFgIcon(colors[i]);
                i = arrColorItemsFor.length;
            }
        }

        //background actions
        for(int i = 0; i < arrColorItemsBack.length; i++) {
            if (source == arrColorItemsBack[i]) {
                editorTextArea.textArea.setBackground(colors[i]);
                statusBar.setBgIcon(colors[i]);
                i = arrColorItemsBack.length;
            }
        }

        //font size actions
        for(int i = 0; i < arrFontSizeItems.length; i++) {
            if (source == arrFontSizeItems[i]) {
                editorTextArea.setFontSize(fontSize[i]);
                statusBar.setSize(fontSize[i]);
                i = arrFontSizeItems.length;
            }
        }

    }


    /*    Methods used in "File" menu for opening and saving files    */
    private void openFile(){
        JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            filePath = fc.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }
        try {
            Scanner scanner = new Scanner(new File(filePath));
            editorTextArea.setText("");
            while (scanner.hasNext()){
                editorTextArea.appendText(scanner.nextLine() + "\n");
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        statusBar.setFileStatus("saved");
    }

    private void savingFile(boolean saveAs){
        if(filePath == null || saveAs){
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
                filePath = fc.getSelectedFile().getAbsolutePath();
        }
        if(filePath != null){
            File file = new File(filePath);
            try {
                PrintWriter pw = new PrintWriter(file);
                Scanner scanner = new Scanner(editorTextArea.getText());
                while (scanner.hasNext())
                    pw.println(scanner.nextLine());
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        statusBar.setFileStatus("saved");
    }
}
