import java.util.Scanner;

public class Main {
    private static final String[][] DEMO = {
            {"112-233-445 95", "Иванов Иван Иванович"},
            {"123-456-789 64", "Петров Пётр Петрович"},
            {"234-567-890 12", "Сидорова Анна Сергеевна"},
            {"345-678-901 23", "Кузнецов Алексей Павлович"},
            {"456-789-012 34", "Смирнова Ольга Ивановна"},
            {"567-890-123 45", "Попов Дмитрий Андреевич"},
            {"678-901-234 56", "Васильева Мария Петровна"},
            {"789-012-345 67", "Новиков Сергей Олегович"},
            {"890-123-456 78", "Фёдорова Елена Николаевна"},
            {"901-234-567 89", "Морозов Игорь Викторович"}
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashTable table = new HashTable(5);

        while (true) {
            printMenu();
            if (!in.hasNextLine()) {
                return;
            }
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1":
                    insert(in, table);
                    break;
                case "2":
                    search(in, table);
                    break;
                case "3":
                    delete(in, table);
                    break;
                case "4":
                    table.print();
                    break;
                case "5":
                    fillDemo(table);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1 - вставить элемент");
        System.out.println("2 - найти элемент");
        System.out.println("3 - удалить элемент");
        System.out.println("4 - напечатать таблицу");
        System.out.println("5 - заполнить демонстрационными данными");
        System.out.println("0 - выход");
        System.out.print("Выбор: ");
    }

    private static String readSnils(Scanner in) {
        System.out.print("СНИЛС (11 цифр): ");
        String snils = Snils.normalize(in.hasNextLine() ? in.nextLine() : null);
        if (snils == null) {
            System.out.println("Ошибка: СНИЛС должен состоять из 11 цифр");
        }
        return snils;
    }

    private static void insert(Scanner in, HashTable table) {
        String snils = readSnils(in);
        if (snils == null) {
            return;
        }
        System.out.print("ФИО: ");
        String name = in.nextLine().trim();
        add(table, snils, name);
    }

    private static void add(HashTable table, String snils, String name) {
        int capacityBefore = table.getCapacity();
        boolean added = table.insert(new Entry(snils, name));
        System.out.println(added ? "Добавлено: " + snils : "Элемент уже есть, ФИО обновлено: " + snils);
        if (table.getCapacity() != capacityBefore) {
            System.out.println("Рехэширование: размер таблицы " + capacityBefore + " -> " + table.getCapacity());
        }
    }

    private static void search(Scanner in, HashTable table) {
        String snils = readSnils(in);
        if (snils == null) {
            return;
        }
        Entry entry = table.search(snils);
        System.out.println(entry == null ? "Не найдено" : "Найдено: " + entry);
    }

    private static void delete(Scanner in, HashTable table) {
        String snils = readSnils(in);
        if (snils == null) {
            return;
        }
        System.out.println(table.delete(snils) ? "Удалено" : "Не найдено");
    }

    private static void fillDemo(HashTable table) {
        for (String[] row : DEMO) {
            add(table, row[0], row[1]);
        }
    }
}
