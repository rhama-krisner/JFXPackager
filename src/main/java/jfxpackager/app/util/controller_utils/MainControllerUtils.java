package jfxpackager.app.util.controller_utils;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class MainControllerUtils {
    public static void findTextPath(String string,
                                    Button button,
                                    FileChooser fileChooserFilter,
                                    TextField textField) {

        fileChooserFilter.setInitialDirectory(
                new File(string));

        String filePath = fileChooserFilter.showOpenDialog(button.getScene().getWindow()).getAbsolutePath();
        textField.setText(filePath);
    }

    public static String checkBoxConfig(String command, CheckBox checkBox, TextField textField) {
        checkBox.selectedProperty().addListener(
                (observableValue, aBoolean, t1) -> textField.setDisable(!t1));
        StringBuilder sb = new StringBuilder();
        if (checkBox.isSelected()) {
            return command + "\"" + textField.getText() + "\"";
        } else {
            return "";
        }
    }


}
