package com.yakuza.countdownlatch.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TasksConductor {

    private final List<Thread> taskList = new ArrayList<>();

    private CancellableCountDownLatch latch;


    private TasksConductor() {
    }

    public TasksConductor addTask(Task task) {
        taskList.add(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.execute();
                    latch.countDown();
                } catch (Exception exc) {
                    latch.cancel();
                }
            }
        }));
        return this;
    }

    public CancellableCountDownLatch startTasks() throws InterruptedException {
        latch = new CancellableCountDownLatch(taskList.size());

        if (taskList.isEmpty()) {
            return latch;
        }

        taskList.iterator().forEachRemaining(new Consumer<Thread>() {
            @Override
            public void accept(Thread thread) {
                thread.start();
            }
        });

        return latch;
    }

    public static TasksConductor getInstance() {
        return new TasksConductor();
    }

}
