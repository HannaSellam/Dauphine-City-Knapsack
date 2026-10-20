package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

/**
* Cette classe implémente un solveur glouton à retrait pour le problème du sac à dos multidimensionnel.
* L'algorithme calcule une solution gloutonne en combinant une phase de retrait et une phase d'ajout.
* @author Gabriel
*/
public class GloutonRetraitSolver {
	/**
	* <p>
	* La méthode resolution() commence avec tous les objets dans la solution et retire les objets les moins intéressants tant que les budgets ne sont pas respectés.
	* Elle lance ensuite un glouton à ajout sur les objets retirés pour améliorer la solution.
	* 
	* @param sac le sac à dos à résoudre.
    * @return une solution admissible sous forme de liste d'objets.
	*/
	public List<Objet> resolution(SacADos sac) {
		List<Objet> solution = new ArrayList<>(sac.getObjets());
		ArrayList<Integer> L = calculerL(sac, solution);
		Comparator<Objet> ordre = new OrdreRetrait(L);
		solution.sort(ordre);
		
		List<Objet> objetsSupp = new ArrayList<>();
		Iterator<Objet> it = solution.iterator();
		while (it.hasNext()) {
			Objet o = it.next();
			if (! verifierBudgets(sac, solution)) {
				objetsSupp.add(o);
				it.remove();
			}
			else 
				break;
		}
		int[] budgetsActuels = sac.getBudgets().clone();
		int[] budgetsRestants = sac.ajusterBudgetsRestantsListe(budgetsActuels, solution);
		
		SacADos sacAjout = new SacADos(budgetsRestants, objetsSupp);
		GloutonAjoutSolver solver = new GloutonAjoutSolver();
		List<Objet> solObjetsSupp = solver.resolution(sacAjout, new OrdreMax());
		solution.addAll(solObjetsSupp);
		return solution;
	}
	
	/**
     * La méthode calculerL() calcule l'ensemble des dimensions pour lesquelles le dépassement de budget est maximal.
     * <p>
     * À partir de la solution courante, on calcule, pour chaque dimension, la différence entre la somme des coûts et le budget.
     * On construit ensuite une liste contenant les indices des dimensions qui ont le plus fort dépassement.
     *
     * @param sac le sac à dos considéré.
     * @param solution la solution courante (liste d'objets).
     * @return la liste des indices de dimensions les plus dépassées.
     */
	public ArrayList<Integer> calculerL(SacADos sac, List<Objet> solution) {
		int[] depassement = new int[sac.getDimension()];
		for (int i=0; i<sac.getDimension(); i++) {
			int sumCouts = 0;
			for (Objet o : solution) {
				sumCouts+=o.getCouts()[i];
			}
			depassement[i] = sumCouts - sac.getBudgets()[i];
		}
		int depMax = Integer.MIN_VALUE;
		for (int i=0; i<depassement.length; i++) {
			if (depassement[i] > depMax) {
				depMax = depassement[i];
			}
		}
		ArrayList<Integer> L = new ArrayList<>();
		for (int i=0; i<depassement.length; i++) {
			if (depassement[i] == depMax) {
				L.add(i);
			}
		}
		return L;
	}

	//Vérifie si une solution respecte tous les budgets du sac à dos.
	private  boolean verifierBudgets(SacADos sac, List<Objet> solution) {
		for (int i=0; i<sac.getDimension(); i++) {
			int sumCouts = 0;
			for (Objet o : solution) {
				sumCouts+=o.getCouts()[i];
			}
			if (sumCouts > sac.getBudgets()[i]) {
				return false;
			}
		}
		return true;
	}
}

	