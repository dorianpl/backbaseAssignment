package tests.api;

import com.backbase.setup.api.models.Profile;
import com.backbase.setup.test.BaseAPITest;
import com.backbase.setup.test.BaseTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(BaseTest.class)
public class TestAPI extends BaseAPITest {

    private String userName;

    @BeforeEach //has been changed from @Before
    public void startBrowser(TestInfo testInfo) {

    }
    @BeforeAll
    public static void setup3() {
    }

    @Test
    @DisplayName("This is my test1")
    public void demo() {
//        User user = createNewRandomUser();
//        userName = user.getUser().getUsername();
//        Response response = registerUser(createNewRandomUser());
//
//        assertThat(response.getStatusCode())
//                .as("User was not created successfully")
//                .isEqualTo(200);
    }

    @Test
    @DisplayName("This is my test2")
    public void userCreateHere() throws IOException  {
        Response response = getUser("doriantest");

        Map responseBody = response.then().extract()
                .body()
                // Extract response as Map<String,Object>
                .as(new TypeRef<Map>() {});

        Map<String, Map<String, String>> map =
                new HashMap<String, Map<String, String>>();
//
//        Map<Object, String> profileDataResponse = responseBody.entrySet().stream()
//                .collect(Collectors.toMap(Entry::getKey, entry -> String.valueOf(entry.getValue())));



//
//        String dorian = profileDataResponse.get("username");
//        assertThat(profileDataResponse.get("username")).as("True in not true").isEqualTo("doriantest");

        assertThat(true).as("True in not true").isEqualTo("doriantest");
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
