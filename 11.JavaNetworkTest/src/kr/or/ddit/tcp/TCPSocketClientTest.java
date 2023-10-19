package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSocketClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		String serverIp = "192.168.141.20"; // localhost
		// 자기 자신 컴퓨터를 나타내는 방법
		// IP : 127.0.0.1
		// 호스트이름 : localhost
		
		System.out.println(serverIp+" 서버에 접속 중입니다...");
		
		// 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 연결이 되면(소켓이 생성되면) 이후의 작업이 진행된다.
		System.out.println("연결 되었습니다...");
		
		// 서버에서 보낸 메세지 받기
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버에서 받은 메세지 출력하기
		System.out.println("받은 메세지 : "+dis.readUTF());
		
		System.out.println("연결 종료.");
		
		dis.close();
		socket.close();
	}
}
