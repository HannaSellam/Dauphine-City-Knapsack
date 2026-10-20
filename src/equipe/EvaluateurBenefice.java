package equipe;

/**
 * Cette interface définit le comportement d'un évaluateur de bénéfice.
 * @author Hanna
 */
public interface EvaluateurBenefice {
	/**
	 * La méthode evaluerBenefice() attribue un bénéfice au projet donné.
	 * 
	 * @param p le projet à évaluer.
	 */
	void evaluerBenefice(Projet p);
}
