package recursive;

import java.util.ArrayList;
import java.util.List;

public class GeneralTree implements Cloneable {

	int key;
	List<GeneralTree> children;

	public GeneralTree() {
		children = new ArrayList<>();
	}

	public void addChild(GeneralTree t) {
		children.add(t);
	}

	public void setKey(int i) {
		this.key = i;
	}

	public int getKey() {
		return this.key;
	}

	public List<GeneralTree> getChildren() {
		return this.children;
	}

	@Override
	public GeneralTree clone() {
		GeneralTree tree = null;
		try {
			tree = (GeneralTree) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tree;
	}

	@Override
	public String toString() {
		String str = "[." + key + " ";
		if (children.size() != 0) {
			for (GeneralTree t : children) {
				str += t;
			}
		}
		str += " ]";
		return str;
	}

}
