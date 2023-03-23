package com.globalpsa.webapp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ngch
 */
@Component
public class TextRepo {
    
    private static final String TEXT_1 = "Logic will get you from A to B. Imagination will take you everywhere.";
    private static final String TEXT_2 = "There are 10 kinds of people. Those who know binary and those who don't.";
    private static final String TEXT_3 = "There are two ways of constructing a software design. One way is to make it so simple that there are obviously no deficiencies and the other is to make it so complicated that there are no obvious deficiencies.";
    private static final String TEXT_4 = "It's not that I'm so smart, it's just that I stay with problems longer.";
    private static final String TEXT_5 = "It is pitch dark. You are likely to be eaten by a grue.";
    
    private List<String> cache;
    
    public TextRepo(){
        cache = new ArrayList();
        cache.add(TEXT_1);
        cache.add(TEXT_2);
        cache.add(TEXT_3);
        cache.add(TEXT_4);
        cache.add(TEXT_5);
    }
    
    public String getRandomLine(){
        int randomIndex = (int) (Math.random()*cache.size());
        return cache.get(randomIndex);
    }
}
