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

	// ��������Ƿ�Ϊ����
	public int intCheck() {
		while (true) {
			Scanner in = new Scanner(System.in);
			try {
				int input = in.nextInt();
				return input;
			} catch (InputMismatchException e) {
				System.out.println("���벻��������������һ��������");
			}
		}
	}

	// ����Ͻ��Ƿ�С���½�
	public boolean boundaryCheck(int low, int high) {
		if (low <= high) {
			return true;
		}
		return false;
	}

	// 1. ���뷶Χ��飬���������룬����Ƿ�Ϊ�������Ͻ��Ƿ�С���½磬��������
	public int[] check() {
		while (true) {
			System.out.println("��������Է�Χ���½磺");
			int low = intCheck();
			System.out.println("��������Է�Χ���Ͻ磺");
			int high = intCheck();
			if (boundaryCheck(low, high)) {
				int[] range = new int[] { low, high };
				System.out.println("�������뷶Χ�ǣ�" + Arrays.toString(range));
				return range;
			} else {
				System.out.println("�Ͻ粻��С���½磬���������룡");
				continue;
			}
		}
	}

	// 2. �����뷶Χ���еȼ��໮�֣�����������䣬�����������������
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
		System.out.println("��Ч����Ϊ��" + Arrays.toString(par[1]));
		System.out.println("��Ч����Ϊ��" + Arrays.toString(par[0]) + ", " + Arrays.toString(par[2]));
		return par;
	}

	// 3. ���ر߽�ֵ������ȼ��໮�ֵ��������䣬����߽�ֵ
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
				+ "����߽�ֵ������������������" + Arrays.toString(boundary));
		System.out.println(
				"-------------------------------------------------------------------------------------------------->");
		return boundary;
	}

	// ��������������������ͨ��hashSet��random��������ָ�����������ظ����ֵ���������������
	public int[] randomNums(int[] range, int num) {
		if (num == 0) {
			System.out.println(Arrays.toString(range) + "���䲻�������ֵ��");
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
			System.out.println(Arrays.toString(range) + "�����������ֵΪ��" + Arrays.toString(random));
			return random;
		}
	}

	// ����ȥ���߽�ֵ�����䣬����������ÿ����������ֵ�������������ֵ����������
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
				System.out.println("������Ҫ���ɵ�" + Arrays.toString(rPar[i]) + "��������ֵ������");
				int num = intCheck();
				if (num + low - 1 <= high) {
					ranNum[i] = num;
					break;
				} else {
					System.out.println("���ֵ�������ܴ�������Ԫ���������������룡");
					continue;
				}
			}
		}
		return ranNum;
	}

	// ����ȼ��໮�ֵ����䣬���ر߽�ֵȥ���������
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

	// 4. ��������ɣ�����ȥ���߽�ֵ���������䣬��ÿ�������Ӧ��������������������������
	public int[] random(int[][] rPar, int[] ranNum) {
		System.out.println("������" + Arrays.toString(rPar[0]) + ", " + Arrays.toString(rPar[1]) + ", "
				+ Arrays.toString(rPar[2]) + "�����������");
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
				+ "������������ɵ�������������" + Arrays.toString(random));
		System.out.println(
				"-------------------------------------------------------------------------------------------------->");
		return random;
	}

	// 5.������
	public static void main(String[] args) {
		BlackBox t = new BlackBox();
		int[] range = t.check();
		int[][] par = t.partition(range);
		t.boundary(par);
		par = t.ridBoundary(par);
		t.random(par, t.rangeCheck(par));
		System.out.println("����ִ�н�����");
	}
}
