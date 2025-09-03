package lifegame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Buttons extends JFrame {

  private BoardModel model;
  private BoardView view;
  private JButton undoB;
  private JButton autoB;
  private ButtonListener bl = new ButtonListener();
  private DoAuto doAuto;
  private Timer timer = new Timer(true);
  private boolean running;
  
  public Buttons(BoardModel model,BoardView view, JButton undoB, JButton nextB, JButton gameB, JButton autoB) {
	  this.model = model;
	  this.view = view;
	  this.undoB = undoB;
	  this.autoB = autoB;
	  undoB.addActionListener(bl);
	  nextB.addActionListener(bl);
	  gameB.addActionListener(bl);
	  autoB.addActionListener(bl);	  
	  this.running  = false;
	  }

  class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
		String name = ((JButton)e.getSource()).getText();
		if(name == "next") {
			model.next();
			undoB.setEnabled(true);
		}else if(name == "undo") {
			model.undo();
			if(model.isUndoable()==false) {
				undoB.setEnabled(false);
			}
		}else if(name == "New Game") {
			Main.main(null);
		}else if(name == "Auto") {
			if(running == false) {
				doAuto = new DoAuto();
				timer.schedule(doAuto, 500, 500);
				autoB.setBackground(Color.GREEN);
				running = true;
			}else {
				doAuto.cancel();
				doAuto = null;
				autoB.setBackground(null);
				running = false;
			}
		}
		view.repaint();
    }
  }
  
  class DoAuto extends TimerTask implements Runnable{
	  @Override
	  public void run() {
		  model.next();
		  undoB.setEnabled(true);
		  view.repaint();
	  }
  }
  
}