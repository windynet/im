package com.kingston.im.chat.logic.chat;

import org.springframework.stereotype.Component;

import com.kingston.im.chat.base.SessionManager;
import com.kingston.im.chat.logic.chat.message.res.ResChatToUser;
import com.kingston.im.chat.net.IoSession;

@Component
public class ChatService {
	
	public void chat(IoSession fromUser, long toUserId, String content) {
		IoSession toUser = SessionManager.INSTANCE.getSessionBy(toUserId);
		if (fromUser == null || toUser == null) {
			return;
		}
		if (!checkDirtyWords(content)) {
			return;
		}
		
		//双方都推送消息
		ResChatToUser response = new ResChatToUser();
		response.setContent(content);
		response.setFromUserId(fromUser.getUser().getUserId());
		toUser.sendPacket(response);
		
		fromUser.sendPacket(response);
	}
	
	private boolean checkDirtyWords(String content) {
		return true;
	}

}
