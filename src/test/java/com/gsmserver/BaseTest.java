package com.gsmserver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public abstract class BaseTest {

    static {
        Configuration.baseUrl = "https://gsmserver.com.ua";
        Configuration.browser = WebDriverRunner.CHROME;
//        Configuration.assertionMode = AssertionMode.SOFT; // For Soft assert
//        Configuration.headless = true; // run without ui
        Configuration.savePageSource = false;
        Configuration.screenshots = false;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));


    }

}
