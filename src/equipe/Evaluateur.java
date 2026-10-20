package equipe;

import java.util.Random;

/**
 * Cette classe représente l'évaluateur de la municipalité.
 * @author Hanna
 */
public class Evaluateur extends Personne implements EvaluateurCout {
	private Random rand = new Random();
	private TypeCout specialisation;
	
	/**
     * Construit un nouvel évaluateur de la municipalité, spécialisé dans un type de coût.
     *
     * @param nom            nom de l'évaluateur.
     * @param prenom         prénom de l'évaluateur.
     * @param age            âge de l'évaluateur.
     * @param specialisation type de coût que cet évaluateur prend en charge.
     */
	public Evaluateur(String nom, String prenom, int age, TypeCout specialisation) {
		super(nom, prenom, age);
		this.specialisation = specialisation;
	}
	
	/**
	 * La méthode evaluerCouts() attribue un coût en fonction de la spécialisation de l'évaluateur de manière aléatoire au projet. 
	 */
	@Override
	public void evaluerCouts(Projet p) {
		int cout = Cout.COUT_MIN + rand.nextInt(Cout.COUT_MAX - Cout.COUT_MIN);
		p.setCouts(specialisation, cout);
	}
}



