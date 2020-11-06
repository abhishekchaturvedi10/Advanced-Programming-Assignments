package practise;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class Patient{
	String name;
	int age;
	String dateOfReporting;
	
	Patient(String name,int age,String dateofreporting){
		this.name=name;
		this.age=age;
		this.dateOfReporting=dateofreporting;
	}
}

public class AP_A0 extends JFrame implements ItemListener{
			
	public static int recoveredAcount=0,recoveredBcount=0,recoveredCcount=0,recoveredDcount=0;
	
	public static int activeAcount=0,activeBcount=0,activeCcount=0,activeDcount=0;
	
	public static List<ArrayList<Patient>> patientsByTower = new ArrayList<ArrayList<Patient>>();
	
	public static List<ArrayList<Patient>> activeByTower = new ArrayList<ArrayList<Patient>>();
	
	public static List<ArrayList<Patient>> recoveredByTower = new ArrayList<ArrayList<Patient>>();
	
	public static JPanel panel = new JPanel(); 
	
	public static JTable table = new JTable(); 
	
	public static DefaultTableModel model = new DefaultTableModel(); 
	
	public static JScrollPane scrollPane = new JScrollPane(table);
	
	boolean towerAcheckedBoolean=false,towerBcheckedBoolean=false,towerCcheckedBoolean=false,towerDcheckedBoolean=false;
	
	public static JFrame mainFrame;  
	
	public static JFrame tableFrame;  
	
	public static String inputDate;
	
	JLabel inputDateLabel=new JLabel();
	JTextField enterDateTextField=new JTextField("ENTER A DATE (dd/mm/yyyy)");	
	
	JLabel getInputDateLabel=new JLabel();
	JTextField getInputTextField=new JTextField();
	
	JButton enterDateButton=new JButton("Enter");
	
	JTextField checkTowersTextField=new JTextField("SELECT THE TOWER(s)");
	
	JCheckBox towerAcheckBox;
	JCheckBox towerBcheckBox;
	JCheckBox towerCcheckBox;
	JCheckBox towerDcheckBox;
	
	JButton towerCheckedButton=new JButton("Done");
	
	JTextField recoveredCountATextField=new JTextField();
	JTextField recoveredCountBTextField=new JTextField();
	JTextField recoveredCountCTextField=new JTextField();
	JTextField recoveredCountDTextField=new JTextField();
	
	JTextField activeACountTextField=new JTextField();
	JTextField activeBCountTextField=new JTextField();
	JTextField activeCCountTextField=new JTextField();
	JTextField activeDCountTextField=new JTextField();
	
	JTextField totalActiveCasesFromSelectedTowersTextField=new JTextField();
	JTextField totalRecoveredCasesFromSelectedTowersTextField=new JTextField();
	
	JLabel extraLabel=new JLabel();
	
	int totalRecoveredCasesFromSelectedTowers=0,totalaActiveCasesFromSelectedTowers=0;
			
	public static void add(String name,int age,char tower,String date) {
		
		if(tower=='A') {
			patientsByTower.get(0).add(new Patient(name,age,date));
		}
		
		if(tower=='B') {
			patientsByTower.get(1).add(new Patient(name,age,date));
		}
		
		if(tower=='C') {
			patientsByTower.get(2).add(new Patient(name,age,date));
		}
		
		if(tower=='D') {
			patientsByTower.get(3).add(new Patient(name,age,date));
		}
	}
	
	void initialise() {
		
		for(int i=0;i<4;i++) activeByTower.add(new ArrayList<Patient>());
		for(int i=0;i<4;i++) recoveredByTower.add(new ArrayList<Patient>());
	}
	
	public static int daysBetween(String a,String b) {
		
		int val=0;
		
		val+=Integer.parseInt(a.substring(0,2))-Integer.parseInt(b.substring(0,2));
		val+=30*(Integer.parseInt(a.substring(3,5))-Integer.parseInt(b.substring(3,5)));
		val+=365*(Integer.parseInt(a.substring(6,10))-Integer.parseInt(b.substring(6,10)));
		
		return val;
	}
	
