package jfxpackager.app;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("MainController.fxml")
public class MainController {
    @FXML
    private Label label_teste;
    @FXML
    private MFXButton button_setLabelText;

    @FXML
    private Button button_clearLabelText;

    @FXML
    private void initialize() {
        button_setLabelText.setOnAction(view -> label_teste.setText("Está funcionando essa bagaça!"));

        button_clearLabelText.setOnAction(view -> label_teste.setText(""));
    }
}