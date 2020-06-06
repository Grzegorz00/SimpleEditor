import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    JLabel fileStatus;

    public StatusBar(){
        setLayout(new BorderLayout());
        fileStatus = new JLabel();
        fileStatus.setText("New ??");
        add(fileStatus, BorderLayout.LINE_END);
    }

}
