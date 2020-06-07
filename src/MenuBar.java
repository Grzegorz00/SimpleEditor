import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

        /*//Options item
        menu = new JMenu("Options");
        arrMI = createOptionsItems();
        addJMenuItems(menu, arrMI, false);*/

        add(menu);
    }

    /*ArrayList<ActionListener> actionListeners = new ArrayList<>();

    public void addActionListener(ActionListener al){
        actionListeners.add(al);
    }*/

    private JMenuItem[] createFileItems(){

        //creating new items
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("Save");
        JMenuItem item3 = new JMenuItem("Save As");
        JMenuItem item4 = new JMenuItem("Exit");

        // setting shortcuts
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        //setting mnemonics
        item1.setMnemonic('O');
        item2.setMnemonic('S');
        item3.setMnemonic('a');
        item4.setMnemonic('x');


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
        JMenuItem item00 = new JMenuItem("Praca");
        JMenuItem item01 = new JMenuItem("Dom");
        JMenuItem item02 = new JMenuItem("Szkola");

        // setting shortcuts in submenu
        item00.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK + InputEvent.SHIFT_DOWN_MASK
        ));
        item01.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK + InputEvent.SHIFT_DOWN_MASK
        ));
        item02.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK + InputEvent.SHIFT_DOWN_MASK
        ));

        //setting mnemonics in submenu
        item00.setMnemonic('P');
        item01.setMnemonic('D');
        item02.setMnemonic('S');

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

    /*private JMenuItem[] createOptionsItems(){


    }*/

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
