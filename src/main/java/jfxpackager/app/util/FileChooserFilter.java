package jfxpackager.app.util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserFilter {
    public static FileChooser imageFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"), "Pictures")
        );

        OS_Check.CheckOperationalSystem();

        return fileChooser;
    }

    public static FileChooser jarFileChooser(){
        String jar = "Jar files (*.jar)";
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(jar, "*.jar");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }

    public static FileChooser javaFileChooser(){
        String classe = "Java class file (*.java)";
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(classe, "*.java");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }

    public static FileChooser anyFileChooser(String path){
        FileChooser fileChooser = new FileChooser();
        File dir = new File(path);
        fileChooser.setInitialDirectory(dir);

        return fileChooser;
    }
}
