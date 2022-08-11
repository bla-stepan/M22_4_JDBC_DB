package M22_4_Update;
//Изменим запрос так, чтобы он добавлял имя пользователя под Id=9
import java.sql.*;

public class Main {
    private final static String HOST = "localhost"; //сервер базы данных
    private final static String DATABASE = "testDB"; //имя базы данных
    private final static String USERNAME = "postgres"; //учетная запись пользователя
    private final static String PASSWORD = "12041980"; //пароль пользователя
    static Connection connection;
    public static void main(String[] args) {
        //строка для соединения с БД
//        String URL = "jdbc:postgresql://" + HOST + "/" + DATABASE + "?user=" + USERNAME + "&password=" + PASSWORD;//путь к БД
        String URL1 = String.format("jdbc:postgresql://%s/%s?user=%s&password=%s", HOST, DATABASE, USERNAME, PASSWORD);
//        System.out.println(URL);
        System.out.println(URL1);
        try {
            connection = DriverManager.getConnection(URL1, USERNAME, PASSWORD);
            if (connection == null) {
                System.out.println("Нет соединения с БД!");
            } else {
                System.out.println("Соединение с БД установлено корректно.");
                //Код остается прежним за исключением SQL
                String SQL = "UPDATE test set name = 'Nick' where ID=9;";//добавляем значение имени пользователя по условию
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

