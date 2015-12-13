package ie.gmit.sw.queues;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class RequestManager 
{
	private BlockingQueue queue = new ArrayBlockingQueue<Request>(10);
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();
	private VigenereHandler handler;
	private String cypherText;
	

	public RequestManager()
	{
	}
	public void add(final Request r)
	{	
		try
		{
			//queue.put(r)//blocks if queue full
			Thread t1 = new Thread(new Runnable()
			{
				public void run()
				{
					try
					{
						queue.put(r);
					
						//writes encoded text 
						out.put(r.getJobNumber(),r.getCypherText());
						//there is an error on this line even though there shouldnt be
						handler = new VigenereHandler(queue, out);
						//rewrites decode text
						out.put(r.getJobNumber(), handler.returnResult());
					}
					catch(Exception e)
					{
						System.out.println("ERROR!"+e.toString());
					}
				}
			});
			t1.start();
			t1.join();//Waiting
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong"+e.toString());
		}
	}
	public String getResult(long jobNumber) throws Exception
	{
		Thread t2 = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					//comes back with the decoded text
					String result =	out.get(jobNumber);
					cypherText = result;
					System.out.println(cypherText);
					
				}
				catch(Exception e)
				{
					System.out.println("its here that youre wrong"+ e.toString());
				}
			}
		});
		t2.start();
		t2.join();
		out.remove(jobNumber);
		//removes form map
		handler.removeRequest(jobNumber);
		return cypherText;
	}
	//added in a main just to test the code 
	public static void main(String[] args) throws Exception 
	{
		Request req = new Request("EMRUDWBEIFFNEUUFBUEH", 4, 1);
		RequestManager rm = new RequestManager();
		rm.add(req);
		System.out.println(rm.getResult(1));
	}
}
