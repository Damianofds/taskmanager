package it.fds.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
public class SchedulerMain {

	@Autowired
	public Scheduler scheduler;
	
	private void start(){
		scheduler.beepForAnHour();
	}
	
	public static void main(String [] args){
		
		ApplicationContext ctx = SpringApplication.run(SchedulerMain.class, args);
		SchedulerMain p = ctx.getBean(SchedulerMain.class);
		p.start();
		
	}
}
