package Presentation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Scene9Controller implements Initializable {
    public MenuButton menuButton2;
    public MenuItem talkButton;
    public MenuItem buildbutton;
    public TextArea textbox;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView character;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button nailbutton;


    @FXML
    void keyPressed(KeyEvent event) throws InterruptedException, IOException {

        switch (event.getCode()) {
            case RIGHT:
                character.setX(character.getX() + 10);
                break;

            case LEFT:
                character.setX(character.getX() - 10);
                if (character.getX() < -30) {
                    Main.setRoot("scene8");
                }
                break;

            case UP:
                character.setY(character.getY() - 10);
                if (character.getY() < -320) {
                    Main.setRoot("scene5");
                }
                break;

            case DOWN:
                character.setY(character.getY() + 10);
                break;

            case ENTER:
                if (character.getX() >= 485 && character.getX() < 520) {
                    menuButton2.fire();
                }
                break;
            default:
                break;
        }


        System.out.println("-----------------------------");
        System.out.println("X-værdi: " + character.getX());
        System.out.println("Y værdi: " + character.getY());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handler(ActionEvent actionEvent) {
        if (actionEvent.getSource() == talkButton) {
            textbox.setVisible(true);
            textbox.setText("Hi! I'm Jennifer, nice to meet you!" +
                    "\nI am trying to build spring protection, for this beautiful spring to make the water clean." +
                    "\nCan you get these items for me? Pickaxe, wood and pipes. " +
                    "\nCome back when you are ready to 'build' the spring");

            KeyFrame keyframe = new KeyFrame(Duration.seconds(15), actionEvent1 -> textbox.setVisible(false)); // With Lambda you can use methods as arguments
            Timeline timeline = new Timeline(keyframe);
            timeline.play();
        }
    }
}

