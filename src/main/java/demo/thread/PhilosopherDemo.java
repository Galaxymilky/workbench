package demo.thread;

/**
 * Created by niu_ben on 2016/11/15.
 */

class Philosopher extends Thread {
    private String name;
    private Fork fork;

    public Philosopher(String name, Fork fork) {
        super(name);
        this.name = name;
        this.fork = fork;
    }

    public void run() {
        while (true) {
            thinking();
            fork.takeFork();
            eating();
            fork.putFork();
        }

    }


    public void eating() {
        System.out.println("I am Eating:" + name);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void thinking() {
        System.out.println("I am Thinking:" + name);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Fork {
    private boolean[] used = {false, false, false, false, false, false};

    public synchronized void takeFork() {
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        while (used[i] || used[(i + 1) % 5]) {
            try {
                wait();
                if(used[i]){
                    System.out.println(name + " is waiting for " + i);
                }else{
                    System.out.println(name + " is waiting for " + (i+1));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        used[i] = true;
        used[(i + 1) % 5] = true;
    }

    public synchronized void putFork() {
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);

        used[i] = false;
        used[(i + 1) % 5] = false;
        notifyAll();//Wake other threads
    }
}

public class PhilosopherDemo {
    public static void main(String[] args) {
        Fork fork = new Fork();
        new Philosopher("0", fork).start();
        new Philosopher("1", fork).start();
        new Philosopher("2", fork).start();
        new Philosopher("3", fork).start();
        new Philosopher("4", fork).start();
    }
}
