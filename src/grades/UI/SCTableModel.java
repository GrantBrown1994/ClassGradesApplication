/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import grades.DB.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author grant
 */
public class SCTableModel extends AbstractTableModel {
            private ArrayList<grades.Busniess.Classes> grades;
    private final String[] COLUMNS = {"AssignmentID", "Assignment", "Description", "Date", "Grade"};
    
        public SCTableModel() {         
        try {
            grades = Database.getAllSecondClassGrades();
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

    
    public grades.Busniess.Classes getObject(int rowIndex){
        return grades.get(rowIndex);
    }
    
       public void databaseUploader(){
       try{
           grades = Database.getAllSecondClassGrades();
           fireTableDataChanged();
       } catch(SQLException e){
           System.err.println(e);
       }
   }
}

