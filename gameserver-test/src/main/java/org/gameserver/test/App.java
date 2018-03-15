package org.gameserver.test;

import com.jyg.bean.LogicEvent;
import com.jyg.net.EventDispatcher;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.jyg.net.Processor;
import com.jyg.net.ProtoProcessor;
import com.jyg.net.Service;
import com.jyg.net.WebSocketService;
import com.jyg.startup.Bootstarp;

import io.netty.buffer.ByteBuf;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {

    	EventDispatcher pcocessor = EventDispatcher.getInstance();
    	pcocessor.registerRpcEvent((short)0, new ProtoProcessor() {

			public ByteBuf process(LogicEvent event) throws Exception {
				return null;
			}
			
			public Parser<MessageLite> getProtoParser() {
				return null;
			}
    		
    	});
    	
        Bootstarp bootstarp = new Bootstarp();
        Service service = new WebSocketService(8000);
        bootstarp.addService(service);
        bootstarp.start();
    }
}