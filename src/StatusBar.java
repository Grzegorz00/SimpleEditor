import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel{

    private JPanel panel = new JPanel();
    private JLabel fileStatus, fg, bg, textSize;
    private ColorButtonIcon fgIcon, bgIcon;

    public StatusBar(){
        setLayout(new BorderLayout());
        panel.setLayout(new FlowLayout());

        fileStatus = new JLabel();
        setFileStatus("new");

        fgIcon = new ColorButtonIcon(Color.black);
        fg = new JLabel("fg", fgIcon,0);
        panel.add(fg);

        bgIcon = new ColorButtonIcon(Color.white);
        bg = new JLabel("bg", bgIcon,0);
        panel.add(bg);

        textSize = new JLabel("12");
        panel.add(textSize);

        add(panel, BorderLayout.LINE_START);
        add(fileStatus, BorderLayout.LINE_END);
    }

    public void setFgIcon(Color color) {
        this.fg.setIcon(new ColorButtonIcon(color));
    }

    public void setBgIcon(Color color) {
        this.bg.setIcon(new ColorButtonIcon(color));
    }
    public void setSize(int size){
        this.textSize.setText(String.valueOf(size));
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus.setText(fileStatus + "  ");
    }
}
