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
    private JMenuItem arrFile[], arrEdit[], arrOptions[], arrAdresses[], arrCollorItems[], arrFontSizeItems[];
    StatusBar statusBar;
    EditorTextArea editorTextArea;
    JFrame myJFrame;
    private String filePath = null;

    public MenuBar(StatusBar statusBar, EditorTextArea editorTextArea, JFrame myJFrame){
        this.statusBar = statusBar;
        this.editorTextArea = editorTextArea;
        this.myJFrame = myJFrame;

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

        //adding menu item for "edit" menu
        JMenu item0 = new JMenu("Adresses");
        addJMenuItems(item0, arrAdresses,false);

        //adding items to the "edit" menu
        arrEdit = new JMenuItem[]{item0};
    }

    private void createOptionsItems(){

        makeAllColorItemsArray(); // filling the "allColorItems" array with colors
        JMenu item0 = new JMenu("Foreground"); // creating submenu with foreground colors
        addJMenuItems(item0,arrCollorItems, false); // adding colors from "allColorItems" array to the "item0" submenu

        makeAllColorItemsArray();
        JMenu item1 = new JMenu("Background");
        addJMenuItems(item1,arrCollorItems, false);

        makeAllFontSizeItemArray();
        JMenu item2 = new JMenu("Font size");
        addJMenuItems(item2,arrFontSizeItems, false);

        //adding items do the "options" menu
        arrOptions = new JMenuItem[]{
                item0,
                item1,
                item2
        };
    }

    public void makeAllColorItemsArray(){
        String colorsString [] = new String[]{
                "Green",
                "Orange",
                "Red",
                "Black",
                "White",
                "Yellow",
                "Blue"
        };
        Color colors [] = new Color[]{
                Color.green,
                Color.orange,
                Color.red,
                Color.black,
                Color.white,
                Color.yellow,
                Color.blue
        };

        arrCollorItems =  new JRadioButtonMenuItem[colors.length];

        for (int i = 0; i < colors.length; i++){
            arrCollorItems[i] = new JRadioButtonMenuItem(colorsString[i], new ColorButtonIcon(colors[i]));
            arrCollorItems[i].setForeground(colors[i]);
        }

        ButtonGroup buttonGroup = new ButtonGroup();
        for(JMenuItem item : arrCollorItems){
            buttonGroup.add(item);
        }

    }

    public void makeAllFontSizeItemArray(){

        arrFontSizeItems = new JMenuItem[9];
        for(int i = 0; i < arrFontSizeItems.length; i++){
            arrFontSizeItems[i] = new JMenuItem(8 + i*2 + " pts");
            arrFontSizeItems[i].setFont(getFont().deriveFont((float)8+i*2));
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
