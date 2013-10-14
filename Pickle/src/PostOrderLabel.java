public class PostOrderLabel {
	int index;
	String label;
	int arity;
	String refName;
	
	PostOrderLabelType type;
	public PostOrderLabel() {
		type = PostOrderLabelType.NONE;
	}
	public PostOrderLabel(int i, String l, int a) {
	
		index = i;
		label = l;
		arity = a;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		switch(type) {
		case NONE:
			sb.append("   ");
			sb.append("|" + label + "   " + arity + "]");
			break;
		case REF:
			sb.append("->"+label);
			sb.append("|   " + arity + "]");
			
			break;
		case SHARED:
			sb.append("  "+index);
			sb.append("|" + label + "   " + arity + "]");
			break;
		}
	
		return sb.toString();
	}
}
