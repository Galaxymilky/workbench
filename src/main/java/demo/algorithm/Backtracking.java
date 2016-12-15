package demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niu_ben on 2016/11/21.
 */
public class Backtracking {

    static List<Integer> matrix;

    public static boolean solve1(int i, List<Integer> matrix, int n) {
        if (i == n) {
            if (isValid(matrix))
                return true;
            return false;
        } else {
            for (int j = 0; j < n; j++) {
                matrix.add(j);
                if (isValid(matrix)) {
                    if (solve1(i + 1, matrix, n))
                        return true;
                }
                matrix.remove(matrix.size() - 1);
            }
            return false;
        }
    }

    public static void solve2(int i, List<Integer> matrix, int n) {
        int count = 0;
        if (i == n) {
            if (isValid(matrix))
                count++;
            return;
        } else {
            for (int j = 0; j < n; j++) {
                matrix.add(j);
                if (isValid(matrix)) {
                    solve2(i + 1, matrix, n);
                }
                matrix.remove(matrix.size() - 1);
            }
        }
    }

    public static void solve3(int i, List<Integer> matrix, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (i == n) {
            if (isValid(matrix))
                result.add(new ArrayList<Integer>(matrix));
            return;
        } else {
            for (int j = 0; j < n; j++) {
                matrix.add(j);
                if (isValid(matrix)) {
                    solve3(i + 1, matrix, n);
                }
                matrix.remove(matrix.size() - 1);
            }
        }
    }

    public static boolean isValid(List<Integer> list) {
        int row = list.size() - 1;
        int col = list.get(row);
        for (int i = 0; i <= row - 1; i++) {
            int row1 = 1;
            int col1 = list.get(i);
            if (col == col1)
                return false;
            if (row1 - row == col1 - col)
                return false;
        }
        return true;
    }

}
