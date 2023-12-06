package jfxpackager.app;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxpackager.app.util.FileChooserFilter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Controller
@FxmlView("MainController.fxml")
public class MainController {
    //TextField
    @FXML
    private TextField textField_AppName;
    @FXML
    private TextField textField_Icon;
    @FXML
    private TextField textField_PathToApp;
    @FXML
    private TextField textField_PathToJar;
    @FXML
    private TextField textField_MainClass;
    @FXML
    private TextField textField_Destination;
    //Buttons
    @FXML
    private Button button_FindIcon;
    @FXML
    private Button button_FindPathToApp;
    @FXML
    private Button button_FindPathToJar;
    @FXML
    private Button button_FindMainClass;
    @FXML
    private Button button_FindDestination;
    @FXML
    private Button button_Package;

    @FXML
    private ComboBox<String> comboBox_PackageType;

    private String textFieldAppName() {
        if (textField_AppName.getText().trim().isEmpty()) {
            return null;
        }

        return textField_AppName.getText();
    }

    private void textFieldIcon() {
        FileChooser fileChooser = FileChooserFilter.imageFileChooser();
        File file = fileChooser.showOpenDialog(button_FindIcon.getScene().getWindow());
        textField_Icon.setText(file.getPath());
    }
    private void textFindPathToApp() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(button_FindPathToApp.getScene().getWindow());
        textField_PathToApp.setText(file.getPath());
    }

    private void textFindPathToJar() {
        FileChooser fileChooser = FileChooserFilter.jarFileChooser();
        File initialDirectory = new File(textField_PathToApp.getText() + "/out/artifacts");
        fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(button_FindPathToApp.getScene().getWindow());

        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            System.out.println("Caminho do arquivo selecionado: " + path);
        }

        textField_PathToJar.setText(selectedFile.getPath());
    }



    @FXML
    private void initialize() {
        button_FindIcon.setOnAction(view -> {
            textFieldIcon();
        });

        button_FindPathToApp.setOnAction(view -> {
            textFindPathToApp();
        });

        button_FindPathToJar.setOnAction(view -> {
            textFindPathToJar();
        });

        print();
    }

    private void print() {
        button_Package.setOnAction(view -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Nome do app: ").append(textFieldAppName()).append("\n");
            sb.append("Local do icone: ").append(textField_Icon.toString()).append("\n");
            sb.append("Diretório do app: ").append(textField_AppName.toString()).append("\n");
            sb.append("Diretório Jar: ").append(textField_PathToJar.toString()).append("\n");
            System.out.println(sb);
        });
    }
}
