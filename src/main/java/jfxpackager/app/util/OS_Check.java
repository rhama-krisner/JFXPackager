package jfxpackager.app.util;

import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

public class OS_Check {
    static String os = System.getProperty("os.name").toLowerCase();

    public static void CheckOperationalSystemForFileChoose() {
        FileChooser fileChooser = new FileChooser();

        if (os.contains("win")) {
            // Windows
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos ICO (*.ico)", "*.ico");
            fileChooser.getExtensionFilters().add(extFilter);
        } else if (os.contains("nix") || os.contains("nux")) {
            // Linux ou Mac
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos PNG (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
        }

        if (os.contains("mac")) {
            // Mac
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos ICNS (*.icns)", "*.icns");
            fileChooser.getExtensionFilters().add(extFilter);
        }

    }

    public static void comboBoxPackageTypeByOperationalSystem(ComboBox<String> comboBox) {
        if (os.contains("win")) {
            comboBox.getItems().addAll("app-image", "exe", "msi");
        } else if (os.contains("nix") || os.contains("nux")) {
            comboBox.getItems().addAll("app-image", "deb", "rpm");
        } else if (os.contains("mac")) {
            comboBox.getItems().addAll("app-image", "pkg", "dmg");
        }
    }


}

