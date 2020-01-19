/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

import java.util.ArrayList;
import javafx.util.Pair;
import knn.Point;


/**
 *
 * @author Abdou
 */
public class KNN {
   // private ArrayList<Double> userCar;
    private Point User;
    private Point Rsa,Des,Aes ,Vig,Hill,Cez; 
    private boolean Fort=false;

    public boolean isFort() {
        return Fort;
    }

    public void setFort(boolean Fort) {
        this.Fort = Fort;
    }
    
public KNN(ArrayList<Double> crdnUser , double FileSize, boolean fort)
    { 
        this.User=new Point(crdnUser.get(0), crdnUser.get(1), crdnUser.get(2), "User");
       
        if(fort)   // si fort est cocher en vas  utiliser aes ,des et rsa  sinn vignère , hill,cézar
        {
           this.setFort(true);
            if( FileSize>1024)
            {
                Rsa=new Point(1500 , 1100, 31.5, "RSA");
                Des=new Point(1000, 800, 18.2, "DES");
                Aes=new Point(600, 600, 14.7, "AES");
            }
            else if (FileSize==1024) 
            {
                Rsa=new Point(1400 , 800, 31.5, "RSA");
                Des=new Point(750, 620, 18.2, "DES");
                Aes=new Point(600, 600, 14.7, "AES");
            }
            else if (FileSize<= 25)

            {
                Rsa=new Point(520 , 250, 31.5, "RSA");
                Des=new Point(490, 400, 18.2, "DES");
                Aes=new Point(520, 300, 14.7, "AES");
            }
            else
            {
                Rsa=new Point(800 , 500, 31.5, "RSA");
                Des=new Point(500, 400, 18.2, "DES");
                Aes=new Point(600, 600, 14.7, "AES");

            }
        }
        else
        {
            this.setFort(false);
            Cez=new Point(200, 200, 0.1, "Cesar");
            Hill=new Point(800, 1000, 1, "Hill");
           Vig=new Point(400, 400, 0.4, "Vignere");
            
        }
        
        
    
    }
    
public String Choice()

    {
     System.out.println("x= "+User.getEncryption_time()+" y= "+User.getDecryption_time()+" Z= "+User.getUsed_memory());
    double minDist;
    if(this.Fort) // aes des rsa
        {
             
             minDist=User.distance(Aes); // 
             double min2=Math.min(User.distance(Rsa), User.distance(Des));
             
             if(minDist<=min2)
                {
                    return "AES";
                    
                }
             else
             {
                 if(min2==User.distance(Rsa))
                 {
                     return "RSA";
                 }
                 else
                 {
                    return "DES";
                 }
             }
             
           
            
        }
    else // vig hill cesar
        {
            
             minDist=User.distance(Cez); // 
             double min2=Math.min(User.distance(Hill), User.distance(Vig));
             
             if(minDist<=min2)
                {
                    return "Cez";
                    
                }
             else
             {
                 if(min2==User.distance(Vig))
                 {
                     return "Vignere";
                 }
                 else
                 {
                    return "Hill";
                 }
             }
             
        }
    
       
    }
   /* public static void main(String[] args) {
        ArrayList<Double> db=new ArrayList<Double>();
        double x=200;
        double y=800;
        double z=15;
       // Aes=new Point(600, 600, 14.7, "AES");
        db.add(x);
        db.add(y);
        db.add(z);
        KNN knn=new KNN(db, 1500, true);
        String s=knn.Choice();
        System.out.println("it s: "+s);
    }*/
    
}
