package com.yakuza.countdownlatch.example;

public class Application {

    public static void main(String args[]) {

        TasksConductor tasksConductor = TasksConductor.getInstance();

        tasksConductor.addTask(new Task() {
            @Override
            public void execute() throws InterruptedException {
                System.out.println("Executed task 1.");
                //throw new RuntimeException("Exception 1");
            }

            @Override
            public String getName() {
                return "Task 1";
            }
        });

        tasksConductor.addTask(new Task() {
            @Override
            public void execute() throws InterruptedException {
                System.out.println("Executed task 2.");
                //throw new RuntimeException("Exception 2");
            }

            @Override
            public String getName() {
                return "Task 2";
            }
        });

        try {
            tasksConductor.startTasks().await();

            System.out.println("Executed.");
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
            System.out.println("Executed with error.");
        }
    }

}
