package solveur.glouton;

import sacADos.Objet;
import sacADos.SacADos;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
* Cette classe implémente un solveur glouton par ajout pour le problème du sac à dos multidimensionnel.
* <p>
* L'algorithme construit une solution en partant d'une solution vide et en parcourant les objets du sac dans un ordre donné.
* Un objet est ajouté à la solution si ses coûts ne dépassent pas les budgets restants.
* @author Gabriel
*/
public class GloutonAjoutSolver {

	/**
     * La méthode resolution() calcule une solution gloutonne par ajout.
     * <p>
     * Les objets du sac sont triés selon le comparateur choisi.
     * Ensuite, on parcourt cette liste triée et on ajoute chaque
     * objet tant que les budgets le permettent.
     *
     * @param sac   le sac à dos à résoudre.
     * @param ordre le comparateur.
     * @return une solution admissible sous forme de liste d'objets.
     */
	public List<Objet> resolution(SacADos sac, Comparator<Objet> ordre) {
		List<Objet> solution = new ArrayList<>();
		List<Objet> copieObjets = new ArrayList<>(sac.getObjets());
		copieObjets.sort(ordre);
		int[] budgetsActuels = sac.getBudgets().clone();
		for (Objet o : copieObjets) {
			if (sac.ajoutPossible(o, budgetsActuels)) {
				solution.add(o);
				budgetsActuels = sac.ajusterBudgetsRestants(budgetsActuels, o);
			}
		}
		return solution;
	}
} 


