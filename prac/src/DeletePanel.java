import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DeletePanel {
    JPanel panel;

    public DeletePanel() {
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "insert Panel")); // 테두리 추가
    }
}

