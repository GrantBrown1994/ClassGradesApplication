/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Window;


/**
 *
 * @author grant
 */
public class ThirdClassFrame extends JFrame {
    
     private static TCTableModel model;
    private static JTable table;
    
    
    public ThirdClassFrame(){
    
             
        model = new TCTableModel();
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
            try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }   
        

            
        this.setTitle("FPLDS");
        this.setLayout(new BorderLayout());
        this.setSize(1200,800);
        this.add(buildButtonPanel(), BorderLayout.SOUTH);
        JScrollPane pane = new JScrollPane(table);
        this.add(pane, BorderLayout.CENTER);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private JPanel buildButtonPanel() {
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       
        
        JButton addButton = new JButton("Add");
        addButton.setToolTipText("Click Here To Add A Grade");
        addButton.addActionListener((ActionEvent) -> {
            doAddButton();
        });
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Click Here To Delete A Grade");
        deleteButton.addActionListener((ActionEvent) -> { doDeleteButton();
        });
        
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Click Here to Edit A Grade");
        editButton.addActionListener((ActionEvent) -> {doEditButton();});
        
        JButton finalButton = new JButton("Final Grade");
        finalButton.addActionListener((ActionEvent) -> {doFinalButton();});
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent) -> {dispose();});
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(finalButton);
        buttonPanel.add(backButton);
        
        return buttonPanel;
    }
    
    public void doAddButton(){
        //dialog box comes up with data enty form
        TCGradeForm grades = new TCGradeForm(this, "Add Grades" , true);
        grades.setVisible(true);
    }
    
    public void doDeleteButton(){
        
        TCDeleteForm delgrades = new TCDeleteForm(this, "Delete Grades", true);
        delgrades.setVisible(true);
    }
    
    public void doEditButton(){
        TCEditForm editForm = new TCEditForm(this, "Edit Grads", true);
        editForm.setVisible(true);
    }
    
    public void doFinalButton(){
        FinalGradeFormTC finalForm = new FinalGradeFormTC(this, "Final Grade", true);
        finalForm.setVisible(true);
    }
    
        public void fireDatabaseUpdates(){
        model.databaseUploader();
    }
}
