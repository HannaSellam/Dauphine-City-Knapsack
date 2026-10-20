package sacADos;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe représente une instance du problème du sac à dos multidimensionnel.
 * Un sac à dos est défini par une dimension, un tableau de budgets et une liste d'objets disponibles.
 * @author Gabriel
 */
public class SacADos {
	private final int dimension;
	private final int[] budgets;
	private final List<Objet> objets;
	
	/**
     * Construit une instance de sac à dos multidimensionnel.
     * 
     * @param budgets tableau des budgets disponibles pour chaque dimension.
     * @param objets  liste des objets pouvant être placés dans le sac à dos.
     */
	public SacADos(int[] budgets, List<Objet> objets) {
		Objet.reinitialiserIdObjet();
		this.dimension = budgets.length;
		this.budgets = budgets;
		this.objets = new ArrayList<>(objets);
	}
	
	/**
     * Retourne la liste des objets disponibles pour ce sac à dos.
     *
     * @return la liste des objets disponibles pour ce sac à dos.
     */
	public List<Objet> getObjets() {
		return objets;
	}
	
	/**
     * Retourne le tableau des budgets du sac à dos pour chaque dimension.
     *
     * @return un tableau contenant les budgets pour chaque dimension.
     */
	public int[] getBudgets() {
		return budgets;
	}
	
	/**
     * Retourne la dimension du sac à dos qui correspond au nombre de budgets.
     *
     * @return la dimension du sac à dos.
     */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * La méthode ajusterBudgetsRestants() met à jour un tableau de budgets restants après l'ajout d'un objet.
	 *
	 * @param budgetsActuels les budgets restants avant l'ajout de l'objet.
	 * @param o l'objet à ajouter.
	 * @return le tableau des budgets restants après l'ajout de l'objet.
	 */
	public int[] ajusterBudgetsRestants(int[] budgetsActuels, Objet o) {
		for (int i=0; i<dimension; i++) {
			budgetsActuels[i] -= o.getCouts()[i];
		}
		return budgetsActuels;
	}
	
	/**
	 * La méthode ajusterBudgetsRestantsListe() met à jour un tableau de budgets restants après l'ajout d'une liste d'objets.
	 *
	 * @param budgetsActuels le tableau des budgets restants avant l'ajout de la liste d'objets.
	 * @param objets la liste d'objets ajoutée.
	 * @return le tableau des budgets restants après l'ajout de la liste d'objets.
	 */
	public int[] ajusterBudgetsRestantsListe(int[] budgetsActuels, List<Objet> objets) {
		for (Objet o : objets) {
			budgetsActuels = ajusterBudgetsRestants(budgetsActuels, o);
		}
		return budgetsActuels;
	}

	/**
	 * La méthode ajoutPossible() vérifie si l'ajout d'un objet est possible en fonction des budgets restants.
	 *
	 * @param o l'objet à ajouter.
	 * @param budgetsRestants le tableau des budgets restants avant l'ajout de l'objet.
	 * @return true si l'objet peut être ajouté en respectant les budgets restants, false sinon.
	 */
	public boolean ajoutPossible(Objet o, int[] budgetsRestants) {
		for (int i=0; i<dimension; i++) {
			if (o.getCouts()[i] > budgetsRestants[i]) {
				return false;
			}
		}
		return true;
	}
	/**
     * Affiche les informations du sac à dos c'est-à-dire ses budgets et ses objets.
     */
	public void afficherSac() {
		System.out.println("Budgets : ");
		for (int b : budgets) {
			System.out.print(b + " ");
		}
		System.out.println("\nObjets :");
		for (Objet o : objets) {
			o.afficherObjet();
		}
	}
}

