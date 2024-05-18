package com.wladischlau;

/**
 * @author Vladislav Nasevich
 */
public class AngryPoliceman implements Policeman {
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("I'll kill you all! Go away!");
    }
}
