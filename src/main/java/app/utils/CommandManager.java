package app.utils;

import app.command.*;
import app.command.Command;

import java.util.HashMap;
import java.util.Map;

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
        commandMapper.put("add", new AddCommand());
        commandMapper.put("update", new UpdateCommand());
        commandMapper.put("clear", new ClearCommand());
        commandMapper.put("save", new SaveCommand());
        commandMapper.put("exit", new ExitCommand());
        commandMapper.put("remove_by_id", new RemoveByIdCommand());
        commandMapper.put("add_if_min", new AddIfMinCommand());
        commandMapper.put("remove_greater", new RemoveGreaterCommand());
        commandMapper.put("remove_lower", new RemoveLowerCommand());
        commandMapper.put("sum_of_number_of_participant", new SumOfNumberOfParticipantsCommand());
        commandMapper.put("count_less_than_genre", new CountLessThanGenreCommand());
        commandMapper.put("filter_less_than_number_of_participants", new FilterLessThanNumberOfParticipantsCommand());
        commandMapper.put("execute_script", new ExecuteScriptCommand());
        commandMapper.put("info", new InfoCommand());
    }

}