	void getRecoveredAndActivePatientsByTower(boolean a,boolean b,boolean c,boolean d) {
		
			for(int i=0;i<patientsByTower.get(0).size();i++) {
				
				String curdate=patientsByTower.get(0).get(i).dateOfReporting;
				
				int days = daysBetween(inputDate,curdate);
				
				if(days>=0&&days<22) {
					
					activeAcount++;
					
					String date=patientsByTower.get(0).get(i).dateOfReporting;
					String name=patientsByTower.get(0).get(i).name;
				 	int age=patientsByTower.get(0).get(i).age;
				 	activeByTower.get(0).add(new Patient(name,age,date));
				}
				else if(days>21) {
					
					recoveredAcount++;
					
					String date=patientsByTower.get(0).get(i).dateOfReporting;
					String name=patientsByTower.get(0).get(i).name;
				 	int age=patientsByTower.get(0).get(i).age;
				 	recoveredByTower.get(0).add(new Patient(name,age,date));
				}
			}
			
			recoveredCountATextField.setText("Recovered cases from tower A =  "+recoveredAcount);
			activeACountTextField.setText("Active cases from tower A =  "+activeAcount);

			for(int i=0;i<patientsByTower.get(1).size();i++) {
				
				String curdate=patientsByTower.get(1).get(i).dateOfReporting;
				
				int days = daysBetween(inputDate,curdate);
				
				if(days>=0&&days<22) {
					
					activeBcount++;
					
					String date=patientsByTower.get(1).get(i).dateOfReporting;
					String name=patientsByTower.get(1).get(i).name;
					int age=patientsByTower.get(1).get(i).age;
					activeByTower.get(1).add(new Patient(name,age,date));
				}
				else if(days>21) {
					
					recoveredBcount++;
					
					String date=patientsByTower.get(1).get(i).dateOfReporting;
					String name=patientsByTower.get(1).get(i).name;
				 	int age=patientsByTower.get(1).get(i).age;
				 	recoveredByTower.get(1).add(new Patient(name,age,date));
				}
			}
			
			recoveredCountBTextField.setText("Recovered cases from tower B =  "+recoveredBcount);
			activeBCountTextField.setText("Active cases from tower B =  "+activeBcount);
			
			for(int i=0;i<patientsByTower.get(2).size();i++) {
				
				String curdate=patientsByTower.get(2).get(i).dateOfReporting;
				
				int days = daysBetween(inputDate,curdate);
				
				if(days>=0&&days<22) {
					
					activeCcount++;
					
					String date=patientsByTower.get(2).get(i).dateOfReporting;
					String name=patientsByTower.get(2).get(i).name;
					int age=patientsByTower.get(2).get(i).age;
					activeByTower.get(2).add(new Patient(name,age,date));
				}
				else if(days>21) {
					
					recoveredCcount++;
					
					String date=patientsByTower.get(2).get(i).dateOfReporting;
					String name=patientsByTower.get(2).get(i).name;
				 	int age=patientsByTower.get(2).get(i).age;
				 	recoveredByTower.get(2).add(new Patient(name,age,date));
				}
			}
			
			recoveredCountCTextField.setText("Recovered cases from tower C =  "+recoveredCcount);
			activeCCountTextField.setText("Active cases from tower C =  "+activeCcount);

			for(int i=0;i<patientsByTower.get(3).size();i++) {
				
				String curdate=patientsByTower.get(3).get(i).dateOfReporting;
				
				int days = daysBetween(inputDate,curdate);
				
				if(days>=0&&days<22) {
					
					activeDcount++;
					
					String date=patientsByTower.get(3).get(i).dateOfReporting;
					String name=patientsByTower.get(3).get(i).name;
					int age=patientsByTower.get(3).get(i).age;
					activeByTower.get(3).add(new Patient(name,age,date));
				}
				else if(days>21) {
					
					recoveredDcount++;
					
					String date=patientsByTower.get(3).get(i).dateOfReporting;
					String name=patientsByTower.get(3).get(i).name;
				 	int age=patientsByTower.get(3).get(i).age;
				 	recoveredByTower.get(3).add(new Patient(name,age,date));
				}
			}
			
			recoveredCountDTextField.setText("Recovered cases from tower D =  "+recoveredDcount);
			activeDCountTextField.setText("Active cases from tower D =  "+activeDcount);
			
			if(a==true) {
				totalRecoveredCasesFromSelectedTowers+=recoveredAcount;
				totalaActiveCasesFromSelectedTowers+=activeAcount;
			}
			if(b==true) {
				totalRecoveredCasesFromSelectedTowers+=recoveredBcount;
				totalaActiveCasesFromSelectedTowers+=activeBcount;
			}
			if(c==true) {
				totalRecoveredCasesFromSelectedTowers+=recoveredCcount;
				totalaActiveCasesFromSelectedTowers+=activeCcount;
			}
			if(d==true) {
				totalRecoveredCasesFromSelectedTowers+=recoveredDcount;
				totalaActiveCasesFromSelectedTowers+=activeDcount;
			}
			
			totalActiveCasesFromSelectedTowersTextField.setText("Total active cases from selected towers = "+totalaActiveCasesFromSelectedTowers);
			totalRecoveredCasesFromSelectedTowersTextField.setText("Total recovered cases from selected towers = "+totalRecoveredCasesFromSelectedTowers);
	}
	
