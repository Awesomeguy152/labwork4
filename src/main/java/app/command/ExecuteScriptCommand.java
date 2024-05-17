package app.command;

import app.MusicBandsManager;
import app.utils.DataInputHandler;

import java.io.FileNotFoundException;

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand() {
        super("executeScript", "executeScript");
    }

    @Override
    public void execute(String[] commandParts) {
        if(commandParts.length < 2){
            System.out.println("Необходимо указать путь к файлу со скриптом");
            return;
        }
        String fileName = commandParts[1];

        try {
            dataInputHandler.setFilePath(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("При выполнении скрипта произошла ошибка");
            e.printStackTrace();
        }
        DataInputHandler.InputMode prevMode = dataInputHandler.getCurrentInputMode();
        dataInputHandler.setCurrentInputMode(DataInputHandler.InputMode.FILE);
        MusicBandsManager.run();
        dataInputHandler.setCurrentInputMode(prevMode);
    }
}
