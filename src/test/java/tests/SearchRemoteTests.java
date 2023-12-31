package tests;


import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("remote")
public class SearchRemoteTests extends TestBase {

    @Test
    void successfulSearchRemoteTest() {
        step("Ввод поискового запроса", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка результатов поиска", () -> $$(AppiumBy.id("org.wikipedia.alpha:id/search_container")).shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void checkErrorRemoteTest() {
        step("Ввод поискового запроса", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });

        step("Переход на страницу Appium", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container")).first().click();
        });

        step("Проверка страницы", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/view_wiki_error_button")).shouldBe(visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("An error occurred"));
        });
    }
}
