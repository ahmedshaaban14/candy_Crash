/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ahmed
 */
abstract public class game_logic {  
    abstract public  void fillBoard();
   abstract  public  int random();
   abstract public  GridPane makeGrid();
  abstract  public  boolean checkState();
   abstract public  boolean checkColState();
    abstract  public  boolean checkRawState();
   abstract  public  void remove(int firstRaw , int firstCol , int lastRaw , int lastCol);
   abstract public  void shiftDown(int raw , int f , int l);
   abstract public  void shiftColDown(int col , int f , int l);
    
}
