package it.fds.taskmanager.scheduler;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fds.taskmanager.TaskService;
import it.fds.taskmanager.dto.TaskDTO;
import it.fds.taskmanager.tasksgenerator.TasksGenerator;

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
				
				taskService.saveTask(task);
				LOGGER.info("New Task SAVED!");
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
