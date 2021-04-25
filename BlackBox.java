package hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class BlackBox {
	
	public final int min = Integer.MIN_VALUE;
	public final int max = Integer.MAX_VALUE;

	// 检查输入是否为整数
	public int intCheck() {
		while (true) {
			Scanner in = new Scanner(System.in);
			try {
				int input = in.nextInt();
				return input;
			} catch (InputMismatchException e) {
				System.out.println("输入不是整数，请输入一个整数：");
			}
		}
	}

	// 检查上界是否小于下界
	public boolean boundaryCheck(int low, int high) {
		if (low <= high) {
			return true;
		}
		return false;
	}

	// 1. 输入范围检查，命令行输入，检查是否为整数和上界是否小于下界，返回整数
	public int[] check() {
		while (true) {
			System.out.println("请输入测试范围的下界：");
			int low = intCheck();
			System.out.println("请输入测试范围的上界：");
			int high = intCheck();
			if (boundaryCheck(low, high)) {
				int[] range = new int[] { low, high };
				System.out.println("您的输入范围是：" + Arrays.toString(range));
				return range;
			} else {
				System.out.println("上界不能小于下界，请重新输入！");
				continue;
			}
		}
	}

	// 2. 对输入范围进行等价类划分，输入测试区间，返回三个区间的数组
	public int[][] partition(int[] range) {
		int low = range[0];
		int high = range[1];
		int[] left, right;
		if (low == min) {
			left = new int[] {};
		} else {
			left = new int[] { min, low - 1 };
		}
		if (high == max) {
			right = new int[] {};
		} else {
			right = new int[] { high + 1, max };
		}
		int[][] par = new int[][] { left, { low, high }, right };
		System.out.println("有效区间为：" + Arrays.toString(par[1]));
		System.out.println("无效区间为：" + Arrays.toString(par[0]) + ", " + Arrays.toString(par[2]));
		return par;
	}

	// 3. 返回边界值，输入等价类划分的三个区间，输出边界值
	public int[] boundary(int[][] par) {
		int[] range = par[1];
		int low = range[0];
		int high = range[1];
		ArrayList<Integer> b = new ArrayList<>();
		if (par[0].length == 2) {
			b.add(low - 1);
		}
		if (low == high) {
			b.add(low);
		} else if (high == low + 1) {
			b.add(low);
			b.add(high);
		} else if (high == low + 2) {
			b.add(low);
			b.add(low + 1);
			b.add(high);
		} else {
			b.add(low);
			b.add(low + 1);
			b.add(high - 1);
			b.add(high);
		}
		if (par[2].length == 2) {
			b.add(high + 1);
		}
		int[] boundary = new int[b.size()];
		for (int i = 0; i < boundary.length; i++) {
			boundary[i] = b.get(i);
		}
		System.out.println(
				"<--------------------------------------------------------------------------------------------------");
		System.out.println(Arrays.toString(par[0]) + ", " + Arrays.toString(par[1]) + ", " + Arrays.toString(par[2])
				+ "区间边界值分析导出测试用例：" + Arrays.toString(boundary));
		System.out.println(
				"-------------------------------------------------------------------------------------------------->");
		return boundary;
	}

	// 输入区间和随机数数量，通过hashSet和random方法生成指定数量的无重复随机值，返回随机数数组
	public int[] randomNums(int[] range, int num) {
		if (num == 0) {
			System.out.println(Arrays.toString(range) + "区间不生成随机值。");
			return new int[0];
		} else {
			int low = range[0];
			int high = range[1];
			Set<Integer> set = new HashSet<>();
			while (set.size() < num) {
				double r = Math.random();
				Integer ran = ((int) (low + r * high - r * low + r));
				set.add(ran);
			}
			int[] random = new int[set.size()];
			Iterator<Integer> iterator = set.iterator();
			for (int i = 0; iterator.hasNext(); i++) {
				random[i] = iterator.next();
			}
			System.out.println(Arrays.toString(range) + "区间生成随机值为：" + Arrays.toString(random));
			return random;
		}
	}

	// 输入去除边界值的区间，命令行输入每个区间的随机值数量，返回随机值数量的数组
	public int[] rangeCheck(int[][] rPar) {
		int[] ranNum = new int[3];
		int low;
		int high;
		for (int i = 0; i < rPar.length; i++) {
			if (rPar[i].length != 0) {
				low = rPar[i][0];
				high = rPar[i][1];
			} else {
				low = 1;
				high = 0;
			}
			while (true) {
				System.out.println("请输入要生成的" + Arrays.toString(rPar[i]) + "区间的随机值数量：");
				int num = intCheck();
				if (num + low - 1 <= high) {
					ranNum[i] = num;
					break;
				} else {
					System.out.println("随机值数量不能大于区间元素数，请重新输入！");
					continue;
				}
			}
		}
		return ranNum;
	}

	// 输入等价类划分的区间，返回边界值去除后的区间
	public int[][] ridBoundary(int[][] par) {
		int[] range = par[1];
		int low = range[0];
		int high = range[1];
		if (high <= low + 3) {
			par[1] = new int[0];
		} else {
			par[1] = new int[] { low + 2, high - 2 };
		}
		if (par[0].length == 0 || par[0][1] == min) {
			par[0] = new int[0];
		} else {
			par[0] = new int[] { min, par[0][1] - 1 };
		}
		if (par[2].length == 0 || par[2][0] == max) {
			par[2] = new int[0];
		} else {
			par[2] = new int[] { par[2][0] + 1, max };
		}
		return par;
	}

	// 4. 随机数生成，输入去除边界值的三个区间，和每个区间对应的随机数数量，返回所有随机数
	public int[] random(int[][] rPar, int[] ranNum) {
		System.out.println("对区间" + Arrays.toString(rPar[0]) + ", " + Arrays.toString(rPar[1]) + ", "
				+ Arrays.toString(rPar[2]) + "生成随机数：");
		ArrayList<Integer> r = new ArrayList<>();
		for (int i = 0; i < rPar.length; i++) {
			int[] ranx = randomNums(rPar[i], ranNum[i]);
			for (int j = 0; j < ranx.length; j++) {
				r.add(ranx[j]);
			}
		}
		int[] random = new int[r.size()];
		for (int i = 0; i < r.size(); i++) {
			random[i] = r.get(i);
		}
		System.out.println(
				"<--------------------------------------------------------------------------------------------------");
		System.out.println(Arrays.toString(rPar[0]) + ", " + Arrays.toString(rPar[1]) + ", " + Arrays.toString(rPar[2])
				+ "区间随机数生成导出测试用例：" + Arrays.toString(random));
		System.out.println(
				"-------------------------------------------------------------------------------------------------->");
		return random;
	}

	// 5.主程序
	public static void main(String[] args) {
		BlackBox t = new BlackBox();
		int[] range = t.check();
		int[][] par = t.partition(range);
		t.boundary(par);
		par = t.ridBoundary(par);
		t.random(par, t.rangeCheck(par));
		System.out.println("程序执行结束。");
	}
}
