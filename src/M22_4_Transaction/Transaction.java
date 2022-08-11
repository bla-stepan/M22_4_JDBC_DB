package M22_4_Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    private final static String HOST = "localhost"; //сервер базы данных
    private final static String DATABASE = "testDB"; //имя базы данных
    private final static String USERNAME = "postgres"; //учетная запись пользователя
    private final static String PASSWORD = "12041980"; //пароль пользователя
    static Connection connection;

    public static void main(String[] args) throws SQLException{
        //строка для соединения с БД формирование строки через класс стригн методом жформат
        String URL = String.format("jdbc:postgresql://%s/%s?user=%s&password=%s", HOST, DATABASE, USERNAME, PASSWORD);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);//отключаем авто-коммит для ведения работы по типу транзакции
            if (connection == null) {
                System.err.println("Нет соединения с БД!");
            } else {
                System.out.println("Соединение с БД установлено");
            }
            String SQL = "ALTER TABLE tets ADD user varchar(255);";//инекция кода SQL для обработки присоединенной базы данных
            //Запрос на получение всех данных
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {//используется интерфейс
                preparedStatement.executeUpdate();
                connection.commit();
                System.err.println("Транзакция прошла");
            }
        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Транзакция не прошла");
            e.printStackTrace();
        }
    }
}

