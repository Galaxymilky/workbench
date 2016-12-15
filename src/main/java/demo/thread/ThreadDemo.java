package demo.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by niu_ben on 2016/11/15.
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        int x = 9;
        System.out.println(x >> 2);
//        if((x >> 2) / 2 > 0){
//            System.out.println(x);
//        }

        /**
         MyThread_1 thread = new MyThread_1();
         thread.setPriority(6);
         thread.start();

         //测试sleep方法
         //        MyThread_2 mt2 = new MyThread_2();
         //        System.out.println("Begin " + System.currentTimeMillis());
         //        mt2.start();
         //        System.out.println("End " + System.currentTimeMillis());

         MyThread_3 thread_3 = new MyThread_3();
         System.out.println("begin = " + thread_3.isAlive());
         thread_3.start();
         Thread.sleep(3000);
         System.out.println("end = " + thread_3.isAlive());

         MyThread_4 mt4 = new MyThread_4();
         mt4.setDaemon(true);
         mt4.start();
         Thread.sleep(5000);
         System.out.println("Leave , then no output, stop");


         MyThread_5 mt5 = new MyThread_5();
         mt5.start();
         Thread.sleep(2000);
         mt5.interrupt();

         MyThread_6 mt = new MyThread_6();
         mt.start();
         mt.join();
         //main线程就会无限期阻塞直到mt的run()方法执行完毕
         //sleep(2000)不释放锁，join(2000)释放锁
         System.out.println("我想当mt对象执行完毕之后我再执行，我做到了");

         */

//        MyThread_7_1 mt71 = new MyThread_7_1(lock);
//        mt71.start();
//        Thread.sleep(3000);
//        MyThread_7_2 mt72 = new MyThread_7_2(lock);
//        mt72.start();

        //验证：wait()方法释放锁
//        MyThread_8_1 mt0 = new MyThread_8_1(lock);
//        MyThread_8_1 mt1 = new MyThread_8_1(lock);
//        mt0.start();
//        mt1.start();

        //验证：notify()方法不释放锁
//        MyThread_9_1 mt91 = new MyThread_9_1(lock);
//        mt91.start();
//        Thread.sleep(1000);
//        MyThread_9_2 mt92_1 = new MyThread_9_2(lock);
//        mt92_1.start();

        //interrupt()打断wait()
//        MyThread_10 mt = new MyThread_10(lock);
//        mt.start();
//        Thread.sleep(5000);
//        mt.interrupt();

        //利用Object对象的notifyAll()方法可以唤醒处于同一监视器下的所有处于wait的线程
//        MyThread_11_1 mt_11_1 = new MyThread_11_1(lock);
//        MyThread_11_1 mt_11_2 = new MyThread_11_1(lock);
//        MyThread_11_1 mt_11_3 = new MyThread_11_1(lock);
//        mt_11_1.start();
//        mt_11_2.start();
//        mt_11_3.start();
//        Thread.sleep(3000);
//        MyThread_11_2 mt_11 = new MyThread_11_2(lock);
//        mt_11.start();

        // yield() release the CPU resource
//        MyThread_12 mt12 = new MyThread_12();
//        mt12.start();

        // interrupted() 测试当前线程是否已经中断，执行后具有将状态标识清除为false的功能。换句话说，如果连续两次调用该方法，那么返回的必定是false：
//        Thread.currentThread().interrupt();
//        System.out.println("是否停止1？" + Thread.interrupted());
//        System.out.println("是否停止2？" + Thread.interrupted());
//        System.out.println("end!");


    }
}

class MyThread_0 extends Thread {
    public void run() {
        System.out.println("MyThread_0 run priority = " + this.getPriority());
    }
}

class MyThread_1 extends Thread {
    public void run() {
        System.out.println("MyThread_1 run priority = " + this.getPriority());

        MyThread_0 thread = new MyThread_0();
        thread.start();
    }
}

