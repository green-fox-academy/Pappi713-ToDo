import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArgumentHandler {

    TaskMaster tm = new TaskMaster();

    public void handleArgument(String[] args) {
        if (args.length == 0) {
            System.out.println(getInstructions());
        } else {
            switch (args[0]) {
                case "-l": {
                    tm.printTasks();
                    break;
                }
                case "-a": {
                    if (args.length == 1) {
                        System.out.println("Unable to add: no task provided");
                        break;
                    }
                    tm.addTask(args[1]);
                    break;
                }
                case "-r": {
                    if (args.length == 1) {
                        System.out.println("Unable to remove: no index provided");
                        break;
                    }
                    int pos=0;
                    try{
                        pos= Integer.parseInt(args[1]);
                    }catch(NumberFormatException ex){
                        System.out.println("Unable to remove: index is not a number");
                        break;
                    }
                    tm.removetask(pos);
                    break;
                }
                case "-c": {
                    int pos=0;
                    if (args.length == 1) {
                        System.out.println("Unable to remove: no index provided");
                        break;
                    }
                    pos=Integer.parseInt(args[1]);
                    tm.checker(pos);
                    break;
                }
                default: {
                    System.out.println("Unsupported arguments");
                    break;
                }
            }

        }
    }


    public String getInstructions() {
        Path instructionPath = Paths.get("Instructions");
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(instructionPath);
        } catch (IOException e) {
            System.out.println("Sry, instructions not available, have fun experimenting");
        }
        StringBuilder contentAsString = new StringBuilder();
        for (String line : content) {
            contentAsString.append(line);
            contentAsString.append("\n");
        }
        return contentAsString.toString();
    }
}

