package Presentation;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public interface IController {
    public void initialize(URL url, ResourceBundle resourceBundle);
    public void mouseClicked(MouseEvent mouseEvent);
    public void handler(ActionEvent actionEvent);
    public void mouseClickedMap(MouseEvent mouseEvent);
    public void removeItem(MouseEvent mouseEvent);
}
