package CreateEvent.controllers;

import CreateEvent.logic.Context;
import CreateEvent.logic.Prelegent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PrelegentsController {

    @FXML
    public JFXButton prelegentSaveButton;
    @FXML
    private JFXButton prelegentAddButton;
    @FXML
    private JFXButton prelegentRemoveButton;
    @FXML
    private JFXListView prelegentList;
    @FXML
    private Circle prelegentPhoto;
    @FXML
    private JFXTextField prelegentName;
    @FXML
    private JFXTextArea prelegentDescription;

    Prelegent prelegent=new Prelegent();
    Image photo=null;

    @FXML
    private void initialize(){
        prelegentName.setStyle("-fx-text-inner-color: grey");
        prelegentDescription.setStyle("-fx-text-inner-color: grey");
        prelegentPhoto.setFill(new ImagePattern(setDefaultImage()));
        turnEditing(false);

        for(int i=0;i<Context.getInstance().currentEvent().getPrelegents().size();i++){
            prelegentList.getItems().add(Context.getInstance().currentEvent().getPrelegent(i).getName());
        }

        prelegentList.getSelectionModel().clearSelection();
    }

    @FXML
    public void setPrelegentPhoto() {
        FileChooser fileChooser = new FileChooser();
        //Set filter
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            photo = SwingFXUtils.toFXImage(bufferedImage, null);
            prelegentPhoto.setFill(new ImagePattern(photo));
        } catch (IOException ex) {
            photo = prelegent.getPhoto();
        }
    }

    private Image setDefaultImage(){
        return  new Image(String.valueOf(getClass().getResource("/images/addUser.png")));
    }

    private void turnEditing(boolean bool){
        prelegentPhoto.setDisable(!bool);
        prelegentName.setDisable(!bool);
        prelegentDescription.setDisable(!bool);
        prelegentSaveButton.setDisable(!bool);
        prelegentRemoveButton.setDisable(!bool);
    }

    private  void setPrelegentView(Prelegent prelegent){
        if(prelegent.getPhoto()!=null){
            prelegentPhoto.setFill(new ImagePattern(prelegent.getPhoto()));
        }
        else{
            prelegentPhoto.setFill(new ImagePattern(setDefaultImage()));
        }
        prelegentName.setText(prelegent.getName());
        prelegentDescription.setText(prelegent.getDescription());
    }


    @FXML
    public void prelegentAdd() {
        prelegent = new Prelegent(null,"Prelegent nr "+prelegentList.getItems().size(),"");
        setPrelegentView(prelegent);
        turnEditing(true);
        //dodaj prelegenta do listy
        prelegentList.getItems().add(prelegent.getName());
        //zaznacz dodanego prelegenta
        prelegentList.getSelectionModel().selectLast();
        //dodaj prelegenta do obiektu
        Context.getInstance().currentEvent().addPrelegent(prelegent);
    }

    @FXML
    public void prelegentRemove() {
        int currentPrelegent = prelegentList.getSelectionModel().getSelectedIndex();
        if(currentPrelegent!=-1){
            Context.getInstance().currentEvent().removePrelegent(currentPrelegent);
            prelegentList.getItems().remove(currentPrelegent);
            prelegent = new Prelegent();
            setPrelegentView(prelegent);
            turnEditing(false);
            prelegentList.getSelectionModel().clearSelection();
            photo = null;
        }
    }
    @FXML
    public void prelegentSave() {
        int currentPrelegent = prelegentList.getSelectionModel().getSelectedIndex();
        prelegent = new Prelegent(photo,prelegentName.getText(),prelegentDescription.getText());
        Context.getInstance().currentEvent().setPrelegent(currentPrelegent,prelegent);
        prelegentList.getItems().set(currentPrelegent,prelegent.getName());
        prelegentList.getSelectionModel().select(currentPrelegent);
        photo = null;
    }

    @FXML
    public void prelegentsListClicked() {
        int currentPrelegent = prelegentList.getSelectionModel().getSelectedIndex();
        if(currentPrelegent!=-1){
            prelegent = Context.getInstance().currentEvent().getPrelegent(currentPrelegent);
            photo = prelegent.getPhoto();
            setPrelegentView(prelegent);
            turnEditing(true);
        }
    }
}
