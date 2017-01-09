package arbr_binaires;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Remy {
    private static int aleaBitCounter = 0;

	public static void main( String[] args ) {
        for(int i = 10; i< 1000; i+=10) {
            int total = 0;
            for(int j = 0; j<100; j++) {
                Node n = remy(i);
                total+=aleaBitCounter;
                aleaBitCounter=0;
            }
            System.out.println(i+" "+(total/100));
        }

      //  new TreePrinter().printTree(n);

	}

	public static Node remy (long n) {
		long label = 1;
		Node tree = new Node(label );

		ArrayList<Node> nodes = new ArrayList<>();
		nodes.add(tree);
		nodes.add(tree);
		nodes.add(tree);

		for(long i = 1; i<n;i++) {
            Node theChoosen = nodes.get(randInt(nodes.size()));

            Node newNode = new Node(i + 1);
            switch (theChoosen.degree()) {
                case 3:

                    switch (randInt(3)) {
                        case 0:
                            replaceFather(theChoosen, newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            break;
                        case 1:
                            theChoosen.setLeft(newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.remove(theChoosen);
                            break;
                        default:
                            theChoosen.setRight(newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.remove(theChoosen);
                    }
                    break;
                case 2:
                    switch (randInt(2)) {
                        case 0:
                            replaceFather(theChoosen, newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            break;
                        default:
                            if (theChoosen.left == null) {
                                theChoosen.setLeft(newNode);
                            }else {
                                theChoosen.setRight(newNode);
                            }
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.add(newNode);
                            nodes.remove(theChoosen);
                    }
                    break;
                case 1:
                    replaceFather(theChoosen, newNode);
                    nodes.add(newNode);
                    nodes.add(newNode);
                    break;
            }
        }
		return tree.getRoot();
	}

    private static void replaceFather(Node theChoosen, Node newNode) {
        if(theChoosen.hasFather())
            theChoosen.father.replaceSon(theChoosen,newNode);
        if(randInt(2) == 0){
            newNode.setLeft(theChoosen);
        }else{
            newNode.setRight(theChoosen);
        }
    }

    private static Integer randInt(long n) {
		Random rand = new Random();
		int size =(int)Math.ceil(Math.log(n)/Math.log(2));
		int val = 0;
		for(int i=0; i< size;i++){
		    aleaBitCounter++;
			val <<= 1;
			if(rand.nextBoolean())
				val ^= 1;
		}
		return (val < n) ? val : randInt(n);	
	}

}
