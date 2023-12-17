package dormsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DormPermitSystem {
	 public static void main(String[] args) {
		System.out.println("=====================================");
		System.out.println("====== P E R M I L O G I T A L ======");
		System.out.println("=====================================");
		 
		List<Resident> residents = new ArrayList<>();
	        	residents.add(new Resident("Dom Dicar", "20"));
	        	residents.add(new Resident("Earl Pabua", "8"));
			residents.add(new Resident("Apong Erazo", "8"));
			residents.add(new Resident("Emmanuel Albeza", "9"));
			residents.add(new Resident("Albert Einstein", "10"));

	        System.out.println("Dorm Residents:");
	        for (Resident resident : residents) {
	            System.out.println(resident.getName() + " - Room: " + resident.getRoomNumber());
	        } // Hello asdfghjkl;

	        Scanner scanner = new Scanner(System.in); 

	        System.out.println("\nEnter your name: ");
	        String name = scanner.nextLine();

	        System.out.println("Enter your room number: "); 
	        String roomNumber = scanner.nextLine();

	        System.out.println("Enter the permit type (late permit, overnight permit, weekend permit): ");
	        String permitType = scanner.nextLine();

	        Resident resident = findResident(residents, name, roomNumber);

	        if (resident != null) {
	            PermitFiling permitFiling = new PermitFiling(name, roomNumber, permitType);
	            permitFiling.filePermit();
	        } else {
	            System.out.println("Dorm resident not found. Please check your name and room number.");
	        }
	    }

	    private static Resident findResident(List<Resident> residents, String name, String roomNumber) {
	        for (Resident resident : residents) {
	            if (resident.getName().equalsIgnoreCase(name) && resident.getRoomNumber().equalsIgnoreCase(roomNumber)) {
	                return resident;
	            }
	        }
	        return null;
	    }
	}
