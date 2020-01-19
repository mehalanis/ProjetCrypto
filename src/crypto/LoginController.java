package crypto;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.Database;
import projet.User;

public class LoginController implements Initializable {

    @FXML
    PasswordField password;
    @FXML
    TextField email;
    @FXML
    Label message;
    Stage stage;

    @FXML
    public void connecter(ActionEvent e) {
        try {
            System.out.println("email : " + email.getText() + " password : " + password.getText());

            Database database = new Database("127.0.0.1");

            ResultSet result = database.load_table("select * from user where email='"+email.getText()+"' and password='"+password.getText()+"'");
            int b=0;
            int id_user = 0;
            while(result.next()){
                b++; id_user=result.getInt("ID_U");
            }

            if (b==1) {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("Index.fxml"));
                load.load();
                IndexController index = load.getController();
                index.setUser(new User(id_user,email.getText(), password.getText()));
                index.setStage(stage);
                Parent root = load.getRoot();

                Scene scene = new Scene(root);
                stage.close();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                
            } else {
                message.setText("Le mot de passe entré est incorrect");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       email.setText("test");
       password.setText("test");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
