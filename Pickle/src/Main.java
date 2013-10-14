import java.util.ArrayList;
import java.util.Collections;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Main()).start();

	}

	private void start() {
		// TODO Auto-generated method stub
		// Node root = new Node("a");
		// root.type = NodeType.ROOT;
		// Tree tree = new Tree(root);
		//
		// Node b = new Node("B");
		// Node c = new Node("C");
		// Node s = new Node("S");
		//
		// Node m = new Node("M");
		// tree.getRoot().addChildren(b);
		// tree.getRoot().addChildren(c);
		//
		// b.addChildren(s);
		// b.addChildren(c);
		//
		// c.addChildren(root);
		// s.addChildren(m);

		Node root = new Node("a");
		Tree tree = new Tree(root);
		Node c = new Node("c");
		Node b = new Node("b");
		Node d = new Node("d");

		root.addChildren(c);
		root.addChildren(b);

		b.addChildren(d);
		b.addChildren(c);
		
		d.addChildren(root);

		c.addChildren(root);

		setShared(root);
		//printTree(tree.getRoot(), 0);

		createPostOrderLinearisation(root);

		Collections.reverse(postlin.table);
		postlin.printTable();
		
		System.out.println();
		PickleTable pt = new PickleTable();
		pt.createPickleTable(postlin);
		pt.printPickleTable();
		pt.printPickleTableToFile();
	}

	public int getMaxTiefe(Node t) {
		ArrayList<Node> checkedNodes = new ArrayList<Node>();
		if (t.children.size() > 0) {
			for (int i = 0; i < t.children.size(); i++) {
				if (!checkedNodes.contains(t.children.get(i))) {
					checkedNodes.add(t.children.get(i));
					return 1 + getMaxTiefe(t.children.get(i));
				}
			}
		}
		return 0;
	}

	ArrayList<Node> warschon2 = new ArrayList<Node>();

	public void setShared(Node root) {

		checkShared(root);
		if (!warschon2.contains(root)) {
			warschon2.add(root);
			if (root.getChildren().size() > 0) {
				for (Node n : root.getChildren()) {
					setShared(n);
				}
			}
		}
	}

	public void checkShared(Node n) {
		if (n.type == NodeType.ROOT && n.parents.size() >= 1
				|| (n.type == NodeType.NODE && n.parents.size() >= 2)) {
			n.isShared = true;
		}
	}

	ArrayList<Node> warschon = new ArrayList<Node>();
	ArrayList<Node> warschonPostOrder = new ArrayList<Node>();
	PostOrderLinearisation postlin = new PostOrderLinearisation(null);
	int countShared = 0;

	public void createPostOrderLinearisation(Node root) {
		PostOrderLabel actualLabel = new PostOrderLabel();
		actualLabel.type = PostOrderLabelType.NONE;
		String label;
		int arity;
		int index = 0;
		if (root.isShared) {
			index = countShared++;
			actualLabel.type = PostOrderLabelType.SHARED;
		} else {
			index = -2;

		}
		label = root.data;
		arity = root.getChildren().size();
		if (warschon.contains(root)) {
			label = new Integer(warschon.indexOf(root)).toString();
			actualLabel.refName = root.data;
			index = -1;
			actualLabel.type = PostOrderLabelType.REF;
			arity = 0;
		}

	
		actualLabel.arity = arity;
		actualLabel.label = label;
		actualLabel.index = index;
		postlin.addLabel(actualLabel);
		if (!warschon.contains(root)) {
			if (root.isShared) {
				warschon.add(root);
			}

			if (root.getChildren().size() > 0) {
				for (Node n : root.getChildren()) {
					createPostOrderLinearisation(n);
				}
			}
		}
	}

	public void printTree(Node root, int depth) {

		for (int i = 0; i < depth; i++) {
			so("   ");
		}
		if (warschon.contains(root)) {
			System.out.print("ref:");
		}

		System.out.println("Node " + root.data + " has " + root.children.size()
				+ " children and " + root.getParents().size()
				+ " parents is shared? " + root.isShared);
		so("\n");
		depth++;
		if (!warschon.contains(root)) {
			if (root.isShared) {
				warschon.add(root);
			}
			if (root.getChildren().size() > 0) {
				for (Node n : root.getChildren()) {
					printTree(n, depth);
				}
			}
		}

	}

	public static void so(String s) {
		System.out.print(s);
	}
}
