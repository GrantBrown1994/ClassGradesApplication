/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import grades.Busniess.Classes;
import grades.DB.Database;
import java.sql.SQLException;

/**
 *
 * @author grant
 */
public class SCGradeForm extends JDialog{
    
        private JTextField assignmentidField;
    private JTextField assignmentField;
    private JTextField descriptionField;
    private JTextField dateField;
    private JTextField gradeField;
    
    private JButton confirmButton;
    private JButton cancelButton;
    
    private Classes grades = new Classes();
    
    public SCGradeForm(Frame parent, String title, boolean modal, Classes grades){
        super(parent,title,modal);
        componentsInside();
        this.grades = grades;
    }
    
    public SCGradeForm(Frame parent, String title, boolean modal){
    
        super(parent,title,modal);
        componentsInside();
    }
    
    public JPanel componentsInside(){
       assignmentidField = new JTextField(); //creates a text field
       assignmentField = new JTextField();
       descriptionField = new JTextField();
       dateField = new JTextField();
       gradeField = new JTextField();
        
        confirmButton = new JButton ();
        cancelButton = new JButton ();
        
        this.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        Dimension d1 = new Dimension (100,20);
        Dimension d2 = new Dimension (300, 20);
        
        assignmentidField.setPreferredSize(d1);
        assignmentidField.setMinimumSize(d1);
        assignmentField.setPreferredSize(d1);
        assignmentField.setMinimumSize(d1);
        descriptionField.setPreferredSize(d2);
        descriptionField.setMinimumSize(d2);
        dateField.setPreferredSize(d1);
        dateField.setMinimumSize(d1);
        gradeField.setPreferredSize(d1);
        gradeField.setMinimumSize(d1);
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener((ActionEvent) -> {
        cancelButtonActionPerformed();});
        
        confirmButton.setText("Confirm");
        confirmButton.addActionListener((ActionEvent) ->
        {confirmButtonActionPerformed();});
        
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridBagLayout ());
        productPanel.add(new JLabel("AssignmentID"), getConstraints(0,0, GridBagConstraints.LINE_END));
        productPanel.add(assignmentidField, getConstraints(1, 0, GridBagConstraints.LINE_START));
        productPanel.add(new JLabel("Assignment"), getConstraints(0,1, GridBagConstraints.LINE_END));
        productPanel.add(assignmentField, getConstraints(1, 1, GridBagConstraints.LINE_START));
        productPanel.add(new JLabel("Description"), getConstraints(0, 2, GridBagConstraints.LINE_END));
        productPanel.add(descriptionField, getConstraints(1, 2, GridBagConstraints.LINE_START));
        productPanel.add(new JLabel("Date"), getConstraints(0, 3, GridBagConstraints.LINE_END));
        productPanel.add(dateField, getConstraints(1, 3, GridBagConstraints.LINE_START));
        productPanel.add(new JLabel("Grade"), getConstraints(0, 4, GridBagConstraints.LINE_END));
        productPanel.add(gradeField, getConstraints(1, 4, GridBagConstraints.LINE_START));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        this.add(productPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        return buttonPanel;
    }
    
   private GridBagConstraints getConstraints (int x, int y, int anchor){
       GridBagConstraints c = new GridBagConstraints ();
       c.gridx = x;
       c.gridy = y;
       c.anchor = anchor;
       c.insets = new Insets(5, 5, 0, 5);
       return c; 
   }
   private void confirmButtonActionPerformed(){
   
       if(validateTheData()){
       
           setAndAdd();
       
       }
   
   }
   private void cancelButtonActionPerformed(){
       dispose();
   }

   
   private boolean validateTheData(){
       
       
       
       String assignmentid = assignmentidField.getText();
       String assignment = assignmentField.getText();
       String description = descriptionField.getText();
       String date = dateField.getText();
       String grade = gradeField.getText();
       
       if(assignmentid == null || assignment == null ||description == null
               || date == null || grade == null || assignmentid.isEmpty() ||
               assignment.isEmpty() || description.isEmpty() || date.isEmpty() ||
               grade.isEmpty()){
       
           JOptionPane.showMessageDialog(this, "Incorrect or Missing Data" , "Data Incorrect", JOptionPane.ERROR_MESSAGE);
       }  else if(assignment.equalsIgnoreCase("hw") ||assignment.equalsIgnoreCase("homework") ||
               assignment.equalsIgnoreCase("test") || assignment.equalsIgnoreCase("quiz")
               || assignment.equalsIgnoreCase("final")){
           try{
           Integer.parseInt(assignmentid);
           Double.parseDouble(grade);
           } catch(NumberFormatException e){
               JOptionPane.showMessageDialog(this, "Incorrect Grade" , "Data Incorrect", JOptionPane.ERROR_MESSAGE);
               return false;
       }}
           else{
                    JOptionPane.showMessageDialog(this, "Must enter HW, Test, Quiz, or Final as Assignment Name",
                    "Data Incorrect", JOptionPane.ERROR_MESSAGE);
                   return false;
                   }
       
   
       return true;
   }
   
   public void setAndAdd(){
   
       String assignmentid = assignmentidField.getText();
       String assignment = assignmentField.getText();
       String description = descriptionField.getText();
       String date = dateField.getText();
       String grade = gradeField.getText();
       int id =Integer.parseInt(assignmentid);
      
       grades.setId(id);
       grades.setAssignment(assignment);
       grades.setDescription(description);
       grades.setDate(date);
       grades.setGrade(grade);
       
       if(confirmButton.getText().equalsIgnoreCase("Confirm")){
       
              try{
           Database.addSC(grades);
           dispose();
           fireDatabaseUpdatedEvent();
       }catch (SQLException e){
           System.err.println(e);
       }
       
       }
   
   }
   
      private void fireDatabaseUpdatedEvent(){
       SecondClassFrame mainWindow = (SecondClassFrame) getOwner();
       mainWindow.fireDatabaseUpdates();
   
}
    
}
