package frmMain;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shapes {
	protected Color color;
	public Shapes(Color c){
		color=c;
	}
	public abstract String toString();
	public abstract void draw(Graphics g);
	public abstract boolean inside(int x,int y);
}
