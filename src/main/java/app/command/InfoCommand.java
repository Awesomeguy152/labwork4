package app.command;

import app.utils.DataSource;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

public class InfoCommand extends AbstractCommand{

    public InfoCommand() {
        super("info", "info");

    }

    @Override
    public void execute(String[] commandParts) {
        System.out.println("Тип коллекции : " + DataSource.getCollection().getClass());
        System.out.println("Количество элементов: " + DataSource.getCollection().size());
        System.out.println("Дата инициализации : " + DataSource.getTimeOfInitialization().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}

