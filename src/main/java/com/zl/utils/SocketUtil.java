package com.zl.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class SocketUtil {
	
	public static void sendString(Socket socket, String str) {
		OutputStreamWriter osw;
	    try {
			osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
	        osw.write(str, 0, str.length());
	        osw.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendObject(Socket socket, Object obj) {
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * see the link for potential risk
	 * http://stackoverflow.com/questions/19839172/how-to-read-all-of-inputstream-in-server-socket-java
	 * @throws IOException 
	 */
	public static String receiveString(Socket socket) throws IOException {
		
		byte[] messageByte = new byte[1000];
		String dataString = "";

		InputStream is = socket.getInputStream();
	    DataInputStream in = new DataInputStream(is);
        int bytesRead = in.read(messageByte);
        dataString += new String(messageByte, 0, bytesRead);
        
	    return dataString;
	}
	
	public static Object receiveObject(Socket socket) throws IOException {
		
		ObjectInputStream in = null;
		Object object = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			object = in.readObject();
			return object;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}
}
