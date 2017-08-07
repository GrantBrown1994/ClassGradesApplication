/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import grades.Busniess.Classes;
import grades.Busniess.GradeTotal;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.math.RoundingMode;
import grades.Busniess.Increment;

/**
 *
 * @author grant
 * this class works directly with the database
 * it gets and sets the table parameters
 */


public class Database {
    
     public static ArrayList<Classes> getAllFirstClassGrades() throws SQLException{
    
    String sqlStatement = "SELECT * FROM grades.powersystems ORDER BY AssignmentID";
    
    ArrayList<Classes> allgrades = new ArrayList<> ();
    
     Connection connection = DBConnection.getDBConnection();
    

    try(PreparedStatement statment = connection.prepareStatement(sqlStatement);
          ResultSet powersystems = statment.executeQuery();){
        while(powersystems.next()){
            int id = powersystems.getInt("AssignmentID");
            String assignment = powersystems.getString("Assignment");
            String description = powersystems.getString("description");
            String date = powersystems.getString("AssignmentDate");
            String grade = powersystems.getString("Grade");
            
            Classes grades = new Classes();
            grades.setId(id);
            grades.setAssignment(assignment);
            grades.setDescription(description);
            grades.setDate(date);
            grades.setGrade(grade);
            
            allgrades.add(grades);
 
        }
        return allgrades;
    } catch(SQLException e){
        throw new SQLException(e);
    }
  }
     
     public static double getFirstClassFinalGrades() throws SQLException {
     
         String sql = "SELECT * FROM grades.powersystems ORDER BY AssignmentID";
         
         Connection connection = DBConnection.getDBConnection();
         
            Increment increment = new Increment();
            GradeTotal gradetotal = new GradeTotal();
         
         try(PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet powersystems = statement.executeQuery();){
             while(powersystems.next()){
                 
                 String assignment = powersystems.getString("Assignment");
                 String grad = powersystems.getString("Grade");
                 double gd = Double.parseDouble(grad);
                 
                  if(assignment.equalsIgnoreCase("HW") || assignment.equalsIgnoreCase("Homework"))
                  {
                      gradetotal.addToHWGrade(gd);
                      increment.hwCount();
                  }else if(assignment.equalsIgnoreCase("Test")){
                      gradetotal.addToTestGrade(gd);
                      increment.testCount();
                  }else if(assignment.equalsIgnoreCase("final")
                          || assignment.equalsIgnoreCase("Final Test")){
                     gradetotal.addToFinalsGrade(gd);
                      increment.finalCount();
                        
                  }else if(assignment.equalsIgnoreCase("quiz")){
                     gradetotal.addToQuizGrade(gd);
                     increment.quizCount();
                  }
                 
             }
         }catch(SQLException e){
             throw new SQLException(e);
         }
         if(increment.getFinalIncrement() == 0){
             increment.finalCount();
         }
         if(increment.getQuizIncrement() == 0){
             increment.quizCount();
         }
         if(increment.getHWIncrement() == 0){
             increment.hwCount();
         }
         if(increment.getTestIncrement() == 0){
             increment.testCount();
         }
         
         BigDecimal quizAmount = new BigDecimal(increment.getQuizIncrement());
         BigDecimal hwAmount = new BigDecimal(increment.getHWIncrement());
         BigDecimal testAmount = new BigDecimal(increment.getTestIncrement());
         BigDecimal finalAmount = new BigDecimal(increment.getFinalIncrement());
         
         BigDecimal hwGrade = new BigDecimal(gradetotal.getHWGrade());
         BigDecimal quizGrade = new BigDecimal(gradetotal.getQuizGrade());
         BigDecimal testGrade = new BigDecimal(gradetotal.getTestGrade());
         BigDecimal finalsGrade = new BigDecimal(gradetotal.getFinalsGrade());
         
         BigDecimal hwPercentage = new BigDecimal("20");
         BigDecimal quizPercentage = new BigDecimal("10");
         BigDecimal testPercentage = new BigDecimal("50");
         BigDecimal finalsPercentage = new BigDecimal("20");
        
         BigDecimal hwFinalGrade = hwGrade.setScale(4, RoundingMode.HALF_UP).divide(hwAmount, 4, RoundingMode.HALF_UP).multiply(hwPercentage);
         BigDecimal quizFinalGrade = quizGrade.setScale(4, RoundingMode.HALF_UP).divide(quizAmount, 4, RoundingMode.HALF_UP).multiply(quizPercentage);
         BigDecimal testFinalGrade = testGrade.setScale(4, RoundingMode.HALF_UP).divide(testAmount, 4, RoundingMode.HALF_UP).multiply(testPercentage);
         BigDecimal finalsFinalGrade = finalsGrade.setScale(4, RoundingMode.HALF_UP).divide(finalAmount, 4, RoundingMode.HALF_UP).multiply(finalsPercentage);
         
         BigDecimal overallGrade = hwFinalGrade.setScale(4, RoundingMode.HALF_UP).add(quizFinalGrade).add(testFinalGrade).add(finalsFinalGrade);
         double overall = overallGrade.doubleValue();
         
         return overall;
     }

