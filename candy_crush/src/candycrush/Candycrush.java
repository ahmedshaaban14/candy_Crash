
package candycrush;




import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class Candycrush extends Application {
 
          static final int COLS = 8;
          static final int ROWS = 8;
 
          static final int SCENE_WIDTH = 2000;
          static final int SCENE_HEIGHT = 1000;
 
          static final double RATIO1 = 0.75;
          static final double RATIO2 = 0.5;
 
          static final double GRID_WIDTH = RATIO2 * SCENE_WIDTH;
          static final double GRID_HEIGHT = RATIO1 * SCENE_HEIGHT;
 
          static final double IMAGE_HEIGHT = GRID_HEIGHT / ROWS;
          static final double IMAGE_WIDTH = GRID_WIDTH / COLS;
 
          static final double X = (SCENE_WIDTH - GRID_WIDTH) / 16;
          static final double Y = (SCENE_HEIGHT - GRID_HEIGHT) / 2;
 
          static rand r;
          static GridPane grid;
 
          private static ImageView first;
          private static ImageView second;
 
          static int clickCounter = 0;
 
          static int[][] board;
          static String[] imagesPath = {
                    "file:bluecandy.png" ,
                    "file:yellowcandy.png" ,
                    "file:orangecandy.png" ,
                    "file:purplecandy.png" ,
                    "file:greencandy.png" ,
                    "file:redcandy.png" };
 
          Node firstcheckstate = null;
          int countlike = 0;
 
         static int numofswaps = 0;
 
          //static Scene scene;
          static Stage stage;
          //for score
          static IntegerProperty score=new SimpleIntegerProperty();
          static Text scoreText=new Text();
          static MediaView mediaview;
          static Stage window;
          static  Group root2;
          static  Scene scene1,scene3,scene2;
          static MediaPlayer mediaPlayer;
           Gamemenu gamemenu;
          static ImageView girlview;
    public void start (Stage stage) throws Exception{
        String musicname="Candy-Crush-Loop5.WAV";
        Media media=new Media(Paths.get(musicname).toUri().toString());//"file:/Candy-Crush-Loop5.WAV");
        MediaPlayer mediaplayer=new MediaPlayer(media);
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.setVolume(0.5);
        mediaplayer.play();// stop it when not play
        mediaview=new MediaView(mediaplayer);
        //for text of score
        scoreText.setTranslateY(150);
        scoreText.setTranslateX(1274);
        scoreText.textProperty().bind(score.asString("Score: %d"));
        scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,68));
        scoreText.setFill(Color.RED); 
        //Setting the Stroke  
        scoreText.setStrokeWidth(2); 
        //Setting the stroke color 
        scoreText.setStroke(Color.GREENYELLOW); 
        //Striking through the text //scoreText.setStrikethrough(true);
        //underlining the text     
        scoreText.setUnderline(true);
        //scoreText.setText("Score+ "+score);
 /*back button*/
 
        javafx.scene.control.Button back_button = new javafx.scene.control.Button("BACK");
 
        back_button.setTranslateX(0);
        back_button.setTranslateY(0);
        back_button.setStyle(
                "-fx-background-radius: 10em; " +
                "-fx-min-width: 3px; " 
 
        );
        //back_button.setStyle("-fx-border-radius: 30");
        // button هنا قمنا بتغيير نوع و حجم خط الكائن
        back_button.setFont(new Font("Arial", 18));
        back_button.setTextFill(Color.RED);
         root2 = new Group();
 
        root2.getChildren().add(back_button);
 
 
 
        DropShadow shadow = new DropShadow(10, Color.RED);
 
        back_button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            back_button.setEffect(shadow);
        });
 
 
        back_button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            back_button.setEffect(null);
        });
        back_button.setOnMouseClicked(e ->{
                window.setX(0);
                window.setY(0);
               window.setScene(scene1);
            });
    InputStream girl= Files.newInputStream(Paths.get("Tiffitransparency.PNG"));
        Image girlimage =new Image(girl);
        girlview = new ImageView(girlimage);
        girlview.setTranslateX(1274);
        girlview.setTranslateY(400);
