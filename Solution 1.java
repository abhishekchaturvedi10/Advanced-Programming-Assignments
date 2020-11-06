package advancedProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ID{
	
	private int Id, recoveryDays;
	
	ID(int Id){
		
		this.Id = Id;
		this.recoveryDays = -1;
	}
	
	public int getID() {
		return this.Id;
	}
	
	public int getRecoveryDays() {
		return this.recoveryDays;
	}
	
	public void setID(int newID) {
		this.Id = newID;
	}
	
	public void setRecoveryDays(int newRecoveryDays) {
		this.recoveryDays = newRecoveryDays;
	}
}

class Patient{
	
	private int age, oxygenLevel;
	private String name, admissionStatus, admittedInstitute, status;
	private float temperature;
	private ID Id; 
	
	Patient(String name, float temperature, int oxygenLevel, int age, int ID){
		
		this.Id = new ID(ID); 
		this.name = name;
		this.temperature = temperature;
		this.oxygenLevel = oxygenLevel;
		this.age = age;	 
		this.admissionStatus = "NOT ADMITTED";
		this.admittedInstitute = null;
		this.status = "ACTIVE";
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getID() {
		return this.Id.getID();
	}
	
	public float getTemperature() {
		return this.temperature;
	}
	
	public int getOxygenLevel() {
		return this.oxygenLevel;
	}
	
	public String getAdmissionStatus() {
		return this.admissionStatus;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getRecoveryDays() {
		return this.Id.getRecoveryDays();
	}
	
	public void setAdmissionStatus(String AdmissionStatus){
		this.admissionStatus = AdmissionStatus;
	}
	
	public void setAdmittedInstitute(String AdmittedInstitute) {
		this.admittedInstitute = AdmittedInstitute;
	}
	
	public void setRecoveryDaysCount(int newRecoveryDays) {
		this.Id.setRecoveryDays(newRecoveryDays);
	}
	
	public void deleteithAdmittedPatients() {
		
		if(this.status == "ACTIVE" && this.admissionStatus == "ADMITTED") {
				
			System.out.println(this.getID());
			this.status = "DELETED"; 
		}
	}
	
	public void displayithPatientsbyID(int Id) {
		
		if(this.getID() == Id && this.status == "ACTIVE") {
				
			System.out.println("Name is " + this.name);
			System.out.println("Age is " + this.age);
			System.out.println("Temeperature is " + this.temperature);
			System.out.println("Oxygen level is " + this.oxygenLevel);
			System.out.println("Admission status - " + this.admissionStatus);
				
			if(this.admissionStatus == "ADMITTED") {
					
				System.out.println("Admitted institute - " + admittedInstitute);
			}
		}
	}
	
	public void displayithPatient() {
		
		if(this.status == "ACTIVE") {
				
			System.out.println(this.getID() + "  " + this.name);
		}
	}
	
	public boolean checkithPatientIfAdmitted() {
		
		if(this.admissionStatus == "NOT ADMITTED") {
			return 	false; 
		}
		
		return true;
	}
}

class Hospital{
	
	private float maxTemperatureLevel;
	private int minOxygenLevel, availableBedsCount;
	private String name, applicationStatus, status;
	private ArrayList<Patient> admittedPatients = new ArrayList<Patient>();
	
	Hospital(String name, float maxTemperatureLevel, int minOxygenLevel, int availableBedsCount){
		
		this.name = name;
		this.maxTemperatureLevel = maxTemperatureLevel;
		this.minOxygenLevel = minOxygenLevel;
		this.availableBedsCount = availableBedsCount;
		this.applicationStatus = "OPEN";
		this.status = "ACTIVE";
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setApplicationStatus() {
		if(this.availableBedsCount<=0) {
			this.applicationStatus = "CLOSED";
		}
		else {
			this.applicationStatus = "OPEN";
		}
	}
	
	public int getAdmittedPatientsCount() {
		return this.admittedPatients.size();
	}
	
	public Patient getithAdmittedPatient(int i) {
		return this.admittedPatients.get(i);
	}
	
	public void admitithPatientByOxygenLevel(Patient patient) {
		
		this.setApplicationStatus();
		
		if(this.applicationStatus == "OPEN" && patient.getAdmissionStatus() == "NOT ADMITTED") {
				
			if(patient.getOxygenLevel() >= this.minOxygenLevel) {
					
				this.availableBedsCount--;
				this.setApplicationStatus();
				patient.setAdmissionStatus("ADMITTED");
				patient.setAdmittedInstitute(this.name);
				this.admittedPatients.add(patient);	
			}
		}
	}
	
	public void admitithPatientByTemperature(Patient patient) {
		
		this.setApplicationStatus();
		
		if(this.applicationStatus == "OPEN" && patient.getAdmissionStatus() == "NOT ADMITTED") {
			
			if(patient.getTemperature() <= this.maxTemperatureLevel) {
					
				this.availableBedsCount--;
				this.setApplicationStatus();
				patient.setAdmissionStatus("ADMITTED");
				patient.setAdmittedInstitute(this.name);
				this.admittedPatients.add(patient);
			}
		}
	}

	public void setithAdmittedPatientRecoveryDays(int i, int days) {
		
		this.admittedPatients.get(i).setRecoveryDaysCount(days);
	}
	
	public int getithAdmittedPatientID(int i) {
		return this.admittedPatients.get(i).getID();
	}
	
	public void deleteithHospital() {
		
		if(this.status == "ACTIVE" && this.applicationStatus == "CLOSED") {
				
			this.status = "DELETED";
			System.out.println(this.name);
		}
	}
	
	public boolean checkithOpenHospital() {
		
		if(this.applicationStatus == "OPEN") {
				return true;
		}
		
		return false;
	}
	
	public void displayithHospitalDetailsbyName() {
		
		if(this.status == "ACTIVE") {
			
			System.out.println(this.name);
			System.out.println("Temperature should be <= " + this.maxTemperatureLevel);
			System.out.println("Oxygen level should be >= " + this.minOxygenLevel);
			System.out.println("Admission status - " + this.applicationStatus);
			System.out.println("Number of available beds = " + this.availableBedsCount);	
		}
	}
	
	public void displayAdmittedPatientsByHospitalName(String Name) {
		
		int count = 0; 
		
		if(this.name.equals(Name) && this.status == "ACTIVE") {
				
			if(this.getAdmittedPatientsCount() > 0) {
					
				for(int j=0; j < this.getAdmittedPatientsCount(); j++) {
							
					System.out.println(this.getithAdmittedPatient(j).getName() + ", recovery time is " + this.getithAdmittedPatient(j).getRecoveryDays() + " days");
					count++;
				}
			}
		}
		
		if(count == 0) {
			
			System.out.println("No active admitted patients");
		}
	}
}

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the number of patients and their details");
		
		System.out.println();
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<Patient> Patients = new ArrayList<Patient>();
		
		ArrayList<Hospital> Hospitals = new ArrayList<Hospital>();
		
		for(int i=1; i<=n; i++) {
			
			String str = br.readLine();
			StringTokenizer st=new StringTokenizer(str, " ");
			
			String name = st.nextToken();
			float temperature = Float.parseFloat(st.nextToken());
			int oxygenLevel = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			Patients.add(new Patient(name, temperature, oxygenLevel, age, i));
		}
		
		boolean checkAllAdmitted = true;
		
		for(int i=0; i < n; i++) {
			
			checkAllAdmitted = checkAllAdmitted && Patients.get(i).checkithPatientIfAdmitted();
		}
		
		while(checkAllAdmitted == false) {
			
			System.out.println();
			
			System.out.println("Enter query number ");
			int query = Integer.parseInt(br.readLine());
			
			System.out.println();
			
			if(query == 1) {
				
				System.out.println("Enter name of the hospital");
				String name = br.readLine();
				
				System.out.println("Enter temperature criteria of the hospital");
				float maxTemperature = Float.parseFloat(br.readLine());
				
				System.out.println("Enter oxygen levels of the hospital");
				int minOxygenLevel = Integer.parseInt(br.readLine());
				
				System.out.println("Enter number of beds available in the hospital");
				int availableBedsCount = Integer.parseInt(br.readLine());
				
				Hospital hospital = new Hospital(name, maxTemperature, minOxygenLevel, availableBedsCount);
				
				System.out.println();
				
				hospital.displayithHospitalDetailsbyName();
				
				for(int i=0; i < Patients.size(); i++) {
					
					hospital.admitithPatientByOxygenLevel(Patients.get(i));
				}
				
				for(int i=0; i < Patients.size(); i++) {
					
					hospital.admitithPatientByTemperature(Patients.get(i));
				}
				
				Hospitals.add(hospital);
				
				if(hospital.getAdmittedPatientsCount()>0) {
					
					System.out.println();
					
					for(int i=0; i < hospital.getAdmittedPatientsCount(); i++) {
						
						System.out.println("Enter recovery days for admitted patient ID " + hospital.getithAdmittedPatientID(i));
						
						int recoveryDays = Integer.parseInt(br.readLine());
						
						hospital.setithAdmittedPatientRecoveryDays(i, recoveryDays);
					}
				}
			}
			else if(query == 2) {
				
				System.out.println("Account ID of the removed patients");
				
				System.out.println();
				
				for(int i=0; i < n; i++) {
					
					Patients.get(i).deleteithAdmittedPatients();
				}
			}
			else if(query == 3) {
				
				System.out.println("Accounts removed of Institute whose admission is closed");
				
				System.out.println();
				
				for(int i=0; i < Hospitals.size(); i++) {
					
					Hospitals.get(i).deleteithHospital();
				}
			}
			else if(query == 4) {
				
				int patientsNotAdmittedcount = 0;
				
				for(int i=0; i < n; i++) {
					
					if(Patients.get(i).checkithPatientIfAdmitted() == false) {
						patientsNotAdmittedcount++;
					}
				}
				
				System.out.println(patientsNotAdmittedcount + " patients");
			}
			else if(query == 5) {
				
				int openHospitalsCount = 0;
				
				for(int i=0; i < Hospitals.size(); i++) {
					
					if(Hospitals.get(i).checkithOpenHospital() == true) {
						openHospitalsCount++;
					}
				}
				
				System.out.println(openHospitalsCount + " institutes are admitting patients currently");
			}
			else if(query == 6) {
				
				System.out.println("Enter the name of hospital");
				
				String hospitalName = br.readLine();
				
				System.out.println();
												
				for(int i=0; i < Hospitals.size(); i++) {

					if(hospitalName.equals(Hospitals.get(i).getName())) {
						Hospitals.get(i).displayithHospitalDetailsbyName();
					}
				}
				
			}
			else if(query == 7) {
				
				System.out.println("Enter the ID of the patient in range (1 - " + n + ")");
				
				int Id = Integer.parseInt(br.readLine());
				
				System.out.println();
				
				for(int i=0; i < n; i++) {
					
					Patients.get(i).displayithPatientsbyID(Id);
				}	
			}
			else if(query == 8) {
				
				for(int i=0; i < n; i++) {
					
					Patients.get(i).displayithPatient();
				}
			}
			else if(query == 9) {
				
				System.out.println("Enter the name of hospital");
				
				String hospitalName = br.readLine();
				
				System.out.println();
				
				for(int i=0; i < Hospitals.size(); i++) {
					
					Hospitals.get(i).displayAdmittedPatientsByHospitalName(hospitalName);
				}
			}
			else {
				
				System.out.println("Invalid query number!!! Enter again");
				System.out.println();
				query = Integer.parseInt(br.readLine());
			}
			
			checkAllAdmitted = true;
			
			for(int i=0; i < n; i++) {
				
				checkAllAdmitted = checkAllAdmitted && Patients.get(i).checkithPatientIfAdmitted();
			}
			
			if(checkAllAdmitted == true) {
				
				System.out.println();
				System.out.println("All the patients have been admitted!");
				System.out.println("Exiting with code 0"); 
				System.exit(0);
			}
		}
	}
}