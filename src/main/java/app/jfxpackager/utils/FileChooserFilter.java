package app.jfxpackager.utils;

import javafx.stage.FileChooser;

import java.io.File;

public class FileChooserFilter {
    public static FileChooser imagesFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"), "Pictures")
        );

        // Verifica o sistema operacional
        String os = System.getProperty("os.name").toLowerCase();

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
