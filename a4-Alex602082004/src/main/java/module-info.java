module com.example.lab_patru {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires com.google.gson;
    requires java.sql;


    opens com.example.lab4 to javafx.fxml;
    exports com.example.lab4;
}