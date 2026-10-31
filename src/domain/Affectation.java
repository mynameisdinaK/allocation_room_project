package domain;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Représente l'affectation d'un chambre a une personne du groupe
 * @author Dina
 */
public class Affectation {
    private final Map<Personne, Integer> affectation; //prends la forme d'un dictionnaire exp: (B:3)


    public Affectation() {
        this.affectation = new LinkedHashMap<>();
    }

    /**
     * Attribue une chambre à une personne.
     *
     * @param personne la personne
     * @param chambre la chambre
     */
    public void attribuerChambre(Personne personne, int chambre) {
        affectation.put(personne, chambre);
    }

    /**
     * Indique si une chambre est déjà attribuée.
     *
     * @param chambre le numéro de la chambre
     * @return true si une personne a déjà cette chambre
     */
    public boolean chambreEstPrise(int chambre) {
        return affectation.containsValue(chambre);
    }

    /**
     * Retourne la chambre d'une personne.
     *
     * @param personne la personne
     * @return le numéro de sa chambre
     * @throws IllegalArgumentException si la personne n'a pas de chambre
     */
    public int chambreDe(Personne personne) {
        if (!affectation.containsKey(personne)) {
            throw new IllegalArgumentException("La personne " + personne.getNom() + " n'a pas de chambre.");
        }
        return affectation.get(personne);
    }

    /**
     * Échange les chambres de deux personnes.
     *
     * @param p1 la première personne
     * @param p2 la seconde personne
     * @throws IllegalArgumentException si les deux personnes sont les mêmes
     */
    public void echangerChambres(Personne p1, Personne p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("On ne peut pas échanger une personne avec elle-même.");
        }
        int premiereChambre = chambreDe(p1);
        int deuxiemeChambre = chambreDe(p2);
        affectation.put(p1, deuxiemeChambre);
        affectation.put(p2, premiereChambre);
    }

    @Override
    public String toString() {
        StringBuilder texte = new StringBuilder();
        for (Personne personne : affectation.keySet()) {
            int chambre = affectation.get(personne);
            texte.append(personne.getNom()+":"+chambre+"\n");
        }
        return texte.toString();
    }
}
