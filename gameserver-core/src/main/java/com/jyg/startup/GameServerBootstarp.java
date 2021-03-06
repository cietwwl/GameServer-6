package com.jyg.startup;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.GeneratedMessageV3;
import com.jyg.enums.ProtoEnum;
import com.jyg.net.EventDispatcher;
import com.jyg.net.HttpProcessor;
import com.jyg.net.ProtoProcessor;
import com.jyg.net.Service;
import com.jyg.process.PingProtoProcessor;
import com.jyg.process.PongProtoProcessor;
import com.jyg.proto.p_common;
import com.jyg.proto.p_common.p_common_request_ping;
import com.jyg.timer.IdleTimer;
import com.jyg.util.GlobalQueue;

/**
 * Created by jiayaoguang on 2017/3/30
 */
public class GameServerBootstarp extends AbstractBootstrap {
	
	List<Service> services = new ArrayList<>(1);
	public GameServerBootstarp(){
		
		
	}
	
	public void addService(Service service) {
		services.add(service); 
	}
	
	
//	public void registerLogicEvent(int eventid, ProtoProcessor<? extends GeneratedMessageV3> processor) throws Exception {
//		EventDispatcher.getInstance().registerLogicEvent(eventid, processor);
//	}
//	
//	public void registerSocketEvent(int eventid, ProtoProcessor<? extends GeneratedMessageV3> protoprocessor) throws Exception {
//		EventDispatcher.getInstance().registerSocketEvent(eventid, protoprocessor);
//	}
	
	public void registerHttpEvent(String path, HttpProcessor processor) throws Exception {
		EventDispatcher.getInstance().registerHttpEvent(path, processor);
	}
	
	public void registerSendEventIdByProto(int eventId,Class<? extends GeneratedMessageV3> protoClazz) throws Exception {
		EventDispatcher.getInstance().registerSendEventIdByProto( eventId, protoClazz);
	}

    public void start() throws Exception {
    	GlobalQueue.start();
    	if(services.size()==0) {
    		throw new Exception("no port is listening");
    	}
    	
    	for(Service service:services) {
    		service.start();
    	}
    }

}