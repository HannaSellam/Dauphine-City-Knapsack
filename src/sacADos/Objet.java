package sacADos;

/**
* Cette classe représente un objet du problème du sac à dos multidimensionnel
* Un objet est caractérisé par une utilité, un tableau de coûts et un identifiant unique.
* @author Gabriel
*/
public class Objet {
	private final int utilite;
	private final int[] couts;
	private final int idObjet;
	private static int cptIdObjet = 0;
	
	/**
     * Construit un nouvel objet avec son utilité et ses coûts.
     * Un identifiant unique est automatiquement attribué à l'objet à partir du compteur statique.
     *
     * @param utilite utilité de l'objet.
     * @param couts   tableau des coûts de l'objet pour chaque contrainte.
     */
	public Objet(int utilite, int[] couts) {
		this.utilite = utilite;
		this.couts = couts;
		cptIdObjet++;
		this.idObjet = cptIdObjet;
	}
	
	/**
     * Retourne le tableau des coûts de l'objet pour chaque dimension.
     *
     * @return un tableau des coûts de l'objet.
     */
	public int[] getCouts() {
		return couts;
	}
	
	/**
     * Retourne l'utilité de l'objet.
     *
     * @return l'utilité de l'objet.
     */
	public int getUtilite() {
		return utilite;
	}
	
	/**
     * Affiche un récapitulatif de l'objet comprenant son identifiant et son utilité.
     */
	public void afficherObjet() {
		System.out.println("~ Objet numero " + idObjet);
		System.out.println("Utilite : " + utilite + "\n");
	}
	
	/**
     * Réinitialise le compteur d'identifiants des objets avant chaque nouveau sac à dos.
     */
	public static void reinitialiserIdObjet() {
		cptIdObjet = 0;
	}
}


