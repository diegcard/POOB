import java.util.ArrayList;
import java.util.List;

public class Foundation {

  private String name;
  private String nit;
  private boolean active;
  private User user;
  private Location location;
  private ArrayList<Pet> pets;

  public ArrayList<Pet> getNearbyPets(Location l) {
    ArrayList<Pet> availablePetsForAdoption = new ArrayList<>();
    if (l.getNeighborhood().equals(location.getNeighborhood())) {
      for (Pet p : pets) {
        if (!p.isAdopted()) {
          availablePetsForAdoption.add(p);
        }
      }
    }
    return availablePetsForAdoption;
  }

  public ArrayList<Pet> getPetsWithOutFiles() {
    ArrayList<Pet> petsWithOutFiles = new ArrayList<>();
    for (Pet p : pets) {
      if (p.getFiles().size() > 0) {
        petsWithOutFiles.add(p);
      }
    }
    return petsWithOutFiles;
  }

  public String getName() {
    return name;
  }

  public String getNit() {
    return nit;
  }

  public boolean isActive() {
    return active;
  }

  public User getUser() {
    return user;
  }

  public Location getLocation() {
    return location;
  }

  public ArrayList<Pet> getPets() {
    return pets;
  }

  public void addNewPets(List<Pet> newPets) {
    pets.addAll(newPets);
  }
}
