public final class Snils {
    private Snils() {
    }

    public static String normalize(String input) {
        if (input == null || !input.matches("[0-9\\- ]+")) {
            return null;
        }
        String digits = digits(input);
        if (digits.length() != 11) {
            return null;
        }
        return digits.substring(0, 3) + "-" + digits.substring(3, 6) + "-"
                + digits.substring(6, 9) + " " + digits.substring(9);
    }

    public static String digits(String snils) {
        return snils.replaceAll("\\D", "");
    }
}