          public static ArrayList<Classes> getAllSecondClassGrades() throws SQLException{
    
    String sqlStatement = "SELECT * FROM grades.seniordesign ORDER BY AssignmentID";
    
    ArrayList<Classes> allgrades = new ArrayList<> ();
    
     Connection connection = DBConnection.getDBConnection();
    

    try(PreparedStatement statment = connection.prepareStatement(sqlStatement);
          ResultSet seniordesign = statment.executeQuery();){
        while(seniordesign.next()){
            int id = seniordesign.getInt("AssignmentID");
            String assignment = seniordesign.getString("Assignment");
            String description = seniordesign.getString("description");
            String date = seniordesign.getString("AssignmentDate");
            String grade = seniordesign.getString("Grade");
            
            Classes grades = new Classes();
            grades.setId(id);
            grades.setAssignment(assignment);
            grades.setDescription(description);
            grades.setDate(date);
            grades.setGrade(grade);
            
            allgrades.add(grades);
 
        }
        return allgrades;
    } catch(SQLException e){
        throw new SQLException(e);
    }
  }
             public static double getSClassFinalGrades() throws SQLException {
     
         String sql = "SELECT * FROM grades.seniordesign ORDER BY AssignmentID";
         
         Connection connection = DBConnection.getDBConnection();
         
            Increment increments = new Increment();
            GradeTotal gradetotals = new GradeTotal();
         
         try(PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet powersystems = statement.executeQuery();){
             while(powersystems.next()){
                 
                 String assignment = powersystems.getString("Assignment");
                 String grad = powersystems.getString("Grade");
                 double gd = Double.parseDouble(grad);
                 
                  if(assignment.equalsIgnoreCase("HW") || assignment.equalsIgnoreCase("Homework"))
                  {
                      gradetotals.addToHWGrade(gd);
                      increments.hwCount();
                  }else if(assignment.equalsIgnoreCase("Test")){
                      gradetotals.addToTestGrade(gd);
                      increments.testCount();
                  }else if(assignment.equalsIgnoreCase("final")
                          || assignment.equalsIgnoreCase("Final Test")){
                     gradetotals.addToFinalsGrade(gd);
                      increments.finalCount();
                        
                  }else if(assignment.equalsIgnoreCase("quiz")){
                     gradetotals.addToQuizGrade(gd);
                     increments.quizCount();
                  }
                 
             }
         }catch(SQLException e){
             throw new SQLException(e);
         }
                  
         if(increments.getFinalIncrement() == 0){
             increments.finalCount();
         }
         if(increments.getQuizIncrement() == 0){
             increments.quizCount();
         }
         if(increments.getHWIncrement() == 0){
             increments.hwCount();
         }
         if(increments.getTestIncrement() == 0){
             increments.testCount();
         }
         
         BigDecimal quizAmount_2 = new BigDecimal(increments.getQuizIncrement());
         BigDecimal hwAmount_2 = new BigDecimal(increments.getHWIncrement());
         BigDecimal testAmount_2 = new BigDecimal(increments.getTestIncrement());
         BigDecimal finalAmount_2 = new BigDecimal(increments.getFinalIncrement());
         
         BigDecimal hwGrade_2 = new BigDecimal(gradetotals.getHWGrade());
         BigDecimal quizGrade_2 = new BigDecimal(gradetotals.getQuizGrade());
         BigDecimal testGrade_2 = new BigDecimal(gradetotals.getTestGrade());
         BigDecimal finalsGrade_2 = new BigDecimal(gradetotals.getFinalsGrade());
         
         BigDecimal hwPercentage_2 = new BigDecimal("20");
         BigDecimal quizPercentage_2 = new BigDecimal("10");
         BigDecimal testPercentage_2 = new BigDecimal("50");
         BigDecimal finalsPercentage_2 = new BigDecimal("20");
        
         BigDecimal hwFinalGrade_2 = hwGrade_2.setScale(4, RoundingMode.HALF_UP).divide(hwAmount_2, 4, RoundingMode.HALF_UP).multiply(hwPercentage_2);
         BigDecimal quizFinalGrade_2 = quizGrade_2.setScale(4, RoundingMode.HALF_UP).divide(quizAmount_2, 4, RoundingMode.HALF_UP).multiply(quizPercentage_2);
         BigDecimal testFinalGrade_2 = testGrade_2.setScale(4, RoundingMode.HALF_UP).divide(testAmount_2, 4, RoundingMode.HALF_UP).multiply(testPercentage_2);
         BigDecimal finalsFinalGrade_2 = finalsGrade_2.setScale(4, RoundingMode.HALF_UP).divide(finalAmount_2, 4, RoundingMode.HALF_UP).multiply(finalsPercentage_2);
         
         BigDecimal overallGrade_2 = hwFinalGrade_2.setScale(4, RoundingMode.HALF_UP).add(quizFinalGrade_2).add(testFinalGrade_2).add(finalsFinalGrade_2);
         double overall_2 = overallGrade_2.doubleValue();
         
         return overall_2;
     }
          
