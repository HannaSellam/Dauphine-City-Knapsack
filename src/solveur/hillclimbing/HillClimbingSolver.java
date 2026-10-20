package solveur.hillclimbing;

import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cette classe implémente une méthode de Hill Climbing (recherche locale) pour le problème du sac à dos multidimensionnel.
 * @author Tana
*/
public class HillClimbingSolver {
	private Random rand = new Random();
	/**
     * La méthode resolution() applique l'algorithme du Hill Climbing classique à partir d'une solution initiale.
     * <p>
     * À chaque étape, on cherche un voisin de meilleure utilité.
     * Si aucun voisin n'a une utilité strictement supérieure, la méthode renvoie la solution courante.
     *
     * @param sac l'instance de sac à dos à résoudre.
     * @param solInit la solution initiale (liste d'objets).
     * @param alea true si l'on souhaite générer un nombre limité de voisins aléatoirement, false sinon.
     * @return une solution locale maximale pour l'utilité.
     */
	public List<Objet> resolution(SacADos sac, List<Objet> solInit, boolean alea) {
		Voisin sol = new Voisin (solInit);
		while (true) {
			Voisin voisinMax = rechercheVoisinMax(sac, sol, alea);
			if (voisinMax.getUtilite() <= sol.getUtilite()) {
				return sol.getObjets();
			}
			sol =  new Voisin (voisinMax.getObjets());
		}
	}

	/**
     * La méthode resolutionEgalite() est une variante de Hill Climbing qui accepte les voisins d'utilité égale.
     *
     * @param sac l'instance de sac à dos à résoudre.
     * @param solInit la solution initiale (liste d'objets).
     * @param alea true si l'on souhaite générer un nombre limité de voisins aléatoirement, false sinon.
     * @return une solution locale maximale (en acceptant les égalités).
     */
	public List<Objet> resolutionEgalite(SacADos sac, List<Objet> solInit, boolean alea) {
		Voisin sol = new Voisin (solInit);
		while (true) {
			Voisin voisinMax = rechercheVoisinMax(sac, sol, alea);
			if (voisinMax.getUtilite() < sol.getUtilite()) {
				return sol.getObjets();
			}
			sol =  new Voisin (voisinMax.getObjets());
		}
	}
	
	/**
     * La méthode resolutionMultiples() applique le Hill Climbing à partir de plusieurs solutions initiales.
     * <p>
     * Les solutions de départ sont obtenues à l'aide des solveurs gloutons.
     * La meilleure solution trouvée par Hill Climbing est renvoyée.
     *
     * @param sac l'instance de sac à dos à résoudre.
     * @param alea true si l'on souhaite générer un nombre limité de voisins aléatoirement, false sinon.
     * @return la meilleure solution locale trouvée parmi les solutions initiales.
     */ 
	public List<Objet> resolutionMultiples(SacADos sac, boolean alea) {
		List<Voisin> solutionsInit = new ArrayList<>();
		
		GloutonAjoutSolver solverAjout = new GloutonAjoutSolver();
		List<Objet> solMax = solverAjout.resolution(sac, new OrdreMax());
		Voisin vMax = new Voisin (solMax);
		solutionsInit.add(vMax);
		
		List<Objet> solSomme = solverAjout.resolution(sac, new OrdreSomme());
		Voisin vSomme = new Voisin (solSomme);
		solutionsInit.add(vSomme);
		
		GloutonRetraitSolver solverRetrait = new GloutonRetraitSolver();
		List<Objet> solRetrait = solverRetrait.resolution(sac);
		Voisin vRetrait = new Voisin (solRetrait);
		solutionsInit.add(vRetrait);
		
		List<Objet> solution = new ArrayList<>();
		int utiliteMax = 0;
		for (Voisin vSol : solutionsInit) {
			List<Objet> solHC = this.resolution(sac, vSol.getObjets(), alea);
			Voisin vSolHC = new Voisin (solHC);
			  if (vSolHC.getUtilite() > utiliteMax) {
				  utiliteMax = vSolHC.getUtilite();
				  solution = vSolHC.getObjets();  
			  }
		  }
		return solution;
	}
	
	//Retourne, parmi tous les voisins de la solution courante, celui qui a la plus grande utilité.
	private Voisin rechercheVoisinMax(SacADos sac, Voisin sol, boolean alea) {
		List<Voisin> listeVoisins = listerVoisins(sac, sol, alea);
		int utiliteMax = sol.getUtilite();
		Voisin voisinMax = new Voisin (sol.getObjets());
		for (Voisin v : listeVoisins) {
			if (v.getUtilite() > utiliteMax) {
				utiliteMax = v.getUtilite();
				voisinMax = new Voisin (v.getObjets());
			}
		}
		return voisinMax;
	}
	
