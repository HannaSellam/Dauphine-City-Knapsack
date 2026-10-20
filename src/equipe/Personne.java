package equipe;

/**
 * Cette classe représente une personne de l'équipe municipale.
 * Une personne est caractérisée par un nom, un prénom et un âge.
 * @author Tana
 */
public class Personne {
	private String nom;
	private String prenom;
	private int age;
	
	/**
     * Crée une personne avec son nom, son prénom et son âge.
     *
     * @param nom    nom de la personne.
     * @param prenom prénom de la personne.
     * @param age    âge de la personne.
     */
	public Personne(String nom, String prenom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		
	}
	
	/**
     * Retourne le nom de la personne.
     *
     * @return le nom de la personne.
     */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne le prénom de la personne.
	 * 
	 * @return le prénom de la personne.
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Retourne l'âge de la personne.
	 * 
	 * @return l'âge de la personne.
	 */
	public int getAge() {
		return age;
	}
	
}
