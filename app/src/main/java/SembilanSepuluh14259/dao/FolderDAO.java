/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SembilanSepuluh14259.dao;

import SembilanSepuluh14259.entities.Folder;
import java.util.ArrayList;

/**
 *
 * @author ssa
 */
public interface FolderDAO
{
    public int createFolder (String foldername);
    public ArrayList<Folder> listAllFolders();
}