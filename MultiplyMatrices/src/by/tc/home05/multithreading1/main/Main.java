package by.tc.home05.multithreading1.main;

import java.util.Scanner;

import by.tc.home05.multithreading1.multiplication.MultiplicationMatrices;

public class Main {
    public static void main(String[] args) {
    	//boolean flag=true, flag2=true;
    	System.out.println("������� ���������� ��������� ������� ( < 1000), ����� ������� Enter > ");
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();
    	System.out.println("������� ���������� ������� ( < ���������� ��������� �������), ����� ������� Enter > ");
    	int countThreads = sc.nextInt();
    	sc.close();
    
        int[][] firstMatrix = new int[num][num];
        int[][] secondMatrix = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                firstMatrix[i][j] = i + 1;
                secondMatrix[i][j] = i + 1;
            }
        }
        long beginTime = System.currentTimeMillis();
        try {
            MultiplicationMatrices MM = new MultiplicationMatrices(firstMatrix, secondMatrix, countThreads);
            int[][] finalMatrix = MM.matrMultiplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - beginTime;
        System.out.println("������� �����������! ����� ������������ ������ > " + time + " �����������.");
    }
}