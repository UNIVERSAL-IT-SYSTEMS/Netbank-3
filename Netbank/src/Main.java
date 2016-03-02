
public class Main {
	
	int balance;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean withdraw(int amount) {
		if (balance - amount < 0)
			return false;
		else {
			balance = balance - amount;
			return true;
		}
		
	}

}
