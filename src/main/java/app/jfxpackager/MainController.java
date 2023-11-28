package app.jfxpackager;

import app.jfxpackager.utils.Alerta;
import app.jfxpackager.utils.FileChooserFilter;
import app.jfxpackager.utils.ProcessBuilderTool;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MainController implements Initializable {

    private String stringImagePath;
    private String directoryPath;
    private String jarName;
    private String mainJar;
    private String pathJavaMainClass;
    private String destinationPath;
    @FXML
    private TextField txtAppName;
    @FXML
    private Button btnEscolherImagem;
    @FXML
    private TextField txtImagemPath;

    @FXML
    private TextField txtAppDirectory;
    @FXML
    private Button btnAppDirectory;

    @FXML
    private TextField txtJar;
    @FXML
    private Button btnJarPath;

    @FXML
    private TextField txtMainClass;
    @FXML
    private Button btnMainClass;

    @FXML
    private TextField txtDestination;
    @FXML
    private Button btnDestination;

    @FXML
    private TextField txtAppVersion;

    @FXML
    private TextField txtVendor;

    @FXML
    private ComboBox<String> packageType = new ComboBox<>();

    @FXML
    private CheckBox cbShortcut;

    @FXML
    private Button btnPackage;

    @FXML
    private Label lblPacking;

    private String setTxtAppName() {
        return txtAppName.getText();
    }

    @FXML
    private void btnEscolherImagemOnClick() {
        FileChooser fileChooser = FileChooserFilter.imagesFileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String imagePath = selectedFile.getPath();

        txtImagemPath.setText(imagePath);
        stringImagePath = imagePath;
    }

    @FXML
    private void btnEscolherDirectoryOnClick() {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        txtAppDirectory.setText(selectedDirectory.toString());

        directoryPath = selectedDirectory.getPath();
    }

    @FXML
    private void btnJarPathOnClick(){
        FileChooser fileChooser = FileChooserFilter.jarFileChooser();
        File initialDirectory = new File(directoryPath + "/out/artifacts");

        System.out.println("Caminho do diretorio: " + initialDirectory.getAbsolutePath());

        fileChooser.setInitialDirectory(initialDirectory);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            mainJar = selectedFile.getParent();
            String absoluteJarPath = selectedFile.getPath();
            Path inputPath = Paths.get(mainJar);
            Path jarPath = Paths.get(absoluteJarPath);
            Path relativeJarPath = inputPath.relativize(jarPath);
            jarName = relativeJarPath.toString();

            txtJar.setText(jarName);

            try (Stream<Path> paths = Files.walk(jarPath)) {
                paths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".jar"))
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Nenhum arquivo selecionado");
        }
    }



    @FXML
    private void btnMainClassOnClick(){
        FileChooser fileChooser = FileChooserFilter.javaFileChooser();
        fileChooser.setInitialDirectory(new File(directoryPath + "/src/main/java"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String javaPath = selectedFile.getPath();
            String baseDir = new File(directoryPath + "/src/main/java").getAbsolutePath();
            String fullClassName = javaPath.substring(baseDir.length() + 1, javaPath.length() - ".java".length());
            fullClassName = fullClassName.replace(File.separatorChar, '.');

            System.out.println(fullClassName);
            pathJavaMainClass = fullClassName;

            txtMainClass.setText(fullClassName);
        } else {
            System.out.println("Nenhum arquivo selecionado");
        }
    }

    @FXML
    private void btnDestinationOnClick(){
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectDirectory = directoryChooser.showDialog(stage);
        txtDestination.setText(selectDirectory.toString());

        destinationPath = selectDirectory.getPath();
    }

    @FXML
    private String appVersionGetText(){
        String appVersion = txtAppVersion.getText();

        return appVersion;
    }

    @FXML
    private String vendorGetText(){
        String vendor = txtVendor.getText();

        return vendor;
    }

    private String comboBoxAction(){

        return packageType.getSelectionModel().getSelectedItem();
    }

    private String checkBoxSelected(){
        if (cbShortcut.isSelected()){
            return "--win-shortcut";
        }
        return null;
    }

    @FXML
    private void btnPackageOnClick() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Mostra a mensagem "Packing..." quando o processo inicia
                Platform.runLater(() -> lblPacking.setText("Packing..."));

                StringBuilder sb = new StringBuilder();

                sb.append("jpackage ");

                sb.append("--input ").append(mainJar).append("\\").append(" ");
                sb.append("--name ").append(setTxtAppName()).append(" ");
                sb.append("--main-jar ").append(jarName).append(" ");
                sb.append("--main-class ").append(pathJavaMainClass).append(" ");
                sb.append("--icon ").append(stringImagePath).append(" ");
                sb.append("--dest ").append(destinationPath).append(" ");
                sb.append("--app-version ").append(appVersionGetText()).append(" ");
                sb.append("--vendor ").append(vendorGetText()).append(" ");
                sb.append("--type ").append(comboBoxAction()).append(" ");
                sb.append(checkBoxSelected());

                ProcessBuilderTool.RunProcess(sb.toString());

                System.out.println(sb);

                Alerta.alerta();

                // Limpa a mensagem da label após o término do processo
                Platform.runLater(() -> lblPacking.setText(""));

                return null;
            }
        };

        new Thread(task).start();
    }


    private void packageType(){
        packageType.getItems().addAll(
                "app-image",
                "exe",
                "msi",
                "rpm",
                "deb",
                "pkg",
                "dmg"
        );
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        packageType();
    }
}