
public class Title {
	
	private String title=null;

	public void setTitle(String str) {
		if(title==null)
			title = str;
		else
			title = title + str;
	}

	public String toString() {
		return title;
	}
	
	

}
