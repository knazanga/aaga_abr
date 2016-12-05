package arbr_binaires;

import java.math.BigInteger;
import java.util.Random;

public class ArbreUtils {
	private static Random random = new Random();

	public static boolean randomBoolean() {
		return random.nextBoolean();
	}

	public static int bitNumber(BigInteger n) {
		return n.bitCount();
	}

	public static BigInteger randInt(BigInteger n) {
		int nb = bitNumber(n);
		BigInteger value;
		do {
			int bits = nb;
			value = BigInteger.ZERO;
			while (bits >= 0) {
				if (randomBoolean()) {
					long p = (long) Math.pow(2, bits);
					value = value.add(BigInteger.valueOf(p));
				}
				bits--;
			}
		} while (value.compareTo(n) != -1);
		return value;
	}
}
