package stackcollapse;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvToBeanBuilder;

public class Main {

	public static void main(String[] args) throws Exception {
		FileReader fileReader = new FileReader("logger.csv");
		List<TaskEntry> taskEntries = new CsvToBeanBuilder<TaskEntry>(fileReader).withType(TaskEntry.class).build()
				.parse();

		List<TaskExecution> stack = new ArrayList<>();

		TaskExecution root = null;
		
		for (TaskEntry taskEntry : taskEntries) {
			popStack(stack, taskEntry.getTaskDepth());
			TaskExecution taskExecution = new TaskExecution();
			taskExecution.setTaskName(taskEntry.getTaskName());
			taskExecution.setDurationInMillis(taskEntry.getDurationInMillis());
			
			if (stack.isEmpty()) {
				root = taskExecution;
			} else {
				TaskExecution parentTask = stack.get(stack.size() - 1);
				parentTask.getSubTasks().add(taskExecution);
			}
			
			stack.add(taskExecution);
		}
		
		printStacks(Collections.singletonList(root));
		

	}

	private static void printStacks(List<TaskExecution> stack) {
		TaskExecution top = stack.get(stack.size() - 1);
		
		StringBuilder builder = new StringBuilder();
		List<String> taskNames = stack.stream().map(TaskExecution::getTaskName).collect(Collectors.toList());
		builder.append(StringUtils.join(taskNames, ";"));
		builder.append(" ");
		builder.append(top.getSelfTime());
		System.out.println(builder.toString());
		
		for (TaskExecution taskExecution : top.getSubTasks()) {
			List<TaskExecution> newStack = new ArrayList<>(stack);
			newStack.add(taskExecution);
			printStacks(newStack);
		}
	}

	private static void popStack(List<?> stack, int i) {
		while (stack.size() > 0 && stack.size() > i) {
			stack.remove(stack.size() - 1);
		}
	}

}
