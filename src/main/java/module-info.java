module com.example.wordleclonerebuild {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires annotations;

    opens com.example.wordleclonerebuild to javafx.fxml;
    exports com.example.wordleclonerebuild;
}