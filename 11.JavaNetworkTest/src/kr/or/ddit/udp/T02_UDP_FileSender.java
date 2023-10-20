package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class T02_UDP_FileSender {

	private DatagramSocket ds;
	private DatagramPacket dp;

	private InetAddress receiverAddr;
	private int port; // 패킷 전송시 사용할 포트번호
	private File file;
	
	public static void main(String[] args) throws Exception {
		new T02_UDP_FileSender("192.168.141.17", 8888).start();
	}

	public T02_UDP_FileSender(String receiverIp, int port) {
		try {
			this.ds = new DatagramSocket();
			this.port = port;
			receiverAddr = InetAddress.getByName(receiverIp);
			file = new File("d:/D_Other/ala.jpg");

			if (!file.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws Exception {

		long fileSize = file.length();
		long totalReadBytes = 0;

		long startTime = System.currentTimeMillis();

		sendData("start".getBytes()); // 전송 시작을 알려주기 위한 문자열 전송

		sendData(file.getName().getBytes());

		sendData(String.valueOf(fileSize).getBytes()); // 총 파일의 크기 정보 전송

		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1000];

		while (true) {
			Thread.sleep(10); // 패킷 전송간의 간격을 주기 위함
			int readBytes = fis.read(buffer, 0, buffer.length);

			if (readBytes == -1) { // 파일을 다 읽은 경우
				break;
			}

			sendData(buffer, readBytes); // 읽어온 파일 내용 전송하기

			totalReadBytes += readBytes;

			System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + "Byte(s) ("
					+ (totalReadBytes * 100 / fileSize) + "%");
		}
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		double transferSpeed = fileSize / diffTime;

		System.out.println("걸린 시간 : " + diffTime + "(ms)");
		System.out.println("평균 전송속도  : " + transferSpeed + "(Bytes/ms)");

		System.out.println("전송 완료...");
	}

	public void sendData(byte[] data) {
		sendData(data, data.length);
	}

	/**
	 * 바이트 배열 데이터 전송하기
	 * 
	 * @param data   전송할 바이트 배열
	 * @param length 전송할 바이트 배열 크기
	 */
	public void sendData(byte[] data, int length) {
		dp = new DatagramPacket(data, length, receiverAddr, port);
		try {
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
