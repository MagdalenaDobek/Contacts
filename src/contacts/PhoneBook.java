package contacts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PhoneBook implements Serializable {
    private final List<Contact> phonebookList = new ArrayList<>();
    private final List<Contact> searchResultList = new ArrayList<>();

    private static final transient Messages MESSAGES = new Messages();

    //deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    //method that adds record to main phonebook list
    public void addContact(Contact contact) {

        contact.setCreatedDate(LocalDateTime.now());
        phonebookList.add(contact);
        MESSAGES.printMessageValue(MessageType.CREATED);

    }

    //method that counts records in main phonebook list
    public int countContacts() {
        return phonebookList.size();
    }

    //method that edits records in main phonebook list
    public void editContact(Contact contact, String field, String value) {

        contact.setField(field, value);
        contact.setEditedDate(LocalDateTime.now());
        MESSAGES.printMessageValue(MessageType.SAVED);
    }

    //method that list records in main phonebook list or in search result list
    public void listContacts(MessageType messageType) {
        List<Contact> list = messageType.name().equals("SEARCH_MENU") ? searchResultList : phonebookList;

        for (Contact contact : list) {
            System.out.printf("%d. %s\n", list.indexOf(contact) + 1, contact.getShortContactInfo());
        }
    }

    //method that removes records in main phonebook list
    public void removeContact(Contact contact) {

        phonebookList.remove(contact);
        MESSAGES.printMessageValue(MessageType.DELETED);
    }

    //method that search contacts in main phonebook list and adds them search result list, which is printed
    public void searchContacts(String query, MessageType messageType) {
        searchResultList.clear();

        for (Contact contact : phonebookList) {
            if (contact.getFullContactInfo().matches(query)) {
                searchResultList.add(contact);
            }
        }

        System.out.printf("Found %d result%s: \n", searchResultList.size(), searchResultList.size() == 1 ? "" : "s");
        listContacts(messageType);
    }

    //helper method to get current contact
    public Contact getContact(MessageType messageType, int recordNo) {
        List<Contact> list = messageType.name().equals("SEARCH_MENU") ? searchResultList : phonebookList;

        return list.get(recordNo);
    }

    //method that prints details of contact
    public void detailsOfContact(Contact contact) {

        contact.getDetails();

        System.out.println("Number: " + contact.getNumber());
        System.out.println("Time created: " + contact.getCreatedDate());
        System.out.println("Time last edit: " + contact.getEditedDate());


    }
}
