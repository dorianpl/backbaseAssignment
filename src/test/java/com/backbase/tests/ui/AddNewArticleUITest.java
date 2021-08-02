package com.backbase.tests.ui;

import com.backbase.setup.pages.ArticlePage;
import com.backbase.setup.pages.EditorPage;
import com.backbase.setup.pages.HomePage;
import com.backbase.setup.pages.SignUpPage;
import com.backbase.setup.test.BaseTest;
import com.backbase.setup.test.BaseUITest;
import com.backbase.utils.PropertiesReader;
import com.backbase.utils.RandomUserNameCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(BaseTest.class)
public class AddNewArticleUITest extends BaseUITest {

    private PropertiesReader properties = new PropertiesReader("test.properties");
    private HomePage homePage;
    private final String ARTICLE_TITLE = "Here is article title";
    private final String ARTICLE_DESCRIPTION = "Here is description";
    private final String ARTICLE_BODY = "Here is article body";
    private final String ARTICLE_TAG = "test";


    @BeforeEach
    public void startBrowser() {
        String url = properties.readProperty("test.mainUrl");
        String username = properties.readProperty("test.authorisation.username");
        String password = properties.readProperty("test.authorisation.password");
        homePage = new HomePage(driver);
        homePage.openMainUrl(url, username, password);
    }

    @Test
    @Tag("UI")
    @Tag("ALL")
    @DisplayName("Test adding new article")
    public void testAddNewArticle() {
        String newUsername = RandomUserNameCreator.generateRandomUserName();

        SignUpPage signUpPage = homePage.openSignUpPage()
                .inputUsername(newUsername)
                .inputEmail(newUsername + "@testmail.com")
                .inputPassword("123");
        homePage = signUpPage.clickSubmitButton();
        EditorPage editorPage = homePage.openNewArticlePage()
                .inputArticleTitle(ARTICLE_TITLE)
                .inputArticleDescription(ARTICLE_DESCRIPTION)
                .inputArticleBody(ARTICLE_BODY)
                .inputArticleTags(ARTICLE_TAG);
        ArticlePage articlePage = editorPage.clickPublishButton();

        assertThat(articlePage
                .topEditArticleButtonIsVisible())
                .as("Top Edit Article button is not visible").isTrue();
        assertThat(articlePage
                .topDeleteArticleButtonIsVisible())
                .as("Top Delete Article button is not visible")
                .isTrue();
        assertThat(articlePage.getArticleTitle())
                .as("Article title is incorrect")
                .contains(ARTICLE_TITLE);
        assertThat(articlePage.getArticleAuthor())
                .as("Article author is incorrect")
                .contains(newUsername);
        assertThat(articlePage.getArticleContent())
                .as("Article content is incorrect")
                .contains(ARTICLE_BODY);
    }
}
