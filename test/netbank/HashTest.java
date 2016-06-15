package netbank;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.*;

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
	
	@Test
	public void exceptionCatch() throws NoSuchAlgorithmException {
		LinkedList mockedList = mock(LinkedList.class);
		when(mockedList.get(0)).thenReturn("first");
		System.out.println(mockedList.get(0));
		System.out.println(mockedList.get(999));
	}
}
