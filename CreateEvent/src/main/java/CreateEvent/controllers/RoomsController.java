package CreateEvent.controllers;

import CreateEvent.logic.Context;
import CreateEvent.logic.Room;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class RoomsController {

    @FXML
    private JFXListView roomList;
    @FXML
    private JFXButton roomAddButton;
    @FXML
    private JFXButton roomRemoveButton;
    @FXML
    private JFXTextField roomName;
    @FXML
    private JFXButton roomSaveButton;

    Room room = new Room();

    @FXML
    private void initialize(){
        roomName.setStyle("-fx-text-inner-color: grey");

        turnEditing(false);

        for(int i = 0; i<Context.getInstance().currentEvent().getRooms().size(); i++){
            roomList.getItems().add(Context.getInstance().currentEvent().getRoom(i).getName());
        }

        roomList.getSelectionModel().clearSelection();
    }

    @FXML
    public void roomListClicked() {
        int currentRoom = roomList.getSelectionModel().getSelectedIndex();
        if(currentRoom!=-1){
            room = Context.getInstance().currentEvent().getRoom(currentRoom);
            roomName.setText(room.getName());
            turnEditing(true);
        }
    }

    @FXML
    public void roomAdd() {
        room = new Room("Sala nr "+roomList.getItems().size());
        roomName.setText(room.getName());
        turnEditing(true);
        //dodaj sale do listy
        roomList.getItems().add(room.getName());
        //zaznacz dodana sale
        roomList.getSelectionModel().selectLast();
        //dodaj sale do obiektu
        Context.getInstance().currentEvent().addRoom(room);
    }

    @FXML
    public void roomRemove() {
        int currentRoom = roomList.getSelectionModel().getSelectedIndex();
        if(currentRoom!=-1){
            Context.getInstance().currentEvent().removeRoom(currentRoom);
            roomList.getItems().remove(currentRoom);
            room = new Room();
            roomName.setText(room.getName());
            turnEditing(false);
            roomList.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void roomSave() {
        int currentRoom = roomList.getSelectionModel().getSelectedIndex();
        room = new Room(roomName.getText());
        Context.getInstance().currentEvent().setRoom(currentRoom,room);
        roomList.getItems().set(currentRoom,room.getName());
        roomList.getSelectionModel().select(currentRoom);
    }

    private void turnEditing(boolean bool){
        roomName.setDisable(!bool);
        roomSaveButton.setDisable(!bool);
        roomRemoveButton.setDisable(!bool);
    }
}
