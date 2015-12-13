package ie.gmit.sw.queues;

import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;



import ie.gmit.sw.VigenereBreaker;

public class VigenereHandler implements Runnable{
	
	private BlockingQueue<Request> queue;
	//map 
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();	
	private String result;
	private Request req = null;
	//the blocking queue which puts info in
	public VigenereHandler(BlockingQueue<Request> q, Map<Long, String> out)
	{
		this.out = out;
		this.queue = q;
		run();
	}
	

	@Override
	public void run()
	{

		try 
		{
			req = queue.take();
		} 
		
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		
		try 
		{
			//decrypts and places jobnumber on the map
			VigenereBreaker vb = (VigenereBreaker) Naming.lookup("////cypher-service");
			result = vb.decrypt(req.getCypherText(),  req.getMaxKeyLenght());
			out.put(req.getJobNumber(), result);

		} catch (Exception e) 
		{
			System.out.println("You are wrong!!" + e.toString());
		}
		
		
	}
	public String returnResult()
	{
		return out.get(req.getJobNumber());
	}
	
	public void removeRequest(long jobNumber)
	{
		out.remove(jobNumber);
	}
}
	


