package it.fds.taskmanager.model;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Task.findTaskToRestore", query = "SELECT t FROM Task t WHERE t.status = 'POSTPONED' and t.postponedat < NOW()"),
	@NamedQuery(name = "Task.findAllExcludePostponed", query = "SELECT t FROM Task t WHERE t.status != 'POSTPONED'"),
	@NamedQuery(name = "Task.getNumberOfTaskWithPriority", query = "SELECT count(t) FROM Task t WHERE t.priority = '3'")
})
public class Task {

	@Id
	UUID uuid;
	Calendar createdat;
	Calendar updatedat;
	Calendar duedate;
	Calendar resolvedat;
	Calendar postponedat;
	Long postponedtime;
	String title;
	String description;
	String priority;
	String status;
	
	public Task() {}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Calendar getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Calendar createdat) {
		this.createdat = createdat;
	}

	public Calendar getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Calendar updatedat) {
		this.updatedat = updatedat;
	}

	public Calendar getDuedate() {
		return duedate;
	}

	public void setDuedate(Calendar duedate) {
		this.duedate = duedate;
	}

	public Calendar getResolvedat() {
		return resolvedat;
	}

	public void setResolvedat(Calendar resolvedat) {
		this.resolvedat = resolvedat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getPostponedat() {
		return postponedat;
	}

	public void setPostponedat(Calendar postponedat) {
		this.postponedat = postponedat;
	}

	public void setPostponedtime(Long postponedtime) {
		this.postponedtime = postponedtime;
	}

	
}
