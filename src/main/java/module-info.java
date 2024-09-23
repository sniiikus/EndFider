module com.ender.endsearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ender.endsearch to javafx.fxml;
    exports com.ender.endsearch;
}