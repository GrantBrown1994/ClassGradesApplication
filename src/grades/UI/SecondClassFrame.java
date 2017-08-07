/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;


/**
 *
 * @author grant
 */
public class SecondClassFrame extends JFrame {
       
    private static SCTableModel model;
    private static JTable table;
    
    
    public SecondClassFrame(){
    
             
        model = new SCTableModel();
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
        

            
        this.setTitle("Senior Design");
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
        finalButton.addActionListener((ActionEvent) -> { doFinalButton();});
        
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(backButton);
        buttonPanel.add(finalButton);
        
        return buttonPanel;
    }
    
    public void doAddButton(){
        //dialog box comes up with data enty form
        SCGradeForm grades = new SCGradeForm(this, "Add Grades" , true);
        grades.setVisible(true);
    }
    
    public void doDeleteButton(){
        
        SCDeleteForm delgrades = new SCDeleteForm(this, "Delete Grades", true);
        delgrades.setVisible(true);
    }
    
    public void doEditButton(){
        SCEditForm editGrades = new SCEditForm(this, "Edit Grades", true);
        editGrades.setVisible(true);
    }
    
    public void doFinalButton(){
        FinalGradeFormSC finalForm = new FinalGradeFormSC(this, "Final Grade", true);
        finalForm.setVisible(true);
        
    }
    
        public void fireDatabaseUpdates(){
        model.databaseUploader();
    }
    
}
    

