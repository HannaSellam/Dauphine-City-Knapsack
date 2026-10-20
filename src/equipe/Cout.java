package equipe;

/** 
 * Cette classe représente le coût d'un projet municipal.
 * Un coût est décrit par trois composantes : économique, social et environnementale.
 * @author Hanna
 */
public class Cout {
	/**
     * Valeur minimale pour un coût (en euros).
     * Cette constante est utilisée pour borner la génération aléatoire.
     */
	public static final int COUT_MIN = 50000;
	
	/**
     * Valeur maximale pour un coût (en euros).
     * Cette constante est utilisée pour borner la génération aléatoire.
     */
	public static final int COUT_MAX = 800000;
	
	private int coutEconomique;
	private int coutSocial;
	private int coutEnvironnemental;
	
	/**
	 * Définit le coût économique du projet.
	 * 
	 * @param cout coût économique en euros.
	 */
	public void setCoutEconomique(int cout) {
		this.coutEconomique = cout;
	}
	
	/**
	 * Définit le coût social du projet.
	 * 
	 * @param cout coût social en euros.
	 */
	public void setCoutSocial(int cout) {
		this.coutSocial = cout;
	}
	
	/**
	 *  Définit le coût environnemental du projet.
	 *  
	 * @param cout coût environnemental en euros.
	 */
	public void setCoutEnvironnemental(int cout) {
		this.coutEnvironnemental = cout;
	}
	
	/**
     * Retourne le coût économique du projet.
     *
     * @return le coût économique du projet en euros.
     */
	public int getCoutEconomique() {
		return coutEconomique;
	}
	
	/**
	 * Retourne le coût social du projet.
	 * 
	 * @return le coût social du projet en euros.
	 */
	public int getCoutSocial() {
		return coutSocial;
	}
	
	/**
     * Retourne le coût environnemental du projet.
     *
     * @return le coût environnemental du projet en euros.
     */
	public int getCoutEnvironnemental() {
		return coutEnvironnemental;
	}
}
