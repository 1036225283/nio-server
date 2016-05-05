package xws;

import java.util.Date;

import com.nitian.util.random.UtilRandom;

public class FoldArray {

	public static void main(String[] args) throws InterruptedException {
		long dates = new Date().getTime();

		int[] ints = new int[100000000];

		for (int i = 0; i < ints.length; i++) {
			// ints[i] = (int) (Math.random() * 1000);
			ints[i] = i;
		}
		find(ints, 5003);
		long datee = new Date().getTime();
		System.out.println(datee - dates);
	}

	/**
	 * 查找单个值
	 * 
	 * @param ints
	 * @param value
	 */
	public static void find(int[] ints, int value) {
		int min = 0;// 区间左边界
		int max = ints.length;// 区间右边界
		int index = 0;
		while (max - min > 0) {
			System.out.println("查找次数：" + index++);
			int interval = max - min;// 区间
			int foldIndex = min + interval / 2 ;// 二分值索引(坐标)
			System.out.println("index：" + foldIndex);
			int foldValue = ints[foldIndex];// 二分值
			if (value == foldValue) {
				System.out.println(foldIndex);
				return;
			} else if (value > foldValue) {
				min = foldValue;// 重新设置左边界
			} else if (value < foldValue) {
				max = foldValue;// 重新设置右边界
			}
		}
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
