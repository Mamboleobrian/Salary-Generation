package controller;

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
}