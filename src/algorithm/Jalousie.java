package algorithm;

import domain.Affectation;
import domain.Personne;

import java.util.Iterator;
import java.util.List;

/**
 * Calcule le coût d'une affectation (= nombre de personnes jalouses) Elle possède une unique méthode.
 * @author Rima
 */
public class Jalousie {

    /**
     * 
     * Rappel: Une personne est jalouse si au moins une personne qu'elle n'aime pas possède une chambre qu'elle préfère à la sienne 
      Chaque personne jalouse compte pour une seule fois
     * @param personnes le groupe
     * @param affectation l'affectation à évaluer (affectation naive dans cette premiere partie)
     * @return le nombre de personnes jalouses
     * @throws IllegalArgumentException si un paramètre est null ou si une personne n'a pas de chambre
     */

      public static int calculerCout(List<Personne> personnes, Affectation affectation){
        int nombreJaloux = 0;
        int i;
        int rangPersonnel;
        int rangAutre;
        boolean jaloux;
        Personne personne;
        Personne autre;
        Iterator<Personne> nonAimees;

        if (personnes == null || affectation == null)
            throw new IllegalArgumentException("Le groupe et l'affectation sont null....");


        i = 0;
        while (i < personnes.size()) {
            personne = personnes.get(i);
            rangPersonnel = personne.getPreferences().rangChambre(affectation.chambreDe(personne));

            // on s'arrête dès qu'on trouve une raison d'être jalouse
            jaloux = false;
            nonAimees = personne.getPersonnesNonAimees().iterator();

            while (!jaloux && nonAimees.hasNext()) {
                autre = nonAimees.next();
                rangAutre = personne.getPreferences().rangChambre(affectation.chambreDe(autre));
                if (rangAutre < rangPersonnel) {
                    jaloux = true;
                }
            }

            if (jaloux) {
                nombreJaloux++;
            }
            i++;
        }

        return nombreJaloux;
    }
}