import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.annotation.Target;

public class DatabaseApplication {

    public static void main(String[] args) {
        // sql 연결
        SQLManager sqlManager = new SQLManager();
        if (!sqlManager.tryConnection()) {
            return ;
        }

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        // 프레임 생성
        int width = 1500;
        int height = 1200;
        JFrame frame = new JFrame("COMPANY");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        RangePanel rangePanel = new RangePanel();
        SelectPanel selectPanel = new SelectPanel();
        TablePanel tablePanel = new TablePanel();
        SearchPanel searchPanel = new SearchPanel(rangePanel, selectPanel, tablePanel);
        InsertPanel insertPanel = new InsertPanel();
        InfoPanel infoPanel = new InfoPanel();
        UpdatePanel updatePanel = new UpdatePanel();
        DeletePanel deletePanel = new DeletePanel();

        JPanel clickPanel = new JPanel(); // selectPanel과 searchPanel 한 줄로 나열
        clickPanel.setLayout(new BoxLayout(clickPanel, BoxLayout.X_AXIS));
        clickPanel.add(selectPanel.panel);
        clickPanel.add(searchPanel.panel);

        JPanel topPanel = new JPanel(); // rangePanel과 clickPanel 한 줄로 나열
        topPanel.setBorder(BorderFactory.createTitledBorder(border, "top Panel")); // 테두리 추가
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(rangePanel.panel);
        topPanel.add(clickPanel);

        JPanel middlePanel = new JPanel(); // insertPanel과 tablePanel로 한줄로 나열
        middlePanel.setBorder(BorderFactory.createTitledBorder(border, "middle Panel")); // 테두리 추가
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(insertPanel.panel);
        middlePanel.add(tablePanel.panel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder(border, "bottom Panel")); // 테두리 추가
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(infoPanel.panel);
        bottomPanel.add(updatePanel.panel);
        bottomPanel.add(deletePanel.panel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 프레임 표시
        frame.setVisible(true);
    }
}

