/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import grades.Busniess.Classes;
import grades.DB.Database;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author grant
 */
public class SCDeleteForm extends JDialog {
    
      private JTextField assignmentidField;

    
    private JButton confirmButton;
    private JButton cancelButton;
    
    private Classes grades = new Classes();
    
    public SCDeleteForm(Frame parent, String title, boolean modal, Classes grades){
        super(parent,title,modal);
        componentsInside();
        this.grades = grades;
    }
    
    public SCDeleteForm(Frame parent, String title, boolean modal){
    
        super(parent,title,modal);
        componentsInside();
    }
    
    public JPanel componentsInside(){
       assignmentidField = new JTextField(); //creates a text field

       
        
        confirmButton = new JButton ();
        cancelButton = new JButton ();
        
        this.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        Dimension d1 = new Dimension (100,20);
        
        
        assignmentidField.setPreferredSize(d1);
        assignmentidField.setMinimumSize(d1);
       
        
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
       
           setAndDelete();
       
       }
   
   }
   private void cancelButtonActionPerformed(){
       dispose();
   }

   
   private boolean validateTheData(){
       
       
       
       String assignmentid = assignmentidField.getText();


       if(assignmentid == null ||assignmentid.isEmpty()){
       
           JOptionPane.showMessageDialog(this, "Incorrect or Missing Data" , "Data Incorrect", JOptionPane.ERROR_MESSAGE);
       }else{
           try{
           Integer.parseInt(assignmentid);
           } catch(NumberFormatException e){
               JOptionPane.showMessageDialog(this, "Incorrect Grade" , "Data Incorrect", JOptionPane.ERROR_MESSAGE);
               return false;
       }
       
       
   }
       return true;
   }
   
   public void setAndDelete(){
   
       String assignmentid = assignmentidField.getText();

       int id =Integer.parseInt(assignmentid);
       
        
       
       if(confirmButton.getText().equalsIgnoreCase("Confirm")){
       
              try{
           Database.deleteSC(id);
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
