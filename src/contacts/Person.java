package contacts;


class Person extends Contact {

    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    private final String noData= "[no data]";


    Person() { }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {

        if (Validation.isBirthdateCorrect(birthDate)) {
            this.birthDate = birthDate;
        } else {
            this.birthDate = noData;
            messages.printMessageValue(MessageType.CONTACT_BIRTHDATE);
        }
    }

    public void setGender(String gender) {

        if (Validation.isGenderCorrect(gender)) {
            this.gender = gender;
        } else {
            this.gender = noData;
            messages.printMessageValue(MessageType.CONTACT_GENDER);
        }
    }


    @Override
    public String getShortContactInfo() {
        return getName() + " " + getSurname();
    }

    @Override
    public String getFullContactInfo() {
        return getShortContactInfo() + " " + getBirthDate() + " " + getGender() + " " + getNumber();
    }


    @Override
    public String getFields() {
        return "name, surname, birth, gender, number";
    }

    @Override
    void setField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirthDate(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setNumber(value);
                break;
            default:

        }
    }

    @Override
    void getDetails() {
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + this.getBirthDate());
        System.out.println("Gender: " + this.getGender());
    }


}