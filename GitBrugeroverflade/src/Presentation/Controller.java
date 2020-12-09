package Presentation;

import Domain.Points;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public javafx.scene.control.ListView ListView;
    public MenuItem takebutton;
    public MenuItem inspectbutton;
    public TextArea textbox;
    public MenuButton menuButton;
    public ImageView mapImageView;
    public ImageView pickaxe;
    public Button dropButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView character;

    private ObservableList<String> itemsInventory;


    //difference between coordinate systems for character and pickaxe
    int xCoordinateOffset = 230;
    int yCoordinateOffset = 40;

    //The distance the character moves when an arrow-key is pressed to move
    int moveCharacterDistance = 20;

    //Variable to cotain users points for completing build quest.
    Points questScore = new Points();


    @FXML
    void keyPressed(KeyEvent event) throws InterruptedException, IOException {

        switch (event.getCode()) {
            case RIGHT: //Village
                character.setX(character.getX() + moveCharacterDistance);
                if (character.getX() > 390) {
                    Main.setRoot("scene5");
                }
                break;

            case LEFT:  //Brimhaven
                character.setX(character.getX() - moveCharacterDistance);
                if (character.getX() < -200) {
                    Main.setRoot("scene4");
                }
                break;

            case UP:    //School
                character.setY(character.getY() - moveCharacterDistance);
                if (character.getY() < -330) {
                    Main.setRoot("scene2");
                }
                //block house
                if (character.getY() < -40 && character.getX() < 100 && character.getX() > -100) {
                    character.setY(-40);
                }
                break;

            case DOWN:  //River
                character.setY(character.getY() + moveCharacterDistance);
                if (character.getY() > 160) {
                    Main.setRoot("scene8");
                }
                break;

            case ENTER:
                //check to see if item is already in inventory
                if (!itemsInventory.contains("pickaxe")) {
                    //check if character X coordinate is close to pickaxe Y coordinate
                    if (character.getX() >= pickaxe.getX() + xCoordinateOffset-30 && character.getX() <= pickaxe.getX()+xCoordinateOffset+30){

                        //check if character Y coordinate is close to pickaxe Y coordinate
                        if (character.getY() >= pickaxe.getY() - yCoordinateOffset - 30 && character.getY() <= pickaxe.getY() + yCoordinateOffset - 30) {

                            //opens menu when character is close enough to item
                            menuButton.fire();
                        }
                    }
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
        itemsInventory = FXCollections.observableArrayList();
        itemsInventory.addAll(Main.game.getInventory());
        ListView.setItems(itemsInventory);
        if (itemsInventory.contains("pickaxe")) {
            pickaxe.setVisible(false);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {

        if (!ListView.isVisible()) {
            ListView.setVisible(true);
            dropButton.setVisible(true);

        } else if (ListView.isVisible()) {
            ListView.setVisible(false);
            dropButton.setVisible(false);
        }
    }

    public void handler(ActionEvent actionEvent) {
        if (actionEvent.getSource() == inspectbutton) {
            textbox.setVisible(true);
            textbox.setText("This is a pickaxe. You will need this, if you want to build spring protection.");
            KeyFrame keyframe = new KeyFrame(Duration.seconds(3), actionEvent1 -> textbox.setVisible(false)); // With Lambda you can use methods as arguments
            Timeline timeline = new Timeline(keyframe);
            timeline.play();

        } else if (actionEvent.getSource() == takebutton) {
            Main.game.addInventory("pickaxe");
            itemsInventory.add("pickaxe");
            pickaxe.setVisible(false);


        }
    }


    public void mouseClickedMap(MouseEvent mouseEvent) {
        if (!mapImageView.isVisible()) {
            mapImageView.setVisible(true);

        } else if (mapImageView.isVisible()) {
            mapImageView.setVisible(false);
        }
    }

    public void removeItem(MouseEvent mouseEvent) {
        moveItem("pickaxe");
        itemsInventory.remove("pickaxe");
        pickaxe.setVisible(true);
    }

    private void moveItem(String itemName) {
        //identify item
        if (itemName == "pickaxe") {

            //check if item is in inventory
            for (String item: itemsInventory) {
                if (item == "pickaxe") {
                    //move item to player location
                    // set item X to player X coordinate (-30 adds space between item and character)
                    pickaxe.setX(character.getX()-xCoordinateOffset-30);

                    // set item Y to player Y coordinate
                    pickaxe.setY(character.getY()+yCoordinateOffset);

                    //move menu to pickaxe location
                    menuButton.setLayoutX(character.getX()+172);
                    menuButton.setLayoutY(character.getY()+310);
                    
                    break;
                }
            }
        }
    }
}
