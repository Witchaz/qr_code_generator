package si.serviceInvoation;

import javafx.application.Application;
import javafx.stage.Stage;
import si.services.FXRouter;


import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "Hello World", 720, 480);
        configRoute();
        FXRouter.goTo("start");
    }

    private void configRoute() {
        String viewPath = "si/views/";
        FXRouter.when("start", viewPath + "start.fxml");

    }

    public static void main(String[] args) {
        launch();
    }
}