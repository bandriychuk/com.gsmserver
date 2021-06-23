package com.gsmserver;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(SoftAssertsExtension.class)
public class SearchTests extends BaseTest{

    @BeforeEach
    void openHomePage() {
        open("/");
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void searchProductByTitle() {
        var productName = "Octoplus Pro Box";
        var productId = "897789";

        $("[name='searchword']").val(productName).pressEnter();
        sleep(500);
        $(".search-title-highlight").shouldHave(text(productName));

        findProductById(productId).$(".product-info_title").shouldHave(text(productName));
        findProductById(productId).$("[data-action-click='site.cart.add']").click();
        findProductById(productId).$(".in-cart").click();
        $("#cart h1").shouldHave(text("Кошик"));

        $$("#cart tr[data-product-id]").shouldHaveSize(1);

        findProductById(productId).$(".product-title").shouldHave(text(productName));
    }

    @Test
    void searchProductByTitleTest() {
        var productName = "Octoplus Pro Box";

        new HomePage().searchFor(productName);
        var actualSearchResultTitle = new SearchResultPage().getSearchResultTitle();

        Assertions.assertEquals(productName, actualSearchResultTitle);
    }

    private SelenideElement findProductById(String productId) {
        return $(by("data-product-id", productId));
    }

}
