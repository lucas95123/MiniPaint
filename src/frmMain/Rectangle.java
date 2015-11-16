package frmMain;

import java.awt.Graphics;

public class Rectangle extends Shapes{
	private int x,y;
	private int width,height;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(x, y, width, height);
	}
	
	@Override
	public boolean inside(int x, int y) {
		// TODO Auto-generated method stub
		return Math.abs(x-this.x)<width&&Math.abs(y-this.y)<height;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Rect "+x+" "+y+" "+width+" "+height+"\n";
	}
	
	public Rectangle(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
}
