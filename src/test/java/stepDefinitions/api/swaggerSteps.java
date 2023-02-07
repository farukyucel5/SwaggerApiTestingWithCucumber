package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.bouncycastle.est.CACertsResponse;
import org.junit.Assert;
import pojos.PetPost.PetExpectedBody;
import pojos.PetPost.Pet_Category;
import pojos.PetPost.TagsInnerBody;

import java.util.List;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
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
        if(pathPar1.equals("pet")&&pathPar2.equals("555")){
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2);
        }
    }




    @And("save the response from the {string} API with data {string},{string},{string},{string},{string},{string},{string},{string}")
    public void saveTheResponseFromTheAPIWithData(String apiName,String name, String id, String categoryId, String categoryName, String tagId, String tagName, String photoUrl, String status) {

        if (apiName.equals("post-a-pet")||apiName.equals("update-a-pet")) {
            String[] photoUrl1 = {photoUrl};
            petCategory = new Pet_Category(Integer.parseInt(categoryId), categoryName);
            tagsInnerBody = new TagsInnerBody(Integer.parseInt(tagId), tagName);
            TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
            petRequestBody = new PetExpectedBody(Long.parseLong(id), petCategory, name, photoUrl1, tagsInnerBodies, status);
            petExpectedBody=new PetExpectedBody(Long.parseLong(id), petCategory, name, photoUrl1, tagsInnerBodies, status);
            response = given().spec(spec).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                    when().body(petRequestBody).
                    post("{pp1}");
        }



    }

    @Then("verify the actual response and expected one with data {string}")
    public void verifyTheActualResponseAndExpectedOneWithData(String apiName) {
        if (apiName.equals("post-a-pet")||apiName.equals("update-a-pet")){
            response.then().assertThat().body("id",lessThanOrEqualTo(2147483647),"id",greaterThanOrEqualTo(-2147483648));
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
}
