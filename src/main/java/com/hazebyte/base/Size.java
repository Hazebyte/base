package com.hazebyte.base;

public class Size {

    private int size;

    private Size(int rows) {
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
