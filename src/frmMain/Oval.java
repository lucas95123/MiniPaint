package frmMain;

import java.awt.Graphics;

public class Oval extends Shapes{
	private int x,y;
	private int width,height;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(x, y, width, height);
	}
	
	@Override
	public boolean inside(int x, int y) {
		// TODO Auto-generated method stub
		return (double)(this.x+width/2-x)*(this.x+width/2-x)/(width/2)/(width/2)+(double)(this.y+height/2-y)*(this.y+height/2-y)/(height/2)/(height/2)<1;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Oval "+x+" "+y+" "+width+" "+height+"\n";
	}
	
	public Oval(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
}
