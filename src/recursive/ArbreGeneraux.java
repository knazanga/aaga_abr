package recursive;

import static recursive.Arbre_recursif.factoriel;
import static recursive.Arbre_recursif.getRandomBigInteger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
			List<GeneralTree> nodes = tree.getChildren().stream().filter(t -> t.getKey() == 0)
					.collect(Collectors.toList());
			while(!nodes.isEmpty()){
				Random rand = new Random(System.currentTimeMillis());
				int index = rand.nextInt(nodes.size());
				GeneralTree node = nodes.get(index);
				if (node.getKey() == 0) {
					i++;
					node.setKey(i);
					nodes.addAll(node.getChildren());
				}
				nodes = nodes.stream().filter(n -> n.getKey() == 0).collect(Collectors.toList());
			}
			return tree;
		}
	}

	public static String output(GeneralTree tree) {
		String str = "";
		if (tree != null) {
			if (!tree.getChildren().isEmpty()) {
				str += tree.getKey() + "-- { ";
				for (GeneralTree t : tree.getChildren()) {
					str += t.getKey() + " ";
				}
				str += " }\n";

				for (GeneralTree t : tree.getChildren()) {
					str += output(t);
				}
			}
		}
		return str;
	}

	public static void printTree(GeneralTree t) {
		System.out.print(t.getKey() + ":");

		for (GeneralTree i : t.getChildren()) {
			System.out.print(i.getKey() + " ");
		}
		System.out.print("\n");
		for (GeneralTree i : t.getChildren()) {
			if (!i.getChildren().isEmpty())
				printTree(i);
		}
	}

	public static void outputOnDotFile(String file, GeneralTree tree) {
		try {
			FileWriter fw = new FileWriter(new File(file));
			fw.write("strict graph{\n\t");
			fw.write(output(tree));
			fw.write("\n}");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		 //System.out.println(generalTreeNumber(4));
		// System.out.println(generalTreeDec(5));
		int i = 15;
		GeneralTree tree = recursiveGeneralTree(i);
		System.out.println(tree);
		tree = labelTree(tree);
		System.out.println(tree);
		outputOnDotFile("graph"+i+".dot", tree);
	}
}
