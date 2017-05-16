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

@RestController
@RequestMapping(value = "/task")
public class TasksController {

    private final Logger LOGGER = Logger.getLogger(TasksController.class);
    
    @Autowired
	private TaskService taskService;
    
	@RequestMapping("/list")
    public List<TaskDTO> list() {
		LOGGER.info("Serving GET /task/list endpoint...");
		StringBuilder sb = new StringBuilder();
		List<TaskDTO> outlist = new LinkedList<>();
		for(TaskDTO t : taskService.findAll()){
			sb.append(t.toString()).append(" - ");
			outlist.add(t);
		}
		String str = sb.toString();
		LOGGER.trace(str);
		LOGGER.info("Serving GET /task/list endpoint... DONE!");
		return outlist;
    }
	
	@RequestMapping("/{uuid}")
    public TaskDTO getTask(@PathVariable(value="uuid")UUID uuid) {
		LOGGER.info("Serving GET /task/{uuid} endpoint...");
		TaskDTO task = taskService.findOne(uuid);
		LOGGER.info("Serving GET /task/{uuid} endpoint... DONE!");
		return task;
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createTask(@RequestBody TaskDTO task) {
		LOGGER.info("Serving POST /task/create endpoint...");
		String taskId = taskService.saveTask(task).getUuid().toString();
		LOGGER.info("Serving POST /task/create endpoint... DONE!");
		return taskId;
	}
	
	@RequestMapping(value = "/{uuid}", method = RequestMethod.POST)
	public String updateTask(@PathVariable(value="uuid")UUID uuid, @RequestBody TaskDTO task) {
		LOGGER.info("Serving POST /task/{uuid} endpoint...");
		task.setUuid(uuid);
		String taskId = taskService.updateTask(task).getUuid().toString();
		LOGGER.info("Serving POST /task/{uuid} endpoint... DONE!");
		return taskId;
	}
	
	@RequestMapping(value = "/{uuid}/postpone", method = RequestMethod.POST)
	public Boolean updateTask(@PathVariable(value="uuid")UUID uuid) {
		LOGGER.info("Serving POST /task/{uuid}/postpone endpoint...");
		Boolean out = taskService.postponeTask(uuid);
		LOGGER.info("Serving POST /task/{uuid}/postpone endpoint... DONE!");
		return out;
	}
	
	@RequestMapping(value = "/{uuid}/resolve", method = RequestMethod.POST)
	public Boolean resolveTask(@PathVariable(value="uuid")UUID uuid) {
		LOGGER.info("Serving POST /task/{uuid}/resolve endpoint...");
		Boolean out = taskService.resolveTask(uuid);
		LOGGER.info("Serving POST /task/{uuid}/resolve endpoint... DONE!");
		return out;
	}
	
}