    public static ArrayList<Classes> getAllThirdClassGrades() throws SQLException{
    
    String sqlStatement = "SELECT * FROM grades.fplds ORDER BY AssignmentID";
    
    ArrayList<Classes> allgrades = new ArrayList<> ();
    
     Connection connection = DBConnection.getDBConnection();
    

    try(PreparedStatement statment = connection.prepareStatement(sqlStatement);
          ResultSet fplds = statment.executeQuery();){
        while(fplds.next()){
            int id = fplds.getInt("AssignmentID");
            String assignment = fplds.getString("Assignment");
            String description = fplds.getString("description");
            String date = fplds.getString("AssignmentDate");
            String grade = fplds.getString("Grade");
            
            Classes grades = new Classes();
            grades.setId(id);
            grades.setAssignment(assignment);
            grades.setDescription(description);
            grades.setDate(date);
            grades.setGrade(grade);
            
            allgrades.add(grades);
 
        }
        return allgrades;
    } catch(SQLException e){
        throw new SQLException(e);
    }
  }
    
    public static ArrayList<Classes> getAllFourthClassGrades() throws SQLException{
    
    String sqlStatement = "SELECT * FROM grades.semiconductors ORDER BY AssignmentID";
    
    ArrayList<Classes> allgrades = new ArrayList<> ();
    
     Connection connection = DBConnection.getDBConnection();
    

    try(PreparedStatement statment = connection.prepareStatement(sqlStatement);
          ResultSet semi = statment.executeQuery();){
        while(semi.next()){
            int id = semi.getInt("AssignmentID");
            String assignment = semi.getString("Assignment");
            String description = semi.getString("description");
            String date = semi.getString("AssignmentDate");
            String grade = semi.getString("Grade");
            
            Classes grades = new Classes();
            grades.setId(id);
            grades.setAssignment(assignment);
            grades.setDescription(description);
            grades.setDate(date);
            grades.setGrade(grade);
            
            allgrades.add(grades);
 
        }
        return allgrades;
    } catch(SQLException e){
        throw new SQLException(e);
    }
  }
    
