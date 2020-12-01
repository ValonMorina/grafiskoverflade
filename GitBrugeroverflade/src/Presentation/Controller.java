package Presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

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
            case RIGHT: //Village
                character.setX(character.getX()+20);
                if (character.getX()>390) {
                Main.setRoot("scene5");
            }
                break;

            case LEFT:  //Brimhaven
                character.setX(character.getX()-20);
                if (character.getX()<-200) {
                    Main.setRoot("scene4");
                }
                break;

            case UP:    //School
                character.setY(character.getY()-20);
                if(character.getY()<-330) {
                    Main.setRoot("scene2");
                }
                //block house
                if(character.getY()<-40 && character.getX()<100 && character.getX()>-100) {
                    character.setY(-40);
                }
                break;

            case DOWN:  //River
                character.setY(character.getY()+20);
                if(character.getY()>160) {
                    Main.setRoot("scene8");
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
        pane.setFocusTraversable(true);
    }
}


