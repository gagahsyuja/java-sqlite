/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SembilanSepuluh14259.entities;

/**
 *
 * @author ssa
 */
public class Folder
{
    public int id;
    public String name;
    
    public Folder (String name)
    {
        this.name = name;
    }
    
    public Folder (int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return "ID: " + id + "\n" + "Name: " + name;
    }
}
