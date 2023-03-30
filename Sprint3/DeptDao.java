package etu1776.framework.modele;
import annotation.*;
import java.lang.annotation.*;
import java.time.LocalDateTime;
@dao(table="DeptDao")
public class DeptDao{
    int id;
    String libele;
    String loc;
    
    public DeptDao() {
    }
    public DeptDao(int id, String libele, String loc) {
        this.id = id;
        this.libele = libele;
        this.loc = loc;
    }
    public int getId() { return id; }
    public String getLibele() { return libele; }
    public String getLoc() { return loc; }
 
    public void setId(int id) { this.id = id; }
    public void setLibele(String libele) { this.libele = libele; }
    public void setLoc(String loc) { this.loc = loc; }
    

}