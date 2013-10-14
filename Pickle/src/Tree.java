import java.util.ArrayList;
import java.util.List;

public class Tree {

	private Node root;

	public Tree(Node rootNode) {
		root = rootNode;
	}

	public Node getRoot() {
		return root;
	}

	public static List<Node> getVorgaenger(Tree t, Node n) {
		
		ArrayList<Node> ret = new ArrayList<Node>();
		
		Node actualNode = t.getRoot();
		
		for(Node node : actualNode.getChildren()) {
			if(n==node) {
				if(!ret.contains(node)) {
					ret.add(node);
				} else {
					
				}
			}
		}
		
		return ret;
	}
}
