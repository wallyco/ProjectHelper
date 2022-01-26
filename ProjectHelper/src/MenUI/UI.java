package MenUI;

import javax.swing.JFrame;
import javax.swing.JTextField;


import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.ScriptManager;

import BotAI.FatigueManager;
import BotAI.LevelManager;
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


public class UI{

	private  JFrame frame = new JFrame();


	private JTextField textNPCName;
    static TaskManager<Task> taskManager = BotMain.Main.ai.getTaskManager();
	static LevelManager levelManager = BotMain.Main.ai.getLevelManager();
	public static JTextPane textLog = new JTextPane();
	private JRadioButton rdbtnAttachAttack;
	private JRadioButton rdbtnAttachDeforester;

	private JTextField textTreeName;
	private JTextField textSetAttackLevel;
	private JTextField textSetStrengthLevel;
	private JTextField textSetDefenceLevel;
	private JTextField textBankItem;
	private JTextField textSetEnergy;

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
		
		JPanel panelMain = new JPanel();
		tabbedPane.addTab("Main Tab", null, panelMain, null);
		panelMain.setLayout(null);
		textLog.setEditable(false);
		
		//////////////////////////////////
		
		
		/////////////////////////////////
		
		//MAIN PANEL
		
		textLog.setBounds(10, 11, 474, 198);
		panelMain.add(textLog);
		
		JLabel lblNewLabel = new JLabel("Fatigue Level");
		lblNewLabel.setBounds(118, 236, 71, 20);
		panelMain.add(lblNewLabel);
		
		textSetEnergy = new JTextField();
		textSetEnergy.setText("100");
		textSetEnergy.setBounds(25, 236, 86, 20);
		panelMain.add(textSetEnergy);
		textSetEnergy.setColumns(10);
		
			
		//ATTACK PANEL
		
		JPanel panelAttack = new JPanel();
		tabbedPane.addTab("Attack", null, panelAttack, null);
		panelAttack.setLayout(null);
		
		textNPCName = new JTextField();
		textNPCName.setBounds(10, 27, 114, 20);
		panelAttack.add(textNPCName);
		textNPCName.setColumns(10);
		
		JLabel lblNPCName = new JLabel("NPC Name");
		lblNPCName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		lblNPCName.setBounds(134, 30, 87, 14);
		panelAttack.add(lblNPCName);
		
		rdbtnAttachAttack = new JRadioButton("Attach Task {ATTACK}");
		rdbtnAttachAttack.setBounds(227, 26, 152, 23);
		panelAttack.add(rdbtnAttachAttack);
		
		textSetAttackLevel = new JTextField();
		textSetAttackLevel.setText("0");
		textSetAttackLevel.setBounds(10, 58, 45, 20);
		panelAttack.add(textSetAttackLevel);
		textSetAttackLevel.setColumns(10);
		
		textSetStrengthLevel = new JTextField();
		textSetStrengthLevel.setText("0");
		textSetStrengthLevel.setBounds(10, 89, 45, 20);
		panelAttack.add(textSetStrengthLevel);
		textSetStrengthLevel.setColumns(10);
		
		textSetDefenceLevel = new JTextField();
		textSetDefenceLevel.setText("0");
		textSetDefenceLevel.setBounds(10, 120, 45, 20);
		panelAttack.add(textSetDefenceLevel);
		textSetDefenceLevel.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Train Attack (Level)");
		lblNewLabel_2.setBounds(65, 61, 102, 14);
		panelAttack.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Train Strength (Level)");
		lblNewLabel_3.setBounds(65, 92, 114, 14);
		panelAttack.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Train Defence (Level)");
		lblNewLabel_4.setBounds(65, 123, 114, 14);
		panelAttack.add(lblNewLabel_4);
		
		rdbtnAttachAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAttachAttack.isSelected()) {
					taskManager.add(new BotDataJPX.Attack(textNPCName.getText().trim())); 
					levelManager.setLevelAttackTo(Integer.parseInt(textSetAttackLevel.getText().trim()));
					levelManager.setLevelStrengthTo(Integer.parseInt(textSetStrengthLevel.getText().trim()));
					levelManager.setLevelDefenceTo(Integer.parseInt(textSetDefenceLevel.getText().trim()));
				}
			}
		});
		//////////////////////////////////////////////////////////
		
		//DEFORESTER PANEL
		
		JPanel panelDeforester = new JPanel();
		tabbedPane.addTab("Deforester", null, panelDeforester, null);
		panelDeforester.setLayout(null);
		
		textTreeName = new JTextField();
		textTreeName.setBounds(26, 28, 86, 20);
		panelDeforester.add(textTreeName);
		textTreeName.setColumns(10);
		
		rdbtnAttachDeforester = new JRadioButton("Attach Task {DEFORESTER}");
		rdbtnAttachDeforester.setBounds(311, 27, 161, 23);
		panelDeforester.add(rdbtnAttachDeforester);
		
		rdbtnAttachDeforester.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAttachDeforester.isSelected()) {
					taskManager.add(new BotDataJPX.Deforester(textTreeName.getText().trim(), textBankItem.getText())); 
				}
			}
			
		});
		
		JLabel lblNewLabel_1 = new JLabel("Tree Name");
		lblNewLabel_1.setBounds(122, 28, 74, 20);
		panelDeforester.add(lblNewLabel_1);
		
		textBankItem = new JTextField();
		textBankItem.setBounds(26, 52, 86, 20);
		panelDeforester.add(textBankItem);
		textBankItem.setColumns(10);
		
		JPanel panelDev = new JPanel();
		tabbedPane.addTab("Dev Tab", null, panelDev, null);
		
		
		///////////////////////////////////////////////////////////
		
		JButton btnPause = new JButton("Pause");
		JButton btnUpdate = new JButton("Update and Resume");


		//RESET BUTTON
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(204, 369, 98, 37);
		getFrame().getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskManager.clear();
				deselectRDBTN();
				updateLog();
			}
		});
		btnReset.setForeground(Color.BLACK);
		btnReset.setBackground(Color.CYAN);
		
		//////////////////////////////
		
		//UPDATE BUTTON
		
		btnUpdate.setBounds(364, 369, 145, 37);
		getFrame().getContentPane().add(btnUpdate);
		btnUpdate.setBackground(Color.CYAN);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FatigueManager.getInstance().setEnergy(Double.parseDouble(textSetEnergy.getText()));
				ScriptManager.getScriptManager().resume();
				btnPause.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnReset.setEnabled(false);
			}
			
		});
		
		/////////////////////////////////////////////////
		
		//PAUSE BUTTON TODO Attach pause function from DBS client
		
		btnPause.setBounds(31, 369, 98, 37);
		getFrame().getContentPane().add(btnPause);
		btnPause.setEnabled(false);
		btnPause.setForeground(Color.BLACK);
		btnPause.setBackground(Color.CYAN);
			
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ScriptManager.getScriptManager().pause();
					btnUpdate.setEnabled(true);
					btnPause.setEnabled(false);
					btnReset.setEnabled(true);
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
		textLog.setText(taskManager.toString() + taskManager.size());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getInstance().getEnergy());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getInstance().getFatigueState());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getENERGY_LEVEL_RELAXED());
		textLog.setText(textLog.getText() + "\n" + FatigueManager.getENERGY_LEVEL_TIRED());

		textLog.setText(textLog.getText() + "\n" + levelManager);
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


