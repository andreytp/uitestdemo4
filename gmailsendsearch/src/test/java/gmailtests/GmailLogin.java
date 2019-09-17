package gmailtests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static gmailtests.Credentials.account;
import static gmailtests.Credentials.password;
import static gmailtests.GmailPage.*;

public class GmailLogin {

    @Before
    public void openPage() {
        open("https://gmail.com");
    }

    @Test
    public void loginSendEmailTest() {
        String emailSubject = "Subject for test mail "
                + RandomStringUtils.randomAlphabetic(6);

        openAuthorizeGmail(account, password);
        composeSendLetter(account, emailSubject);
        refreshEmailList();

        assertFindEmailElement(emailSubject);
        clickToSentEmails();
        assertFindEmailElement(emailSubject);
    }
}
