import java.util.ArrayList;
import java.util.List;

public class Node {
	String data;
	List<Node> children;
	List<Node> parents;
	boolean isShared=false;
	
	NodeType type;
	
	public Node(String label) {
		data = label;
		children = new ArrayList<Node>();
		parents = new ArrayList<Node>();
		
		type = NodeType.NODE;
	}
	public List<Node> getChildren() {
		return children;
	}

	public void addChildren(Node node) {
		children.add(node);
		node.addParent(this);
	}
	public void addParent(Node node) {
		parents.add(node);
	}
	public List<Node> getParents() {
		return parents;
	}


}