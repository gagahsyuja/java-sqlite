/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SembilanSepuluh14259.dao;

import SembilanSepuluh14259.entities.UserData;
import SembilanSepuluh14259.entities.PasswordStore;
import java.util.ArrayList;

/**
 *
 * @author ssa
 */
public interface PasswordStoreDAO
{
    public int addPassword (PasswordStore newPassword, UserData user);
    public ArrayList<PasswordStore> listPassword (UserData user);
    public ArrayList<PasswordStore> findPassword (String name, UserData user);
    public int updatePass (PasswordStore changedPass);
    public int deletePass (PasswordStore deletedPass);
}
