package equipe;

import java.util.Random;

/**
 * Cette classe représente un expert spécialisé dans un ou plusieurs secteurs d'activité. 
 * Il peut proposer des projets dans ses secteurs de compétence.
 * @author Tana
 */
public class Expert extends Personne implements InitiateurProjet {
	private Secteur[] listeSecteurs;
	private static int idProjet = 0;
	private static Random rand = new Random();
	
	/**
     * Crée un expert avec son identité et initialise aléatoirement sa liste de secteurs de compétence.
     *
     * @param nom    nom de famille de l'expert.
     * @param prenom prénom de l'expert.
     * @param age    âge de l'expert.
     */
	public Expert(String nom, String prenom, int age) {
		super(nom, prenom, age);
		this.listeSecteurs = choixListeSecteur();
	}
	
	/*Détermine aléatoirement la liste des secteurs dans lesquels l'expert est compétent.
	Le nombre de secteurs est choisi aléatoirement entre 1 et le nombre total de secteurs existants.
	Chaque secteur apparaît au plus une fois dans le tableau.*/
	private Secteur[] choixListeSecteur() {
	    int nbSecteurs = rand.nextInt(Secteur.values().length)+1;
	    Secteur[] liste = new Secteur[nbSecteurs];
	    Secteur secteur;
	    boolean doublon;
	    for (int i=0; i<nbSecteurs; i++) {
	    	do {
	    		doublon = false;
	    		int index = rand.nextInt(Secteur.values().length);
	    		secteur = Secteur.values()[index];
	    		for (int j=0; j<i; j++) {
	    			if (liste[j]==secteur) {
	    				doublon = true;
	    				break;
	    			}
	    		}
	    	} while (doublon);
	    	liste[i] = secteur;
	    }
	    return liste;
	}
	
	//Choisit aléatoirement le secteur du projet proposé par l'expert parmi ses secteurs d'expertise.
	private Secteur choixSecteurProjet() {
		int index = rand.nextInt(listeSecteurs.length);
		return listeSecteurs[index];
	}
	
	/**
     * Le titre du projet est construit à partir d'un identifiant incrémenté automatiquement.
	 */
	@Override
	public Projet proposerProjet() {
		idProjet++;
		String titre = "Projet numero " + idProjet;
	    Secteur secteur = choixSecteurProjet();
		String description = "Projet secteur :" + secteur;
		Projet p = new Projet(titre, description, secteur);
		return p;
		
	}

}


