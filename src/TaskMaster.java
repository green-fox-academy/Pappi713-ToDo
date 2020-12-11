import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskMaster {


    public List<Task> taskList() {
        Path instructionPath = Paths.get("TasksList");
        List<Task> tasks = new ArrayList<>();
        List<String> alltask = new ArrayList<>();

        try {
            alltask = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("failure at file reading");
        }
        for (String task : alltask) {
            tasks.add(new Task(task));
        }
        ;
        return tasks;
    }


    public void printTasks() {
        List<Task> t = taskList();
        if (t.isEmpty()) {
            System.out.println("No todos for today! :)");
        } else {
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).checked){
                    System.out.println(i + 1 + " - [x]" + t.get(i).description);

                }
                else{
                    System.out.println(i + 1 + " - [ ]" + t.get(i).description);
                }
            }
        }
    }

    public void addTask(String str) {
        List<Task> tl = taskList();
        tl.add(new Task(str));
        clearFileContent();

        reWriteFile(tl);
    }

    private void reWriteFile(List<Task> tl) {
        Path instructionPath = Paths.get("TasksList");
        try {
            for (Task task : tl) {
                Files.write(instructionPath, Arrays.asList(task.description), StandardOpenOption.APPEND);
            }
        } catch (IOException ex) {
            System.out.println("Unable to write file: TasksList");
        }
    }

    public void clearFileContent() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("TasksList");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error during clearing the file content");
        }
    }

    public void removetask(Integer number) {

        List<Task> tl = taskList();
        try {
            tl.remove(number - 1);
            clearFileContent();
            reWriteFile(tl);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Unable to remove: index is out of bound");
        }
    }
    public void checker(Integer number){
        List<Task> tl =taskList();
        tl.get(number-1).checked=true;
    }
}


