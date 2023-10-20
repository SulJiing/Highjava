package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class T01_HTTP_Server {

	private final int port = 80;
	private final String encoding = "UTF-8";

	public static void main(String[] args) {
		new T01_HTTP_Server().start();
	}

	public void start() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(this.port);

			while (true) {
				System.out.println("브라우저 요청 대기 중...");
				Socket socket = serverSocket.accept();

				// Http 요청 처리 스레드 생성 및 구동
				HttpHandler handler = new HttpHandler(socket);
				handler.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 응답 내용 생성하기
	 * @param filePath 응답 내용으로 사용할 파일 경로
	 * @return 응답내용을 담은 바이트 배열
	 */
	private byte[] makeResponseBody(String filePath) {
		byte[] data = null;
		FileInputStream fis = null;
		try {
			File file = new File(filePath);	//file 사이즈가 궁금해 length이용하려고 생성
			data = new byte[(int)file.length()];
			fis = new FileInputStream(file);
			fis.read(data);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	/**
	 * 응답 헤더 생성하기
	 * @param contentLength 응답내용 크기
	 * @param mimeType 응답내용 컨텐츠 타입정보
	 * @return 헤더정보를 담은 바이트 배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n"+"Server: MyHttpServer 1.0\r\n"+ "Content-length:" + contentLength + "\r\n"
				+ "Content-type: " + mimeType + " charset=" + this.encoding;
		
		return header.getBytes();
	}
	

	/**
	 * Http 요청 처리를 위한 스레드 클래스
	 * 
	 * @author PC-08
	 *
	 */
	class HttpHandler extends Thread {

		private Socket socket;

		public HttpHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("요청 처리 시작...");
			OutputStream out = null;
			BufferedReader br = null;

			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // BufferdReader쓰는 이유는 받을 데이터가
																							// String이기 때문

				// 요청메세지 헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();

				// Request Line
				String reqLine = br.readLine(); // readLine() 한줄씩 읽음

//				System.out.println("Request Line : " + reqLine);

				while (true) {
					String str = br.readLine();

					if (str.equals(""))
						break; // Empty Line 체크

					sb.append(str + "\n");
				}

				// 헤더 정보
				String reqHeader = sb.toString();
//				System.out.println("=== 요청 헤더 정보 ===");
//				System.out.println(reqHeader);
//				System.out.println("---------------------");

				// 요청 메세지의 경로정보 가져오기
				String reqPath = ""; // 요청 경로 정보
				StringTokenizer st = new StringTokenizer(reqLine);
//				StringTokenizer st = new StringTokenizer(reqLine, ); // 두번째 파라미터 부분에 원하는 구분자 넣을 수 있음 없으면 default값으로 공백이 들어감
				while (st.hasMoreTokens()) {
					String token = st.nextToken();

					if (token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}

				// HTTP프로토콜의 양식에 맞춰 보내줘야 함

				// URL디코더를 이용한 처리(한글이 퍼센트인코딩처리 되어있어 디코딩 해줘야 함)
				reqPath = URLDecoder.decode(reqPath, encoding);
//				System.out.println("요청 경로 정보 : " + reqPath);

				String filePath = "./WebContent" + reqPath;

				// 해당 파일이름을 이용하여 Content-Type 정보 추출하기 //마임타입 형태
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);

				File file = new File(filePath);
				if (!file.exists()) {
//					makeErrorPage(out, 404, "NOT FOUND");
					return;
				}

				byte[] body = makeResponseBody(filePath);

				byte[] header = makeResponseHeader(body.length, contentType);

				out.write(header);
				// 응답 내용을 보내기 전에 반드시 Empty Line 넣기...
				out.write("\r\n\r\n".getBytes());
				out.write(body);

				out.flush(); // 강제 데이터 방출

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
