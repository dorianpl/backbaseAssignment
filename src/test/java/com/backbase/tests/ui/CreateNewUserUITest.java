package com.backbase.tests.ui;

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
public class CreateNewUserUITest extends BaseUITest {

    @Test
    @Tags({@Tag("UI"), @Tag("ALL")})
    @DisplayName("Test signing up to application")
    public void testCreateNewUser() {
        String newUsername = RandomUserNameCreator.generateRandomUserName();

        SignUpPage signUpPage = homePage.openSignUpPage();
        signUpPage.inputUsername(newUsername)
                .inputEmail(newUsername + "@testmail.com")
                .inputPassword("123");
        homePage = signUpPage.clickSubmitButton();
        assertThat(homePage.profileNameIsVisible())
                .as("Profile name is not visible")
                .isTrue();
        assertThat(homePage.settingsAreVisible())
                .as("Settings button is not visible")
                .isTrue();
        assertThat(homePage.newArticleIsVisible())
                .as("New Article button is not visible")
                .isTrue();
        assertThat(homePage.returnActiveFeedType())
                .as("Your Feed tab is not active")
                .contains("Your Feed");
    }
}
