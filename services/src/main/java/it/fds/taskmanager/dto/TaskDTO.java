package it.fds.taskmanager.dto;

import java.util.Calendar;
import java.util.UUID;

public class TaskDTO {

	private UUID uuid;
	private Calendar createdat;
	private Calendar updatedat;
	private Calendar duedate;
	private Calendar resolvedat;
	private Calendar postponedat;
	private Long postponedtime;
	private String title;
	private String description;
	private String priority;
	private String status;
	
	public TaskDTO() {
		super();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("uuid: '").append(uuid).append("' ")
		.append("createdat: '").append(createdat).append("' ")
		.append("updatedat: '").append(updatedat).append("' ")
		.append("duedate: '").append(duedate).append("' ")
		.append("resolvedat: '").append(resolvedat).append("' ")
		.append("postponedat: '").append(postponedat).append("' ")
		.append("postponedtime: '").append(postponedtime).append("' ")
		.append("title: '").append(title).append("' ")
		.append("description: '").append(description).append("' ")
		.append("priority: '").append(priority).append("' ")
		.append("status: '").append(status).append("'\n");
		return sb.toString();
	}

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
	public Long getPostponedtime() {
		return postponedtime;
	}
	public void setPostponedtime(Long postponedtime) {
		this.postponedtime = postponedtime;
	}
	
	
}
