package com.gracjan.app.actors;

public class King extends Actor {
    public King() {
        super(ActorType.KING, "\033[1;34mK\u001B[0m", "human", 150, 15, 50);
    }
}
