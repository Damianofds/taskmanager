package it.fds.taskmanager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.fds.taskmanager.model.Task;

public interface TasksRepository  extends JpaRepository<Task,Long> {

	Task findByUuid(UUID uuid);
	
	List<Task> findTaskToRestore();
	
	List<Task> findAllExcludePostponed();
	
	Integer getNumberOfTaskWithPriority();
}
