package jfxpackager.app;


import atlantafx.base.controls.ToggleSwitch;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxpackager.app.util.FileChooserFilter;
import jfxpackager.app.util.ProcessBuilderTool;
import jfxpackager.app.util.PropertiesConfig;
import jfxpackager.app.util.Theme;
import net.rgielen.fxweaver.core.FxmlView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

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

    private String textFieldAppVersion() {
        checkBox_appVersion.selectedProperty().addListener((observableValue, aBoolean, t1) -> textField_appVersion.setDisable(!t1));

        if (checkBox_appVersion.isSelected()) {
            return "--app-version " + textField_appVersion.getText();
        } else {
            return "";
        }
    }

    private String textFieldVendor() {
        checkBox_Vendor.selectedProperty().addListener((observableValue, aBoolean, t1) -> textField_vendor.setDisable(!t1));
        if (checkBox_Vendor.isSelected()) {
            return "--vendor \"" + textField_vendor.getText() + "\"";
        } else {
            return "";
        }

    }

    private String toggleSwitchCreateShortcut() {
        String os = System.getProperty("os.name").toLowerCase();
        if (toggleSwitch_createShortcut.isSelected()) {
            if (os.contains("win")) {
                return "--win-shortcut ";
            } else if (os.contains("nix") || os.contains("nux")) {
                return "--linux-shortcut";
            }

        } else {
            return "";
        }
        ;
        return os;
    }

    private String textArea_description() {
        toggleSwitch_addDescription.selectedProperty().addListener((observableValue, aBoolean, t1) -> textArea_description.setDisable(!t1));
        if (toggleSwitch_addDescription.isSelected()) {
            return "--description \"" + textArea_description.getText() + "\"";
        } else {
            return "";
        }
    }


    private void comboBoxPackageType() {
        comboBox_PackageType.getItems().addAll(
                "app-image", "exe", "msi", "rpm", "deb", "pkg", "dmg");

        comboBox_PackageType.getSelectionModel().getSelectedIndex();
    }

    private void themeConfig() {
        Theme theme = new Theme();
        PropertiesConfig config = new PropertiesConfig();

        toggleSwitch_theme.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            try {
                if (t1) {
                    theme.DarkTheme();
                    config.setPropertyTheme("dark");
                    fontIcon_theme.setIconLiteral("bi-sun-fill");
                } else {
                    theme.LightTheme();
                    config.setPropertyTheme("light");
                    fontIcon_theme.setIconLiteral("bi-moon-fill");

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void testOnSelected() throws IOException {
        PropertiesConfig config = new PropertiesConfig();

        toggleSwitch_theme.setSelected(config.getProperties().equals("dark"));
    }

    @FXML
    private void initialize() throws IOException {
        testOnSelected();

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

        run();
    }

    private String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("jpackage ");
        //Diretorio do app
        sb.append("--input ").append(textField_PathToApp.getText()).append("\\").append(" ");
        //Nome do app
        sb.append("--name ").append(textFieldAppName()).append(" ");
        //Local do icone
        sb.append("--icon ").append(textField_Icon.getText()).append(" ");
        //Diretório Jar
        sb.append("--main-jar ").append(textField_PathToJar.getText()).append(" ");
        //Criar Atalho
        sb.append(toggleSwitchCreateShortcut()).append(" ");
        //Classe main
        sb.append("--main-class ").append(textField_MainClass.getText()).append(" ");
        //Diretorio de destino
        sb.append("--dest ").append(textField_Destination.getText()).append(" ");
        //Versão do aplicativo
        sb.append(textFieldAppVersion()).append(" ");
        //Desenvolvedor
        sb.append(textFieldVendor()).append(" ");
        //Tpo de empacotamento
        sb.append("--type ").append(comboBox_PackageType.getValue()).append(" ");
        //Descrição
        sb.append(textArea_description()).append(" ");
        System.out.println(sb);

        return sb.toString();
    }

    private void run() {
        button_Package.setOnAction(view -> {
            Task task = new Task() {
                @Override
                protected Object call() {
                    ProcessBuilderTool.RunProcess(print());
                    return null;
                }
            };

            new Thread(task).start();
        });
    }
}