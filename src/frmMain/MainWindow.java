package frmMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

enum Button{
	LINE,RECTANGLE,OVAL,TEXT
}

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CanvasPanel pCanvas=new CanvasPanel();
	private FileManager fileManager=new FileManager();
	private JFileChooser fc;
	private JToggleButton btnDrawLine;
	private JToggleButton btnDrawRectangle;
	private JToggleButton btnDrawOval;
	private JToggleButton btnDrawText;
	private File currentOpenedFile;
	public MainWindow(){
		/*MenuBar*/
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		/*Menu*/
		JMenu menuFile=new JMenu("�ļ�");
		menuBar.add(menuFile);
		JMenu menuEdit=new JMenu("�༭");
		menuBar.add(menuEdit);
		
		/*Menu item of File*/
		JMenuItem menuItemNew=new JMenuItem("�½�",new ImageIcon("image/menu_new.gif"));
		menuItemNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				newFile();
			}
		});
		JMenuItem menuItemOpen=new JMenuItem("��",new ImageIcon("image/menu_open.gif"));
		menuItemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					openFile();
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(pCanvas, ex.toString(), "�޷������ļ�", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		JMenuItem menuItemSave=new JMenuItem("����",new ImageIcon("image/menu_save.gif"));
		menuItemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					saveFile();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(pCanvas, ex.toString(), "�޷������ļ�", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		JMenuItem menuItemSaveAs=new JMenuItem("���Ϊ",new ImageIcon("image/menu_saveas.gif"));
		menuItemSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					saveAsFile();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(pCanvas, ex.toString(), "�޷������ļ�", JOptionPane.CANCEL_OPTION);
				}
			}
		});
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemSaveAs);
		
		/*Menu item of Edit*/
		JMenuItem menuItemDraw=new JMenuItem("��ͼ",new ImageIcon("image/cursor_draw.gif"));
		menuItemDraw.setBackground(Color.WHITE);
		menuItemDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pCanvas.setMode(Mode.DRAW);
			}
		});
		JMenuItem menuItemSelect=new JMenuItem("ѡ��",new ImageIcon("image/cursor_selection.gif"));
		menuItemSelect.setBackground(Color.WHITE);
		menuItemSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCanvas.setMode(Mode.SELECT);
			}
		});
		menuEdit.add(menuItemDraw);
		menuEdit.add(menuItemSelect);	
		
		/*Panels*/
		JPanel pMain=new JPanel(new BorderLayout());/*The main panel*/
		JPanel pOption=new JPanel(new FlowLayout(FlowLayout.LEFT,2,0));/*The option panel*/
		pOption.setBackground(Color.LIGHT_GRAY);
		pCanvas.setBackground(Color.WHITE);
		pCanvas.setShape(Button.LINE);
		pCanvas.setMode(Mode.DRAW);
		add(pMain);
		
		/*Components of option panel*/
		/*Button of drawing Line*/
		btnDrawLine=new JToggleButton(new ImageIcon("image/shape_line.gif"));
		btnDrawLine.setBackground(new Color(250, 251, 252));
		btnDrawLine.setSelected(true);
		btnDrawLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCanvas.setShape(Button.LINE);
			}
		});
		btnDrawLine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==127)
					pCanvas.deleteOnCanvas();
			}
		});
		/*Button of drawing Rectangle*/
		btnDrawRectangle=new JToggleButton(new ImageIcon("image/shape_rectangle.gif"));
		btnDrawRectangle.setBackground(new Color(250, 251, 252));
		btnDrawRectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCanvas.setShape(Button.RECTANGLE);
			}
		});
		btnDrawRectangle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==127)
					pCanvas.deleteOnCanvas();
			}
		});
		/*Button of drawing Oval*/
		btnDrawOval=new JToggleButton(new ImageIcon("image/shape_oval.gif"));
		btnDrawOval.setBackground(new Color(250, 251, 252));
		btnDrawOval.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCanvas.setShape(Button.OVAL);
			}
		});
		btnDrawOval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==127)
					pCanvas.deleteOnCanvas();
			}
		});
		/*Button of drawing Text*/
		btnDrawText=new JToggleButton(new ImageIcon("image/shape_text.gif"));
		btnDrawText.setBackground(new Color(250, 251, 252));
		btnDrawText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCanvas.setShape(Button.TEXT);
			}
		});
		btnDrawText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
					pCanvas.addText(e.getKeyChar()+"",false);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==10)
					pCanvas.addText("",true);
				else if(e.getKeyCode()==127)
					pCanvas.deleteOnCanvas();
			}
		});
		
		/*Set the 4 button as button group*/
		ButtonGroup btnGroup=new ButtonGroup();
		
		btnGroup.add(btnDrawLine);
		btnGroup.add(btnDrawRectangle);
		btnGroup.add(btnDrawOval);
		btnGroup.add(btnDrawText);
		/*add the components into pOption Panel*/
		pOption.add(btnDrawLine);
		pOption.add(btnDrawRectangle);
		pOption.add(btnDrawOval);
		pOption.add(btnDrawText);
		
		/*Components of main panel*/
		pMain.add(pOption, BorderLayout.NORTH);
		pMain.add(pCanvas, BorderLayout.CENTER);
	}
	public void saveFile() throws IOException{
		pCanvas.setModified(false);
		if(currentOpenedFile!=null)
			fileManager.saveFile(pCanvas,currentOpenedFile);
		else{
			fc=new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("��ͼ�����ļ�(.drw)", "drw"));
			fc.showSaveDialog(this);
			currentOpenedFile=fileManager.saveFile(pCanvas, fc.getSelectedFile());
		}
	}
	public void openFile() throws FileNotFoundException{
		pCanvas.setModified(false);
		fc=new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("��ͼ�����ļ�(.drw)", "drw"));
		fc.showOpenDialog(this);
		currentOpenedFile=fileManager.openFile(pCanvas, fc.getSelectedFile());
	}
	public void saveAsFile() throws IOException{
		currentOpenedFile=null;
		saveFile();
	}
	public void newFile(){
		if(pCanvas.isModified()&&!pCanvas.isEmpty()){
			int returnVal=JOptionPane.showConfirmDialog(pCanvas, "���浱ǰͼ��", "�ļ�δ����", JOptionPane.YES_NO_CANCEL_OPTION); 
			if(returnVal==JOptionPane.YES_OPTION){
				try{
					saveFile();
					pCanvas.makeEmpty();
				}
				catch(Exception ex){
					JOptionPane.showConfirmDialog(pCanvas, ex.toString(), "�޷������ļ�", JOptionPane.CANCEL_OPTION);
				}					
			}
			else if(returnVal==JOptionPane.NO_OPTION){
				pCanvas.makeEmpty();
			}
			else;
		}
		else{
			pCanvas.makeEmpty();
		}
	}

	public static void main(String[] args){
		MainWindow mainWindow=new MainWindow();
		mainWindow.setTitle("Mini Canvas by Lu Kuan V1.0");
		mainWindow.setSize(1000, 700);
		mainWindow.setVisible(true);
	}
}
