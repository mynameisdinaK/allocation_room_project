package algorithm;

import domain.Personne;
import domain.Preferences;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**Construit une affectation initiale des chambres
  Chaque personne reçoit sa chambre préférée encore disponible
  Les personnes sont traitées dans l'ordre de la liste fournie.
  @author Rima
    */

public class AffectationNaive {

  
    public static Map<Personne, Integer> calculer(List<Personne> personnes) {
         if (personnes == null || personnes.isEmpty()) {
            throw new IllegalArgumentException("Le groupe doit contenir au moins une personne.");
        }

        int n = personnes.size();

        // verifier les préférences avant de commencer l'affectation
        for (Personne personne : personnes) {
            if (personne == null) {
                throw new IllegalArgumentException( "Le groupe contient une personne invalide.");
             }

            if (personne.getPreferences() == null || !personne.getPreferences().estComplete(n)) {
                throw new IllegalArgumentException("Les préférences de " + personne + " sont invalides ou incomplètes.");
    }
}

        
        List<Integer> chambresAttribuees = new ArrayList<>();// c'est les chambres déjà attribuées

        
        Map<Personne, Integer> affectation = new LinkedHashMap<>();//l'affectation finale : personne = numéro de chambre

        // Traiter les personnes dans l'ordre de la liste
        for (Personne personne : personnes) {
            Preferences preferences = personne.getPreferences();

            // Parcourir les chambres de la plus préférée à la moins préférée
            for (int chambre : preferences.getChambresPref()) {
                if (!chambresAttribuees.contains(chambre)) {// Prendre la première chambre encore disponible
                    affectation.put(personne, chambre);
                    chambresAttribuees.add(chambre);

                    
                    break;// La personne a reçu une chambre : passer à la suivante
                }
            }
        }

        return affectation;
    }
}