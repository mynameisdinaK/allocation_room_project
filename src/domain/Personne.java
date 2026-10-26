package allocation_room_project.src.domain;

/**
 *Class décrivant une personne du groupe
 * @author A_COMPLETER
 * @version 1.0
 */
public class Personne {
    private String nom;

    /**
     * Crée une personne avec le nom donné.
     *
     * @param nom le nom de la personne, qui doit respecter {@link #estNomValide(String)}
     * @throws IllegalArgumentException si le nom n'est pas valide
     */
    public Personne(String nom) {
        if (!estNomValide(nom)) 
            throw new IllegalArgumentException("Nom invalide : " + nom);
        this.nom = nom;
    }

    /**
    * Indique si un nom est valide pour une personne.
    * <p>
    * Un nom est valide s'il est composé d'une seule lettre majuscule
    * entre {@code A} et {@code Z}.
    *
    * @param nom le nom à vérifier, éventuellement {@code null}
    * @return {@code true} si le nom est valide, {@code false} sinon
    */
    //TODO: rendre cette fonction un peu plus flexible en authorisant les lettres miniscules (on les interpretera seulement par la suite comme des lettres majuscules)
    public static boolean estNomValide(String nom) {
    return nom != null&& nom.length() == 1&& nom.charAt(0) >= 'A'&& nom.charAt(0) <= 'Z';}

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
