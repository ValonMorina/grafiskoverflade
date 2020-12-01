package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Scene4Controller
{

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
            case RIGHT: //TownSquare
                character.setX(character.getX()+10);
                if (character.getX()>40) {
                    Main.setRoot("sample");
                }
                break;

            case LEFT:
                character.setX(character.getX()-10);
                break;

            case UP:
                character.setY(character.getY()-10);

                break;

            case DOWN:
                character.setY(character.getY()+10);
                break;

            case N:
                Main.setRoot("scene2");
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


