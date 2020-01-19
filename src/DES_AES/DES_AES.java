/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES_AES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DES_AES {

    FileInputStream fis;
    FileOutputStream fos;
    String key = "SofianeArtsitbzzzf";
    ArrayList<String> text;
    String f;
    String Mode;

    public DES_AES(String mode, String key, String entree, String Sortie) throws FileNotFoundException, Throwable {
        fis = new FileInputStream(entree);
        fos = new FileOutputStream(Sortie);
        this.key = key;
        this.Mode = mode;

    }

    public void encrypt() throws Throwable {
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, this.fis, this.fos);
    }

    public void decrypt() throws Throwable {
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE, this.fis, this.fos);
    }

    public void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), this.Mode);
        Cipher cipher = Cipher.getInstance(this.Mode);

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, sks);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    public void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

}
