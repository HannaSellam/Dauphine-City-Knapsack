package solveur.glouton;

import java.util.Comparator;

import sacADos.Objet;

/**
* Cette classe définit un ordre de comparaison basé sur le rapport utilité / somme des coûts d'un objet.
* @author Gabriel
*/
public class OrdreSomme implements Comparator<Objet> {
	/**
     * La méthode compare() compare deux objets selon leur rapport
     * utilité / somme des coûts.
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
		  double sum1 = 0;
		  for (int i=0; i<o1.getCouts().length; i++) {
			  sum1 += o1.getCouts()[i];
		  }
		  double fsum1 = o1.getUtilite()/sum1;
		  
		  double sum2 = 0;
		  for (int i=0; i<o2.getCouts().length; i++) {
			  sum2 += o2.getCouts()[i];
		  }
		  double fsum2 = o2.getUtilite()/sum2;
		  
		  
		  if (fsum1 < fsum2) {
			  return 1;
		  }
		  else if (fsum1 > fsum2) {
			  return -1;
		  }
		  else 
			  return 0;
	}

} 