package jfxpackager.app.util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserFilter {
    private static FileChooser filter(String extensao, String msg){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(extensao, msg);
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser;
    }
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
        return filter(jar, "*.jar");
    }

    public static FileChooser javaClassFileChooser(){
        String classe = "Java class file (*.java)";
        return filter(classe, "*.java");
    }
}
