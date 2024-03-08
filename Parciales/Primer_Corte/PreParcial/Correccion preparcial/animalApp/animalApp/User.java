import java.time.LocalDate;
import java.util.ArrayList;

public class User {

  private String name;
  private String email;
  private String cellPhone;
  private boolean active;
  private LocalDate birthDate;
  private boolean hasPet;
  private Location location;
  private ArrayList<Reference> references;
  private ArrayList<Request> requests;
  private ArrayList<Pet> pets;

  public Location getLocation() {
    return location;
  }

  public ArrayList<Pet> getNearbyPets() {
    ArrayList<Pet> availablePetsForAdoption = new ArrayList<>();
    for (Pet p : pets) {
      if (!p.isAdopted()) {
        availablePetsForAdoption.add(p);
      }
    }
    return availablePetsForAdoption;
  }


  public void scheduleInterview(String petName, LocalDate date) {
    for (Request r : requests) {
      r.scheduleInterview(petName, date);
    }
  }

  public ArrayList getPetsWithOutFiles() {
    ArrayList<Pet> petsWithOutFiles = new ArrayList<>();
    for (Pet p : pets) {
      if (p.getFiles().size() > 0) {
        petsWithOutFiles.add(p);
      }
    }
    return petsWithOutFiles;
  }
}
