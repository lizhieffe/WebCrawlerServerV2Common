package com.zl.sockets;

import java.io.IOException;
import java.net.Socket;

import com.zl.interfaces.ISocketListenerCallback;
import com.zl.utils.SimpleLogger;
import com.zl.utils.SocketUtil;

public class VerificationListenerCallback implements ISocketListenerCallback {
	
	public VerificationListenerCallback() {
	}
	
	@Override
	public void onReceiveObject(Socket socket, Object object) {
		if (object instanceof Verification) {
			try {
				SocketUtil.sendObject(socket, "true");
			}
			catch (IOException ex) {
				SimpleLogger.error(ex.toString());
			}
		}
		else {
			try {
				SocketUtil.sendObject(socket, "false");
			}
			catch (IOException ex) {
				SimpleLogger.error(ex.toString());
			}
		}
	}

}
