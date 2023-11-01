module si.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens si.serviceInvoation to javafx.fxml;
    exports si.serviceInvoation;
}