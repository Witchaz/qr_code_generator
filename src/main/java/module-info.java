module com.example.qr_code_generator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.qr_code_generator to javafx.fxml;
    exports com.example.qr_code_generator;
}