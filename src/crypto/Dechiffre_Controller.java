/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import DES_AES.DES_AES;
import cesar_vigenere.Cesar;
import cesar_vigenere.CodDec_vigenere;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import projet.Database;
import projet.FileString;
import projet.User;

public class Dechiffre_Controller implements Initializable {

    @FXML
    Label emplacment_txt;
    File file = null;
    User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void Dechiffre(ActionEvent e) {
        int b = 0;
        try {
            int id_f = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
            try {
                Database db = new Database("127.0.0.1");

                ResultSet r = db.load_table("select * from auto where ID_U =" + user.getId() + " and ID_F=" + id_f);

                while (r.next()) {
                    b++;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (b == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ALert");
                alert.setHeaderText("Information Alert");

                alert.setContentText("vous n'avez pas l'autorisation d'ouvrir ce fichier");
                alert.show();
            } else {
                Database db = new Database("localhost");
                ResultSet result = db.load_table("SELECT `ID_F`, `ID_U`, `ID_TYPE`, `ID_CRYPTO` FROM `fichier` WHERE ID_F=" + id_f);
                int id_type = -1, id_crypto = -1;
                while (result.next()) {
                    id_type = result.getInt("ID_TYPE");
                    id_crypto = result.getInt("ID_CRYPTO");
                }

                if ((id_type != -1) && (id_crypto != -1)) {
                    result = db.load_table("SELECT `NOM` FROM `type` WHERE ID_TYPE=" + id_type);
                    String nom_type = null;
                    while (result.next()) {
                        nom_type = result.getString("NOM");
                    }
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
                    File f = fileChooser.showSaveDialog(null);
                    if ((nom_type.equals("DES")) || (nom_type.equals("AES")) || (nom_type.equals("Cesar")) || (nom_type.equals("Vigenere"))) {
                        result = db.load_table("SELECT `ID_VADC`, `ID_CRYPTO`, `PASS` FROM `vig_aes_des_cesar` WHERE ID_CRYPTO=" + id_crypto);
                        String mot_de_passe = null;
                        while (result.next()) {
                            mot_de_passe = result.getString("PASS");
                        }
                        if ((nom_type.equals("DES")) || (nom_type.equals("AES"))) {
                            System.out.println("mode = " + nom_type);
                            DES_AES d = new DES_AES(nom_type, mot_de_passe, this.file.getAbsolutePath(), f.getAbsolutePath());
                            d.decrypt();
                        } else {
                            if ((nom_type.equals("Cesar")) || (nom_type.equals("Vigenere"))) {
                                String entree = FileString.FileToString(this.file);
                                String result_cesar_vig = "";
                                if (nom_type.equals("Cesar")) {
                                    Cesar ce = new Cesar();
                                    result_cesar_vig = ce.decrypt(Integer.parseInt(mot_de_passe), entree);

                                } else {
                                    CodDec_vigenere o = new CodDec_vigenere();
                                    result_cesar_vig = o.Decode(entree, mot_de_passe);
                                }
                                FileString.StringToFile(result_cesar_vig, f);
                            }

                        }
                    }
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dechiffre_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dechiffre_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException es) {

        } catch (Throwable ex) {
            Logger.getLogger(Dechiffre_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void LoadFile(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        this.file = fc.showOpenDialog(null);
        this.emplacment_txt.setText(file.getAbsolutePath());
        // 

    }

    public void setUser(User user) {
        this.user = user;
    }

}
