package com.texoit.goldenraspberryawardsapi.config.movie;

import com.texoit.goldenraspberryawardsapi.application.core.usecase.movie.FindMovieByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindMovieByIdConfigTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FindMovieByIdConfigTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    @DisplayName("Verifica se o bean FindMovieByIdUseCase foi criado corretamente")
    public void testFindMovieByIdUseCaseBeanCreation() {
        FindMovieByIdUseCase findMovieByIdUseCase = applicationContext.getBean(FindMovieByIdUseCase.class);
        assertNotNull(findMovieByIdUseCase);
    }
}
