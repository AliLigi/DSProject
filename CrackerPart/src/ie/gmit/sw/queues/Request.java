package ie.gmit.sw.queues;

public class Request {
	private String cypherText;
	private int maxKeyLenght;
	private long jobNumber;
	
	//source created this for me. Added the constructor and the gets and sets!
	public Request(String cypherText, int maxKeyLenght, long jobNumber) {
		super();
		this.cypherText = cypherText;
		this.maxKeyLenght = maxKeyLenght;
		this.jobNumber = jobNumber;
	}

	public String getCypherText() {
		return cypherText;
	}

	public void setCypherText(String cypherText) {
		this.cypherText = cypherText;
	}

	public int getMaxKeyLenght() {
		return maxKeyLenght;
	}

	public void setMaxKeyLenght(int maxKeyLenght) {
		this.maxKeyLenght = maxKeyLenght;
	}

	public long getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(long jobNumber) {
		this.jobNumber = jobNumber;
	}
	
	

}
