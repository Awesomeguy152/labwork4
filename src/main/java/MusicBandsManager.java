import command.Command;
import command.HelpCommand;
import command.ShowCommand;
import model.MusicBand;

import java.util.*;

public class MusicBandsManager {
    private static Map<String, Command> commandMapper = new HashMap<>();
    private static TreeSet<MusicBand> data = new TreeSet();
    public static void main(String[] args) {
        commandMapper.put("help", new HelpCommand());
        commandMapper.put("show", new ShowCommand(data));
        run();
    }

    public static void run(){
        System.out.println("Введите команду, Команда help-список команд");
        Scanner scanner = new Scanner(System.in); // создание переменной
        while(true){
            String commandString = scanner.nextLine(); //
            String[] commandParts = commandString.split(" ");
            if (commandMapper.containsKey(commandParts[0])){
                commandMapper.get(commandParts[0]).execute(commandParts);
            }
        }
    }
}

