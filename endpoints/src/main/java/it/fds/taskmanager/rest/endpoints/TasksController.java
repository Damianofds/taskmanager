package it.fds.taskmanager.rest.endpoints;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.fds.taskmanager.TaskService;
import it.fds.taskmanager.dto.TaskDTO;

/**
 * This is the only controller for the whole taskmanager application.
 * It exposes all the REST endpoints to support the initial requirements.
 *  
 * @author fds
 *
 */
@RestController
@RequestMapping(value = "/task")
public class TasksController {

    private final Logger LOGGER = Logger.getLogger(TasksController.class);
    
    @Autowired
	private TaskService taskService;
    
    /**
     * Load the tasks except those marked as POSTPONED
     * 
     * @return a List of all the tasks available
     */
	@RequestMapping("/list")
    public List<TaskDTO> list() {
		LOGGER.info("Serving GET /task/list endpoint...");
		StringBuilder sb = new StringBuilder();
		List<TaskDTO> outlist = new LinkedList<>();
		for(TaskDTO t : taskService.showList()){
			sb.append(t.toString()).append(" - ");
			outlist.add(t);
		}
		String str = sb.toString();
		LOGGER.trace(str);
		LOGGER.info("Serving GET /task/list endpoint... DONE!");
		return outlist;
    }
	
	/**
	 * Return the all the details for a single task
	 * 
	 * @param uuid associated to the task to retrieve
	 * @return
	 */
	@RequestMapping("/{uuid}")
    public TaskDTO getTask(@PathVariable(value="uuid")UUID uuid) {
		LOGGER.info("Serving GET /task/{uuid} endpoint...");
		TaskDTO task = taskService.findOne(uuid);
		LOGGER.info("Serving GET /task/{uuid} endpoint... DONE!");
		return task;
    }
	
	/**
	 * Create a new task
	 * 
	 * @param task data to use to inizialize the task
	 * @return the new task UUID
	 */
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public String createTask(@RequestBody TaskDTO task) {
		LOGGER.info("Serving POST /task/create endpoint...");
		String taskId = taskService.saveTask(task).getUuid().toString();
		LOGGER.info("Serving POST /task/create endpoint... DONE!");
		return taskId;
	}
	
	/**
	 * Update an existing task
	 * 
	 * @param uuid of the task to update
	 * @param task data to use in update
	 * @return
	 */
	@RequestMapping(value = "/{uuid}", method = RequestMethod.POST)
	public Boolean updateTask(@PathVariable(value="uuid")UUID uuid, @RequestBody TaskDTO task) {
		LOGGER.info("Serving POST /task/{uuid} endpoint...");
		task.setUuid(uuid);
		String taskId = taskService.updateTask(task).getUuid().toString();
		LOGGER.info("Serving POST /task/{uuid} endpoint... DONE!");
		return true;
	}
	
	/**
	 * Postpone a task for <strong>N</strong> minutes
	 *  
	 * @param uuid the uuid of the task to postpone
	 * @param timeMinute the amount of minutes to postpone
	 * @return True if the operation ended successfuly
	 */
	@RequestMapping(value = "/{uuid}/postpone", method = RequestMethod.POST)
	public Boolean postponeTask(@PathVariable(value="uuid")UUID uuid,  @RequestBody Integer timeMinute) {
		LOGGER.info("Serving POST /task/{uuid}/postpone endpoint...");
		Boolean out = taskService.postponeTask(uuid, timeMinute);
		LOGGER.info("Serving POST /task/{uuid}/postpone endpoint... DONE!");
		return out;
	}
	
	/**
	 * Mark a task as resolved
	 * 
	 * @param uuid the task to mark as RESOLVED
	 * @return True if the operation ended successfuly
	 */
	@RequestMapping(value = "/{uuid}/resolve", method = RequestMethod.POST)
	public Boolean resolveTask(@PathVariable(value="uuid")UUID uuid) {
		LOGGER.info("Serving POST /task/{uuid}/resolve endpoint...");
		Boolean out = taskService.resolveTask(uuid);
		LOGGER.info("Serving POST /task/{uuid}/resolve endpoint... DONE!");
		return out;
	}
	
}
