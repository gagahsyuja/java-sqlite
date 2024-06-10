/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259.dao;

import SembilanSepuluh14259.entities.PasswordStore;
import SembilanSepuluh14259.helpers.DBConnect;
import SembilanSepuluh14259.entities.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ssa
 */
public class UserDaoSQLite implements UserDAO
{

    @Override
    public int register(String username, String password, String fullname)
    {
        int id = 0;
        String query = "INSERT into userdata VALUES (NULL, ?, ?, ?)";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            UserData newUser = new UserData(username, password, fullname);
            
            pstmt.setString(1, newUser.username);
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.fullname);
            pstmt.executeUpdate();
            
            id = pstmt.getGeneratedKeys().getInt(1);
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return id;
    }

    @Override
    public UserData login(String username, String password)
    {   
//        UserData user = null;
        boolean passwordCorrect = false;
        
        String query = "SELECT * FROM userdata where username = ?";
        
        try (Connection conn = DBConnect.connect())
        {   
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            
            ResultSet result = pstmt.executeQuery();
            
            while (result.next())
            {
                if (UserData.checkPassword(password, result.getString("password")))
                {
                    return new UserData(result.getInt("id"),
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("fullname"));   
                }
            }
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public int update(int id, String fullname)
    {
        String query = "UPDATE userdata SET fullname = ? WHERE id = ?";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, fullname);
            pstmt.setInt(2, id);
            
            return pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int update(int id, String password, String fullname)
    {
        String query = "UPDATE userdata SET fullname = ?, password = ? WHERE id = ?";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            UserData newUser = new UserData("", password, fullname);
            
            pstmt.setString(1, newUser.fullname);
            pstmt.setString(2, newUser.getPassword());
            pstmt.setInt(3, id);
            
            return pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int delete(int id)
    {
        String query = "DELETE FROM userdata WHERE id = ?";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, id);
            
            return pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }
    
}
