package cesar_vigenere;

public class Cesar {

    static char[] alphabet1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String crypt(int n, String sIn) {
        /* 
         * On convertit le string en un tableau de char
         * On cr�e un tableau Out de m�me taille
         * On prend chaque lettre du string
         * On prend sa position actuelle dans l'alphabet
         * On calcule sa nouvelle position en fonction de n
         * On va chercher dans l'alphabet la lettre correspondant � la nouvelle position
         * On assigne cette lettre dans le tableau de sortie au mm emplacement
         * On transforme le tableau Out en un string   
         */
        sIn = sIn.toUpperCase();
        char[] charSIn = sIn.toCharArray();
        char[] charSOut = new char[charSIn.length];
        int pos1, pos2;
        for (int i = 0; i < charSIn.length; i++) {
            if (charSIn[i] == '\n') {
                charSOut[i] = '\n';
            } else {
                pos1 = posChar(charSIn[i], alphabet1);
                pos2 = newPos(pos1, n);
                if (pos2 == -1) {
                    charSOut[i] = ' '; // si -1, c'est que ce n'est pas une lettre, on met un espace � la place    
                } else {
                    charSOut[i] = alphabet1[pos2];
                }
            }
        }
        return new String(charSOut); // on fait un string avec le tableau de char
    }

    public static String decrypt(int n, String sIn) {
        /*
         * La marche a suivre est la meme que pour le crypt sauf qu'on prend un n' = -n;  
         */
        sIn = sIn.toUpperCase();
        char[] charSIn = sIn.toCharArray();
        char[] charSOut = new char[charSIn.length];
        int pos1, pos2;
        for (int i = 0; i < charSIn.length; i++) {
            pos1 = posChar(charSIn[i], alphabet1);
            pos2 = newPos(pos1, -n);
            if (pos2 == -1) {
                charSOut[i] = ' '; // si -1, c'est que ce n'est pas une lettre, on met un espace � la place    
            } else {
                charSOut[i] = alphabet1[pos2];
            }
        }
        return new String(charSOut); // on fait un string avec le tableau de char  
    }

    // Renvoie la position du caractere dans le tableau  
    // -1 si il n'est pas dans le tableau  
    private static int posChar(char c, char[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == c) {
                return i;
            }
        }
        return -1;
    }

    // Donne la nouvelle position dans l'alphabet en fonction de n  
    private static int newPos(int pos, int n) {
        int pos2 = pos;
        if (pos <= -1) { // -1 signifie que le caractere n'a pas �t� trouv� dans l'alphabet (caractere sp�cial, chiffre, espace, etc.)
            pos2 = -1;
        } else {
            int i = 0;
            while (i < abs(n)) {
                if (n < 0) {
                    if (pos2 - 1 == -1) {
                        pos2 = 26;
                    } else {
                        pos2--;
                    }
                } else {
                    if (pos2 + 1 >= 26) {
                        pos2 = 0;
                    } else {
                        pos2++;
                    }
                }
                i++;
            }
        }
        return pos2;
    }

    // Valeur absolue de a  
    public static int abs(int a) {
        if (a >= 0) {
            return a;
        } else {
            return -a;
        }
    }

    /* public static void main(String[] args)  {
        String t = "alger la blanche"; // a recupur� de l'interface 
        int decalage=1; // a recupur� de l'interface
        System.out.println(crypt(decalage,t));
        System.out.println(decrypt(decalage,crypt(decalage,t)));
    }*/
}
