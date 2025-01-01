module com.urassh.dvdrental {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.urassh.dvdrental to javafx.fxml;
    exports com.urassh.dvdrental;
    exports com.urassh.dvdrental.controller;
    opens com.urassh.dvdrental.controller to javafx.fxml;
}