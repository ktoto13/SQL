package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static ru.netology.data.SQLHelper.cleanDatabase;
import static com.codeborne.selenide.Selenide.open;

public class SQLTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    void positiveLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerifyPageVisiblity();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

}