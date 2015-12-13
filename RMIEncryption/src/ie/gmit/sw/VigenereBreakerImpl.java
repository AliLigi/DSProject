package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker {

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;
	
	public VigenereBreakerImpl(String filename) throws Exception {
		breaker = new KeyEnumerator(filename);
		//UnicastRemoteObject.exportObject(this);
		
	}
	
	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException {
		return breaker.crackCypher(cypherText, maxKeyLength);
	}

	public static void main(String[] args) throws Exception {
		if(args.length==0){
			System.out.println("Usage: java ie.gmit.sw.VigenereBreakerImpl <filename>");
		}
		
		System.out.println("starting remote service");
		
		LocateRegistry.createRegistry(1099);
		
		Naming.bind("cypher-service", new VigenereBreakerImpl(args[0]));
		
		System.out.println("service started...");
		
		
	}
	
}
