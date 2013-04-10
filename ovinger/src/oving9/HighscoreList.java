package oving9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HighscoreList extends ObservableList implements Iterable<Comparable> {

	List<Comparable> list;
	int maxSize;
	
	public HighscoreList(int size) {
		//super();
		list = new ArrayList<Comparable>();
		maxSize = size;
	}
	
	public void addResult(Comparable result) {
		int index = 0;
		for (Comparable element : this) {
			if (result.compareTo(element) < 0 ) {
				break;
			}
			index++;
		}
		if (index < maxSize) {
			if (size() >= maxSize) {
				getList().remove(maxSize-1);
			}
			addElement(index, result);
		}
	}
	
	@Override
	public Iterator<Comparable> iterator() {
		return getList().listIterator();
	}

	@Override
	protected List getList() {
		return list;
	}

}
