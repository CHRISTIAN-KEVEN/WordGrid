/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CHRISTIAN
 */
public class Grid {

    int size;
    char[][] content;
    List<String> words;
    List<Coordinate> coordinates = new ArrayList<>();

    private enum Direction {
        DIAGONAL_UP_LEFT, VERTICAL_UP,
        DIAGONAL_UP_RIGHT, HORIZONTAL_FORWARD,
        DIAGONAL_DOWN_RIGHT, VERTICAL_DOWN,
        DIAGONAL_DOWN_LEFT, HORIZONTAL_BACKWARD
    };

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

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                content[i][j] = '_';
                coordinates.add(new Coordinate(i, j));
            }
        }
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return this.words;
    }

    public void putWordsInGrid(List<String> words) {

       // this.words = words;
        for (String word : words) {

            Collections.shuffle(this.coordinates);
            char[] wordCaracters = word.toCharArray();
            
            for (Coordinate coordinate : coordinates) {

                Direction selectedDirection = getDirectionThatFits(word, coordinate);
             //   System.out.println("Selected Direction, " + selectedDirection);
                if (selectedDirection != null) {
                    switch (selectedDirection) {
                        case DIAGONAL_UP_LEFT:
                            for(char c: wordCaracters) {
                                content[coordinate.x--][coordinate.y--] = c;
                            }
                            break;
                        case VERTICAL_UP:
                            for(char c: wordCaracters) {
                                content[coordinate.x--][coordinate.y] = c;
                            }
                            break;
                        case DIAGONAL_UP_RIGHT:
                            for(char c: wordCaracters) {
                                content[coordinate.x--][coordinate.y++] = c;
                            }
                            break;
                        case HORIZONTAL_FORWARD:
                            for(char c: wordCaracters) {
                                content[coordinate.x][coordinate.y++] = c;
                            }
                            break;
                        case DIAGONAL_DOWN_RIGHT:
                            for(char c: wordCaracters) {
                                content[coordinate.x++][coordinate.y++] = c;
                            }
                            break;
                        case VERTICAL_DOWN:
                            for(char c: wordCaracters) {
                                content[coordinate.x++][coordinate.y] = c;
                            }
                            break;
                        case DIAGONAL_DOWN_LEFT:
                            for(char c: wordCaracters) {
                                content[coordinate.x++][coordinate.y--] = c;
                            }
                            break;
                        case HORIZONTAL_BACKWARD:
                            for(char c: wordCaracters) {
                                content[coordinate.x][coordinate.y--] = c;
                            }
                            break;

                    }
                }
              break;
            }
        }
    }
    
    

    public Direction getDirectionThatFits(String word, Coordinate coordinate) {

        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            
            if(doesFitAndHasFreeSpaces(word, coordinate, direction)) {
                
                return direction;
            }
        }

        return null;
    }

    public void displayGrid() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(content[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean doesFitAndHasFreeSpaces(String word, Coordinate coordinate, Direction direction) {

        int wordLength = word.length();
        switch (direction) {

            case DIAGONAL_UP_LEFT:
                if (coordinate.y < wordLength || coordinate.x < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x - i][coordinate.y - i] != '_') {
                        return false;
                    }
                }
                break;
            case VERTICAL_UP:
                if (coordinate.x < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x - i][coordinate.y] != '_')
                        return false;
                }
                break;
            case DIAGONAL_UP_RIGHT:
                if (coordinate.y + wordLength > this.size || coordinate.x < wordLength) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x - i][coordinate.y + i] != '_')
                        return false;
                }
                break;
            case HORIZONTAL_FORWARD:
                if (coordinate.y + wordLength > this.size) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x][coordinate.y + i] != '_')
                        return false;
                }
                break;
            case DIAGONAL_DOWN_RIGHT:
                if (coordinate.y + wordLength > this.size || coordinate.x + wordLength > this.size) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x + i][coordinate.y + i] != '_')
                        return false;
                }
                break;
            case VERTICAL_DOWN:
                if (coordinate.x + wordLength > this.size) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x + i][coordinate.y] != '_')
                        return false;
                }
                break;
            case DIAGONAL_DOWN_LEFT:
                if (coordinate.y < wordLength || coordinate.x + wordLength > this.size) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x + i][coordinate.y - i] != '_') 
                        return false;
                }
                break;
            case HORIZONTAL_BACKWARD:
                if (coordinate.y - wordLength < 0) return false;
                for (int i = 0; i < wordLength; i++) {
                    if (content[coordinate.x][coordinate.y - i] != '_') 
                       
                    
                        return false;
                }
                break;
        }

        return true;
    }

    private class Coordinate {

        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public void randomFill() {
        
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i<this.size; i++) {
            for (int j=0; j<this.size; j++) {
                if(this.content[i][j] == '_') {
                    int rand = (int)(26*Math.random());
                    this.content[i][j] = letters.charAt(rand);
                }
            }
        }
    }
    
//    public void fillGrid(List<String> words) {
//        for(String word : words) {
//            int wordLength = word.length();
//            Collections.shuffle(this.coordinates);
//            for(Coordinate coordinate : this.coordinates) {
//                int x = coordinate.x;
//                int y = coordinate.y;
//                Direction selectedDirection = this.getDirectionForFit(word, coordinate);
//                if( selectedDirection != null ) {
//                    switch(selectedDirection) {
//                        
//                        
//                    }
//                     for(char c : word.toCharArray()) {
//                    this.content[x][y++] = c;
//                }
//                }
//               
//            }
//        }
//    }
//    
//    public Direction getDirectionForFit(String word, Coordinate coordinate) {
//        
//        List<Direction> directions = Arrays.asList(Direction.values());
//        Collections.shuffle(directions);
//        
//        for(Direction direction : directions) {
//            if(doesFit(word, coordinate, direction))
//                return direction;
//        }
//        
//        return null;
//    }
//    
//    public boolean doesFit(String word, Coordinate coordinate, Direction direction) {
//        
//        int wordLength = word.length();
//        switch(direction) {
//            case HORIZONTAL_FORWARD:
//               if(coordinate.y + wordLength >= this.size) return false;
//               for(int i=0; i<wordLength; i++) {
//                   if(this.content[coordinate.x][coordinate.y + i] != '_')
//                       return false;
//               }
//               break;
//            case VERTICAL_DOWN:
//               if(coordinate.x + wordLength >= this.size) return false;
//               for(int i=0; i<wordLength; i++) {
//                   if(this.content[coordinate.x + i][coordinate.y] != '_')
//                       return false;
//               }
//               break;
//               case DIAGONAL_DOWN_RIGHT:
//               if(coordinate.y + wordLength >= this.size || coordinate.x + wordLength >= this.size) return false;
//               for(int i=0; i<wordLength; i++) {
//                   if(this.content[coordinate.x + i][coordinate.y + i] != '_')
//                       return false;
//               }
//               break;
//        }
//        
//        return true;
//    }
}
