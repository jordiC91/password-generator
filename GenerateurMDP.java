import java.util.Random;

public class GenerateurMDP {

    private GenerateurMDP() {
        
    }
    
    public static char[] getMDP(int taille, boolean presence_chiffre, boolean presence_carac_speciaux) {
        char[] mdp = new char[taille];
        String alphabet_complet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-@#&'(!)$%:;/.?^,";
        String alphabet_avec_lettre_et_chiffre = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String alphabet_avec_lettre_et_carac_speciaux = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-@#&'(~+=!)$%:;°]é/.?^,\"";
        String alphabet_avec_lettre = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            if (presence_chiffre == true && presence_carac_speciaux == false) {
                mdp[i] = alphabet_avec_lettre_et_chiffre.charAt(rand.nextInt(alphabet_avec_lettre_et_chiffre.length()));
            } else if (presence_chiffre == true && presence_carac_speciaux == true) {
                mdp[i] = alphabet_complet.charAt(rand.nextInt(alphabet_complet.length()));
            } else if (presence_chiffre == false && presence_carac_speciaux == true) {
                mdp[i] = alphabet_avec_lettre_et_carac_speciaux.charAt(rand.nextInt(alphabet_avec_lettre_et_carac_speciaux.length()));
            } else if (presence_chiffre == false && presence_carac_speciaux == false) {
                mdp[i] = alphabet_avec_lettre.charAt(rand.nextInt(alphabet_avec_lettre.length()));
            }
        }

        return mdp;
    }
}
