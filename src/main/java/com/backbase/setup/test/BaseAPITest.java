package com.backbase.setup.test;

import com.backbase.setup.api.models.user.GetUser;
import com.backbase.setup.api.models.profile.Profile;
import com.backbase.setup.api.models.user.User;
import com.backbase.setup.api.models.user.UserData;
import com.backbase.setup.api.models.article.Article;
import com.backbase.setup.api.models.article.ArticleData;
import com.backbase.setup.api.models.article.GetArticle;
import com.backbase.utils.PropertiesReader;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.List;

import static com.backbase.utils.RandomUserNameCreator.generateRandomUserName;
import static io.restassured.RestAssured.given;

public class BaseAPITest extends BaseTest {

    private PropertiesReader propertiesReader = new PropertiesReader("test.properties");
    private final String AUTH_USER_NAME = propertiesReader.readProperty("test.authorisation.username");
    private final String AUTH_PASSWORD = propertiesReader.readProperty("test.authorisation.password");
    private final String API_USERS = propertiesReader.readProperty("test.api.users");
    private final String API_PROFILES = propertiesReader.readProperty("test.api.profiles");
    private final String API_ARTICLES = propertiesReader.readProperty("test.api.articles");

    public ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUri() {
        RestAssured.baseURI = propertiesReader.readProperty("test.mainUrl");
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public User createNewRandomUser() {
        String newUsername = generateRandomUserName();
        return new User(new UserData()
                .setUsername(newUsername)
                .setEmail(newUsername + "@testmail.com")
                .setPassword("123"));
    }

    public Article createNewArticleData(String title, String description, String body, List<String> tag) {
        return new Article(new ArticleData()
                .setTitle(title)
                .setDescription(description)
                .setBody(body)
                .setTagList(tag));
    }

    public Response createArticle(Article article, String token) {
        Response response = given().contentType(ContentType.JSON).log().all()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .header("jwtauthorization", "Token ".concat(token))
                .body(article)
                .when()
                .post(API_ARTICLES);
        response.then().log().all();
        return response;
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

    public Response getArticle(String slug) {
        return given().contentType(ContentType.JSON).log().all()
                .when()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .get(API_ARTICLES + "/" + slug);
    }

    public Response getArticle(String slug, String token) {
        return given().contentType(ContentType.JSON).log().all()
                .header("jwtauthorization", "Token ".concat(token))
                .when()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .get(API_ARTICLES + "/" + slug);
    }

    public Response getProfile(String userName) {
        return given().contentType(ContentType.JSON).log().all()
                .when()
                .auth().basic(AUTH_USER_NAME, AUTH_PASSWORD)
                .get(API_PROFILES + "/" + userName);
    }

    public Profile mapProfile(String response) throws IOException {
        try {
            return mapper.readValue(response, new TypeReference<Profile>() {
            });
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }

    public GetUser mapGetUser(String response) throws IOException {
        try {
            return mapper.readValue(response, new TypeReference<GetUser>() {
            });
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }

    public GetArticle mapGetArticle(String response) throws IOException {
        try {
            return mapper.readValue(response, new TypeReference<GetArticle>() {
            });
        } catch (UnrecognizedPropertyException e) {
            throw new RuntimeException("there was something wrong with parsing response data");
        }
    }
}