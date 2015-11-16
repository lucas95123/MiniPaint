package frmMain;

import java.awt.Graphics;

public class Text extends Shapes{
	private int x,y;
	private String str;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(str, x, y);
	}
	
	@Override
	public boolean inside(int x, int y) {
		// TODO Auto-generated method stub
		return Math.abs(x-this.x)<=str.length()*10&&Math.abs(this.y-y)<=10;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(str.equals("\n"))
			return "";
		if(str.contains("\n"))
			if(str.indexOf("\n")==0)
				str=str.substring(2, str.length());
			else
				str=str.substring(0, str.indexOf("\n"));
		return "Text "+str+" "+x+" "+y+" ";
	}
	
	public Text(String str,int x,int y){
		this.str=str;
		this.x=x;
		this.y=y;
	}
	
	public String getText(){
		return str;
	}
}
