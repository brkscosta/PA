/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Exceptions.StudentGradeException;

/**
 *
 * @author BRKsCosta
 */
public class StudentGrades {
    
    private IGradesDao gradesDao;
    private IStatistic statistic;
    
    public StudentGrades() {
        
    }
    
    public void insert(String studentCode, String studentName, Integer studentGrade)  
            throws StudentGradeException {
        GradeEntry entry = new GradeEntry(studentCode, studentName, studentGrade);
        
        if("".equals(studentCode) || "".equals(studentName) || "".equals(studentGrade))
            throw new StudentGradeException("Parametros inválidos");
        
        gradesDao.insert(entry);
    }
    
    public void remove(String studenCode) throws StudentGradeException { 
        
        if("".equals(studenCode))
            throw new StudentGradeException("Code cannot be empty");
        
        gradesDao.remove(studenCode);
        
    }
    
    public GradeEntry get(String studentCode) throws StudentGradeException {
        
        if("".equals(studentCode))
            throw new StudentGradeException("Student code cannot be null");
        return gradesDao.select(studentCode);
    }
    
    public void updateGrade(String studentCode, Integer newGrade) throws StudentGradeException {
        
        if("".equals(studentCode) || newGrade < 10)
            throw new StudentGradeException("Code cannot be null and grade is: " 
                    + newGrade + " must be >= 10");
        
        gradesDao.updateGrade(studentCode, newGrade);
    }   
    
    public Iterable<GradeEntry> getAll() {
        return gradesDao.selectAll();
    }
    
    public void changeStatistic(IStatistic stat){
        this.statistic = stat;
    }
    
    public double computeStatistic(Iterable<GradeEntry> stat){
        return this.statistic.compute(stat);
    }
    
}
