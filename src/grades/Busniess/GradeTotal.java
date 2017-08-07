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
public class GradeTotal {
    
    private double hwGrade;
    private double quizGrade;
    private double testGrade;
    private double finalsGrade;
    
    public GradeTotal(){
        hwGrade = 0 ;
        quizGrade = 0;
        testGrade = 0;
        finalsGrade = 0;
    }
    
    public void addToHWGrade(double i){
        this.hwGrade += i;
    }
    public void addToTestGrade(double i){
        this.testGrade += i;
    }
    public void addToQuizGrade(double i){
        this.quizGrade += i;
    }
    public void addToFinalsGrade(double i){
        this.finalsGrade += i;
    }
    
    public double getHWGrade() {
        return this.hwGrade;
    }
    public double getTestGrade(){
        return this.testGrade;
    }
    public double getQuizGrade(){
        return this.quizGrade;
    }
    public double getFinalsGrade(){
        return this.finalsGrade;
    }
}   
