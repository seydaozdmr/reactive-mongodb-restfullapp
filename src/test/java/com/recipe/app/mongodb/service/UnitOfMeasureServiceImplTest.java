package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import com.recipe.app.mongodb.repository.reaktive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService=new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository);
    }

    @Test
    void findAll() {
        List<UnitOfMeasure> unitOfMeasures=new ArrayList<>();
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setDescription("Gram");
        unitOfMeasures.add(uom1);


        UnitOfMeasure uom2=new UnitOfMeasure();
        uom2.setDescription("Kg");
        unitOfMeasures.add(uom2);

        Mockito.when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1,uom2));

        List<UnitOfMeasure> list=unitOfMeasureService.findAll().collectList().block();

        assertEquals(2,list.size());
        Mockito.verify(unitOfMeasureReactiveRepository,Mockito.times(1)).findAll();
    }
}