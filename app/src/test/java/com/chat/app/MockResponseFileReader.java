package com.chat.app;

import java.io.InputStreamReader;

public class MockResponseFileReader {
    private String content;

    public MockResponseFileReader(String path) {
        InputStreamReader inputStreamReader = new InputStreamReader(getClass().getResourceAsStream(path));
        inputStreamReader.rea
        inputStreamReader.close();
    }
}
