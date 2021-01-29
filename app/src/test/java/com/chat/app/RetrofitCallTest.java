package com.chat.app;

import com.chat.app.ui.viewmodel.DashBoardViewModel;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import okhttp3.mockwebserver.MockWebServer;

public class RetrofitCallTest {

    private MockWebServer mockWebServer;
    private DashBoardViewModel presenter;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        presenter = new DashBoardViewModel()

        // Then we need to swap the retrofit api impl. with a mock one
        // I usually store my Retrofit api impl as a static singleton in class RestClient, hence:
//        RestClient.setApi(mockRetrofitApiImpl);
//
//        controller.create();
    }
}
