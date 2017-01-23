package recursive;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Arbre_recursif {

	public static long combinaison2(int p, int n) throws Exception {
		long result = 1L;
		if (n < 0 || p < 0 || p > n) {
			throw new Exception("Bad values!");
		} else {
			int q = n - p;
			if (p < p) {
				int tmp = p;
				p = q;
				p = tmp;
			}
			for (int i = p + 1; i <= n; i++) {
				result *= i;
			}
			for (int i = 2; i <= q; i++) {
				result /= i;
			}
			return result;
		}
	}

	public static BigInteger binomial(int p, int n) {
		return factoriel(n).divide(factoriel(p).multiply(factoriel(n - p)));
	}

	public static BigInteger getCatNumber(int n) {
		return factoriel(2 * n).divide(factoriel(n).multiply(factoriel(n + 1)));
	}

	public static BigInteger factoriel(int n) {
		BigInteger resultat = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			resultat = resultat.multiply(BigInteger.valueOf(i));
		}
		return resultat;
	}

	public static List<BigInteger> createDec(int n) {
		List<BigInteger> dec = new ArrayList<>();
		dec.add(getCatNumber(n - 1));
		for (int i = 1; i < n - 1; i++) {
			dec.add(getCatNumber(i).multiply(getCatNumber(n - i - 1)));
		}
		dec.add(getCatNumber(n - 1));
		return dec;
	}

	public static List<BigInteger> createDec2(int n) {
		List<BigInteger> dec = new ArrayList<>();
		dec.add(getCatNumber(n - 1));
		for (int i = 1; i < n - 1; i++) {
			dec.add(getCatNumber(i).multiply(getCatNumber(n - i - 1).multiply(binomial(i, n))));
		}
		dec.add(getCatNumber(n - 1));
		return dec;
	}

	public static BigInteger getRandomBigInteger(BigInteger maxvalue) {
		return new BigInteger(maxvalue.bitLength(), new Random()).mod(maxvalue);
	}

	public static int[] decomposition(int n) {
		List<BigInteger> dec = createDec2(n);
		BigInteger r = getRandomBigInteger(getCatNumber(n));
		int i = 0;
		while (r.compareTo(BigInteger.valueOf(0)) > 0) {
			r = r.subtract(dec.get(i));
			i++;
		}
		if (i > 0) {
			i--;
		}
		return new int[] { i, n - i - 1 };
	}

	public static Set<List<Integer>> composition(int n) {

		Set<List<Integer>> compositions = new HashSet<>();
		compositions.add(Arrays.asList(n));
		if (n > 1) {
			for (int i = 1; i < n; i++) {
				compositions.add(Arrays.asList(i, n - i));
				compositions.addAll(produitCroise(composition(i), composition(n - i)));
			}
		}
		return compositions;
	}

	public static <T> Set<List<T>> produitCroise(Set<List<T>> list1, Set<List<T>> list2) {
		Set<List<T>> result = new HashSet<>();
		ArrayList<T> tmp;
		for (List<T> l : list1) {
			for (List<T> l2 : list2) {
				tmp = new ArrayList<>();
				tmp.addAll(l);
				tmp.addAll(l2);
				result.add(tmp);
			}
		}
		return result;
	}

	public static Noeud recursiveTree(int n) {
		if (n == 0) {
			return new Feuille();
		} else if (n == 1) {
			return new NoeudBinaire(new Feuille(), new Feuille());
		} else {
			int[] composants = decomposition(n);
			if (composants[0] == 0) {
				return new NoeudBinaire(recursiveTree(n - 1), new Feuille());
			} else if (composants[0] == n - 1) {
				return new NoeudBinaire(new Feuille(), recursiveTree(n - 1));
			} else {
				return new NoeudBinaire(recursiveTree(composants[0]), recursiveTree(composants[1]));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Set<List<Integer>> compo = composition(6);

		for (List<Integer> l : compo) {
			System.out.print("[");
			for (Integer i : l) {
				System.out.print(i + " ");
			}
			System.out.print("]\n");
		}
	}
}