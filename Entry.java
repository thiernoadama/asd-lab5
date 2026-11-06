public class Entry {
    private final String snils;
    private String fullName;

    public Entry(String snils, String fullName) {
        this.snils = snils;
        this.fullName = fullName;
    }

    public String getSnils() {
        return snils;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return snils + " | " + fullName;
    }
}
