package demo.dp;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

public class BoxObserver extends Frame {
    Observable notifier = new BoxObservable();

    public BoxObserver(int grid) {
        setTitle("Demonstrates Observer pattern");
        setLayout(new GridLayout(grid, grid));
        for (int x = 0; x < grid; x++)
            for (int y = 0; y < grid; y++) add(new Box(x, y, notifier));
    }

    public static void main(String[] args) {
        int grid = 8;
        if (args.length > 0)
            grid = Integer.parseInt(args[0]);
        Frame f = new BoxObserver(grid);
        f.setSize(500, 400);
        f.setVisible(true);
        f.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
    }
}