class MyThread_2 extends Thread {
    public void run() {
        try {
            System.out.println("Current Thread " + this.getName());
            Thread.sleep(3000);
            System.out.println("Current Thread " + this.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyThread_3 extends Thread {
    public void run() {
        System.out.println("Id = " + this.getId() + " Name = " + this.getName() + " & run = " + this.isAlive());
    }

}

class MyThread_4 extends Thread {
    private int i = 0;

    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread_5 extends Thread {
    public void run() {
        for (int i = 0; i < 500000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }
}

class MyThread_6 extends Thread {
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 10000);
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread_7_1 extends Thread {
    private Object lock;

    public MyThread_7_1(Object lock) {
        this.lock = lock;
    }

    public void run() {
        try {
            synchronized (lock) {
                System.out.println("[Start] wait time = " + System.currentTimeMillis());

                lock.wait();

                System.out.println("[End] wait time = " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread_7_2 extends Thread {
    private Object lock;

    public MyThread_7_2(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            System.out.println("2-[Start] notify time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("2-[End] notify time = " + System.currentTimeMillis());
        }
    }
}

class MyThread_8_1 extends Thread {
    private Object lock;

    public MyThread_8_1(Object lock) {
        this.lock = lock;
    }

    public void run() {
        ThreadDomain_8 td = new ThreadDomain_8();
        td.testDomain(lock);
    }
}

class ThreadDomain_8 {

    public void testDomain(Object lock) {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " Begin wait()");
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " End wait()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread_9_1 extends Thread {

    private Object lock;

    public MyThread_9_1(Object lock) {
        this.lock = lock;
    }

    public void run() {
        ThreadDomain_9 td = new ThreadDomain_9();
        td.testMethod(lock);
    }
}

class MyThread_9_2 extends Thread {
    private Object lock;

    public MyThread_9_2(Object lock) {
        this.lock = lock;
    }

    public void run() {
        ThreadDomain_9 td = new ThreadDomain_9();
        td.synNotifyMethod(lock);
    }
}

class ThreadDomain_9 {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("Begin wait(), ThreadName = " + Thread.currentThread().getName());
                lock.wait();
                System.out.println("End wait(), ThreadName = " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("Begin notify(), ThreadName = " + Thread.currentThread().getName());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("End notify(), ThreadName = " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MyThread_10 extends Thread {
    private Object lock;

    public MyThread_10(Object lock) {
        this.lock = lock;
    }

    public void run() {
        ThreadDomain_10 td = new ThreadDomain_10();
        td.testMethod(lock);
    }

}

class ThreadDomain_10 {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("Begin wait()");
                lock.wait();
                System.out.println("End wait()");
            }
        } catch (Exception e) {
            System.out.println("wait() is interrupted by interrupt()!");
            e.printStackTrace();
        }
    }
}

class MyThread_11_1 extends Thread {
    private Object lock;

    public MyThread_11_1(Object lock) {
        this.lock = lock;
    }

    public void run() {
        ThreadDomain_11 td = new ThreadDomain_11();
        td.testMethod(lock);
    }

}

class MyThread_11_2 extends Thread {
    private Object lock;

    public MyThread_11_2(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

}

class ThreadDomain_11 {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("Begin wait(), ThreadName = " + Thread.currentThread().getName());
                lock.wait();
                System.out.println("End wait(), ThreadName = " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread_12 extends Thread {
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            count += i + 1;
            Thread.yield();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(" Time is " + (endTime - beginTime) + "");
    }
}


/**
 * ThreadPool
 */
class ThreadPool {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<Integer>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(20000));
        final Random random = new Random();
        for (int i = 0; i < 20000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(list.size());
    }
}

class NonThreadPool {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println();
        final List<Integer> list = new LinkedList<Integer>();
        final Random random = new Random();
        for (int i = 0; i < 20000; i++) {
            Thread thread = new Thread() {
                public void run() {
                    list.add(random.nextInt());
                }
            };

            thread.start();

            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(list.size());

    }
}

/**
 * Timer
 */
class Timer_1 {
    private static Timer timer = new Timer("Guard", false);

    static public class MyTask_1 extends TimerTask {

        @Override
        public void run() {
            System.out.println("Now CurrentTime is " + new Date());
            this.cancel();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public class MyTask_2 extends TimerTask {

        @Override
        public void run() {
            System.out.println("Now CurrentTime is " + new Date());

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //TimerTask 是以队列形式顺序执行，所以前一个任务耗时过长，后边任务运行时间延迟
        MyTask_1 task_1 = new MyTask_1();
        MyTask_2 task_2 = new MyTask_2();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr_1 = "2016-5-4 11:22:30";
        String dateStr_2 = "2016-5-4 11:22:35";
        try {
            Date dateRef_1 = sdf.parse(dateStr_1);
            Date dateRef_2 = sdf.parse(dateStr_2);
            timer.schedule(task_1, dateRef_1, 20000);

            timer.schedule(task_2, dateRef_2, 20000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}