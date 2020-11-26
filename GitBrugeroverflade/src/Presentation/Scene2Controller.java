package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class Scene2Controller {
    private static StackPane root = new StackPane();

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

    public static Parent getContent() {
        return root;
    }

    @FXML
    void keyPressed(KeyEvent event) throws InterruptedException, IOException {

        switch (event.getCode()) {
            case RIGHT:
                character.setX(character.getX()+10);
                break;

            case LEFT:
                character.setX(character.getX()-10);
                break;

            case UP:
                character.setY(character.getY()-10);

                break;

            case DOWN:
                character.setY(character.getY()+10);

            default:
                break;
        }

        System.out.println("-----------------------------");
        System.out.println("X-værdi: " + character.getX());
        System.out.println("Y værdi: " + character.getY());
    }
}

