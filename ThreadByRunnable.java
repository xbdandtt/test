package test;

public class ThreadByRunnable implements Runnable{

	private int i=20,number =10;
	
	 public void firstMethod() throws Exception {
	        synchronized (this) {
	            number += 100;
	            System.out.println(number);
	        }
	    }
	
	 public void secondMethod() throws Exception {
	        synchronized (this) {
	            /**
	             * (休息2S,阻塞线程)
	             * 以验证当前线程对象的机锁被占用时,
	             * 是否被可以访问其他同步代码块
	             */
	            Thread.sleep(2000);
	            //this.wait(2000);
	            number *= 200;
	        }
	    }

	 
	@Override
	public void run() {
		/*System.out.println("当前线程名称是：" + Thread.currentThread().getName());
		for (; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);

            try {
                // 因为sleep是静态方法，所以不需要通过Thread.currentThread()方法获取当前线程句柄
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }*/
		try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) throws Exception {
//        ThreadByRunnable st = new ThreadByRunnable();
//        ThreadByRunnable st1 = new ThreadByRunnable();
//        new Thread(st, "新线程1").start();
//        new Thread(st1, "新线程2").start();
		ThreadByRunnable threadTest = new ThreadByRunnable();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
        System.out.println("number="+threadTest.number);
    }
}
