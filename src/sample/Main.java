package sample;

import com.sun.prism.PhongMaterial;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Stage window = primaryStage;
        window.setTitle("Square 3D");

        Box box = createCube(150);

        Slider sliderX = createSlider(0, 0, 360);
        Slider sliderY = createSlider(0, 0, 360);
        Slider sliderZ = createSlider(0, 0, 360);

        sliderX.valueProperty().addListener((number, oldVal, newVal) -> {
            double oldDouble = Double.parseDouble(oldVal.toString());
            double newDouble = Double.parseDouble(newVal.toString());
            rotate(box, "x", newDouble - oldDouble);
        });
        sliderY.valueProperty().addListener((number, oldVal, newVal) -> {
            double oldDouble = Double.parseDouble(oldVal.toString());
            double newDouble = Double.parseDouble(newVal.toString());
            rotate(box, "y", newDouble - oldDouble);
        });
        sliderZ.valueProperty().addListener((number, oldVal, newVal) -> {
            double oldDouble = Double.parseDouble(oldVal.toString());
            double newDouble = Double.parseDouble(newVal.toString());
            rotate(box, "z", newDouble - oldDouble);
        });

        Label labelX = new Label("X Axis");
        Label labelY = new Label("Y Axis");
        Label labelZ = new Label("Z Axis");

        HBox sliderXLayout = new HBox(10);
        HBox sliderYLayout = new HBox(10);
        HBox sliderZLayout = new HBox(10);

        sliderXLayout.setPadding(new Insets(40, 0, 0, 0));
        sliderXLayout.getChildren().addAll(labelX, sliderX);
        sliderYLayout.getChildren().addAll(labelY, sliderY);
        sliderZLayout.getChildren().addAll(labelZ, sliderZ);

        StackPane boxLayout = new StackPane();
        boxLayout.setPadding(new Insets(40));
        boxLayout.getChildren().add(box);

        Button button = new Button("Reset");
        button.setOnAction(e -> {
            sliderX.setValue(0);
            sliderY.setValue(0);
            sliderZ.setValue(0);
            box.getTransforms().clear();
        });

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(boxLayout, sliderXLayout, sliderYLayout, sliderZLayout, button);

        //Creating a scene object
        Scene scene = new Scene(layout, 400, 440);

        //Adding scene to the stage
        window.setScene(scene);
        window.show();
    }

    private Slider createSlider(int value, int min, int max) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(value);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setMinWidth(300);
        slider.setMaxWidth(300);
        return slider;
    }

    private Box createCube(int size) {
        Box box = new Box();

        //Setting the properties of the Box
        box.setWidth(size);
        box.setHeight(size);
        box.setDepth(size);

        return box;
    }

    private Box rotate(Box box, String axis, double angle) {
        Rotate rotation = new Rotate(0);

        switch(axis) {
            case "X":
            case "x":
                rotation = new Rotate(angle, Rotate.X_AXIS);
                break;
            case "Y":
            case "y":
                rotation = new Rotate(angle, Rotate.Y_AXIS);
                break;
            case "Z":
            case "z":
                rotation = new Rotate(angle, Rotate.Z_AXIS);
                break;
        }

        box.getTransforms().add(rotation);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}