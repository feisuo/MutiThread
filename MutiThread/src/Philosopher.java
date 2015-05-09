/**
 * ��ѧ��
 * 
 * @author win7
 * 
 */
public class Philosopher extends Thread {
	/**
	 * ����
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
				System.out.println(name + "˼��һ���");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (left) {
				//��������ӣ��ֶ��������
				try {
					Thread.sleep(10000);
					System.out.println(name + "������������");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (right) {
					try {
						System.out.println(name + "�Է���");
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
			pers[i] = new Philosopher("��ѧ��" + i,chop[i],chop[left]);
		}
		// �����߳���Ҫ�ķ�ʱ�䣬���Եȴ�����Ϻ�����������������������ƽ
		for (int i = 0; i < pers.length; i++) {
			pers[i].start();
		}
	}
}
