/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRKsCosta
 */
public class GradesDao implements IGradesDao {

    public static final String FILENAME = "grades.dat";
    
    private List<GradeEntry> inMemory;

    public GradesDao() {
        this.inMemory = new ArrayList<>();
        this.loadFile();
    }

    @Override
    public List<GradeEntry> selectAll() {
        return inMemory;
    }

    @Override
    public GradeEntry select(String studentCode) {
        for (GradeEntry entry : inMemory) {
            if (entry.getStudentCode().compareToIgnoreCase(studentCode) == 0) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public void insert(GradeEntry entry) {
        int index = -1;

        for (int i = 0; i < inMemory.size(); i++) {
            if (inMemory.get(i).getStudentCode().compareToIgnoreCase(entry.getStudentCode()) == 0) {
                index = i;
            }
        }

        inMemory.add(entry);
        
        this.saveFile();

    }

    @Override
    public void remove(String studentCode) {
        int index = -1;

        for (int i = 0; i < inMemory.size(); i++) {
            if (inMemory.get(i).getStudentCode().compareToIgnoreCase(studentCode) == 0) {
                index = i;
            }
        }

        if (index != -1) {
            inMemory.remove(index);
            saveFile();
        }
    }

    @Override
    public void updateGrade(String studentCode, Integer newGrade) {
        int index = -1;

        for (int i = 0; i < inMemory.size(); i++) {
            if (inMemory.get(i).getStudentCode().compareToIgnoreCase(studentCode) != 0
                    || newGrade <= 10) {
                index = i;
                if (index != -1) {
                    GradeEntry entry = new GradeEntry(studentCode, inMemory.get(i).getStudentName(), newGrade);
                    inMemory.set(index, entry);
                    saveFile();
                }
            }
        }

    }

    private void saveFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(inMemory);
            out.close();

            fileOut.close();
        } catch (IOException e) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadFile() {
        try {
            
            FileInputStream fileIn = new FileInputStream("C:\\OneDrive\\Documentos\\GitHub\\PA\\LABS\\Lab6\\" + FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inMemory = (List<GradeEntry>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
