import java.util.ArrayList;
import java.util.LinkedList;

public class PickleTable {

	LinkedList<PickleLabel> table;

	public PickleTable() {
		table = new LinkedList<PickleLabel>();

	}
	
	public void printPickleTable()  {
		
		for(PickleLabel l : table) {
			System.out.println(l.toString());
		}
	}
	public void printPickleTableToFile()  {
		
		for(PickleLabel l : table) {
			System.out.println(l.printedString());
		}
	}
	public void createPickleTable(PostOrderLinearisation lin) {
		ArrayList<Integer> refWar = new ArrayList<Integer>();
		ArrayList<Integer> indexWar = new ArrayList<Integer>();
		for(PostOrderLabel actualLabel : lin.table) {
			PickleLabel newLabel = new PickleLabel();
			switch(actualLabel.type) {
			case NONE:
				newLabel.arity = actualLabel.arity;
				newLabel.label = actualLabel.label;
				newLabel.key = "";
				newLabel.index = -1;
				break;
			case REF:
				if(!indexWar.contains(Integer.valueOf(actualLabel.label))) {
					//promissen
					newLabel.arity=actualLabel.arity;
					newLabel.key = "PROMISE";
					newLabel.index = Integer.valueOf(actualLabel.label);
					newLabel.label = actualLabel.refName;
					indexWar.add(Integer.valueOf(actualLabel.label));
					break;
				} else {
					newLabel.arity=actualLabel.arity;;
					newLabel.key = "LOAD";
					newLabel.index =Integer.valueOf(actualLabel.label);
					newLabel.label = actualLabel.label;
				}
				if(refWar.contains(actualLabel.index)) {
					newLabel.arity=actualLabel.arity;;
					newLabel.key = "LOAD";
					newLabel.index = actualLabel.index;
					newLabel.label = actualLabel.label;
					break;
				}
				break;
			case SHARED:

				if(indexWar.contains(actualLabel.index)) {
					newLabel.arity=actualLabel.arity;;
					newLabel.key = "FULFILL";
					newLabel.index = actualLabel.index;
					newLabel.label = actualLabel.label;
					break;
				}
				if(!refWar.contains(actualLabel.index)) {
					PickleLabel pl = new PickleLabel();
					pl.arity = actualLabel.arity;
					pl.key = actualLabel.label;
					table.add(pl);
					newLabel.key = "STORE";
					newLabel.index = actualLabel.index;
					refWar.add(actualLabel.index);
					break;
				}
				
				
				break;
			
			}
			table.add(newLabel);
			
		}
	}
}
