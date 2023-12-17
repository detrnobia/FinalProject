package dormsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class PermitFiling extends Resident {
    private String permitType;

    public PermitFiling(String name, String roomNumber, String permitType) {
    	super(name, roomNumber);
        this.permitType = permitType;
    }

    public String getPermitType() {
        return permitType;
    }

    public void filePermit() {
        // Validate filing time
        if (!isValidFilingTime()) {
            System.out.println("Permits can only be filed on or before 6:00 PM.");
            return;
        }

        // Get additional information for the permit
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter time in (HH:mm): ");
        String timeIn = scanner.nextLine();

        // Check if it's a late permit and validate time
        if ("late permit".equalsIgnoreCase(permitType)) {
            if (!isValidLatePermitTime(timeIn)) {
                System.out.println("Invalid time for late permit. Must be before 11:00 PM.");
                return;
            }

            // Check late permit offenses
            if (getLatePermitOffenses() >= 3) {
                System.out.println("You have reached the maximum number of late permit offenses (3).");
                return;
            }
        }

        // Create a CSV line with the permit information
        String csvLine = getName() + "," + getRoomNumber() + "," + permitType + "," + timeIn;

        // Save the permit to a CSV file
        savePermitToFile(csvLine);

        // If it's a late permit, increment the offenses
        if ("late permit".equalsIgnoreCase(permitType)) {
            incrementLatePermitOffenses();
            System.out.println("Late permit filed successfully. Offense count: " + getLatePermitOffenses());
        } else {
            System.out.println("Permit filed successfully.");
        }
    }

    private boolean isValidLatePermitTime(String timeIn) {
        String[] timeParts = timeIn.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        return hour <= 23 && hour <= 11;
    }

    private boolean isValidFilingTime() {
        // Assuming the filing time is up to 6:00 PM
        Date currentDate = new Date();
        int currentHour = currentDate.getHours();
        int currentMinute = currentDate.getMinutes();
        return currentHour <= 18 && currentMinute <= 0;
    }

    private void savePermitToFile(String csvLine) {
        try (FileWriter writer = new FileWriter("permits.csv", true)) {
            writer.write(csvLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
