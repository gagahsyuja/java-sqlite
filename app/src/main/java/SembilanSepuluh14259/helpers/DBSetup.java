/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ssa
 */
public class DBSetup
{
    public static void create_table()
    {
        ArrayList<String> sql = new ArrayList<>();

        sql.add("""
                CREATE TABLE if not exists
                folder (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT);
                """);

        sql.add("""
                CREATE TABLE if not exists userdata (id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT, password TEXT, fullname TEXT);
                """);
        
        sql.add("""
                CREATE UNIQUE INDEX if not exists user_username_IDX ON userdata (username);
                """);
        
        sql.add("""
                CREATE TABLE if not exists passwordstore (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT, username TEXT, password TEXT, hashkey TEXT,
                    score REAL, category INTEGER, user_id INTEGER, folder_id INTEGER,
                    CONSTRAINT passwordstore_user_FK FOREIGN KEY (user_id) REFERENCES userdata(id) ON
                DELETE RESTRICT ON UPDATE RESTRICT,
                    CONSTRAINT passwordstore_folder_FK FOREIGN KEY (folder_id) REFERENCES folder(id)
                ON DELETE SET NULL ON UPDATE SET NULL);
                """);
        
        sql.add("""
                CREATE TABLE if not exists additional (
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    entry_key TEXT, entry_value TEXT, is_password INTEGER, password_id INTEGER,
                CONSTRAINT additional_passwordstore_FK FOREIGN KEY (password_id) REFERENCES
                passwordstore(id) ON DELETE CASCADE ON UPDATE CASCADE);
                """);
        
        try (Connection conn = DBConnect.connect(); Statement stmt = conn.createStatement())
        {
            for (String i: sql)
            {
                stmt.execute(i);
            }

        }
        
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
