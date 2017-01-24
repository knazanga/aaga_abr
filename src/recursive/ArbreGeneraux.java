package recursive;

import static recursive.Arbre_recursif.factoriel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArbreGeneraux {

	public static BigInteger multiBinomial(List<Integer> coef) {
		BigInteger result = BigInteger.ONE;
		int n = coef.stream().mapToInt(i -> i).sum();
		result = factoriel(n);
		for (Integer i : coef) {
			result = result.divide(factoriel(i));
		}
		return result;
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

	public static BigInteger generalTreeNumber(int n) {
		if (n <= 0) {
			return BigInteger.ZERO;
		} else if (n == 1) {
			return BigInteger.ONE;
		} else {
			Set<List<Integer>> compos = composition(n - 1);
			BigInteger result = BigInteger.ZERO;
			for (List<Integer> l : compos) {
				BigInteger term = BigInteger.ONE;
				for (Integer i : l) {
					term = term.multiply(generalTreeNumber(i));
				}
				term = term.multiply(multiBinomial(l));
				result = result.add(term);
			}
			return result;
		}
	}

	public static void main(String[] args) {
		System.out.println(generalTreeNumber(4));
	}
}