	//Génère une liste de voisins (aléatoire ou complète) de la solution courante.
	private List<Voisin> listerVoisins(SacADos sac, Voisin sol, boolean alea) {
		if (alea) {
			return listerVoisinsAleatoire(sac, sol);
		}
		else {
			return listerVoisinsComplet(sac, sol);
		}
	}
	
	//Ajoute un nouveau voisin, obtenu par ajout d'un objet, à la liste de voisins.
	private void ajouterListeVoisin(Voisin sol, List<Voisin> listeVoisins, Objet o) {
		List<Objet> objetsNouvVoisin =  new ArrayList<>(sol.getObjets());
		objetsNouvVoisin.add(o);
		Voisin voisin = new Voisin (objetsNouvVoisin);
		listeVoisins.add(voisin);
	}
	
	//Génère aléatoirement des voisins par ajout d'un objet parmi ceux restants.
	private void ajouterVoisinAleatoire(SacADos sac, Voisin sol, List<Voisin> listeVoisins, int nbVoisins, List<Objet> objetsRestants) {
		int[] budgetsRestants = sac.ajusterBudgetsRestantsListe(sac.getBudgets().clone(), sol.getObjets());
		int nbObjetsRestants = objetsRestants.size();
		for (int i=0; i<nbObjetsRestants; i++) {
			int indice = rand.nextInt(objetsRestants.size());
			Objet o = objetsRestants.get(indice);
			objetsRestants.remove(o);
			if (sac.ajoutPossible(o, budgetsRestants)) {
				ajouterListeVoisin(sol, listeVoisins, o);
				if (listeVoisins.size() == nbVoisins) {
					return;
				}
			}
		}
	}
	//La méthode listerVoisinsAleatoire() génère aléatoirement un nombre limité de voisins de la solution courante.
	private List<Voisin> listerVoisinsAleatoire(SacADos sac, Voisin sol) {
		List<Voisin> listeVoisins = new ArrayList<>();
		int nbVoisins =  5 + rand.nextInt(6);
		//Ajout seulement
		List<Objet> objetsRestants = listerObjetsRestants(sac, sol);
		ajouterVoisinAleatoire(sac, sol, listeVoisins, nbVoisins, objetsRestants);
		if (listeVoisins.size() == nbVoisins) {
			return listeVoisins;
		}
		//Retrait seulement
		if (sol.getObjets().size() != 0 ) {
			int indice = rand.nextInt(sol.getObjets().size());
			Objet o = sol.getObjets().get(indice);
			List<Objet> nouvSol =  new ArrayList<>(sol.getObjets());
			nouvSol.remove(o);
			Voisin voisinSansAjout = new Voisin (nouvSol);
			listeVoisins.add(voisinSansAjout);
			if (listeVoisins.size() == nbVoisins) {
				return listeVoisins;
			}
			//Retrait et ajout
			objetsRestants = listerObjetsRestants(sac, sol);
			ajouterVoisinAleatoire(sac, voisinSansAjout, listeVoisins, nbVoisins, objetsRestants);
			
		}
		return listeVoisins;
	}
	
	//La méthode listerVoisinsComplet() génère l'ensemble des voisins de la solution courante.
	private List<Voisin> listerVoisinsComplet(SacADos sac, Voisin sol) {
		List<Voisin> listeVoisins = new ArrayList<>();
		int[] budgetsActuels = sac.getBudgets().clone();
		
		//Ajout seulement
		int[] budgetsRestants = sac.ajusterBudgetsRestantsListe(budgetsActuels, sol.getObjets());
		List<Objet> objetsRestants = listerObjetsRestants(sac, sol);
		for (Objet o : objetsRestants) {
			if (sac.ajoutPossible(o, budgetsRestants)) {
				ajouterListeVoisin(sol, listeVoisins, o);
			}
		}
		
		for (Objet o : sol.getObjets()) {
			List<Objet> nouvSol =  new ArrayList<>(sol.getObjets());
			objetsRestants = listerObjetsRestants(sac, sol);
			//Retrait seulement
			nouvSol.remove(o);
			Voisin voisinSansAjout = new Voisin (nouvSol);
			listeVoisins.add(voisinSansAjout);
			//Retrait et ajout
			budgetsRestants = sac.ajusterBudgetsRestantsListe(budgetsActuels, nouvSol);
			for (Objet obj : objetsRestants) {
				if (sac.ajoutPossible(obj, budgetsRestants)) {
					ajouterListeVoisin(voisinSansAjout, listeVoisins, obj);
				}
			}
		}
		return listeVoisins;
	}
	
	//Renvoie la liste des objets du sac qui ne sont pas présents dans la solution courante.
	private List<Objet> listerObjetsRestants(SacADos sac, Voisin sol) {
		List<Objet> objetsRestants = new ArrayList<>(sac.getObjets());
		for (Objet o : sol.getObjets()) {
			objetsRestants.remove(o);  
		}
		return objetsRestants;
	}
}
