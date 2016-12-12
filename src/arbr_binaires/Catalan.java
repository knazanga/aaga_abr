package arbr_binaires;

import java.util.Random;

public class Catalan {

	protected static int catalan(int n) {
        int res = 0;
         
        if (n <= 1) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - i - 1);
        }
        return res;
    }
	
	public int getCatalan(int n){
		return catalan(n);
	}
	
	public static void main(String[] args) {

		int val = 9;
		int cat = catalan(val);
		
		System.out.println("catalan "+val+ " = "+cat);
		
		Random selection = new Random();
		int selec = selection.nextInt(cat);
		
		System.out.println("Generation de l'arbre n°"+selec+" de taille "+val);
		
		
		
	}

}
