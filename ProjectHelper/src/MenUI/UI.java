package MenUI;

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
	private TaskManager<Task> taskManager = BotMain.Main.taskManager;
	public static JTextPane textLog = new JTextPane();
	private JRadioButton rdbtnAttachAttack;
	private JRadioButton rdbtnAttachDeforester;

	private JTextField textTreeName;

	/**
	 * Launch the application.
	 */
	

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
		
		rdbtnAttachAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAttachAttack.isSelected()) {
					taskManager.add(new BotDataJPX.Attack(textNPCName.getText().trim())); 
				}
			}
		});
		//////////////////////////////////////////////////////////
		
		//DEFORESTER PANEL
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Deforester", null, panel_2, null);
		panel_2.setLayout(null);
		
		textTreeName = new JTextField();
		textTreeName.setBounds(26, 28, 86, 20);
		panel_2.add(textTreeName);
		textTreeName.setColumns(10);
		
		rdbtnAttachDeforester = new JRadioButton("Attach Task {DEFORESTER}");
		rdbtnAttachDeforester.setBounds(311, 27, 161, 23);
		panel_2.add(rdbtnAttachDeforester);
		
		rdbtnAttachDeforester.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAttachDeforester.isSelected()) {
					taskManager.add(new BotDataJPX.Deforester(textTreeName.getText().trim())); 
				}
			}
			
		});
		
		JLabel lblNewLabel_1 = new JLabel("Tree Name");
		lblNewLabel_1.setBounds(122, 28, 74, 20);
		panel_2.add(lblNewLabel_1);
		
		
		///////////////////////////////////////////////////////////
		
		JButton btnPause = new JButton("Pause");

		//RESET BUTTON
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(204, 369, 98, 37);
		getFrame().getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskManager.clear();
				deselectRDBTN();
			}
		});
		btnReset.setForeground(Color.BLACK);
		btnReset.setBackground(Color.CYAN);
		
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
		
		btnPause.setBounds(31, 369, 98, 37);
		getFrame().getContentPane().add(btnPause);
		btnPause.setEnabled(true);
		btnPause.setForeground(Color.BLACK);
		btnPause.setBackground(Color.CYAN);
			
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ScriptManager.getScriptManager().pause();
					btnUpdate.setEnabled(true);
					btnPause.setEnabled(false);
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
	
	public void updateLog() {
		textLog.setText("");
		textLog.setText(taskManager.toString());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getInstance().getEnergy());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getInstance().getFatigueState());
	}
	
	public void deselectRDBTN() {
		rdbtnAttachAttack.setSelected(false);
		rdbtnAttachDeforester.setSelected(false);
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}


