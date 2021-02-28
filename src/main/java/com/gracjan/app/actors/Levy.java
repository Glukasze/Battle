package com.gracjan.app.actors;

public class Levy extends Actor {

    public Levy() {
        super(ActorType.LEVY, "\033[0;34ml\033[0m", "human", 25, 4, 10);
    }
}
