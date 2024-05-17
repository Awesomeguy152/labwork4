package app;

import app.command.AbstractCommand;
import app.model.*;
import app.utils.CommandManager;
import app.utils.DataInputHandler;
import app.utils.DataSource;
import app.utils.MusicBandMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MusicBandsManager {
    private static CommandManager commandManager;
    private static DataInputHandler inputHandler;

    private static String collectionFileName;
    private static boolean isScript = false;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Для работы менеджера требуется указать путь к файлу с коллекцией");
            return;
        }

        collectionFileName = System.getenv(args[0]);

        if (collectionFileName == null) {
            System.out.println("Не удалось найти путь к файлу с коллекцией по переменной окружения");
            return;
        }

        if (!loadCollectionFromFile()) {
            System.out.println("Не удалось считать коллекцию из файла");
            return;
        }

        commandManager = new CommandManager();
        inputHandler = new DataInputHandler();
        AbstractCommand.dataInputHandler = inputHandler;
        run();
    }

    private static boolean loadCollectionFromFile() {
        try {
            Scanner fileScanner = new Scanner(new File(collectionFileName));
            StringBuilder sb = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine());
            }
            String serializedCollection = sb.toString();

            TreeSet<MusicBand> deserializedCollection = null;

            TypeReference<List<MusicBandForXml>> collectionFile = new TypeReference<List<MusicBandForXml>>() {
            };
            XmlMapper xmlMapper = new XmlMapper();
            List<MusicBandForXml> deserializedList = xmlMapper.readValue(serializedCollection, collectionFile);
            deserializedCollection = new TreeSet<>(MusicBandMapper.toMusicBandList(deserializedList));
            DataSource.setCollection(deserializedCollection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveCollectionToFile() {
        TreeSet<MusicBand> collection = DataSource.getCollection();

        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(collectionFileName));) {
            XmlMapper xmlMapper = new XmlMapper();

            List<MusicBandForXml> collectionToSerialized
                    = MusicBandMapper.toMusicBandForXmlList(new ArrayList<>(DataSource.getCollection()));
            String serializedCollection = xmlMapper.writeValueAsString(collectionToSerialized);
            osw.write(serializedCollection);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void run() {
        System.out.println("Введите команду, Команда help-список команд");
        while (inputHandler.hasNext()) {
            String commandString = inputHandler.nextLine(); //
            String[] commandParts = commandString.split(" ");
            if (commandManager.getCommandMapper().containsKey(commandParts[0])) {
                commandManager.getCommandMapper().get(commandParts[0]).execute(commandParts);
            } else {
                System.out.println("такой команды не существует");
            }
        }
    }

    public static MusicBand createMusicBand() {
        MusicBand musicBand = new MusicBand();

        String message = "Введите название группы";
        String name = inputNotNullString(message);
        musicBand.setName(name);

        Coordinates coordinates = createCoordinates();
        musicBand.setCoordinates(coordinates);

        message = "Введите количество участников группы: ";
        Integer numberOfParticipants;
        while (true) {
            numberOfParticipants = (Integer) inputNumber(message, false, Integer.class);
            if (numberOfParticipants != null) {
                if (numberOfParticipants >= 0) {
                    break;
                } else {
                    System.out.println("количество участников должно быть больше 0: ");
                }
            } else {
                break;
            }
        }
        musicBand.setNumberOfParticipants(numberOfParticipants);

        message = "Введите название жанра: ";
        MusicGenre genre = inputMusicGenre(message, true);
        musicBand.setGenre(genre);

        Person frontman = createPerson();
        musicBand.setFrontMan(frontman);

        musicBand.setCreationDate(ZonedDateTime.now());
        return musicBand;
    }

    private static Person createPerson() {
        Person person = new Person();

        String message = "Введите имя : ";
        String name = inputNotNullString(message);
        person.setName(name);

        message = "Введите дату Рождения : ";
        LocalDate date = inputLocalDate(message, false);
        person.setBirthday(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        message = "Введите вес : ";
        int weight;
        while (true) {
            weight = (int) inputNumber(message, false, Integer.class);
            if (weight > 0) {
                break;
            } else {
                System.out.println("x должен быть больше 0 : ");
            }
        }
        person.setWeight(weight);

        message = "Введите цвет глаз : ";
        Color color = inputColor(message, false);
        person.setEyeColor(color);

        /*
        MusicBand mb = new MusicBand();
        MusicBand mb1 = new MusicBand();

        int compareResult = mb.compareTo(mb1); 0
        // cr < 0 - mb < mb1
        // cr == 0 - mb == mb1
        // cr > 0 - mb > mb
         */
        return person;
    }

    private static Coordinates createCoordinates() {
        Coordinates coordinates = new Coordinates();

        String message = "Введите координату x: ";
        long x;
        while (true) {
            x = (long) inputNumber(message, true, Long.class);
            if (x <= 707) {
                break;
            } else {
                System.out.println("x должен быть меньше 707: ");
            }
        }
        coordinates.setX(x);

        message = "Введите координату y: ";
        long y;
        while (true) {
            y = (long) inputNumber(message, true, Long.class);
            if (y > -776) {
                break;
            } else {
                System.out.println("y не должен быть меньше -776: ");
            }
        }
        coordinates.setY(y);
        return coordinates;
    }

    public static String inputNotNullString(String message) {
        while (true) {
            System.out.print(message);
            String result = inputHandler.nextLine();
            if (result.equals("")) {
                System.out.println("Значение не может быть null, повторите попытку: ");
            } else {
                return result;
            }
        }
    }

    private static Number inputNumber(String message, boolean notNull, Class className) {
        while (true) {
            System.out.print(message);
            String input = inputHandler.nextLine();
            if (input.isBlank()) {
                if (notNull) {
                    System.out.println("не может быть null: ");
                    continue;
                } else {
                    return null;
                }
            }
            try {
                if (className == Integer.class) {
                    return Integer.parseInt(input);
                }
                if (className == Double.class) {
                    return Double.parseDouble(input);
                }
                if (className == Long.class) {
                    return Long.parseLong(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("неверный формат числа: ");
                continue;
            }
            return 0;
        }
    }

    public static MusicGenre inputMusicGenre(String message, boolean notNull) {
        while (true) {
            System.out.print(message);
            System.out.print("(PSYCHEDELIC_ROCK," +
                    " PSYCHEDELIC_CLOUD_RAP," +
                    " SOUL," +
                    " POP): ");
            String input = inputHandler.nextLine();
            if (input.isBlank()) {
                if (notNull) {
                    System.out.println("не может быть null: ");
                    continue;
                } else {
                    return null;
                }
            }
            try {
                return MusicGenre.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод, повторите попытку. ");
            }
        }
    }

    private static LocalDate inputLocalDate(String message, boolean notNull) {
        while (true) {
            System.out.print(message);
            String input = inputHandler.nextLine();
            if (input.isBlank()) {
                if (notNull) {
                    System.out.println("не может быть null: ");
                    continue;
                } else {
                    return null;
                }
            }
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return LocalDate.parse(input, dateTimeFormatter);
            } catch (DateTimeParseException r) {
                System.out.println("Некорректный ввод, повторите попытку. ");
            }
        }
    }

    private static Color inputColor(String message, boolean notNull) {
        while (true) {
            System.out.print(message);
            System.out.print("BLUE," +
                    "YELLOW," +
                    "BROWN");
            String input = inputHandler.nextLine();
            if (input.isBlank()) {
                if (notNull) {
                    System.out.println("не может быть null: ");
                    continue;
                } else {
                    return null;
                }
            }
            try {
                return Color.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод, повторите попытку. ");
            }
        }
    }
}

