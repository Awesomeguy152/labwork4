package utils;

import command.Command;
import command.HelpCommand;
import command.ShowCommand;
import model.MusicBand;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CommandManager {
    private Map<String, Command> commandMapper = new HashMap<>();

    public Map<String, Command> getCommandMapper() {
        return commandMapper;
    }

    public void setCommandMapper(Map<String, Command> commandMapper) {
        this.commandMapper = commandMapper;
    }

    public CommandManager() {
        commandMapper.put("help", new HelpCommand());
        commandMapper.put("show", new ShowCommand());
    }

}
