package solveur.hillclimbing;

import sacADos.Objet;
import java.util.List;

/**
 * Cette classe représente une solution candidate (un voisin) pour le problème du sac à dos.
 * @author Tana
*/
public class Voisin {
	private final List<Objet> objets;
	private int utilite;
	
	/**
     * Construit un voisin à partir d'une liste d'objets.
     *
     * @param objets la liste des objets qui constitue cette solution candidate.
     */
	public Voisin(List<Objet> objets) {
		this.objets = objets;
		this.utilite = calculUtiliteSolution();
	}
	
	/**
     * La méthode calculUtiliteSolution() calcule l'utilité totale de la solution en sommant l'utilité de tous les objets.
     *
     * @return l'utilité totale de la solution.
    */
	public int calculUtiliteSolution() {
		int utilite=0;
		for (Objet o : objets) {
			utilite+=o.getUtilite();
		}
		return utilite;
	}
	
	/**
     * Retourne la liste des objets de la solution.
     *
     * @return la liste des objets de la solution.
     */
	public List<Objet> getObjets() {
		return objets;
	}
	
	/**
     * Retourne l'utilité de la solution.
     *
     * @return l'utilité de la solution.
     */
	public int getUtilite() {
		return utilite;
	}
}
