/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Cesar_Controller implements Initializable {

       @FXML
    TextField mot_de_passe;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public int getMot_de_passe() {
        return Integer.parseInt(mot_de_passe.getText());
    }
    
}