	public static void getTable(boolean towerA,boolean towerB,boolean towerC,boolean towerD) {
		
		String[] columnNames = new String[] {"NAME","AGE","TOWER","REPORTING DATE","RECOVERY DATE","STATUS"};
		
		model.setColumnIdentifiers(columnNames);
		
		String name,age,tower="z",reportingDate,recoveryDate;
		
		for(int i=0;i<4;i++) {
			
			if(i==0&&towerA==false) continue;
			if(i==1&&towerB==false) continue;
			if(i==2&&towerC==false) continue;
			if(i==3&&towerD==false) continue;
			
			if(i==0) tower="A";
			if(i==1) tower="B";
			if(i==2) tower="C";
			if(i==3) tower="D";
			
			for(int j=0;j<recoveredByTower.get(i).size();j++) {
				
				name=recoveredByTower.get(i).get(j).name;
				age=Integer.toString(recoveredByTower.get(i).get(j).age);
				reportingDate=recoveredByTower.get(i).get(j).dateOfReporting;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				
				try{
				   c.setTime(sdf.parse(reportingDate));
				}catch(ParseException e){
					e.printStackTrace();
				 }
				
				c.add(Calendar.DAY_OF_MONTH, 22); 
				String x = sdf.format(c.getTime()); 
				
				recoveryDate=x;
				
				model.addRow(new Object[]{name, age, tower,reportingDate,recoveryDate,"Recovered"});
			}
			
			for(int j=0;j<activeByTower.get(i).size();j++) {
				
				name=activeByTower.get(i).get(j).name;
				age=Integer.toString(activeByTower.get(i).get(j).age);
				reportingDate=activeByTower.get(i).get(j).dateOfReporting;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				
				try{
				   c.setTime(sdf.parse(reportingDate));
				}catch(ParseException e){
					e.printStackTrace();
				 }
				
				c.add(Calendar.DAY_OF_MONTH, 22); 
				String x = sdf.format(c.getTime()); 
				
				recoveryDate=x;
				
				model.addRow(new Object[]{name, age, tower,reportingDate,recoveryDate,"Active"});
			}
		}
		
		Font font = new Font("Arial Black", Font.BOLD, 12);
	    table.setFont(font);
	    table.setBackground(Color.yellow);
	    table.setForeground(Color.blue);
	    
	    JTableHeader tableHeader = table.getTableHeader();
	    tableHeader.setBackground(Color.black);
	    tableHeader.setForeground(Color.white);
	    
	    Font headerFont = new Font("Arial Black", Font.BOLD, 14);
	    tableHeader.setFont(headerFont);
	    
		table.setModel(model);
		
		tableFrame.add(new JScrollPane(table)); 
		tableFrame.setVisible(true);
	}
	