      public static ArrayList<Classes> getAllFifthClassGrades() throws SQLException{
    
    String sqlStatement = "SELECT * FROM grades.energystorage ORDER BY AssignmentID";
    
    ArrayList<Classes> allgrades = new ArrayList<> ();
    
     Connection connection = DBConnection.getDBConnection();
    

    try(PreparedStatement statment = connection.prepareStatement(sqlStatement);
          ResultSet energy = statment.executeQuery();){
        while(energy.next()){
            int id = energy.getInt("AssignmentID");
            String assignment = energy.getString("Assignment");
            String description = energy.getString("description");
            String date = energy.getString("AssignmentDate");
            String grade = energy.getString("Grade");
            
            Classes grades = new Classes();
            grades.setId(id);
            grades.setAssignment(assignment);
            grades.setDescription(description);
            grades.setDate(date);
            grades.setGrade(grade);
            
            allgrades.add(grades);
 
        }
        return allgrades;
    } catch(SQLException e){
        throw new SQLException(e);
    }
  }
             
    public static void add(Classes grade) throws SQLException {
        String sql
                = "INSERT INTO grades.powersystems (AssignmentID, Assignment, Description, AssignmentDate, Grade) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getDBConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, grade.getId());
            ps.setString(2, grade.getAssignment());
            ps.setString(3, grade.getDescription());
            ps.setString(4, grade.getDate());
            ps.setString(5, grade.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    public static void addSC(Classes grade) throws SQLException {
        String sql
                = "INSERT INTO grades.seniordesign (AssignmentID, Assignment, Description, AssignmentDate, Grade) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getDBConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, grade.getId());
            ps.setString(2, grade.getAssignment());
            ps.setString(3, grade.getDescription());
            ps.setString(4, grade.getDate());
            ps.setString(5, grade.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
        public static void addTC(Classes grade) throws SQLException {
        String sql
                = "INSERT INTO grades.fplds (AssignmentID, Assignment, Description, AssignmentDate, Grade) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getDBConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, grade.getId());
            ps.setString(2, grade.getAssignment());
            ps.setString(3, grade.getDescription());
            ps.setString(4, grade.getDate());
            ps.setString(5, grade.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
     public static void addFourthC(Classes grade) throws SQLException {
        String sql
                = "INSERT INTO grades.semiconductors (AssignmentID, Assignment, Description, AssignmentDate, Grade) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getDBConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, grade.getId());
            ps.setString(2, grade.getAssignment());
            ps.setString(3, grade.getDescription());
            ps.setString(4, grade.getDate());
            ps.setString(5, grade.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
     
    public static void addFifthC(Classes grade) throws SQLException {
        String sql
                = "INSERT INTO grades.energystorage (AssignmentID, Assignment, Description, AssignmentDate, Grade) "
                + "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getDBConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, grade.getId());
            ps.setString(2, grade.getAssignment());
            ps.setString(3, grade.getDescription());
            ps.setString(4, grade.getDate());
            ps.setString(5, grade.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
        
    
    public static void delete(int id) throws SQLException{
        String sql = "DELETE FROM grades.powersystems "
                   + "WHERE AssignmentID = ?";
           Connection connection = DBConnection.getDBConnection();
           try(PreparedStatement ps = connection.prepareStatement(sql)){
               
               ps.setInt(1, id);
               ps.executeUpdate();
           } catch (SQLException e) {
            throw new SQLException(e);
        }
    
    }
    
        public static void deleteSC(int id) throws SQLException{
        String sql = "DELETE FROM grades.seniordesign "
                   + "WHERE AssignmentID = ?";
           Connection connection = DBConnection.getDBConnection();
           try(PreparedStatement ps = connection.prepareStatement(sql)){
               
               ps.setInt(1, id);
               ps.executeUpdate();
           } catch (SQLException e) {
            throw new SQLException(e);
        }
    
    }
        
      public static void deleteTC(int id) throws SQLException{
        String sql = "DELETE FROM grades.fplds "
                   + "WHERE AssignmentID = ?";
           Connection connection = DBConnection.getDBConnection();
           try(PreparedStatement ps = connection.prepareStatement(sql)){
               
               ps.setInt(1, id);
               ps.executeUpdate();
           } catch (SQLException e) {
            throw new SQLException(e);
        }
    
    }
      
            public static void deleteFourC(int id) throws SQLException{
        String sql = "DELETE FROM grades.semiconductors "
                   + "WHERE AssignmentID = ?";
           Connection connection = DBConnection.getDBConnection();
           try(PreparedStatement ps = connection.prepareStatement(sql)){
               
               ps.setInt(1, id);
               ps.executeUpdate();
           } catch (SQLException e) {
            throw new SQLException(e);
        }
    
    }
            
     public static void deleteFifthC(int id) throws SQLException{
        String sql = "DELETE FROM grades.semiconductors "
                   + "WHERE AssignmentID = ?";
           Connection connection = DBConnection.getDBConnection();
           try(PreparedStatement ps = connection.prepareStatement(sql)){
               
               ps.setInt(1, id);
               ps.executeUpdate();
           } catch (SQLException e) {
            throw new SQLException(e);
        }
    
    }
    
    
    
    
    
    public static void editEntry(Classes grades) throws SQLException{ //throws it to calling method
    
        String sql = "UPDATE grades.powersystems SET "
                + "Assignment = ?, "
                + "Description = ?, "
                + "AssignmentDate = ?, "
                + "Grade = ?"
                + "WHERE AssignmentID = ?";
        
        Connection connection = DBConnection.getDBConnection();
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
        
            ps.setString(1, grades.getAssignment());
            ps.setString(2, grades.getDescription());
            ps.setString(3, grades.getDate());
            ps.setString(4, grades.getGrade());
            ps.setInt(5, grades.getId());
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            throw new SQLException(e); //rethrows the method
        }
    
    }
    
    
        public static void editSCEntry(Classes grades) throws SQLException{ //throws it to calling method
    
        String sql = "UPDATE grades.seniordesign SET "
                + "Assignment = ?, "
                + "Description = ?, "
                + "AssignmentDate = ?, "
                + "Grade = ?"
                + "WHERE AssignmentID = ?";
        
        Connection connection = DBConnection.getDBConnection();
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
        
            ps.setString(1, grades.getAssignment());
            ps.setString(2, grades.getDescription());
            ps.setString(3, grades.getDate());
            ps.setString(4, grades.getGrade());
            ps.setInt(5, grades.getId());
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            throw new SQLException(e); //rethrows the method
        }
    
    }
        
            public static void editThirdCEntry(Classes grades) throws SQLException{ //throws it to calling method
    
        String sql = "UPDATE grades.fplds SET "
                + "Assignment = ?, "
                + "Description = ?, "
                + "AssignmentDate = ?, "
                + "Grade = ?"
                + "WHERE AssignmentID = ?";
        
        Connection connection = DBConnection.getDBConnection();
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
        
            ps.setString(1, grades.getAssignment());
            ps.setString(2, grades.getDescription());
            ps.setString(3, grades.getDate());
            ps.setString(4, grades.getGrade());
            ps.setInt(5, grades.getId());
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            throw new SQLException(e); //rethrows the method
        }
    
    }
                public static void editFourCEntry(Classes grades) throws SQLException{ //throws it to calling method
    
        String sql = "UPDATE grades.semiconductors SET "
                + "Assignment = ?, "
                + "Description = ?, "
                + "AssignmentDate = ?, "
                + "Grade = ?"
                + "WHERE AssignmentID = ?";
        
        Connection connection = DBConnection.getDBConnection();
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
        
            ps.setString(1, grades.getAssignment());
            ps.setString(2, grades.getDescription());
            ps.setString(3, grades.getDate());
            ps.setString(4, grades.getGrade());
            ps.setInt(5, grades.getId());
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            throw new SQLException(e); //rethrows the method
        }
    
    }
        public static void editFifCEntry(Classes grades) throws SQLException{ //throws it to calling method
    
        String sql = "UPDATE grades.energystorage SET "
                + "Assignment = ?, "
                + "Description = ?, "
                + "AssignmentDate = ?, "
                + "Grade = ?"
                + "WHERE AssignmentID = ?";
        
        Connection connection = DBConnection.getDBConnection();
        
        try(PreparedStatement ps = connection.prepareStatement(sql)){
        
            ps.setString(1, grades.getAssignment());
            ps.setString(2, grades.getDescription());
            ps.setString(3, grades.getDate());
            ps.setString(4, grades.getGrade());
            ps.setInt(5, grades.getId());
            ps.executeUpdate();
            
        }catch(SQLException e)
        {
            throw new SQLException(e); //rethrows the method
        }
    
    }
    

    
}

