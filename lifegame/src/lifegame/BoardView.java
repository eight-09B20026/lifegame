package lifegame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class BoardView extends JPanel implements MouseListener, MouseMotionListener{
	private int w;
	private int h;
	private int c;
	private int r;
	private int length;
	private int space;
	private int lastX;
	private int lastY;
	private BoardModel model;
	
	public BoardView(BoardModel model) {
		this.model = model;
		this.c = model.getCols();
		this.r = model.getRows();
		this.lastX = -1;
		this.lastY = -1;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		this.w = getWidth() - 1;
		this.h = getHeight() - 1;
		this.length = Math.min(this.w/this.c, this.h/this.r);
		this.space = (this.w - this.length*this.c)/2;
		for(int i=0; i<=this.c; i++) {
			g.drawLine(this.space + this.length*i, 0, this.space + this.length*i, this.length*this.r);
		}
		for(int i=0; i<=this.r; i++) {
			g.drawLine(this.space, this.length*i, this.space + this.length*this.c, this.length*i);
		}
		
		for(int i=0; i<this.r; i++) {
			for(int j=0; j<this.c; j++) {
				if(model.isAlive(i, j) == true) {
					g.fillRect(this.space + this.length*j, this.length*i, this.length, this.length);
				}
			}
		}
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		int x = (e.getX() - this.space);
		int y = e.getY() / this.length;
		if(x >= 0) {
			x = x / this.length;
		}
		if(0<=x && x<c && 0<=y && y<r) {
			model.changeCellState(y, x);
			this.lastX = x;
			this.lastY = y;
		}
		this.repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		int x = (e.getX() - this.space);
		int y = e.getY() / this.length;
		if(x >= 0) {
			x = x / this.length;
		}
		if(0<=x && x<c && 0<=y && y<r && (x != this.lastX || y != this.lastY)) {
			model.changeCellState(y, x);
			this.lastX = x;
			this.lastY = y;
		}
		this.repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
	}
}
