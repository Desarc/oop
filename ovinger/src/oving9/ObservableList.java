package oving9;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableList {

	private ArrayList<ListListener> listeners;
	
	public ObservableList() {
		listeners = new ArrayList<ListListener>();
	}
	
	
	public void addListListener(ListListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeListListener(ListListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
	
	protected void fireListChanged(int lowIndex, int highIndex) {
		for (ListListener listener : listeners) {
			listener.listChanged(this, lowIndex, highIndex);
		}
	}
	
	protected abstract List getList();
	
	public int size() {
		return getList().size();	
	}
	
	protected void addElement(int index, Object element) {
		getList().add(index, element);
		fireListChanged(index, size()-1);
	}
	
	protected void removeElement(int index) {
		if (size() > index && index >= 0) {
			getList().remove(index);
			fireListChanged(index, size());
		}
	}
	
	

}
