package com.workbench.util;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("######0.00");
        int n = in.nextInt();//n种商品
        int m = in.nextInt();//m种满减
        // 使用特价
        double disSum = 0;
        // 使用满减
        double minSum = 0;
        // 每种商品金额
        double tmp = 0;
        for (int i = 0; i < n * 2; i++) {
            if (i % 2 == 0) {
                tmp = in.nextInt();
                minSum += tmp;
            } else {
                if (in.nextInt() == 0) {
                    disSum += tmp;
                } else {
                    disSum += tmp * 0.8;
                }
            }
        }
        tmp = 0;
        int curTmp = 0;
        for (int i = 0; i < m; i++) {
            if (minSum >= in.nextInt()) {
                curTmp = in.nextInt();
                if (curTmp > tmp) {
                    tmp = curTmp;
                }
            }
        }
        minSum = minSum - tmp;
        System.out.println(df.format(minSum > disSum ? disSum : minSum));
    }
}