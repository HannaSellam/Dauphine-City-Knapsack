package main;

import equipe.*;
import sacADos.*;
import solveur.glouton.*;
import solveur.hillclimbing.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.util.InputMismatchException;

/**
 * Classe principale du programme.
 * @author Hanna
 */
public class Main {
	/**
     * Point d'entrée du programme.
     *
     * @param args arguments de la ligne de commande.
     */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			List<Objet> solFinale = afficherMenu(scan);
			if (solFinale == null) {
				break;
			}
			Voisin solution = new Voisin(solFinale);
			
			if (solFinale.size() == 0) {
				System.out.println("\n¤¤ Solution vide");
			}
			else {
				System.out.println("\n¤¤ Solution contient les objets :");
				for (Objet o : solFinale) {
					o.afficherObjet();
				}
				System.out.println("¤¤ Utilité de la solution " + solution.calculUtiliteSolution());	
			}
		}
		scan.close();
	}

	private static List<Projet> simulationEquipeMunicipale() {
		List<Projet> projets = new ArrayList<>();
		Random rand = new Random();
		int nbExperts =  rand.nextInt(5) + 1;
		System.out.println("-- Nombre d'experts de l'équipe : " + nbExperts + "\n");
		
		int age =  rand.nextInt(65-25+1) + 25;
		Evaluateur evaluateurEco = new Evaluateur("NomEvaluateurEco", "PrenomEvaluateurEco", age, TypeCout.ECONOMIQUE);
		age =  rand.nextInt(65-25+1) + 25;
		Evaluateur evaluateurSocial = new Evaluateur("NomEvaluateurSocial", "PrenomEvaluateurSocial", age, TypeCout.SOCIAL);
		age =  rand.nextInt(65-25+1) + 25;
		Evaluateur evaluateurEnv = new Evaluateur("NomEvaluateurEnv", "PrenomEvaluateurEnv", age, TypeCout.ENVIRONNEMENTAL);
		
		age =  rand.nextInt(65-25+1) + 25;
		Elu elu = new Elu("NomElu", "PrenomElu", age);
		
		for (int i=1; i<=nbExperts; i++) {
			age =  rand.nextInt(65-25+1) + 25;
			Expert expert = new Expert("NomExpert" + i, "PrenomExpert" + i, age);
			int nbPropProjets =  rand.nextInt(3) + 1;
			for (int j=0; j<nbPropProjets; j++) {
				System.out.println("¤¤ Projet proposé par : " + expert.getNom() + " " + expert.getPrenom() + " " + expert.getAge() + "ans\n");
				Projet p = expert.proposerProjet();
				
				evaluateurEco.evaluerCouts(p);
				evaluateurSocial.evaluerCouts(p);
				evaluateurEnv.evaluerCouts(p);
				
				elu.evaluerBenefice(p);
				
				projets.add(p);
				p.afficherProjet();
			}	
		}
		System.out.println("-- Nombre de projets étudiés : " + projets.size() + "\n");
		return projets;
	}
	
	private static SacADos genererSacADos(List<Projet> projets, int option, Scanner scan) {
		while (true) {
			try {
				if (option == 1) {
					System.out.println("\nGénération du sac par couts ");
					System.out.println("Saisir le cout économique : ");
					int budgetEconomique = scan.nextInt();
					System.out.println("Saisir le cout social : ");
					int budgetSocial = scan.nextInt();
					System.out.println("Saisir le cout environnemental : ");
					int budgetEnvironnemental  = scan.nextInt();
					int[] budgets = {budgetEconomique, budgetSocial, budgetEnvironnemental};
					return VersSacADos.genererSacADosCouts(projets, budgets);
				}
				else {
					System.out.println("\nGénération du sac par secteurs ");
					int[] budgets = new int[Secteur.values().length];
					for (Secteur s : Secteur.values()) {
						System.out.println("Saisir budget du secteur " + s);
						int b = scan.nextInt();
						budgets[s.ordinal()] = b;
					}
					return VersSacADos.genererSacADosSecteurs(projets, budgets);
				}
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : budget saisi invalide");
				scan.next();
			}
		}
	}
	
	private static SacADos genererDepuisFichier(Scanner scan) {
		SacADos sac = null;
		while (sac == null) {
			System.out.println("Saisir le nom du fichier :");
			String nomFichier = scan.next();
			sac = VersSacADos.genererSacADosFichier(nomFichier);
		}
		return sac;
	}
	
	private static boolean afficherMenuAlea(Scanner scan) {
		while (true) {
			System.out.println("Générer nombre limité de voisins aléatoirement ? :");
			System.out.println("OUI  - Saisir 1");
			System.out.println("NON - Saisir 2");
			try {
				int choix = scan.nextInt();
				switch (choix) {
					case 1 :
						return true;
					case 2 :
						return false;
					default :
						System.out.println("Erreur : choisir 1 ou 2");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1 ou 2");
				scan.next();
			}	
		}	
	}
	
	private static List<Objet> afficherMenuHillClimbing(SacADos sac, Scanner scan) {
		HillClimbingSolver solverHC = new HillClimbingSolver();
		while (true) {
			System.out.println("~~~~~~ MENU ~~~~~~");
			System.out.println("Résolution par hill climbing classique - Saisir 1");
			System.out.println("Résolution par hill climbing égalité - Saisir 2");
			System.out.println("Résolution par hill climbing multiples - Saisir 3");
			try {
				boolean alea;
				int choix = scan.nextInt();
				switch (choix) {
					case 1 :
						System.out.println("Choix ordre pour solution initiale :");
						List<Objet> solInit = afficherMenuOrdre(sac, scan);
						alea = afficherMenuAlea(scan);
						return solverHC.resolution(sac, solInit, alea);
					case 2 :
						System.out.println("Choix ordre pour solution initiale :");
						solInit = afficherMenuOrdre(sac, scan);
						alea = afficherMenuAlea(scan);
						return solverHC.resolutionEgalite(sac, solInit, alea);
					case 3 :
						alea = afficherMenuAlea(scan);
						return solverHC.resolutionMultiples(sac, alea);
					default :
						System.out.println("Erreur : choisir 1, 2 ou 3");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1, 2 ou 3");
				scan.next();
			}
		}
	}
	
	private static List<Objet> afficherMenuOrdre(SacADos sac, Scanner scan) {
		GloutonAjoutSolver solverAjout = new GloutonAjoutSolver();
		while (true) {
			System.out.println("~~~~~~ MENU ~~~~~~");
			System.out.println("Ordre Somme - Saisir 1");
			System.out.println("Ordre Max - Saisir 2");
			try {
				int choix = scan.nextInt();
				switch (choix) {
					case 1 :
						return solverAjout.resolution(sac, new OrdreSomme());
					case 2 : 
						return solverAjout.resolution(sac, new OrdreMax());
					default :
						System.out.println("Erreur : choisir 1 ou 2");
				}	
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1 ou 2");
				scan.next();
			}
		}
			
	}
		
	private static List<Objet> afficherMenuResolution(SacADos sac, Scanner scan) {
		while (true) {
			try {
				System.out.println("~~~~~~ MENU ~~~~~~");
				System.out.println("Résolution par methode gloutonne à ajout - Saisir 1");
				System.out.println("Résolution par methode gloutonne à retrait - Saisir 2");
				System.out.println("Résolution par methode Hill Climbing - Saisir 3");
				int choix = scan.nextInt();
				switch (choix) {
					case 1 :
						return afficherMenuOrdre(sac, scan);
					case 2 :
						GloutonRetraitSolver solverRetrait = new GloutonRetraitSolver();
						return solverRetrait.resolution(sac);
					case 3 :
						return afficherMenuHillClimbing(sac, scan);
					default :
						System.out.println("Erreur : choisir 1, 2 ou 3");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1,2 ou 3");
				scan.next();
			}
		}
	}
	
	private static int afficherMenuBudgets(Scanner scan) {
		while (true) {
			try {
				System.out.println("~~~~~~ MENU ~~~~~~");
				System.out.println("Génération du sac à dos selon les couts - Saisir 1");
				System.out.println("Génération du sac à dos selon les secteurs - Saisir 2");
				return scan.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1 ou 2");
				scan.next();
			}
		}
	}
		
	private static List<Objet> afficherMenu(Scanner scan) {
		while (true) {
			try {
				System.out.println("~~~~~~ MENU ~~~~~~");
				System.out.println("Simulation équipe municipale - Saisir 1");
				System.out.println("Générer sac a dos à partir d'un fichier - Saisir 2");
				System.out.println("Quitter - Saisir 3");
				int choix = scan.nextInt();
				switch (choix) {
					case 1 :
						List<Projet> projets = simulationEquipeMunicipale();
						int option = afficherMenuBudgets(scan);
						SacADos sac = genererSacADos(projets, option, scan);
						return afficherMenuResolution(sac, scan);
					case 2 :
						sac = genererDepuisFichier(scan);
						return  afficherMenuResolution(sac, scan);
					case 3 :
						System.out.println("~~~~~~ FIN ~~~~~~");
						return  null;
					default :
						System.out.println("Erreur : choisir 1, 2 ou 3");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("ERREUR : SAISIR 1,2 ou 3");
				scan.next();
			}
		}
	}
}