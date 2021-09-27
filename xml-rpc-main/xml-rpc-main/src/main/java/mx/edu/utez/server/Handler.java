package mx.edu.utez.server;

import mx.edu.utez.database.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;

    public int suma(int x, int y){
        return x + y;
    }

    public boolean createUser(String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("INSERT INTO `user`(name, lastname, email, password, date_register, status)VALUES(?,?,?,?,NOW(),1);");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        return flag;
    }

    public boolean updateUser(int id, String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("UPDATE user SET name = ?, lastname = ?, email = ?, password = ? WHERE id = ?;");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);
            pstm.setInt(5, id);

            flag = pstm.executeUpdate() == 1;

        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        return flag;
    }

    public List<User> findAll(){
        Statement statement;
        List<User> listUser = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            String query = "  SELECT * FROM user;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setDate(rs.getString("date_register"));
                user.setStatus(rs.getInt("status"));
                listUser.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return listUser;
    }

    public boolean deleteUser(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("DELETE FROM `user` WHERE id = ?;");
            pstm.setInt(1, id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }

        return flag;
    }
}
