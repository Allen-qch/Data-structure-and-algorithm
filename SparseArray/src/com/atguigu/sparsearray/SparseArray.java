package com.atguigu.sparsearray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SparseArray {
	public static void main(String[] args) throws Exception {
		//创建一个二维棋盘，其中无子表示0；1表示黑子；2表示蓝子
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
		//输出原始二维数组
		System.out.println("原始的二维数组-----");
		for(int[]row:chessArr1) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//将二维数组转换为稀疏数组
		//1，先遍历二维数组 得到非0数据的个数
		int sum = 0;
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArr1[i][j]!=0) {
					sum++;
				}
			}
		}
		//System.out.println(sum);
		
		//创建对应的稀疏数组
		int sparseArray[][] =new int[sum+1][3];
		//给稀疏数组赋值
		sparseArray[0][0]=11;
		sparseArray[0][1]=11;
		sparseArray[0][2]=sum;
		//遍历二维数组，将非0的值存放到sparseArr中
		int count = 0;//count用于记录第几个非0数据
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
		//输出稀疏数组的形式
		System.out.println();
		System.out.println("输出稀疏数组为-----");
		
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
		
		//将稀疏数组还原成原始的二维数组
		/*1. 先读取稀疏数组的第一行， 根据第一行的数据， 创建原始的二维数组， 比如上面的 chessArr2 = int[11][11]
		2. 在读取稀疏数组后几行的数据， 并赋给 原始的二维数组 即可.*/
		//1. 先读取稀疏数组的第一行， 根据第一行的数据， 创建原始的二维数组
		int chessArr2[][]=new int[sparseArray[0][0]][sparseArray[0][1]];
		//2. 在读取稀疏数组后几行的数据(从第二行开始)， 并赋给 原始的二维数组 即可
		for(int i=1;i<sparseArray.length;i++) {
			chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}
		//恢复后的二维数组
		System.out.println();
		System.out.println("恢复后的二维数组");
		for(int[] row:chessArr2) {
			for(int data:row) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		
		//四、将稀疏数组保存到磁盘上，如map.data
		
			System.out.println("将稀疏数组保存到磁盘上，如map.data---");
			File f = new File("E:\\map.data");
			FileOutputStream fos = new FileOutputStream(f);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			System.out.println("写入中------");
			for(int i=0;i<sparseArray.length;i++) {
				osw.write(sparseArray[i][0]+","+sparseArray[i][0]+","+sparseArray[i][0]+ ",");
			}
			osw.close();
			fos.close();
			System.out.println("写入磁盘成功");
			
		//五、将磁盘的文件读出来
			
			//读取磁盘中的map.data文件
			System.out.println("读取中--------------");
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			StringBuffer sb = new StringBuffer();
			while(isr.ready()) {
				sb.append((char)isr.read());
			}
			isr.close();
			fis.close();
			System.out.println("读取成功");
			String ss = sb.toString();
			String[] sb1 = sb.toString().split(",");
			System.out.printf("从磁盘上读取的字符串为：\n%s\n",ss);	//格式化输出
			//恢复稀疏数组
			int sum1 = 0;
			int[][] sparseArr1 = new int[sb1.length/3][3];
			sparseArr1[0][0] = Integer.parseInt(sb1[0]);
			sparseArr1[0][1] = Integer.parseInt(sb1[1]);
			sparseArr1[0][2] = Integer.parseInt(sb1[2]);
			for(int i=3;i<sb1.length;i+=3) {
				sum1++;
				sparseArr1[sum1][0] = Integer.parseInt(sb1[i]);
				sparseArr1[sum1][1] = Integer.parseInt(sb1[i+1]);
				sparseArr1[sum1][2] = Integer.parseInt(sb1[i+2]);
			}
			System.out.println("还原后的稀疏数组为：");
			for(int i=0;i<sparseArr1.length;i++) {
				System.out.printf("%d\t%d\t%d\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
			}
			//恢复成棋盘数组
			int[][] chessArr3 = new int[sparseArr1[0][0]][sparseArr1[0][1]]; 
			for(int i=1;i<sparseArr1.length;i++) {
				chessArr3[sparseArr1[i][0]][sparseArr1[i][1]] = sparseArr1[i][2]; 
			}
			//显示棋盘二维数组
			for(int[] chessArr00:chessArr3) {
				for(int data00:chessArr00) {
					System.out.printf("%d\t",data00);
				}
				System.out.println();//作用：输入一行之后转为下一行
			}
			/*for(int[] a : chessArr3) {
				for(int b : a) {
					System.out.printf("%d\t", b);
				}
				System.out.println();
			}*/
	}
	
	
}
