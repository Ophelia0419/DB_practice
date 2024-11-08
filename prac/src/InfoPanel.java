import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InfoPanel {
    JPanel panel;

    public InfoPanel() {
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "insert Panel")); // 테두리 추가
    }
}
