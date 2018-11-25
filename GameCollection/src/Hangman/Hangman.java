package Hangman;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import java.util.Random;


public class Hangman extends Application
{
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	//***************************
	//List of variables used
	//****************************
	private ObservableList<Node> children;	//ObservableList will store a collection of the pane's children
	int numOfTimesPlayed = 0;
	Random rand = new Random(); //random class instance to generate a random number
	int randNum;	//number to hold the random digit 
	String wordPlayed = "";	//The actual word being played
	StringBuilder hiddenWord = new StringBuilder(""); //The hiddenWord to be displayed
	Boolean correct = false;	
	int wrong;
	int numOfGamesWon = 0;
	int numOfGamesLost = 0;
	static PrintWriter pw;
	Character guess = ' ';	//hold the input of the user
	String AlreadyGuessed = "This letter was already guessed";
	String GuessedIncorrectly = "This letter is not in the word";	
	String ErrorMessage = "Please enter 1 Letter";
	ArrayList <String> usedWords = new ArrayList<String>();
	private Stage stage = new Stage();
	//***************************
	 
	String[] words = new String[]{"Dragon", "Galaxy", "Shadow", "Oblivion", "Iconic", "Epoch", "Serenity", "Dwarf", "Supremacy", "Alcazar", "Solitude", "Wizardry"};
	
