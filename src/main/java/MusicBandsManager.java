import command.Command;
import command.HelpCommand;
import command.ShowCommand;
import model.Coordinates;
import model.MusicBand;
import model.Person;
import utils.CommandManager;
import utils.DataInputHandler;

import java.util.*;

public class MusicBandsManager {
    private static CommandManager commandManager;
    private static DataInputHandler inputHandler;

    public static void main(String[] args) {
        commandManager = new CommandManager();
        inputHandler = new DataInputHandler();
        run();
    }

    private static void run(){
        System.out.println("Введите команду, Команда help-список команд");
        Scanner scanner = new Scanner(System.in); // создание переменной
        while(true){
            String commandString = scanner.nextLine(); //
            String[] commandParts = commandString.split(" ");
            if (commandManager.getCommandMapper().containsKey(commandParts[0])){
                commandManager.getCommandMapper().get(commandParts[0]).execute(commandParts);
            }
            else {
                System.out.println("такой команды не существует");
            }
        }
    }

    private static MusicBand createMusicBand(){
        MusicBand musicBand = new MusicBand();


        String message = "Введите название группы";
        String name = inputNotNullString(message);
        musicBand.setName(name);




        return musicBand;
    }
    private static Coordinates createCoordinates(){
        Coordinates coordinates = new Coordinates();

        String message = "Введите координату x: ";
        long x;
        while(true) {
             x = (long) inputNumber(message, true, Long.class);
             if (x <= 707){
                 break;
             }
             else {
                 System.out.println("x должен быть меньше 707");
             }
        }
        coordinates.setX(x);

        message = "Введите координату y: ";
        long y;
        while(true){
            y = (long) inputNumber(message, true, Long.class);
            if (y <= -776){
                break;
            }
            else {
                System.out.println("y не должен быть больше -776");
            }
        }
        coordinates.setY(y);
        return null;
    }
    private static String inputNotNullString(String message){
        while(true){
            System.out.println(message);
            String result = inputHandler.nextLine();
            if (result.equals("")){
                System.out.println("Значение не может быть null, повторите попытку");
            }
            else {
                return result;
            }
        }
    }
    private static Number inputNumber(String message, boolean notNull, Class className){
        while(true){
            System.out.println(message);
            String input = inputHandler.nextLine();
            if (input.isBlank()){
                if (notNull){
                    System.out.println("не может быть null");
                    continue;
                }
            }
            try {
                if (className == Integer.class){
                    return Integer.parseInt(input);
                }
                if (className == Double.class){
                    return Double.parseDouble(input);
                }
                if (className == Long.class){
                    return Long.parseLong(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("неверный формат числа");
                continue;
            }
            return 0;
        }
    }
}

