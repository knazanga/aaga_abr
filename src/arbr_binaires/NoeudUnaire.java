package arbr_binaires;

import java.math.BigInteger;

public class NoeudUnaire extends Noeud{
	public NoeudUnaire(BigInteger i) {
		super(i);
	}

	public static final boolean FILS_GAUCHE=true;
	public static final boolean FILS_DrOIT=false;
	
	private boolean isLeft;
	private Noeud fils;
	
	public boolean isLeft() {
		return isLeft;
	}
	
	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
	public Noeud getFils() {
		return fils;
	}
	public void setFils(Noeud fils) {
		this.fils = fils;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isNoeudUnaire() {
		return true;
	}

	@Override
	public boolean isNoeudBinaire() {
		return false;
	}

}
