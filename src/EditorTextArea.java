import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class EditorTextArea extends JPanel{

    private JScrollPane scrollPane;
    JTextArea textArea;
    private StatusBar statusBar;

    public EditorTextArea(int rows, int columns, StatusBar statusBar){
        textArea = new JTextArea(rows,columns);
        //???????????????
        //???????????????
        //???????????????
        // jak powiekszysz ekran nie powieksza się pole tekstowe
        //
        //fajniej by było jak byś zrobił funckje set Foreground text area jako prywatne
        //???????????????
        //???????????????

        textAreaListenner();


        scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        this.statusBar = statusBar;
    }

    private void textAreaListenner() {
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

    /*public void setForeground(Color color){
        textArea.setForeground(Color.green);
    }

    public void setForeground(Color color){
        textArea.setBackground(Color.green);
    }*/

    public void setFontSize(int size){
        textArea.setFont(getFont().deriveFont((float)size));
    }

    public void appendText(String text){
        textArea.append(text);
    }
    public void insertTextCarrot(String text){
        textArea.insert(text,textArea.getCaretPosition());
    }

    public String getText() {
        return textArea.getText();
    }
}
