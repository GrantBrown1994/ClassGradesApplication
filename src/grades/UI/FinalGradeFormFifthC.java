/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import grades.DB.Database;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author grant
 */
public class FinalGradeFormFifthC extends JDialog {
 
    
    public FinalGradeFormFifthC(JFrame parent, String title, boolean modal ){
    
       super(parent,title,modal);
       componentsInside();
      
    }
    
    private JTextField gradeField;
    private JButton backButton;
    private double grades;
  
    
    
    public JPanel componentsInside(){

       gradeField = new JTextField();
      
        

        
        this.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        Dimension d1 = new Dimension (100,20);
        Dimension d2 = new Dimension (200,100); //(width, height)
        Dimension d3 = new Dimension (500,300);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(d3);
        gradeField.setPreferredSize(d2);
        gradeField.setMinimumSize(d2);
        
       

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridBagLayout ());
        
        productPanel.add(new JLabel("Grade"), getConstraints(0, 0, GridBagConstraints.LINE_END));
        productPanel.add(gradeField, getConstraints(1, 0, GridBagConstraints.LINE_START));

        
        try{
        grades = Database.getFifthClassFinalGrades();
        gradeField.setText(Double.toString(grades));}
        catch(SQLException e){
            System.err.println(e);
        }
     
        
       
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
       

        
        this.add(productPanel, BorderLayout.CENTER);
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
   
}

    
