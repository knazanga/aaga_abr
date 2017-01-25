package recursive;

import static recursive.Arbre_recursif.factoriel;
import static recursive.Arbre_recursif.getRandomBigInteger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

	public static Map<List<Integer>, BigInteger> generalTreeDec(int n) {
		Map<List<Integer>, BigInteger> dec = new HashMap<>();
		Set<List<Integer>> compositions = composition(n - 1);
		BigInteger value;
		for (List<Integer> l : compositions) {
			value = BigInteger.ONE;
			value = value.multiply(multiBinomial(l));
			for (Integer i : l) {
				value = value.multiply(generalTreeNumber(i));
			}
			dec.put(l, value);
		}
		return dec;
	}

	public static List<Integer> decomposition(int n) {
		Map<List<Integer>, BigInteger> dec = generalTreeDec(n);
		BigInteger r = getRandomBigInteger(generalTreeNumber(n));
		List<Integer> decomp = null;
		for (List<Integer> l : dec.keySet()) {
			r = r.subtract(dec.get(l));
			if (r.compareTo(BigInteger.valueOf(0)) <= 0) {
				decomp = l;
				break;
			}
		}

		return decomp;

	}

	public static GeneralTree recursiveGeneralTree(int n) {
		GeneralTree tree = null;
		if (n == 1) {
			tree = new GeneralTree();
		} else if (n == 2) {
			tree = new GeneralTree();
			tree.addChild(new GeneralTree());
		} else {
			tree = new GeneralTree();
			List<Integer> decomp = decomposition(n);
			for (Integer i : decomp) {
				tree.addChild(recursiveGeneralTree(i));
			}
		}
		return tree;
	}

	public static GeneralTree labelTree(GeneralTree tree) {
		if (tree == null) {
			return tree;
		} else {
			int i = 1;
			tree.setKey(i);
			List<GeneralTree> nodes = tree.getChildren();
			while (!nodes.isEmpty()) {
				Random rand = new Random();
				int index = rand.nextInt(nodes.size());
				i++;
				GeneralTree node = nodes.get(index);
				node.setKey(i);
				nodes.addAll(node.getChildren());
				nodes = nodes.stream().filter(n -> n.getKey() == 0).collect(Collectors.toList());
			}

			return tree;
		}
	}

	public static void main(String[] args) {
		// System.out.println(generalTreeNumber(6));
		// System.out.println(generalTreeDec(5));
		GeneralTree tree1 = recursiveGeneralTree(16);
		System.out.println(tree1);
		System.out.println(labelTree(tree1));
	}
}
