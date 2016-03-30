package xws;

import java.util.Date;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		long dates = new Date().getTime();
		test(0, 10000000, 103);
		long datee = new Date().getTime();
		System.out.println(datee - dates);

		// Object[] is = new Object[1000000];
		// Object[] is2 = new Object[1000000];
		// System.out.println("start");
		// for (int i = 0; i < is.length; i++) {
		// is[i] = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 12, 13, 45, 14,
		// 15, 15, 15, 23, 42, 53, 53 };
		// }
		// System.out.println(is[10]);

		// Thread.sleep(5000);
		// byte[] bs = (byte[]) is[9999];
		// System.out.println(bs);
	}

	/**
	 * 2分查找
	 */
	public static void test(int min, int max, int test) {
		int two = (max - min) / 2;// 2分支
		int index = 0;// 查找次数
		while (true) {
			index = index + 1;
			System.out.println("index=" + index + " min=" + min + " two=" + two
					+ " max=" + max);

			if (test > two) {
				min = two;
				two = (max - min) / 2 + min;
			} else if (test < two) {
				max = two;
				two = (max - min) / 2 + min;
			}
			if (test == two) {
				System.out.println("找到了：" + test);
				return;
			}
		}
	}
}
