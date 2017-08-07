/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.Busniess;

/**
 *
 * @author grant
 */
public class Increment {
    
    private int hwIncrement;
    private int testIncrement;
    private int quizIncrement;
    private int finalIncrement;
    
    public Increment(){
        hwIncrement = 0;
        testIncrement = 0;
        quizIncrement = 0;
        finalIncrement = 0;
    }
    
    public void hwCount(){
        this.hwIncrement += 1;
        
    }
    public void testCount(){
        this.testIncrement += 1;
    }
    public void quizCount(){
        this.quizIncrement += 1;
    }
    public void finalCount(){
        this.finalIncrement += 1;
    }
    
    public int getHWIncrement(){
        return this.hwIncrement;
    }
    public int getTestIncrement(){
        return this.testIncrement;
    }
    public int getQuizIncrement(){
        return this.quizIncrement;
    }
    public int getFinalIncrement(){
        return this.finalIncrement;
    }
}
