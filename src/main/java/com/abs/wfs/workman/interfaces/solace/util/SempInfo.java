package com.abs.wfs.workman.interfaces.solace.util;

import com.solacesystems.common.config.Version;
import com.solacesystems.jcsmp.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class SempInfo {
    protected JCSMPSession session = null;
    protected static final String SEMP_VERSION_TR = "soltr/5_1";

    public SempInfo() {
        Version v = JCSMPRuntime.onlyInstance().getVersion();
//		System.out.printf("Sample app %s / JCSMP %s\n", getClass().getSimpleName(), v.getSwVersion());
    }

    /**
     * Print appliance info (version strings) and appliance capabilities. Only call
     * after connecting the session.
     *
     * This demonstrates how to use JCSMPSession#getCapability(CapabilityType)
     * to query peer capabilities.
     */
    protected void printRouterInfo() {
        final List<CapabilityType> routerversioncaps = new ArrayList<CapabilityType>() {
            private static final long serialVersionUID = 1L;
            {
                add(CapabilityType.PEER_PLATFORM);
                add(CapabilityType.PEER_SOFTWARE_DATE);
                add(CapabilityType.PEER_SOFTWARE_VERSION);
            }
        };

        try {
            String routerInfo = "Appliance information: ";
            Iterator<CapabilityType> it_routerinfo = routerversioncaps.iterator();
            while(it_routerinfo.hasNext()) {
                routerInfo += String.valueOf(session.getCapability(it_routerinfo.next()));
                if (it_routerinfo.hasNext()) routerInfo += ", ";
            }
//			System.out.println(routerInfo);

            routerInfo = "Appliance capabilities: ";
            it_routerinfo = Arrays.asList(CapabilityType.values()).iterator();
            while(it_routerinfo.hasNext()) {
                CapabilityType c = it_routerinfo.next();
                if (routerversioncaps.contains(c))
                    continue;
                routerInfo += String.format("%s:%s", c, session.getCapability(c));
                if (it_routerinfo.hasNext())
                    routerInfo += ", ";
            }
//			System.out.println(routerInfo);

        } catch (JCSMPException ex) {
            System.out.println("Error occurred printing appliance info: " + ex);
        }
    }

    /**
     * Close the session and exit.
     */
    protected void finish(final int status) {
        if (session != null) {
//			printFinalSessionStats(session);
            session.closeSession();
        }
//		System.exit(status);
    }

//	protected void printFinalSessionStats(JCSMPSession s) {
//		SolaceUtils.printSessionStats(s);
//	}

    protected void printRxMessage(BytesXMLMessage msg) {
        byte[] data = new byte[msg.getContentLength()];
        msg.readContentBytes(data);
        String rxXmlDoc = new String(data);
        String rxAttachment = "";
        if (msg.hasAttachment()) {
            byte[] attachment = new byte[msg.getAttachmentContentLength()];
            msg.readAttachmentBytes(attachment);
            rxAttachment = new String(attachment);
        }

        String replyTo = null;
        // This call accesses custom header data and may impact performance.
        if (msg.getReplyTo() != null)
            replyTo = msg.getReplyTo().toString();

        String cids = null;
        List<Long> cidlist = null;
        if ((cidlist = msg.getConsumerIdList()) != null && cidlist.size() > 0) {
            cids = "";
            for (Long curCid : cidlist) {
                cids += String.valueOf(curCid) + " ";
            }
        }

        System.out.println("Received message: " + msg.toString());
        System.out.println("   Message contents: " + rxXmlDoc);
        System.out.println("   Message attachment: " + rxAttachment);
        if (replyTo != null)
            System.out.println("   Message replyTo: " + replyTo);
        if (cids != null)
            System.out.println("   Message consumer IDs: " + cids);
    }

    protected class PrintingMessageHandler implements XMLMessageListener {
        public PrintingMessageHandler() {
        }

        public void onException(JCSMPException exception) {
            System.err.println("Error occurred, printout follows.");
            exception.printStackTrace();
        }

        public void onReceive(BytesXMLMessage msg) {
            printRxMessage(msg);
        }
    }

    public class PrintingPubCallback implements JCSMPStreamingPublishCorrelatingEventHandler {

        @Override
        public void handleErrorEx(Object messageID, JCSMPException cause, long timestamp) {
            System.err.println("Error occurred for message: " + (String)messageID);
            cause.printStackTrace();
        }

        @Override
        public void responseReceivedEx(Object messageID) {
            System.out.println("Response received for message: " + (String)messageID);
        }
    }

    public class PrintingSessionEventHandler implements SessionEventHandler {
        public void handleEvent(SessionEventArgs event) {
            System.out.printf("Received Session Event %s with info %s\n", event.getEvent(), event.getInfo());
        }
    }
}
