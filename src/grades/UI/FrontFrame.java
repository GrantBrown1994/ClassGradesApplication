/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.UI;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.FlowLayout;
import grades.UI.FirstClassFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author grant
 */
public class FrontFrame extends JFrame {
    
    public FrontFrame(){
        
            try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }   
        
        this.setTitle("Fall 2017 Classes");
        this.setLayout(new BorderLayout());
        this.add(buildButtonPanel(), BorderLayout.CENTER);
        this.setSize(255,270); //(width, height)
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    private JPanel buildButtonPanel(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints i = new GridBagConstraints();
        
        i.insets = new Insets(5, 0, 5, 0);
       i.gridx = 0; i.gridy = 0; i.anchor = GridBagConstraints.CENTER;
       
        JButton firclass = new JButton("Power Systems");
        firclass.addActionListener((ActionEvent) -> {
            if(firclass.getText().equals("Power Systems"))
            {   doFirst(); }
        });
        panel.add(firclass, i);
        i.gridx = 0; i.gridy = 1; i.anchor = GridBagConstraints.CENTER;
        
        JButton secondclass = new JButton("Senior Design");
        secondclass.addActionListener((ActionEvent) -> {
            if(secondclass.getText().equals("Senior Design"))
                doSecond();});
        panel.add(secondclass, i);
        i.gridx = 0; i.gridy = 2; i.anchor = GridBagConstraints.CENTER;
        
        JButton thirdclass = new JButton("FPLDS");
         thirdclass.addActionListener((ActionEvent) -> {
            if(thirdclass.getText().equals("FPLDS"))
            doThird();});
            panel.add(thirdclass, i);
        i.gridx = 0; i.gridy = 3; i.anchor = GridBagConstraints.CENTER;
        
        JButton fourthclass = new JButton("Mod/Sim Semiconductors");
        fourthclass.addActionListener((ActionEvent) -> {doFourth();});
         panel.add(fourthclass, i);
         
         i.gridx = 0; i.gridy =4; i.anchor = GridBagConstraints.CENTER;
        JButton fifthclass = new JButton("Energy Storage");
        fifthclass.addActionListener((ActionEvent) -> {doFifth();});
        panel.add(fifthclass, i);
        
        //if you want to add padding need to use gridbaglayout
        
        return panel;
        
    }
    
    private void doFirst(){
        FirstClassFrame firstFrame = new FirstClassFrame();
        firstFrame.setVisible(true);

    }
    
    private void doSecond(){
        SecondClassFrame secondFrame = new SecondClassFrame();
        secondFrame.setVisible(true);
    }
    
    private void doThird(){
    
       ThirdClassFrame thirdFrame = new ThirdClassFrame();
       thirdFrame.setVisible(true);
    }
    
    private void doFourth(){
        FourthClassFrame fourthFrame = new FourthClassFrame();
        fourthFrame.setVisible(true);
    }
    
    private void doFifth(){
    
        FifthClassFrame fifthFrame = new FifthClassFrame();
        fifthFrame.setVisible(true);
    }
    
}
