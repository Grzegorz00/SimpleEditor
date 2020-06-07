import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {

    JMenu menu;


    public MenuBar(){
        JMenuItem arrMI[] = createFileItems();
        addJMenuItems(arrMI, "File", true);
        add(menu);
    }

    /*ArrayList<ActionListener> actionListeners = new ArrayList<>();

    public void addActionListener(ActionListener al){
        actionListeners.add(al);
    }*/

    public JMenuItem[] createFileItems(){

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

    public void addJMenuItems(JMenuItem arrMI [], String titleOfMenu, boolean separator){
        menu = new JMenu(titleOfMenu);
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
