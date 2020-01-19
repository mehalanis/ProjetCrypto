/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class RSA_Controller implements Initializable {

    @FXML
    ComboBox p, q, e;
    ArrayList<Integer> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = eratosthenes(1000);
        p.getItems().addAll(list);
        q.getItems().addAll(list);
        p.getSelectionModel().selectFirst();
        q.getSelectionModel().select(1);
    }

    public int PGCD(int a, int b) {
        return (((a % b) == 0) ? b : PGCD(b, a % b));
    }

    public ArrayList<Integer> list_e() {
        ArrayList<Integer> list = new ArrayList<Integer>();
    
        int z =  ((Integer)p.getValue() - 1) * ((Integer)q.getValue() - 1);
        for (int i = 2; i < z; i++) {
            if (PGCD(i, z) == 1) {
                list.add(i);
            }
        }
        return list;
    }
    
    @FXML
    public void initE(ActionEvent ee){
        this.e.getItems().addAll(list_e());
    }

    public ArrayList<Integer> eratosthenes(int n) {
        boolean a[] = new boolean[n + 1];
        ArrayList<Integer> list = new ArrayList<Integer>();
        a[0] = true;
        a[1] = true;
        int cpt = 0;
        //int sqn = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            if (!a[i]) {
                cpt++;
                int j = i * i;
                while (j <= n) {
                    a[j] = true;
                    j += i;
                }

            }
        }
        for (int i = 100; i < n; i++) {
            if (!a[i]) {
                list.add(i);
            }
        }

        return list;
    }

}
