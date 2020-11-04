/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgrid;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author CHRISTIAN
 */
public class Grid {
    
    int size;
    char[][] content;
    List<String> words;

    public Grid(int gridSize, List<String> words) {
        this.words = words;
        this.size = gridSize;
        initGrid();
    }    
    public Grid(int gridSize) {
        this.size = gridSize;
        initGrid();
    }
    public Grid() {
        this(9);
    }
    
    public void initGrid() {
        
        content = new char[size][size];
        
        for(int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                content[i][j] = '_';
            }
        }
    }
    
    public void displayGrid() {
        
        for(int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.print(content[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void setWords(List<String> words) {
        this.words = words;
    }
    public List<String> getWords() {
        return this.words;
    }
    
    public void putWordsInGrid(List<String> words) {
        
            for(String word: words) {
                if(word.length() > this.size) continue; /* This line makes sure we never have a word length greater than grid size
                                                     In other words every word that passes here must be able to fit somewhere in the grid */
                int x = ThreadLocalRandom.current().nextInt(0, this.size);
                int y = ThreadLocalRandom.current().nextInt(0, this.size);
                
                // Getting a random position, testing whether 
                //Testing whether we still have space ahead to fill in the character
                
                int direction = ThreadLocalRandom.current().nextInt(0,8);
                
                switch(direction) {
                    // Diagonal upward left
                    case 0:
                        if(y - word.length()>= -1 && x-word.length()>= -1 && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x--][y--] = ch;
                            }
                        }
                        break;
                        
                    // Vertical upward
                    case 1:
                        if(x - word.length() > -1 && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x--][y] = ch;
                            }
                        }
                      break;
                    /* NB: y represents columns and as such horizontal steps.
                           So when thinking of x-coordinates displacement, manipulate y
                           and vice-versa for x*/
                      
                    // Diagonal upward right
                    case 2:
                        if(y + word.length() < this.size && x - word.length() >= 0 && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x++][y++] = ch;
                            }
                        }
                        break;
                    
                    // Horizontal forward
                    case 3:
                        if(y + word.length() <= this.size && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x][y++] = ch;
                            }
                        }
                        break;
                        
                    // Diagonal downward right
                    case 4:
                        if(y + word.length() < this.size && x + word.length() < this.size && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x++][y++] = ch;
                            }
                        }
                        break;
                    
                    // Vertical down
                    case 5:
                        if(x + word.length() <= this.size && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x++][y] = ch;
                            }
                        }
                        break;
                    
                    // Diagonal down left
                    case 6:
                        if(y - word.length()>= 0 && x + word.length() < this.size && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x--][y--] = ch;
                            }
                        }
                        break;
                    
                    // Horizontal left
                    case 7:
                        if( x - word.length() >= -1 && content[x][y] != '_') {
                            for(char ch: word.toCharArray()) {
                                content[x][y--] = ch;
                            }
                        }
                        
                }
            }
    }
}
