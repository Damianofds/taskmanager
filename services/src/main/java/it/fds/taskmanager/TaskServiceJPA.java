package it.fds.taskmanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fds.taskmanager.dto.TaskDTO;
import it.fds.taskmanager.model.Task;
import it.fds.taskmanager.repository.TasksRepository;

/**
 * This is an implementation of the TaskService interface which rely on a JPA data model 
 * 
 * @author fds
 *
 */
@Service
public class TaskServiceJPA implements TaskService {

	private final Logger LOGGER = Logger.getLogger(TaskServiceJPA.class);
	
	@Autowired
	private TasksRepository tasksRepo;
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#showList()
	 */
	@Override
	public List<TaskDTO> showList() {
		List<Task> taskslist = tasksRepo.findAllExcludePostponed();
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for(Task t : taskslist){
			TaskDTO tmpBean = new TaskDTO();
			BeanUtils.copyProperties(t, tmpBean);
			tasksDTO.add(tmpBean);
		}
		return tasksDTO;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#findOne(java.util.UUID)
	 */
	@Override
	public TaskDTO findOne(UUID uuid) {
		Task tasks = tasksRepo.findByUuid(uuid);
		TaskDTO tmpBean = new TaskDTO();
		BeanUtils.copyProperties(tasks, tmpBean);
		return tmpBean;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#saveTask(it.fds.taskmanager.dto.TaskDTO)
	 */
	@Override
	public TaskDTO saveTask(TaskDTO task) {
		Task t = new Task();
		BeanUtils.copyProperties(task, t);
		t.setUuid(UUID.randomUUID());
		Task newTask = tasksRepo.save(t);
		TaskDTO newTaskDTO = new TaskDTO(); 
		BeanUtils.copyProperties(newTask, newTaskDTO);
		return newTaskDTO;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#updateTask(it.fds.taskmanager.dto.TaskDTO)
	 */
	@Override
	public TaskDTO updateTask(TaskDTO task) {
		Task t = new Task();
		BeanUtils.copyProperties(task, t);
		t.setUpdatedat(Calendar.getInstance());
		Task newTask = tasksRepo.save(t);
		TaskDTO newTaskDTO = new TaskDTO(); 
		BeanUtils.copyProperties(newTask, newTaskDTO);
		return newTaskDTO;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#resolveTask(java.util.UUID)
	 */
	@Override
	public Boolean resolveTask(UUID uuid) {
		Task t = tasksRepo.findByUuid(uuid);
		t.setResolvedat(Calendar.getInstance());
		t.setStatus(TaskState.RESOLVED.toString().toUpperCase());
		tasksRepo.save(t);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#postponeTask(java.util.UUID, java.lang.Integer)
	 */
	@Override
	public Boolean postponeTask(UUID uuid, Integer timeMinute) {
		Task t = tasksRepo.findByUuid(uuid);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, timeMinute);
		t.setPostponedat(c);
		t.setStatus(TaskState.POSTPONED.toString().toUpperCase());
		tasksRepo.save(t);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see it.fds.taskmanager.TaskService#unmarkPostoned()
	 */
	@Override
	public void unmarkPostoned() {
		List<Task> list = tasksRepo.findTaskToRestore();
		for(Task t : list){
			t.setPostponedat(null);
			t.setStatus(TaskState.RESTORED.toString().toUpperCase());
			tasksRepo.save(t);
		}
	}
}
