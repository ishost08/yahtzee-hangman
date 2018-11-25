
package Yahtzee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Yahtzee extends Application {
    
    //variables
    private boolean dice[] = new boolean[5];
    private int dice_p[] = new int[5];
    private ImageView diceimg[] = new ImageView[5];
    private Button hold[] = new Button[5];
    private Button buttonRoll = null;
    private Label info = null;
    private Label ones_1 = null;
    private Label ones_2 = null;
    private Label twos_1 = null;
    private Label twos_2 = null;
    private Label threes_1 = null;
    private Label threes_2 = null;
    private Label fours_1 = null;
    private Label fours_2 = null;
    private Label fives_1 = null;
    private Label fives_2 = null;
    private Label sixes_1 = null;
    private Label sixes_2 = null;
    private Label threeofakind_1 = null;
    private Label threeofakind_2 = null;
    private Label fourofakind_1 = null;
    private Label fourofakind_2 = null;
    private Label fullhouse_1 = null;
    private Label fullhouse_2 = null;
    private Label smallstraight_1 = null;
    private Label smallstraight_2 = null;
    private Label largestraight_1 = null;
    private Label largestraight_2 = null;
    private Label chance_1 = null;
    private Label chance_2 = null;
    private Label yahtzee_1 = null;
    private Label yahtzee_2 = null;
    private Label sum_1 = null;
    private Label sum_2 = null;
    private Label bonus_1 = null;
    private Label bonus_2 = null;
    private Label total_1 = null;
    private Label total_2 = null;
    private int cnt = 0;
    private int roll = 0;
    private int turn = 1;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));//Loads the fxml doc
        dice[0] = false;
        dice[1] = false;
        dice[2] = false;
        dice[3] = false;
        dice[4] = false;
        //ArrayList that holds the regular dice
        ArrayList<String> dice_a = new ArrayList<>();
        dice_a.add("1_a.png");
        dice_a.add("2_a.png");
        dice_a.add("3_a.png");
        dice_a.add("4_a.png");
        dice_a.add("5_a.png");
        dice_a.add("6_a.png");
        //ArrayList that holds the red dice
        ArrayList<String> dice_b = new ArrayList<>();
        dice_b.add("1_b.png");
        dice_b.add("2_b.png");
        dice_b.add("3_b.png");
        dice_b.add("4_b.png");
        dice_b.add("5_b.png");
        dice_b.add("6_b.png");
        try
        {
            Scene scene = new Scene(root);
            for(Node node:(ObservableList<Node>)scene.getRoot().getChildrenUnmodifiable())
            {
                if(node instanceof Label&&node.getId()!= null)
                {
                    if(((Label)node).getId().split("_").length == 2)
                    {
                        //System.out.println(((Label)node).getId());
                        ((Label)node).setOnMouseClicked(new EventHandler<MouseEvent>(){
                            @Override
                            public void handle(MouseEvent event) {
                                if(((Label)event.getSource()).getTextFill().toString().equals("0xff0000ff"))
                                {
                                    ((Label)event.getSource()).setTextFill(Color.BLACK);
                                    if(((Label)event.getSource()).getId().split("_")[1].equalsIgnoreCase(""+ turn))
                                    {
                                        clear();
                                    }
                                }
                            }
                        });
                    }
                    if(node.getId().equals("info"))
                    {
                        info = ((Label)node);
                        ((Label)node).setText("Player "+ turn +"'s Turn, You can Roll " + (3-roll) + " Times.");
                    }
                    if(node.getId().equalsIgnoreCase("ones_1"))
                    {
                        ones_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("ones_2"))
                    {
                        ones_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("twos_1"))
                    {
                        twos_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("twos_2"))
                    {
                        twos_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("threes_1"))
                    {
                        threes_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("threes_2"))
                    {
                        threes_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("fours_1"))
                    {
                        fours_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("fours_2"))
                    {
                        fours_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("fours_1"))
                    {
                        fours_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("fours_2"))
                    {
                        fours_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("fives_1"))
                    {
                        fives_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("fives_2"))
                    {
                        fives_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("sixes_1"))
                    {
                        sixes_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("sixes_2"))
                    {
                        sixes_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("threeofakind_1"))
                    {
                        threeofakind_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("threeofakind_2"))
                    {
                        threeofakind_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("fourofakind_1"))
                    {
                        fourofakind_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("fourofakind_2"))
                    {
                        fourofakind_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("fullhouse_1"))
                    {
                        fullhouse_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("fullhouse_2"))
                    {
                        fullhouse_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("smallstraight_1"))
                    {
                        smallstraight_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("smallstraight_2"))
                    {
                        smallstraight_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("largestraight_1"))
                    {
                        largestraight_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("largestraight_2"))
                    {
                        largestraight_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("chance_1"))
                    {
                        chance_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("chance_2"))
                    {
                        chance_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("yahtzee_1"))
                    {
                        yahtzee_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("yahtzee_2"))
                    {
                        yahtzee_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("sum_1"))
                    {
                        sum_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("sum_2"))
                    {
                        sum_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("bonus_1"))
                    {
                        bonus_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("bonus_2"))
                    {
                        bonus_2 = ((Label)node);
                    }
                    if(node.getId().equalsIgnoreCase("total_1"))
                    {
                        total_1 = ((Label)node);
                    }
                    else if(node.getId().equalsIgnoreCase("total_2"))
                    {
                        total_2 = ((Label)node);
                    }
                }
                else if(node instanceof ImageView)
                {
                    if(node.getId().equalsIgnoreCase("dice1"))
                    {
                        diceimg[0] = ((ImageView) node);
                        //new Image(getClass().getResource("1_a.png").toExternalForm());
                        ((ImageView) node).setImage(new Image(getClass().getResource("1_a.png").toExternalForm()));
                    }
                    else if(node.getId().equalsIgnoreCase("dice2"))
                    {
                        diceimg[1] = ((ImageView) node);
                        ((ImageView) node).setImage(new Image(getClass().getResource("2_a.png").toExternalForm()));
                    }
                    else if(node.getId().equalsIgnoreCase("dice3"))
                    {
                        diceimg[2] = ((ImageView) node);
                        ((ImageView) node).setImage(new Image(getClass().getResource("3_a.png").toExternalForm()));
                    }
                    else if(node.getId().equalsIgnoreCase("dice4"))
                    {
                        diceimg[3] = ((ImageView) node);
                        ((ImageView) node).setImage(new Image(getClass().getResource("4_a.png").toExternalForm()));
                    }
                    else if(node.getId().equalsIgnoreCase("dice5"))
                    {
                        diceimg[4] = ((ImageView) node);
                        ((ImageView) node).setImage(new Image(getClass().getResource("5_a.png").toExternalForm()));
                    }
                }
                else if(node instanceof Button)
                {
                    if(((Button)node).getId().equalsIgnoreCase("Roll"))
                    {
                        buttonRoll = ((Button)node);
                        ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                if(((Button)event.getSource()).getId().equalsIgnoreCase("Roll"))
                                {
                                    Random rand = new Random();
                                    
                                    for(int h = 0; h < 5; h++)
                                    {
                                        if(!dice[h])
                                        {
                                            dice_p[h] = rand.nextInt(6);
                                            diceimg[h].setImage(new Image(getClass().getResource((dice_p[h]+1)+"_a.png").toExternalForm()));
                                        }
                                    }
                                    for(int j = 0; j < 5; j++)
                                    {
                                        hold[j].setVisible(true);
                                    }
                                    roll++;
                                    
                                    ones();
                                    twos();
                                    threes();
                                    fours();
                                    fives();
                                    sixes();
                                    threeofakind();
                                    fourofakind();
                                    fullhouse();
                                    smallstraight();
                                    largestraight();
                                    chance();
                                    yahtzee();
                                    sum();
                                    check();
                                    //If the amount of rolls is 3 then ask the player to decide on the points
                                    if(roll == 3)
                                    {
                                        roll = 0;
                                        ((Button)event.getSource()).setVisible(false);
                                        for(int j = 0; j < 5; j++)
                                        {
                                            hold[j].setVisible(false);
                                        }
                                        info.setText("Click on Empty Field to Add Marks!");
                                    }
                                    else
                                    {
                                        info.setText("Player "+ turn +"'s Turn, You can Roll " + (3-roll) + " Times.");
                                    }
                                }
                            }
                        });
                    }
                    else
                    {
                        ((Button)node).setVisible(false);
                        if(((Button)node).getId().equalsIgnoreCase("1"))
                        {
                            hold[0] = ((Button)node);
                        }
                        else if(((Button)node).getId().equalsIgnoreCase("2"))
                        {
                            hold[1] = ((Button)node);
                        }
                        else if(((Button)node).getId().equalsIgnoreCase("3"))
                        {
                            hold[2] = ((Button)node);
                        }
                        else if(((Button)node).getId().equalsIgnoreCase("4"))
                        {
                            hold[3] = ((Button)node);
                        }
                        else if(((Button)node).getId().equalsIgnoreCase("5"))
                        {
                            hold[4] = ((Button)node);
                        }
                        ((Button)node).setOnAction(new EventHandler<ActionEvent>(){
                        //Check if the user decided to hold or not
                            @Override
                            public void handle(ActionEvent event) {
                                if(((Button)event.getSource()).getId().equalsIgnoreCase("1"))
                                {
                                    if(((Button)event.getSource()).getText().equalsIgnoreCase("Hold"))
                                    {
                                        dice[0] = true;
                                        diceimg[0].setImage(new Image(getClass().getResource(dice_b.get(dice_p[0])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Drop");
                                    }
                                    else if(((Button)event.getSource()).getText().equalsIgnoreCase("Drop"))
                                    {
                                        dice[0] = false;
                                        diceimg[0].setImage(new Image(getClass().getResource(dice_a.get(dice_p[0])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Hold");
                                    }
                                }
                                else if(((Button)event.getSource()).getId().equalsIgnoreCase("2"))
                                {
                                    if(((Button)event.getSource()).getText().equalsIgnoreCase("Hold"))
                                    {
                                        dice[1] = true;
                                        diceimg[1].setImage(new Image(getClass().getResource(dice_b.get(dice_p[1])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Drop");
                                    }
                                    else if(((Button)event.getSource()).getText().equalsIgnoreCase("Drop"))
                                    {
                                        dice[1] = false;
                                        diceimg[1].setImage(new Image(getClass().getResource(dice_a.get(dice_p[1])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Hold");
                                    }
                                }
                                else if(((Button)event.getSource()).getId().equalsIgnoreCase("3"))
                                {
                                    if(((Button)event.getSource()).getText().equalsIgnoreCase("Hold"))
                                    {
                                        dice[2] = true;
                                        diceimg[2].setImage(new Image(getClass().getResource(dice_b.get(dice_p[2])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Drop");
                                    }
                                    else if(((Button)event.getSource()).getText().equalsIgnoreCase("Drop"))
                                    {
                                        dice[2] = false;
                                        diceimg[2].setImage(new Image(getClass().getResource(dice_a.get(dice_p[2])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Hold");
                                    }
                                }
                                else if(((Button)event.getSource()).getId().equalsIgnoreCase("4"))
                                {
                                    if(((Button)event.getSource()).getText().equalsIgnoreCase("Hold"))
                                    {
                                        dice[3] = true;
                                        diceimg[3].setImage(new Image(getClass().getResource(dice_b.get(dice_p[3])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Drop");
                                    }
                                    else if(((Button)event.getSource()).getText().equalsIgnoreCase("Drop"))
                                    {
                                        dice[3] = false;
                                        diceimg[3].setImage(new Image(getClass().getResource(dice_a.get(dice_p[3])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Hold");
                                    }
                                }
                                else if(((Button)event.getSource()).getId().equalsIgnoreCase("5"))
                                {
                                    if(((Button)event.getSource()).getText().equalsIgnoreCase("Hold"))
                                    {
                                        dice[4] = true;
                                        diceimg[4].setImage(new Image(getClass().getResource(dice_b.get(dice_p[4])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Drop");
                                    }
                                    else if(((Button)event.getSource()).getText().equalsIgnoreCase("Drop"))
                                    {
                                        dice[4] = false;
                                        diceimg[4].setImage(new Image(getClass().getResource(dice_a.get(dice_p[4])).toExternalForm()));
                                        ((Button)event.getSource()).setText("Hold");
                                    }
                                }
                            }
                        });
                    }
                }
            }
            //Make stage visible
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void ones()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {	
            if(dice_p[g] == 0)
            {
                c++;
            }
        }
        if(turn == 1)
        {
            if(ones_1.getTextFill().toString().equals("0xff0000ff"))
            {
                ones_1.setText("" + c);//set the label for ones to the count
            }
        }
        else if(turn == 2)
        {
            if(ones_2.getTextFill().toString().equals("0xff0000ff"))
            {
                ones_2.setText("" + c);
            }
        }
    }
    public void twos()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {
            if(dice_p[g] == 1)//check if the die is a 2
            {
                c++;
            }
        }
        c *= 2;
        if(turn == 1)
        {
            if(twos_1.getTextFill().toString().equals("0xff0000ff"))
            {
                twos_1.setText("" + c);//set the label for twos
            }
        }
        else if(turn==2)
        {
            if(twos_2.getTextFill().toString().equals("0xff0000ff"))
            {
                twos_2.setText("" + c);
            }
        }
    }
    //repeat the previous steps to test all dice
    public void threes()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {
            if(dice_p[g] == 2)
            {
                c++;
            }
        }
        c *= 3;
        if(turn == 1)
        {
            if(threes_1.getTextFill().toString().equals("0xff0000ff"))
            {
                threes_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(threes_2.getTextFill().toString().equals("0xff0000ff"))
            {
                threes_2.setText("" + c);
            }
        }
    }
    
    
    public void fours()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {
            if(dice_p[g] == 3)
            {
                c++;
            }
        }
        c *= 4;
        if(turn == 1)
        {
            if(fours_1.getTextFill().toString().equals("0xff0000ff"))
            {
                fours_1.setText(""+c);
            }
        }
        else if(turn == 2)
        {
            if(fours_2.getTextFill().toString().equals("0xff0000ff"))
            {
                fours_2.setText(""+c);
            }
        }
    }
    public void check()
    {
        if(turn == 1)
        {
            if(cnt == 26)
            {
                total_1.setText(""+(Integer.parseInt(sum_1.getText())+Integer.parseInt(bonus_1.getText())+Integer.parseInt(threeofakind_1.getText())+Integer.parseInt(fourofakind_1.getText())+Integer.parseInt(fullhouse_1.getText())+Integer.parseInt(smallstraight_1.getText())+Integer.parseInt(largestraight_1.getText())+Integer.parseInt(chance_1.getText())+Integer.parseInt(yahtzee_1.getText())));
            }
        }
        else if(turn == 2)
        {
            if(cnt == 26)
            {
                total_2.setText(""+(Integer.parseInt(sum_2.getText())+Integer.parseInt(bonus_2.getText())+Integer.parseInt(threeofakind_2.getText())+Integer.parseInt(fourofakind_2.getText())+Integer.parseInt(fullhouse_2.getText())+Integer.parseInt(smallstraight_2.getText())+Integer.parseInt(largestraight_2.getText())+Integer.parseInt(chance_2.getText())+Integer.parseInt(yahtzee_2.getText())));
            }
        }
    }
    public void fives()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {
            if(dice_p[g] == 4)
            {
                c++;
            }
        }
        c *= 5;
        if(turn==1)
        {
            if(fives_1.getTextFill().toString().equals("0xff0000ff"))
            {
                fives_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(fives_2.getTextFill().toString().equals("0xff0000ff"))
            {
                fives_2.setText("" + c);
            }
        }
    }
    public void sixes()
    {
        int c = 0;
        for(int g = 0; g < 5; g++)
        {
            if(dice_p[g] == 5)
            {
                c++;
            }
        }
        c *= 6;
        if(turn == 1)
        {
            if(sixes_1.getTextFill().toString().equals("0xff0000ff"))
            {
                sixes_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(sixes_2.getTextFill().toString().equals("0xff0000ff"))
            {
                sixes_2.setText("" + c);
            }
        }
    }
    public boolean threeofakind()
    {
        int c = 0;
        boolean check = false;
        for(int g = 0; g < 5; g++)
        {
            int cnt = 0;
            for(int j = 0; j < 5; j++)
            {
                if(dice_p[j] == dice_p[g])
                {
                    cnt++;
                }
            }
            if(cnt >= 3)
            {
                check = true;
            }
        }
        if(check)
        {
            for(int j = 0; j < 5; j++)
            {
                c+=(dice_p[j] + 1);
            }
        }
        if(turn == 1)
        {
            if(threeofakind_1.getTextFill().toString().equals("0xff0000ff"))
            {
                threeofakind_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(threeofakind_2.getTextFill().toString().equals("0xff0000ff"))
            {
                threeofakind_2.setText("" + c);
            }
        }
        if(check)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean pair()
    {
        int c = 0;
        boolean check = false;
        for(int g = 0;g < 5; g++)
        {
            int cnt = 0;
            for(int j = 0; j < 5; j++)
            {
                if(dice_p[j] == dice_p[g])
                {
                    cnt++;
                }
            }
            if(cnt == 2)
            {
                check = true;
            }
        }
        if(check)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void fourofakind()
    {
        int c = 0;
        boolean check = false;
        for(int g = 0; g < 5; g++)
        {
            int cnt = 0;
            for(int j = 0;j < 5; j++)
            {
                if(dice_p[j] == dice_p[g])
                {
                    cnt++;
                }
            }
            if(cnt >= 4)
            {
                check = true;
            }
        }
        if(check)
        {
            for(int j = 0; j < 5; j++)
            {
                c+=(dice_p[j] + 1);
            }
        }
        if(turn == 1)
        {
            if(fourofakind_1.getTextFill().toString().equals("0xff0000ff"))
            {
                fourofakind_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(fourofakind_2.getTextFill().toString().equals("0xff0000ff"))
            {
                fourofakind_2.setText("" + c);
            }
        }
    }
    public void fullhouse()
    {
        int c = 0;
        if(threeofakind()&&pair())
        {
            c = 25;
        }
        if(turn == 1)
        {
            if(fullhouse_1.getTextFill().toString().equals("0xff0000ff"))
            {
                fullhouse_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(fullhouse_2.getTextFill().toString().equals("0xff0000ff"))
            {
                fullhouse_2.setText("" + c);
            }
        }
    }
    public void chance()
    {
        int c = 0;
        for(int k = 0; k < 5; k++)
        {
            c+=(dice_p[k] + 1);
        }
        if(turn == 1)
        {
            if(chance_1.getTextFill().toString().equals("0xff0000ff"))
            {
                chance_1.setText(""+c);
            }
        }
        else if(turn == 2)
        {
            if(chance_2.getTextFill().toString().equals("0xff0000ff"))
            {
                chance_2.setText("" + c);
            }
        }
    }
    public void smallstraight()
    {
        int c = 0;
        boolean check = true;
        ArrayList<Integer> lp = new ArrayList<>();
        for(int y = 0; y < 5; y++)
        {
            lp.add(dice_p[y]);
        }
        Collections.sort(lp);
        for(int y = 0; y < 4; y++)
        {
            if(lp.get(y) != y)
            {
                check = false;
            }
        }
        if(check)
        {
            c = 30;
        }
        else
        {
            check = true;
            for(int y = 1; y < 5; y++)
            {
                if(lp.get(y) != y)
                {
                    check = false;
                }
            }
            if(check)
            {
                c = 30;
            }
            else
            {
                check = true;
                for(int y = 1; y < 5; y++)
                {
                    if(lp.get(y) != (y+1))
                    {
                        check = false;
                    }
                }
            }
        }
        if(check)
        {
            c = 30;
        }
        if(turn == 1)
        {
            if(smallstraight_1.getTextFill().toString().equals("0xff0000ff"))
            {
                smallstraight_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(smallstraight_2.getTextFill().toString().equals("0xff0000ff"))
            {
                smallstraight_2.setText("" + c);
            }
        }
    }
    public void yahtzee()
    {
        int c = 0;
        boolean check = false;
        ArrayList<Integer> lp = new ArrayList<>();
        int cy = dice_p[0];
        if(dice_p[1] == cy&&dice_p[2] == cy&&dice_p[3] == cy&&dice_p[4] == cy)
        {
            check = true;
        }
        if(check)
        {
            c = 50;
        }
        if(turn == 1)
        {
            if(yahtzee_1.getTextFill().toString().equals("0xff0000ff"))
            {
                yahtzee_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(yahtzee_2.getTextFill().toString().equals("0xff0000ff"))
            {
                yahtzee_2.setText("" + c);
            }
        }
    }
    //Determine the total of regular die points
    public void sum()
    {
        int c = 0;
        if(!ones_1.getText().equalsIgnoreCase("")&&!twos_1.getText().equalsIgnoreCase("")&&!twos_1.getText().equalsIgnoreCase("")&&!threes_1.getText().equalsIgnoreCase("")&&!fours_1.getText().equalsIgnoreCase("")&&!fives_1.getText().equalsIgnoreCase("")&&!sixes_1.getText().equalsIgnoreCase(""))
        {
            c += Integer.parseInt(ones_1.getText());
            c += Integer.parseInt(twos_1.getText());
            c += Integer.parseInt(threes_1.getText());
            c += Integer.parseInt(fours_1.getText());
            c += Integer.parseInt(fives_1.getText());
            c += Integer.parseInt(sixes_1.getText());
        }
        if(turn == 1)
        {
            if(sum_1.getTextFill().toString().equals("0xff0000ff")&&c != 0)
            {
                if(c >= 63)
                {
                    bonus_1.setText("" + 35);
                }
                sum_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(sum_2.getTextFill().toString().equals("0xff0000ff")&&c != 0)
            {
                if(c >= 63)
                {
                    bonus_2.setText("" + 35);
                }
                sum_2.setText("" + c);
            }
        }
    }
    public void largestraight()
    {
        int c = 0;
        boolean check = true;
        ArrayList<Integer> lp = new ArrayList<>();
        for(int y = 0; y < 5; y++)
        {
            lp.add(dice_p[y]);
        }
        Collections.sort(lp);
        for(int y = 0; y < 5; y++)
        {
            if(lp.get(y) != y)
            {
                check = false;
            }
        }
        if(check)
        {
            c = 40;
        }
        if(turn == 1)
        {
            if(largestraight_1.getTextFill().toString().equals("0xff0000ff"))
            {
                largestraight_1.setText("" + c);
            }
        }
        else if(turn == 2)
        {
            if(largestraight_2.getTextFill().toString().equals("0xff0000ff"))
            {
                largestraight_2.setText("" + c);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void clear()
    {
        if(turn == 1)
        {
            turn = 2;
        }
        else
        {
            turn = 1;
        }
        //System.out.println(((Label)event.getSource()).getTextFill());
        roll = 0;
        info.setText("Player " + turn + "'s Turn, You can Roll " + (3-roll) + " Times.");
        for(int j = 0; j < 5; j++)
        {
            hold[j].setVisible(false);
            hold[j].setText("Hold");
            diceimg[j].setImage(new Image(getClass().getResource((j + 1)+"_a.png").toExternalForm()));
            dice[j] = false;
        }
        buttonRoll.setVisible(true);
        cnt++;
        if(cnt == 26)
        {
            buttonRoll.setVisible(false);
            for(int j = 0; j < 5; j++)
            {
                hold[j].setVisible(false);
            }
            if(Integer.parseInt(total_1.getText())>Integer.parseInt(total_2.getText()))
            {
                info.setText("Player 1 is a Winner!");
            }
            else if(Integer.parseInt(total_1.getText())<Integer.parseInt(total_2.getText()))
            {
                info.setText("Player 2 is a Winner!");
            }
            else
            {
                info.setText("It's a Tie!");
            }
        }
        if(ones_1.getTextFill().toString().equals("0xff0000ff"))
        {
           ones_1.setText("");
        }
        if(ones_2.getTextFill().toString().equals("0xff0000ff"))
        {
           ones_2.setText("");
        }
        if(twos_1.getTextFill().toString().equals("0xff0000ff"))
        {
           twos_1.setText("");
        }
        if(twos_2.getTextFill().toString().equals("0xff0000ff"))
        {
           twos_2.setText("");
        }
        if(threes_1.getTextFill().toString().equals("0xff0000ff"))
        {
           threes_1.setText("");
        }
        if(threes_2.getTextFill().toString().equals("0xff0000ff"))
        {
           threes_2.setText("");
        }
        if(fours_1.getTextFill().toString().equals("0xff0000ff"))
        {
           fours_1.setText("");
        }
        if(fours_2.getTextFill().toString().equals("0xff0000ff"))
        {
           fours_2.setText("");
        }
        if(fives_1.getTextFill().toString().equals("0xff0000ff"))
        {
           fives_1.setText("");
        }
        if(fives_2.getTextFill().toString().equals("0xff0000ff"))
        {
           fives_2.setText("");
        }
        if(sixes_1.getTextFill().toString().equals("0xff0000ff"))
        {
           sixes_1.setText("");
        }
        if(sixes_2.getTextFill().toString().equals("0xff0000ff"))
        {
           sixes_2.setText("");
        }
        if(threeofakind_1.getTextFill().toString().equals("0xff0000ff"))
        {
           threeofakind_1.setText("");
        }
        if(threeofakind_2.getTextFill().toString().equals("0xff0000ff"))
        {
           threeofakind_2.setText("");
        }
        if(fourofakind_1.getTextFill().toString().equals("0xff0000ff"))
        {
           fourofakind_1.setText("");
        }
        if(fourofakind_2.getTextFill().toString().equals("0xff0000ff"))
        {
           fourofakind_2.setText("");
        }
        if(fullhouse_1.getTextFill().toString().equals("0xff0000ff"))
        {
           fullhouse_1.setText("");
        }
        if(fullhouse_2.getTextFill().toString().equals("0xff0000ff"))
        {
           fullhouse_2.setText("");
        }
        if(smallstraight_1.getTextFill().toString().equals("0xff0000ff"))
        {
           smallstraight_1.setText("");
        }
        if(smallstraight_2.getTextFill().toString().equals("0xff0000ff"))
        {
           smallstraight_2.setText("");
        }
        if(largestraight_1.getTextFill().toString().equals("0xff0000ff"))
        {
           largestraight_1.setText("");
        }
        if(largestraight_2.getTextFill().toString().equals("0xff0000ff"))
        {
           largestraight_2.setText("");
        }
        if(chance_1.getTextFill().toString().equals("0xff0000ff"))
        {
           chance_1.setText("");
        }
        if(chance_2.getTextFill().toString().equals("0xff0000ff"))
        {
           chance_2.setText("");
        }
        if(yahtzee_1.getTextFill().toString().equals("0xff0000ff"))
        {
           yahtzee_1.setText("");
        }
        if(yahtzee_2.getTextFill().toString().equals("0xff0000ff"))
        {
           yahtzee_2.setText("");
        }
    }
}
