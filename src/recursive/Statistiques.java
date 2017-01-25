package recursive;

import java.util.LinkedList;
import java.util.List;

public class Statistiques {

	public static int profondeur(Noeud arbre){

		int profFin = 0;
		
		if(arbre instanceof NoeudBinaire)
			profFin = profondeur((NoeudBinaire)arbre);
		if(arbre instanceof Feuille)
			profFin = 1;

		return profFin;
	}
	
	public static int profondeurGenTree(GeneralTree tree){
		int profFin = 0;
		
		if(tree.getChildren().isEmpty())
			return 1;
		else {
			List<GeneralTree> l = tree.getChildren();
			List<Integer> prof = new LinkedList<>();
			for(GeneralTree gt : l){
				profFin = (1+profGenTreeChild(gt));
				prof.add(profFin);
			}
			
			int base = 0;
			for(Integer v : prof){
				base = Math.max(base, v);
			}
			return base;
		}
		
	}
	
	public static int profGenTreeChild(GeneralTree t){
		List<GeneralTree> liste = t.getChildren();
		
		if(t.getChildren().isEmpty())
			return 1;
		
		else{
			List<Integer> prof = new LinkedList<>();
			int v = 0;
			for(GeneralTree gt : liste){
				if(!gt.getChildren().isEmpty())
					v = (1+profGenTreeChild(gt));
				else
					v = 1;
				prof.add(v);
			}
			
			int base = 0;
			for(Integer val : prof)
				base = Math.max(base, val);
			
			return base;
			
		}
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
		if(arbre.getGauche().toString().equals("f") && (arbre.getDroite().toString().equals("f")))
			return 2;
		
		else {
			if(!arbre.getGauche().toString().equals("f"))
				nbGauche = (1 + largeurGauche((NoeudBinaire)arbre.getGauche()));
			if(!arbre.getDroite().toString().equals("f"))
				nbDroite = (1 + largeurDroite((NoeudBinaire)arbre.getDroite()));
		}
			
				
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
	
	public static int largeurGenTree(GeneralTree tree){
		if(tree.getChildren().isEmpty())
			return 1;
		else{
			GeneralTree first = tree.getChildren().get(0);
			GeneralTree last = tree.getChildren().get(tree.getChildren().size()-1);
			
			return (1+largeurLeftGenTree(first)+largeurRightGenTree(last));
		}
	}
	
	public static int largeurLeftGenTree(GeneralTree left){
		if(left.getChildren().isEmpty())
			return 1;
		else{
			return (1+largeurLeftGenTree(left.getChildren().get(0)));
		}
	}
	
	public static int largeurRightGenTree(GeneralTree right){
		if(right.getChildren().isEmpty())
			return 1;
		else{
			return (1+largeurRightGenTree(right.getChildren().get(right.getChildren().size()-1)));
		}
	}
	
	

	public static void main(String[] args) {
		long before = System.currentTimeMillis();
		Noeud arbre = Arbre_recursif.recursiveTree(40, 2);
		long after = System.currentTimeMillis();
		System.out.println("Execution time : "+(after-before)+" ms ("+((after-before)/1000.0)+" s)");
		System.out.println(arbre);
		int profondeur = profondeur(arbre);
		System.out.println("profondeur arbre -> "+profondeur);
		
		int largeurArbre = largeurArbre(arbre);
		System.out.println("largeur de l'arbre -> "+largeurArbre);
		System.out.println();
		GeneralTree tree1 = ArbreGeneraux.recursiveGeneralTree(16);
		int prof = profondeurGenTree(tree1);
		System.out.println(tree1);
		System.out.println("profondeur arbre général -> "+prof);
		System.out.println("largeur arbre général ->"+largeurGenTree(tree1));
	}

}
