import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.*;
import jpcap.packet.EthernetPacket;
import java.io.Serializable;
import java.net.DatagramPacket;
import javax.swing.table.DefaultTableModel;
import jpcap.packet.TCPPacket;
/**
 *
 * @author home
 */

public class PacketContents implements PacketReceiver{
    
    //EthernetPacket pckt;
    public void recievePacket(Packet packet){
       // SnifferForm.outputText.append("This is ethernet protocol"+packet.toString());
        //SnifferForm.outputText.append(packet.toString()+"\n-------------------------------------------------"+"-------------------------------------------------\n\n");
    if (packet instanceof TCPPacket)
    {
    TCPPacket tcp =(TCPPacket) packet;
        System.out.println("IP Src:"+tcp.src_ip
        +"\nSrc Port"+tcp.src_port
        +"\nDst IP"+tcp.dst_ip
        +"\nDst Port"+tcp.dst_port
        +"\nlength:"+tcp.length
        +"\nHeader:"+tcp.header
        +"\nDatalink:"+tcp.datalink
        );
        String data1 = "1";
    String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();
    String data5 = "tcp";

    Object[] row = { data1, tcp.src_ip.toString(), data3, data4,data5 };

    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    
    }
    }

    @Override
    public void receivePacket(Packet packet) {
       //  SnifferForm.outputText.append("This is ethernet protocol"+packet.toString());
        //  SnifferForm.outputText.append(packet.toString()+"\n-------------------------------------------------"+"-------------------------------------------------\n\n");
      if (packet instanceof TCPPacket)
    {
    TCPPacket tcp =(TCPPacket) packet;
        System.out.println("IP Src:"+tcp.src_ip
        +"\nSrc Port"+tcp.src_port
        +"\nDst IP"+tcp.dst_ip
        +"\nDst Port"+tcp.dst_port
        +"\nlength:"+tcp.length
        +"\nHeader:"+tcp.header
        +"\nDatalink:"+tcp.datalink
        );
     String data1 = "1";
    String data2 = tcp.src_ip.toString();
    String data3 = tcp.src_ip.toString();
    String data4 = tcp.dst_ip.toString();
    String data5 = "tcp";

    Object[] row = { data1, tcp.src_ip, tcp.src_ip,tcp.dst_ip,"tcp" };

    DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
    model.addRow(row); 
    
    }
      
      
}
}
