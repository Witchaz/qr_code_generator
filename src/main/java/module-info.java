module si {
    requires javafx.controls;
    requires javafx.fxml;
	requires com.google.zxing;
	requires java.desktop;
	requires org.apache.commons.io;
	
	
	opens si.serviceInvoation to javafx.fxml;
    exports si.serviceInvoation;
    exports si.controllers;
    opens  si.controllers to javafx.fxml;
    exports si.services;
    exports si.models;
    opens si.models to java.base;
}

