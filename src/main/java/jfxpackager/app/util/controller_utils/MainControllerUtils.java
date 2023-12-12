package jfxpackager.app.util.controller_utils;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class MainControllerUtils {
    public static void findTextPath(String string,
                                    Button button,
                                    FileChooser fileChooserFilter) {

        fileChooserFilter.setInitialDirectory(
                new File(string));

        fileChooserFilter.showOpenDialog(button.getScene().getWindow()).getPath();
    }
}
