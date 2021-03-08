package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Pattern pattern;
    private static Matcher matcher;

    //method validates if birthdate has correct date format and range (from 1900-01-01 to today)
    protected static boolean isBirthdateCorrect(String birthdate) {
        pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        matcher = pattern.matcher(birthdate);

        // create formatter and convert birthdate String to LocalDate
        if (matcher.matches()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate date = LocalDate.parse(birthdate, formatter);
                return date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.parse("1900-01-01", formatter));
            } catch (DateTimeParseException dateTimeParseException) {
                dateTimeParseException.getStackTrace();
            }
        }
        return false;
    }

    //method validates if input for gender is correct (M as Male and F as Female)
    protected static boolean isGenderCorrect(String type) {

        pattern = Pattern.compile("[MF]");
        matcher = pattern.matcher(type);

        return matcher.matches();
    }

    /*mehod validates if format of telephone number is correct:
    1. The phone number should be split into groups using a space or dash. One group is also possible.
    2. Before the first group, there may or may not be a plus symbol.
    3. The first group or the second group can be wrapped in parentheses, but there should be no more than one group which is wrapped in parentheses. There may be no groups wrapped in parentheses.
    4. A group can contain numbers, uppercase, and lowercase English letters. A group should be at least 2 symbols in length. But the first group may be only one symbol in length.
     */
    protected static boolean isNumberCorrect(String number) {

        pattern = Pattern.compile("^(\\+?(\\([\\da-zA-Z]*\\))([- ?][\\da-zA-Z]*)*)$|^(\\+?([\\da-zA-Z]+)([- ?]\\(?[\\da-zA-Z]*\\)?)*)$");
        matcher = pattern.matcher(number);

        return matcher.matches();
    }

    //method validates if given record number is in scope of phonebook list
    protected static boolean isRecordNumCorrect(int recordNo, int size) {

        return (recordNo < size && recordNo >= 0);
    }

    //method validates if record type is correct (person or organization)
    protected static boolean isTypeCorrect(String type) {

        pattern = Pattern.compile("organization|person");
        matcher = pattern.matcher(type);

        return matcher.matches();
    }




}
