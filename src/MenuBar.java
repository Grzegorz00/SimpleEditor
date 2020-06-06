import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    JMenu menu;
    JMenuItem item;
    JMenu file;

    public MenuBar(){
        /*file = new JMenu("file");
        item = new JMenuItem("Elo");
        item.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK)
        );
        file.add(item);

        JSeparator jSeparator = new JSeparator();
        jSeparator.setBackground(Color.RED);
        file.add(jSeparator);
        item = new JMenuItem("MeloPopap");
        file.add(item);
        add(file);*/


        JMenuItem arrMI[] = new JMenuItem[]{
                new JMenuItem("Elo"),
                new JMenuItem("Melo"),
                new JMenuItem("Rafal"),
                new JMenuItem("Rafal"),
                new JMenuItem("kasia"),
                new JMenuItem("robert111"),
                new JMenuItem("Exit")
        };
        addJMenuItems(arrMI, "File", true);
        add(menu);
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
