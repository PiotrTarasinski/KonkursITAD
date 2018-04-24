package CreateEvent.controllers;

import CreateEvent.logic.Context;
import CreateEvent.logic.Lecture;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LecturesController {

    @FXML
    private Label erroxText;
    @FXML
    private JFXListView lectureList;
    @FXML
    private JFXButton lectureAddButton;
    @FXML
    private JFXButton lectureRemoveButton;
    @FXML
    private JFXTextField lectureName;
    @FXML
    private JFXTimePicker lectureTimeStart;
    @FXML
    private JFXTimePicker lectureTimeEnd;
    @FXML
    private JFXComboBox prelegentChooseComboBox;
    @FXML
    private JFXComboBox roomChooseComboBox;
    @FXML
    private JFXTextArea lectureDescription;
    @FXML
    private JFXButton lectureSaveButton;


    Lecture lecture = new Lecture();
    Boolean correct;
    int currentLecture;

    @FXML
    private void initialize(){
        lectureTimeStart.setIs24HourView(true);
        lectureTimeEnd.setIs24HourView(true);
        lectureName.setStyle("-fx-text-inner-color: grey");
        lectureTimeEnd.setStyle("-fx-text-inner-color: grey");
        lectureTimeStart.setStyle("-fx-text-inner-color: grey");
        prelegentChooseComboBox.setStyle("-fx-text-inner-color: grey");
        roomChooseComboBox.setStyle("-fx-text-inner-color: grey");
        lectureDescription.setStyle("-fx-text-inner-color: grey");

        turnEditing(false);

        //zaladuj prelekcje do listy
        for(int i = 0; i<Context.getInstance().currentEvent().getLectures().size(); i++){
            lectureList.getItems().add(Context.getInstance().currentEvent().getLecture(i).getName());
        }
        //wyczysc zaznaczanie w liscie prelekcji
        lectureList.getSelectionModel().clearSelection();
        //zaladuj prelegentow do comboBoxa
        for(int i=0;i<Context.getInstance().currentEvent().getPrelegents().size();i++){
            prelegentChooseComboBox.getItems().add(Context.getInstance().currentEvent().getPrelegent(i).getName());
        }
        //wyczysc zaznaczanie w comboBoxie prelegentow
        prelegentChooseComboBox.getSelectionModel().clearSelection();

        //zaladuj sale do comboBoxa
        for(int i=0;i<Context.getInstance().currentEvent().getRooms().size();i++){
            roomChooseComboBox.getItems().add(Context.getInstance().currentEvent().getRoom(i).getName());
        }
        //wyczysc zaznaczanie w comboBoxie sal
        roomChooseComboBox.getSelectionModel().clearSelection();
    }

    private void turnEditing(boolean bool){
        lectureName.setDisable(!bool);
        lectureTimeStart.setDisable(!bool);
        lectureTimeEnd.setDisable(!bool);
        lectureDescription.setDisable(!bool);
        lectureSaveButton.setDisable(!bool);
        lectureRemoveButton.setDisable(!bool);
        prelegentChooseComboBox.setDisable(!bool);
        roomChooseComboBox.setDisable(!bool);
    }

    private void setLectureView(Lecture lecture){
        lectureName.setText(lecture.getName());
        lectureDescription.setText(lecture.getDescription());
        lectureTimeStart.setValue(lecture.getTimeStart());
        lectureTimeEnd.setValue(lecture.getTimeEnd());
        prelegentChooseComboBox.getSelectionModel().select(lecture.getPrelegent().getName());
        roomChooseComboBox.getSelectionModel().select(lecture.getRoom().getName());
    }

    @FXML
    public void lectureListClicked() {
        currentLecture = lectureList.getSelectionModel().getSelectedIndex();
        if(currentLecture!=-1){
            lecture = Context.getInstance().currentEvent().getLecture(currentLecture);
            setLectureView(lecture);
            turnEditing(true);
        }
    }

    @FXML
    public void lectureAdd() {
        lecture = new Lecture();
        lecture.setName("Prelekcja nr "+lectureList.getItems().size());
        setLectureView(lecture);
        turnEditing(true);
        currentLecture = -1;
    }

    @FXML
    public void lectureRemove() {
        int currentLecture = lectureList.getSelectionModel().getSelectedIndex();
        if(currentLecture!=-1){
            Context.getInstance().currentEvent().removeLecture(currentLecture);
            lectureList.getItems().remove(currentLecture);
            lecture = new Lecture();
            setLectureView(lecture);
            turnEditing(false);
            lectureList.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void lectureSave() {
        if(isCorrect()) {
            int currentPrelegent = prelegentChooseComboBox.getSelectionModel().getSelectedIndex();
            int currentRoom = roomChooseComboBox.getSelectionModel().getSelectedIndex();
            lecture = new Lecture();
            lecture.setName(lectureName.getText());
            lecture.setDescription(lectureDescription.getText());
            lecture.setTimeStart(lectureTimeStart.getValue());
            lecture.setTimeEnd(lectureTimeEnd.getValue());
            lecture.setPrelegent(Context.getInstance().currentEvent().getPrelegent(currentPrelegent));
            lecture.setRoom(Context.getInstance().currentEvent().getRoom(currentRoom));
            if(currentLecture==-1) {
                Context.getInstance().currentEvent().addLecture(lecture);
                lectureList.getItems().add(lectureList.getItems().size(),lecture.getName());
                lectureList.getSelectionModel().selectLast();
            }
            else{
                Context.getInstance().currentEvent().setLecture(currentLecture,lecture);
                lectureList.getItems().set(currentLecture, lecture.getName());
                lectureList.getSelectionModel().select(currentLecture);
            }
        }
    }

    private boolean isCorrect(){
        if(lectureTimeStart.getValue()==null){
            erroxText.setText("Wybierz czas rozpoczęcia prelekcji!");
            return false;
        }
        if(lectureTimeEnd.getValue()==null){
            erroxText.setText("Wybierz czas zakończenia prelekcji!");
            return false;
        }
        if(!(Context.getInstance().currentEvent().getTimeStart().equals(lectureTimeStart.getValue()) || Context.getInstance().currentEvent().getTimeStart().isBefore(lectureTimeStart.getValue()))){
            erroxText.setText("Prelekcja nie może rozpocząć się przed rozpoczęciem eventu!");
            return false;
        }
        if(!(Context.getInstance().currentEvent().getTimeEnd().isAfter(lectureTimeStart.getValue()))){
            erroxText.setText("Prelekcja nie może rozpocząć się po zakończeniu eventu!");
            return false;
        }
        if(Context.getInstance().currentEvent().getTimeEnd().isBefore(lectureTimeEnd.getValue())){
            erroxText.setText("Prelekcja nie może kończyć się po zakończeniu eventu!");
            return false;
        }
        if(lectureTimeEnd.getValue().isBefore(lectureTimeStart.getValue())){
            erroxText.setText("Prelekcja nie może kończyć się przed jej rozpoczęciem!");
            return false;
        }
        if(prelegentChooseComboBox.getSelectionModel().getSelectedIndex()==-1){
            erroxText.setText("Musisz wybrać prelegenta!");
            return false;
        }
        if(roomChooseComboBox.getSelectionModel().getSelectedIndex()==-1){
            erroxText.setText("Musisz wybrać sale!");
            return false;
        }


        erroxText.setText("");
        return true;
    }
}
