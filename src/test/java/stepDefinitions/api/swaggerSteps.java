package stepDefinitions.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import pojos.Pet.PetExpectedBody;
import pojos.Pet.Pet_Category;
import pojos.Pet.TagsInnerBody;
import pojos.User.ExpectedUserArray;
import pojos.User.UserObject;

import static hooks.api.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class swaggerSteps {

    Pet_Category petCategory;
    PetExpectedBody petExpectedBody;
    PetExpectedBody petRequestBody;
    TagsInnerBody tagsInnerBody;
    public Response response;
    ExpectedUserArray requestUser;
    UserObject userObject;

    @Given("create the endpoint with the {string} ,{string} and {string}")
    public void createTheEndpointWithTheAnd(String pathPar1, String pathPar2, String query_par) {
        if(pathPar1.equals("pet")&& pathPar2.equals("")&& query_par.equals("")){
            spec.pathParam("pp1",pathPar1);
        }
        if (pathPar1.equals("pet")&&pathPar2.equals("findByStatus")) {
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2).queryParam("status",query_par);
        }
        if(pathPar1.equals("pet")&&query_par.equals("find-a-petById")){
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2);
        }
        if (query_par.equals("delete-a-pet")){
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2);
        }
        if (pathPar2.equals("createWithArray")){
            spec.pathParams("pp1",pathPar1,"pp2",pathPar2);
        }
    }




    @And("save the response from the {string} API with data {string},{string},{string},{string},{string},{string},{string},{string}")
    public void saveTheResponseFromTheAPIWithData(String apiName,String par1, String par2, String par3, String par4, String par5, String par6, String par7, String par8) {

        if (apiName.equals("post-a-pet")) {
            String[] photoUrl1 = {par7};
            petCategory = new Pet_Category(Integer.parseInt(par3), par4);
            tagsInnerBody = new TagsInnerBody(Integer.parseInt(par5), par6);
            TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
            petRequestBody = new PetExpectedBody(Integer.parseInt(par2), petCategory, par1, photoUrl1, tagsInnerBodies, par8);
            petExpectedBody=new PetExpectedBody(Integer.parseInt(par2), petCategory, par1, photoUrl1, tagsInnerBodies, par8);
            response = given().spec(spec).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                    when().body(petRequestBody).
                    post("{pp1}");
        }
        if(apiName.equals("update-a-pet")){
            String[] photoUrl1 = {par7};
            petCategory = new Pet_Category(Integer.parseInt(par3), par4);
            tagsInnerBody = new TagsInnerBody(Integer.parseInt(par5), par6);
            TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
            petRequestBody = new PetExpectedBody(Integer.parseInt(par2), petCategory, par1, photoUrl1, tagsInnerBodies, par8);
            petExpectedBody=new PetExpectedBody(Integer.parseInt(par2), petCategory, par1, photoUrl1, tagsInnerBodies, par8);
            response = given().spec(spec).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                    when().body(petRequestBody).
                    put("{pp1}");

        }
        if(apiName.equals("find-a-pet")){
            response=given().spec(spec).accept(ContentType.JSON).when().get("{pp1}/{pp2}");

        }
        if (apiName.equals("find-a-petById")){
            String[] photoUrl1 = {par7};
            petCategory = new Pet_Category(Integer.parseInt(par3), par4);
            tagsInnerBody = new TagsInnerBody(Integer.parseInt(par5), par6);
            TagsInnerBody[] tagsInnerBodies = {tagsInnerBody};
            petExpectedBody=new PetExpectedBody(Integer.parseInt(par2), petCategory, par1, photoUrl1, tagsInnerBodies, par8);
            response=given().spec(spec).accept(ContentType.JSON).when().get("{pp1}/{pp2}");

        }
        if (apiName.equals("delete-a-pet")){
            response=given().spec(spec).accept(ContentType.JSON).when().delete("{pp1}/{pp2}");
        }

        if (apiName.equals("createUser")){
            userObject=new UserObject(Integer.parseInt(par1),par2,par3,par4,par5,par6,par7,Integer.parseInt(par8));
            UserObject[] userArr={userObject};
            requestUser=new ExpectedUserArray(userArr);
            response = given().spec(spec).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                    when().body(requestUser).
                    post("{pp1}/{pp2}");

        }



    }

    @Then("verify the actual response and expected one are the same in the {string}")
    public void verifyTheActualResponseAndExpectedOneAreTheSameInThe(String apiName) {
        if (apiName.equals("post-a-pet")||apiName.equals("update-a-pet")){
            petRequestBody=response.as(PetExpectedBody.class);
            System.out.println(petRequestBody);
            assertEquals(petExpectedBody.getId(),petRequestBody.getId());
            assertEquals(petExpectedBody.getCategory().getId(),petRequestBody.getCategory().getId());
            assertEquals(petExpectedBody.getCategory().getName(),petRequestBody.getCategory().getName());
            assertEquals(petExpectedBody.getName(),petRequestBody.getName());
            assertEquals(petExpectedBody.getPhotoUrls()[0],petRequestBody.getPhotoUrls()[0]);
            assertEquals(petExpectedBody.getTags()[0].getId(),petRequestBody.getTags()[0].getId());
            assertEquals(petExpectedBody.getTags()[0].getName(),petRequestBody.getTags()[0].getName());
            assertEquals(petExpectedBody.getStatus(),petRequestBody.getStatus());

        }
        if(apiName.equals("find-a-pet")){
            assertEquals(response.statusCode(),200);
        }
        if (apiName.equals("find-a-petById")){
            assertEquals(response.statusCode(),200);
            response.then().assertThat().body("id",Matchers.equalTo(petExpectedBody.getId()),
                    "name",Matchers.equalTo(petExpectedBody.getName()));
            response.prettyPrint();
        }

        if (apiName.equals("createUser")){
            response.prettyPrint();
            assertEquals(200,response.statusCode());

        }
    }

    @Then("verify the element is deleted")
    public void verifyTheElementIsDeleted() {
        response.prettyPrint();
        response.then().assertThat().statusCode(200).body("id",Matchers.nullValue());
    }
}
