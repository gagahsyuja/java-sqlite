/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259.dao;

import SembilanSepuluh14259.helpers.DBConnect;
import SembilanSepuluh14259.entities.UserData;
import SembilanSepuluh14259.entities.Folder;
import SembilanSepuluh14259.entities.PasswordStore;
import SembilanSepuluh14259.dao.FolderDaoSQLite;
import SembilanSepuluh14259.dao.FolderDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ssa
 */
public class PasswordStoreDaoSQLite implements PasswordStoreDAO
{

    @Override
    public int addPassword(PasswordStore newPassword, UserData user)
    {
        int id = 0;
        
        String query = "INSERT into passwordstore VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, newPassword.name);
            pstmt.setString(2, newPassword.username);
            pstmt.setString(3, newPassword.getPassword());
            pstmt.setString(4, newPassword.hashkey);
            pstmt.setDouble(5, newPassword.getScore());
            pstmt.setInt(6, newPassword.getCategoryCode());
            pstmt.setInt(7, user.id);
            pstmt.setInt(8, newPassword.folder.id);
            
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
    public ArrayList<PasswordStore> listPassword(UserData user)
    {
        String query = "SELECT * FROM passwordstore WHERE name = ? AND username = ?";
        ArrayList<PasswordStore> passwords = new ArrayList<>();
        
        FolderDAO folderDAO = new FolderDaoSQLite();
        ArrayList<Folder> folderList = folderDAO.listAllFolders();
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, user.fullname);
            pstmt.setString(2, user.username);
            
            ResultSet result = pstmt.executeQuery();
            
            while (result.next())
            {   
                int folderID = result.getInt("folder_id");
                String folderName = folderList.get(folderID - 1).name;
                
                Folder folder = new Folder(folderID, folderName);
                
                PasswordStore password = new PasswordStore(
                        folderID,
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("hashkey"),
                        result.getDouble("score"),
                        result.getInt("category"),
                        folder
                );
                
                passwords.add(password);
            }
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return passwords;
    }

    @Override
    public ArrayList<PasswordStore> findPassword(String name, UserData user)
    {
        String query = "SELECT * FROM passwordstore WHERE name = ? OR name = ? OR name = ?";
        ArrayList<PasswordStore> passwords = new ArrayList<>();
        
        FolderDAO folderDAO = new FolderDaoSQLite();
        ArrayList<Folder> folderList = folderDAO.listAllFolders();
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, name);
            pstmt.setString(2, user.fullname);
            pstmt.setString(2, user.username);
            
            ResultSet result = pstmt.executeQuery();
            
            while (result.next())
            {
                int folderID = result.getInt("folder_id");
                String folderName = folderList.get(folderID).name;
                
                Folder folder = new Folder(folderID, folderName);
                
                PasswordStore password = new PasswordStore(
                        folderID,
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("hashkey"),
                        result.getDouble("score"),
                        result.getInt("category"),
                        folder
                );
                
                passwords.add(password);
            }
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return passwords;
    }

    @Override
    public int updatePass(PasswordStore changedPass)
    {
        String query = """
                    UPDATE passwordstore SET
                        name = ?,
                        username = ?,
                        password = ?,
                        hashkey = ?,
                        score = ?,
                        category = ?,
                        folder_id = ? 
                     WHERE id = ?
                       """;
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, changedPass.name);
            pstmt.setString(2, changedPass.username);
            pstmt.setString(3, changedPass.getPassword());
            pstmt.setString(4, changedPass.hashkey);
            pstmt.setDouble(5, changedPass.getScore());
            pstmt.setInt(6, changedPass.getCategoryCode());
            pstmt.setInt(7, changedPass.folder.id);
            pstmt.setInt(8, changedPass.id);
            
            return pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int deletePass(PasswordStore deletedPass)
    {
        String query = "DELETE from passwordstore WHERE id = ?";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, deletedPass.id);
            
            return pstmt.executeUpdate();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }
    
}
