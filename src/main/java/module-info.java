module hu.szte.inf {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    exports hu.szte.inf;
    exports hu.szte.inf.controllers;

    opens hu.szte.inf to javafx.fxml;
    opens hu.szte.inf.controllers to javafx.fxml;
}
