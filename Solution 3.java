package advancedProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class GenericList <T> {
	
	private ArrayList <T> list;
	
	public GenericList() {
		this.list = new ArrayList <T>();
	}
	
	public void add(T i) {
		this.list.add(i);
	}
	
	public T get(int i) {
		return this.list.get(i);
	}
	
	public ArrayList <T> getList(){
		return this.list;
	}
}

abstract class Character {

	protected String status;
	protected float HP;

	Character() {
		this.status = "Alive";
	}

	public String getStatus() {
		return this.status;
	}

	public float getHP() {
		return this.HP;
	}

	public abstract void addHP(float HP);

	public abstract void subHP(float HP);

	public void setStatus(String status) {
		if (status.equals("Dead")) {
			this.HP = 0;
		}

		this.status = status;
	}
}

class Mafia extends Character {

	Mafia() {
		super();
		this.HP = 2500;
	}

	@Override
	public void addHP(float HP) {
		this.HP += HP;
	}

	@Override
	public void subHP(float HP) {
		this.HP -= HP;
	}
}

class Detective extends Character  {

	Detective() {
		super();
		this.HP = 800;
	}

	@Override
	public void addHP(float HP) {
		this.HP += HP;
		if (this.status.equals("Dead") && this.HP > 0) {
			this.status = "Alive";
		}
	}

	@Override
	public void subHP(float HP) {
		this.HP = Math.max(0, this.HP - HP);
		if (this.HP == 0) {
			this.status = "Dead";
		}
	}
}

class Healer extends Character {

	Healer() {
		super();
		this.HP = 800;
	}

	@Override
	public void addHP(float HP) {
		this.HP += HP;
		if (this.status.equals("Dead") && this.HP > 0) {
			this.status = "Alive";
		}
	}

	@Override
	public void subHP(float HP) {
		this.HP = Math.max(0, this.HP - HP);
		if (this.HP == 0) {
			this.status = "Dead";
		}
	}
}

class Commoner extends Character {

	Commoner() {
		super();
		this.HP = 1000;
	}

	@Override
	public void addHP(float HP) {
		this.HP += HP;
		if (this.HP > 0) {
			this.status = "Alive";
		}
	}

	@Override
	public void subHP(float HP) {
		this.HP = Math.max(0, this.HP - HP);
		if (this.HP == 0) {
			this.status = "Dead";
		}
	}
}

class MafiaGame {

	private int N, userID;
	GenericList<Character> characters = new GenericList<>();

	public void run() throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to Mafia");
		System.out.println("Enter Number of players:");
		this.N = Integer.parseInt(reader.readLine());

		while (this.N<6) {
			System.out.println("Number of players cannot be less than 6. Enter Number of players:");
			this.N = Integer.parseInt(reader.readLine());
		}
				
		for (int i = 0; i < N / 5; i++) {
			Character m = new Mafia();
			characters.add(m);
		}

		for (int i = N / 5; i < 2 * (N / 5); i++) {
			Character d = new Detective();
			characters.add(d);
		}

		for (int i = 2 * (N / 5); i < 2 * (N / 5) + Math.max(1, N / 10); i++) {
			Character h = new Healer();
			characters.add(h);
		}

		for (int i = 2 * (N / 5) + Math.max(1, N / 10); i < N; i++) {
			Character c = new Commoner();
			characters.add(c);
		}
		
		Collections.shuffle(characters.getList());

