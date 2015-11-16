package frmMain;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shapes{
	private int x1,y1;
	private int x2,y2;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}
	
	@Override
	public boolean inside(int x, int y) {
		// TODO Auto-generated method stub
		double yReal=(double)(y2-y1)/(x2-x1)*x+(double)(x2*y1-x1*y2)/(x2-x1);
		boolean satisfy;
		satisfy=Math.abs(y-yReal)<3&&(x>=Math.min(x1, x2)&&x<=Math.max(x1, x2))&&(y>=Math.min(y1, y2)&&y<=Math.max(y1, y2));
		return satisfy;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Line "+x1+" "+y1+" "+x2+" "+y2+"\n";
	}
	
	public Line(int x1,int y1,int x2, int y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	
	public void setCoordinate(int x1,int y1,int x2, int y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
}
