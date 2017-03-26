package com.rozetka.platform.selenium.utils;

import org.testng.annotations.DataProvider;

/**
 * Created by Nikita Ovsyannikov on 12.09.2016.
 */
public class DataProviderPool {
    public static final String USER_CREDENTIALS = "userCredentials";

    @DataProvider(name = USER_CREDENTIALS)
    public static Object[][] getUserEmailData() {
        return new Object[][] {
                {"tess.testss@gmail.com", "123456789"}
        };
    }
}
