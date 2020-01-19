package projet;

import javafx.scene.control.CheckBox;

public class User {
    public int id;
    public String email;
    public  String Password;
    public  CheckBox etat;

    public User(int id,String email) {
        this.id=id;
        this.email = email;
        
         etat=new CheckBox();
    }
    
    public User(int id,String email, String Password) {
        this(id,email);
        this.Password = Password;
       
    }

    public int getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public CheckBox getEtat() {
        return etat;
    }

}
