package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

abstract class Contact implements Serializable {
    private String number;
    private LocalDateTime createdDate;
    private LocalDateTime editedDate;

    protected static final transient Messages messages = new Messages();

    // abstract setters
    abstract void setField(String field, String value);

    //setters
    public void setNumber(String number) {
        if (Validation.isNumberCorrect(number)) {
            this.number = number;
        } else {
            this.number = "[no number]";
            messages.printMessageValue(MessageType.CONTACT_NUMBER);
        }
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        this.editedDate = createdDate;
    }

    public void setEditedDate(LocalDateTime editedDate) {
        this.editedDate = editedDate;
    }

    // abstract getters
    abstract String getShortContactInfo();

    abstract String getFullContactInfo();

    abstract String getFields();

    abstract void getDetails();

    //getters
    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate.truncatedTo(ChronoUnit.MINUTES);
    }

    public LocalDateTime getEditedDate() {
        return editedDate.truncatedTo(ChronoUnit.MINUTES);
    }



}
