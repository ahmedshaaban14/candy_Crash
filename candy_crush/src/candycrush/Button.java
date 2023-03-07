/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
/**
 *
 * @author Dell
 */
public class Button extends StackPane {
    Text text;
        public   Button (String name ){
            text =new Text(name);
            text.setFont(text.getFont().font(0));
            text.setFill(Color.DARKGRAY);
            Rectangle rec = new Rectangle(250,165);
            rec.setOpacity(0);
            rec.setFill(Color.BLACK);
            rec.setEffect(new GaussianBlur(8));
            setAlignment(Pos.BOTTOM_CENTER);
            setRotate(-0.5);
            getChildren().addAll(rec, text);
 
            setOnMouseEntered(event  ->{
              rec.setTranslateX(10);
              text.setTranslateX(10);
 
              rec.setFill(Color.BLACK);
              text.setFill(Color.WHITE);
 
            });
            setOnMouseExited(event -> {
              rec.setTranslateX(0);
              text.setTranslateX(0);
              rec.setFill(Color.WHITE);
              text.setFill(Color.BLACK);
          }); 
 
          DropShadow shadow = new DropShadow(50,Color.AQUA);
          shadow.setInput(new Glow());
          setOnMousePressed(event  -> setEffect(shadow));
          setOnMouseReleased(event -> setEffect(null));
 
        }
}