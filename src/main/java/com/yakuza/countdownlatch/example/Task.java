package com.yakuza.countdownlatch.example;

public interface Task {

    void execute() throws InterruptedException;

    String getName();

}
