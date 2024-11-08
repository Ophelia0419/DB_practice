import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablePanel {
    JPanel panel;
    JTable table;
    JScrollPane jScrollPane;
    DefaultTableModel model;
    JButton deleteButton;

    public TablePanel() {
        panel = new JPanel(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "table Panel"));

        // 테이블 초기화
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class; // 첫 번째 열에 체크박스를 추가
                }
                return String.class;
            }
        };

        table = new JTable(model);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);
        jScrollPane = new JScrollPane(table);
        panel.add(jScrollPane, BorderLayout.CENTER);

        // 삭제 버튼 추가
        deleteButton = new JButton("Delete Selected");
        panel.add(deleteButton, BorderLayout.SOUTH);

        // 삭제 버튼 액션 리스너
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> deletedIds = new ArrayList<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    Boolean isSelected = (Boolean) model.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        String ssn = model.getValueAt(i, 2).toString(); // SSN이 두 번째 열에 있다고 가정
                        deletedIds.add(ssn);
                    }
                }

                if (!deletedIds.isEmpty()) {
                    for (String ssn : deletedIds) {
                        SQLManager.whereList = new ArrayList<>();
                        SQLManager.whereList.add("Ssn");
                        SQLManager.whereList.add(ssn);

                        try {
                            SQLManager.runSQL(SQLManager.DELETE);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(panel, "Error deleting record: " + ex.getMessage());
                        }
                    }
                    JOptionPane.showMessageDialog(panel, "Selected records deleted successfully!");
                    // 삭제된 행을 테이블에서 제거
                    for (int i = model.getRowCount() - 1; i >= 0; i--) {
                        if ((Boolean) model.getValueAt(i, 0)) {
                            model.removeRow(i);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select rows to delete.");
                }
            }
        });
    }

    public void makeSelectTable() {
        if (jScrollPane != null) {
            panel.remove(jScrollPane);
        }
        model = null;
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }
        };

        ArrayList<String> columns = new ArrayList<>();
        columns.add("Select");
        columns.addAll(SQLManager.selectList);
        model.setColumnIdentifiers(columns.toArray());

        for (ArrayList<String> rowData : SQLManager.result) {
            ArrayList<Object> newRow = new ArrayList<>();
            newRow.add(false); // 체크박스 초기값
            newRow.addAll(rowData);
            model.addRow(newRow.toArray());
        }

        table.setModel(model);
        panel.revalidate();
        panel.repaint();
        panel.add(jScrollPane);
    }
}
