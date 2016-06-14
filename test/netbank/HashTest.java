package netbank;

import static org.junit.Assert.*;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class HashTest {

	String salt;

	@Before
	public void initiate() {
		salt = Hash.getSalt();
	}

	@Test
	public void workingTest() {
		assertNotNull(salt);
		assertNotNull(Hash.SHA512("test123", salt));
	}

	@Test(expected = NoSuchAlgorithmException.class)
	public void exceptionCatch() throws NoSuchAlgorithmException {
		throw new NoSuchAlgorithmException();
	}
}
