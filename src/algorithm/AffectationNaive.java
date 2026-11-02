package algorithm;

import domain.Affectation;
import domain.Personne;



import java.util.ArrayList;
import java.util.List;

/**Construit une première affectation naive des personnes à leur chambres.
 * Raisonnement: on va commencer par affecter la chambre préféree à la personne qui a le plus de relations.
  @author Rima
    */

public class AffectationNaive {

    /**
     * Cherche la personne qui a le plus de relations qui ne s'aiment pas.
     *
     * @param personnes la liste des personnes qui n'ont pas encore de chambre
     * @return l'indice de cette personne, la première de la liste en cas d'égalité
     */
    private static int personnePlusDeRelation(List<Personne> personnes) {
        int i = 1;
        int indiceMax = 0;
        int nombreRelations;

        while (i < personnes.size()) {
            nombreRelations = personnes.get(i).getPersonnesNonAimees().size();
            if (nombreRelations > personnes.get(indiceMax).getPersonnesNonAimees().size()) {
                indiceMax = i;
            }
            i++;
        }

        return indiceMax;
    }



    /**
     * Construit l'affectation naïve d'un groupe.
     *
     * @param personnes le groupe, chaque personne doit avoir des préférences complètes
     * @return l'affectation
     * @throws IllegalArgumentException si le groupe est vide ou si des préférences sont invalides
     */
    public static Affectation affectationNaive(List<Personne> personnes) {
        int taille;
        int i;
        int indice;
        int rang;
        int chambre;
        boolean chambreTrouvee;
        Personne personne;
        List<Integer> chambresPref;
        List<Personne> restantes;
        Affectation resultat = new Affectation();

        if (personnes == null || personnes.isEmpty()) {
            throw new IllegalArgumentException("Il devrait y avoir au moins une personne....");
        }
        taille = personnes.size();

        // vérification des préférences
        i = 0;
        while (i < taille) {
            personne = personnes.get(i);
            if (personne == null) {
                throw new IllegalArgumentException("Le groupe contient une personne invalide....");
            }
            if (personne.getPreferences() == null || !personne.getPreferences().estComplete(taille)) {
                throw new IllegalArgumentException("Les préférences de " + personne + " sont invalides ou alors incomplètes.");
            }
            i++;
        }

        restantes = new ArrayList<>(personnes);

        //la personne avec le plus de relations à chaque tour prend sa chambre préférée
        while (!restantes.isEmpty()) {
            indice = personnePlusDeRelation(restantes);
            personne = restantes.remove(indice);
            chambresPref = personne.getPreferences().getChambresPref();
            rang = 0;
            chambreTrouvee = false;

            while (!chambreTrouvee && rang < chambresPref.size()) {
                chambre = chambresPref.get(rang);
                
                if (!resultat.chambreEstPrise(chambre)) {
                    resultat.attribuerChambre(personne, chambre);
                    chambreTrouvee = true;
                }
                rang++;
            }
        }

        return resultat;
    }
}