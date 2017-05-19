package it.fds.taskmanager.scheduler;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fds.taskmanager.TaskService;

/**
 * Search for postponed tasks that must be reactivated
 * 
 * @author fds
 *
 */
@Component
public class SchedulerPostponedChecks {
	
	@Autowired
	TaskService taskService;
	
	private final Logger LOGGER = Logger.getLogger(SchedulerPostponedChecks.class);
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public SchedulerPostponedChecks(){}
	
	public void checkPostponed() {
		
		final Runnable solvePostponedgenerator = new Runnable() {
			public void run() {
				
				taskService.unmarkPostoned();
				LOGGER.info("Tasks unmarked!");
			}
		};
		final ScheduledFuture<?> fut2 = scheduler.scheduleAtFixedRate(solvePostponedgenerator, 5, 5, SECONDS);//TODO externalize the interval!
		scheduler.schedule(new Runnable() {
			public void run() {
				fut2.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}
}
