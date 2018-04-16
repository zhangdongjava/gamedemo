package com.zzz.study.netty;

/**
 * Created by zha on 2018/4/8.
 */
public class Demo1 {

    public static void main(String[] args) {
        String cs = "12132564564313213489dsadadafsf";
        int num = 26;//排数
        int row = 1;//第几行
        int rowNum = (num * 2 - 1);//每排个数
        for (int i = 0; i < num * rowNum; i++) {
            if (i % rowNum == 0 && i > 0) {
                System.out.println();
                row++;
            }
            //(num - row) 当前行的空格数
            if (i - rowNum * (row - 1) == (num - row)) {//前
                System.out.print(cs.charAt(row-1));
            } else if (rowNum * row - (num - row) == i +1) {// 后
                System.out.print(cs.charAt(row-1));
            }else if (row == num) {// 最后一排
                System.out.print(cs.charAt(row-1));
            } else {
                System.out.print(' ');
            }

        }
    }
}
