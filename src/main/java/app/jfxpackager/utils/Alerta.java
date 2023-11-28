package app.jfxpackager.utils;

import app.jfxpackager.StartApplication;
import javafx.scene.control.Alert;

import javax.swing.*;

public class Alerta{
    public static void alerta() {
        JOptionPane.showMessageDialog(null,
                "Your file is ready to use.",
                "Finished packaging",
                JOptionPane.WARNING_MESSAGE);
    }
}
