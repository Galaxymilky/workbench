package demo.dp;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class Box extends Canvas implements Observer {
    Observable notifier;
    int x, y; // Locations in grid
    Color cColor = newColor();
    static final Color[] colors = {Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green, Color.lightGray,
            Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow
    };

    static final Color newColor() {
        return colors[
                (int) (Math.random() * colors.length)
                ];
    }

    Box(int x, int y, Observable notifier) {
        this.x = x;
        this.y = y;
        notifier.addObserver(this);
        this.notifier = notifier;
        addMouseListener(new Box.ML());
    }

    public void paint(Graphics g) {
        g.setColor(cColor);
        Dimension s = getSize();
        g.fillRect(0, 0, s.width, s.height);
    }

    class ML extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            notifier.notifyObservers(Box.this);
        }
    }

    public void update(Observable o, Object arg) {
        Box clicked = (Box) arg;
        if (nextTo(clicked)) {
            cColor = clicked.cColor;
            repaint();
        }
    }

    private final boolean nextTo(Box b) {
        return Math.abs(x - b.x) <= 1 && Math.abs(y - b.y) <= 1;
    }
}
