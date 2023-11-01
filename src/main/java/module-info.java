module si.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens si.serviceInvoation to javafx.fxml;
    exports si.serviceInvoation;
    exports si.controllers;
    opens  si.controllers to javafx.fxml;
    exports si.services;
    exports si.models;
    opens si.models to java.base;
}