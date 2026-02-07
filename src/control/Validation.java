/**
 * GENIUS SOFT CONFIDENTIAL
 *
 * [2015] - [2015] Genius Soft (PVT) LTD
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Genius Soft (PVT) LTD and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Genius Soft (PVT) LTD and its
 * suppliers and may be covered by Sri Lanka and Foreign Patents, patents in process, and are protected by trade secret
 * or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Genius Soft (PVT) LTD.
 */
package control;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperPrint;

public class Validation {

    public static int getIntFromString(String value) {
        return Integer.parseInt(value);
    }

    public static int getIntOrZeroFromString(String value) {
        if (isInteger(value)) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    public static BigDecimal getBigDecimalFromString(String value) {
        return getBigDecimalOrZeroFromString(value);
    }

    public static BigDecimal getBigDecimalOrZeroFromString(String value) {
        if (value != null) {
            value = value.replaceAll(",", "");
        }
        if (isBigDecimal(value)) {
            return new BigDecimal(value);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBigDecimal(String value) {
        try {
            if (value != null) {
                value = value.replaceAll(",", "");
            }
            new BigDecimal(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNotEmpty(String value) {
        return (value != null && !value.trim().isEmpty());
    }

    public static String formatWithTwoDigits(String value) {
//        if (value.isEmpty()) {
//            value = "0";
//        }
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(false);
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
        return NF.format(Double.parseDouble(value));
    }

    public static String formatWithOutDigitsWithGrouping(String value) {
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(true);
        NF.setMinimumFractionDigits(0);
        NF.setMaximumFractionDigits(0);
        return NF.format(Double.parseDouble(value.replaceAll(",", "")));
    }

    public static String formatWithTwoDigitsWithGrouping(String value) {
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(true);
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
        return NF.format(Double.parseDouble(value.replaceAll(",", "")));
    }

    public static String formatWithThreeDigitsWithGrouping(String value) {
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(true);
        NF.setMinimumFractionDigits(3);
        NF.setMaximumFractionDigits(3);
        return NF.format(Double.parseDouble(value.replaceAll(",", "")));
    }

    public static BigDecimal getBigDecimalByFormattedString(String value) {
        return getBigDecimalOrZeroFromString(value);
    }

    public static String formatWithThreeDigits(String value) {
        NumberFormat NF = NumberFormat.getInstance();
        NF.setGroupingUsed(false);
        NF.setMinimumFractionDigits(0);
        NF.setMaximumFractionDigits(3);
        return NF.format(Double.parseDouble(value.replaceAll(",", "")));
    }

    public static Date getSqlDateByUtilDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static Date getSqlDateByString(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new Date(format.parse(date).getTime());
    }

    public static void checkNullText(JFormattedTextField textField) {
        if (textField.getText().isEmpty()) {
            textField.setValue(null);
        }
    }

    public static String getSelectedItemFromComboBox(JComboBox comboBox) {
        if (comboBox.getSelectedItem() == null) {
            return "";
        } else {
            return comboBox.getSelectedItem().toString();
        }
    }

    public static void checkIntegerText(JTextField textField) {
        if (!isInteger(textField.getText())) {
            textField.setText("");
        }
    }

    public static void setUpperCase(JTextField textField) {
        textField.setText(textField.getText().toUpperCase());
    }
    /**
     *
     * @param monthString
     * @return interger month number eg : December -> 12
     * @throws ParseException
     */
    public static int getMonthNumberFromMonthName(String monthString) throws ParseException {
        java.util.Date date = new SimpleDateFormat("MMM").parse(monthString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static void formatForSinhala(JTextField textField) {
        String orgtext, finaltext, text;
        text = textField.getText();
        finaltext = text;

        int n = textField.getText().length();

        if (n > 1) {

            char last1 = text.charAt(n - 2);
            char last2 = text.charAt(n - 1);
            orgtext = last1 + "" + last2;

            String ss = text.substring(0, text.length() - 2);

            switch (orgtext) {
                case "ra":
                    finaltext = ss + "¾";
                    break;
                case "Ta":
                    finaltext = ss + "´";
                    break;
                case "ua":
                    finaltext = ss + "ï";
                    break;
                case "Ua":
                    finaltext = ss + "ò";
                    break;
                case "Oa":
                    finaltext = ss + "è";
                    break;
                case "pa":
                    finaltext = ss + "É";
                    break;
                case "Pa":
                    finaltext = ss + "þ";
                    break;
                case "ga":
                    finaltext = ss + "Ü";
                    break;
                case "ja":
                    finaltext = ss + "õ";
                    break;
                case "La":
                    finaltext = ss + "Ä";
                    break;
                case "ca":
                    finaltext = ss + "Ê";
                    break;
                case "va":
                    finaltext = ss + "â";
                    break;
                case "na":
                    finaltext = ss + "í";
                    break;

                case "rs":
                    finaltext = ss + "ß";
                    break;
                case "us":
                    finaltext = ss + "ñ";
                    break;
                case "Us":
                    finaltext = ss + "ô";
                    break;
                case "Os":
                    finaltext = ss + "ê";
                    break;
                case "ps":
                    finaltext = ss + "Ñ";
                    break;
                case "Ps":
                    finaltext = ss + "ý";
                    break;
                case "gs":
                    finaltext = ss + "á";
                    break;
                case "js":
                    finaltext = ss + "ú";
                    break;
                case "Ls":
                    finaltext = ss + "Å";
                    break;
                case "cs":
                    finaltext = ss + "ð";
                    break;
                case "vs":
                    finaltext = ss + "ä";
                    break;
                case "ns":
                    finaltext = ss + "ì";
                    break;
                case "os":
                    finaltext = ss + "È";
                    break;
                case "Gs":
                    finaltext = ss + "À";
                    break;
                case ":s":
                    finaltext = ss + "Ó";
                    break;
                case "Vs":
                    finaltext = ss + "Î";
                    break;
                case "Ms":
                    finaltext = ss + "Ý";
                    break;

                case "rS":
                    finaltext = ss + "Í";
                    break;
                case "uS":
                    finaltext = ss + "ó";
                    break;
                case "US":
                    finaltext = ss + "ö";
                    break;
                case "OS":
                    finaltext = ss + "ë";
                    break;
                case "pS":
                    finaltext = ss + "Ö";
                    break;
                case "PS":
                    finaltext = ss + "Â";
                    break;
                case "gS":
                    finaltext = ss + "à";
                    break;
                case "jS":
                    finaltext = ss + "ù";
                    break;
                case "LS":
                    finaltext = ss + "Ç";
                    break;
                case "cS":
                    finaltext = ss + "Ô";
                    break;
                case "vS":
                    finaltext = ss + "ã";
                    break;
                case "nS":
                    finaltext = ss + "î";
                    break;
                case "oS":
                    finaltext = ss + "§";
                    break;
                case "GS":
                    finaltext = ss + "Á";
                    break;
                case ":S":
                    finaltext = ss + "Ò";
                    break;
                case "VS":
                    finaltext = ss + "Ð";
                    break;
                case "MS":
                    finaltext = ss + "Ú";
                    break;

                case "<q":
                    finaltext = ss + "¿";
                    break;

                default:
                    finaltext = text;
                    break;
            }

        } else {
            orgtext = "";
        }

        textField.setText(finaltext);
    }

    public static String getFormattedNumber(String number) {
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(amount);
    }

    public static String getStringWithSpacingBetweenIntegersFromInt(int amount) {
        return (amount / 10) % 10 + "  " + amount % 10;
    }



    public static String removeNonNumeralsFromString(String string) {
        return string.replaceAll("[^\\d]", "");
    }

    public static String removeNonDecimalsFromString(String string) {
        return string.replaceAll("[^-.\\d]", "");
    }

    public static boolean isValidEmail(String email) {
        //validation permitted by RFC 5322
        return (isNotEmpty(email) && !email.startsWith(".") && !email.endsWith(".") && Pattern.compile(
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                .matcher(email).matches());

        /*
        source 1: http://commons.apache.org/proper/commons-validator/apidocs/src-html/org/apache/commons/validator/routines/EmailValidator.html#line.36
        source 2: https://howtodoinjava.com/regex/java-regex-validate-email-address/
         */
    }

    public static String getStringWithoutHTMLTags(String str) {
        return str.replaceAll("\\<.*?\\>", "");
    }

    public static java.util.Date getDateWithoutTime(java.util.Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateFormat.format(date));
    }

    public static String getStringWithoutWhiteSpaces(String string) {
        return string.replaceAll("\\s", "");
    }

    public static boolean isValidNic(String nic) {
        int year;
        int days;

        nic = nic.trim();

        if (!(nic.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$"))) {
            return false;
        }

        switch (nic.length()) {
            case 10:
                year = getIntFromString("19" + nic.substring(0, 2));
                days = getIntFromString(nic.substring(2, 5));

                break;
            case 12:
                year = getIntFromString(nic.substring(0, 4));
                days = getIntFromString(nic.substring(4, 7));

                break;
            default:
                return false;
        }

        if (year == 0 || year > Calendar.getInstance().get(Calendar.YEAR) - 14) {
            return false;
        }

        if (days == 0 || ((days < 500) ? (days > 366) : ((days - 500) > 366))) {
            return false;
        }

        return true;
    }

    public static HashMap<String, Object> getNicDetailsFromNicNo(String nic) {
        HashMap<String, Object> nicDetails = new HashMap<>();
        int year = 0;
        int days = 0;

        nic = nic.trim();

        if (!isValidNic(nic)) {
            nicDetails.put("isValidNIC", false);
            return nicDetails;
        } else {
            nicDetails.put("isValidNIC", true);
        }

        switch (nic.length()) {
            case 10:
                nicDetails.put("isTenDigitNIC", false);
                year = getIntFromString("19" + nic.substring(0, 2));
                days = getIntFromString(nic.substring(2, 5));

                break;
            case 12:
                nicDetails.put("isTenDigitNIC", true);
                year = getIntFromString(nic.substring(0, 4));
                days = getIntFromString(nic.substring(4, 7));

                break;
        }

        if (days > 500) {
            nicDetails.put("isGenderMale", false);
            days -= 500;
        } else {
            nicDetails.put("isGenderMale", true);
        }

        nicDetails.put("yearOfBirth", year);
        nicDetails.put("noOfDaysToDOBFromYearStart", days);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, days);

        nicDetails.put("dob", new Date(calendar.getTimeInMillis()));

        return nicDetails;
    }

    public static Date getDateOfBirthByNicNo(String nicNo) {
        HashMap<String, Object> nicInfo = getNicDetailsFromNicNo(nicNo);

        if ((boolean) nicInfo.get("isValidNIC")) {
            return (Date) nicInfo.get("dob");
        }

        return null;
    }

    public static boolean isTextFeildEditableInUI(JTextField textField) {
        return textField.isEditable() && textField.isEnabled() && textField.isFocusable() && textField.isVisible()
                && textField.isDisplayable() && textField.isShowing();
    }

    public static boolean getBooleanFromString(String value) {
        if (!isNotEmpty(value)) {
            return false;
        }

        value = value.trim();

        if (getIntOrZeroFromString(value) > 0) {
            return true;
        }

        if (Boolean.valueOf(value)) {
            return true;
        }

        return false;
    }
    
    public static boolean isJasperReportNotEmpty(JasperPrint jp) {
        return (jp.getPages().size() > 0);
    }
}
