package jfxpackager.app;


import atlantafx.base.controls.ToggleSwitch;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxpackager.app.util.*;
import jfxpackager.app.util.controller_utils.MainControllerUtils;
import net.rgielen.fxweaver.core.FxmlView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

@Controller
@FxmlView("MainController.fxml")
public class MainController {
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
    private CheckBox checkBox_appVersion;
    @FXML
    private CheckBox checkBox_Vendor;
    @FXML
    private ComboBox<String> comboBox_PackageType;
    @FXML
    private ToggleSwitch toggleSwitch_createShortcut;
    @FXML
    private ToggleSwitch toggleSwitch_addDescription;
    @FXML
    private ToggleSwitch toggleSwitch_theme;
    @FXML
    private TextArea textArea_description;
    @FXML
    private FontIcon fontIcon_theme;

    private String textFieldAppName() {
        return textField_AppName.getText().isBlank() ? null : textField_AppName.getText();
    }

    private void textFieldIcon() {
        FileChooser fileChooser = FileChooserFilter.imageFileChooser();
        File file = fileChooser.showOpenDialog(button_FindIcon.getScene().getWindow());
        textField_Icon.setText(file.getPath());
    }

    private void textFindPathToApp() {
        File file = new DirectoryChooser().showDialog(button_FindPathToApp.getScene().getWindow());
        textField_PathToApp.setText(file.getPath());
    }

    private String appVersion() {
        return MainControllerUtils.checkBoxConfig("--app-version ", checkBox_appVersion, textField_appVersion);
    }

    private String vendor() {
        return MainControllerUtils.checkBoxConfig("--vendor ", checkBox_Vendor, textField_vendor);
    }

    private void textFieldDestination() {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        textField_Destination.setText(selectedDirectory.toString());
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
        OS_Check.comboBoxPackageTypeByOperationalSystem(comboBox_PackageType);

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

        button_FindPathToJar.setOnAction(view -> MainControllerUtils
                .findTextPath(
                        textField_PathToApp.getText() + "/", button_FindPathToApp,
                        FileChooserFilter.jarFileChooser(), textField_PathToJar));

        button_FindMainClass.setOnAction(view -> MainControllerUtils
                .findTextPath(
                        textField_PathToApp.getText() + "/src/main/java",
                        button_FindMainClass,
                        FileChooserFilter.javaClassFileChooser(), textField_MainClass));

        appVersion();

        vendor();

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
        sb.append(appVersion()).append(" ");
        //Desenvolvedor
        sb.append(vendor()).append(" ");
        //Tpo de empacotamento
        sb.append("--type ").append(comboBox_PackageType.getValue()).append(" ");
        //Descrição
        sb.append(textArea_description()).append(" ");
        System.out.println(sb);

        return sb.toString();
    }

    private void run() {
        button_Package.setOnAction(v -> new Thread(() -> new Task<Void>() {
            @Override
            protected Void call() {
                ProcessBuilderTool.RunProcess(print());
                return null;
            }
        }).start());
    }
}