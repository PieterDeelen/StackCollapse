package stackcollapse;

import java.util.ArrayList;
import java.util.List;

class TaskExecution {
	private String taskName;
	private int durationInMillis;
	private List<TaskExecution> subTasks = new ArrayList<>();

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(int durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	public List<TaskExecution> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<TaskExecution> subTasks) {
		this.subTasks = subTasks;
	}

	@Override
	public String toString() {
		return "TaskExecution [taskName=" + taskName + ", durationInMillis=" + durationInMillis + "]";
	}

	public int getSelfTime() {
		int selfTime = durationInMillis;
		for (TaskExecution taskExecution : subTasks) {
			selfTime -= taskExecution.getDurationInMillis();
		}
		
		return selfTime;
	}
	
	

}