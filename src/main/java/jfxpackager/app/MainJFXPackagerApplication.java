package jfxpackager.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import jfxpackager.app.util.Theme;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class MainJFXPackagerApplication extends Application {
    private ConfigurableApplicationContext context;
    private final Theme theme;

    public MainJFXPackagerApplication() {
        this.theme = new Theme();
    }

    private ConfigurableApplicationContext initContext() {
        return new SpringApplicationBuilder()
                .sources(JfxpackageApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void init() {
        context = initContext();
    }

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
        applyTheme(stage);
    }

    private void applyTheme(Stage stage) {
        try {
            theme.LightTheme();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        this.context.stop();
        Platform.exit();
    }
}

class StageReadyEvent extends ApplicationEvent {
    public Stage getStage() {
        return (Stage) getSource();
    }

    public StageReadyEvent(Object source) {
        super(source);
    }
}
