import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class SQLManager {
    private static final String url = "jdbc:mysql://localhost:3306/company?serverTimeZone=UTC";
    private static final String user = "root";
    private static final String password = null /*각자의 비밀번호*/;
    public static final int SELECT = 1;
    public static final int INSERT = 2;
    public static final int DELETE = 3;
    public static final int UPDATE = 4;
    private static Connection connection = null;
    static ArrayList<String> selectList;
    static ArrayList<String> insertList;
    static ArrayList<String> insertValue;
    static ArrayList<String> whereList;
    static ArrayList<ArrayList<String>> result;

    public boolean tryConnection() {
        try {
            connection = getConnection(url, user, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("데이터베이스에 연결할 수 없습니다.");
            e.printStackTrace();
        }
        return connection != null;
    }

    public static void runSQL(int command) throws SQLException {
        String sql = switch (command) {
            case SELECT -> makeSelectString() + " FROM EMPLOYEE " + makeWhereString();
            case INSERT -> "INSERT INTO EMPLOYEE " + makeInsertString() + " VALUES" + makeInsertValue();
            default -> "DELETE FROM EMPLOYEE " + makeWhereString();
        };

        try (Statement stmt = connection.createStatement()) {
            if (command == SELECT) {
                getExecuteQuery(stmt, sql);
            } else {
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e){
            System.out.println("Wrong SQL Query");
        }
    }

    private static String makeSelectString() {
        String select = "SELECT ";
        for (int i = 0; i < selectList.size(); i++) {
            String attribute = selectList.get(i);
            if (attribute.equals("Name")) {
                select += "Fname, Minit, Lname";
            } else {
                select += attribute;
            }
            if (i != selectList.size() - 1) {
                select += ", ";
            }
        }
        return select;
    }

    private static String makeWhereString() {
        if (whereList != null) {
            System.out.println("WHERE " + whereList.get(0) + " = '" + whereList.get(1) + "'");
            return "WHERE " + whereList.get(0) + " = '" + whereList.get(1) + "'";
        }
        return "";
    }

    private static String makeInsertString() {
        String insert = "(";
        for (int i = 0; i < insertList.size(); i++) {
            String attribute = insertList.get(i);
            insert += attribute;
            if (i != insertList.size() - 1) {
                insert += ", ";
            }
        }
        insert += ")";
        return insert;
    }

    private static String makeInsertValue() {
        String value = "(";
        for (int i = 0; i < insertValue.size(); i++) {
            String attribute = insertValue.get(i);
            value += attribute;
            if (i != insertValue.size() - 1) {
                value += ", ";
            }
        }
        value += ")";
        return value;
    }

    private static void getExecuteQuery(Statement stmt, String sql) throws SQLException {
        System.out.println(sql);
        result = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            result.add(new ArrayList<>());
            int k = 0;
            for (int j = 0; j < selectList.size(); j++) {
                switch (selectList.get(j)) {
                    case "Name" -> {
                        result.get(i).add(rs.getString(j + k + 1) + " " + rs.getString(j + k + 2) + " " + rs.getString(j + k + 3));
                        k += 2;
                    }
                    case "Bdate" -> result.get(i).add(rs.getDate(j + k + 1).toString());
                    case "Salary" -> result.get(i).add(String.valueOf(rs.getDouble(j + k + 1)));
                    case "Dno" -> result.get(i).add(String.valueOf(rs.getInt(j + k + 1)));
                    default -> result.get(i).add(rs.getString(j + k + 1));
                }
            }
            i++;
        }
    }
}