/* @file FileManager.java
* @brief Responsible for saving and opening of Mini Canvas
* @author lucas95123@outlook.com
* @version 1.0
* @date 2015/11/18
*/
package frmMain;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class FileManager extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileWriter fw;
	private FileReader fr;
	private Scanner in;
	public FileManager(){
		
	}
	public File saveFile(CanvasPanel p,File f) throws IOException{
		if(!f.toString().endsWith(".drw"))
			f=new File(f.toString()+".drw");
		fw = new FileWriter(f);
		for(Shapes s:p.getShapeList()){
			fw.write(s.toString());
		}
		fw.close();
		return f;
	}
	public File openFile(CanvasPanel p,File f) throws FileNotFoundException{
		if(!f.toString().endsWith(".drw"))
			f=new File(f.toString()+".drw");
		fr=new FileReader(f);
		in = new Scanner(fr);
		while(true){
			try {
				String[] str=in.nextLine().split(" ");
				switch (str[0]) {
				case "Line":
					p.add(new Line(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),
							new Color(Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7]))));
					break;
				case "Rect":
					p.add(new Rectangle(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),
							new Color(Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]))));
					break;
				case "Oval":
					p.add(new Oval(Integer.parseInt(str[1]),Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),
							new Color(Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]))));
					break;
				case "Text":
					p.add(new Text(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]),
							new Color(Integer.parseInt(str[4]), Integer.parseInt(str[5]), Integer.parseInt(str[6]))));
					break;
				default:
					break;
				}
			} catch (Exception ex) {
				// TODO: handle exception
				break;
			}
		}
		p.repaint();
		return f;
	}
}
