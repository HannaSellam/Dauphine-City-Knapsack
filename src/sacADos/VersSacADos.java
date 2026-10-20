package sacADos;

import equipe.Projet;
import equipe.Cout;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Cette classe fournit des méthodes utilitaires pour construire des instances de {@link sacADos.SacADos} 
 * à partir de projets municipaux ou à partir d'un fichier de données.
 * @author Hanna
 */
public class VersSacADos {
	/**
	 * La méthode genererSacADosCouts() construit un sac à dos à partir d'une liste de projets et un tableau de budgets disponibles.
	 * Les budgets concernent les 3 différents types de coûts.
	 * Chaque projet devient un objet du sac à dos.
	 * Le bénéfice du projet devient l'utilité de l'objet et le tableau de coûts du projet devient le tableau de coûts de l'objet.
	 *
	 * @param projets la liste des projets à convertir.
	 * @param budgets le tableau des budgets du sac à dos.
	 * @return le sac à dos correspondant aux projets et aux budgets disponibles.
	 */
	public static SacADos genererSacADosCouts(List<Projet> projets, int[] budgets) {
		List<Objet> objets = new ArrayList<>();
		for (Projet p : projets) {
			Cout c = p.getCouts();
			int[] couts = {c.getCoutEconomique(), c.getCoutSocial(), c.getCoutEnvironnemental()};
			Objet o = new Objet(p.getBenefice(), couts);
			objets.add(o);
		}
		SacADos sac = new SacADos(budgets, objets);
		return sac;
	}
	
	/**
     * La méthode genererSacADosSecteurs() construit un sac à dos à partir d'une liste de projets et un tableau de budgets disponibles.
     * Les budgets concernent les 5 différents secteurs et seul le coût économique est considéré.
     * Chaque projet devient un objet du sac à dos.
     * Le bénéfice du projet devient l'utilité de l'objet et chaque projet possède un tableau de coûts de taille cinq
     * (un par secteur possible). 
     * Seule la composante correspondant au secteur du projet est non nulle.
     *
     * @param projets la liste des projets à convertir.
     * @param budgets le tableau des budgets du sac à dos.
     * @return le sac à dos correspondant aux projets et aux budgets disponibles.
     */
	public static SacADos genererSacADosSecteurs(List<Projet> projets, int[] budgets) {
		List<Objet> objets = new ArrayList<>();
		for (Projet p : projets) {
			Cout c = p.getCouts();
			int[] couts = new int[5];
			couts[p.getSecteur().ordinal()] = c.getCoutEconomique();
			Objet o = new Objet(p.getBenefice(), couts);
			objets.add(o);
		}
		SacADos sac = new SacADos(budgets, objets);
		return sac;
	}
	
	/**
     * La méthode genererSacADosFichier() construit un sac à dos à partir d'un fichier de Benchmark.
     * <p>
     * Le fichier doit contenir le nombre d'objets, le nombre de budgets, la valeur optimale, puis les utilités de chaque objet, les contraintes de cout et enfin les budgets.
     * Les données sont lues, transformées en instances de Objet, puis utilisées pour créer une instance de SacADos.
     *
     * @param fichier le nom/chemin du fichier à lire.
     * @return le sac à dos décrit dans le fichier, ou {@code null} si le fichier est introuvable.
     */
	public static SacADos genererSacADosFichier(String fichier) {
		List<Objet> objets = new ArrayList<>();
		
		try {
			Scanner scan = new Scanner(new File(fichier));
			int n = scan.nextInt();
			int k = scan.nextInt();
			int valOptimale = scan.nextInt();
			
			int[] utilites = new int[n];
			for (int i=0; i<n; i++) {
				utilites[i] = scan.nextInt();
			}
		
			int[][] contraintes = new int[k][n];
			for (int i=0; i<k; i++) {
				for (int j=0; j<n; j++) {
					contraintes[i][j] = scan.nextInt();
				}
			}
			
			int[] budgets = new int[k];
			for (int i=0; i<k; i++) {
				budgets[i] = scan.nextInt();
			}
			
			scan.close();
		
			for (int i=0; i<n; i++) {
				int[] couts = new int[k];
				for (int j=0; j<k; j++) {
					couts[j] = contraintes[j][i];
				}
				Objet o = new Objet(utilites[i], couts);
				objets.add(o);
			}
			
			SacADos sac = new SacADos(budgets, objets);
			return sac;
		
		}
		catch (FileNotFoundException e) {
			System.out.println("Erreur : fichier introuvable");
			return null;
		}
	}
}
