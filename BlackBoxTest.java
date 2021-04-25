package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import hw.BlackBox;

class BlackBoxTest {
	
	public static final int min = Integer.MIN_VALUE;
	public static final int max = Integer.MAX_VALUE;
	BlackBox b;

	@BeforeEach
	void setUp() throws Exception {
		this.b = new BlackBox();
		System.out.println("......TESTING......");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.b = null;
	}

	@ParameterizedTest
	@MethodSource
	void testPartition(int[] input, int[][] result) {
		assertArrayEquals(result, b.partition(input));
	}

	static List<Arguments> testPartition() {
	    return List.of( // arguments:
	    		//�ȼ��໮�ֳ������Ϊ�����û���������[low,high]��һ������»���Ϊ[min,low-1],[low,high],[high+1,max]�����ܳ��ֵ���������lowΪmin��highΪmaxʱ����Ӧ��һ������ӦΪ������
	            Arguments.arguments(new int[] {-2,6}, new int[][] {{min,-3},{-2,6},{7,max}}),
	    		Arguments.arguments(new int[] {min,6}, new int[][] {{},{min,6},{7,max}}),
				Arguments.arguments(new int[] {-2,max}, new int[][] {{min,-3},{-2,max},{}}),
				Arguments.arguments(new int[] {min,max}, new int[][] {{},{min,max},{}}));
	}

	@ParameterizedTest
	@MethodSource
	void testBoundary(int[][] input, int[] result) {
		assertArrayEquals(result, b.boundary(input));
	}
	
	static List<Arguments> testBoundary() {
	    return List.of( // arguments:
	    		//�߽�ֵ�����������Ϊ�����û���������[low,high]��һ������µ���[low-1,low,low+1,high-1,high,high+1]�����ܳ��ֵ���������(1)lowΪmin��highΪmaxʱ����Ӧ��һ�߲�ȡlow-1��high+1(2)low��high��ֵ<=2ʱ��low+1��high-1Ҫѡ���Ե���
	            Arguments.arguments(new int[][] {{min,-3},{-2,6},{7,max}}, new int[] {-3,-2,-1,5,6,7}),
	            Arguments.arguments(new int[][] {{},{min,6},{7,max}}, new int[] {min,min+1,5,6,7}),
	            Arguments.arguments(new int[][] {{min,-3},{-2,max},{}}, new int[] {-3,-2,-1, max -1,max}),
	            Arguments.arguments(new int[][] {{},{min,max},{}}, new int[] {min, min+1, max-1,max}),
	            Arguments.arguments(new int[][] {{min,1},{2,4},{5,max}}, new int[] {1,2,3,4,5}),
	            Arguments.arguments(new int[][] {{min,1},{2,3},{4,max}}, new int[] {1,2,3,4}),
	            Arguments.arguments(new int[][] {{min,1},{2,2},{3,max}}, new int[] {1,2,3}));
	}

	@ParameterizedTest
	@MethodSource
	void testRandom(int[][] input1, int[] input2, int result) {
		assertTrue(b.random(input1, input2).length == result);
	}
	
	static List<Arguments> testRandom() {
	    return List.of( // arguments:
	    		//��������ɳ������Ϊ����ȥ���߽�ֵ���������䣬��ÿ�������Ӧ�������������һ������µ������ɵ���������������ܳ��ֵ��������ڵ��������������������������������
	            Arguments.arguments(new int[][] {{min,0},{4,6},{10,max}},new int[] {1,2,3},6),
	            Arguments.arguments(new int[][] {{},{min+2,6},{10,max}},new int[] {0,2,3},5),
	            Arguments.arguments(new int[][] {{min,0},{4,max-2},{}},new int[] {1,2,0},3),
	            Arguments.arguments(new int[][] {{},{min+2,max-2},{}},new int[] {0,2,0},2),
	            Arguments.arguments(new int[][] {{min,0},{4,4},{8,max}},new int[] {1,1,3},5),
	            Arguments.arguments(new int[][] {{min,0},{},{5,max}},new int[] {1,0,3},4));
	}

}