	@Override
	public void start(Stage primaryStage)
	{

		//Create a pane to hold the little man
		Pane pane = new Pane();
		
		children = pane.getChildren();
		
		//If the user clicks play on the menu -> call playGame()
		playGame();
		
		//create a scene and place it on the stage
		Scene scene = new Scene(pane,450, 350);
		primaryStage.setTitle("Game of Hangman");
		//Deprive the user of the ability to resize the window  
		primaryStage.setResizable(false);
		
		//Text for display
		Text StartOfGame = new Text(230, 40,"Your Word");
		StartOfGame.setFont(Font.font(30));
		StartOfGame.setFill(Color.BLUE);
		children.add(StartOfGame);
		
		//Text to prompt user to guess a letter
		Text UserPrompt = new Text(230, 180, "Guess Letter: ");
		UserPrompt.setFont(Font.font(25));
		UserPrompt.setFill(Color.BLUE);
		children.add(UserPrompt);
		
		//set the stage and make it visible
		this.stage = primaryStage;
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	
	private void playGame()
	{
		numOfTimesPlayed++;	//Increment the number every time the game is played
		//****Write the numOfTimesPlayed to a file
		CreateGallows();	//Draw the gallows in the beginning of each game
		
		setGameWord();	//Generate and set the random word to be played
		
		hideWord();		//Hide the random word
		
		guessLetter();	//Check if the guess is correct
			
	}
	
	private void CreateGallows()
	{
		//Create the gallows
		Line Gallows1 = new Line(40, 30, 40, 300);
		Line Gallows2 = new Line(40, 30, 150, 30);
				
		//add the gallows to the pane
		children.add(Gallows1);
		children.add(Gallows2);
	}
	
	private void setGameWord()
	{
		//***This function chooses and sets the random word for the game
		//**************************************************************
		//random number form 1 to 12
		randNum = rand.nextInt(11) + 0;
		//Instead of this - create a method that checks for duplicate words
		//in the arraylist and return true or false
		if(!usedWords.contains(wordPlayed))
		{
			wordPlayed = words[randNum]; 
			usedWords.add(wordPlayed);
			
			//Enable to see word in console for testing 
			//System.out.println(wordPlayed);
		}
		//if the game runs out of words notify the user and quit
		if (usedWords.size() >= 12)
		{
			System.out.println("The game is now out of words! Thank you for playing.");
			Platform.exit();
		}		
	}
	
	private void hideWord()
	{
		//***This function replaces each letter of the random word with asterisks and displays it
		//***********************************************************************
		int wordLength = wordPlayed.length();	//Length of the random word
		
		//assign an asterisk for every letter of the word
		for(int i = 0; i < wordLength; i++)
		{
			hiddenWord.append("*");
		}		
	}
	
	private void guessLetter() throws StringIndexOutOfBoundsException
	{
		//Button to submit user input
		//Following lines set the font size, button size and button position on the screen
		Button BtnGo = new Button("GO");
		BtnGo.setFont(Font.font(20));
		BtnGo.setPrefSize(90,50);
		BtnGo.setLayoutX(310);
		BtnGo.setLayoutY(204);
		children.add(BtnGo); 	//add to pane's children, therefore make visible
		
		//Text area for user to type input
		//Following lines set the font size, textfield position on the screen and width of textfield
		TextField userInput = new TextField();
		userInput.setPrefWidth(50);
		userInput.setFont(Font.font(25));
		userInput.setLayoutX(230);
		userInput.setLayoutY(200);
		children.add(userInput);	//add to pane's children, therefore make visible
		
		//This displays the asterisks of the HiddenWord
		Text text = new Text(270, 90, hiddenWord.toString());
		text.setFont(Font.font(STYLESHEET_CASPIAN, FontPosture.ITALIC, 30));
		children.add(text);
		
		//This empty message is later replaced by a notification ,if applicable
		Text message = new Text(210, 110, " ");
		message.setFont(Font.font(19));
		children.add(message);
		
		wrong = 0;	//set the number of wrong guesses to 0 for every wordPlayed
		
		try {
		BtnGo.setOnMouseClicked(event ->
		{
			//set the variable to the user's input
			guess = userInput.getText().charAt(0);	
			correct = false;	//flag to determine if the guess is correct
			
			//test if the input isn't a number and that it's only 1 character
			if(!Character.isDigit(guess) && userInput.getText().length() == 1)
			{
			//iterate through every letter of the word played
				for(int j = 0; j < hiddenWord.length(); j++)
				{	
					if(Character.toUpperCase(guess) == wordPlayed.toUpperCase().charAt(j))
					{
						hiddenWord.setCharAt(j, guess);
						text.setText(hiddenWord.toString());
						correct = true;
						//right++;
						//System.out.println("Right " + right);
						message.setText(" ");
					}		
				}
			}
			//Draw hangman part if the guess doesn't match or too long, or too small
				if(correct == false || userInput.getText().length() > 1 || userInput.getText().length() == 0)
				{
					//increment the number of wrong guesses to pass to hangman switch statement
					wrong++;
					//System.out.println("Wrong " +wrong);
					try 
					{
						drawHangman(wrong);
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
					//Also show the message that the letter doesn't exist in the word
					message.setText(GuessedIncorrectly);
				}
				
				//If all asterisks in the hidden word are replaced by letters - player wins
				if(!hiddenWord.toString().contains("*"))
				{
					try 
					{
						Win();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}

			});
		}
		catch (StringIndexOutOfBoundsException ex)
		{
			ex.printStackTrace();
		}
	
	}
	
	private void drawHangman(int numIncorrect) throws IOException
	{
		
		switch (numIncorrect) 
		{
		case 1:
		{
			//Create the rope
			Line WrongGuess1 = new Line(150,30,150,50);
			children.add(WrongGuess1);
			break;
		}
		
		case 2:
		{
			//Create the head
			Circle WrongGuess2 = new Circle();
			WrongGuess2.setCenterX(150);
			WrongGuess2.setCenterY(90);
			WrongGuess2.setRadius(40);
			WrongGuess2.setStroke(Color.BLACK);
			WrongGuess2.setFill(Color.ALICEBLUE);
			children.add(WrongGuess2);
			break;
		}
		
		case 3:
		{
			//Create the body
			Line WrongGuess3 = new Line(150,130,150,220);
			children.add(WrongGuess3);
			break;
		}
		
		case 4:
		{
			//Create left arm
			Line WrongGuess4 = new Line(150, 140, 100, 190);
			children.add(WrongGuess4);
			break;
		}
		
		case 5:
		{
			//Create right arm
			Line WrongGuess5 = new Line(150, 140, 200, 190);
			children.add(WrongGuess5);
			break;
		}
		
		case 6:
		{
			//Create left leg
			Line WrongGuess6 = new Line(150,220,110,270);
			children.add(WrongGuess6);
			break;
		}
		
		case 7:
		{
			//Create right leg
			Line WrongGuess7 = new Line(150, 220, 190, 270);	
			children.add(WrongGuess7);
			break;
		}
		
		case 8:
		{
			Lose();
			break;
		}
		
	}
		
	}
	
	private void Win() throws IOException
	{
		numOfGamesWon++;
		FileWriter fw = new FileWriter("gamesWon.dat");
		//pw.print(" ");
		
		fw.write("Games Won " + numOfGamesWon);
		//clear the buffer
		fw.flush();
		fw.close();

		//Tell user about the win
		Text wonMessage = new Text(215, 200, "You Won!");
		//Set font size of the  text
		wonMessage.setFont(Font.font(30));
		//Make text blue
		wonMessage.setFill(Color.BLUE);
		
		//Create a pane and put Play Again and Quit buttons in it
		Pane pane = new Pane();
		
		//Button that relaunches the app to play again
		Button btnPlay = new Button("Play Again");
		btnPlay.setFont(Font.font(20));
		btnPlay.setLayoutX(210);
		btnPlay.setLayoutY(210);
		//Restart game on click
		btnPlay.setOnAction(e -> {
			Hangman game = new Hangman();
			game.start(stage);
		});
		
		//Button that quits the app on click
		Button btnQuit = new Button("Quit");
		btnQuit.setFont(Font.font(20));
		btnQuit.setLayoutX(235);
		btnQuit.setLayoutY(260);
		//Exit game on click
		btnQuit.setOnAction(e -> Platform.exit());
		
		//Add buttons to the children list of the pane
		pane.getChildren().add(btnPlay);
		pane.getChildren().add(btnQuit);
		pane.getChildren().add(wonMessage);
		//Create a scene, add a pane to it and choose the size
		Scene scene = new Scene(pane, 500, 500);
		
		Stage stage = new Stage();
		//Make the stage visible and non-resizable
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
	private void Lose() throws IOException
	{
		//increment the number of losses
		numOfGamesLost++;
		FileWriter fw1 = new FileWriter("gamesLost.dat");
		//pw.print(" ");
		
		fw1.write("Games Lost " + numOfGamesLost);
		//clear the buffer
		fw1.flush();
		fw1.close();

		//Tell user about the win
		Text LoseMessage = new Text(215, 200, "You Lost!");
		//Set font size of the  text
		LoseMessage.setFont(Font.font(30));
		//Make text blue
		LoseMessage.setFill(Color.BLUE);
		
		//Create a pane and put Play Again and Quit buttons in it
		Pane pane1 = new Pane();
		
		//Button that relaunches the app to play again
		Button btnPlay1 = new Button("Play Again");
		btnPlay1.setFont(Font.font(20));
		btnPlay1.setLayoutX(210);
		btnPlay1.setLayoutY(210);
		//Restart game on click
		btnPlay1.setOnAction(e -> 
				{
					Hangman game = new Hangman();
					game.start(stage);
				});
		
		//Button that quits the app on click
		Button btnQuit1 = new Button("Quit");
		btnQuit1.setFont(Font.font(20));
		btnQuit1.setLayoutX(235);
		btnQuit1.setLayoutY(260);
		//Quit game on click
		btnQuit1.setOnAction(e -> Platform.exit());
				
		
		//Add buttons to the children list of the pane
		pane1.getChildren().add(btnPlay1);
		pane1.getChildren().add(btnQuit1);
		pane1.getChildren().add(LoseMessage);
		//Create a scene, add a pane to it and choose the size
		Scene scene = new Scene(pane1, 500, 500);
		
		Stage stage = new Stage();
		//Make the stage visible and non-resizable
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	
}
