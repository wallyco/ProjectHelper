package Task;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TaskManager<E extends Task> {
	private Queue<Task> listOfTasks = new LinkedList<>();
	private Queue<Task> copy = new LinkedList<>();
	
	public TaskManager() { }
	
	public boolean add(Task task) {
		if(!listOfTasks.contains(task)) {
			listOfTasks.add(task);
			return true;
		}
		return false;
	}
	
	public boolean remove(E task) {
		return listOfTasks.remove(task);
	}
	
	public boolean getNext() {
		if(listOfTasks.peek() != null) {
			listOfTasks.poll();
			return true;
		}
		return false;
	}
	
	public boolean perform() {
		if(listOfTasks.peek() != null
				&& listOfTasks.peek().execute())
			return true;
		getNext();
		return false;
	}
	
	public void clear() {
		listOfTasks.clear();
	}
	
	public void insertAtHead(Task task) {
		if(!listOfTasks.contains(task)) {
			for(Task t : listOfTasks) {
				copy.add(t);
			}
			clear();
			this.add(task);
			for(Task t : copy) {
				listOfTasks.add(t);
			}
			copy.clear();
		}
	
	}
	
	public void offer(Task o) {
		listOfTasks.offer(o);
	}
	
	public int size() {
		return listOfTasks.size();
	}

	@Override
	public String toString() {
		return "TaskManager : " + listOfTasks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(copy, listOfTasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		TaskManager other = (TaskManager) obj;
		return Objects.equals(copy, other.copy) && Objects.equals(listOfTasks, other.listOfTasks);
	}

}
