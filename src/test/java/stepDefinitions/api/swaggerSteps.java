package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.PetPost.PetExpectedBody;
import pojos.PetPost.Pet_Category;
import pojos.PetPost.TagsInnerBody;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class swaggerSteps {

    Pet_Category petCategory;
    PetExpectedBody petExpectedBody;
    PetExpectedBody petRequestBody;
    TagsInnerBody tagsInnerBody;
    public Response response;
    HooksAPI hooksAPI=new HooksAPI();

    @Given("create the swagger api endpoint with the path parameter {string}")
    public void createTheSwaggerApiEndpointWithThePathParameter(String pathPar) {

        spec.pathParam("pp1",pathPar);
    }

    @And("save the response from the {string} API")
    public void saveTheResponseFromTheAPI(String apiName) {
       if(apiName.equals("post-a-pet")){
           String[] photoUrl={"https://tr.pinterest.com/pin/679480662510500791/"};
           petCategory=new Pet_Category(57,"Dog");
           tagsInnerBody=new TagsInnerBody(23,"kangal");
           TagsInnerBody[] tagsInnerBodies={tagsInnerBody};
           petRequestBody=new PetExpectedBody(554,petCategory,"Kılıc",photoUrl,tagsInnerBodies,"available");
           petExpectedBody=new PetExpectedBody(554,petCategory,"Kılıc",photoUrl,tagsInnerBodies,"available");
           response=given().spec(spec).
                   accept(ContentType.JSON).
                   contentType(ContentType.JSON).
                   when().body(petRequestBody).
                   post("{pp1}");
       } else if (apiName.equals("update-a-pet")) {
           String[] photoUrl={"https://tr.pinterest.com/pin/679480662510500791/"};
           petCategory=new Pet_Category(57,"Dog");
           tagsInnerBody=new TagsInnerBody(23,"Kangal");
           TagsInnerBody[] tagsInnerBodies={tagsInnerBody};
           petRequestBody=new PetExpectedBody(555,petCategory,"Sword of the wisdom",photoUrl,tagsInnerBodies,"available");
           petExpectedBody=new PetExpectedBody(555,petCategory,"Sword of the wisdom",photoUrl,tagsInnerBodies,"available");
           response=given().spec(spec).
                   accept(ContentType.JSON).
                   contentType(ContentType.JSON).
                   when().body(petRequestBody).
                   put("{pp1}");
           
       }
    }

  

    @Then("verify that the expected response and the actual response are the same as each other")
    public void verifyThatTheExpectedResponseAndTheActualResponseAreTheSameAsEachOther() {
        assertEquals(petExpectedBody.getId(),petRequestBody.getId());
        assertEquals(petExpectedBody.getCategory().getId(),petRequestBody.getCategory().getId());
        assertEquals(petExpectedBody.getCategory().getName(),petRequestBody.getCategory().getName());
        assertEquals(petExpectedBody.getName(),petRequestBody.getName());
        assertEquals(petExpectedBody.getPhotoUrls()[0],petRequestBody.getPhotoUrls()[0]);
        assertEquals(petExpectedBody.getTags()[0].getId(),petRequestBody.getTags()[0].getId());
        assertEquals(petExpectedBody.getTags()[0].getName(),petRequestBody.getTags()[0].getName());
        assertEquals(petExpectedBody.getStatus(),petRequestBody.getStatus());
    }

   
}
