package com.rozetka.platform.selenium.driver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {

    IE {
        public DesiredCapabilities getDesiredCapabilities(Proxy setting) {
            String s = File.separator;
            String pth = System.getProperty("user.dir") + s + "selenium_standalone" + s  + "windows" + s +
                    "internetexplorer" + s + "64bit" + s + "IEDriverServer.exe";
            System.setProperty("webdriver.ie.driver", pth);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setBrowserName("internet explorer");
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability("nativeEvents", false);
            capabilities.setCapability("enablePersistentHover", false);
            capabilities.setCapability("javascriptEnabled", true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ie.ensureCleanSession", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    };

    protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }
        return capabilities;
    }
}