module espol.minimax {
    requires javafx.controls;
    requires javafx.fxml;


    opens espol.startGame to javafx.fxml;
    exports espol.startGame;
    exports espol.controller;
    opens espol.controller to javafx.fxml;
}