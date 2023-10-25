package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	// 대화명, 클라이언트의 소켓을 저장하기 위한 객체변수 선언
	private Map<String, Socket> clients;
	
	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	// 서버 시작
	public void startServer() {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("멀티챗 서버가 시작되었습니다.");
			
			while(true) {
				// 클라이언트의 접속을 대기한다.
				socket = serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속하였습니다.");
				
				// 메세지 전송처리를 위한 스레드 생성 및 실행
				ServerRiceiver receiver = new ServerRiceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 	대화방 즉, Map에 저장된 전체 유저에게 안내 메세지를 전송하는 메서드
	 * @param msg 전송할 안내 메세지
	 */
	public void sendMessage(String msg) {
		
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next(); // 대화명 구하기
				
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg); // 메세지 보내기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 	대화방 즉, Map에 저장된 전체 유저에게 안내 메세지를 전송하는 메서드
	 * @param msg 전송할 안내 메세지
	 * @param from 메세지 보낸 사람
	 * @throws IOException 
	 */
	private void sendMessage(String msg, String from) throws IOException {
		Iterator<String> it = clients.keySet().iterator();

		while (it.hasNext()) {
			String name = it.next();
			DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());

			if (msg.startsWith("/w_")) {
				int nameEndId = msg.indexOf("_", 3); // 대상 이름의 끝 인덱스
				System.err.println(nameEndId);
				if (nameEndId > 3) {
					String targetName = msg.substring(3, nameEndId);
					if (name.equals(targetName)) {
						dos.writeUTF("귓속말 from " + from + ": " + msg.substring(nameEndId + 1));
					}
				}
			} else {
				dos.writeUTF("[" + from + "]" + msg);
			}
		}
	}
	
	/**
	 * 	서버에서 클라이언트로부터 수신한 메세지를 처리하기 위한 쓰레드 클래스
	 * 	(Inner클래스에서는 Outer클래스의 멤버들을 직접 접근할 수 있음.
	 * @author PC-11
	 *
	 */
	class ServerRiceiver extends Thread {
		
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerRiceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			try {
				
				// 서버에서는 클라이언트가 보내는 최초의 메세지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				
				// 대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메세지를 보낸다.
				sendMessage("#"+name+"님이 입장했습니다.");
				
				// 대화명과 소켓객체를 Map에 저장한다.
				clients.put(name, socket);
				
				System.out.println("현재 서버 접속자 수는"+clients.size()+"명 입니다.");
				
				// 이후의 메세지 처리는 반복문으로 처리한다.
				// 메세지를 받으면 바로 모든 클라이언트에게 보내준다.
				while(dis != null) {
					sendMessage(dis.readUTF(),name);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트의 접속에 문제가 생긴 경우이므로 사용자 정리 작업을 해준다.
				sendMessage(name+"님이 나가셨습니다.");
				
				// Map에서 해당 사용자를 삭제한다.
				clients.remove(name);
				
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속을 종료하였습니다.");
				System.out.println("현재 서버 접속자 수는"+clients.size()+"명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer().startServer();
	}
}
