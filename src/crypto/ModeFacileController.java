/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;

public class ModeFacileController implements Initializable {

    @FXML
    Slider encryption_slider, decryption_slider, memory_slider;
    @FXML
    Label encryption_label, decryption_label, memory_label;
    @FXML
    RadioButton oui,non;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        encryption_label.textProperty().bindBidirectional(encryption_slider.valueProperty(), NumberFormat.getIntegerInstance());
        decryption_label.textProperty().bindBidirectional(decryption_slider.valueProperty(), NumberFormat.getIntegerInstance());
        memory_label.textProperty().bindBidirectional(memory_slider.valueProperty(), NumberFormat.getIntegerInstance());
        ToggleGroup tg = new ToggleGroup();
        oui.setToggleGroup(tg);
        non.setToggleGroup(tg);
        tg.selectToggle(oui);

    }
    public boolean getFort(){
        return ((oui.isSelected())?true:false);
    }
    public  ArrayList<Double> getValue(){
         ArrayList<Double> db=new ArrayList<Double>();
         db.add(encryption_slider.getValue()*100);
         db.add(decryption_slider.getValue()*100);
         db.add(memory_slider.getValue()*100);
         return db;   
    }
}
