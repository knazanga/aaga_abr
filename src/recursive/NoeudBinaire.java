package recursive;

public class NoeudBinaire extends Noeud {

	protected Noeud gauche;
	protected Noeud droite;

	public NoeudBinaire(Noeud gch, Noeud drt) {
		this.gauche = gch;
		this.droite = drt;
	}

	@Override
	public String toString() {
		return "(ni, " + gauche + ", " + droite + ")";
	}

	public Noeud getGauche() {
		return gauche;
	}

	public void setGauche(Noeud gauche) {
		this.gauche = gauche;
	}

	public Noeud getDroite() {
		return droite;
	}

	public void setDroite(Noeud droite) {
		this.droite = droite;
	}
}
