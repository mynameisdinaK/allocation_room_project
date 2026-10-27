package domain;

import java.util.HashSet;
import java.util.Set;
/**
 *Class décrivant une personne du groupe
 * @author Dina
 */
public class Personne {
    private String nom;
    private Set <Personne> personnesNonAimees;
    private Preferences preferences;

    /**
     * Crée une personne avec le nom donné et initialise sa liste de préférence en terme de chambre et la liste des personens qu'elle n'aime pas.
     *
     * @param nom le nom de la personne. Il doit être une lettre comprise entre A et Z.
     * @throws IllegalArgumentException si le nom n'est pas valide
     */
    public Personne(String nom) {
        if (!estNomValide(nom)) 
            throw new IllegalArgumentException("Nom invalide (lettre majuscule de A à Z requis) : " + nom);
        this.nom = nom.toUpperCase();
        this.preferences = new Preferences();
        this.personnesNonAimees = new HashSet<>();
    }

    //Methodes ==================================

    /**
    * Indique si un nom est valide pour une personne.
    * Un nom est valide s'il est composé d'une seule lettre majuscule entre A et Z.
    *
    * @param nom le nom de la personne à valider.
    * @return {@code true} si le nom est valide, {@code false} sinon
    */
    public static boolean estNomValide(String nom) {
        boolean nomValide = false;
        char lettre;

        if (nom!=null &&nom.length() == 1){
            lettre = nom.toUpperCase().charAt(0);
            if(lettre >= 'A' && lettre <='Z'){
                nomValide = true;
            }
        }
    return nomValide;
    }

    /**
     * Ajoute une personne dans la liste des personnes qu'elle n'aime pas.
     * 
     * @param autrePersonne qu'elle n'aime pas
     */
    public void ajoutePersonneNonAime(Personne autrePersonne){
        personnesNonAimees.add(autrePersonne);
    }

    public boolean naimePas(Personne autrePersonne){
        return personnesNonAimees.contains(autrePersonne);
    }

    //==============================Redefinition

    @Override
    public String toString() {
        return nom.toUpperCase();
    }


    //===================================Getters et Setters

    public String getNom() {
        return nom;
    }

    public Preferences getPreferences(){
        return preferences;
    }

    public Set<Personne> getPersonnesNonAimees(){
        return personnesNonAimees;
    }

    public void setPreferences(Preferences preferences){
        this.preferences = preferences;
    }

}
