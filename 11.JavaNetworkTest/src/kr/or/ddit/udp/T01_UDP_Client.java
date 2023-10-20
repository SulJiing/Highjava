package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class T01_UDP_Client {
	private DatagramPacket dp;
	private DatagramSocket ds;

	private byte[] msg;

	public static void main(String[] args) throws IOException {
			new T01_UDP_Client().start();
	}

	public T01_UDP_Client() {
		try {
			msg = new byte[100];
			// 소켓객체 생성(포트번호 명시하지 않으면 이용가능한 임의의 포트번호가 할당 됨)
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		InetAddress serverAddr = InetAddress.getByName("192.168.141.3");
		dp = new DatagramPacket(msg, 1, serverAddr, 8888);
		ds.send(dp); // 패킷을 서버로 전송한다.
		dp = new DatagramPacket(msg, msg.length);
		ds.receive(dp); // 서버가 보내주는 패킷을 받기 위해 수신한다
		System.out.println("현재 서버 시간 => " + new String(dp.getData()));
		ds.close(); // 소켓 종료
	}
}
