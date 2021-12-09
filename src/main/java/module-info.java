module espol.minimax {
    requires javafx.controls;
    requires javafx.fxml;


    opens espol.minimax to javafx.fxml;
    exports espol.minimax;
    exports espol.controller;
    opens espol.controller to javafx.fxml;
}