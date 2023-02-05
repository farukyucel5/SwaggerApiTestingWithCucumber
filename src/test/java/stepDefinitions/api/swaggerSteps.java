package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pojos.PetPost.PetExpectedBody;
import pojos.PetPost.Pet_Category;
import pojos.PetPost.TagsInnerBody;

import java.util.Collections;
import java.util.List;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class swaggerSteps {

    Pet_Category petCategory;
    PetExpectedBody petExpectedBody;
    PetExpectedBody petRequestBody;
    TagsInnerBody tagsInnerBody;
    public Response response;
    HooksAPI hooksAPI=new HooksAPI();

    @Given("create the endpoint with the {string} ,{string} and {string}")
    public void createTheEndpointWithTheAnd(String pathPar1, String pathPar2, String query_par) {
        if(pathPar1.equals("pet")&& pathPar2.equals("")&& query_par.equals("")){
            spec.pathParam("pp1",pathPar1);
        }
        if (pathPar1.equals("pet")&&pathPar2.equals("findByStatus")) {
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2).queryParam("status",query_par);
        }
    }

    @And("save the response from the {string} API")
    public void saveTheResponseFromTheAPI(String apiName) {
        switch (apiName) {
            case "post-a-pet" -> {
                String[] photoUrl = {"https://tr.pinterest.com/pin/679480662510500791/"};
                petCategory = new Pet_Category(57, "Dog");
                tagsInnerBody = new TagsInnerBody(23, "kangal");
                TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
                petRequestBody = new PetExpectedBody(554, petCategory, "Kılıc", photoUrl, tagsInnerBodies, "available");
                petExpectedBody = new PetExpectedBody(554, petCategory, "Kılıc", photoUrl, tagsInnerBodies, "available");
                response = given().spec(spec).
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        when().body(petRequestBody).
                        post("{pp1}");
            }
            case "update-a-pet" -> {
                String[] photoUrl = {"https://tr.pinterest.com/pin/679480662510500791/"};
                petCategory = new Pet_Category(57, "Dog");
                tagsInnerBody = new TagsInnerBody(23, "Kangal");
                TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
                petRequestBody = new PetExpectedBody(555, petCategory, "Sword of the wisdom", photoUrl, tagsInnerBodies, "available");
                petExpectedBody = new PetExpectedBody(555, petCategory, "Sword of the wisdom", photoUrl, tagsInnerBodies, "available");
                response = given().spec(spec).
                        accept(ContentType.JSON).
                        contentType(ContentType.JSON).
                        when().body(petRequestBody).
                        put("{pp1}");

            }
            case "finds-by-status" -> {
                response = given().
                        spec(spec).
                        accept(ContentType.JSON)
                        .when().
                        get("{pp1}/{pp2}");
            }
        }
    }


    @Then("verify the expected response and the actual response are the same as each other in {string}")
    public void verifyTheExpectedResponseAndTheActualResponseAreTheSameAsEachOtherIn(String apiNme) {
        if (apiNme.equals("post-a-pet")||apiNme.equals("update-a-pet")){
            assertEquals(petExpectedBody.getId(),petRequestBody.getId());
            assertEquals(petExpectedBody.getCategory().getId(),petRequestBody.getCategory().getId());
            assertEquals(petExpectedBody.getCategory().getName(),petRequestBody.getCategory().getName());
            assertEquals(petExpectedBody.getName(),petRequestBody.getName());
            assertEquals(petExpectedBody.getPhotoUrls()[0],petRequestBody.getPhotoUrls()[0]);
            assertEquals(petExpectedBody.getTags()[0].getId(),petRequestBody.getTags()[0].getId());
            assertEquals(petExpectedBody.getTags()[0].getName(),petRequestBody.getTags()[0].getName());
            assertEquals(petExpectedBody.getStatus(),petRequestBody.getStatus());
        }
        if (apiNme.equals("finds-by-status")){
            response.then().assertThat().statusCode(200).
                    body("name",hasItem("Sword of the wisdom"),"status",hasItem("available"));
        }
    }
}
