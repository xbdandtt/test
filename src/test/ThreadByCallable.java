package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadByCallable implements Callable<Integer>{

	static ExecutorService mExecutor = Executors.newSingleThreadExecutor();  
	
	@Override
	public Integer call() throws Exception {
		System.out.println("当前线程名是：" + Thread.currentThread().getName());
		int i=0;
		for(;i<5;i++){
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		return i;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ThreadByCallable tc = new ThreadByCallable();
		FutureTask<Integer> future = new FutureTask<Integer>(tc);
		FutureTask<Integer> future1 = new FutureTask<Integer>(tc);
		new Thread(future, "线程1").start();
		new Thread(future1, "线程2").start();
		try{
			System.out.println("子线程返回值：" + future.get());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Future<?> f = mExecutor.submit(tc);
		System.out.println("future result from callable : " + f.isDone()); 
		
	}
}

