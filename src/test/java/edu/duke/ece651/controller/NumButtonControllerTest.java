package edu.duke.ece651.controller;

import edu.duke.ece651.model.RPNStack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ListViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class NumButtonControllerTest {
    private TextField testText;
    private NumButtonController cont;
    private RPNStack model;
    @Start
    private void start(Stage stage) {
        testText = new TextField();
        model = mock(RPNStack.class);
        cont = new NumButtonController(model);
        cont.currentNumber = testText;
    }
    @Test
    public void test_onNumberButton(FxRobot robot) {
        Platform.runLater(()->{
            Button b = new Button("7");
            cont.onNumberButton(new ActionEvent(b, null));
        });
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("7"));
    }
    @Test
    void test_enterButton(FxRobot robot) {
        run_Enter(robot,"1234.5");
        verify(model).pushNum(1234.5);
        verifyNoMoreInteractions(model);
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
    }

    @Test
    void test_whiteSpaceEnter(FxRobot robot){
        run_Enter(robot," ");
        verifyNoMoreInteractions(model);
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
    }
    @Test
    void test_moreWhiteSpaceEnter(FxRobot robot){
        run_Enter(robot," 3456.90 ");
        verify(model).pushNum(3456.90);
        verifyNoMoreInteractions(model);
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
    }

    private void run_Enter(FxRobot robot, String content){
        Platform.runLater(()->{
            testText.setText(content);
            Button b = new Button("Enter");
            cont.onEnter(new ActionEvent(b,null));
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

}