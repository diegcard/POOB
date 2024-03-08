import java.time.LocalDate;

public class Request {

  private LocalDate date;
  private char status;
  private LocalDate decisionDate;
  private int punctuation;
  private User user;
  private Pet pet;
  private Interview interview;
  private Contract contract;

  /**
   * Create an interview if the pet is valid and the date is greater than the actual date
   *
   * @param petName
   * @param date
   */
  public void scheduleInterview(String petName, LocalDate date) {
    if (validatePet(petName) && date.isAfter(LocalDate.now())) {
      interview = new Interview(date);
    }
  }

  /**
   * validate that the pet name is the same that the petName parameter
   * and checks if the pet is already adopted
   *
   * @param petName
   * @return
   */
  public boolean validatePet(String petName) {
    return pet.getName().equals(petName) && !pet.isAdopted();
  }
}
