/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259;

import SembilanSepuluh14259.dao.*;
import SembilanSepuluh14259.helpers.*;
import SembilanSepuluh14259.entities.*;
import java.util.ArrayList;
/**
 *
 * @author ssa
 */
public class App
{
    public static void main(String[] args)
    {
        DBSetup.create_table();
        
        // USER
        UserDAO userDAO = new UserDaoSQLite();
        
        userDAO.register("john", "john123", "Michael John");
        userDAO.register("andy", "andy123", "andy andy");
        
        UserData john = userDAO.login("john", "john123");
        UserData andy = userDAO.login("andy", "andy123");
        
        
        // FOLDER
        FolderDAO folderDAO = new FolderDaoSQLite();
        
        int social = folderDAO.createFolder("Social") - 1;
        int eCommerce = folderDAO.createFolder("E-Commerce") - 1;
        int academy = folderDAO.createFolder("Academy") - 1;
        int other= folderDAO.createFolder("Other") - 1;
        
        ArrayList<Folder> folderList = folderDAO.listAllFolders();
        
        
        // PASSWORD
        PasswordStoreDAO passwordStoreDAO = new PasswordStoreDaoSQLite();
        
        PasswordStore [] johnPasswords = {
            new PasswordStore(john.fullname, "commerciallyjohn", "john123", PasswordStore.CAT_MOBILEAPP, folderList.get(eCommerce)),
            new PasswordStore(john.fullname, "johngaming", "john1234", PasswordStore.CAT_MOBILEAPP, folderList.get(other)),
            new PasswordStore(john.fullname, "smartyjohn", "john12345", PasswordStore.CAT_WEBAPP, folderList.get(academy)),
            new PasswordStore(john.fullname, "sociallyjohn", "john123456", PasswordStore.CAT_WEBAPP, folderList.get(social)),
            new PasswordStore(john.fullname, "johnjohn123", "john123457", PasswordStore.CAT_OTHER, folderList.get(other)),
        };
        
        PasswordStore [] andyPasswords = {
            new PasswordStore(andy.fullname, andy.username, "andysocial", PasswordStore.CAT_WEBAPP, folderList.get(social)),
            new PasswordStore(andy.fullname, andy.username, "andycommerce", PasswordStore.CAT_MOBILEAPP, folderList.get(eCommerce)),
            new PasswordStore(andy.fullname, andy.username, "andycademy", PasswordStore.CAT_WEBAPP, folderList.get(academy)),
            new PasswordStore(andy.fullname, andy.username, "andy12345678", PasswordStore.CAT_OTHER, folderList.get(other)),
            new PasswordStore(andy.fullname, andy.username, "andygame", PasswordStore.CAT_OTHER, folderList.get(other)),
        };
        
        for (PasswordStore password: johnPasswords)
        {
            passwordStoreDAO.addPassword(password, john);
        }
        
        for (PasswordStore password: andyPasswords)
        {
            passwordStoreDAO.addPassword(password, andy);
        }
        
        ArrayList<PasswordStore> andyPasswordList = passwordStoreDAO.listPassword(andy);
        
        int i = 1;
        
        for (PasswordStore user: andyPasswordList)
        {
            System.out.print(i);
            System.out.println("\tAccount\t\t: " + user.name);
            System.out.println("\tUsername\t: " + user.username);
            System.out.println("\tCategory\t: " + user.getCategory());
            System.out.println("\tFolder\t\t: " + user.folder.name);
            System.out.println("");
            
            i++;
        }
    }
}
