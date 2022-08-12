package M22_4_Practika;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private final static String HOST = "localhost"; // сервер базы данных
    private final static String DATABASENAME = "testDB"; // имя базы
    private final static String USERNAME = "postgres"; // учетная запись пользователя
    private final static String PASSWORD = "12041980"; // пароль
    static Connection con;//соединение

    public static void main(String[] args) throws SQLException {
        //Строка для соединения с бд
        String url = String.format("jdbc:postgresql://%s/%s?user=%s&password=%s", HOST, DATABASENAME, USERNAME, PASSWORD);
        con = DriverManager.getConnection(url, USERNAME, PASSWORD);//драйвер
        try {
            con = DriverManager.getConnection(url, USERNAME, PASSWORD);
            //con.setAutoCommit(false);//отключаем авто-коммит
            if (con == null)//если соединения нет
                System.err.println("Нет соединения с БД!");
            else {//если соединение есть
                System.out.println("Соединение с БД установлено корректно");
                if (checkValue(new Scanner(System.in).nextInt())){
                    System.out.println("Число есть в таблице.");
                } else {
                    System.out.println("Число отсутствует в таблице");
                }
                if (insertValue(new Scanner(System.in).nextInt())){
                    System.out.println("Число добавлено в таблицу");
                }else {
                    System.out.println("Число не добавлено");
                }
            }
        } catch (SQLException e) {//ClassNotFoundException |
            con.rollback();
            System.err.println("Данные не добавлены");
            e.printStackTrace();
        }
    }
    public static boolean checkValue(int checkedvalue) {
        String SQL = "Select * from test where ID=?;";
        try (PreparedStatement prst = con.prepareStatement(SQL)) {
            prst.setInt(1, checkedvalue);
            ResultSet resultSet = prst.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean insertValue(int insertedvalue) {
        String SQL = "insert into test(id) values(?);";
        try (PreparedStatement prst = con.prepareStatement(SQL)) {
            prst.setInt(1, insertedvalue);
            int i = prst.executeUpdate();
            if (i >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
