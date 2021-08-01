package tests.ui;

import com.backbase.setup.pages.HomePage;
import com.backbase.setup.pages.SignInPage;
import com.backbase.setup.pages.SignUpPage;
import com.backbase.setup.test.BaseTest;
import com.backbase.setup.test.BaseUITest;
import com.backbase.utils.PropertiesReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(BaseTest.class)
public class LogIn extends BaseUITest{

    private PropertiesReader properties = new PropertiesReader("test.properties");
    HomePage homePage;

    @BeforeEach //has been changed from @Before
    public void startBrowser() {
        String url = properties.readProperty("test.mainUrl");
        String username = properties.readProperty("test.authorisation.username");
        String password = properties.readProperty("test.authorisation.password");
        HomePage homePage = new HomePage(driver);
        homePage.openMainUrl(url, username, password);
    }

    @BeforeAll
    public static void setup() {
    }

    @Test
    @DisplayName("Test Login to Application")
    public void demo() {
        String url = properties.readProperty("test.mainUrl");
        String username = properties.readProperty("test.authorisation.username");
        String password = properties.readProperty("test.authorisation.password");
        HomePage homePage = new HomePage(driver);
        homePage.openMainUrl(url, username, password);
        SignInPage signInPage = homePage.openSignInPage();
        wait(2000);

        SignUpPage signUpPage = signInPage.openSignUpPage();
        wait(2000);

        homePage = signInPage.openHomePage();

        wait(2000);
    }

    @Test
    @DisplayName("This is my test2")
    public void demo2() {
        assertThat(false).as("True in not true").isTrue();

    }

    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void close() {
    }

    public void wait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
