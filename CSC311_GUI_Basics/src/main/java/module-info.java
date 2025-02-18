module edu.farmingdale.csc311_gui_basics {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.csc311_gui_basics to javafx.fxml;
    exports edu.farmingdale.csc311_gui_basics;
}