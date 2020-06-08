import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuBar extends JMenuBar implements ActionListener {

    private JMenu menu;
    private JMenuItem arrFile[], arrEdit[], arrOptions[], arrAdresses[], arrCollorItemsFor[], arrCollorItemsBack[], arrFontSizeItems[];
    StatusBar statusBar;
    EditorTextArea editorTextArea;
    JFrame myJFrame;
    private String filePath = null;
    private String adressesString[];
    Color colors [];
    int fontSize[];

    public MenuBar(StatusBar statusBar, EditorTextArea editorTextArea, JFrame myJFrame){
        this.statusBar = statusBar;
        this.editorTextArea = editorTextArea;
        this.myJFrame = myJFrame;
        fillStringAdresses();

        //File item
        menu = new JMenu("File");
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

    private void fillStringAdresses() {
        adressesString = new String[]{
                "//work adress",
                "//home adress",
                "//school adress"
        };
    }

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
        arrAdresses = new JMenuItem[]{
                item00,
                item01,
                item02
        };

        // adding actions
        for (JMenuItem item : arrAdresses)
            item.addActionListener(this);

        //adding menu item for "edit" menu
        JMenu item0 = new JMenu("Adresses");
        addJMenuItems(item0, arrAdresses,false);

        //adding items to the "edit" menu
        arrEdit = new JMenuItem[]{item0};
    }

    private void createOptionsItems(){

        arrCollorItemsFor = makeAllColorItemsArray(); // filling the "allColorItems" array with colors
        JMenu item0 = new JMenu("Foreground"); // creating submenu with foreground colors
        addJMenuItems(item0,arrCollorItemsFor, false); // adding colors from "allColorItems" array to the "item0" submenu

        arrCollorItemsBack = makeAllColorItemsArray();
        JMenu item1 = new JMenu("Background");
        addJMenuItems(item1,arrCollorItemsBack, false);

        makeAllFontSizeItemArray();
        JMenu item2 = new JMenu("Font size");
        addJMenuItems(item2,arrFontSizeItems, false);

        // adding actions
        for (JMenuItem item : arrCollorItemsFor)
            item.addActionListener(this);
        for (JMenuItem item : arrCollorItemsBack)
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

    public JMenuItem[] makeAllColorItemsArray(){
        String colorsString [] = new String[]{
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

        JMenuItem arrCollorItems[] =  new JRadioButtonMenuItem[colors.length];

        for (int i = 0; i < colors.length; i++){
            arrCollorItems[i] = new JRadioButtonMenuItem(colorsString[i], new ColorButtonIcon(colors[i]));
            arrCollorItems[i].setForeground(colors[i]);
        }

        ButtonGroup buttonGroup = new ButtonGroup();
        for(JMenuItem item : arrCollorItems){
            buttonGroup.add(item);
        }
        return arrCollorItems;
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

    public void addJMenuItems(JMenu menu, JMenuItem arrMI[], boolean separator){
        for(int i = 0; i< arrMI.length; i++) {
            if(separator && i == arrMI.length - 1){
                JSeparator jSeparator = new JSeparator();
                jSeparator.setBackground(Color.RED);
                menu.add(jSeparator);
            }
            menu.add(arrMI[i]);
        }
    }

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
        } else if(source == arrFile[1]){
            // Save file
            savingFile(filePath);
            myJFrame.setTitle("Simple editor - " + filePath);
        } else if(source == arrFile[2]){
            // Save as file
            savingFile(null);
            myJFrame.setTitle("Simple editor - " + filePath);
        }
        if(source == arrFile[3]){
            //Exit
            myJFrame.dispose();
        }
    }

    private void arrEditActions(ActionEvent e) {
        Object source = e.getSource();

        for(int i = 0; i < arrAdresses.length; i++) {
            if (source == arrAdresses[i]) {
                editorTextArea.insertTextCarrot(adressesString[i]);
                statusBar.setFileStatus("modified");
                i = adressesString.length;
            }
        }

    }

    private void arrOptionsActions(ActionEvent e){
        Object source = e.getSource();

        //foreground actions
        for(int i = 0; i < arrCollorItemsFor.length; i++) {
            if (source == arrCollorItemsFor[i]) {
                editorTextArea.textArea.setForeground(colors[i]);
                statusBar.setFgIcon(colors[i]);
                i = arrCollorItemsFor.length;
            }
        }

        //background actions
        for(int i = 0; i < arrCollorItemsBack.length; i++) {
            if (source == arrCollorItemsBack[i]) {
                editorTextArea.textArea.setBackground(colors[i]);
                statusBar.setBgIcon(colors[i]);
                i = arrCollorItemsBack.length;
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

    private void openFile(){
        JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            filePath = fc.getSelectedFile().getAbsolutePath();
            try {
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    editorTextArea.appendText(scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            statusBar.setFileStatus("saved");
        }
    }

    private void savingFile(String filePath){
        if(filePath == null){
            System.out.println("kurwa dlaczego");
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
                    pw.println(scanner.nextLine() + "\n");
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        statusBar.setFileStatus("saved");
    }
}
