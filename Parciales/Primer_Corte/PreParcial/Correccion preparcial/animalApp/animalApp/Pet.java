import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet {

  private String name;
  private LocalDate birthDate;
  private char sex;
  private String description;
  private boolean adopted;
  private ArrayList<File> files;
  private ArrayList<Request> requests;

  public boolean isAdopted() {
    return adopted;
  }

  public String getName() {
    return name;
  }

  public ArrayList<File> getFiles() {
    return files;
  }

  public void addNewPets(List<Pet> sublist) {
  }
}
