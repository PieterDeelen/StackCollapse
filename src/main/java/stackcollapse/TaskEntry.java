package stackcollapse;

import com.opencsv.bean.CsvBindByName;

public class TaskEntry {

	@CsvBindByName(column = "Thread")
	private String thread;

	@CsvBindByName(column = "TaskName")
	private String taskName;

	@CsvBindByName(column = "TaskDepth")
	private int taskDepth;

	@CsvBindByName(column = "DurationInMillis")
	private int durationInMillis;

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskDepth() {
		return taskDepth;
	}

	public void setTaskDepth(int taskDepth) {
		this.taskDepth = taskDepth;
	}

	public int getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(int durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	@Override
	public String toString() {
		return "TaskEntry [thread=" + thread + ", taskName=" + taskName + ", taskDepth=" + taskDepth
				+ ", durationInMillis=" + durationInMillis + "]";
	}

}
