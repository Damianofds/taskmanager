package it.fds.taskmanager;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fds.taskmanager.repository.TasksRepository;

@Service
public class ReportServiceJPA implements ReportService{

    private final Logger LOGGER = Logger.getLogger(ReportServiceJPA.class);
    
    @Autowired
    private TasksRepository tasksRepo;
    
    @Override
    public int getNumberOfTaskWithStatus(TaskState state) {
        throw new NotImplementedException("Dear candidate, implement me please!");
    }

    @Override
    public int getNumberOfTasksWithPriority(int priority) {
        return tasksRepo.getNumberOfTaskWithPriority();
    }

}
