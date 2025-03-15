package com.example.findpublicwifiservice;

import com.example.findpublicwifiservice.service.OpenApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenApiServiceTest {
    private OpenApiService openApiService;
    private ServletContext mockServletContext;

    @BeforeEach
    public void setUp() {
        mockServletContext = Mockito.mock(ServletContext.class);
        //실제 AccessKey는 web.xml에 있습니다(커밋X)
        Mockito.when(mockServletContext.getInitParameter("openapi.accessKey")).thenReturn("테스트용notRealAccessKey");
        openApiService = new OpenApiService(mockServletContext);
    }
    
    @Test
    public void testAccessKeyInitialization() {
        // accessKey가 제대로 초기화 되었는지 테스트
        assertEquals("테스트용notRealAccessKey", openApiService.getAccessKeyForTesting());
    }

}
