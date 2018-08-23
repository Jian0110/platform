package cn.com.ssj.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import cn.com.ssj.service.UserService;

@ServerEndpoint(value = "/websocket/1")
@Component
public class WebSocket implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext; 
	
	private UserService userService;
	
	
	private static final Logger logger = LogManager.getLogger(WebSocket.class);

	private static final Map<String, Session> userMap = new HashMap<String, Session>();

	private Session session;

	private String key;

	@OnOpen
	public void onOpen(Session session) {
		
		if(this.userService == null) {
			//Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(logger::info);
			userService = (UserService)applicationContext.getBean("userService");
		}
		
		logger.info("onOpen : {}", session);
		this.session = session;
		Map<String, List<String>> params = session.getRequestParameterMap();

		List<String> values = null;
		if (params.get("username") != null) {
			values = params.get("username");
			if (StringUtils.isNotBlank(values.get(0))) {
				this.key = values.get(0);
				userMap.put(values.get(0), session);
			}
		}
		logger.info("onOpen params: {}", params);
	}

	@OnClose
	public void onClose() {
		logger.info("onClose: {}", this.key);
		userMap.remove(this.key);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("onMessage : {} , {}", message, session);
		sendMessage("what");
	}

	@OnError
	public void onError(Session session, Throwable error) {
		logger.info("onError : {} , {} ", session, error);
	}

	public void sendMessage(String message)  {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.session.getAsyncRemote().sendText(message);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		WebSocket.applicationContext = applicationContext;
	}

	public int getConnectCount() {
		return userMap.size();
	}
}
