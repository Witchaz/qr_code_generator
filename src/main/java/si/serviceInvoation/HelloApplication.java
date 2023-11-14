package si.serviceInvoation;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import si.services.FXRouter;


import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResource("/si/image/Logo.png").toExternalForm()));
        FXRouter.bind(this, stage, "QR code generator", 720, 480);
        configRoute();
        FXRouter.goTo("start");
    }

    private void configRoute() {
        String viewPath = "si/views/";
        FXRouter.when("start", viewPath + "start.fxml");
        FXRouter.when("seriesData", viewPath + "seriesWindow.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}