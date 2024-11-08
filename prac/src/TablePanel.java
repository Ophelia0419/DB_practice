import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel {
    JPanel panel;
    JTable table;
    JScrollPane jScrollPane;
    DefaultTableModel model;

    public TablePanel() {
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "table Panel")); // 테두리 추가
    }

    public void makeSelectTable() {
        if (jScrollPane != null) {
            panel.remove(jScrollPane);
        }
        model = null;
        model = new DefaultTableModel(SQLManager.selectList.toArray(), 0);
        for (ArrayList<String> rowData : SQLManager.result) {
            model.addRow(rowData.toArray());
        }
        table = new JTable(model);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setPreferredSize(new Dimension(800, 400));
        table.setBackground(Color.white);
        table.setRowHeight(30);
        jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(800, 400));
        panel.revalidate();
        panel.repaint();
        panel.add(jScrollPane);
    }
}
