package etu1776.framework.modele;
import java.util.ArrayList;
import annotation.*;

public class Emp{
    int deptno;
    String name;
    int salaire;
    String job;
    public Emp(){ }
    public Emp(int deptno, String name, int salaire, String job) {
        this.deptno = deptno;
        this.name = name;
        this.salaire = salaire;
        this.job = job;
    }
    public int getDeptno() { return deptno; }
    public String getName() { return name; }
    public int getSalaire() { return salaire; }
    public String getJob() {return job; }
 

    public void setDeptno(int deptno) { this.deptno = deptno;}
    public void setName(String name) { this.name = name; }
    public void setSalaire(int salaire) { this.salaire = salaire; }
    public void setJob(String job) { this.job = job; }


    @Methods("listEmp") 
    public void get_Emplist(){
        System.out.println("Nom : Kano");
        System.out.println("Nom : Kitty");
          System.out.println("Nom : baby");

    }


    


    
}