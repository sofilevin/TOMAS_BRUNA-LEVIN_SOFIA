package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB {

  /*  private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NUMERO_MATRICULA INT," +
            "NOMBRE VARCHAR(100)," +
            "APELLIDO VARCHAR(100))";*/

    public static void crearTablas(){
        Connection connection = null;

        try {

            connection = getConnection();



            Statement stmt = connection.createStatement();
            String script = readSQLFile();
            stmt.execute(script);


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

    public static String readSQLFile(){
        String data = null;
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/dao/SQL_CREATE_TABLE.sql");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }


    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./TOMAS-BRUNA_LEVIN-SOFIA",
                "sa", "sa");
    }

}
