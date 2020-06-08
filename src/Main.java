import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        SwingUtilities.invokeLater(this::createGUI);
    }

    protected void createGUI()
    {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new BorderLayout());

        // 3 main classes
        StatusBar statusBar = new StatusBar();
        EditorTextArea editorTextArea = new EditorTextArea(20,40,statusBar);
        MenuBar menu = new MenuBar(statusBar,editorTextArea,jf);

        jf.add(menu, BorderLayout.PAGE_START);
        jf.add(editorTextArea, BorderLayout.CENTER);
        jf.add(statusBar, BorderLayout.PAGE_END);
        jf.pack();

        jf.setTitle("Simple editor - untitled");
        jf.setVisible(true);
    }
}