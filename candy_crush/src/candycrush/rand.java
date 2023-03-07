/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;
import static candycrush.Candycrush.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
 
 
public   class rand  extends game_logic {
 
 @Override
 public  void fillBoard(){
                    // create oyunTahtasi array according to the row and column numbers
                    board = new int[COLS][ROWS];
                    for ( int i = 0 ; i < board.length ; i++ ) {
                              for ( int j = 0 ; j < board[i].length ; j++ ) {
                                        // set the tile on game board to the random number
                                        board[i][j] = random();
                              }
                    }
//                    displayBoard();
                    while ( checkState() ) {
//                              displayBoard();
                    }
                    displayBoard();
          }
 
 
 @Override
          public  int random() {
                    return (int)(Math.random() * imagesPath.length);
          }
 
 
 
 @Override
          public  GridPane makeGrid() {
                    grid = new GridPane();
//                    grid.setBackground(backgroundpic);
                    grid.setPrefSize(GRID_WIDTH , GRID_HEIGHT);
//                    grid.setAlignment(Pos.CENTER);
                    for ( int rowIndex = 0 ; rowIndex < ROWS ; rowIndex++ ) {
                              //an array to hold buttons of one row 
                              for ( int colIndex = 0 ; colIndex < COLS ; colIndex++ ) {
                                        String path = Candycrush.imagesPath[board[rowIndex][colIndex]];
                                        ImageView imageView = new ImageView(new Image(path));
                                        imageView.setFitHeight(IMAGE_HEIGHT);
                                        imageView.setFitWidth(IMAGE_WIDTH);
                                        imageView.setCursor(Cursor.HAND);
                                        grid.add(imageView , colIndex , rowIndex);
                              }
                    }
                    return grid;
          }
//
//
//          public void board_size_rand() {
//
//                    // put random numbers between 1 and 5 into each tile on oyunTahtasi
//                    // these numbers will show which character is on a given tile
//                    for ( int i = 0 ; i < 10 ; i++ ) {
//                              for ( int j = 0 ; j < 10 ; j++ ) {   // find a random number
//                                        Random rand = new Random();
//                                        // rastgele sayı ( 1 <= sayi < 6 ) şeklinde olacak
//                                        // ( 1 <= random number < 6)
//                                        // generate the random number that is needed
//                                        int candynum = rand.nextInt(6);
//                                        board[i][j] = candynum;
//
//                              }
//                    }
//          }
 
 
 
//          public static void main(String[] args) {
//                    fillBoard();
//          }
 @Override
           public  boolean checkState() {
                    return checkRawState() || checkColState();
          }
 
 
 
          public  boolean checkColState() {
                    boolean state = false;
                    int count = 1;
                    int i = 0;
                    for ( int j = 0 ; j < board[i].length ; j++ ) {
                              count = 1;
                              for ( i = 0 ; i < board.length - 1 ; i++ ) {
                                        if ( board[i][j] == board[i + 1][j] ) {
                                                  count++;
                                        } else if ( count >= 3 ) {
                                                  state = true;
                                                  remove(i - count + 1 , j , i , j);
                                                  count = 1;
                                                  int x=count*6;
                                                  score.set(score.get()+x);
                                        } else {
                                                  count = 1;
                                        }
                              }
                              if ( count >= 3 ) {
                                        state = true;
                                        remove(board.length - count , j , board.length - 1 , j);
                                        int x=count*6;
                                        score.set(score.get()+x);
                              }
                    }
                    while ( state && checkState() ) {
                    }
//                    System.out.println(state);
                    return state;
          }
 
 
 
 @Override
          public  boolean checkRawState() {
                    boolean state = false;
                    int count = 1;
                    for ( int i = 0 ; i < board.length ; i++ ) {
                              count = 1;
                              for ( int j = 0 ; j < board[i].length - 1 ; j++ ) {
                                        if ( board[i][j] == board[i][j + 1] ) {
                                                  count++;
                                        } else if ( count >= 3 ) {
                                                  state = true;
                                                  remove(i , j - count + 1 , i , j);
                                                  int x=count*6;
                                                  score.set(score.get()+x);
                                                  count = 1;
 
                                        } else {
                                                  count = 1;
                                        }
                              }
                              if ( count >= 3 ) {
                                        state = true;
                                        remove(i , board[i].length - count , i , board[i].length - 1);
                                        int x=count*6;
                                         score.set(score.get()+x);
                              }
                    }
                    while ( state && checkState() ) {
                    }
//                    System.out.println(state);
                    return state;
          }
 
 
 
 @Override
          public  void remove(int firstRaw , int firstCol , int lastRaw , int lastCol) {
                    if ( firstCol == lastCol ) {
//                              System.out.println("colum : " + lastCol);
//                              System.out.println(firstRaw + "\t" + lastRaw);
                              shiftColDown(firstCol , firstRaw , lastRaw);
                    } else if ( firstRaw == lastRaw ) {
//                              System.out.println("raw : " + lastRaw);
//                              System.out.println(firstCol + "\t" + lastCol);
                              shiftDown(firstRaw , firstCol , lastCol);
                    }
          }
 
 
 
 @Override
          public  void shiftDown(int raw , int f , int l) {
                    for ( int i = f ; i <= l ; i++ ) {
                              shiftColDown(i , raw , raw);  
                    }
          }
 
 @Override
          public  void shiftColDown(int col , int f , int l) {
//                    System.out.println(col + "\n" + f + "   " + l);
                    int first = f;
                    int i;
                    for ( i = l ; i >= f ; i-- ) {
                              if ( first > 0 ) {
                                        --first;
                                        board[i][col] = board[first][col];
                              } else {
                                        break;
                              }
                    }
                    for ( int j = 0 ; j <= i ; j++ ) {
                              board[j][col] = random();
                    }
          }
 
 
          public  void displayBoard() {
                    for ( int i = 0 ; i < 8 ; i++ ) {
                              for ( int j = 0 ; j < 8 ; j++ ) {
                                        System.out.print(board[i][j] + " ");
                              }
                              System.out.println();
                    }
                    System.out.println();
                    System.out.println();
                    System.out.println();
          }
 
}