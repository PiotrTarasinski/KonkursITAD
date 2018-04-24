package CreateEvent.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {


    @FXML
    private AnchorPane topAnchor;
    @FXML
    private AnchorPane centerAnchor;

    @FXML
    public void openEvent() {
        setCenter("/fxml/EventFXML.fxml");
    }

    @FXML
    public void openPrelegents() {
        setCenter("/fxml/PrelegentsFXML.fxml");
    }

    @FXML
    public void openRooms() {
        setCenter("/fxml/RoomsFXML.fxml");
    }

    @FXML
    public void openLectures() {
        setCenter("/fxml/LecturesFXML.fxml");
    }
    @FXML
    public void openPreview() {
        setCenter("/fxml/PreviewFXML.fxml");
    }

    @FXML
    private void initialize(){
        openEvent();
    }

    public void setCenter(String fxmlPath){
        Node screen = null;
        try {
            screen = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        centerAnchor.getChildren().setAll(screen);

        AnchorPane.setTopAnchor(screen,0.0);
        AnchorPane.setBottomAnchor(screen,0.0);
        AnchorPane.setRightAnchor(screen,0.0);
        AnchorPane.setLeftAnchor(screen,0.0);
    }

}
