package crypto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projet.User;

public class MenuController implements Initializable {

    private StackPane CenterIntreface;
    private User user;
    private Stage stage;
    @FXML
    Button expert, facile, dechiffre;

    @FXML
    public void load(ActionEvent e) {
        Button r = (Button) e.getSource();
        String s = null;
        if ((r == expert) || (r == facile)) {
            s = "ModeExpert";
        } else {
            s = "Dechiffre"; //dechiffre
        }

        try {
            CenterIntreface.getChildren().clear();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource(s + ".fxml"));
            load.load();
            if (s.equals("ModeExpert")) {
                ModeExpertController mode = load.getController();
                mode.setUser(new User(user.getId(), user.getEmail(), user.getPassword()));
                mode.setStage(stage);
                if (r == facile) {
                    mode.hbox_radio.setVisible(false);
                    mode.setMode("ModeFacile");
                } else {
                    if (r == expert) {
                        mode.setMode("Cesar");
                    }
                }
            } else {
                Dechiffre_Controller mode = load.getController();
                mode.setUser(new User(user.getId(), user.getEmail(), user.getPassword()));

            }
            Parent root = load.getRoot();

            CenterIntreface.getChildren().add(root);
        } catch (IOException ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCenterIntreface(StackPane CenterIntreface) {
        this.CenterIntreface = CenterIntreface;
    }

    public void setUser(User user) {
        this.user = user;
        System.out.println("user " + user.getEmail());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
