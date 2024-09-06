package helpers.mobile;

import config.mobile.credentials.BrowserstackCredentials;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class BrowserstackVideoHelper {
    private static final BrowserstackCredentials browserstackCredentials = ConfigFactory.create(BrowserstackCredentials.class, System.getProperties());

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(browserstackCredentials.browserstackUsername(), browserstackCredentials.browserstackPassword())
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}