package com.texoit.goldenraspberryawardsapi.config.movie;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.movie.InsertMovieUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InsertMovieConfigTest {

    private final ApplicationContext applicationContext;

    public InsertMovieConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean InsertMovieUseCase foi criado corretamente")
    public void testInsertMovieUseCaseBeanCreation() {
        InsertMovieUseCase insertMovieUseCase = applicationContext.getBean(InsertMovieUseCase.class);
        assertNotNull(insertMovieUseCase);
    }
}