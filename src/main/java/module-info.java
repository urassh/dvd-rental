module com.urassh.dvdrental {
    requires javafx.controls;
    requires javafx.fxml;
    requires mapdb;
    requires annotations;
    requires com.google.guice;
    requires java.net.http;
    requires com.google.gson;
    requires com.google.common;
    requires io.github.cdimascio.dotenv.java;
    requires lombok;

    exports com.urassh.dvdrental;
    exports com.urassh.dvdrental.controller.goods;
    exports com.urassh.dvdrental.controller.home;
    exports com.urassh.dvdrental.controller.members;
    exports com.urassh.dvdrental.controller.rental;
    exports com.urassh.dvdrental.controller.sidebar;
    exports com.urassh.dvdrental.controller.returns;
    exports com.urassh.dvdrental.controller.returns.detail;
    exports com.urassh.dvdrental.module to com.google.guice;
    exports com.urassh.dvdrental.util to com.google.guice;
    exports com.urassh.dvdrental.repository to com.google.guice;
    exports com.urassh.dvdrental.usecase.goods to com.google.guice;
    exports com.urassh.dvdrental.usecase.members to com.google.guice;
    exports com.urassh.dvdrental.usecase.rental to com.google.guice;
    exports com.urassh.dvdrental.infrastructure.gateway to com.google.guice;
    exports com.urassh.dvdrental.repository.impl to com.google.guice;
    exports com.urassh.dvdrental.infrastructure.records to com.google.gson;

    opens com.urassh.dvdrental.controller.goods to javafx.fxml;
    opens com.urassh.dvdrental.controller.home to javafx.fxml;
    opens com.urassh.dvdrental.controller.members to javafx.fxml;
    opens com.urassh.dvdrental.controller.rental to javafx.fxml;
    opens com.urassh.dvdrental.controller.sidebar to javafx.fxml;
    opens com.urassh.dvdrental.controller.returns to javafx.fxml;
    opens com.urassh.dvdrental.controller.returns.detail to javafx.fxml;
    opens com.urassh.dvdrental.infrastructure.records to com.google.gson;
    exports com.urassh.dvdrental.repository.dummy to com.google.guice;
    exports com.urassh.dvdrental.usecase.returns to com.google.guice;
}