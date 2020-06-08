import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel{

    private JLabel fileStatus;

    public StatusBar(){
        setLayout(new BorderLayout());
        fileStatus = new JLabel();
        setFileStatus("new");
        add(fileStatus, BorderLayout.LINE_END);
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus.setText(fileStatus + "  ");
    }
}
