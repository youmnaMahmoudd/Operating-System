package com.example.cpu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private static Label elapsed;



    @FXML
    protected static void uppdateElapsed() throws InterruptedException {
        int c=0;
        elapsed.setText(String.valueOf(c));
        Thread.sleep(1000);
        c++;
    }


}