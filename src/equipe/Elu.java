package equipe;

import java.util.Random;

/**
 * Cette classe représente l'élu de la municipalité qui évalue les bénifices des projets proposés.
 * @author Hanna
 */
public class Elu extends Personne implements EvaluateurBenefice {
	private Random rand = new Random();
	
	/**
     * Crée un nouvel élu avec les informations d'identité fournies.
     *
     * @param nom    nom de l'élu.
     * @param prenom prénom de l'élu.
     * @param age    âge de l'élu.
     */
	public Elu(String nom, String prenom, int age) {
		super(nom, prenom, age);
	}
	
	/**
	 * La méthode evaluerBenefice() attribue un bénéfice aléatoire (entre 0 et 500000) au projet. 
	 */
	@Override
	public void evaluerBenefice(Projet p) {
		int benefice = rand.nextInt(500001);
		p.setBenefice(benefice);
	}

}