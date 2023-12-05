package jfxpackager.app.util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserFilter {
    public FileChooser imageFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"), "Pictures")
        );

        return fileChooser;
    }
}
