import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchPanel {
    JPanel panel;
    JButton button;

    public SearchPanel(RangePanel rangePanel, SelectPanel selectPanel, TablePanel tablePanel) {
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "search Panel")); // 테두리 추가

        button = new JButton("Search");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPanel.getSelect();
                rangePanel.getWhere();
                try {
                    SQLManager.runSQL(SQLManager.SELECT);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                tablePanel.makeSelectTable();
            }
        });

        panel.add(button);
    }
}

