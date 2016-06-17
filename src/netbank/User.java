package netbank;

public abstract class User {
	public static boolean ChangePassword(UserInf user, String password) {
		user.setHash(Hash.SHA512(password, user.getSalt()));
		return DatabaseSet.setUser(user);
	}
}
