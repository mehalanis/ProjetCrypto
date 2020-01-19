/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

/**
 *
 * @author Abdou
 */
public class Point {
    private double encryption_time; // x encryption time
     private double decryption_time;//y decryption time

    public double getEncryption_time() {
        return encryption_time;
    }

    public void setEncryption_time(double encryption_time) {
        this.encryption_time = encryption_time;
    }

    public double getDecryption_time() {
        return decryption_time;
    }

    public void setDecryption_time(double decryption_time) {
        this.decryption_time = decryption_time;
    }

    public double getUsed_memory() {
        return used_memory;
    }

    public void setUsed_memory(double used_memory) {
        this.used_memory = used_memory;
    }

    public String getNomAlgo() {
        return nomAlgo;
    }

    public void setNomAlgo(String nomAlgo) {
        this.nomAlgo = nomAlgo;
    }
     
      private double used_memory; // z used memory 
    private String nomAlgo ;// l' algo de crypto 

    
    
    public Point(double x,double y,double z,String nom)
    { 
    this.encryption_time=x;
    this.decryption_time=y;
    this.used_memory=z;
    this.nomAlgo=nom;
    }
    public double distance(Point p)
    { 
    double rslt;
    rslt=Math.sqrt(Math.pow((p.getEncryption_time()-this.getEncryption_time()), 2)+Math.pow((p.getDecryption_time()-this.getDecryption_time()), 2)+Math.pow((p.getUsed_memory()-this.getUsed_memory()), 2));
    return rslt;
        
    
    }
    
}
