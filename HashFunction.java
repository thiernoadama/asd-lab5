public final class HashFunction {
    private static final int PART_LENGTH = 3;

    private HashFunction() {
    }

    public static int fold(String snils, int capacity) {
        String digits = Snils.digits(snils);
        long sum = 0;
        for (int i = 0; i < digits.length(); i += PART_LENGTH) {
            int end = Math.min(i + PART_LENGTH, digits.length());
            sum += Integer.parseInt(digits.substring(i, end));
        }
        return (int) (sum % capacity);
    }
}
