package hooks.api;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

public class HooksAPI {
    //Bu sepece herkes nasil ulasir? cozum uretmemiz gerekiyor
    //HooksAPI.spec ile herkes ulasabilir. bu bir cozum
    public static RequestSpecification spec;

    @Before(order = 0)
    public void beforeAPIScnerio() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("base_url_swagger"))
                .build();
    }
}
