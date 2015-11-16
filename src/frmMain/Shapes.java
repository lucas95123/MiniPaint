package frmMain;

import java.awt.Graphics;

public abstract class Shapes {
	public abstract String toString();
	public abstract void draw(Graphics g);
	public abstract boolean inside(int x,int y);
}
