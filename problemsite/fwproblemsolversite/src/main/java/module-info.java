module com.fwproblemsolversite {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.fwproblemsolversite to javafx.fxml;
    exports com.fwproblemsolversite;

    opens com.model to javafx.fxml;
    exports com.model;
}
