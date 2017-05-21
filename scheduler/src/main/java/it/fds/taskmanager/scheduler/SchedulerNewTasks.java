package it.fds.taskmanager.scheduler;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.fds.taskmanager.TaskService;
import it.fds.taskmanager.dto.TaskDTO;
import it.fds.taskmanager.tasksgenerator.TasksGenerator;

/**
 * Create and schedule new Tasks
 * 
 * @author fds
 *
 */
@Component
public class SchedulerNewTasks {

	@Autowired
	TaskService taskService;
	
	private final Logger LOGGER = Logger.getLogger(SchedulerNewTasks.class);
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	@Autowired
	private TasksGenerator basicTaskGenerator;
	
	public SchedulerNewTasks(){}
	
	public void generateTask() {
		final Runnable newTaskGenerator = new Runnable() {
			public void run() {
				
				TaskDTO task = basicTaskGenerator.generateTask();
				TaskDTO newt = taskService.saveTask(task);
				
				RestTemplate rt = new RestTemplate();
				ResponseEntity<String> result = null;
				try {
					result = rt.postForEntity(new URI("http://localhost:8080/task/notify"), newt.getUuid(), String.class);
					LOGGER.info("Result of the notify call: '" + result + "'");
					LOGGER.info("New Task SAVED!");
				} catch (RestClientException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		};
		final ScheduledFuture<?> fut1 = scheduler.scheduleAtFixedRate(newTaskGenerator, 5, 5, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				fut1.cancel(true);
			}
		}, 60*60, SECONDS);
	}
}
