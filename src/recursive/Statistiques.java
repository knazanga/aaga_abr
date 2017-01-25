package recursive;

public class Statistiques {

	public static int profondeur(Noeud arbre){

		int profFin = 0;
		
		if(arbre instanceof NoeudBinaire)
			profFin = profondeur((NoeudBinaire)arbre);
		if(arbre instanceof Feuille)
			profFin = 1;

		return profFin;
	}

	public static int profondeur(NoeudBinaire arbre){
		if(arbre.getGauche().toString().equals("f"))
			return (1+profondeur(arbre.getDroite()));
		
		if(arbre.getDroite().toString().equals("f"))
			return (1+profondeur(arbre.getGauche()));

		
		if((arbre.getGauche().toString().equals("f"))&&((arbre.getDroite().toString().equals("f")))){
			return 1;
		}
		
		else
			return (1+ (Math.max(profondeur(arbre.getGauche()), profondeur(arbre.getDroite()))));
	}
	
	public static int largeurArbre(Noeud arbre){
		int largeur = 0;
		
		if(arbre instanceof NoeudBinaire)
			largeur = largeurArbreBin((NoeudBinaire)arbre);
		if(arbre instanceof Feuille)
			largeur = 1;
		
		return largeur;
	}
	
	public static int largeurArbreBin(NoeudBinaire arbre){
		int nbGauche = 0;
		int nbDroite = 0;
//		if(arbre.getGauche().toString().equals("f") && (arbre.getDroite().toString().equals("f")))
//			return 2;
//		
//		else {
			if(!arbre.getGauche().toString().equals("f"))
				nbGauche = (1 + largeurGauche((NoeudBinaire)arbre.getGauche()));
			if(!arbre.getDroite().toString().equals("f"))
				nbDroite = (1 + largeurDroite((NoeudBinaire)arbre.getDroite()));
		//}
			
				
		return(nbGauche + nbDroite);
	}
	
	public static int largeurGauche(NoeudBinaire gauche){
		if(gauche.getGauche().toString().equals("f"))
			return 1;
		else
			return (1+largeurGauche((NoeudBinaire)gauche.getGauche()));
	}

	public static int largeurDroite(NoeudBinaire droite){
		if(droite.getDroite().toString().equals("f"))
			return 1;
		else
			return (1+largeurDroite((NoeudBinaire)droite.getDroite()));
	}
	
	

	public static void main(String[] args) {
		Noeud arbre = Arbre_recursif.recursiveTree(28, 2);
		System.out.println(arbre);
		int profondeur = profondeur(arbre);
		System.out.println("profondeur arbre = "+profondeur);
		
		int largeurArbre = largeurArbre(arbre);
		System.out.println("largeur de l'arbre : "+largeurArbre);
	}

}
