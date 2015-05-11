/**
 * synchronize test
 * @author win7
 *
 */
public class SynchronizeTest {
	static synchronized void add() {
		try {
			System.out.println("add");
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static synchronized void plus() {
		try {
			System.out.println("plus");
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static class MyThread1 extends Thread{
		@Override
		public void run(){
			SynchronizeTest.add();
		}
		
	}
	static class MyThread2 extends Thread{
		@Override
		public void run(){
			SynchronizeTest.plus();
		}
		
	}
	public static void main(String[] args) {
		MyThread1 t1 = new MyThread1();
		MyThread2 t2 = new MyThread2();
		t1.start();
		t2.start();
	}
}
