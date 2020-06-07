import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {

    JMenu menu;
    JMenuItem arrMI[];

    public MenuBar(){

        //File item
        menu = new JMenu("File");
        arrMI = createFileItems();
        addJMenuItems(menu, arrMI, true);
        add(menu);

        //Edit item
        menu = new JMenu("Edit");
        arrMI = createEditItems();
        addJMenuItems(menu, arrMI, false);
        add(menu);

        //Options item
        menu = new JMenu("Options");
        arrMI = createOptionsItems();
        addJMenuItems(menu, arrMI, false);

        add(menu);
    }

    /*ArrayList<ActionListener> actionListeners = new ArrayList<>();

    public void addActionListener(ActionListener al){
        actionListeners.add(al);
    }*/

    private JMenuItem[] createFileItems(){

        //creating new items
        JMenuItem item1 = new JMenuItem("Open",'O');
        JMenuItem item2 = new JMenuItem("Save",'S');
        JMenuItem item3 = new JMenuItem("Save As",'a');
        JMenuItem item4 = new JMenuItem("Exit",'x');

        // setting shortcuts
        item1.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        item2.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        item3.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        item4.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        JMenuItem fileItems[] = new JMenuItem[]{
            item1,
            item2,
            item3,
            item4
        };

        return fileItems;
    }

    private JMenuItem[] createEditItems() {

        //creating items for submenu "Adresy"
        JMenuItem item00 = new JMenuItem("Praca",'P');
        JMenuItem item01 = new JMenuItem("Dom",'D');
        JMenuItem item02 = new JMenuItem("Szkola",'S');

        // setting shortcuts in submenu
        item00.setAccelerator(KeyStroke.getKeyStroke("ctrl shift P"));
        item01.setAccelerator(KeyStroke.getKeyStroke("ctrl shift D"));
        item02.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));


        //array with submenu items
        JMenuItem adresyItems[] = new JMenuItem[]{
                item00,
                item01,
                item02
        };

        //new menuItem for "edit" menu
        JMenu item1 = new JMenu("Adresy");
        addJMenuItems(item1, adresyItems,false);
        //array with items from "edit" menu
        JMenuItem editItems[] = new JMenuItem[]{item1};

        return editItems;
    }

    private JMenuItem[] createOptionsItems(){

        JRadioButtonMenuItem[] colorArray;


        colorArray = makeColorItemsTable();
        JMenu item1 = new JMenu("Foreground");
        addJMenuItems(item1,colorArray, false);

        colorArray = makeColorItemsTable();
        JMenu item2 = new JMenu("Background");
        addJMenuItems(item2,colorArray, false);

        JMenuItem fontArray[] = makeFontSizeTable();
        JMenu item3 = new JMenu("Font size");
        addJMenuItems(item3,fontArray, false);

        //array with items from "options" menu
        JMenuItem optionItems[] = new JMenuItem[]{
                item1,
                item2,
                item3
        };

        return optionItems;
    }

    public JRadioButtonMenuItem[] makeColorItemsTable(){
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

        JRadioButtonMenuItem[] colorArray =  new JRadioButtonMenuItem[colors.length];

        for (int i = 0; i < colors.length; i++){
            colorArray[i] = new JRadioButtonMenuItem(colorsString[i], new ColorButtonIcon(colors[i]));
            colorArray[i].setForeground(colors[i]);
        }

        return colorArray;
    }

    public JMenuItem[] makeFontSizeTable(){

        JMenuItem fontArray[] = new JMenuItem[9];
        for(int i = 0; i < fontArray.length; i++){
            fontArray[i] = new JMenuItem(8 + i*2 + " pts");
            fontArray[i].setFont(getFont().deriveFont((float)8+i*2));
        }

        return fontArray;
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
}
