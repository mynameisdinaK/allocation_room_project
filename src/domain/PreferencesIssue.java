package domain;

/**
 * Enumeration listant le type de problemes auquel on peut être confronté dans les preferences de chambre d'une personne
 * @author Dina
 * 
 */
public enum PreferencesIssue {
    AUCUN ("pas de problème"),
    MANQUANT ("préférences absentes"),
    INCOMPLET ("préférences incomplètes"),
    DUPLIQUE ("une chambre est citée plusieurs fois"),
    HORS_LIMITE ("une chambre n'existe pas"); 

    private String message;

    PreferencesIssue(String message) {
        this.message = message;
    }

    /**
     * Retourne le texte à afficher pour ce problème.
     *
     * @return le message en français
     */
    public String getMessage() {
        return message;
    }
}
