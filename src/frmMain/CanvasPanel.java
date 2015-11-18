/* @file CanvasPanel.java
* @brief Drawing Panel of Mini Canvas
* @author lucas95123@outlook.com
* @version 1.0
* @date 2015/11/17
*/
package frmMain;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

enum Mode{
	DRAW,TEXT,SELECT;
}

public class CanvasPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shapes> ShapeList=new ArrayList<>();/*List to hold all shapes on the canvas*/
	private int pressedX=0;/*The X of clicked coordinate*/
	private int pressedY=0;/*The Y of clicked coordinate*/
	private Button shapes;/*Which shape is selected to draw*/
	private Shapes previousShape;/*Previously draw shape*/
	private Shapes selectedShape;
	private Text previousText;/*Previously entered text*/
	private Mode currentMode=Mode.DRAW;/*Selection mode or drawing mode*/
	private boolean newShapes=true;/*Whether the drawing one is a new shape*/
	private boolean modified=false;
	private Color color;
	public CanvasPanel(){
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(currentMode!=Mode.SELECT){
					pressedX=e.getX();
					pressedY=e.getY();
					newShapes=true;
				}
				else{
					selectedShape=selecteShape(e.getX(),e.getY());
				}
			}
			public void mouseEntered(MouseEvent e){
				if(currentMode==Mode.DRAW){
					setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				}
				else if(currentMode==Mode.TEXT){
					setCursor(new Cursor(Cursor.TEXT_CURSOR));
				}
				else{
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(currentMode!=Mode.SELECT){
					if(previousText!=null){
						remove(previousText);
						previousText=new Text(previousText.getText(),e.getX(),e.getY(),color);
						add(previousText);
						repaint();
					}	
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(currentMode!=Mode.SELECT){
					switch (shapes) {
					case LINE:
						if(!newShapes)
							remove(previousShape);
						else
							newShapes=false;
						previousShape=new Line(pressedX, pressedY, e.getX(), e.getY(),color);
						add(previousShape);
						repaint();
						break;
					case RECTANGLE:
						if(!newShapes)
							remove(previousShape);
						else
							newShapes=false;
						if(e.getX()-pressedX<0&&e.getY()-pressedY<0)
							previousShape=new Rectangle(e.getX(),e.getY(),pressedX-e.getX(),pressedY-e.getY(),color);
						else if(e.getX()-pressedX<0&&e.getY()-pressedY>0)
							previousShape=new Rectangle(e.getX(),pressedY,pressedX-e.getX(),e.getY()-pressedY,color);
						else if(e.getX()-pressedX>0&&e.getY()-pressedY<0)
							previousShape=new Rectangle(pressedX,e.getY(),e.getX()-pressedX,pressedY-e.getY(),color);
						else
							previousShape=new Rectangle(pressedX,pressedY,e.getX()-pressedX,e.getY()-pressedY,color);
						add(previousShape);
						repaint();
						break;
					case OVAL:
						if(!newShapes)
							remove(previousShape);
						else
							newShapes=false;
						if(e.getX()-pressedX<0&&e.getY()-pressedY<0)
							previousShape=new Oval(e.getX(),e.getY(),pressedX-e.getX(),pressedY-e.getY(),color);
						else if(e.getX()-pressedX<0&&e.getY()-pressedY>0)
							previousShape=new Oval(e.getX(),pressedY,pressedX-e.getX(),e.getY()-pressedY,color);
						else if(e.getX()-pressedX>0&&e.getY()-pressedY<0)
							previousShape=new Oval(pressedX,e.getY(),e.getX()-pressedX,pressedY-e.getY(),color);
						else
							previousShape=new Oval(pressedX,pressedY,e.getX()-pressedX,e.getY()-pressedY,color);
						add(previousShape);
						repaint();
						break;
					default:
						break;
					}
				}
			}
		});
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
	}
	public void add(Shapes s){
		modified=true;
		ShapeList.add(s);
	}
	public void addText(String s,boolean end){
		if(currentMode!=Mode.SELECT){
			if(!end)
			{
				if(newShapes)
					remove(previousText);
				else
					newShapes=false;
				if(previousText!=null)
					previousText=new Text(previousText.getText()+s,pressedX,pressedY,color);
				else
					previousText=new Text(s,pressedX,pressedY,color);
				add(previousText);
				repaint();
			}
			else {
				previousText=null;
			}
		}
	}
	
	public void remove(Shapes s){
		ShapeList.remove(s);
	}
	
	public void setShape(Button s){
		shapes=s;
	}
	
	public void draw(Graphics g){
		for(Shapes s:ShapeList)
			s.draw(g);
	}
	
	public void setMode(Mode m){
		currentMode=m;
	}
	
	public void deleteOnCanvas(){
		remove(selectedShape);
		selectedShape=null;
		repaint();
	}
	
	public boolean isModified(){
		return modified;
	}
	
	public void setModified(boolean modified){
		this.modified=modified;
	}
	
	public boolean isEmpty(){
		return ShapeList.isEmpty();
	}
	
	public void makeEmpty(){
		ShapeList.clear();
		repaint();
	}
	
	public ArrayList<Shapes> getShapeList(){
		return ShapeList;
	}
	
	public void setColor(Color c){
		color=c;
	}
	
	private Shapes selecteShape(int x,int y){
		for(Shapes s:ShapeList){
			if(s.inside(x, y))
				return s;
		}
		return null;
	}
}