/*end back button*/
 
 
        window=stage;
        Pane root =new Pane();
        root.setPrefSize(1220, 900);
        music();
 
        InputStream input= Files.newInputStream(Paths.get("background.JPG"));
        Image image =new Image(input);
        ImageView view = new ImageView(image);
        view.setFitHeight(900);
        view.setFitWidth(1280);
        gamemenu= new Gamemenu(); 
         root.getChildren().addAll(view,gamemenu);
       // root.getChildren().addAll(view , gamemenu);
         scene1 =new Scene(root);
 
        window.setScene(scene1); 
        window.setTitle("Candy Crush Saga");//title of window/stage should have only one scene attatched to it if i want to put a new stage i can disattatch the old one
        window.getIcons().add(new Image("file:icon.png"));
        window.show();
 
 
 
 
    }
 
    public void music() {
		String s = "music.mp3";
		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}
 
 
 
          public static void refresh() {
                    //rand r2=new rand();
                    grid = (GridPane) r.makeGrid();
                   grid.setTranslateX(X);
                    grid.setTranslateY(Y);
                   grid.setOnMousePressed((e) -> action(e));
                    Pane pane = new Pane();
                    pane.getChildren().addAll(grid,mediaview,scoreText,girlview,root2);                   
            pane.setStyle(
            "-fx-background-image: url(\"file:candies_background.JPG\"); " +
            "-fx-background-size: cover;"
        );
            window.setX(0);
            window.setY(0);
             scene2=new Scene(pane , SCENE_WIDTH , SCENE_HEIGHT);
                    window.setScene(scene2);
                                                                                           //  if(numofswaps==5&&score.get()>50)
                                                                                            // {
                                                                                                // window.close();
                                                                                             //}
 
          }
 
          public static void action(MouseEvent e) {
                    for ( Node node : grid.getChildren() ) {
                              if ( node.getBoundsInParent().contains(e.getX() , e.getY()) ) {
                                        ImageView image = (ImageView) node;
                                        if ( clickCounter == 0 ) {
                                                  first = image;
                                        } else if ( first != image ) {
                                                  second = image;
                                                  int firstRow = GridPane.getRowIndex(first);
                                                  int firstCol = GridPane.getColumnIndex(first);
                                                  int secondRow = GridPane.getRowIndex(second);
                                                  int secondCol = GridPane.getColumnIndex(second);
 
                                                  if ( isNeerBy() ) {
                                                            int tem = board[firstRow][firstCol];
                                                            board[firstRow][firstCol] = board[secondRow][secondCol];
                                                            board[secondRow][secondCol] = tem;
                                                            if ( r.checkState() ) {
                                                                      swap();
                                                                      numofswaps++;
                                                                         if(numofswaps==7&&score.get()>50)
                    {numofswaps=0;
                      score.set(0);
 
                Stage primarystage =new Stage();
                Pane result1 =new Pane();
                result1.setPrefSize(600, 200);
                try {
                       InputStream input2 = Files.newInputStream(Paths.get("cangratulation.jfif"));
                         Image image_solu =new Image(input2);
                          ImageView view2 = new ImageView(image_solu);
                            view2.setFitHeight(200);
                            view2.setFitWidth(600);
                            result1.getChildren().addAll(view2); 
                             scene3 =new Scene(result1);
                             primarystage.setScene(scene3);
                             //window.close();
 
                } catch (IOException ex) {
                    Logger.getLogger(Candycrush.class.getName()).log(Level.SEVERE, null, ex);
                }
 
primarystage.show();
 
 //window.show();
 
 
 
          }
 
 
                                                            } else {
                                                                      tem = board[firstRow][firstCol];
                                                                      board[firstRow][firstCol] = board[secondRow][secondCol];
                                                                      board[secondRow][secondCol] = tem;
                                                                      swapBack();
                                                            }
                                                  }
                                        } else {
                                                  first = null;
                                        }
                                        clickCounter = ++clickCounter % 2;
                                        System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node));
                                        break;
                              }
                    }
 
          }
 
 
 
 
 
 
          public static boolean isNeerBy() {
                    int firstRow = GridPane.getRowIndex(first);
                    int firstCol = GridPane.getColumnIndex(first);
                    int secondRow = GridPane.getRowIndex(second);
                    int secondCol = GridPane.getColumnIndex(second);
                    return (secondCol == firstCol && Math.abs(firstRow - secondRow) <= 1)
                            || (firstRow == secondRow && Math.abs(firstCol - secondCol) <= 1);
          }
 
          public static void swapBack() {
                    int firstRow = GridPane.getRowIndex(first);
                    int firstCol = GridPane.getColumnIndex(first);
                    int secondRow = GridPane.getRowIndex(second);
                    int secondCol = GridPane.getColumnIndex(second);
//                    System.out.println(board[firstRow][firstCol]);
//                    System.out.println(board[secondRow][secondCol]);
//                    int tem = board[firstRow][firstCol];
//                    board[firstRow][firstCol] = board[secondRow][secondCol];
//                    board[secondRow][secondCol] = tem;
//                    System.out.println(board[firstRow][firstCol]);
//                    System.out.println(board[secondRow][secondCol]);
 
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(0.5) ,
                                    new KeyValue(first.translateXProperty() , (secondCol - firstCol) * (IMAGE_WIDTH)) ,
                                    new KeyValue(second.translateXProperty() , (firstCol - secondCol) * (IMAGE_WIDTH)) ,
                                    new KeyValue(first.translateYProperty() , (secondRow - firstRow) * (IMAGE_HEIGHT)) ,
                                    new KeyValue(second.translateYProperty() , (firstRow - secondRow) * (IMAGE_HEIGHT))
                            ) ,
                            new KeyFrame(Duration.seconds(1) ,
                                    new KeyValue(first.translateXProperty() , 0) ,
                                    new KeyValue(second.translateXProperty() , 0) ,
                                    new KeyValue(first.translateYProperty() , 0) ,
                                    new KeyValue(second.translateYProperty() , 0)
                            ));
                    timeline.play();
          }
 
          public static void swap() {
                    int firstRow = GridPane.getRowIndex(first);
                    int firstCol = GridPane.getColumnIndex(first);
                    int secondRow = GridPane.getRowIndex(second);
                    int secondCol = GridPane.getColumnIndex(second);
 
//                    int tem = board[firstRow][firstCol];
//                    board[firstRow][firstCol] = board[secondRow][secondCol];
//                    board[secondRow][secondCol] = tem;
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(0.5) ,
                                    new KeyValue(first.translateXProperty() , (secondCol - firstCol) * (IMAGE_WIDTH)) ,
                                    new KeyValue(second.translateXProperty() , (firstCol - secondCol) * (IMAGE_WIDTH)) ,
                                    new KeyValue(first.translateYProperty() , (secondRow - firstRow) * (IMAGE_HEIGHT)) ,
                                    new KeyValue(second.translateYProperty() , (firstRow - secondRow) * (IMAGE_HEIGHT))
                            ) ,
                            new KeyFrame(Duration.seconds(0.5) , (e) -> {
                                      first.setTranslateX(0);
                                      first.setTranslateY(0);
                                      second.setTranslateX(0);
                                      second.setTranslateY(0);
                                      grid.getChildren().removeAll(first , second);
                                      grid.add(first , secondCol , secondRow);
                                      grid.add(second , firstCol , firstRow);
                                      refresh();
                            }
                            ));
                    timeline.play();
          }
}