package com.wladischlau;

import javax.annotation.PostConstruct;

/**
 * @author Vladislav Nasevich
 */
public class DefaultPoliceman implements Policeman {

    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init() {
        System.out.println(recommender.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("I'm politely telling you to leave the room.");
    }
}
