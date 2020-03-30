package com.hazebyte.base;

/**
 * Represents an inventory size.
 */
public class Size {

    public static final Size ONE = new Size(1),
        TWO = new Size(2),
        THREE = new Size(3),
        FOUR = new Size(4),
        FIVE = new Size(5),
        SIX = new Size(6);

    private int size;

    private Size(int rows) {
        if (rows < 0 || rows > 6) throw new IndexOutOfBoundsException("The number of rows should be between [0, 6]");
        this.size = rows * 9;
    }

    public static Size from(int slots) {
        if (slots < 10) {
            return new Size(1);
        } else if (slots < 19) {
            return new Size(2);
        } else if (slots < 28) {
            return new Size(3);
        } else if (slots < 37) {
            return new Size(4);
        } else if (slots < 46) {
            return new Size(5);
        } else {
            return new Size(6);
        }
    }

    public int toInt() {
        return size;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Size)) {
            return false;
        }

        Size size = (Size) object;
        return size.toInt() == this.toInt();
    }

}
