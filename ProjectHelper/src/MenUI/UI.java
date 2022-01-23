package MenUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;


import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.ScriptManager;

import BotAI.FatigueManager;
import BotAI.FatigueStates;
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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class UI{

	private  JFrame frame = new JFrame();


	private JTextField textNPCName;
	private TaskManager<Task> manager = BotMain.Main.manager;
	public static JTextPane textLog = new JTextPane();
	private JRadioButton rdbtnAttachAttack;

	/**
	 * Launch the application.
	 */
	
	
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UI(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public UI() { }
	
	/**
	 * @wbp.parser.constructor
	 */
	public UI(boolean accept) { 
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(this.frame);
		getFrame().setVisible(true);
		getFrame().setBounds(100, 100, 535, 456);
		getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 499, 296);
		getFrame().getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main Tab", null, panel, null);
		panel.setLayout(null);
		textLog.setEditable(false);
		
		//////////////////////////////////
		
		
		/////////////////////////////////
		
		//MAIN PANEL
		
		textLog.setBounds(10, 11, 474, 147);
		panel.add(textLog);
		
		JLabel lblNewLabel = new JLabel("Fatigue Level");
		lblNewLabel.setBounds(118, 170, 71, 20);
		panel.add(lblNewLabel);
		
		JComboBox<FatigueStates> comboFatigueSelector = new JComboBox<>();
		comboFatigueSelector.setEditable(false);
		
		comboFatigueSelector.addItem(FatigueStates.DEFAULT);
		comboFatigueSelector.addItem(FatigueStates.ENGAGED);
		comboFatigueSelector.addItem(FatigueStates.RELAXED);
		comboFatigueSelector.addItem(FatigueStates.TIRED);
		
		comboFatigueSelector.setBounds(10, 169, 98, 22);
		panel.add(comboFatigueSelector);
		
		
		
		
		//ATTACK PANEL
		
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
		getFrame().getContentPane().add(btnPause);
		btnPause.setEnabled(true);
		btnPause.setForeground(Color.BLACK);
		btnPause.setBackground(Color.CYAN);
		
		//SET BUTTON
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(245, 369, 98, 37);
		getFrame().getContentPane().add(btnSet);
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSet.setForeground(Color.BLACK);
		btnSet.setBackground(Color.CYAN);
		
		//////////////////////////////
		
		//UPDATE BUTTON
		
		JButton btnUpdate = new JButton("Update and Resume");
		btnUpdate.setBounds(364, 369, 145, 37);
		getFrame().getContentPane().add(btnUpdate);
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FatigueManager.getInstance().setFatigueState((FatigueStates) comboFatigueSelector.getSelectedItem());
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
					
					manager.add(new BotDataJPX.Attack(textNPCName.getText().trim())); 
				}
			}
		});
		
		getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				getFrame().dispose();
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


