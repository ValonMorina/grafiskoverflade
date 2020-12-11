package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Controller {

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
                character.setX(character.getX()+10);
                break;

            case LEFT:
                character.setX(character.getX()-10);
                break;

<<<<<<< Updated upstream
            case UP:
                character.setY(character.getY()-10);
=======
            case UP:    //School
                character.setY(character.getY() - moveCharacterDistance);
                if (character.getY() < -330) {
                    Main.setRoot("scene2");
                }
                //block house
                if (character.getY() < -40 && character.getX() < 100 && character.getX() > 0) {
                    character.setY(-40);
                }
                break;
>>>>>>> Stashed changes

                break;

            case DOWN:
                character.setY(character.getY()+10);
                break;

            case N:
               Main.setRoot("Bathroom");
               System.out.println("Scene loaded successfully");
                break;

            default:
                break;
        }



        System.out.println("-----------------------------");
        System.out.println("X-værdi: " + character.getX());
        System.out.println("Y værdi: " + character.getY());
    }
}


