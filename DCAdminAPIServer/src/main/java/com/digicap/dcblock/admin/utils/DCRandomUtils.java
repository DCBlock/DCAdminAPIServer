package com.digicap.dcblock.admin.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.util.Base64Utils;

public class DCRandomUtils {
	private static String hostName = null;
	public static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";
	public static final String PROVIDER_BOUNCYCASTLE = "BC";
	public static final String ALGORITHM_SHA1 = "SHA-1";
	/** Hexadecimal table for fast converting. */
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };
	
	static {
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNonce(int len) {
		int b64len = len / 3 * 2;

		byte[] tmp = sha1(concat((hostName + System.currentTimeMillis()).getBytes(), getRandom(5)));
		byte[] tmp2 = new byte[b64len];
		System.arraycopy(tmp, 0, tmp2, 0, b64len);
		return Base64Utils.encodeToString(tmp2);
	}
	
	public byte[] sha1(byte[] data) {
		return engine(data, ALGORITHM_SHA1, PROVIDER_BOUNCYCASTLE);
	}
	
	private byte[] engine(byte[] data, String hashAlg, String provider) {
		byte[] result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(hashAlg, provider);
			result = digest.digest(data);
		} catch (NoSuchProviderException e) {
			// Java Security에 적절한 Provider가 static 또는 dynamic으로 찾을 수 없을 때
			// log.severe("No Such Provider [" + provider + "] Exception");
			System.out.println("A : " + e);
		} catch (NoSuchAlgorithmException e) {
			// 해당 알고리즘을 찾을 수 없을 때
			// log.severe("No Such Algorithm [" + hashAlg + "] Exception");
			System.out.println("B : " + e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("C : " + e);
		}
		return result;
	}
	
	public byte[] concat(byte[] a, byte[] b) {
		byte[] tmp = new byte[a.length + b.length];
		System.arraycopy(a, 0, tmp, 0, a.length);
		System.arraycopy(b, 0, tmp, a.length, b.length);

		return tmp;
	}
	
	public synchronized byte[] getRandom(int len) {
		byte[] rand = new byte[len];
		try {
			SecureRandom.getInstance(ALGORITHM_SHA1PRNG).nextBytes(rand);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rand;
	}
	
	public String getUniqueRandom(int len) {
		String ret = "";
		ret = dumpHex(sha1(concat((hostName + System.currentTimeMillis()).getBytes(), getRandom(5))));

		return ret.substring(0, len);
	}
	
	public String dumpHex(byte[] byteArray) {
		return dumpHex(byteArray, (char) 0x00);
	}
	
	public String dumpHex(byte[] byteArray, char separator) {
		return dumpHex(byteArray, 0, byteArray.length, separator);
	}
	
	public String dumpHex(byte[] byteArray, int offset, int length, char separator) {
		StringBuffer sb = new StringBuffer();
		dumpHex(sb, byteArray, offset, length, separator);
		return sb.toString();
	}
	
	public void dumpHex(StringBuffer strBuffer, byte[] byteArray, int offset, int length, char separator) {
		if (byteArray == null || byteArray.length == 0)
			return;
		for (int i = offset; i < offset + length; i++) {
			appendHex(byteArray[i], strBuffer);
			if (separator != (char) 0x00 && i != offset + length - 1) {
				strBuffer.append(separator);
			}
		}
	}
	
	public void appendHex(byte hex, StringBuffer str) {
		str.append((char) hexDigits[(hex >>> 4) & 0x0F]);
		str.append((char) hexDigits[hex & 0x0F]);
	}
	
	public int getIntegerRandom(int length){
        Random generator = new Random();
        int randomValue = generator.nextInt(length);
        
        return randomValue;
	}
}
