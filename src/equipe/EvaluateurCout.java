package equipe;

/**
 * Cette interface définit le comportement d'un évaluateur de coût.
 * @author Hanna
 */
public interface EvaluateurCout {
	/**
	 * La méthode evaluerCouts() attribue un coût au projet donné.
	 * 
	 * @param p le projet à évaluer.
	 */
	void evaluerCouts(Projet p);
}
