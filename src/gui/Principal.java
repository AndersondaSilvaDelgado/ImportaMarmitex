package gui;


import bo.ImportarTextoBO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anderson
 */
public class Principal {
    
    public static void main(String[] args) {

        ImportarTextoBO importarTextoBO = new ImportarTextoBO();
        importarTextoBO.importar();
          
    }
    
}
