package CreateEvent;


import CreateEvent.logic.Event;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EventCreator");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        primaryStage.show();
    }



}
