package candycrush;
 
 
//import static candycrush.Candycrush.menumediaPlayer;
import static candycrush.Candycrush.r;
import static candycrush.Candycrush.root2;
import static candycrush.Candycrush.SCENE_WIDTH;
import static candycrush.Candycrush.SCENE_HEIGHT;
//import static candycrush.Candycrush.gamemediaplayer;
import static candycrush.Candycrush.r;
import static candycrush.Candycrush.refresh;
import static candycrush.Candycrush.window;
//import  candycrush.Button.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 *
 * @author Dell
 */
public class Gamemenu extends Parent{
    static Rectangle rec;
   static VBox menu0;

       public  Gamemenu(){
             menu0 = new VBox(30);
          
 
            menu0.setTranslateX(515);
            menu0.setTranslateY(370);
            
 
          Button start = new Button("Start");
            start.setOnMousePressed(event ->{
 
 
                   r=new rand();
                   r.fillBoard();
                   refresh(); 
//                   menumediaPlayer.dispose();
//                   gamemediaplayer.play();
 
            });
 
            Button exit = new Button("Exit");
            exit.setOnMouseClicked(event ->{
                    System.exit(0);
            });
         Button aboutus = new Button ("about us") ;
            aboutus.setOnMousePressed(event ->  {
 
                //Stage primarystage =new Stage();
                Pane root3 =new Pane();
                root3.setPrefSize(800, 600);
                try {
                    InputStream input2 = Files.newInputStream(Paths.get("about_us.PNG"));
                    Image image =new Image(input2);
 
                    ImageView view2 = new ImageView(image);
                    view2.setFitHeight(900);
                    view2.setFitWidth(1280);
                    root3.getChildren().addAll(view2,root2); 
                    Scene scene2 =new Scene(root3, SCENE_WIDTH-630, SCENE_HEIGHT-85);
                    window.setX(300);
                    window.setY(50);
                    window.setScene(scene2);
                    //window.show();    
 
                } catch (IOException ex) {
                    Logger.getLogger(Candycrush.class.getName()).log(Level.SEVERE, null, ex);
                }
//                menumediaPlayer.play();
//                gamemediaplayer.dispose();
// 
               });
 
 
                    menu0.getChildren().addAll(start, aboutus, exit);
 
                    Rectangle rec= new Rectangle(100,60);
                    rec.setFill(Color.BURLYWOOD);
                    rec.setOpacity(0.06);
                    getChildren().addAll(rec,menu0);
 
 
        } 
}