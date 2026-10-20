package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;

import sacADos.Objet;

/**
 * Cette classe définit un ordre de comparaison pour la phase de retrait.
 * @author Gabriel
 */
public class OrdreRetrait implements Comparator<Objet> {
	private ArrayList<Integer> L;
	
	/**
     * Construit un comparateur pour la phase de retrait.
     * 
     * @param L liste des dimensions avec le plus gros dépassement de budget.
     */
	public OrdreRetrait(ArrayList<Integer> L) {
		this.L = L;
	}
	
	/**
     * La méthode compare() compare deux objets en utilisant le rapport utilité / coût maximal, calculé uniquement
     * sur les dimensions présentes dans la liste L.
     * Le tri est décroissant : un objet avec un ratio plus élevé sera placé avant un objet avec un ratio plus faible.
     *
     * @param o1 le premier objet
     * @param o2 le second objet
     * @return un entier négatif si o1 doit etre placé avant o2,
     *         un entier positif si o1 doit etre placé après o2,
     *         0 en cas d'égalité.
     */
	@Override
	public int compare (Objet o1, Objet o2) { 
		double coutMax1 = 0;
		for (int l : L) {
			if (o1.getCouts()[l] > coutMax1) {
				coutMax1 = o1.getCouts()[l];
			}
		}
		double fmv1 = o1.getUtilite()/coutMax1;
		
		double coutMax2 = 0;
		for (int l : L) {
			if (o2.getCouts()[l] > coutMax2) {
				coutMax2 = o2.getCouts()[l];
			}
		}
		double fmv2 = o2.getUtilite()/coutMax2;
		
		if (fmv1 < fmv2) {
			  return 1;
		  }
		  else if (fmv1 > fmv2) {
			  return -1;
		  }
		  else 
			  return 0;
	}
}