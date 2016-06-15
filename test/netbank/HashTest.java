package netbank;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;

public class HashTest {

	String salt;

	@Before
	public void initiate() {
		salt = Hash.getSalt();
	}

	@Test
	public void workingTest() {
		Hash h = new Hash();
		assertNotNull(salt);
		assertNotNull(Hash.SHA512("test123", salt));
	}

//	@Test(expected = NoSuchAlgorithmException.class)
//	public void exceptionCatch() throws NoSuchAlgorithmException {
//		PowerMockito.spy(Hash.class);
//		Mockito.when(Hash.SHA512("test", salt)).thenReturn(false);
//		PowerMockito.verifyStatic(Mockito.times(2));
//	}
}
