package arbr_binaires;

import java.math.BigInteger;

public class NoeudBinaire extends Noeud {

	public NoeudBinaire(BigInteger i) {
		super(i);
	}
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	public Noeud getFilsGauche() {
		return filsGauche;
	}
	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}
	public Noeud getFilsDroit() {
		return filsDroit;
	}
	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}
	@Override
	public boolean isLeaf() {
		return false;
	}
	@Override
	public boolean isNoeudUnaire() {
		return false;
	}
	@Override
	public boolean isNoeudBinaire() {
		return true;
	}	
}
