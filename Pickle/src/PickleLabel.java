public class PickleLabel {

	String key;
	int index;
	String label;
	int arity;

	public PickleLabel() {

	}

	@Override
	public String toString() {
		return "[" + key + "|" + index + "|" + label + "|" + arity+"]";
	}
	
	public String printedString() {
		StringBuilder sb = new StringBuilder();
		
		if(key.compareTo("PROMISE")==0 ||
				key.compareTo("FULFILL")==0 
				) {
			sb.append(key);
			sb.append(" ");
			sb.append(String.valueOf(index));
			sb.append(" ");
			sb.append(label);
			sb.append("\t");
			sb.append(String.valueOf(arity)+"\n");
		} else {
			if(key.compareTo("")==0) { //NORMALER KNOTEN
				sb.append(label);
				sb.append("\t");
				sb.append(String.valueOf(arity)+"\n");
			}
			else {
				sb.append(key);
				sb.append(" ");
				sb.append(String.valueOf(index));
				sb.append("\t-\n");
			}
		}
		
		return sb.toString();
	}

}
