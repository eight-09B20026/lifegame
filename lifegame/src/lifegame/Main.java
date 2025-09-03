package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class Main implements Runnable{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}
	
	@Override
	public void run() {
		int cols = 12;
		int rows = 12;
		
		JSpinner colsString = new JSpinner(new SpinnerNumberModel(12, 12, 22, 1));
		JSpinner rowsString = new JSpinner(new SpinnerNumberModel(12, 12, 22, 1));
		Object[] numbers = {
		    "horizontal:", colsString,
		    "vertical:", rowsString	
		};
		
		int option = JOptionPane.showConfirmDialog(null, numbers, "decide horizontal and vertical",JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			cols = ((Integer) colsString.getValue()).intValue();
			rows = ((Integer) rowsString.getValue()).intValue();
		}else {
			return;
		}
		

		BoardModel model = new BoardModel(cols, rows);
		
		JFrame frame = new JFrame("Lifegame");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel base = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton nextB = new JButton("next");
		JButton undoB = new JButton("undo");
		JButton gameB = new JButton("New Game");
		JButton autoB = new JButton("Auto");
		undoB.setEnabled(false);
		
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400, 300));
		frame.setMinimumSize(new Dimension(300, 200));
		base.setLayout(new BorderLayout());
		BoardView view = new BoardView(model);
		base.add(view, BorderLayout.CENTER);
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		new Buttons(model, view, undoB, nextB, gameB, autoB);
		
		buttonPanel.add(undoB);
		buttonPanel.add(nextB);
		buttonPanel.add(gameB);
		buttonPanel.add(autoB);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}


