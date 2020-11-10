/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgrid;

import java.util.Arrays;

/**
 *
 * @author CHRISTIAN
 */
public class WordGrid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grid grid = new Grid(15);
        grid.putWordsInGrid(Arrays.asList("ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN"));
        grid.randomFill();
        grid.displayGrid();
        
    }
    
}
