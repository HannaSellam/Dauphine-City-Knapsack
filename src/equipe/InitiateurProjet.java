package equipe;

/**
 * Cette interface définit le comportement d'un initiateur de projet.
 * @author Tana
 */
public interface InitiateurProjet {
	/**
	 * La méthode proposerProjet() crée et renvoie un nouveau projet.
	 * 
	 * @return le nouveau projet proposé par l'expert.
	 */
	Projet proposerProjet();
}

