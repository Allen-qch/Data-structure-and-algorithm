package com.atguigu.sparsearray;

public class SparseArray {
	public static void main(String[] args) {
		//����һ����ά���̣��������ӱ�ʾ0��1��ʾ���ӣ�2��ʾ����
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
		//���ԭʼ��ά����
		System.out.println("ԭʼ�Ķ�ά����-----");
		for(int[]row:chessArr1) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//����ά����ת��Ϊϡ������
		//1���ȱ�����ά���� �õ���0���ݵĸ���
		int sum = 0;
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArr1[i][j]!=0) {
					sum++;
				}
			}
		}
		//System.out.println(sum);
		
		//������Ӧ��ϡ������
		int sparseArray[][] =new int[sum+1][3];
		//��ϡ�����鸳ֵ
		sparseArray[0][0]=11;
		sparseArray[0][1]=11;
		sparseArray[0][2]=sum;
		//������ά���飬����0��ֵ��ŵ�sparseArr��
		int count = 0;//count���ڼ�¼�ڼ�����0����
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArr1[i][j]!=0) {
					count++;
					sparseArray[count][0]=i;
					sparseArray[count][1]=j;
					sparseArray[count][2]=chessArr1[i][j];
				}
			}
		}
		//���ϡ���������ʽ
		System.out.println();
		System.out.println("���ϡ������Ϊ-----");
		
		/*for(int[] row:sparseArray) {
			for(int data:row) {
				System.out.printf("%d\t",data);
				System.out.println();
			}
		}*/
		for(int i=0;i<sparseArray.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
		}
		System.out.println();
		
		//��ϡ�����黹ԭ��ԭʼ�Ķ�ά����
		/*1. �ȶ�ȡϡ������ĵ�һ�У� ���ݵ�һ�е����ݣ� ����ԭʼ�Ķ�ά���飬 ��������� chessArr2 = int[11][11]
		2. �ڶ�ȡϡ��������е����ݣ� ������ ԭʼ�Ķ�ά���� ����.*/
		//1. �ȶ�ȡϡ������ĵ�һ�У� ���ݵ�һ�е����ݣ� ����ԭʼ�Ķ�ά����
		int chessArr2[][]=new int[sparseArray[0][0]][sparseArray[0][1]];
		//2. �ڶ�ȡϡ��������е�����(�ӵڶ��п�ʼ)�� ������ ԭʼ�Ķ�ά���� ����
		for(int i=1;i<sparseArray.length;i++) {
			chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		//�ָ���Ķ�ά����
		System.out.println();
		System.out.println("�ָ���Ķ�ά����");
		for(int[] row:chessArr2) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//�ġ���ϡ�����鱣�浽�����ϣ���map.data
		System.out.println("��ϡ�����鱣�浽�����ϣ���map.data---");
		
		
		
		
	}
	
	
}
