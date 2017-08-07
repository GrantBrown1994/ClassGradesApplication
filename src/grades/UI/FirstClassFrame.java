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
import javax.swing.JDialog;
import grades.UI.FCTableModel;
import javax.swing.JOptionPane;
import grades.UI.FCTableModel;
import grades.Busniess.Classes;

/**
 *
 * @author grant
 */
public class FirstClassFrame extends JFrame{
    
    private JTable table;
    private FCTableModel model;
    
    public FirstClassFrame(){
        
        model = new FCTableModel();
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
        

            
        this.setTitle("Power Systems Analysis");
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
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent) -> {dispose();});
        
        JButton finalButton = new JButton("Final Grade");
        finalButton.setToolTipText("Calculate Grade Total");
        finalButton.addActionListener((ActionEvent) -> { doFinalButton();});
        
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(finalButton);
        buttonPanel.add(backButton);
        
        return buttonPanel;
    }
    
    public void doAddButton(){
        
      GradeForm addForm = new GradeForm(this, "Add Grade" , true);
      addForm.setVisible(true);
    }
    
    public void doDeleteButton(){
       DeleteForm deleteForm = new DeleteForm(this, "Delete Grade", true);
       deleteForm.setVisible(true);
    }
    
    public void doEditButton(){
        

       EditForm editForm = new EditForm(this, "Edit Entry" , true);
       editForm.setVisible(true);
    }
    
    
  public void doFinalButton(){
        
      FinalGradeForm finalForm = new FinalGradeForm(this,"Final Grades" , true);
      finalForm.setVisible(true);
     
  }

    
    
    public void fireDatabaseUpdates(){
        model.databaseUploader();
    }
}
