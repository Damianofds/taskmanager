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
public class Scheduler {

	@Autowired
	TaskService taskService;
	
	private final Logger LOGGER = Logger.getLogger(Scheduler.class);
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	@Autowired
	private TasksGenerator basicTaskGenerator;
	
	public Scheduler(){}
	
	public void generateTask() {
		final Runnable generator = new Runnable() {
			public void run() {
				
				TaskDTO task = basicTaskGenerator.generateTask();
				
				taskService.saveTask(task);
				LOGGER.info("TASK SAVED!");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(generator, 5, 5, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
