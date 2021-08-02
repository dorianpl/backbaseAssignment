package com.backbase.tests.api;

import com.backbase.setup.api.models.user.GetUser;
import com.backbase.setup.api.models.article.Article;
import com.backbase.setup.api.models.article.GetArticle;
import com.backbase.setup.test.BaseAPITest;
import com.backbase.setup.test.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(BaseTest.class)
public class AddNewArticleAPITest extends BaseAPITest {

    private final String TITLE = "Lorem ipsum dolor sit amet";
    private final String DESCRIPTION = "consectetur adipiscing elit";
    private final String BODY = "Suspendisse a molestie dui";
    private final List<String> TAG = Collections.singletonList("awruk");

    @Test
    @Tag("API")
    @Tag("ALL")
    @DisplayName("Test create new article and check logged out")
    public void testAddNewArticleLoggedOut() throws IOException {
        Response response = registerUser(createNewRandomUser());

        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);

        GetUser getUser = mapGetUser(response.asString());
        String token = getUser.getUser().getToken();
        String username = getUser.getUser().getUsername();

        Article article = createNewArticle();
        response = createArticle(article, token);

        assertThat(response.getStatusCode())
                .as("Article was not created successfully")
                .isEqualTo(200);

        GetArticle getArticle = mapGetArticle(response.asString());

        String slug = getArticle.getArticle().getSlug();
        response = getArticle(slug);

        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);

        getArticle = mapGetArticle(response.asString());

        assertThat(getArticle.getArticle().getAuthor().getUsername())
                .as("Author was not created successfully")
                .isEqualTo(username);
        assertThat(getArticle.getArticle().getTitle())
                .as("Title was not created successfully")
                .isEqualTo(TITLE);
        assertThat(getArticle.getArticle().getDescription())
                .as("Description was not created successfully")
                .isEqualTo(DESCRIPTION);
        assertThat(getArticle.getArticle().getBody())
                .as("Body was not created successfully")
                .isEqualTo(BODY);
        assertThat(getArticle.getArticle().getTagList())
                .as("Body was not created successfully")
                .isEqualTo(TAG);
    }

    @Test
    @Tag("API")
    @Tag("ALL")
    @DisplayName("Test create new article and check logged in")
    public void testAddNewArticleLoggedIn() throws IOException {
        Response response = registerUser(createNewRandomUser());

        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);

        GetUser getUser = mapGetUser(response.asString());
        String token = getUser.getUser().getToken();
        String username = getUser.getUser().getUsername();

        Article article = createNewArticle();
        response = createArticle(article, token);

        assertThat(response.getStatusCode())
                .as("Article was not created successfully")
                .isEqualTo(200);

        GetArticle getArticle = mapGetArticle(response.asString());
        String slug = getArticle.getArticle().getSlug();
        response = getArticle(slug, token);

        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);

        getArticle = mapGetArticle(response.asString());

        assertThat(getArticle.getArticle().getAuthor().getUsername())
                .as("Author was not created successfully")
                .isEqualTo(username);
        assertThat(getArticle.getArticle().getTitle())
                .as("Title was not created successfully")
                .isEqualTo(TITLE);
        assertThat(getArticle.getArticle().getDescription())
                .as("Description was not created successfully")
                .isEqualTo(DESCRIPTION);
        assertThat(getArticle.getArticle().getBody())
                .as("Body was not created successfully")
                .isEqualTo(BODY);
        assertThat(getArticle.getArticle().getTagList())
                .as("Body was not created successfully")
                .isEqualTo(TAG);
    }

    private Article createNewArticle() {
        return createNewArticleData(TITLE, DESCRIPTION, BODY, TAG);
    }
}
