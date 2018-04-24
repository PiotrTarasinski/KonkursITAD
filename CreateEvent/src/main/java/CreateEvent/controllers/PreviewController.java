package CreateEvent.controllers;

import CreateEvent.logic.Context;
import CreateEvent.logic.Lecture;
import CreateEvent.logic.Room;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class PreviewController {


    @FXML
    private VBox LectureVbox;
    @FXML
    private VBox previewMainVBox;
    @FXML
    private AnchorPane previewEvent;
    @FXML
    private ImageView eventLogo;
    @FXML
    private Label eventName;
    @FXML
    private Label eventLocalization;
    @FXML
    private Label eventDateAndTime;
    @FXML
    private Label eventDescription;
    @FXML
    private HBox prelegentsHBox;
    @FXML
    private VBox prelegentsVBox1;
    @FXML
    private VBox prelegentsVBox2;
    @FXML
    private VBox prelegentsVBox3;

    @FXML
    private void initialize() {
        setEventView();
        setPrelegentsView();
        setLecturesView();
    }

    private void setEventView() {
        if (Context.getInstance().currentEvent().getLogo() != null) {
            eventLogo.setImage(Context.getInstance().currentEvent().getLogo());
        }
        eventName.setText(Context.getInstance().currentEvent().getName());
        eventDescription.setText(Context.getInstance().currentEvent().getDescription());
        eventLocalization.setText(Context.getInstance().currentEvent().getLocalization());
        eventDateAndTime.setText(Context.getInstance().currentEvent().getDate().toString() + " godz. " + Context.getInstance().currentEvent().getTimeStart() + " - " + Context.getInstance().currentEvent().getTimeEnd());
    }

    private void setPrelegentsView() {
        Image defaultUserPhoto = new Image(String.valueOf(getClass().getResource("/images/user.png")));
        int prelegentsCount = Context.getInstance().currentEvent().getPrelegents().size();

        VBox currentVBox = prelegentsVBox1;
        for (int i = 0; i < prelegentsCount; i++) {
            Circle circle = new Circle(0, 0, 125);
            Label label = new Label();
            JFXTextArea description = new JFXTextArea();

            //set avatar
            if (Context.getInstance().currentEvent().getPrelegent(i).getPhoto() != null) {
                circle.setFill(new ImagePattern(Context.getInstance().currentEvent().getPrelegent(i).getPhoto()));
            } else {
                circle.setFill(new ImagePattern(defaultUserPhoto));
            }

            //set name
            label.setText(Context.getInstance().currentEvent().getPrelegent(i).getName());
            label.setFont(Font.font("System", FontWeight.BOLD, 30));
            label.setTextFill(Color.WHITESMOKE);

            //set description
            description.setText(Context.getInstance().currentEvent().getPrelegent(i).getDescription());
            description.setEditable(false);
            description.setFont(Font.font("System", FontWeight.BOLD, 16));
            description.setStyle("-fx-text-inner-color: grey");
            description.setMinHeight(250);
            description.setMaxHeight(250);
            description.setPadding(new Insets(0, 15, 0, 15));

            //add to VBox
            currentVBox.getChildren().add(circle);
            currentVBox.getChildren().add(label);
            currentVBox.getChildren().add(description);

            //change VBox
            if (currentVBox == prelegentsVBox1) {
                currentVBox = prelegentsVBox2;
            } else if (currentVBox == prelegentsVBox2) {
                currentVBox = prelegentsVBox3;
            } else if (currentVBox == prelegentsVBox3) {
                currentVBox = prelegentsVBox1;
            }

        }

    }

    private void setLecturesView(){
        int roomCount = Context.getInstance().currentEvent().getRooms().size();
        int lectureCount = Context.getInstance().currentEvent().getLectures().size();

        ArrayList<Lecture> lectures = new ArrayList<>(lectureCount);

        ArrayList<Room> rooms = Context.getInstance().currentEvent().getRooms();
        ArrayList<Lecture> lectureDeff = Context.getInstance().currentEvent().getLectures();


        for(int i=0;i<roomCount;i++){
            for(int j=0;j<lectureCount;j++){
                if(rooms.get(i).getName()==lectureDeff.get(j).getRoom().getName()){
                    lectures.add(lectureDeff.get(j));
                }
            }
        }

        String currentRoom = "zxc564346357";
        for(int i=0;i<lectures.size();i++){

            if(currentRoom != lectures.get(i).getRoom().getName()) {
                Label label2 = new Label();
                label2.setText(lectures.get(i).getRoom().getName());
                label2.setFont(Font.font("System", FontWeight.BOLD, 32));
                label2.setTextFill(Color.WHITESMOKE);

                LectureVbox.getChildren().add(label2);
                currentRoom = lectures.get(i).getRoom().getName();
            }
                Label label = new Label();
                label.setText(lectures.get(i).getTimeStart().toString()+" - "+lectures.get(i).getTimeEnd().toString()+" : "+lectures.get(i).getName()+" - "+lectures.get(i).getPrelegent().getName());
                label.setFont(Font.font("System", FontWeight.BOLD, 20));
                label.setTextFill(Color.WHITESMOKE);

                LectureVbox.getChildren().add(label);

        }
    }
}
