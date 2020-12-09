package Presentation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Scene2Controller implements IController, Initializable {
    public javafx.scene.control.ListView ListView;
    public MenuItem takebutton;
    public MenuItem inspectbutton;
    public TextArea textbox;
    public MenuButton menuButton;
    public ImageView mapImageView;
    public ImageView papir;
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
                character.setX(character.getX()+20);
                if(character.getX()>200) {
                    Main.setRoot("Scene6");
                }
                break;

            case LEFT:
                character.setX(character.getX()-20);
                break;

            case UP:
                character.setY(character.getY()-20);

                break;

            case DOWN:
                character.setY(character.getY()+20);
                if(character.getY()>20) {
                    Main.setRoot("sample");
                }

            case ENTER:
                if (character.getX() < 40 && character.getX() > -20 && character.getY()==-380 && !itemsInventory.contains("papir")) {
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
            textbox.setText("This is paper");
            KeyFrame keyframe = new KeyFrame(Duration.seconds(3), actionEvent1 -> textbox.setVisible(false)); // With Lambda you can use methods as arguments
            Timeline timeline = new Timeline(keyframe);
            timeline.play();

        } else if (actionEvent.getSource() == takebutton) {
            Main.game.addInventory("papir");
            itemsInventory.add("papir");
            papir.setVisible(false);


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

        if (!itemsInventory.contains("papir")) {
            papir.setVisible(true);
        }

    }
}

