package org.openmrs.module.hospitalcore.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static void main(String[] args) throws Exception {
        Date tt = getDateFromStr("13/12/2020");
        System.out.println(getAgeFromBirthday(tt));
    }

    public static Integer getAgeFromBirthday(Date birthday) {
        if (birthday == null)
            return null;
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(birthday);
        Calendar today = Calendar.getInstance();
        int age = today.get(1) - dateOfBirth.get(1);
        dateOfBirth.add(1, age);
        if (today.before(dateOfBirth))
            age--;
        return Integer.valueOf(age);
    }

    public static long getDateStr(String date) {
        long result = 0L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            sdf.setLenient(false);
            result = sdf.parse(date).getTime();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return result;
    }

    public static Date getDateFromStr(String date) {
        Date result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            result = sdf.parse(date);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return result;
    }

    public static boolean isValidDateStr(String date, String format) {
        try {
            if (date.length() != format.length())
                return false;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static long getCurrentDate() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String getYYYYMMDD() {
        SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        return formatterYYYYMMDD.format(Calendar.getInstance().getTime());
    }

    public static String getYYYYMMDDEx() {
        SimpleDateFormat formatterYYYYMMDDEx = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        return formatterYYYYMMDDEx.format(Calendar.getInstance().getTime());
    }

    public static String getYYYYMMDDHHMM() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(Calendar.getInstance().getTime());
    }

    public static String getDDMMYYYY() {
        SimpleDateFormat formatterDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
        return formatterDDMMYYYY.format(Calendar.getInstance().getTime());
    }

    public static String getDDMMYYY_HHMM() {
        SimpleDateFormat DDMMYYYY_HHMM = new SimpleDateFormat("ddMMyyyy-HHmm");
        return DDMMYYYY_HHMM.format(Calendar.getInstance().getTime());
    }

    public static String getYYYY() {
        SimpleDateFormat formatterYYYY = new SimpleDateFormat("yyyy");
        return formatterYYYY.format(Calendar.getInstance().getTime());
    }

    public static String getMM() {
        SimpleDateFormat formatterMM = new SimpleDateFormat("MM");
        return formatterMM.format(Calendar.getInstance().getTime());
    }

    public static String getMorMM() {
        SimpleDateFormat formatterMM = new SimpleDateFormat("MM");
        String ret = formatterMM.format(Calendar.getInstance().getTime());
        return ret.startsWith("0") ? ret.substring(1, 2) : ret;
    }

    public static String convertFromLongToString(SimpleDateFormat fm, long date) {
        return fm.format(Long.valueOf(date));
    }

    public static Timestamp createTimestamp() {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp createDateTimestamp(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp String2Timestamp(String strInputDate) {
        String strDate = strInputDate;
        String strSub = null;
        int i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        int nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if (i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        if (strDate.length() < 4)
            if (strDate.substring(0, 1).equals("9")) {
                strDate = "19" + strDate.trim();
            } else {
                strDate = "20" + strDate.trim();
            }
        int nYear = (new Integer(strDate)).intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getDateTimeString(Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    public static String getDateString(Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts);
    }

    public static String getTimeString(Timestamp ts) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
    }

    public static String Timestamp2DDMMYYYY(Timestamp ts) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        if (calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if (calendar.get(2) + 1 < 10)
            return strTemp + "/0" + (calendar.get(2) + 1) + "/" + calendar.get(1);
        return strTemp + "/" + (calendar.get(2) + 1) + "/" + calendar.get(1);
    }

    public String Timestamp2MMDDYYYY(Timestamp ts) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        if (calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if (calendar.get(2) + 1 < 10)
            return "0" + (calendar.get(2) + 1) + "/" + strTemp + "/" + calendar.get(1);
        return (calendar.get(2) + 1) + "/" + strTemp + "/" + calendar.get(1);
    }

    public String Timestamp2DDMMYY(Timestamp ts) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        int endYear = calendar.get(1) % 100;
        if (calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if (calendar.get(2) + 1 < 10) {
            if (endYear < 10)
                return strTemp + "/0" + (calendar.get(2) + 1) + "/0" + endYear;
            return strTemp + "/0" + (calendar.get(2) + 1) + "/" + endYear;
        }
        if (endYear < 10)
            return strTemp + "/" + (calendar.get(2) + 1) + "/0" + endYear;
        return strTemp + "/" + (calendar.get(2) + 1) + "/" + endYear;
    }

    public String Timestamp2DMYY(Timestamp ts) {
        int endYear = 0;
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        endYear = calendar.get(1) % 100;
        String strTemp = Integer.toString(calendar.get(5));
        if (endYear < 10)
            return strTemp + "/" + (calendar.get(2) + 1) + "/0" + endYear;
        return strTemp + "/" + (calendar.get(2) + 1) + "/" + endYear;
    }

    public String Timestamp2DMYYYY(Timestamp ts) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        return strTemp + "/" + (calendar.get(2) + 1) + "/" + calendar.get(1);
    }

    public String Timestamp2YYYYMMDD(Timestamp ts) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        int endYear = calendar.get(1);
        if (calendar.get(5) < 10)
            strTemp = "0" + strTemp;
        if (calendar.get(2) + 1 < 10)
            return endYear + "/0" + (calendar.get(2) + 1) + "/" + strTemp;
        return endYear + "/" + (calendar.get(2) + 1) + "/" + strTemp;
    }

    public static String Timestamp2HHMMSS(Timestamp ts, int iStyle) {
        String strTemp;
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        if (iStyle == 0) {
            strTemp = Integer.toString(calendar.get(11));
        } else {
            strTemp = Integer.toString(calendar.get(11));
        }
        if (strTemp.length() < 2)
            strTemp = "0" + strTemp;
        if (calendar.get(12) < 10) {
            strTemp = strTemp + ":0" + calendar.get(12);
        } else {
            strTemp = strTemp + ":" + calendar.get(12);
        }
        if (calendar.get(13) < 10) {
            strTemp = strTemp + ":0" + calendar.get(13);
        } else {
            strTemp = strTemp + ":" + calendar.get(13);
        }
        if (iStyle != 0)
            if (calendar.get(9) == 0) {
                strTemp = strTemp + " AM";
            } else {
                strTemp = strTemp + " PM";
            }
        return strTemp;
    }

    public String getDateTime24hString(Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0);
    }

    public String getDateTime12hString(Timestamp ts) {
        if (ts == null)
            return "";
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    public static String timestampPlusDay2DDMMYYYY(Timestamp ts, int iDayPlus) {
        if (ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        int iDay = calendar.get(5);
        calendar.set(5, iDay + iDayPlus);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return Timestamp2DDMMYYYY(tsNew);
    }

    public static long timestampPlusDay(Timestamp ts, int iDayPlus) {
        if (ts == null)
            return -1L;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ts.getTime());
        int iDay = calendar.get(5);
        calendar.set(5, iDay + iDayPlus);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return tsNew.getTime();
    }

    public static String getDateFromRange(String date, int rangeDate) {
        SimpleDateFormat formatterDDMMYYYYEx = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateFromStr(date));
        int iDay = calendar.get(5);
        calendar.set(5, iDay + rangeDate);
        return formatterDDMMYYYYEx.format(calendar.getTime());
    }

    public Timestamp getPreviousDate(Timestamp ts) {
        if (ts == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        int iDay = calendar.get(5);
        calendar.set(5, iDay - 1);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return tsNew;
    }

    public Timestamp getNextDate(Timestamp ts) {
        if (ts == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        int iDay = calendar.get(5);
        calendar.set(5, iDay + 1);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return tsNew;
    }

    public int getDayOfWeek(Timestamp ts) {
        if (ts == null)
            return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        int iDay = calendar.get(7);
        return iDay;
    }

    public String getLastestDateOfMonth(String strMonthYear) {
        String strDate = strMonthYear;
        String strSub = null;
        int i = strDate.indexOf("/");
        if (i < 0)
            return "";
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub)).intValue();
        strDate = strDate.substring(i + 1);
        int nYear = (new Integer(strDate)).intValue();
        boolean leapyear = false;
        if (nYear % 100 == 0) {
            if (nYear % 400 == 0)
                leapyear = true;
        } else if (nYear % 4 == 0) {
            leapyear = true;
        }
        if (nMonth == 2) {
            if (leapyear)
                return "29/" + strDate;
            return "28/" + strDate;
        }
        if (nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7 || nMonth == 8 || nMonth == 10 || nMonth == 12)
            return "31/" + strDate;
        if (nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11)
            return "30/" + strDate;
        return "";
    }

    public Timestamp getFriday(Timestamp ts) {
        if (ts == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        int iDoW = getDayOfWeek(ts);
        if (iDoW == 1)
            iDoW = 8;
        int k = 6 - iDoW;
        calendar.setTime(new Date(ts.getTime()));
        int iDay = calendar.get(5);
        calendar.set(5, iDay + k);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return tsNew;
    }

    public boolean isFriday(Timestamp ts) {
        if (ts == null)
            return false;
        Calendar calendar = Calendar.getInstance();
        if (getDayOfWeek(ts) == 6)
            return true;
        return false;
    }

    public static Timestamp getTimestamp(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            Timestamp date = new Timestamp(dateFormat.parse(dateStr).getTime());
            return date;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return null;
        }
    }

    public Timestamp getNextDateN(Timestamp ts, int n) {
        if (ts == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        calendar.add(5, n);
        Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
        return tsNew;
    }

    public static int getDaysDiff(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int d1 = cal.get(5);
        cal.setTime(date2);
        int d2 = cal.get(5);
        return d2 - d1;
    }
}
