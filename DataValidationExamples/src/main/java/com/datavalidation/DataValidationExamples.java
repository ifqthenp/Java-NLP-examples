package com.datavalidation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidationExamples
{
    public static void main(String[] args)
    {
        System.out.println(validateInt("23"));
        System.out.println(validateInt("23.5"));
        System.out.println(validateInt("cat"));
        System.out.println();

        String dateFormat = "MM/dd/yyyy";
        System.out.println(validateDate("Friday", dateFormat));
        System.out.println(validateDate("3/12/1990", dateFormat));
        System.out.println(validateDate("03/12/1990", dateFormat));
        System.out.println();

        System.out.println(validateEmail("name@company.com"));
        System.out.println(validateEmail("name@company"));
        System.out.println(validateEmail("name"));
        System.out.println();

        System.out.println(validateZipCode("12345"));
        System.out.println(validateZipCode("12345-1234"));
        System.out.println(validateZipCode("name"));
    }

    private static String validateZipCode(final String zipCodeString)
    {
        String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(zipRegex);
        Matcher matcher = pattern.matcher(zipCodeString);
        if (matcher.matches()) {
            return zipCodeString + " is a valid zip code";
        } else {
            return zipCodeString + " is not a valid zip code";
        }
    }

    public static String validateEmail(final String emailString)
    {
        try {
            InternetAddress testEmail = new InternetAddress(emailString);
            testEmail.validate();
            return emailString + " is a valid email";
        }
        catch (AddressException e) {
            return emailString + " is not a valid email";
        }
    }

    public static String validateDate(final String dateString, final String dateFormat)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(dateString);
            if (simpleDateFormat.format(date).equals(dateString)) {
                return dateString + " is a valid date";
            } else {
                return dateString + " is not a valid date";
            }
        }
        catch (ParseException e) {
            return dateString + " is not a valid date";
        }
    }

    public static String validateInt(final String input)
    {
        try {
            int validInt = Integer.parseInt(input);
            return validInt + " is a valid integer";
        }
        catch (NumberFormatException e) {
            return input + " is not a valid integer";
        }
    }
}
