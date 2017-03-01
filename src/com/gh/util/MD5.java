package com.gh.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	// ȫ������
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * ������ʽΪ���ָ��ַ���
     * 
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * ת���ֽ�����Ϊ16�����ִ�
     * 
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * @param strObj
     * @return
     */
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
	
    
    /**
     * @param strObj
     * @return
     */
    public static String GetMD5CodeToUpper(String strObj){
    	return GetMD5Code(strObj).toUpperCase();
    }
    
    /**
     * @param strObj
     * @return
     */
    public static String GetMD5CodeToLower(String strObj){
    	return GetMD5Code(strObj).toLowerCase();
    }
    
    //====================================================================//
    
    /**
     * @param text
     * @return
     */
    public static String MD5(String text) {
    	StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
	    	byte b[] = md.digest();
	    	int i;
	    	
	    	for (int offset = 0; offset < b.length; offset++) {
	    		i = b[offset];
	    		if(i<0) i+= 256;
	    		if(i<16)
	    		buf.append("0");
	    		buf.append(Integer.toHexString(i));
	    	}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return buf.toString();
    }
 
    //====================================================================//
    
    /**
     * @param text
     * @return
     */
    public static String MD5Digest(String text) {
    	String pwd = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			pwd = new BigInteger(1, md.digest()).toString(16); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return pwd;
    }
    
    //====================================================================// 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(GetMD5Code("1234"));
		System.out.println(GetMD5CodeToLower("12345678"));
		System.out.println(GetMD5CodeToUpper("12345678"));
		// 25d55ad283aa400af464c76d713c07ad
		System.out.println(MD5("12345678"));
		// 25d55ad283aa400af464c76d713c07ad
		System.out.println(MD5Digest("12345678"));
	}

}