	public AP_A0() {
		
		mainFrame=new JFrame();
		mainFrame.setTitle("COVID TRACKER");
		mainFrame.setSize(1100,600);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tableFrame=new JFrame();
		tableFrame.setTitle("STATUS TABLE OF COVID TRACKER");
		tableFrame.setSize(1000,390);
		tableFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		enterDateButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				inputDate=(getInputTextField.getText());
				getInputDateLabel.setText(inputDate);		
			}
		});
		
		towerAcheckBox=new JCheckBox("TOWER A");
		towerBcheckBox=new JCheckBox("TOWER B");
		towerCcheckBox=new JCheckBox("TOWER C");
		towerDcheckBox=new JCheckBox("TOWER D");
		
		towerAcheckBox.addItemListener(this);
		towerBcheckBox.addItemListener(this);
		towerCcheckBox.addItemListener(this);
		towerDcheckBox.addItemListener(this);
		
		towerAcheckBox.setFont(towerAcheckBox.getFont().deriveFont(Font.BOLD, 14f));
		towerBcheckBox.setFont(towerBcheckBox.getFont().deriveFont(Font.BOLD, 14f));
		towerCcheckBox.setFont(towerCcheckBox.getFont().deriveFont(Font.BOLD, 14f));
		towerDcheckBox.setFont(towerDcheckBox.getFont().deriveFont(Font.BOLD, 14f));
		
		enterDateTextField.setFont(enterDateTextField.getFont().deriveFont(Font.BOLD, 14f));
		getInputTextField.setFont(getInputTextField.getFont().deriveFont(Font.BOLD, 14f));
		enterDateButton.setFont(enterDateButton.getFont().deriveFont(Font.BOLD, 14f));
		
		checkTowersTextField.setFont(checkTowersTextField.getFont().deriveFont(Font.BOLD, 14f));
		towerCheckedButton.setFont(towerCheckedButton.getFont().deriveFont(Font.BOLD, 14f));
	
		getInputTextField.setForeground(Color.blue);
		
		enterDateTextField.setBounds(50,30,210,25);
		getInputTextField.setBounds(50,60,85,20);
		enterDateButton.setBounds(50,90,100,20);
		checkTowersTextField.setBounds(50,150,165,20);
		
		towerAcheckBox.setBounds(50,190,170,20);
		towerBcheckBox.setBounds(50,220,170,20);
		towerCcheckBox.setBounds(50,250,170,20);
		towerDcheckBox.setBounds(50,280,170,20);
		
		towerCheckedButton.setBounds(50,330,100,20);
		
		recoveredCountATextField.setBounds(50,390,245,25);
		recoveredCountBTextField.setBounds(300,390,245,25);
		recoveredCountCTextField.setBounds(550,390,245,25);
		recoveredCountDTextField.setBounds(800,390,245,25);
		
		activeACountTextField.setBounds(50,420,215,25);
		activeBCountTextField.setBounds(300,420,215,25);
		activeCCountTextField.setBounds(550,420,215,25);
		activeDCountTextField.setBounds(800,420,215,25);
		
		totalRecoveredCasesFromSelectedTowersTextField.setBounds(50,500,336,25);
		totalActiveCasesFromSelectedTowersTextField.setBounds(450,500,305,25);
		
		recoveredCountATextField.setFont(recoveredCountATextField.getFont().deriveFont(Font.BOLD, 14f));
		recoveredCountBTextField.setFont(recoveredCountBTextField.getFont().deriveFont(Font.BOLD, 14f));
		recoveredCountCTextField.setFont(recoveredCountCTextField.getFont().deriveFont(Font.BOLD, 14f));
		recoveredCountDTextField.setFont(recoveredCountDTextField.getFont().deriveFont(Font.BOLD, 14f));
		
		activeACountTextField.setFont(activeACountTextField.getFont().deriveFont(Font.BOLD, 14f));
		activeBCountTextField.setFont(activeBCountTextField.getFont().deriveFont(Font.BOLD, 14f));
		activeCCountTextField.setFont(activeCCountTextField.getFont().deriveFont(Font.BOLD, 14f));
		activeDCountTextField.setFont(activeDCountTextField.getFont().deriveFont(Font.BOLD, 14f));
		
		activeACountTextField.setBackground(Color.red);
		activeBCountTextField.setBackground(Color.red);
		activeCCountTextField.setBackground(Color.red);
		activeDCountTextField.setBackground(Color.red);
		
		recoveredCountATextField.setBackground(Color.green);
		recoveredCountBTextField.setBackground(Color.green);
		recoveredCountCTextField.setBackground(Color.green);
		recoveredCountDTextField.setBackground(Color.green);
		
		totalActiveCasesFromSelectedTowersTextField.setFont(totalActiveCasesFromSelectedTowersTextField.getFont().deriveFont(Font.BOLD, 14f));
		totalRecoveredCasesFromSelectedTowersTextField.setFont(totalRecoveredCasesFromSelectedTowersTextField.getFont().deriveFont(Font.BOLD, 14f));
		
		totalActiveCasesFromSelectedTowersTextField.setBackground(Color.pink);
		totalRecoveredCasesFromSelectedTowersTextField.setBackground(Color.pink);
		
		mainFrame.add(enterDateTextField);
		mainFrame.add(getInputTextField);
		mainFrame.add(enterDateButton);
		mainFrame.add(checkTowersTextField);
		
		mainFrame.add(towerAcheckBox);
		mainFrame.add(towerBcheckBox);
		mainFrame.add(towerCcheckBox);
		mainFrame.add(towerDcheckBox);
		
		mainFrame.add(towerCheckedButton);
		
		towerCheckedButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(towerAcheckBox.isSelected()) {
					towerAcheckedBoolean=true;
				}
				else towerAcheckedBoolean=false;
				
				if(towerBcheckBox.isSelected()) {
					towerBcheckedBoolean=true;
				}
				else towerBcheckedBoolean=false;
				
				if(towerCcheckBox.isSelected()) {
					towerCcheckedBoolean=true;
				}
				else towerCcheckedBoolean=false;
				
				if(towerDcheckBox.isSelected()) {
					towerDcheckedBoolean=true;
				}
				else towerDcheckedBoolean=false;
				
				initialise();
				
				getRecoveredAndActivePatientsByTower(towerAcheckedBoolean,towerBcheckedBoolean,towerCcheckedBoolean,towerDcheckedBoolean);
				
				mainFrame.add(recoveredCountATextField);
				mainFrame.add(recoveredCountBTextField);
				mainFrame.add(recoveredCountCTextField);
				mainFrame.add(recoveredCountDTextField);
				
				mainFrame.add(activeACountTextField);
				mainFrame.add(activeBCountTextField);
				mainFrame.add(activeCCountTextField);
				mainFrame.add(activeDCountTextField); 
				
				mainFrame.add(totalActiveCasesFromSelectedTowersTextField);
				mainFrame.add(totalRecoveredCasesFromSelectedTowersTextField);
				
				getTable(towerAcheckedBoolean,towerBcheckedBoolean,towerCcheckedBoolean,towerDcheckedBoolean);
				
			}
		});
		
		mainFrame.add(extraLabel);
		
		mainFrame.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent ie) {
		
		if(towerAcheckBox.isSelected()) {
			towerAcheckedBoolean=true;
		}
		else towerAcheckedBoolean=false;
		
		if(towerBcheckBox.isSelected()) {
			towerBcheckedBoolean=true;
		}
		else towerBcheckedBoolean=false;
		
		if(towerCcheckBox.isSelected()) {
			towerCcheckedBoolean=true;
		}
		else towerCcheckedBoolean=false;
		
		if(towerDcheckBox.isSelected()) {
			towerDcheckedBoolean=true;
		}
		else towerDcheckedBoolean=false;
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<4;i++) patientsByTower.add(new ArrayList<Patient>());
		
		add("Flora",6,'A',"01/04/2020");
		add("Denys",24,'B',"01/04/2020");
		add("Jim",42,'C',"18/05/2020");
		add("Hazel",87,'D',"23/06/2020");
		add("Caery",72,'A',"01/06/2020");
		add("David",7,'B',"14/06/2020");
		add("Kevim",37,'D',"05/06/2020");
		add("Tom",67,'D',"20/06/2020");
		add("Bob",74,'A',"04/07/2020");
		add("Rachel",48,'C',"24/07/2020");
		add("Thomas",21,'C',"11/06/2020");
		add("Mary",17,'D',"21/06/2020");
		add("Smith",89,'A',"07/08/2020"); 	
		add("Pearson",47,'B',"04/06/2020");
		add("Anderson",62,'B',"27/07/2020");
		add("Johnson",10,'D',"01/08/2020");
		add("Robertz",50,'A',"09/08/2020");
		add("Julie",86,'B',"02/05/2020");
		add("Edith",42,'D',"07/06/2020");
		add("John",95,'D',"01/06/2020");
				
		new AP_A0();
	}
}