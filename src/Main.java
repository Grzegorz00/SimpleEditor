import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        SwingUtilities.invokeLater(() -> createGUI());
    }

    protected void createGUI()
    {
        JFrame jf = new JFrame();
        jf.setTitle("Simple editor - ?????????");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new BorderLayout());

        jf.setVisible(true);
    }
}