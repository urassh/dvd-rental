module com.urassh.dvdrental {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.urassh.dvdrental;
    exports com.urassh.dvdrental.controller.goods;
    exports com.urassh.dvdrental.controller.home;
    exports com.urassh.dvdrental.controller.members;
    exports com.urassh.dvdrental.controller.rental;
    exports com.urassh.dvdrental.controller.sidebar;

    opens com.urassh.dvdrental.controller.goods to javafx.fxml;
    opens com.urassh.dvdrental.controller.home to javafx.fxml;
    opens com.urassh.dvdrental.controller.members to javafx.fxml;
    opens com.urassh.dvdrental.controller.rental to javafx.fxml;
    opens com.urassh.dvdrental.controller.sidebar to javafx.fxml;
}