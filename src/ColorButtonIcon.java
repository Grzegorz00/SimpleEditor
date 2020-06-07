import javax.swing.*;
import java.awt.*;

public class ColorButtonIcon implements Icon{

    private Color color;
    private int width = 10;
    private int height = 10;

    public ColorButtonIcon(Color color){
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x,y,width,height);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
