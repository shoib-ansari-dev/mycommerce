package com.example.productservice;

import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@ActiveProfiles("test")
@SpringBootTest
class ProductserviceApplicationTests {

    @MockitoBean
    private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

//    @Test
//    void main(){
//        ProductserviceApplication.main(new String[]{});
//    }
}
