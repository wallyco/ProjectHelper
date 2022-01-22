package Task;

import java.util.LinkedList;
import java.util.Queue;

//TODO Flesh this out

public class TaskManager<E extends Task> {
	private Queue<Task> listOfTasks = new LinkedList<>();
	
	public TaskManager() { }
	
	public boolean add(Task task) {
		if(!listOfTasks.contains(task) 
				&& listOfTasks.add(task))
			return true;
		return false;
	}
	
	public boolean remove(E task) {
		if(listOfTasks.remove(task))
			return true;
		return false;
	}
	
	//TODO Figure out a better way to handle the queue
	public boolean getNext() {
		Task temp;
		if(listOfTasks.peek() != null) {
			temp = listOfTasks.peek();
			listOfTasks.poll().execute();
			listOfTasks.add(temp);
		}
		return false;
	}
	
	public void clear() {
		listOfTasks.clear();
	}

	@Override
	public String toString() {
		return "TaskManager : " + listOfTasks;
	}
	
	

}
