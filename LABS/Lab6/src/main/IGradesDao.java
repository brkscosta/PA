/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

/**
 *
 * @author BRKsCosta
 */
public interface IGradesDao {
    
    List<GradeEntry> selectAll();
    GradeEntry select(String studentCode);
    void insert(GradeEntry entry);
    void remove(String studentCode);
    void updateGrade(String studentCode, Integer newGrade);
    
}
