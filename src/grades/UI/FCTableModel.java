/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import grades.DB.Database;
import grades.Busniess.Classes;

/**
 *
 * @author grant
 */

public class FCTableModel extends AbstractTableModel {
    
    private ArrayList<Classes> grades;
    private final String[] COLUMNS = {"AssignmentID", "Assignment", "Description", "Date", "Grade"};
    
        public FCTableModel() {
            
        try {
            grades = Database.getAllFirstClassGrades();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    @Override
    public int getRowCount(){
        return grades.size();
          }
    
    @Override
    public String getColumnName(int columnIndex){
        return COLUMNS[columnIndex];
    }
   
    @Override
    public int getColumnCount(){
     return COLUMNS.length;
    }

   
    @Override 
    public Object getValueAt(int rowIndex, int columnIndex){
     switch(columnIndex){
         case 0:
             return grades.get(rowIndex).getId();
         case 1:
             return grades.get(rowIndex).getAssignment();
         case 2:
             return grades.get(rowIndex).getDescription();
         case 3:
             return grades.get(rowIndex).getDate();
         case 4:
             return grades.get(rowIndex).getGrade();
         default:
             return null;
     }
     
    }

    
    public Classes getGrade(int rowIndex){
        return grades.get(rowIndex);
    }


   public void databaseUploader(){
       try{
           grades = Database.getAllFirstClassGrades();
           fireTableDataChanged();
       } catch(SQLException e){
           System.err.println(e);
       }
   }
 }