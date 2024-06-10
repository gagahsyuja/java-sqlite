/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259.dao;

import SembilanSepuluh14259.helpers.DBConnect;
import SembilanSepuluh14259.entities.Folder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ssa
 */
public class FolderDaoSQLite implements FolderDAO
{

    @Override
    public int createFolder(String foldername)
    {
        int id = 0;
        String query = "INSERT into folder VALUES (NULL, ?)";
        Folder folder = new Folder(foldername);
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, folder.name);
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
    public ArrayList<Folder> listAllFolders()
    {
        ArrayList<Folder> folders = new ArrayList<>();
        String query = "SELECT * FROM folder";
        
        try (Connection conn = DBConnect.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            ResultSet result = pstmt.executeQuery();
            
            while (result.next())
            {   
                Folder folder = new Folder(result.getInt("id"), result.getString("name"));
                folders.add(folder);
            }
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return folders;
    }
    
}
