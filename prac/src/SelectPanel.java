import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectPanel {
    JPanel panel;
    JCheckBox name;
    JCheckBox ssn;
    JCheckBox bdate;
    JCheckBox address;
    JCheckBox sex;
    JCheckBox salary;
    JCheckBox superSsn;
    JCheckBox dno;

    public SelectPanel() {
        panel = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        panel.setBorder(BorderFactory.createTitledBorder(border, "select Panel")); // 테두리 추가

        name = new JCheckBox("Name");
        ssn = new JCheckBox("Ssn");
        bdate = new JCheckBox("Bdate");
        address = new JCheckBox("Address");
        sex = new JCheckBox("Sex");
        salary = new JCheckBox("Salary");
        superSsn = new JCheckBox("Super_ssn");
        dno = new JCheckBox("Dno");

        panel.add(name);
        panel.add(ssn);
        panel.add(bdate);
        panel.add(address);
        panel.add(sex);
        panel.add(salary);
        panel.add(superSsn);
        panel.add(dno);
    }

    public void getSelect() {
        SQLManager.selectList = new ArrayList<>();
        if (name.isSelected()) {
            SQLManager.selectList.add("Name");
        }
        if (ssn.isSelected()) {
            SQLManager.selectList.add("Ssn");
        }
        if (bdate.isSelected()) {
            SQLManager.selectList.add("Bdate");
        }
        if (address.isSelected()) {
            SQLManager.selectList.add("Address");
        }
        if (sex.isSelected()) {
            SQLManager.selectList.add("Sex");
        }
        if (salary.isSelected()) {
            SQLManager.selectList.add("Salary");
        }
        if (superSsn.isSelected()) {
            SQLManager.selectList.add("Super_ssn");
        }
        if (dno.isSelected()) {
            SQLManager.selectList.add("Dno");
        }
    }
}

