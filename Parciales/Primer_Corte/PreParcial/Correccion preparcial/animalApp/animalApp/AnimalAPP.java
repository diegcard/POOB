import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimalAPP {
  private HashMap<String, User> users;
  private HashMap<String, Foundation> foundations;

  public List<Pet> getNearbyPets(String userEmail) {
    User u = users.get(userEmail);
    ArrayList<Pet> nearPetsForAdoption = new ArrayList<>();
    if (u != null) {
      Location l = u.getLocation();
      for (Foundation f : foundations.values()) {
        nearPetsForAdoption.addAll(f.getNearbyPets(l));
      }

      for (User us : users.values()) {
        if (us.getLocation().getNeighborhood().equals(u.getLocation().getNeighborhood())) {
          nearPetsForAdoption.addAll(us.getNearbyPets());
        }
      }
    }
    return nearPetsForAdoption;
  }


  // PRACTICE EXERCISES

  /**
   * EXERCISE 1
   * Schedule the adoption interview for a specific user .
   *
   * @param userEmail
   * @param petName
   * @param date
   * @return
   */
  public void scheduleInterview(String userEmail, String petName, LocalDate date) {
    User u = users.get(userEmail);
    if (u != null) {
      u.scheduleInterview(petName, date);
    }
  }

  /**
   * EXERCISE 2
   * get the list of all the pets that doesn't have any file associated
   *
   * @return
   */
  public ArrayList<Pet> getPetsWithOutFiles() {
    ArrayList<Pet> petsWithOutFiles = new ArrayList<>();
    for (Foundation f : foundations.values()) {
      petsWithOutFiles.addAll(f.getPetsWithOutFiles());
    }
    for (User u : users.values()) {
      petsWithOutFiles.addAll(u.getPetsWithOutFiles());
    }
    return petsWithOutFiles;
  }

  /**
   * EXERCISE 3
   * when a foundation close all the pets must be relocated in the foundations that are nearby
   * A foundation can be considered close if it is in the same neighborhood
   * when all the pets are relocated, the foundation should be deleted from animalApp
   *
   * @param nit
   */
  public boolean closeFoundation(String nit) {
    ArrayList<Foundation> availableFoundations = new ArrayList<>();
    Foundation f = foundations.get(nit);
    if (f != null) {
      for (Foundation foundation : foundations.values()) {
        if (!f.equals(foundation) && f.getLocation().getNeighborhood().equals(foundation.getLocation().getNeighborhood())) {
          availableFoundations.add(foundation);
        }
      }
      if (availableFoundations.size() > 0) {
        redistributePets(availableFoundations, f);
      }
      foundations.remove(nit);
      return true;
    }
    return false;
  }

  /**
   * this method redistribute the pets of a foundation in other foundations
   *
   * @param availableFoundations
   * @param foundationToClose
   */
  private void redistributePets(ArrayList<Foundation> availableFoundations, Foundation foundationToClose) {
    int totalPets = foundationToClose.getPets().size();
    int totalFoundations = availableFoundations.size();
    int petsPerFoundation = totalPets / totalFoundations;
    int remainder = totalPets % totalFoundations;

    int index = 0;
    for (int i = 0; i < totalFoundations; i++) {
      int sublistSize = petsPerFoundation + (i < remainder ? 1 : 0);
      List<Pet> sublist = new ArrayList<>(sublistSize);
      for (int j = 0; j < sublistSize; j++) {
        sublist.add(foundationToClose.getPets().get(index++));
      }
      availableFoundations.get(i).addNewPets(sublist);
    }
  }

}
