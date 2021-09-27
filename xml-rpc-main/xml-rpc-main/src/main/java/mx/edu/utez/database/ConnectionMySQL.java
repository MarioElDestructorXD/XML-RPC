package mx.edu.utez.database;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/person2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "root");
    }

    public static void main(String[] args) {

        try{
            Connection con = ConnectionMySQL.getConnection();
            System.out.println("Conexión exitosa");

        }catch (Exception e){ //cacha el error
            e.printStackTrace();
        }
    }
}