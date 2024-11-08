import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RangePanel {
    JPanel panel;
    JPanel dynamicPanel;
    JComboBox<String> comboBox;
    JComboBox<String> detailComboBox;
    JTextField textField;

    public RangePanel() {
        panel = new JPanel();
        dynamicPanel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "range Panel")); // 테두리 추가

        String[] attributes = {"None", "Name", "Ssn", "Bdate", "Address", "Sex", "Salary", "Super_ssn", "Dno"};
        comboBox = new JComboBox<>(attributes);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();

                dynamicPanel.removeAll();

                // 첫 번째 콤보박스 선택에 따라 두 번째 콤보박스 항목 설정
                if ("Sex".equals(selected)) {
                    String[] options = {"M", "F"};
                    detailComboBox = new JComboBox<>(options);
                    dynamicPanel.add(detailComboBox);
                } else if ("Dno".equals(selected)) {
                    String[] options = {"1", "4", "5"};
                    detailComboBox = new JComboBox<>(options);
                    dynamicPanel.add(detailComboBox);
                } else if ("None".equals(selected)) {
                    detailComboBox = null;
                    textField = null;
                } else {
                    detailComboBox = null;
                    textField = new JTextField(30);
                    dynamicPanel.add(textField);
                }
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            }
        });

        panel.add(comboBox);
        panel.add(dynamicPanel);
    }

    public void getWhere() {
        if (detailComboBox != null) {
            SQLManager.whereList = new ArrayList<>();
            SQLManager.whereList.add(comboBox.getSelectedItem().toString());
            SQLManager.whereList.add(detailComboBox.getSelectedItem().toString());
        } else if (textField != null) {
            SQLManager.whereList = new ArrayList<>();
            SQLManager.whereList.add(comboBox.getSelectedItem().toString());
            SQLManager.whereList.add(textField.getText());
        } else {
            SQLManager.whereList = null;
        }
    }
}

