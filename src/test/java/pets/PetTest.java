package pets;

import backend.application.Constants;
import backend.application.PetContext;
import com.google.gson.reflect.TypeToken;
import io.cucumber.datatable.DataTable;
import backend.managers.APIManager;
import org.junit.Assert;
import backend.pojos.petItem.Category;
import backend.pojos.petItem.PetItem;
import backend.pojos.petItem.TagsItem;
import backend.util.JsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PetTest extends PetContext {

    static final Logger logger = Logger.getLogger(PetContext.class.getName());

    public static void getPets(String status) {
        List<String> statusList = new ArrayList<>();
        statusList.add(status);
        PetTest.setValue(Constants.getByStatusResponse, APIManager.getPets(statusList));
    }

    public static void validateStatus(String status) {
        String getByStatusResponse = PetTest.getValue(Constants.getByStatusResponse);
        Type type = new TypeToken<ArrayList<PetItem>>(){}.getType();
        List<PetItem> petItemList = (ArrayList) JsonUtil.fromGson(getByStatusResponse, type);

        boolean assertBool = true;
        for (PetItem petItem: petItemList){
            if (!petItem.getStatus().equalsIgnoreCase(status)){
               String mismatch =  String.format("For Pet Id, %s - Expected Status %s, Actual Status %s",
                        petItem.getId(),status,petItem.getStatus());
               logger.info(mismatch);
               assertBool = false;
            }
        }
        Assert.assertTrue("Pet Status is not same as expected",assertBool);
    }

    public static void createPet(DataTable dt) {

        List<Map<String,String>> dataTableAsMapList = dt.asMaps();
        Map<String,String> map = dataTableAsMapList.get(0);
        PetItem petItem = getPet(map);
        PetContext.setValue(Constants.petID,String.valueOf(petItem.getId()));
        PetContext.setValue(Constants.petName,String.valueOf(petItem.getName()));
        APIManager.createPet(JsonUtil.toJson(petItem));
    }

    private static PetItem getPet(Map<String, String> map) {

        PetItem petItem = new PetItem();

        long id = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        petItem.setId(id);
        petItem.setName(map.get("name"));

        List<String> photoUrlString = new ArrayList<>();
        photoUrlString.add(map.get("photoUrls"));
        petItem.setPhotoUrls(photoUrlString);

        TagsItem tagsItem = new TagsItem();
        tagsItem.setId(id);
        tagsItem.setName(map.get("name"));
        List<TagsItem> tagsItems = new ArrayList<>();
        petItem.setTags(tagsItems);

        Category category = new Category();
        category.setId(id);
        category.setName(map.get("name"));
        petItem.setCategory(category);

        petItem.setStatus("available");
        return petItem;
    }

    public static void validatePetCreated() {

        String petId = PetContext.getValue(Constants.petID);
        String response = APIManager.getPetById(petId);
        PetItem petItem = (PetItem) JsonUtil.fromGson(response,PetItem.class);

        Assert.assertEquals(String.valueOf(petItem.getId()),PetContext.getValue(Constants.petID));
        Assert.assertEquals(petItem.getName(),PetContext.getValue(Constants.petName));
    }

    public static void updatePetStatus(String status) {
        String petId = PetContext.getValue(Constants.petID);
        APIManager.updatePet(petId, "sold");
    }

    public static void validateStatusForAPet(String status) {
        String petId = PetContext.getValue(Constants.petID);
        String response = APIManager.getPetById(petId);
        PetItem petItem = (PetItem) JsonUtil.fromGson(response,PetItem.class);
        Assert.assertEquals(status,petItem.getStatus());

    }

    public static void deletePet() {
        String petId = PetContext.getValue(Constants.petID);
        APIManager.deletPet(petId);
    }
}
