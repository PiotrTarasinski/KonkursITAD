package CreateEvent.controllers;

import CreateEvent.logic.Context;
import com.jfoenix.controls.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EventController {

    @FXML
    private Label erroxText;
    @FXML
    private ImageView eventLogo;
    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXTextArea eventLocalization;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTimePicker eventTimeStart;
    @FXML
    private JFXTimePicker eventTimeEnd;
    @FXML
    private JFXTextArea eventDescription;
    @FXML
    private JFXButton eventAcceptButton;

    private static boolean imageChanged = false;

    @FXML
    private void initialize() {
        eventTimeStart.setIs24HourView(true);
        eventTimeEnd.setIs24HourView(true);
        eventName.setStyle("-fx-text-inner-color: grey");
        eventLocalization.setStyle("-fx-text-inner-color: grey");
        eventDate.setStyle("-fx-text-inner-color: grey");
        eventTimeStart.setStyle("-fx-text-inner-color: grey");
        eventTimeEnd.setStyle("-fx-text-inner-color: grey");
        eventDescription.setStyle("-fx-text-inner-color: grey");

        //display name
        eventName.setText(Context.getInstance().currentEvent().getName());
        //display localization
        eventLocalization.setText(Context.getInstance().currentEvent().getLocalization());
        //display description
        eventDescription.setText(Context.getInstance().currentEvent().getDescription());
        //display date
        eventDate.setValue(Context.getInstance().currentEvent().getDate());
        //display timeStart
        eventTimeStart.setValue(Context.getInstance().currentEvent().getTimeStart());
        //display timeEnd
        eventTimeEnd.setValue(Context.getInstance().currentEvent().getTimeEnd());
        //display logo
        if (imageChanged) {
            eventLogo.setImage(Context.getInstance().currentEvent().getLogo());
        }

    }

    @FXML
    public void setEventLogo() {
        FileChooser fileChooser = new FileChooser();
        //Set filter
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            eventLogo.setImage(image);
            imageChanged = true;
        } catch (IOException ex) {
            imageChanged = false;
        }
    }

    @FXML
    public void eventAccept() {
        if(isCorrect()){
            //set name
            Context.getInstance().currentEvent().setName(eventName.getText());
            //set localization
            Context.getInstance().currentEvent().setLocalization(eventLocalization.getText());
            //set description
            Context.getInstance().currentEvent().setDescription(eventDescription.getText());
            //set date
            Context.getInstance().currentEvent().setDate(eventDate.getValue());
            //set timeStart
            Context.getInstance().currentEvent().setTimeStart(eventTimeStart.getValue());
            //set timeEnd
            Context.getInstance().currentEvent().setTimeEnd(eventTimeEnd.getValue());
            //set logo
            if (imageChanged) {
                Context.getInstance().currentEvent().setLogo(eventLogo.getImage());
            }
        }
    }

    private boolean isCorrect() {
        if (eventTimeStart.getValue() == null) {
            erroxText.setText("Wybierz czas rozpoczęcia!");
            return false;
        }
        if (eventTimeEnd.getValue() == null) {
            erroxText.setText("Wybierz czas zakończenia!");
            return false;
        }

        if (eventTimeEnd.getValue().isBefore(eventTimeStart.getValue())) {
            erroxText.setText("Źle podany czas!");
            return false;
        }

        erroxText.setText("");
        return true;
        }


}
