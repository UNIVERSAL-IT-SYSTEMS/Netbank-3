
public class Account {
	
	private Double balance;
	private String owner;
	private Double interest;
	private Double dept;
	private String valuta;
	
	public Account(Double balance, String owner, Double interest, Double dept, String valuta) {
		this.balance = balance;
		this.owner = owner;
		this.interest = interest;
		this.dept = dept;
		this.valuta = valuta;
	}
	
	public Double getBalance() { return balance; }
	public String getOwner() { return owner; }
	public Double getInterest() { return interest; }
	public Double getDept() { return dept; }
	public String getValuta() { return valuta; }
	
	public void setBalance(Double balance) { this.balance = balance; }
	public void setOwner(String owner) { this.owner = owner; }
	public void setInterest(Double interest) { this.interest = interest; }
	public void setDept(Double dept) { this.dept = dept; }
	public void setValuta(String valuta) { this.valuta = valuta; }
	
}
