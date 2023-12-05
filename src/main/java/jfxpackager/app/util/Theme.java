package jfxpackager.app.util;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.setUserAgentStylesheet;

public class Theme{

    public void DarkTheme(Stage stage) throws Exception {
        setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
    }

    public void LightTheme(Stage stage) throws Exception {
        setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
    }
}
