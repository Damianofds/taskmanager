package it.fds.taskmanager.tasksgenerator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.fds.taskmanager.TaskState;
import it.fds.taskmanager.dto.TaskDTO;

/**
 * Implements a very basic task generator useful for testing.
 * All the task properties are generated choosing among:
 * <ol>
 * 	<li><strong>for text fields</strong> randomly from an hardcoded list of titles and descriptions</li>
 * 	<li><strong>for date fields</strong> randomly around the current date</li>
 * </ol> 
 *  
 * @author fds
 *
 */
@Component
public class BasicTasksGenerator implements TasksGenerator{

	private static Map<Integer,String> titles;
	private static Map<Integer,String> descriptions;
	
	static{
		titles = new HashMap<>();
		titles.put(0, "buy stationary");
		titles.put(1, "buy food");
		titles.put(2, "wash clothes");
		titles.put(3, "have fun");
		titles.put(4, "pay bills");
		titles.put(5, "fix website");
		titles.put(6, "listen that new album");
		titles.put(7, "watch that film");
		titles.put(8, "relax");
		titles.put(9, "new book");
		
		descriptions = new HashMap<>();
		descriptions.put(0, "it's never enough");
		descriptions.put(1, "apples banana cereals eggs");
		descriptions.put(2, "tshirt and the sweather");
		descriptions.put(3, "go to that concert");
		descriptions.put(4, "gas luce acqua ASDL");
		descriptions.put(5, "which one?");
		descriptions.put(6, "populous... when is the album out?");
		descriptions.put(7, "I hope soon as possible");
		descriptions.put(8, "yeah");
		descriptions.put(9, "find something new to read");
		
	}
	
	@Override
	public TaskDTO generateTask() {
		TaskDTO task = new TaskDTO();
		int index = getIntBetween0and10();
		int priority = getIntBetween0and10()/3;
		task.setTitle(BasicTasksGenerator.titles.get(index));
		task.setDescription(BasicTasksGenerator.descriptions.get(index));
		
		task.setCreatedat(getRandomPastDate());
		task.setDuedate(getRandomFutureDate());
		task.setPostponedat(getRandomFutureDate());
		task.setPriority(""+priority);
		task.setUpdatedat(getNowDate());
		task.setPostponedtime(null);
		task.setResolvedat(null);
		task.setStatus(TaskState.NEW.toString().toUpperCase());

		return task;
	}
	
	protected Calendar getRandomPastDate(){
		return getRandomDate(true);
	}
	
	protected Calendar getRandomFutureDate(){
		return getRandomDate(false);
	}
	
	protected Calendar getNowDate(){
		return Calendar.getInstance();
	}
	
	protected Calendar getRandomDate(boolean isPast){
		int days = getIntBetween0and10();
		int index = (isPast)? -1*days:days;
		Calendar out = getNowDate();
		out.add(Calendar.DAY_OF_MONTH, index);
		return out;
	}
	
	private int getIntBetween0and10(){
		return (int) (Math.random()*10);
	}
}