		System.out.println("Choose a Character");
		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) Commoner");
		System.out.println("5) Assign Randomly");
		int x = Integer.parseInt(reader.readLine());
		
		while (x < 1 || x > 5) {
			System.out.println("Incorrect Input! Choose a Character");
			x = Integer.parseInt(reader.readLine());
		}
		
		this.userID = (int) (Math.random() *this.N);
		
		if (x == 1) {
			while(!(characters.get(userID) instanceof Mafia)) {
				this.userID = (int) (Math.random() *this.N);
			}
		} else if (x == 2) {
			while(!(characters.get(userID) instanceof Detective)) {
				this.userID = (int) (Math.random() *this.N);
			}
		} else if (x == 3) {
			while(!(characters.get(userID) instanceof Healer)) {
				this.userID = (int) (Math.random() *this.N);
			}
		} else if (x == 4) {
			while(!(characters.get(userID) instanceof Commoner)) {
				this.userID = (int) (Math.random() *this.N);
			}
		} else {
			x = 1 + (int)(Math.random() * 4);

			if (x == 1) {
				while(!(characters.get(userID) instanceof Mafia)) {
					this.userID = (int) (Math.random() *this.N);
				}
			} else if (x == 2) {
				while(!(characters.get(userID) instanceof Detective)) {
					this.userID = (int) (Math.random() *this.N);
				}
			} else if (x == 3) {
				while(!(characters.get(userID) instanceof Healer)) {
					this.userID = (int) (Math.random() *this.N);
				}
			} else if (x == 4) {
				while(!(characters.get(userID) instanceof Commoner)) {
					this.userID = (int) (Math.random() *this.N);
				}
			}
		}

		int pid = userID+1;
		System.out.println("You are Player" + pid);

		if (x == 1) {
			System.out.println("You are a Mafia. Other Mafias are: ");
			printPlayersByClassType(characters.getList(), (Object)(new Mafia()));
		}

		if (x == 2) {
			System.out.println("You are a Detective. Other Detectives are: ");
			printPlayersByClassType(characters.getList(), (Object)(new Detective()));
		}

		if (x == 3) {
			System.out.println("You are a Healer. Other Healers are: ");
			printPlayersByClassType(characters.getList(), (Object)(new Healer()));
		}

		if (x == 4) {
			System.out.println("You are a Commoner. Other Commoners are: ");
			printPlayersByClassType(characters.getList(), (Object)(new Commoner()));
		}
		
		int roundNum = 0;
		
		while(this.isGameOver().equals("continue")) {
			this.round(++roundNum);
		}
		
		if (!this.isGameOver().equals("continue")) {
			gameOver();
		}
	}
	
	public void gameOver() {
		
		System.out.println();
		System.out.println("GAME OVER");
		System.out.println();

		if (isGameOver().equals("MafiasWin"))
			System.out.println("The Mafias have won.");

		if (isGameOver().equals("MafiasLose"))
			System.out.println("The Mafias have lost.");
		
		System.out.println();

		printPlayers(characters.getList());
		
		System.out.println("Thanks for playing!");
		System.exit(0);
	}

	public String isGameOver() {

		int MafiasAlive = 0;
		int nonMafiasAlive = 0;

		for (int i = 0; i < N; i++) {
			if (characters.get(i).getStatus().equals("Alive") && characters.get(i) instanceof Mafia)
				MafiasAlive++;
		}

		for (int i = 0; i < N; i++) {
			if (characters.get(i).getStatus().equals("Alive") && !(characters.get(i) instanceof Mafia))
				nonMafiasAlive++;
		}

		if (MafiasAlive == 0)
			return "MafiasLose";

		if (MafiasAlive >= nonMafiasAlive)
			return "MafiasWin";

		return "continue";
	}
	
	public int voteOut(int userVote) {
		
		int vote=-1;
		int [] votesCount = new int[N];
		
		while(vote==-1) {
				
			for(int i=0;i<N;i++) {
				votesCount[i]=0;
			}
				
			if(userVote!=-1) {
				votesCount[userVote]++;
			}
				
			for(int i=0;i<N;i++) {
				if(characters.get(i).getStatus().equals("Alive") && i!=userID) {
					int v=-1;;
					while(v<0 || characters.get(v).getStatus().equals("Dead")) {
						v=(int)(Math.random()*this.N);
					}
					votesCount[v]++;
				}
			}
				
			int maxVotes=0;
				
			for(int i=0;i<N;i++) {
				maxVotes=Math.max(maxVotes, votesCount[i]);
			}
				
			int cnt=0;
				
			for(int i=0;i<N;i++) {
				if(maxVotes==votesCount[i]) cnt++;
			}
				
			if(cnt>1) {
				vote=-1;
			}
			else {
				for(int i=0;i<N;i++) {
					if(maxVotes==votesCount[i]) vote=i;
				}
			}
		}

		return vote;
	}

	public int voting(Object obj) {
		
		int vote=-1;
		int [] votesCount = new int[N];
		
		while(vote==-1) {
				
			for(int i=0;i<N;i++) {
				votesCount[i]=0;
			}
				
			for(int i=0;i<N;i++) {
				if(characters.get(i).getStatus().equals("Alive") && obj.getClass()==characters.get(i).getClass()) {
					int v=-1;
					while(v<0 || characters.get(v).getStatus().equals("Dead") || (characters.get(i) instanceof Mafia && characters.get(v) instanceof Mafia) || (characters.get(i) instanceof Detective && characters.get(v) instanceof Detective)) {
						v=(int)(Math.random()*this.N);
					}
					votesCount[v]++;
				}
			}
				
			int maxVotes=0;
				
			for(int i=0;i<N;i++) {
				maxVotes=Math.max(maxVotes, votesCount[i]);
			}
				
			int cnt=0;
				
			for(int i=0;i<N;i++) {
				if(maxVotes==votesCount[i]) cnt++;
			}
				
			if(cnt>1) {
				vote=-1;
			}
			else {
				for(int i=0;i<N;i++) {
					if(maxVotes==votesCount[i]) vote=i;
				}
			}
		}

		return vote;
	}
	
	public void printPlayers(ArrayList<? extends Character> characters) {
		
		for (int j = 0; j < N; j++) {
			
			int pno = j+1;
			
			if (characters.get(j) instanceof Mafia) {
				System.out.print("Player" + pno + "  ");
			}
		}
		
		System.out.print("---> Mafia");
		System.out.println();

		for (int j = 0; j < N; j++) {
			
			int pno = j+1;
			
			if (characters.get(j) instanceof Detective) {
				System.out.print("Player" + pno + "  ");
			}
		}
		
		System.out.print("---> Detective");
		System.out.println();

		for (int j = 0; j < N; j++) {
			
			int pno = j+1;
			
			if (characters.get(j) instanceof Healer) {
				System.out.print("Player" + pno + "  ");
			}
		}
		
		System.out.print("---> Healer");
		System.out.println();

		for (int j = 0; j < N; j++) {
			
			int pno = j+1;
			
			if (characters.get(j) instanceof Commoner) {
				System.out.print("Player" + pno + "  ");
			}
		}
		
		System.out.print("---> Commoner");
		System.out.println();
		System.out.println();
	}
	
	void printPlayersByClassType(ArrayList<? extends Character> characters, Object obj) {
		
		System.out.print("[ ");
		for (int i = 0; i < N; i++) {
			if (userID == i || characters.get(i).getClass()!=obj.getClass()) {
				continue;
			}
			else {
				int p =i+1;
				System.out.print("Player" + p + " ");
			}
		}
		System.out.print(" ]");
	}
	
	public void round(int i) throws Exception, Exception {

		int target = -1, test = -1, heal = -1, vote = -1;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println();
		System.out.println();
		System.out.println("Round " + i + ":");
		System.out.println();

		int x = 0;

		for (int j = 0; j < N; j++) {
			if (characters.get(j).getStatus().equals("Alive")) {
				x++;
			}
		}

		System.out.println(x + " players are remaining: ");
		for (int j = 0; j < N; j++) {
			if (characters.get(j).getStatus().equals("Alive")) {
				int p = j+1;
				System.out.print("Player" + p + " ");
			}
		}
		System.out.println("are alive.");
		System.out.println();
		
		
		if (characters.get(userID) instanceof Mafia && characters.get(userID).getStatus().equals("Alive")) {

			System.out.println("Choose a target:");
			target = Integer.parseInt(reader.readLine());

			while (target < 1 || target > N || characters.get(target-1).getStatus().equals("Dead") || characters.get(target-1) instanceof Mafia) {
				System.out.println("Cannot target a mafia or a dead player or invalid input. Choose a target");
				target = Integer.parseInt(reader.readLine());
			}
			
			target--;
			
		} else {
			target = voting(new Mafia());
			System.out.println("Mafias have chosen a target");
		}

		System.out.println();
		
		int detectiveCnt = 0;
		
		for(int j=0; j<N; j++) {
			if(characters.get(j) instanceof Detective && characters.get(j).getStatus().equals("Alive")) {
				detectiveCnt++;
			}
		}

		if (detectiveCnt>0 && characters.get(userID) instanceof Detective && characters.get(userID).getStatus().equals("Alive")) {

			System.out.println("Choose a player to test: ");
			test = Integer.parseInt(reader.readLine());

			while (test < 1 || test > N || !characters.get(test-1).getStatus().equals("Alive") || characters.get(test-1) instanceof Detective) {
				System.out.println("You cannot test a detective or a dead player or invalid input! Choose a player to test: ");
				test = Integer.parseInt(reader.readLine());
			}
			
			test--;
			int t = test+1;

			if (!(characters.get(test) instanceof Mafia)) {
				System.out.println("Player" + t + " is not a mafia");
			} else {
				System.out.println("Player" + t + " is a mafia");
			}			
		} else if (detectiveCnt>0 && (!(characters.get(userID) instanceof Detective) || !characters.get(userID).getStatus().equals("Alive"))) {
			
			test = voting(new Detective()); 
			System.out.println("Detectives have chosen a player to test.");
		
		}else {
			test=-1;
			System.out.println("Detectives have chosen a player to test.");
		}
		
		System.out.println();
		
		int healerCnt = 0;
		
		for(int j=0; j<N; j++) {
			if(characters.get(j) instanceof Healer && characters.get(j).getStatus().equals("Alive")) {
				healerCnt++;
			}
		}

		if (healerCnt>0 && characters.get(userID) instanceof Healer && (characters.get(userID).getStatus().equals("Alive"))) {

			System.out.println("Choose a player to heal: ");
			heal = Integer.parseInt(reader.readLine());
			
			while (heal < 1 || heal > N || !characters.get(heal-1).getStatus().equals("Alive")) {
				System.out.println("You cannot heal a dead player or invalid input! Choose a player to heal: ");
				heal = Integer.parseInt(reader.readLine());
			}
			heal--;
			
		} else if (healerCnt>0 && (!(characters.get(userID) instanceof Healer) || !characters.get(userID).getStatus().equals("Alive"))) {

			heal = voting(new Healer());

			System.out.println("Healers have chosen someone to heal.");
			
		}else {
			heal=-1;
			System.out.println("Healers have chosen someone to heal.");
		}
		
		System.out.println();
		System.out.println("--End of actions--");
		System.out.println();
				
		float X = 0;
		int Y = 0;

		for (int k = 0; k < N; k++) {
			if (characters.get(k).getHP() > 0 && characters.get(k).getStatus().equals("Alive") && characters.get(k) instanceof Mafia) {
				Y++;
				X += characters.get(k).getHP();
			}
		}
		
		float damage = characters.get(target).getHP() / Y;
		characters.get(target).subHP(X);
		float leftOverDamage = 0;

		for (int k = 0; k < N; k++) {
			if (characters.get(k).getHP() > 0 && characters.get(k).getStatus().equals("Alive") && characters.get(k) instanceof Mafia) {
				characters.get(k).subHP(damage);
				leftOverDamage += Math.max(0, damage - characters.get(k).getHP());
			}
		}

		boolean z = false;

		for (int k = 0; k < N; k++) {
			if (characters.get(k).getHP() > 0 && characters.get(k) instanceof Mafia && characters.get(k).getStatus().equals("Alive")) {
				z = true;
				break;
			}
		}

		while (leftOverDamage > 0 && !z) {

			float newLeftOverDamage = 0;

			for (int k = 0; k < N; k++) {
				if (characters.get(k).getHP() > 0 && characters.get(k).getStatus().equals("Alive") && characters.get(k) instanceof Mafia) {
					characters.get(k).subHP(leftOverDamage);
					newLeftOverDamage += Math.max(0, leftOverDamage - characters.get(k).getHP());
				}
			}

			for (int k = 0; k < N; k++) {
				if (characters.get(k).getHP() > 0 && characters.get(k) instanceof Mafia && characters.get(k).getStatus().equals("Alive")) {
					z = true;
					break;
				}
			}

			leftOverDamage = newLeftOverDamage;
		}
		
		if(heal>-1) characters.get(heal).addHP(500);
				
		if(characters.get(target).getStatus().equals("Alive")) {
			System.out.println("No one died.");
		}
		else {
			int pid = target+1;
			System.out.println("Player" + pid + " died.");
			
			if (!this.isGameOver().equals("continue")) {
				gameOver();
			}
		}
		
		System.out.println();
		
		int userVote=-1;

		if (characters.get(userID).getStatus().equals("Alive")) {
			
			if(test>-1 && characters.get(test) instanceof Mafia) {}
			else {
				System.out.println("Choose a player to voteout: ");
				userVote = Integer.parseInt(reader.readLine());

				while (userVote < 1 || userVote > N || !characters.get(userVote - 1).getStatus().equals("Alive")) {
					System.out.println("You cannot voteout a dead player or a invalid input. Choose a player to voteout: ");
					userVote = Integer.parseInt(reader.readLine());
				}
				
				userVote--;
			}
		}
		
		if(test!=-1 && characters.get(test) instanceof Mafia) {
			System.out.println("Detectives tested on a mafia and the mafia will be voted out by default");
			vote = test;
		}
		else {
			vote = voteOut(userVote);
		}
		
		characters.get(vote).setStatus("Dead");
		
		int pid = vote+1;
		System.out.println();
		System.out.println("Player" + pid + " has been voted out");
		System.out.println();
		
		System.out.println("--End of round " + i + "--");
	}
}

public class Assignment_3 {

	public static void main(String[] args) throws Exception {
			
		MafiaGame game = new MafiaGame();
		game.run();
	}
}