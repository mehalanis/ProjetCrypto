package crypto;

import DES_AES.DES_AES;
import cesar_vigenere.Cesar;
import cesar_vigenere.CodDec_vigenere;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import knn.KNN;
import projet.Database;
import projet.FileString;
import projet.User;

public class ModeExpertController implements Initializable {

    User user;
    @FXML
    GridPane gridpane;
    @FXML
    RadioButton vigenere, hill, rsa, des, aes, cesar;
    @FXML
    TableView table;
    @FXML
    HBox hbox_radio;
    @FXML
    Label emplacment_txt;
    Parent root;
    ObservableList<User> data;
    String Mode;
    ModeFacileController modefacile;
    Vigenere_Controller Vigenere;
    Cesar_Controller Cesar;
    DES_Controller DES_AES;
    File file = null;
    Stage stage;
    ArrayList<User> list_user;

    @FXML
    public void Load(ActionEvent e) {
        RadioButton radio = null;
        if (e != null) {
            radio = (RadioButton) e.getSource();
        }

        if (radio == rsa) {
            Mode = "RSA";
        } else if (radio == hill) {
            Mode = "HILL";
        } else if (radio == vigenere) {
            Mode = "Vigenere";
        } else if (radio == cesar) {
            Mode = "Cesar";
        } else if (radio == des) {
            Mode = "DES";
        } else if (radio == aes) {
            Mode = "DES";
        }
        FXMLLoad();

    }

    public void FXMLLoad() {
        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource(this.Mode + ".fxml"));
            load.load();
            switch (this.Mode) {
                case "ModeFacile": {
                    modefacile = load.getController();
                    break;
                }
                case "Vigenere": {
                    Vigenere = load.getController();
                    break;
                }
                case "Cesar": {
                    Cesar = load.getController();
                    break;
                }
                case "DES": {
                    DES_AES = load.getController();
                    break;
                }
                case "AES": {
                    DES_AES = load.getController();
                    break;
                }
            }
            gridpane.getChildren().remove(root);
            root = load.getRoot();

