package M22_4_Alter;

import java.sql.*;

public class Main {
    private final  static String HOST = "localhost"; //сервер базы данных
    private final  static String DATABASE = "testDB"; //имя базы данных
    private final  static String USERNAME = "postgres"; //учетная запись пользователя
    private final  static String PASSWORD = "12041980"; //пароль пользователя
    static Connection connection;
    public static void main(String[] args) {
        //строка для соединения с БД
        String URL ="jdbc:postgresql://"+HOST+"/"+DATABASE+"?user="+USERNAME+"&password="+PASSWORD;//путь к БД
        String URL1 =String.format("jdbc:postgresql://%s/%s?user=%s&password=%s", HOST, DATABASE, USERNAME, PASSWORD);
        System.out.println(URL);
        System.out.println(URL1);
        try{
            connection = DriverManager.getConnection(URL1, USERNAME, PASSWORD);
            if (connection==null){
                System.out.println("Нет соединения с БД!");
            }else{
                System.out.println("Соединение с БД установлено корректно.");
                //ПРАКТИКА Модуль 22.4. вставка и обновление БД (ДОБАВЛЕН КОД)
                String SQL = "ALTER TABLE test ADD name varchar(255);";//добавление столбца name
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
                    preparedStatement.executeUpdate();
                }
                //конец добавленного кода
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

