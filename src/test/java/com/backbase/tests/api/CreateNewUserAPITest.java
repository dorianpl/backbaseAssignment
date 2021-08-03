package com.backbase.tests.api;

import com.backbase.setup.api.models.user.GetUser;
import com.backbase.setup.api.models.profile.Profile;
import com.backbase.setup.test.BaseAPITest;
import com.backbase.setup.test.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(BaseTest.class)
public class CreateNewUserAPITest extends BaseAPITest {

    @Test
    @Tags({@Tag("API"), @Tag("ALL")})
    @DisplayName("Test new user creation and check with profile")
    public void testCreateNewUser() throws IOException {
        Response response = registerUser(createNewRandomUser());
        assertThat(response.getStatusCode())
                .as("User was not created successfully")
                .isEqualTo(200);
        GetUser getUser = mapGetUser(response.asString());
        String username = getUser.getUser().getUsername();

        response = getProfile(username);
        assertThat(response.getStatusCode())
                .as("Profile was not loaded successfully")
                .isEqualTo(200);

        Profile profile = mapProfile(response.asString());
        assertThat(profile.getProfile().getUsername())
                .as("User Name was not created successfully")
                .isEqualTo(username);
    }
}
