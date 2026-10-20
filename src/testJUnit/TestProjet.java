package testJUnit;

import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.OrdreMax;
import solveur.glouton.OrdreSomme;
import solveur.glouton.OrdreRetrait;
import solveur.glouton.GloutonRetraitSolver;
import solveur.hillclimbing.Voisin;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestProjet {

	@Test
	public void calculUtiliteSolution_deuxObjets_SommeUtilitesObjetsSolution() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 30});
		Objet o2 = new Objet(200, new int[] {70, 60});
		List<Objet> listeTest = new ArrayList<>();
		listeTest.add(o1);
		listeTest.add(o2);
		Voisin voisinTest = new Voisin(listeTest);
		
		//Act
		int utilite = voisinTest.calculUtiliteSolution();
		
		//Assert
		assertEquals(300, utilite);
	}
	
	@Test
	public void calculUtiliteSolution_aucunObjet_Zero() {
		//Arrange
		List<Objet> listeTest = new ArrayList<>();
		Voisin voisinTest = new Voisin(listeTest);
		
		//Act
		int utilite = voisinTest.calculUtiliteSolution();
		
		//Assert
		assertEquals(0, utilite);
	}
	
	@Test
	public void compare_Objet1AvecfmaxSuperieur_entierNegatif() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 30});
		Objet o2 = new Objet(200, new int[] {150, 60});
		OrdreMax ordre = new OrdreMax();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(-1, res);
	}
	
	@Test
	public void compare_deuxObjetsFmaxEgalite_Zero() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 50});
		Objet o2 = new Objet(200, new int[] {100, 100});
		OrdreMax ordre = new OrdreMax();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(0, res);
	}
	
	@Test
	public void compare_Objet2FmaxSuperieur_entierPositif() {
		//Arrange
		Objet o1 = new Objet(50, new int[] {50, 50});
		Objet o2 = new Objet(100, new int[] {50, 50});
		OrdreMax ordre = new OrdreMax();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(1, res);
	}
	
	@Test
	public void compare_Objet1FsumSuperieur_entierNegatif() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 30});
		Objet o2 = new Objet(200, new int[] {150, 60});
		OrdreSomme ordre = new OrdreSomme();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(-1, res);
	}
	
	@Test
	public void compare_deuxObjetsFsumEgalite_Zero() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 50});
		Objet o2 = new Objet(200, new int[] {100, 100});
		OrdreSomme ordre = new OrdreSomme();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(0, res);
	}
	
	@Test
	public void compare_Objet2AvecFsumSuperieur_entierPositif() {
		//Arrange
		Objet o1 = new Objet(50, new int[] {50, 50});
		Objet o2 = new Objet(100, new int[] {50, 50});
		OrdreSomme ordre = new OrdreSomme();
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(1, res);
	}
	
	@Test
	public void compare_Objet1FmvSuperieur_entierNegatif() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 25, 10});
		Objet o2 = new Objet(60, new int[] {50, 20, 10});
		ArrayList<Integer> L = new ArrayList<>();
		L.add(1);
		L.add(2);
		OrdreRetrait ordre = new OrdreRetrait(L);
				
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(-1, res);
	}
	
	@Test
	public void compare_deuxObjetsFmvEgalite_Zero() {
		//Arrange
		Objet o1 = new Objet(100, new int[] {50, 10, 25});
		Objet o2 = new Objet(80, new int[] {40, 10, 20});
		ArrayList<Integer> L = new ArrayList<>();
		L.add(0);
		L.add(2);
		OrdreRetrait ordre = new OrdreRetrait(L);
						
		//Act
		int res = ordre.compare(o1, o2);
						
		//Assert
		assertEquals(0, res);
	}
	
	@Test
	public void compare_Objet2FmvSuperieur_entierPositif() {
		//Arrange
		Objet o1 = new Objet(60, new int[] {60, 20, 10});
		Objet o2 = new Objet(120, new int[] {30, 20, 10});
		ArrayList<Integer> L = new ArrayList<>();
		L.add(0);
		OrdreRetrait ordre = new OrdreRetrait(L);
		
		//Act
		int res = ordre.compare(o1, o2);
				
		//Assert
		assertEquals(1, res);
	}
	
	@Test
	public void calculerL_DeuxDimensions_listeDimensionsDepassees() {
		//Arrange
		Objet o1 = new Objet(10, new int[] {40, 30, 20});
		Objet o2 = new Objet(10, new int[] {35, 30, 55});
		List<Objet> solutionTest = new ArrayList<>();
		solutionTest.add(o1);
		solutionTest.add(o2);
		int[] budgetsTest = {50, 50, 50};
		SacADos sac = new SacADos(budgetsTest, solutionTest);
		GloutonRetraitSolver solverRetrait = new GloutonRetraitSolver();
		ArrayList<Integer> listeAttendue = new ArrayList<>();
		listeAttendue.add(0);
		listeAttendue.add(2);
		
		//Act
		ArrayList<Integer> listeResultat = solverRetrait.calculerL(sac, solutionTest);
				
		//Assert
		assertEquals(listeAttendue, listeResultat);
	}
	
	@Test
	public void ajoutPossible_objetPeutEtreAjoute_true() {
		//Arrange
		Objet o1 = new Objet(10, new int[] {20, 30, 15});
		List<Objet> listeTest = new ArrayList<>();
		listeTest.add(o1);
		int[] budgetsTest = {50, 50, 20};
		SacADos sac = new SacADos(budgetsTest, listeTest);
				
		//Act
		boolean res = sac.ajoutPossible(o1, budgetsTest);
				
		//Assert
		assertEquals(true, res);
	}
	
	@Test
	public void ajoutPossible_AjoutImpossible_false() {
		//Arrange
		Objet o1 = new Objet(10, new int[] {20, 30, 25});
		List<Objet> listeTest = new ArrayList<>();
		listeTest.add(o1);
		int[] budgetsTest = {50, 50, 20};
		SacADos sac = new SacADos(budgetsTest, listeTest);
				
		//Act
		boolean res = sac.ajoutPossible(o1, budgetsTest);
				
		//Assert
		assertEquals(false, res);
	}
}
