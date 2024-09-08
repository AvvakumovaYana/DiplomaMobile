package pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class BaseScreen {

    protected static void ensureElement(SelenideElement element, int waitingPeriodInSeconds) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(waitingPeriodInSeconds));
    }
}
