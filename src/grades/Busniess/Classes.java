/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.Busniess;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author grant
 */
public class Classes {
    
    private int id;
    private String assignment;
    private String description;
    private String date;
    private String grade;
    
    public Classes(){}
    
    public Classes(int id, String assignment, String description, String date, String grade){
        this.id = id;
        this.assignment = assignment;
        this.description = description;
        this.date = date;
        this.grade = grade;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int setId(int id){
      return this.id = id;
    }
    
    public String getAssignment(){
        return this.assignment;
    }
    
    public void setAssignment(String assignment){
       this.assignment = assignment;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
       this.description = description;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public void setDate(String date){
       this.date = date;
    }
    
    public String getGrade(){
        
        return this.grade;
    }
    
    public void setGrade(String grade){
       this.grade = grade;
    }
   
}
    

