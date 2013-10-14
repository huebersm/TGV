import java.util.LinkedList;

public class PostOrderLinearisation {
	LinkedList<PostOrderLabel> table;

	public PostOrderLinearisation(Node root) {
		table = new LinkedList<PostOrderLabel>();

	}

	public void addLabel(PostOrderLabel label) {
		table.add(label);

	}
	public void printTable() {
		for(PostOrderLabel l : table) {
			System.out.println(l.toString());
		}
	}
}
