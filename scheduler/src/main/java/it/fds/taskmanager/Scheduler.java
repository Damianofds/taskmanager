package it.fds.taskmanager;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import it.fds.taskmanager.TaskService;
import it.fds.taskmanager.dto.TaskDTO;

@Component
class Scheduler {

	@Autowired
	TaskService taskService;
	
	private final Logger LOGGER = Logger.getLogger(Scheduler.class);
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public Scheduler(){}
	
	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				
				TaskDTO task = new TaskDTO();
				task.setTitle("A title");
				task.setDescription("A description!");
				taskService.saveTask(task);
				LOGGER.info("TASK SAVED!");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 5, 5, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
