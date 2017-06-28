package sensingcar.coap.sever;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import org.eclipse.californium.core.CaliforniumLogger;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.scandium.ScandiumLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensingcar.coap.server.resource.BackTireResource;
import sensingcar.coap.sever.resource.BuzzerResource;
import sensingcar.coap.sever.resource.CameraResource;
import sensingcar.coap.sever.resource.FrontTireResource;
import sensingcar.coap.sever.resource.GasSensorResource;
import sensingcar.coap.sever.resource.LaserEmitterResource;
import sensingcar.coap.sever.resource.LcdResource;
import sensingcar.coap.sever.resource.PhotoresistorSensorResource;
import sensingcar.coap.sever.resource.RGBLedResource;
import sensingcar.coap.sever.resource.ThermistorSensorResource;
import sensingcar.coap.sever.resource.TrackingSensorResource;
import sensingcar.coap.sever.resource.UltrasonicSensorResource;

public class CoapResourceServer {
	//Field
		private static final Logger logger = LoggerFactory.getLogger(CoapResourceServer.class);
	private CoapServer coapServer;
	
	
	//static block(californium의 자체로그출력 방지)
	static {
		CaliforniumLogger.initialize();
		CaliforniumLogger.setLevel(Level.OFF);
		ScandiumLogger.initialize();
		ScandiumLogger.setLevel(Level.OFF);
	}
	
	
	
	//Constructor

	public CoapResourceServer() throws Exception {
		coapServer= new CoapServer();
				for(InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()){
			if(!addr.isLinkLocalAddress()){
				coapServer.addEndpoint(new CoapEndpoint(new InetSocketAddress(addr, CoAP.DEFAULT_COAP_PORT)));
			}
		}
		
		coapServer.add(new BackTireResource());
		coapServer.add(new FrontTireResource());
		coapServer.add(new BuzzerResource());
		coapServer.add(new LaserEmitterResource());
		coapServer.add(new CameraResource());
		coapServer.add(new RGBLedResource());
		coapServer.add(new LcdResource());
		
		coapServer.add(new UltrasonicSensorResource());
		coapServer.add(new ThermistorSensorResource());
		coapServer.add(new PhotoresistorSensorResource());
		coapServer.add(new TrackingSensorResource());
		coapServer.add(new GasSensorResource());
	}
	
//	Method
	public void start(){
		logger.info("실행");
		coapServer.start();
	}
	
	public void stop(){
		logger.info("실행");
		coapServer.stop();
		coapServer.destroy();
		
	}
	
}
