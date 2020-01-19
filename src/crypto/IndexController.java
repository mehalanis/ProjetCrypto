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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projet.User;

public class IndexController implements Initializable {

    @FXML
    StackPane CenterIntreface;

    User user;

    @FXML
    Label email_user;
    Parent GridMenu;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void home(ActionEvent e) {
        CenterIntreface.getChildren().clear();
        CenterIntreface.getChildren().add(GridMenu);
    }

    @FXML
    public void exit(ActionEvent e) {
        try {
            stage.close();
            Crypto c = new Crypto();
            c.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser(User user) {
        this.user = user;
        email_user.setText("Bonjour , " + user.getEmail());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("Menu.fxml"));
            load.load();
            MenuController menu = load.getController();
            menu.setCenterIntreface(CenterIntreface);
            menu.setUser(new User(user.getId(), user.getEmail(), user.getPassword()));
            menu.setStage(stage);
            GridMenu = load.getRoot();

            CenterIntreface.getChildren().add(GridMenu);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
