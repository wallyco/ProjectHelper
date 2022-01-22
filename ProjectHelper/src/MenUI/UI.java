package MenUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;


import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.ScriptManager;


import Task.Task;
import Task.TaskManager;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

public class UI{

	private  JFrame frame = new JFrame();
	private JTextField textNPCName;
	private TaskManager<Task> manager = BotMain.Main.manager;
	public static JTextPane textLog = new JTextPane();
	public JTextField textFatigueOffset;
	private JRadioButton rdbtnAttachAttack;

	/**
	 * Launch the application.
	 */
	
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					setFrame(window.frame);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 457, 456);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 421, 296);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main Tab", null, panel, null);
		panel.setLayout(null);
		textLog.setOpaque(false);
		textLog.setEditable(false);
		
		//////////////////////////////////
		
		
		/////////////////////////////////
		
		//LOG MENUU
		
		textLog.setBounds(10, 11, 360, 115);
		panel.add(textLog);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(377, 11, 29, 115);
		panel.add(scrollBar);
		
		textFatigueOffset = new JTextField("5000");
		textFatigueOffset.setBounds(10, 164, 47, 20);
		panel.add(textFatigueOffset);
		textFatigueOffset.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fatigue Offset (INT)");
		lblNewLabel.setBounds(67, 164, 106, 20);
		panel.add(lblNewLabel);
		
		
		
		
		//ATTACK TAB
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Attack", null, panel_1, null);
		panel_1.setLayout(null);
		
		textNPCName = new JTextField();
		textNPCName.setBounds(10, 27, 114, 20);
		panel_1.add(textNPCName);
		textNPCName.setColumns(10);
		
		JLabel lblNPCName = new JLabel("NPC Name");
		lblNPCName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblNPCName.setBounds(134, 30, 87, 14);
		panel_1.add(lblNPCName);
		
		rdbtnAttachAttack = new JRadioButton("Attach Task {ATTACK}");
		rdbtnAttachAttack.setBounds(227, 26, 152, 23);
		panel_1.add(rdbtnAttachAttack);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(31, 369, 98, 37);
		frame.getContentPane().add(btnPause);
		btnPause.setEnabled(true);
		btnPause.setForeground(Color.BLACK);
		btnPause.setBackground(Color.CYAN);
		
		//SET BUTTON
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(174, 369, 98, 37);
		frame.getContentPane().add(btnSet);
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSet.setForeground(Color.BLACK);
		btnSet.setBackground(Color.CYAN);
		
		//////////////////////////////
		
		//UPDATE BUTTON
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(302, 369, 98, 37);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptManager.getScriptManager().resume();
				btnPause.setEnabled(true);
				btnUpdate.setEnabled(false);	
			}
			
		});
		
		/////////////////////////////////////////////////
		
		//PAUSE BUTTON
			
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ScriptManager.getScriptManager().pause();
					manager.clear();
					deselectRDBTN();
					btnUpdate.setEnabled(true);
					btnPause.setEnabled(false);
			}		
		});
		rdbtnAttachAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAttachAttack.isSelected()
						&& textNPCName != null) {
					
					manager.add(new BotDataJPX.Attack(textNPCName.getText())); 
				}
			}
		});
		
		getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				kill();
				ScriptManager.getScriptManager().stop();
			}
		});
	}
	
	//////////////////////////////////

	public void kill() {
		MethodProvider.log("Killing");
		getFrame().dispose();
	}
	
	public void deselectRDBTN() {
		rdbtnAttachAttack.setSelected(false);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}


