/**
 * 哲学家
 * 
 * @author win7
 * 
 */
public class Philosopher extends Thread {
	/**
	 * 筷子
	 * 
	 * @author win7
	 * 
	 */
	static class Chopstic {

	}

	private Chopstic left, right;
	private String name;

	Philosopher(String name, Chopstic left, Chopstic right) {
		this.name = name;
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println(name + "思考一会儿");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (left) {
				//都拿起筷子，手动造成死锁
				try {
					Thread.sleep(10000);
					System.out.println(name + "拿起做筷子中");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (right) {
					try {
						System.out.println(name + "吃饭中");
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		Philosopher[] pers = new Philosopher[5];
		Chopstic[] chop = new Chopstic[5];
		for (int i = 0; i < chop.length; i++) {
			chop[i] = new Chopstic();
		}
		for (int i = 0; i < pers.length; i++) {
			int left = i-1;
			if(left == -1){
				left = 4;
			}
			pers[i] = new Philosopher("哲学家" + i,chop[i],chop[left]);
		}
		// 创建线程需要耗费时间，所以等创建完毕后再启动，这样竞争锁更公平
		for (int i = 0; i < pers.length; i++) {
			pers[i].start();
		}
	}
}
