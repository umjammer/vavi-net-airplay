/*
 * https://github.com/bencall/RPlay
 */

package vavi.net.airplay;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.JmmDNSImpl;
import javax.jmdns.impl.NetworkTopologyEventImpl;

import vavi.util.Debug;


/**
 * Bonjour transmitter for iTunes to detect the airport terminal
 *
 * @author bencall
 */
public class Bonjour {

    private JmmDNS mdns;

    public Bonjour(String name, String identifier, int port, boolean pass) throws IOException {

        // Set up TXT Record
        Map<String, String> txtRec = new HashMap<>();
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

        // Zeroconf registration
        ServiceInfo info = ServiceInfo
                .create(identifier + "@" + name + "._raop._tcp.local", identifier + "@" + name, port, 0, 0, txtRec);
Debug.println(Level.FINE, info);

        mdns = JmmDNS.Factory.getInstance();
        ((JmmDNSImpl) mdns).inetAddressAdded(new NetworkTopologyEventImpl(JmDNS.create(InetAddress.getByName("localhost")),
                                                                         InetAddress.getByName("localhost")));

        try {
            // If this isn't done the Announcement sometimes doesn't go out on
            // the local interface
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mdns.registerService(info);
Debug.println(Level.FINE, "service registered");
    }

    /**
     * Stop service publishing
     */
    public void stop() throws IOException {
        mdns.unregisterAllServices();
        mdns.close();
    }
}
