package vavi.apps.rplay;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.JmmDNSImpl;
import javax.jmdns.impl.NetworkTopologyEventImpl;


/**
 * Emetteur Bonjour pour qu'iTunes detecte la borne airport
 * @author bencall
 *
 */

//
public class BonjourEmitter {
	JmmDNS dns;
	
	public BonjourEmitter(String name, String identifier, int port, boolean pass) throws IOException {

			// Set up TXT Record	    
		    Map<String,Object> txtRec = new HashMap<>();
		    txtRec.put("txtvers", "1");
		    txtRec.put("pw", String.valueOf(pass));
		    txtRec.put("sr", "44100");
		    txtRec.put("ss", "16");
		    txtRec.put("ch", "2");
		    txtRec.put("tp", "UDP");
		    txtRec.put("sm", "false");
		    txtRec.put("sv", "false");
		    txtRec.put("ek", "1");
		    txtRec.put("et", "0,1");
		    txtRec.put("cn", "0,1");
		    txtRec.put("vn", "3");

		    // Il faut un serial bidon pour se connecter
		    if (identifier == null) {
		    	identifier = "";
		    	for(int i=0; i<6; i++) {
		    		identifier = identifier + Integer.toHexString((int) (Math.random()*255)).toUpperCase();
		    	}
		    }

			// Zeroconf registration
		    ServiceInfo info = ServiceInfo.create(identifier + "@" + name + "._raop._tcp.local", identifier + "@" + name, port, 0, 0, txtRec);

		    dns = JmmDNS.Factory.getInstance();
		    ((JmmDNSImpl)dns).inetAddressAdded(new NetworkTopologyEventImpl(JmDNS.create(InetAddress.getByName("localhost")), InetAddress.getByName("localhost")));

		    try {
		        Thread.sleep(1000); // If this isn't done the Announcement sometimes doesn't go out on the local interface
		    } catch (InterruptedException e) {
		        e.printStackTrace(System.err);
		    }

		    dns.registerService(info);
	}

	/**
	 * Stop service publishing
	 */
	public void stop() throws IOException {
        dns.unregisterAllServices();
		dns.close();
	} 
}

