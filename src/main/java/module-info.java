module com.example.gui2022 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gui2022 to javafx.fxml;
    exports com.example.gui2022;
}