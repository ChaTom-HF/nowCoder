package com.newcoder.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static final Object lock = new Object();

    public static void main(String[] args) {

        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
//        getNotifyThread();

    }

    private static Thread getNotifyThread() {
        Thread notifyThread = new Thread(new Notify(), "notifyThread@" + getTime());
        notifyThread.start();
        return notifyThread;
    }

    private static String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            Thread notifyThread = null;
            //加锁，拥有lock的Moitor
            synchronized (lock) {
                while (flag) {
                    try {
                        if (notifyThread != null) {
                            notifyThread.interrupt();
                        }
                        System.out.println(Thread.currentThread() + "[wait] flag is true. waitting@" + getTime());
                        notifyThread = getNotifyThread();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //执行wait之后会放弃锁并进入对象的等待队列中，进入等待状态
                        //当被唤醒后会自动重新获得锁
                        //而sleep就是直接去睡觉不会释放锁
						/*
						线程状态由 RUNNING 变为 WAITING，并将当前线程放置到对象的等待队列中
						 */
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "[waitEnd] flag is false. running@" + getTime());
            }
        }
    }


    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                int r = (int) (1 + Math.random() * (10 - 1 + 1));
                flag = r != 6;
                //获取lock的锁，然后进行通知，通知是不会释放lock的锁，
                //直到当前线程释放了 lock 后，WaitThread才能从 wait 方法中返回
                System.out.println(Thread.currentThread() + "[notifyAll] hold lock. notify@" + getTime() + ", r:" + r);
				/*
				使用 nofity() 会更加的高效。需要注意的是，nofity() 在某些情况下却会导致死锁，所以只有在经过精细地设计后，才能使用 nofity()。
				总的来讲，一开始应该总是使用 notifyAll()，只有在发现确实它导致性能问题时，才考虑 notify()，并且对死锁问题给予足够的关注。
				唤醒并一定真得能立刻唤醒，它需要等待调用 notify()或notifyAll() 的线程释放锁之后，等待线程才有机会从 wait() 返回。

				notify() 方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而 notifyAll() 方法则是将等待队列中所有线程全部移动到同步对象。
				被移动的线程状态由 WAITING 变为 BLOCKED
				 */
                if (flag) {
                    lock.notifyAll();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread() + "[sleep] hold lock. notify@" + getTime());
                try {
                    //而sleep就是直接去睡觉不会释放锁
                    //所以 lock.wait() 想要 re-obtain ownership of the monitor and resumes execution
                    // 必须等待 睡眠结束
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
