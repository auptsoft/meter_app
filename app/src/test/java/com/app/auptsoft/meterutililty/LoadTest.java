package com.app.auptsoft.meterutililty;

import com.app.auptsoft.meterutililty.model.Load;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoadTest {

    @Test
    public void checkStatus_isCorrect1(){
        Load load = new Load(1, 3, 0, 4, 3, 3);
        assertTrue(load.checkStatus()>0);
    }

    @Test
    public void checkStatus_isCorrect2(){
        Load load = new Load(22, 3, 0, 3, 3, 3);
        assertTrue(load.checkStatus()>0);
    }

    @Test
    public void checkStatus_isCorrect3(){
        Load load = new Load(1, 1, 1, 1, 1, 1);
        assertTrue(load.checkStatus()==0);

    }
}
