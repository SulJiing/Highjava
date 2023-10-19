package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	// 서버는 클라이언트가 접속하면 컴퓨터의 D_Other 폴더에 있는 이미지를 클라이언트에 보내준다.
	private ServerSocket server;
	private Socket socket;
	private FileInputStream fis;
	private DataInputStream dis;
	private DataOutputStream dos;

	public void startServer() {
		try {

			server = new ServerSocket(7777);
			System.out.println("파일 서버 준비 완료...");

			String downFolder = "D:\\A_TeachingMaterial\\05. web\\vsweb\\imeage";

			File file = null;

			while (true) {
				System.out.println("파일 전송 대기 중...");
				socket = server.accept();

				System.out.println("요청파일 존재여부 체크 중...");

				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				file = new File(downFolder + dis.readUTF());

				if (!file.exists()) {
					System.out.println("요청파일(" + file.getName() + ")존재하지 않습니다.");
					dos.writeUTF("요청파일(" + file.getName() + ")존재하지 않습니다.");

					dos.close();
					dis.close();
					socket.close();
					continue;
				} else {
					dos.writeUTF("OK"); // 요청파일 전송 준비 완료 확인 메세지
					System.out.println("요청파일(" + file.getName() + ") 전송 시작...");
				}

				fis = new FileInputStream(file);

				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

				int data = 0;
				while ((data = bis.read()) != -1) {
					bos.write(data);
				}
				System.out.println("요청파일)" + file.getName() + ") 전송완료...");

				bis.close();
				bos.close();
				dis.close();
				dos.close();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TcpFileServer().startServer();
	}
}
