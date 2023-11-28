package app.jfxpackager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JFXPackager");
        stage.setScene(scene);
        String imgUri = "/img/JFXPackage.png";
        stage.getIcons()
                .add(new Image(
                Objects.requireNonNull(
                        StartApplication.class
                                .getResourceAsStream(imgUri))
        ));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}