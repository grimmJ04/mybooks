module hu.szte.inf {
    requires jakarta.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.data.commons;
    requires org.hibernate.orm.core;

    exports hu.szte.inf;
    exports hu.szte.inf.controllers;
    exports hu.szte.inf.models;

    opens hu.szte.inf to javafx.fxml;
    opens hu.szte.inf.controllers to javafx.fxml;
    opens hu.szte.inf.models to org.hibernate.orm.core;
}
