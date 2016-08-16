package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dotin school 6 on 8/7/2016.
 */
public class DBConnection {
    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/bank_database";
    private static String user = "root";
    private static String pass = "root";

    private DBConnection() {
    }

    public static Connection getDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Successful connection....");

              /*  Statement statement =  connection.createStatement();
                statement.execute("CREATE TABLE SAMPLE (customerNumber int)");*/

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("خطا دربرقراری ارتباط...");
        }
        return connection;
    }


   /* public static void main(String[] args) {
        new DBConnection();
    }*/

   /* private static void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url , user , pass);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}