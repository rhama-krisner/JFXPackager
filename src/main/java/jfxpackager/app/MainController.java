package jfxpackager.app;


import atlantafx.base.controls.ToggleSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxpackager.app.util.FileChooserFilter;
import jfxpackager.app.util.Theme;
import net.rgielen.fxweaver.core.FxmlView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
@FxmlView("MainController.fxml")
public class MainController {
    String createShortcut;
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
    @FXML
    private TextField textField_appVersion;
    @FXML
    private TextField textField_vendor;

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
    //Check Box
    @FXML
    private CheckBox checkBox_appVersion;
    @FXML
    private CheckBox checkBox_Vendor;
    //Combo Box
    @FXML
    private ComboBox<String> comboBox_PackageType;
    //Toggle Switch
    @FXML
    private ToggleSwitch toggleSwitch_createShortcut;
    @FXML
    private ToggleSwitch toggleSwitch_addDescription;
    @FXML
    private ToggleSwitch toggleSwitch_theme;
    //Text Area
    @FXML
    private TextArea textArea_description;
    //Icon
    @FXML
    private FontIcon fontIcon_theme;

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

        textField_PathToJar.setText(selectedFile.getPath());
    }

    private void textFindMainClass() {
        FileChooser fileChooser = FileChooserFilter.javaClassFileChooser();
        File initialDirectory = new File(textField_PathToApp.getText() + "/src/main/java");
        fileChooser.setInitialDirectory(initialDirectory);

        File selectedFile = fileChooser.showOpenDialog(button_FindMainClass.getScene().getWindow());

        textField_MainClass.setText(selectedFile.getPath());
    }

    private void textFieldDestination() {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        textField_Destination.setText(selectedDirectory.toString());
    }

    private void textFieldAppVersion() {
        checkBox_appVersion.selectedProperty().addListener((observableValue, wasSelected, isNowSelected) -> {
            System.out.println("Ouvinte foi chamado");
            textField_appVersion.setDisable(!isNowSelected);
        });
    }

    private void textFieldVendor() {
        checkBox_Vendor.selectedProperty().addListener((observableValue, wasSelected, isNowSelected) -> {
            System.out.println("Ouvinte foi chamado");
            textField_vendor.setDisable(!isNowSelected);
        });
    }

    private void toggleSwitchCreateShortcut() {
        toggleSwitch_createShortcut.selectedProperty().addListener((observableValue, wasSelected, isNowSelected) -> {
            System.out.println("Create Shortcut habilitado");
            createShortcut = "-- create-shortcut";
        });
    }

    private void textArea_description() {
        toggleSwitch_addDescription.selectedProperty().addListener((observableValue, wasSelected, isNowSelected) -> {
            System.out.println("Ouvinte foi chamado");
            textArea_description.setDisable(!isNowSelected);
        });

        textArea_description.getText();
    }

    private void comboBoxPackageType() {
        comboBox_PackageType.getItems().addAll(
                "app-image", "exe", "msi", "rpm", "deb", "pkg", "dmg");

        comboBox_PackageType.getSelectionModel().getSelectedIndex();
    }

    private void themeConfig() {
        Stage stage = new Stage();
        Theme theme = new Theme();

        toggleSwitch_theme.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            try {
                if (t1) {
                    theme.DarkTheme();
                    fontIcon_theme.setIconLiteral("bi-sun-fill");
                } else {
                    theme.LightTheme();
                    fontIcon_theme.setIconLiteral("bi-moon-fill");

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @FXML
    private void initialize() {
        themeConfig();

        button_FindIcon.setOnAction(view -> textFieldIcon());

        button_FindPathToApp.setOnAction(view -> textFindPathToApp());

        button_FindPathToJar.setOnAction(view -> textFindPathToJar());

        button_FindMainClass.setOnAction(view -> textFindMainClass());

        textFieldAppVersion();

        textFieldVendor();

        toggleSwitchCreateShortcut();

        button_FindDestination.setOnAction(view -> textFieldDestination());

        comboBoxPackageType();

        textArea_description();

        print();
    }

    private void print() {
        button_Package.setOnAction(view -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Nome do app: ").append(textFieldAppName()).append("\n");
            sb.append("Local do icone: ").append(textField_Icon.getText()).append("\n");
            sb.append("Diretório do app: ").append(textField_AppName.getText()).append("\n");
            sb.append("Diretório Jar: ").append(textField_PathToJar.getText()).append("\n");
            sb.append("Diretório classe Main.java: ").append(textField_MainClass.getText()).append("\n");
            sb.append("Diretório de destino: ").append(textField_Destination.getText()).append("\n");
            sb.append("Versão: ").append(textField_appVersion.getText()).append("\n");
            sb.append("Desenvolvedor: ").append(textField_vendor.getText()).append("\n");
            sb.append("Tipo: ").append(comboBox_PackageType.getValue()).append("\n");
            sb.append("Adicionar Atalho: ").append(createShortcut).append("\n");
            sb.append("Descrição: ").append(textArea_description.getText()).append("\n");
            System.out.println(sb);
        });
    }
}
