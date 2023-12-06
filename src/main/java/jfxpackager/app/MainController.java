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

@Controller
@FxmlView("MainController.fxml")
public class MainController {
    private String pathToApp;
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
        if (textField_AppName.getText().trim().equals("")) {
            return null;
        }

        return textField_AppName.getText();
    }

    private String textFieldIcon() {
        if (textField_Icon.getText().trim().equals("")) {
            return null;
        } else {
            FileChooser fileChooser = FileChooserFilter.imageFileChooser();
            File file = fileChooser.showOpenDialog(null);
            textField_Icon.setText(file.getPath());

            return file.getPath();
        }
    }

    private void textFindPathToApp() {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        textField_PathToApp.setText(file.getPath());
    }


    @FXML
    private void initialize() {
        button_FindIcon.setOnAction(view -> {
            textFieldIcon();
        });

        button_FindPathToApp.setOnAction(view -> {
            textFindPathToApp();
        });

        print();
    }

    private void print() {
        button_Package.setOnAction(view -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Nome do app: ").append(textFieldAppName()).append("\n");
            sb.append("Local do icone: ").append(textFieldIcon()).append("\n");
            sb.append("Diretório do app: ").append(textField_PathToApp).append("\n");

            System.out.println(sb);
        });
    }
}
