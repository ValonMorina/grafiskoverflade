package Presentation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene3Controller implements IController, Initializable
{
    public javafx.scene.control.ListView ListView;
    public MenuItem takebutton;
    public MenuItem inspectbutton;
    public TextArea textbox;
    public MenuButton menuButton;
    public ImageView mapImageView;
    public ImageView wood;
    public Button dropButton;
    public Button mapbutton;

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
            case RIGHT:
                character.setX(character.getX()+10);
                if (character.getX()>210) {
                    Main.setRoot("scene2");
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
                if (character.getY()>20) {
                    Main.setRoot("scene4");
                }
                break;

            case ENTER:
                if (character.getX() >= 20 && character.getX() <= 70 && character.getY()<-340 && !itemsInventory.contains("wood")) {
                    menuButton.fire();
                }

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
        if (itemsInventory.contains("wood")) {
            wood.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if (!ListView.isVisible()) {
            ListView.setVisible(true);
            dropButton.setVisible(true);

        } else if (ListView.isVisible()) {
            ListView.setVisible(false);
            dropButton.setVisible(false);
        }
    }

    @Override
    public void handler(ActionEvent actionEvent) {
        if (actionEvent.getSource() == inspectbutton) {
            textbox.setVisible(true);
            textbox.setText("This is wood. You will need this if you want to build spring protection!");
            KeyFrame keyframe = new KeyFrame(Duration.seconds(7), actionEvent1 -> textbox.setVisible(false)); // With Lambda you can use methods as arguments
            Timeline timeline = new Timeline(keyframe);
            timeline.play();

        } else if (actionEvent.getSource() == takebutton) {
            Main.game.addInventory("wood");
            itemsInventory.add("wood");
            wood.setVisible(false);


        }
    }
    @Override
    public void mouseClickedMap(MouseEvent mouseEvent) {
        if (!mapImageView.isVisible()) {
            mapImageView.setVisible(true);

        } else if (mapImageView.isVisible()) {
            mapImageView.setVisible(false);
        }
    }

    @Override
    public void removeItem(MouseEvent mouseEvent) {
        String item = (String) ListView.getSelectionModel().getSelectedItem();
        Main.game.removeInventory(item);
        itemsInventory.remove(item);

        if (!itemsInventory.contains("wood")) {
            wood.setVisible(true);
        }

    }
}
