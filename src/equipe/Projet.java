package equipe;

/**
 * Cette classe représente un projet municipal.
 * Un projet possède un titre, une description, un secteur, un bénéfice et un ensemble de coûts (économique, social et environnemental).
 * @author Hanna
 */
public class Projet {
	private String titre;
	private String description;
	private Secteur secteur;
	private int benefice;
	private Cout couts;
	
	/**
     * Construit un nouveau projet avec son titre, sa description et son secteur.
     * Un objet {@link Cout} est créé pour le projet, mais ses valeurs seront fixées plus tard par les évaluateurs.
     * 
     * @param titre       titre du projet.
     * @param description description succincte du projet.
     * @param secteur     secteur d'activité auquel le projet appartient.
     */
	public Projet(String titre, String description, Secteur secteur) {
		this.titre = titre;
		this.description = description;
		this.secteur = secteur;
		this.couts = new Cout();
	}
	
	/**
     * Met à jour un des coûts du projet en fonction du type de coût.
     *
     * @param type type de coût à modifier.
     * @param cout nouvelle valeur du coût (en euros).
     */
	public void setCouts(TypeCout type, int cout) {
		switch (type) {
			case ECONOMIQUE :
				couts.setCoutEconomique(cout);
				break;
			case SOCIAL :
				couts.setCoutSocial(cout);;
				break;
			case ENVIRONNEMENTAL :
				couts.setCoutEnvironnemental(cout);;
				break;
		}
	}
	
	/**
     * Retourne l'ensemble des coûts associés au projet.
     *
     * @return l'objet {@link Cout} représentant les différents coûts.
     */
	public Cout getCouts() {
		return couts;
	}
	
	/**
     * Définit le bénéfice attendu du projet.
     *
     * @param benefice bénéfice du projet (en euros).
     */
	public void setBenefice(int benefice) {
		this.benefice = benefice;
	}
	
	/**
     * Retourne le bénéfice du projet.
     *
     * @return le bénéfice du projet (en euros).
     */
	public int getBenefice() {
		return benefice;
	}
	
	/**
     * Retourne le secteur d'activité du projet.
     *
     * @return le secteur d'activité du projet.
     */
	public Secteur getSecteur() {
		return secteur;
	}
	
	/**
     * Affiche un récapitulatif du projet comprenant le titre, le secteur, les différents coûts et le bénéfice associé.
     */
	public void afficherProjet() {
		System.out.println(titre + " - " + "Secteur : " + secteur);
		System.out.println("~ Cout economique : " + couts.getCoutEconomique());
		System.out.println("~ Cout social : " + couts.getCoutSocial());
		System.out.println("~ Cout environnemental : " + couts.getCoutEnvironnemental());
		System.out.println("~ Benefice : " + benefice + "\n");
	}
}


