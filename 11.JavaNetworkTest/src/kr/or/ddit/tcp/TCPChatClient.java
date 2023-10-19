package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost",7777);
		
		System.out.println("채팅 서버에 연결되었습니다.");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}
