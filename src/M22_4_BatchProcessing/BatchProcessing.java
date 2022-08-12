package M22_4_BatchProcessing;

import java.sql.*;

public class BatchProcessing {
    private final static String HOST = "localhost"; // сервер базы данных
    private final static String DATABASENAME = "testDB"; // имя базы
    private final static String USERNAME = "postgres"; // учетная запись пользователя
    private final static String PASSWORD = "12041980"; // пароль
    static Connection connection;//соединение

    public static void main(String[] args) throws SQLException {
        //Строка для соединения с бд
        String url = "jdbc:postgresql://" + HOST + "/" + DATABASENAME + "?user=" + USERNAME + "&password=" + PASSWORD;
        connection = DriverManager.getConnection(url, USERNAME, PASSWORD);//драйвер
        try {
            connection.setAutoCommit(false);//отключаем авто-коммит
            if (connection == null)//если соединения нет
                System.err.println("Нет соединения с БД!");
            else {//если соединение есть
                System.out.println("Соединение с БД установлено корректно");
            }
            String SQL = "INSERT INTO test(ID, user) VALUES(?,?);";
            //Запрос на получение всех данных
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                //Часть кода, в котором мы добавляем несколько запросов для одной отправки данных
                preparedStatement.setInt(1, 10);//запрос на добавление значения 10 в первый столбец
                preparedStatement.setString(2, "Olaf");//запрос на добавление значения имени в столбец 2 (name)
                preparedStatement.addBatch();//блок

                preparedStatement.setInt(1, 11);
                preparedStatement.setString(2, "Erik");
                preparedStatement.addBatch();

                preparedStatement.setInt(1, 12);
                preparedStatement.setString(2, "Baleog");
                preparedStatement.addBatch();

                int[] count = preparedStatement.executeBatch();
                connection.commit();
                System.out.println("Данные отправлены");
            }
        } catch (SQLException e) {//ClassNotFoundException |
                connection.rollback();
            System.err.println("Данные не добавлены");
            e.printStackTrace();
        }
    }

    public static boolean checkvalue(int checkedvalue) {
        String SQL = "Select * from test where ID=?;";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, checkedvalue);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean insertvalue(int insertedvalue) {
        String SQL = "insert  test(id) values(?)";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, insertedvalue);
            int i = statement.executeUpdate();
            if (i >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
