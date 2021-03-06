package com.company;

public class Keyboard {
    private String right;
    private String left;
    private String down;
    private String up;
    private String option;

    public Keyboard(String right, String left, String down, String up, String option) {
        this.right = right;
        this.left = left;
        this.down = down;
        this.up = up;
        this.option = option;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
