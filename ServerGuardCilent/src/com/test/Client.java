package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	static Socket server;

	public static void main(String[] args) throws Exception {
		System.out.println("�����������ַ�����ȡ��������Ϣ�������ִ�Сд��");
		System.out.println("C:CPU��Ϣ,M:�ڴ�״̬,N:������Ϣ,O:����ϵͳ��Ϣ,Q:�˳�ϵͳ");
		server = new Socket(InetAddress.getLocalHost(), 9876);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = wt.readLine();
			out.println(str);
			out.flush();
			if ("Q".equals(str)||"q".equals(str)) {
				break;
			}
			System.out.println(in.readLine());
		}
		server.close();
	}
}
