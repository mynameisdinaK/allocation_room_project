package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant les préférences de chambre d'une personne.
 * Les chambres sont classées dans l'ordre croissant de préférence.
 * @author Dina
 */
public class Preferences{
    private List<Integer> chambresPref; //liste des chambres préférées d'une personne

    /**
     * Crée la liste des préférences vides
     */
    public Preferences(){
        this.chambresPref = new ArrayList<>();
    }

    /**
     * Crée les préférences d'une personne à partir de sa liste de chambres.
     * @param chambresPref les numéros de chambres, de la préférée à la moins aimée
     */
    public Preferences(List<Integer> chambresPref){
        this.chambresPref = new ArrayList<>(chambresPref);
    }

    //===================Methodes

    /**
     * Retourne le rang d'une chambre dans le classement de la personne
     * @param chambre la chambre qu'on veut determiner
     * @return rang de la chambre passée en paramètre
     */
    public int rangChambre(int chambre){
        if (!chambresPref.contains(chambre)) {
            throw new IllegalArgumentException("Il semblerait que la chambre " + chambre + " n'est pas dans les préférences...");
        }
        return chambresPref.indexOf(chambre);
    }

    /**
     * Indique que la liste de préférences de chambres de la personne courante est complétée (il n'ya plus de chambres à rajouter)
     * 
     * @param taille le nombre total de chambres pour la session courante
     * @return {@code true} si la liste de préférences des chambres est bien complète.
     */
    public boolean estComplete(int taille){
        return diagnostiqueProbleme(taille)==PreferencesIssue.AUCUN;
    }

    /**
     * Cherche le probleme survenu pour les préférences d'une personne
     * 
     * @param taille le nombre de chambre total de la session en cours
     * @return le type de probleme
     */
    public PreferencesIssue diagnostiqueProbleme(int taille){
        
        PreferencesIssue probleme = PreferencesIssue.AUCUN;

        if (chambresPref.isEmpty()) {
            probleme = PreferencesIssue.MANQUANT;
        }else if (contientChambreInexistant(taille)) {
            probleme = PreferencesIssue.HORS_LIMITE;
        }else if (contientDoublon()) {
            probleme = PreferencesIssue.DUPLIQUE;
        }else if (chambresPref.size() < taille) {
            probleme = PreferencesIssue.INCOMPLET;}
    return probleme;
    }

    /** Verifie si une chambre n'existe pas dans la liste*/
    private boolean contientChambreInexistant(int taille) {
        boolean inexistant = false;
        for (int chambre : chambresPref) {
            if (chambre < 1 || chambre > taille) 
                inexistant = true;

        }
        return inexistant;
    }
    
    /** Vrai si une chambre apparaît deux fois dans la liste. */
    private boolean contientDoublon() {
        boolean doublon = false;
        int i;
        int j;
        int n = chambresPref.size();

        for (i = 0; i <n; i++) {
            for (j = i + 1; j <n; j++) {
                if (chambresPref.get(i).equals(chambresPref.get(j))) {
                    doublon = true;
                }
            }
        }
        return doublon;
    }

    //============Redefinition
    @Override
    public String toString() {
        StringBuilder texte = new StringBuilder();
        if (chambresPref.isEmpty()) {
            texte.append("aucune chambre rentrée");
    } else {
        for (int i = 0; i < chambresPref.size(); i++) {
            if (i > 0) {
                texte.append(" > ");
            }
            texte.append(chambresPref.get(i));
        }
    }
    return texte.toString();
}

    //===============Getters et Setters
    public List<Integer> getChambresPref(){
        return chambresPref;
    }
}