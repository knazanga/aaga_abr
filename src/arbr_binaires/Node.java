package arbr_binaires;

import java.util.ArrayList;

public class Node {

	Node father;
	long label;
	public Node left;
	public Node right;

	public Node(long label) {
		this.father = null;
		this.label = label;
		left = null;
		right = null;
	}

	public ArrayList<Node> toList(){
		ArrayList<Node> result = new ArrayList<>();

		result.add(this);
		if(left != null){
			result.addAll(left.toList());
		}else{
			result.add(this);
		}
		if(right != null){
			result.addAll(right.toList());
		}else{
			result.add(this);
		}

		return result;
	}

	public int degree() {
		int weight = 1;
		weight += left == null ? 1 : 0;
		weight += right == null ? 1 : 0;
		return weight;
	}
    public void setLeft(Node n){
		this.left = n;
		n.father = this;
	}
    public void setRight(Node n){
		this.right = n;
		n.father = this;
	}

    public boolean hasFather(){ return father != null; }

	public void replaceSon(Node old, Node n){
	    if(old.equals(left))
	        setLeft(n);
	    else if(old.equals(right))
	        setRight(n);
    }

    public Node getRoot() {
        Node n = this;
        while(n.father != null) {
            n = n.father;
            //System.out.println(n);
        }
        return n;
    }

    public int size(){
        int n = 1;
        n+= left==null?0:left.size();
        n+= right==null?0:right.size();
        return n;
    }
    
    public int height(){
    	int l =0, r=0;
    	if(left != null)
    		l = left.height();
    	if(right != null)
    		r = right.height();
    	return Math.max(l, r)+1;
    }
}
