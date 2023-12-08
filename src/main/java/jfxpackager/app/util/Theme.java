package jfxpackager.app.util;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;

import static javafx.application.Application.setUserAgentStylesheet;

public class Theme {

    public void DarkTheme() {
        setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
    }

    public void LightTheme() {
        setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
    }
}
