package controller;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.a*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public validator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }


    public boolean emailValidate(String email) {
        String EmailCheck ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,255}$";
        Pattern pattern1 = Pattern.compile(EmailCheck);
        return pattern1.matcher(email).matches();
    }
    //This method is used to change the date format of the JFXDatePicker

    public void changeDatePickerFormat(DatePicker jfxDatePicker)

    {

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        jfxDatePicker.setConverter(new StringConverter<LocalDate>()

        {
            @Override
            public String toString(LocalDate object) {
                if(object!=null)
                {
                    return dateTimeFormatter.format(object);
                }
                return null;
            }

            @Override

            public LocalDate fromString(String string)
            {

                if(string !=null && !string.trim().isEmpty())

                {

                    return LocalDate.parse(string,dateTimeFormatter);

                }

                return null;
            }
        });
    }



    //This method prevents users from choosing future dates from the JFXDatePicker

    public boolean pastDates(DatePicker jfxDatePicker)

    {
        boolean check=false;
        LocalDate today=LocalDate.now();
        LocalDate date=jfxDatePicker.getValue();

        if(!(date==null || date.isAfter(today.now()))){
            check=true;

        }
        return check;

    }



    //changes the date format to dd/MM/yyyy

    public String changeDateFormat(LocalDate date)

    {

        DateTimeFormatter DateFormat =DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return date.format(DateFormat);

    }




}