package ru.ilya.ozontest.allure;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){
        ScreenshotMaker.takeScreenshot();
    }
}