package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void captureElementScreenshot(
            WebElement element,
            String fileName) throws IOException {

        File src = element.getScreenshotAs(OutputType.FILE);

        FileHandler.copy(
                src,
                new File(System.getProperty("user.dir")
                        + "/screenshots/" + fileName + ".png")
        );
    }
}