package com.wangfj.netty.server;

import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wfj.netty.Server;

/**
 * 服务器运行控制器
 * 
 * @Class Name ServerControlHandler
 * @Author wangfei
 * @Create In 2014年9月28日
 */
public class ServerControlHandler {
	private static Logger log = LoggerFactory.getLogger("ServerControlHandler");

	private static Server server;

	public static void main(String[] args) throws Exception {
		if (args != null && args.length != 0 && (args[0].equals("stop"))) {
			int ctrlPort = Integer.parseInt(args[1]);
			Socket s = new Socket("localhost", ctrlPort);
			PrintWriter w = new PrintWriter(s.getOutputStream());
			w.println("stop");
			w.flush();
			w.close();
			s.close();
		} else if (args == null || args.length == 0 || args[0].equals("start")) {
			int serverPort = Integer.parseInt(args[1]);
			int conrtrolPort = Integer.parseInt(args[2]);
			server = new Server(serverPort, conrtrolPort);
			try {
				server.run();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				System.out.println(e.getMessage());
				server.shutdown(-1);
			}
		}
	}

}
