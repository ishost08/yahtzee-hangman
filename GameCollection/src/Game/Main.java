package Game;




import Hangman.Hangman;
import Yahtzee.Yahtzee;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.text.Text;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		
	}
	
	
         
       

		
    @Override
    public void start(Stage primaryStage) {
        

       //Sets the Stage
		 primaryStage.setTitle("Game Collection");
		 primaryStage.show();
		 
		 //Create a pane 
		  Pane pane = new Pane();
		 
	         //Scene to display the menu
		  
	         Scene scene = new Scene(pane, 400, 500);
	         scene.setFill(Color.AQUAMARINE);
	         //Set the stage for the game chosen
	         Stage stage = new Stage();
	 		
	 		primaryStage.setScene(scene);
	 		primaryStage.setResizable(false);
	 		primaryStage.show();
	 		
	 		Label label = new Label("Pick a Game");
	         
	 		//Button to start the hangman game
	         Button startH = new Button("Start Hangman");
	         startH.setLayoutX(165);
	 		startH.setLayoutY(260);
	         pane.getChildren().add(startH);
	         startH.setOnAction(e -> {
	 			Hangman game = new Hangman();
	 			game.start(stage);
	 		});
			
	         //Button to start the yahtzee game
	         Button startY = new Button("Start Yahtzee");
	         startY.setLayoutX(170);
	 		startY.setLayoutY(220);
	         pane.getChildren().add(startY);
	         startY.setOnAction(e -> {
	 			Yahtzee game = new Yahtzee();
	 			try {
					game.start(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	 		});
                      
                      
    }
}
