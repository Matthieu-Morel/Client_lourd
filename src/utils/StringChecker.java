package utils;

public class StringChecker {
    public static boolean isNumeric(String string) {
        try {
            string = string.replace(",", ".");
            Double test = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean containsOnlyDigits(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInteger(String string) {
        try {
            int test = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}