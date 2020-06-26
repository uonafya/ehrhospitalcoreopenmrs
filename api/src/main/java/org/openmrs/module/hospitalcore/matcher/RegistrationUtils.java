package org.openmrs.module.hospitalcore.matcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.openmrs.api.context.Context;

public class RegistrationUtils {
    public static String getNewIdentifier() {
        Calendar now = Calendar.getInstance();
        String shortName = Context.getAdministrationService().getGlobalProperty("registration.identifier_prefix");
        String noCheck = shortName + String.valueOf(now.get(1)).substring(2, 4) + String.valueOf(now.get(2) + 1) + String.valueOf(now.get(5)) + String.valueOf(now.get(12)) + String.valueOf((new Random()).nextInt(999));
        return noCheck + "-" + getCheckdigit(noCheck);
    }

    public static String getBase30Identifier(int length, String startID) {
        String validChars = "0123456789ACDEFGHJKLMNPRSTUVWXY";
        boolean allY = false;
        for (int i = 0; i < startID.length(); i++) {
            if (startID.charAt(i) == 'Y') {
                allY = true;
            } else {
                allY = false;
                break;
            }
        }
        if (startID.length() > length) {
            System.out.println("your identifier length is more than fixed langth");
            return "";
        }
        for (int index = startID.length() - 1; index >= 0; index--) {
            char toIncrement = startID.charAt(index);
            int diff = validChars.length() - 1 - validChars.indexOf(toIncrement);
            if (diff == 0) {
                if (index == startID.length() - 1) {
                    startID = startID.substring(0, index) + validChars.charAt(0) + startID.substring(index, startID.length() - 1);
                } else {
                    startID = startID.substring(0, index) + validChars.charAt(0) + startID.substring(index + 1);
                    if (startID.equals("0"))
                        break;
                }
            } else {
                startID = startID.substring(0, index) + validChars.charAt(validChars.indexOf(toIncrement) + 1) + startID.substring(index + 1);
                break;
            }
        }
        if (startID.equals("0")) {
            System.out.println("Cannot generate more identifiers");
        } else if (allY) {
            startID = "1" + startID;
        }
        return startID + "-" + getCheckdigit(startID);
    }

    private static int getCheckdigit(String idWithoutCheckdigit) {
        String validChars = "0123456789ACDEFGHJKLMNPRSTUVWXY";
        idWithoutCheckdigit = idWithoutCheckdigit.trim().toUpperCase();
        int sum = 0;
        for (int i = 0; i < idWithoutCheckdigit.length(); i++) {
            int weight;
            char ch = idWithoutCheckdigit.charAt(idWithoutCheckdigit.length() - i - 1);
            if (validChars.indexOf(ch) == -1)
                System.out.println("\"" + ch + "\" is an invalid character");
            int digit = ch - 48;
            if (i % 2 == 0) {
                weight = 2 * digit - digit / 5 * 9;
            } else {
                weight = digit;
            }
            sum += weight;
        }
        sum = Math.abs(sum) + 10;
        return (10 - sum % 10) % 10;
    }

    public static String getAgeFromBirthDate(Date birth, boolean estimate) {
        String result = "";
        Calendar now = Calendar.getInstance();
        Calendar bd = Calendar.getInstance();
        bd.setTime(birth);
        long ONE_DAY = 86400000L;
        long date1_ms = now.getTimeInMillis();
        long date2_ms = bd.getTimeInMillis();
        long difference_ms = Math.abs(date1_ms - date2_ms);
        float noOfDay = (float)(difference_ms / ONE_DAY);
        int day = Math.round(noOfDay);
        String est = "";
        if (estimate)
            est = "~";
        if (bd.get(1) < now.get(1) && day > now.getActualMaximum(6)) {
            int year = now.get(1) - bd.get(1);
            if (year == 1) {
                result = est + year + " year";
            } else {
                result = est + year + " years";
            }
        } else if (day > 31) {
            int month = 0;
            if (bd.get(1) < now.get(1))
                month = 12 - bd.get(2) + now.get(2);
            if (bd.get(1) == now.get(1))
                month = now.get(2) - bd.get(2);
            if (month == 1) {
                result = result + est + month + " month";
            } else {
                result = result + est + month + " months";
            }
        } else if (day == 1) {
            result = result + est + day + "day";
        } else {
            result = result + est + day + " days";
        }
        return result;
    }
}
