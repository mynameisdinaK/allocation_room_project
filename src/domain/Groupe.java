package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**Classe représentant le groupe courant
 * @author Dina
 */
public class Groupe {
    private int taille; //taille du groupe courant
    private List<Personne> personnes; //liste des personnes dans le groupe courant
    private static final int MAX_TAILLE = 26; //pour cette première partie, il ne peut avoir que 26 personnes dans un groupe

    /**
     * Crée un groupe avec des personnes
     *
     * @param taille le nombre de personnes, entre 1 et {@link #MAX_TAILLE}
     * @throws IllegalArgumentException si la taille>{@link #MAX_TAILLE}
     */
    public Groupe(int taille) {
        if (taille < 1 || taille > MAX_TAILLE) {
            throw new IllegalArgumentException("Taille invalide : " + taille);
        }
        this.taille = taille;
        this.personnes = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            char lettre = (char) ('A' + i);
            personnes.add(new Personne(String.valueOf(lettre)));
        }
    }

    //Getters et Settres================
    public int getTaille() {
        return taille;
    }

    /**
     * Retourne les personnes du groupe.
     *
     * @return la liste des personnes, de A à la dernière lettre
     */
    public List<Personne> getPersonnes() {
        return personnes;
    }

    /**
     * deux personnes ne s'aiment pas
     *
     * @param p1 la première personne de la relation
     * @param p2 la seconde personne de la relation
     * @throws IllegalArgumentException dans le cas ou p1=p2 ou alors p1 (et/ou p2) n'existent pas.*/
    public void ajouterRelation(Personne p1, Personne p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("Erreur.... les personnes ne peuvent detester que les autres, pas eux mêmes.");
        }
        if (!personnes.contains(p1) || !personnes.contains(p2)) {
            throw new IllegalArgumentException(p1+" ou alors "+p2+"n'existent pas dans ce groupe");
        }
        p1.ajoutePersonneNonAime(p2);
        p2.ajoutePersonneNonAime(p1);
    }

    /**
     *Met à jour les préférences d'une personne
     *
     * @param personne la personne courante
     * @param chambresPref les chambres classées de la préférée à la moins aimée
     * @throws IllegalArgumentException si la personne n'est pas dans le groupe
     */
    public void definirPreferences(Personne personne, List<Integer> chambresPref) {
        if (!personnes.contains(personne)) {
            throw new IllegalArgumentException("La personne n'existe pas dans le groupe");
        }
        personne.setPreferences(new Preferences(chambresPref));
    }

    /**
     * Cherche une personne par son nom. Méthode assez permissive qui ne provoque pas d'errreur si la lettre est écrit en miniscule ou en majuscule.
     *
     * @param nom nom de la personne
     * @return la personne si personne porte ce nom
     */
    public Personne rechercheNomPersonne(String nom) {
        String nomBonFormat = nom.trim().toUpperCase();
        Personne personneTrouvee = null;
        boolean trouve = false;
        int i = 0;
        Personne personne;

        while(i<taille && !trouve){
            personne = personnes.get(i);
            if (personne.getNom().equals(nomBonFormat)) {
                personneTrouvee = personne;
            }
            trouve= true;
            i++;}
        return personneTrouvee;
    }

    /**
     * Vérifie les préférences de toutes les personnes.
     * @return pour chaque personne qui a un problème, son type de problème
     */
    public Map<Personne, PreferencesIssue> verifierPreferences() {
        Map<Personne, PreferencesIssue> problemes = new LinkedHashMap<>();
        for (Personne personne : personnes) {
            PreferencesIssue probleme = personne.getPreferences().diagnostiqueProbleme(taille);
            if (probleme != PreferencesIssue.AUCUN) {
                problemes.put(personne, probleme);
            }
        }
        return problemes;
    }
    
}
