package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NUMERO_MATRICULA INT," +
            "NOMBRE VARCHAR(100)," +
            "APELLIDO VARCHAR(100))";

    public static void crearTablas(){
        Connection connection = null;

        try {

            connection = getConnection();

            Statement stmt = connection.createStatement();

            stmt.execute(SQL_DROP_CREATE_ODONTOLOGOS);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./TOMAS-BRUNA_LEVIN-SOFIA",
                "sa", "sa");
    }

}
