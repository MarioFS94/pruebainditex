package com.vermont.inditex.pruebamario.service;

import com.vermont.inditex.pruebamario.model.PriceRequestDto;
import com.vermont.inditex.pruebamario.model.PriceResponseDTO;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;
import java.math.BigInteger;

//@ExtendWith(MockitoExtension.class)
public class ProductsServiceTests {
    @SpyBean
    ProductsService productsService;

    private PriceRequestDto request;

    public static MockWebServer server;

    private AutoCloseable closeable;

    @BeforeAll
    static void setUp() throws IOException {
        server = new MockWebServer();
        server.start(8080);
    }

    @AfterAll
    static void tearDown() throws IOException {
        server.shutdown();
    }

    @BeforeEach
    public void openMocks(){
        String baseUrl = "http://localhost:" + server.getPort();
    }
    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    @Test
    public void productServiceTest1(){

        productsService.getProductData(request);
    }
}
