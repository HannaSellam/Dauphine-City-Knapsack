package solveur.glouton;

import java.util.Comparator;

import sacADos.Objet;

/**
 * Cette classe définit un ordre de comparaison basé sur le rapport utilité / coût maximum d'un objet.
 * @author Gabriel
 */
public class OrdreMax implements Comparator<Objet> {
	/**
     * La méthode compare() compare deux objets selon leur rapport
     * utilité / coût maximal.
     * Le tri est décroissant : un objet avec un ratio plus élevé sera placé avant un objet avec un ratio plus faible.
     *
     * @param o1 le premier objet.
     * @param o2 le second objet.
     * @return un entier négatif si o1 doit etre placé avant o2,
     *         un entier positif si o1 doit etre placé après o2,
     *         0 en cas d'égalité.
     */
	@Override
	public int compare (Objet o1, Objet o2) {
		  double coutMax1 = 0;
		  for (int i=0; i<o1.getCouts().length; i++) {
			  if (o1.getCouts()[i] > coutMax1) {
				  coutMax1 = o1.getCouts()[i];
			  }
		  }
		  double fmax1 = o1.getUtilite()/coutMax1;
		  
		  double coutMax2 = 0;
		  for (int i=0; i<o2.getCouts().length; i++) {
			  if (o2.getCouts()[i] > coutMax2) {
				  coutMax2 = o2.getCouts()[i];
			  }
		  }
		  double fmax2 = o2.getUtilite()/coutMax2;
		  
		  if (fmax1 < fmax2) {
			  return 1;
		  }
		  else if (fmax1 > fmax2) {
			  return -1;
		  }
		  else 
			  return 0;
	}

}