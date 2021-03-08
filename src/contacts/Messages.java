package contacts;

public class Messages {
    //console colors
    private static final String RESET_TEXT_COLOR = "\u001B[0m";
    private static final String RED_CONSOLE_TEXT = "\u001B[91m";
    private static final String GREEN_CONSOLE_TEXT = "\u001B[32m";
    //messages
    private static final String MENU = "[menu] Enter action (add, list, search, count, exit): ";
    private static final String TYPE = "Enter the type: (person, organization): ";
    private static final String WRONG_NUMBER = "Wrong number format!\n";
    private static final String WRONG_BIRTHDATE = "Bad birthdate!\n";
    private static final String WRONG_GENDER = "Bad gender!\n";
    private static final String RECORD_ADDED = "A record added.\n\n";
    private static final String RECORD_DELETED = "A record deleted.\n";
    private static final String RECORD_SAVED = "A record saved.\n";
    private static final String RECORD_MENU = "\n[record] Enter action (edit, delete, menu): ";
    private static final String NO_RECORDS = "No records!\n";
    private static final String SEARCH_QUERY = "Enter search query: ";
    private static final String SEARCH_MENU = "\n[search] Enter action ([number], back, again): ";
    private static final String LIST_MENU = "\n[list] Enter action ([number], back): ";

    private String message;

    private String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void printMessage(String message) {
        setMessage(message);
        System.out.print(getMessage());
    }

    private void printMessageGreen(String message) {
        setMessage(message);
        System.out.printf("%s%s%s", GREEN_CONSOLE_TEXT, getMessage(), RESET_TEXT_COLOR);
    }

    private void printMessageRed(String message) {
        setMessage(message);
        System.out.printf("%s%s%s", RED_CONSOLE_TEXT, getMessage(), RESET_TEXT_COLOR);
    }


    protected void printMessageValue(MessageType messageType) {

        switch (messageType.name()) {
            case ("MAIN_MENU") :
                printMessage(MENU);
                break;
            case ("SEARCH_MENU"):
                printMessage(SEARCH_MENU);
                break;
            case ("LIST_MENU"):
                printMessage(LIST_MENU);
                break;
            case ("TYPE"):
                printMessage(TYPE);
                break;
            case ("EMPTY_PHONEBOOK"):
                printMessage(NO_RECORDS);
                break;
            case ("RECORD_MENU"):
                printMessage(RECORD_MENU);
                break;
            case ("SEARCH_QUERY"):
                printMessage(SEARCH_QUERY);
                break;
            case ("CREATED"):
                printMessageGreen(RECORD_ADDED);
                break;
            case ("DELETED"):
                printMessageGreen(RECORD_DELETED);
                break;
            case ("SAVED"):
                printMessageGreen(RECORD_SAVED);
                break;
            case ("CONTACT_NUMBER"):
                printMessageRed(WRONG_NUMBER);
                break;
            case ("CONTACT_BIRTHDATE"):
                printMessageRed(WRONG_BIRTHDATE);
                break;
            case ("CONTACT_GENDER"):
                printMessageRed(WRONG_GENDER);
                break;
        }


    }
}
