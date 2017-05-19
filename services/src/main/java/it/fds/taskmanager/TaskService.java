package it.fds.taskmanager;

import java.util.List;
import java.util.UUID;

import it.fds.taskmanager.dto.TaskDTO;

/**
 * Defines all of the operations required to the application's business logic
 *  
 * @author fds
 *
 */
public interface TaskService {

	List<TaskDTO> showList();

	TaskDTO findOne(UUID uuid);

	TaskDTO saveTask(TaskDTO task);

	TaskDTO updateTask(TaskDTO task);

	Boolean resolveTask(UUID uuid);

	Boolean postponeTask(UUID uuid, Integer timeMinute);

	void unmarkPostoned();

}