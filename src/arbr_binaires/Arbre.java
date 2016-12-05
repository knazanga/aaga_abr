package arbr_binaires;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public abstract class Arbre {
	Set<Noeud> noeuds = new HashSet<Noeud>();

	public Set<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(Set<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	public static Arbre insertNoeud(Arbre tree, BigInteger key) {
		BigInteger tp = ArbreUtils.randInt(BigInteger.valueOf(6));
		if (tp.compareTo(BigInteger.valueOf(2)) > 0) {
			return insertAFeuille(tree, key);
		} else if (tp.compareTo(BigInteger.ONE) > 0)
			return insertANoeudUnaire(tree, key);
		else
			return insertANoeudBinaire(tree, key);
	}

	public static Arbre insertANoeudBinaire(Arbre tree, BigInteger key) {
		System.out.println("Insertion à un noeud Binaire");
		System.out.println("Insertion comme père");
		return null;
	}

	public static Arbre insertANoeudUnaire(Arbre tree, BigInteger key) {
		System.out.println("Insertion à un noeud unaire");
		if(ArbreUtils.randomBoolean()){
			System.out.println("Insertion au fils vide");
		}else{
			System.out.println("Insertion comme un père");
		}
		return null;
	}

	public static Arbre insertAFeuille(Arbre tree, BigInteger key) {
		System.out.println("Insertion à une feuille");
			BigInteger where = ArbreUtils.randInt(BigInteger.valueOf(3));
			if (where.equals(BigInteger.ZERO)) {
				System.out.println("Insertion en fils gauche");
			} else if (where.equals(BigInteger.ONE)) {
				System.out.println("Insertion en fils droit");
			} else {
				System.out.println("Insertion comme père");
			}
			return null;
	}

	public static Arbre construireArbre(BigInteger taille) {
		BigInteger iteration = BigInteger.ONE;
		Noeud noeud = new Feuille(iteration);
		Arbre tree = noeud;
		while (!iteration.equals(taille)) {
			iteration = iteration.add(BigInteger.ONE);
			tree = insertNoeud(tree, iteration);
		}
		return tree;
	}
}