            gridpane.add(root, 0, 1);
        } catch (IOException ex) {
        }
    }

    @FXML
    public void crypter(ActionEvent e) {
        try {
            if (file == null) {
                LoadFile(null);
            }
            boolean ModeFacile = false;
            String password = "";
            if (this.Mode.equals("ModeFacile")) {
                KNN knn = new KNN(modefacile.getValue(), 1500, modefacile.getFort());
                this.Mode = knn.Choice();
                System.out.println("it s: " + this.Mode);
                ModeFacile = true;
                if (this.Mode.equals("Cesar")) {
                    password = "2";
                } else {
                    int i = 0;
                    if (this.Mode.equals("AES")) {
                        i = 16;
                    } else {
                        i = 8;
                    }
                    password = geek_Password(i);

                }

            }
            Database db = new Database("localhost");
            int id_type = 0;
            if ((this.Mode.equals("DES")) && (aes.isSelected())) {
                this.Mode = "AES";
            }

            ResultSet r = db.load_table("SELECT * FROM `type` WHERE NOM='" + this.Mode + "'");
            while (r.next()) {
                id_type = r.getInt("ID_TYPE");
            }

            int id_crypto = db.Insert("insert into crypto() values ()");

            int id_f = db.Insert("INSERT INTO `fichier`(`ID_U`, `ID_TYPE`, `ID_CRYPTO`) VALUES (" + user.getId() + "," + id_type + "," + id_crypto + ")");
            db.Insert("insert into auto(ID_U,ID_F) values (" + user.getId() + " ," + id_f + ")");
            for (User i : list_user) {
                if (i.getEtat().isSelected()) {
                    db.Insert("insert into auto(ID_U,ID_F) values (" + i.getId() + " ," + id_f + ")");
                }

            }
            String result = "", entree = FileString.FileToString(this.file);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(id_f + ".txt");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
            File f = fileChooser.showSaveDialog(stage);
            if (f != null) {
                switch (this.Mode) {
                    case "Vigenere": {
                        if (ModeFacile == false) {
                            password = Vigenere.getMot_de_passe();
                        }
                        int v = db.Insert("INSERT INTO `vig_aes_des_cesar`(`ID_CRYPTO`, `PASS`) VALUES (" + id_crypto + ",'" + password + "')");
                        CodDec_vigenere o = new CodDec_vigenere();

                        result = o.Code(entree, password);
                        break;
                    }
                    case "Cesar": {
                        if (ModeFacile == false) {
                            password = String.valueOf(Cesar.getMot_de_passe());
                        }
                        int v = db.Insert("INSERT INTO `vig_aes_des_cesar`(`ID_CRYPTO`, `PASS`) VALUES (" + id_crypto + ",'" + password + "')");

                        Cesar c = new Cesar();
                        result = c.crypt(Integer.parseInt(password), entree);
                        break;
                    }
                    case "DES": {
                        if (ModeFacile == false) {
                            password = DES_AES.getMot_de_passe();
                        }
                        int v = db.Insert("INSERT INTO `vig_aes_des_cesar`(`ID_CRYPTO`, `PASS`) VALUES (" + id_crypto + ",'" + password + "')");

                        try {
                            DES_AES d = new DES_AES("DES", password, this.file.getAbsolutePath(), f.getAbsolutePath());
                            d.encrypt();
                        } catch (Throwable ex) {
                            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    }
                    case "AES": {
                        if (ModeFacile == false) {
                            password = DES_AES.getMot_de_passe();
                        }

                        int v = db.Insert("INSERT INTO `vig_aes_des_cesar`(`ID_CRYPTO`, `PASS`) VALUES (" + id_crypto + ",'" + password + "')");
                        try {
                            DES_AES d = new DES_AES("AES", password, this.file.getAbsolutePath(), f.getAbsolutePath());
                            d.encrypt();
                        } catch (Throwable ex) {
                            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);

                        }
                        break;
                    }
                }

                if ((f != null) && (!this.Mode.equals("DES")) && (!this.Mode.equals("AES"))) {
                    FileString.StringToFile(result, f);
                }
            }
            if (ModeFacile == true) {
                this.Mode = "ModeFacile";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void LoadFile(ActionEvent e) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier txt", "*.txt"));
        this.file = fc.showOpenDialog(null);
        this.emplacment_txt.setText(file.getAbsolutePath());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        vigenere.setToggleGroup(tg);
        hill.setToggleGroup(tg);
        rsa.setToggleGroup(tg);
        des.setToggleGroup(tg);
        aes.setToggleGroup(tg);
        cesar.setToggleGroup(tg);
        tg.selectToggle(cesar);
        this.emplacment_txt.setText("");

    }

    public void setUser(User user) {
        this.user = user;
        list_user = new ArrayList<User>();

        try {
            Database db = new Database("127.0.0.1");

            ResultSet r = db.load_table("SELECT * FROM `user` WHERE  ID_U!=" + user.getId());
            while (r.next()) {

                list_user.add(new User(r.getInt("ID_U"), r.getString("EMAIL")));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModeExpertController.class.getName()).log(Level.SEVERE, null, ex);
        }

        data = FXCollections.observableArrayList(list_user);

        TableColumn email = new TableColumn("Email");
        TableColumn etat = new TableColumn("Etat");
        etat.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        email.prefWidthProperty().bind(table.widthProperty().multiply(0.87));
        table.getColumns().addAll(etat, email);

        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        etat.setCellValueFactory(new PropertyValueFactory<User, String>("etat"));
        table.setItems(data);

    }

    public void setMode(String Mode) {
        this.Mode = Mode;
        FXMLLoad();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String geek_Password(int len) {

        // A strong password has Cap_chars, Lower_chars, 
        // numeric value and symbols. So we are using all of 
        // them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        // String symbols = "!@#$%^&*_=+-/.?<>)";

        String values = Capital_chars + Small_chars
                + numbers;//+ symbols;

        // Using random method 
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            password[i]
                    = values.charAt(rndm_method.nextInt(values.length()));

        }
        return String.valueOf(password);
    }

}
