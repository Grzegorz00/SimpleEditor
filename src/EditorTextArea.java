import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class EditorTextArea extends JPanel{

    JTextArea textArea;

    private StatusBar statusBar;

    public EditorTextArea(StatusBar statusBar){
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textAreaListener();
        add(new JScrollPane(textArea),BorderLayout.CENTER);
        setPreferredSize(new Dimension(400,300));
        this.statusBar = statusBar;
    }

    private void textAreaListener() {
        textArea.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        statusBar.setFileStatus("modified");
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        statusBar.setFileStatus("modified");
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        statusBar.setFileStatus("modified");
                    }
                }
        );
    }

    public String getText() {
        return textArea.getText();
    }

    //setters
    public void setText(String text){
        textArea.setText(text);
    }

    public void appendText(String text){
        textArea.append(text);
    }
    public void insertTextCarrot(String text){
        textArea.insert(text,textArea.getCaretPosition());
    }

    public void setFontSize(int size){
        textArea.setFont(getFont().deriveFont((float)size));
    }
}
