/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.javafxtut;

import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;

@Disabled
@ExtendWith(ApplicationExtension.class)
class AppTest {
    App a;
    @Start
    public void start(Stage stage) throws IOException {
        a = new App();
        a.start(stage);
    }

    @Test
    void test_numButtons(FxRobot robot) {
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(""));
        String str = "123450.6789";
        for (char digit : str.toCharArray()){
            if (digit == '.') {
                robot.clickOn("#dot");
            }
            else {
                robot.clickOn(""+digit);
            }
        }
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(str));
    }
}
