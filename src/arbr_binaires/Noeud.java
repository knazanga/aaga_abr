package arbr_binaires;

import java.math.BigInteger;

public abstract class Noeud extends Arbre{
	private BigInteger key;
	
	public Noeud(BigInteger i){
		this.key=i;
	}
	
	
	public BigInteger getKey() {
		return key;
	}



	public void setKey(BigInteger key) {
		this.key = key;
	}



	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof Noeud))
			return false;
		Noeud nd=(Noeud) obj;
		if(this.key.compareTo(nd.getKey())!=0)
			return false;
		return true;
	}
	
	public abstract boolean isLeaf();
	
	public abstract boolean isNoeudUnaire();
	
	public abstract boolean isNoeudBinaire();
}
