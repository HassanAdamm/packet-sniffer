
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import javax.swing.table.DefaultTableModel;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;
import java.util.ArrayList;
import java.util.List;
import jpcap.packet.ICMPPacket;

public class PacketContents implements PacketReceiver {

    public static TCPPacket tcp;
    public static UDPPacket udp;
    public static ICMPPacket icmp;

    public static List<Object[]> rowList = new ArrayList<Object[]>();

    public void recievePacket(Packet packet) {
    }

    @Override
    public void receivePacket(Packet packet) {
        String _filterPort = "";
        
        
        if (packet instanceof TCPPacket) {
            tcp = (TCPPacket) packet;
            
            _filterPort = getProtocol(tcp.dst_port);
            
            if (_filterPort == "")
                _filterPort = getProtocol(tcp.src_port);

            Object[] row = {sniffer.No, tcp.length, tcp.src_ip, tcp.dst_ip, "TCP", _filterPort};

            rowList.add(new Object[]{sniffer.No, tcp.length, tcp.src_ip, tcp.dst_ip, "TCP", tcp.src_port, tcp.dst_port,
                tcp.ack, tcp.ack_num, tcp.data, tcp.sequence, tcp.offset, tcp.header});

            DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
            model.addRow(row);
            sniffer.No++;

        } else if (packet instanceof UDPPacket) {

            udp = (UDPPacket) packet;
            
            Object[] row = {sniffer.No, udp.length, udp.src_ip, udp.dst_ip, "UDP", "UDP"};
            rowList.add(new Object[]{sniffer.No, udp.length, udp.src_ip, udp.dst_ip, "UDP", udp.src_port, udp.dst_port,
                udp.data, udp.offset, udp.header});

            DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
            model.addRow(row);
            sniffer.No++;

        } else if (packet instanceof ICMPPacket) {

            icmp = (ICMPPacket) packet;
            
            Object[] row = {sniffer.No, icmp.length, icmp.src_ip, icmp.dst_ip, "ICMP", "ICMP"};
            rowList.add(new Object[]{sniffer.No, icmp.length, icmp.src_ip, icmp.dst_ip, "ICMP", icmp.checksum, icmp.header,
                icmp.offset, icmp.orig_timestamp, icmp.recv_timestamp, icmp.trans_timestamp, icmp.data});

            DefaultTableModel model = (DefaultTableModel) sniffer.jTable1.getModel();
            model.addRow(row);
            sniffer.No++;

        }
    }
    
    private String getProtocol(int port){
        switch(port){
            case 80:
                return "HTTP";                
            case 443:
                return "HTTPS";
            case 21:
                return "FTP";
            case 22:
                return "SSH";
            case 23:
                return "TELNET";
            case 25:
                return "SMTP";
            case 110:
                return "POP3";
            case 143:
                return "IMAP";
            case 993:
                return "IMAPS";
            case 53:
                return "DNS";
        }
        
        return "";        
    }
}
