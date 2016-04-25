package com.france.serverGuard.test;

import java.net.InetAddress;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

public class StatusUtil {

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws SigarException {
//		 try {
//			 getOs();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	public static String getOs() throws SigarException{
		 Sigar sigar = new Sigar();  
		 StringBuilder stringBuilder=new StringBuilder();
	        // ȡ����ǰ����ϵͳ������    
	        String hostname = "";  
	        try {  
	            hostname = InetAddress.getLocalHost().getHostName();  
	        } catch (Exception e) {  
	            hostname = sigar.getNetInfo().getHostName();  
	        }  
	        stringBuilder.append("��������"+hostname);
//	        System.out.println(hostname);  
//	  
//	        // ��ȡ��ǰ����ϵͳ����Ϣ    
	        OperatingSystem operatingSystem = OperatingSystem.getInstance();  
//	        System.out.println("operatingSystem arch��" + operatingSystem.getArch());// ����ϵͳ�ں������磺 386��486��586��x86  
//	        System.out.println("operatingSystem cpuEndian��" + operatingSystem.getCpuEndian());//  
//	        System.out.println("operatingSystem dataModel��" + operatingSystem.getDataModel());//  
//	        System.out.println("operatingSystem description��" + operatingSystem.getDescription());// ϵͳ����  
	        stringBuilder.append(" ����ϵͳ:"+operatingSystem.getDescription());
//	        System.out.println("operatingSystem machine��" + operatingSystem.getMachine());//    
//	        System.out.println("operatingSystem name��" + operatingSystem.getName());// ����ϵͳ����  
//	        System.out.println("operatingSystem patchLevel��" + operatingSystem.getPatchLevel());// ����ϵͳ��������  
//	        System.out.println("operatingSystem vendor��" + operatingSystem.getVendor());// ����ϵͳ��Ӧ��   
//	        System.out.println("operatingSystem vendorCodeName��" + operatingSystem.getVendorCodeName());// ��Ӧ�̱�����  
//	        System.out.println("operatingSystem vendorName��" + operatingSystem.getVendorName());// ����ϵͳ��Ӧ������   
//	        System.out.println("operatingSystem vendorVersion��" + operatingSystem.getVendorVersion());// ����ϵͳ��Ӧ�̰汾    
//	        System.out.println("operatingSystem version��" + operatingSystem.getVersion());// ����ϵͳ�İ汾��  
//	  
//	        // ��ȡ��ǰϵͳ���̱��е��û���Ϣ    
//	        Who whoArray [] = sigar.getWhoList();  
//	        if (whoArray != null) {  
//	            for (int i = 0; i < whoArray.length; i++) {  
//	                Who who = whoArray[i];  
//	                System.out.println("\n~~~~~~~~~" + i + "~~~~~~~~~~~~");  
//	                System.out.println("who device��" + who.getDevice());  
//	                System.out.println("who host��" + who.getHost());  
//	                System.out.println("who time��" + who.getTime());  
//	                System.out.println("who user��" + who.getUser());// ��ǰϵͳ���̱��е��û���  
//	            }  
//	        }  
	        sigar.close();  
	        return stringBuilder.toString();
	}
	static void getDisk() throws SigarException{
		Sigar sigar = new Sigar();  
		  
        FileSystem [] fileSystemArray = sigar.getFileSystemList();  
        for (int i=0;i<fileSystemArray.length;i++ ) {
        	FileSystem fileSystem=fileSystemArray[i];
            System.out.println("fileSystem dirName��" + fileSystem.getDirName());//�������̷�����   
            System.out.println("fileSystem devName��" + fileSystem.getDevName());//�������̷�����   
            System.out.println("fileSystem typeName��" + fileSystem.getTypeName());// �ļ�ϵͳ�����������籾��Ӳ�̡������������ļ�ϵͳ��    
            System.out.println("fileSystem sysTypeName��" + fileSystem.getSysTypeName());//�ļ�ϵͳ���ͣ����� FAT32��NTFS  
            System.out.println("fileSystem options��" + fileSystem.getOptions());  
            System.out.println("fileSystem flags��" + fileSystem.getFlags());  
            System.out.println("fileSystem type��" + fileSystem.getType());  
              
            FileSystemUsage fileSystemUsage = null;  
              
            try {  
                fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());  
            } catch (SigarException e) {//��fileSystem.getType()Ϊ5ʱ����ָ��쳣������ʱ�ļ�ϵͳ����Ϊ����  
                continue;  
            }  
            System.out.println("fileSystemUsage total��" + fileSystemUsage.getTotal() + "KB");// �ļ�ϵͳ�ܴ�С  
            System.out.println("fileSystemUsage free��" + fileSystemUsage.getFree() + "KB");// �ļ�ϵͳʣ���С   
            System.out.println("fileSystemUsage used��" + fileSystemUsage.getUsed() + "KB");// �ļ�ϵͳ��ʹ�ô�С  
            System.out.println("fileSystemUsage avail��" + fileSystemUsage.getAvail() + "KB");// �ļ�ϵͳ���ô�С  
            System.out.println("fileSystemUsage files��" + fileSystemUsage.getFiles());  
            System.out.println("fileSystemUsage freeFiles��" + fileSystemUsage.getFreeFiles());  
            System.out.println("fileSystemUsage diskReadBytes��" + fileSystemUsage.getDiskReadBytes());  
            System.out.println("fileSystemUsage diskWriteBytes��" + fileSystemUsage.getDiskWriteBytes());  
            System.out.println("fileSystemUsage diskQueue��" + fileSystemUsage.getDiskQueue());  
            System.out.println("fileSystemUsage diskServiceTime��" + fileSystemUsage.getDiskServiceTime());  
            System.out.println("fileSystemUsage usePercent��" + fileSystemUsage.getUsePercent() * 100 + "%");// �ļ�ϵͳ��Դ��������    
            System.out.println("fileSystemUsage diskReads��" + fileSystemUsage.getDiskReads());  
            System.out.println("fileSystemUsage diskWrites��" + fileSystemUsage.getDiskWrites());  
            System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");  
        }  
	}
	public static String getCpu() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();
		CpuInfo infos[] = sigar.getCpuInfoList();
	       CpuPerc cpuList[] = null;
	       cpuList = sigar.getCpuPercList();
	       for (int i = 0; i < infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
	           stringBuilder.append("��" + (i + 1) + "��CPU��Ϣ:");
	           stringBuilder.append("CPU�ܵ�ʹ����:    " + CpuPerc.format(cpuList[i].getCombined())+" ////");
	       }
	       sigar.close();
	    return stringBuilder.toString();
	}
	public static String getNet() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();   
		stringBuilder.append("ipv4:"+sigar.getFQDN());
		
