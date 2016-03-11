package luogh.learn.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
	private static int iter_counter = 0;
	public static void main(String args[]) {
//		int[] arrays = {6,1,2,7,9,3,4,5,10,8};
		int[] arrays = {4,1,2,5};
		sort(arrays,0,arrays.length-1);
	}
	
	public static void sort(int[] arrays,int beginIndex,int endIndex){
		if(beginIndex >= endIndex) return ;
		int refPosition = beginIndex;
		int endPosition = endIndex;
		int refValue = arrays[beginIndex]; // set reference value 
		while(beginIndex != endIndex) {
			//6,1,2,7,9,3,4,5,10,8
			// ��һ��ѭ��������ұ߿�ʼ���������߿�ʼ��ȥ��ѯ���ڲο�ֵ��ֵ��ȡ������������ֱ�ӵ���endIndex��λ�á����� 4 1 2 5 
			// ��ʱ��beginIndex = endIndex == 3.��ô�����Ļ����ڶ���ѭ����ʱ�޷�ִ�У��������б�Ϊ�� 5 1 2 4,��ʱ
			while(arrays[endIndex] >= refValue && beginIndex < endIndex){
				endIndex --;
			}
						
			while(arrays[beginIndex] <= refValue && beginIndex < endIndex){
				beginIndex ++;
			}
			
			if(arrays[beginIndex] > arrays[endIndex]){
				//swap 
				int temp = arrays[beginIndex];
				arrays[beginIndex] = arrays[endIndex];
				arrays[endIndex] = temp;
				
				System.out.println("Change value in position:["+beginIndex+","+endIndex+"]" +" Arrays Value:"+Arrays.toString(arrays));
			}
		}
		
		// reset reference value position in array,for the next iterate
		int temp = arrays[beginIndex];
		arrays[beginIndex] = arrays[refPosition];
		arrays[refPosition] = temp;
		
		System.out.println("iter Times:"+(++iter_counter)+" Arrays:"+Arrays.toString(arrays));
		System.out.println("refValue:"+refValue+" refPosition:"+refPosition+ " endPostion:"+endPosition);
		sort(arrays,refPosition,beginIndex-1);
		sort(arrays,beginIndex+1,endPosition);
		
	}
	
}
