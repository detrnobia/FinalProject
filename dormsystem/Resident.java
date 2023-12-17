package dormsystem;

public class Resident {
    private String name;
    private String roomNumber;
    private int latePermitOffenses;

    public Resident(String name, String roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.latePermitOffenses = 0;
    }

    public String getName() {
        return name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getLatePermitOffenses() {
        return latePermitOffenses;
    }

    public void incrementLatePermitOffenses() {
        this.latePermitOffenses++;
    }
}

