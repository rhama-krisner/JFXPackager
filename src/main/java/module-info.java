module app.jfxpackager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens app.jfxpackager to javafx.fxml;
    exports app.jfxpackager;
}