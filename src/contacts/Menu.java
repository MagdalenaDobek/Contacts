package contacts;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static PhoneBook phoneBook;

    private static final String FILENAME = "phonebook.db";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Messages MESSAGES = new Messages();

    private Contact contact;
    private boolean isActive;

//    serialization
    static {
        try {
            phoneBook = (PhoneBook) SerializationUtils.deserialize(FILENAME);
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
            phoneBook = new PhoneBook();
        }
    }

    //method that shows main menu
    protected void mainMenu() throws IOException {
        String input;
        isActive = true;
        while (isActive) {
            try {
                MESSAGES.printMessageValue(MessageType.MAIN_MENU);
                input = SCANNER.nextLine();
                selectAction(input);
            } catch (IllegalStateException illegalStateException) {
                illegalStateException.getMessage();
            }

        }
    }
    //method that closes the application
    private void exit() {
        isActive = false;
        SCANNER.close();
    }

    //method that creates contact (person or organization) and invokes addContact() method form PhoneBook class
    private void add() {
        String type;
        String value;
        do {
            MESSAGES.printMessageValue(MessageType.TYPE);
            type = SCANNER.nextLine().toLowerCase();
        } while (!Validation.isTypeCorrect(type));

        contact = type.equals("person") ? new Person() : new Organization();

        String[] fields = contact.getFields().split(", ");
        for (String field : fields) {
            System.out.printf("Enter the %s: ", field);

            value = SCANNER.nextLine();
            contact.setField(field, value);
        }

        phoneBook.addContact(contact);
    }

    //method that invokes countContacts() method from PhoneBook class and prints how many contacts is in PhoneBook
    private void count() {
        int size = phoneBook.countContacts();
        System.out.printf("The Phone Book has %d record%s. \n\n", size, size == 1 ? "" : "s");

    }
    //method that invokes removeContact() method from PhoneBook class
    private void delete() {
        phoneBook.removeContact(contact);
    }

   /*
   method that takes field and new value of an input and passes it to editContact() method
   in class PhoneBook, then invokes method detailsOfContact() which prints contact info
    */
    private void edit() {
        String field;
        String value;

        String regEx = this.contact.getFields().replace(", ", "|");

        do {
            System.out.printf("Select a field (%s): ", contact.getFields());
            field = SCANNER.nextLine();
        } while (!field.matches(regEx));

        System.out.printf("Enter %s: ", field);
        value = SCANNER.nextLine();

        phoneBook.editContact(contact, field, value);
        phoneBook.detailsOfContact(contact);
    }

    /*
    method that invokes listContacts() method in PhoneBook class to print list of records
    and then invokes method getAction(), which get user input to perform given action
    */
    private void list() throws IOException {
        if (phoneBook.countContacts() == 0) {
            MESSAGES.printMessageValue(MessageType.EMPTY_PHONEBOOK);
        } else {
            phoneBook.listContacts(MessageType.LIST_MENU);
            getAction(MessageType.LIST_MENU);
        }
        selectAction("menu");
    }

    //method that passes user search query to method searchContact() in PhoneBook class
    private void search() throws IOException {
        if (phoneBook.countContacts() == 0) {
            MESSAGES.printMessageValue(MessageType.EMPTY_PHONEBOOK);
        } else {
            MESSAGES.printMessageValue(MessageType.SEARCH_QUERY);
            String query = "(?i).*" + SCANNER.nextLine() + ".*";

            phoneBook.searchContacts(query, MessageType.SEARCH_MENU);
            getAction(MessageType.SEARCH_MENU);
        }
        selectAction("menu");
    }

    //method that returns current contact to edit or delete
    private Contact getCurrentContact(int recordNo, MessageType messageType) {
        recordNo -= 1;
        int phoneBookSize = phoneBook.countContacts();

        while (!Validation.isRecordNumCorrect(recordNo, phoneBookSize)) {
            MESSAGES.printMessageValue(messageType);
            recordNo = SCANNER.nextInt() - 1;
        }

        contact = phoneBook.getContact(messageType, recordNo);
        return contact;
    }

    //method that get user input to determine which action must be executed in selectAction method
    private void getAction(MessageType messageType) throws IOException {
        String action;
        MESSAGES.printMessageValue(messageType);
        action = SCANNER.nextLine();

        if (action.matches("[0-9]+")) {
            this.contact = getCurrentContact(Integer.parseInt(action), messageType);
            phoneBook.detailsOfContact(contact);
            MESSAGES.printMessageValue(MessageType.RECORD_MENU);
            action = SCANNER.nextLine();
        }
        selectAction(action);
    }

    //method that based on user input invokes given method from thia class
    private void selectAction(String action) throws IOException {
        switch (action) {
            case "add":
                add();
                SerializationUtils.serialize(phoneBook, FILENAME);
                break;
            case "edit":
                edit();
                SerializationUtils.serialize(phoneBook, FILENAME);
                break;
            case "delete":
                delete();
                SerializationUtils.serialize(phoneBook, FILENAME);
                break;
            case "list":
                list();
                break;
            case "count":
                count();
                break;
            case "exit":
                exit();
                break;
            case "search":
            case "again":
                search();
                break;
            case "back":
            case "menu":
            default:
                System.out.println();
                mainMenu();
                break;
        }
    }


}



