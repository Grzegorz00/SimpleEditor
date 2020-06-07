import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel{

    JLabel fileStatus;

    public StatusBar(){
        setLayout(new BorderLayout());
        fileStatus = new JLabel("New");
        //fileStatus.setText("elo");
        add(fileStatus, BorderLayout.LINE_END);
    }
}
