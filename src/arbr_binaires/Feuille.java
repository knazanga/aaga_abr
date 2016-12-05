package arbr_binaires;

import java.math.BigInteger;

public class Feuille extends Noeud{

	
	public Feuille(BigInteger i) {
		super(i);
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public boolean isNoeudUnaire() {
		return false;
	}

	@Override
	public boolean isNoeudBinaire() {
		return false;
	}


}
