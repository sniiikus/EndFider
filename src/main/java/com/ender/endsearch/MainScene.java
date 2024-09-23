package com.ender.endsearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainScene {
    @FXML
    private Label welcomeText;


    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;
    @FXML
    private TextField text5;
    @FXML
    private TextField text6;
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }


    public ArrayList<String> calc_coor(Double x1,Double z1, Double a11, Double x2, Double z2, Double a22){
        ArrayList<String> ans =new ArrayList<String>();
        Double a1=Math.toRadians(a11);
        Double a2=Math.toRadians(a22);

        if (a1.equals(a2)){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You have entered the same angles. It is impossible to find a fortress. Please try again.");
            alert.showAndWait();
            ans.add("Error");
            ans.add("Error");
            return ans;
        }
        else {
            if (x1.equals(x2) && z1.equals(z2)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You have entered the same coordinates. It is impossible to find a fortress. Please try again.");
                alert.showAndWait();
                ans.add("Error");
                ans.add("Error");
                return ans;

            } else {

                if (Math.abs(a11 - a22) == 180) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Your throws are parallel. It is impossible to find a fortress. Please try again.");
                    alert.showAndWait();
                    ans.add("Error");
                    ans.add("Error");
                    return ans;
                } else {

                    double C1 = z1 * Math.sin(a1) + x1 * Math.cos(a1);
                    double C2 = z2 * Math.sin(a2) + x2 * Math.cos(a2);
                    double C3 = C1 * Math.sin(a2) - C2 * Math.sin(a1);

                    double xx = C3 / Math.sin(a2 - a1);
                    double z;
                    if (a1 == 0) {
                        z = (C2 - xx * Math.cos(a2)) / Math.sin(a2);
                    } else {
                        z = (C1 - xx * Math.cos(a1)) / Math.sin(a1);
                    }

                    ans.add(String.format(Locale.US, "%.2f", xx));
                    ans.add(String.format(Locale.US, "%.2f", z));

                    return ans;
                }
            }
        }



    }





    public void calcCoor(ActionEvent event) throws IOException {
        if ((text1.getText().length()!=0) && (text2.getText().length()!=0) && (text3.getText().length()!=0) && (text4.getText().length()!=0) && (text5.getText().length()!=0) && (text6.getText().length()!=0)){
            if (!isNumeric(text1.getText()) || !isNumeric(text2.getText()) || !isNumeric(text3.getText()) || !isNumeric(text4.getText()) || !isNumeric(text5.getText()) || !isNumeric(text6.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You have entered the wrong type of data. It can be only in a numeric format. Please try again.");
                alert.showAndWait();
            }
            else {
                double x1=Double.valueOf(text1.getText());
                double z1=Double.valueOf(text2.getText());
                double a1=Double.valueOf(text3.getText());
                double x2=Double.valueOf(text4.getText());
                double z2=Double.valueOf(text5.getText());
                double a2=Double.valueOf(text6.getText());

                ArrayList<String> coordinates=calc_coor(x1,z1,a1,x2,z2,a2);

                if (!coordinates.get(0).equals("Error") && !coordinates.get(1).equals("Error")) {
                    field1.setText((coordinates.get(0)));
                    field2.setText((coordinates.get(1)));
                }
                else{
                    coordinates.clear();
                }

            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You have not filled all the necessary fields. Please try again.");
            alert.showAndWait();
        }
    }



}