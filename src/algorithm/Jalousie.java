package algorithm;

import domain.Personne;

import java.util.List;
import java.util.Map;

/**
 * Calcule le nombre de personnes jalouses dans une affectation
 * @author Rima
 */
public class Jalousie {

    /**
     Calcule le coût d'une affectation
      Une personne est jalouse si au moins une personne qu'elle n'aime pas possède une chambre qu'elle préfère à la sienne 
      et  Chaque personne jalouse compte une seule fois

     */
    public static int calculerCout(
            List<Personne> personnes,
            Map<Personne, Integer> affectation) {

        if (personnes == null || affectation == null) {
            throw new IllegalArgumentException(
                    "Le groupe et l'affectation ne doivent pas être null.");
        }

        int nombreJaloux = 0;
        for (Personne personne : personnes) {// Examiner chaque personne du groupe

            Integer chambrePersonne = affectation.get(personne);
            if (chambrePersonne == null) {
                throw new IllegalArgumentException(
                        "Aucune chambre attribuée à " + personne);
            }

            
            int rangPersonnel = personne.getPreferences().rangChambre(chambrePersonne);

            //vérifier les personnes qu'elle n'aime pas
            for (Personne autre : personne.getPersonnesNonAimees()) {

                Integer chambreAutre = affectation.get(autre);

                if (chambreAutre == null) {
                    throw new IllegalArgumentException("Aucune chambre attribuée à " + autre);
                }

                // Rang de la chambre de l'autre personne, évalué selon les préférences de la personne courante.
                int rangAutre = personne.getPreferences().rangChambre(chambreAutre);

                
                if (rangAutre < rangPersonnel) {// Un rang plus petit signifie une chambre préférée.
                    nombreJaloux++;

                   
                    break;
                }
            }
        }

        return nombreJaloux;
    }
}