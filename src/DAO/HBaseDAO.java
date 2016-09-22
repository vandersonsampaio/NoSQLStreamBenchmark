package DAO;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import com.google.protobuf.ServiceException;

public class HBaseDAO implements IDAO {
	
	private String HOST_CONNECTION = "178.62.254.52";
	//private static Configuration conf = null;
	
	public HBaseDAO(){
		Configuration conf = HBaseConfiguration.create();
		
		conf.set("hbase.master", "178.62.254.52"); // master info
        conf.set("hbase.master.port", "60000");
		conf.set("hbase.zookeeper.quorum", "178.62.254.52");
		//this.conf.set("hbase.master", "178.62.254.52:60010");
        conf.set("hbase.zookeeper.property.clientport", "2181");
        conf.set("zookeeper.znode.parent", "/hbase-unsecure");
        
        try {
			HBaseAdmin.checkHBaseAvailable(conf);
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] obter(String resolucao){
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		getAllRecord("test");
		return true;
	}
	
	@Override
	public void limpar(){
		
	}
	
	 public void getAllRecord (String tableName) {
        /*try{
        	HBaseAdmin admin = new HBaseAdmin(conf);
             HTable table = new HTable(conf, tableName);
             Scan s = new Scan();
             ResultScanner ss = table.getScanner(s);
             for(Result r:ss){
                 for(KeyValue kv : r.raw()){
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));
                 }
             }
        } catch (IOException e){
            e.printStackTrace();
        }*/
    }
}

