package stepdefs;

import backend.application.PetContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pets.PetTest;

public class StepDefs extends PetContext {

    public StepDefs(PetTest petTest) {
    }

    @When("Get {} pets")
    public void get_available_pets(String status) {
        PetTest.getPets(status);
    }


    @Then("Validate pet status as {}")
    public void validate_pet_status_as_available(String status) {
        PetTest.validateStatus(status);

    }


    @When("Create New Pet with Random Id$")
    public void createNewPetWithRandomId(DataTable dt) {
        PetTest.createPet(dt);
    }

    @Then("Validate The Pet X is created")
    public void validateThePetXIsCreated() {
        PetTest.validatePetCreated();
    }

    @When("Update The Pet X status as {}")
    public void updateThePetXStatusAsSold(String status) {
        PetTest.updatePetStatus(status);
    }

    @Then("Validate The Pet X has status {}")
    public void validateThePetXHasStatusSold(String status) {
        PetTest.validateStatusForAPet(status);
    }

    @When("Delete The Pet X")
    public void deleteThePetX() {
        PetTest.deletePet();
    }

}
