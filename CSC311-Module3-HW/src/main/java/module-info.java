module edu.farmingdale.csc311module3hw {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.csc311module3hw to javafx.fxml;
    exports edu.farmingdale.csc311module3hw;
}