//        // ��ǰ��������ʽ����  
//        try {  
//            System.out.println(sigar.getFQDN());// ��Fully Qualified Domain Name������Ϊ��ȫ������  ipv4
//        } catch (SigarException e) {  
//            e.printStackTrace();  
//        }  
//  
//        String[] netInterfaceList = sigar.getNetInterfaceList();  
//  
//        // ��ȡ����������Ϣ  
//        for (int i = 0; i < netInterfaceList.length; i++) {  
//            String netInterface = netInterfaceList[i];// ����ӿ�  
//            System.out.println("netInterface��" + netInterface);  
//            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
//            System.out.println("Address = " + netInterfaceConfig.getAddress());// IP��ַ  
//            System.out.println("Netmask = " + netInterfaceConfig.getNetmask());// ��������  
//            if ((netInterfaceConfig.getFlags() & 1L) <= 0L) {// ����װ���Ƿ���������  
//                System.out.println("!IFF_UP...skipping getNetInterfaceStat");  
//                continue;  
//            }  
//            NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(netInterface);  
//            System.out.println("netInterfaceStat rxPackets��" + netInterfaceStat.getRxPackets());// ���յ��ܰ�����  
//            System.out.println("netInterfaceStat txPackets��" + netInterfaceStat.getTxPackets());// ���͵��ܰ�����  
//            System.out.println("netInterfaceStat rxBytes��" + netInterfaceStat.getRxBytes());// ���յ������ֽ���  
//            System.out.println("netInterfaceStat txBytes��" + netInterfaceStat.getTxBytes());// ���͵����ֽ���  
//            System.out.println("netInterfaceStat rxErrors��" + netInterfaceStat.getRxErrors());// ���յ��Ĵ������  
//            System.out.println("netInterfaceStat txErrors��" + netInterfaceStat.getTxErrors());// �������ݰ�ʱ�Ĵ�����  
//            System.out.println("netInterfaceStat rxDropped��" + netInterfaceStat.getRxDropped());// ����ʱ�����İ���  
//            System.out.println("netInterfaceStat txDropped��" + netInterfaceStat.getTxDropped());// ����ʱ�����İ���  
//            System.out.println("netInterfaceStat rxOverruns��" + netInterfaceStat.getRxOverruns());  
//            System.out.println("netInterfaceStat txOverruns��" + netInterfaceStat.getTxOverruns());  
//            System.out.println("netInterfaceStat rxFrame��" + netInterfaceStat.getRxFrame());  
//            System.out.println("netInterfaceStat txCollisions��" + netInterfaceStat.getTxCollisions());  
//            System.out.println("netInterfaceStat txCarrier��" + netInterfaceStat.getTxCarrier());  
//            System.out.println("netInterfaceStat speed��" + netInterfaceStat.getSpeed());  
//        }  
//  
//        // һЩ��������Ϣ  
//        for (int i = 0; i < netInterfaceList.length; i++) {  
//            String netInterface = netInterfaceList[i];// ����ӿ�  
//            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);  
//            if (NetFlags.LOOPBACK_ADDRESS.equals(netInterfaceConfig.getAddress())  
//                || (netInterfaceConfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0  
//                || NetFlags.NULL_HWADDR.equals(netInterfaceConfig.getHwaddr())) {  
//                continue;  
//            }  
//  
//            System.out.println("netInterfaceConfig name��" + netInterfaceConfig.getName());  
//            System.out.println("netInterfaceConfig hwaddr��" + netInterfaceConfig.getHwaddr());// ����MAC��ַ  
//            System.out.println("netInterfaceConfig type:" + netInterfaceConfig.getType());  
//            System.out.println("netInterfaceConfig description��" + netInterfaceConfig.getDescription());// ����������Ϣ  
//            System.out.println("netInterfaceConfig address��" + netInterfaceConfig.getAddress());// IP��ַ  
//            System.out.println("netInterfaceConfig destination��" + netInterfaceConfig.getDestination());  
//            System.out.println("netInterfaceConfig broadcast��" + netInterfaceConfig.getBroadcast());// ���ع㲥��ַ  
//            System.out.println("netInterfaceConfig netmask��" + netInterfaceConfig.getNetmask());// ��������  
//            System.out.println("netInterfaceConfig flags��" + netInterfaceConfig.getFlags());  
//            System.out.println("netInterfaceConfig mtu��" + netInterfaceConfig.getMtu());  
//            System.out.println("netInterfaceConfig metric��" + netInterfaceConfig.getMetric());  
//        }  
        sigar.close(); 
        return stringBuilder.toString();
	}
	public static String getMem() throws SigarException{
		Sigar sigar = new Sigar();  
		StringBuilder stringBuilder=new StringBuilder();  
        // �����ڴ���Ϣ    
        Mem mem = sigar.getMem();  
//        System.out.println("mem total��" + mem.getTotal() + " B");  
//        System.out.println("mem ram��" + mem.getRam() + " B");  
//        System.out.println("mem used��" + mem.getUsed() + " B");  
//        System.out.println("mem free��" + mem.getFree() + " B");  
//        System.out.println("mem actualUsed��" + mem.getActualUsed() + " B");    
//        System.out.println("mem actualFree��" + mem.getActualFree() + " B");  
//        System.out.println("mem usedPercent��" + mem.getUsedPercent() + "%");  
//        System.out.println("mem freePercent��" + mem.getFreePercent() + "%");  
        stringBuilder.append("�ڴ�ʹ����:"+mem.getUsedPercent() + "%")  ;
//        // ��������Ϣ    
//        Swap swap = sigar.getSwap();  
//        System.err.println("swap total��" + swap.getTotal() + " B");  
//        System.err.println("swap used��" + swap.getUsed() + " B");  
//        System.err.println("swap free��" + swap.getFree() + " B");  
//        System.err.println("swap pageIn��" + swap.getPageIn());  
//        System.err.println("swap pageOut��" + swap.getPageOut());  
        return stringBuilder.toString();
	}
}
