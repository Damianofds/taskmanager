package it.fds.taskmanager;

import it.fds.taskmanager.scheduler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Launcher class for the Task scheduler
 * 
 * @author fds
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
public class SchedulerMain {

	@Autowired
	public SchedulerNewTasks scheduler1;
	
	@Autowired
	public SchedulerPostponedChecks scheduler2;
	
	private void start(){
		scheduler1.generateTask();
		scheduler2.checkPostponed();
	}
	
	public static void main(String [] args){
		
		ApplicationContext ctx = SpringApplication.run(SchedulerMain.class, args);
		SchedulerMain p = ctx.getBean(SchedulerMain.class);
		p.start();
		
	}
}

