package it.fds.taskmanager.tasksgenerator;

import it.fds.taskmanager.dto.TaskDTO;

/**
 * Define the operations required to generate automatically tasks to schedule 
 * 
 * @author fds
 *
 */
public interface TasksGenerator {

	public TaskDTO generateTask();
}
