package Presentation;

import Domain.IGame;
import Domain.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button btnRemove;
    private ObservableList<String> itemsInventory;


    @FXML
    void keyPressed(KeyEvent event) throws InterruptedException, IOException {

        switch (event.getCode()) {
            case RIGHT: //Village
                character.setX(character.getX() + 20);
                if (character.getX() > 390) {
                    Main.setRoot("scene5");
                }
                break;

            case LEFT:  //Brimhaven
                character.setX(character.getX() - 20);
                if (character.getX() < -200) {
                    Main.setRoot("scene4");
                }
                break;

            case UP:    //School
                character.setY(character.getY() - 20);
                if (character.getY() < -330) {
                    Main.setRoot("scene2");
                }
                //block house
                if (character.getY() < -40 && character.getX() < 100 && character.getX() > -100) {
                    character.setY(-40);
                }
                break;

            case DOWN:  //River
                character.setY(character.getY() + 20);
                if (character.getY() > 160) {
                    Main.setRoot("scene8");
                }
                break;

            case ENTER:
                if (character.getX() >= 200 && character.getX() < 280 && !itemsInventory.contains("pickaxe")) {
                    menuButton.fire();
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
            KeyFrame keyframe = new KeyFrame(Duration.seconds(7), actionEvent1 -> textbox.setVisible(false)); // With Lambda you can use methods as arguments
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
        String item = (String) ListView.getSelectionModel().getSelectedItem();
        Main.game.removeInventory(item);
        itemsInventory.remove(item);

        if (!itemsInventory.contains("pickaxe")) {
            pickaxe.setVisible(true);
        }

    }
}
