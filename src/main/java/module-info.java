module com.urassh.dvdrental {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.urassh.dvdrental to javafx.fxml;
    exports com.urassh.dvdrental;
    exports com.urassh.dvdrental.controller;
    opens com.urassh.dvdrental.controller to javafx.fxml;
    exports com.urassh.dvdrental.controller.goods;
    opens com.urassh.dvdrental.controller.goods to javafx.fxml;
    exports com.urassh.dvdrental.controller.home;
    opens com.urassh.dvdrental.controller.home to javafx.fxml;
    exports com.urassh.dvdrental.controller.members;
    opens com.urassh.dvdrental.controller.members to javafx.fxml;
    exports com.urassh.dvdrental.controller.rental;
    opens com.urassh.dvdrental.controller.rental to javafx.fxml;
    exports com.urassh.dvdrental.controller.sidebar;
    opens com.urassh.dvdrental.controller.sidebar to javafx.fxml;
}