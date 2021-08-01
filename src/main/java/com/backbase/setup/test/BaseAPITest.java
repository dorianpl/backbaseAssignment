package com.backbase.setup.test;

import com.backbase.setup.api.models.Profile;
import com.backbase.setup.api.models.ProfileData;
import com.backbase.setup.api.models.User;
import com.backbase.setup.api.models.UserRegistrationData;
import com.backbase.utils.PropertiesReader;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BaseAPITest extends BaseTest {

    private PropertiesReader propertiesReader = new PropertiesReader("test.properties");

    public final String AUTH_USER_NAME = propertiesReader.readProperty("test.authorisation.username");
    public final String AUTH_PASSWORD = propertiesReader.readProperty("test.authorisation.password");
    public final String API_USERS = propertiesReader.readProperty("test.api.users");
    public final String API_PROFILES = propertiesReader.readProperty("test.api.profiles");
    public ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void setup() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

//        RestAssured.requestSpecification = new RequestSpecBuilder().
//                setContentType(ContentType.JSON).
//                build();
//
//        RestAssured.responseSpecification = new ResponseSpecBuilder().
//                expectContentType(ContentType.JSON).
//                build();
    }

    @BeforeEach
    public void setUri(){
        RestAssured.baseURI = "https://qa-task.backbasecloud.com/";
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public User createNewRandomUser() {
        String newUsername = "TestUser" + randomAlphabetic(6).toLowerCase();
        return new User(new UserRegistrationData()
                .setUsername(newUsername)
                .setEmail(newUsername + "@mail.com")
                .setPassword("123"));
    }

    public Response registerUser(User user) {
        Response response = given().contentType(ContentType.JSON).log().all()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .body(user)
                .when()
                .post(API_USERS);
        response.then().log().all();
        return response;
    }

    public Response getUser(String userName) {
        return given().contentType(ContentType.JSON).log().all()
                .when()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .get(API_PROFILES + "/" + userName);
    }

    public Profile mapProfile (String response) throws IOException {
        try {
            return mapper.readValue(response, new TypeReference<Profile>() {});
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }
}