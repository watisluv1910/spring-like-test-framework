package com.wladischlau;

/**
 * @author Vladislav Nasevich
 */
@Deprecated
public class Disinfector {

    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("Beginning disinfection, all out!");
        policeman.makePeopleLeaveRoom();
        disinfect(room);
        announcer.announce("You can go back to the room. Risk it!");
    }

    private void disinfect(Room room) {
        System.out.println("Disinfection completed!");
    }
}
