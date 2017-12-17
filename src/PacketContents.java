import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.*;
import jpcap.packet.EthernetPacket;
import java.io.Serializable;
import java.net.DatagramPacket;
import javax.swing.table.DefaultTableModel;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import jpcap.packet.IPPacket;
import java.util.*;
import jpcap.packet.DatalinkPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author home
 */

public class PacketContents implements PacketReceiver{
    public static TCPPacket tcp;
    public static UDPPacket udp;
    public static EthernetPacket eth;
    public static DatalinkPacket dp;
    public static byte b[];
    public static Packet[] pkt=new Packet[10000];
    public static    String[] SRC_IP_PKT=new String[10000];
    public static    String[] DST_IP_PKT=new String[10000];
    public static    String[] SRC_PORT_PKT=new String[10000];
    public static    String[] DST_PORT_PKT=new String[10000];
    public static    String[] LENGTH_PKT=new String[10000];
    public static    String[] HEADER_PKT=new String[10000];
    public static    String[] DATALINK_PKT=new String[10000];
    
    
    public static List<Object[]> rowList = new ArrayList<Object[]>();
    
    
    //EthernetPacket pckt;
    public void recievePacket(Packet packet){
      // SnifferForm.outputText.append("This is ethernet protocol"+packet.toString());
       // SnifferForm.outputText.append(packet.toString()+"\n-------------------------------------------------"+"-------------------------------------------------\n\n");
    pkt[SnifferForm.No]=packet;
    
       if (packet instanceof TCPPacket)
    {
        tcp=(TCPPacket) packet;
        /*tcp[SnifferForm.No].src_port=
        +"\nDst IP"+tcp[SnifferForm.No].dst_ip
        +"\nDst Port"+tcp[SnifferForm.No].dst_port
        +"\nlength:"+tcp[SnifferForm.No].length
        +"\nHeader:"+tcp[SnifferForm.No].header
        +"\nDatalink:"+tcp[SnifferForm.No].datalink
        );*/
      //  tcp[SnifferForm.No].getAckw
        //String data1 = "1";
   /* String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();*/
   
    //String data5 = "tcp";
    dp = packet.datalink;
    eth=(EthernetPacket)dp;
    b=tcp.data;
    Object[] row = { SnifferForm.No, tcp.length, tcp.src_ip,tcp.dst_ip,"tcp" };
    
    rowList.add(new Object[] { SnifferForm.No, tcp.length, tcp.src_ip,tcp.dst_ip,"tcp",tcp.src_port
            ,tcp.dst_port,tcp.ack,tcp.ack_num,b.toString(),tcp.sequence });
    


    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    SnifferForm.No++;
    }
       else if(packet instanceof UDPPacket)
       {
       
        udp=(UDPPacket) packet;
        /*tcp[SnifferForm.No].src_port=
        +"\nDst IP"+tcp[SnifferForm.No].dst_ip
        +"\nDst Port"+tcp[SnifferForm.No].dst_port
        +"\nlength:"+tcp[SnifferForm.No].length
        +"\nHeader:"+tcp[SnifferForm.No].header
        +"\nDatalink:"+tcp[SnifferForm.No].datalink
        );*/
      //  tcp[SnifferForm.No].getAckw
        //String data1 = "1";
   /* String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();*/
    //String data5 = "tcp";
    Object[] row = { SnifferForm.No, udp.length, udp.src_ip,udp.dst_ip,"udp" };
    rowList.add(new Object[] { SnifferForm.No, udp.length, udp.src_ip,udp.dst_ip,"udp",udp.src_port,udp.dst_port,udp.data });


    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    SnifferForm.No++;   
       
       
       }
    }

    @Override
    public void receivePacket(Packet packet) {
       //  SnifferForm.outputText.append("This is ethernet protocol"+packet.toString());
        //  SnifferForm.outputText.append(packet.toString()+"\n-------------------------------------------------"+"-------------------------------------------------\n\n");
        //SnifferForm.outputText.append(packet.toString()+"\n-------------------------------------------------"+"-------------------------------------------------\n\n");
    if (packet instanceof TCPPacket)
    {
        tcp=(TCPPacket) packet;
        /*tcp[SnifferForm.No].src_port=
        +"\nDst IP"+tcp[SnifferForm.No].dst_ip
        +"\nDst Port"+tcp[SnifferForm.No].dst_port
        +"\nlength:"+tcp[SnifferForm.No].length
        +"\nHeader:"+tcp[SnifferForm.No].header
        +"\nDatalink:"+tcp[SnifferForm.No].datalink
        );*/
      //  tcp[SnifferForm.No].getAckw
        //String data1 = "1";
   /* String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();*/
    //String data5 = "tcp";
    dp = packet.datalink;
    eth=(EthernetPacket)dp;
    b=tcp.data;
        
    
    Object[] row = { SnifferForm.No, tcp.length, tcp.src_ip,tcp.dst_ip,"tcp" };
    
rowList.add(new Object[] { SnifferForm.No, tcp.length, tcp.src_ip,tcp.dst_ip,"tcp",tcp.src_port,tcp.dst_port
        ,tcp.ack,tcp.ack_num,b.toString(),tcp.sequence});

    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    SnifferForm.No++;
    }
           else if(packet instanceof UDPPacket)
       {
       
        udp=(UDPPacket) packet;
        /*tcp[SnifferForm.No].src_port=
        +"\nDst IP"+tcp[SnifferForm.No].dst_ip
        +"\nDst Port"+tcp[SnifferForm.No].dst_port
        +"\nlength:"+tcp[SnifferForm.No].length
        +"\nHeader:"+tcp[SnifferForm.No].header
        +"\nDatalink:"+tcp[SnifferForm.No].datalink
        );*/
      //  tcp[SnifferForm.No].getAckw
        //String data1 = "1";
   /* String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();*/
    //String data5 = "tcp";
    Object[] row = { SnifferForm.No, udp.length, udp.src_ip,udp.dst_ip,"udp" };
    rowList.add(new Object[] { SnifferForm.No, udp.length, udp.src_ip,udp.dst_ip,"udp",udp.src_port,udp.dst_port,udp.data });

    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    SnifferForm.No++;   
       
       
       }
}
}
