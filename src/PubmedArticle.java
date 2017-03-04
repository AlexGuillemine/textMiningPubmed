
public class PubmedArticle {

	private Title title;
	private PMID pmid;
	private Abstract abstractText;
	
	public void setTitle(Title title) {
		this.title = title;
	}
	
	public void setPmid(PMID pmid) {
		this.pmid = pmid;
	}

	public void setAbstractText(Abstract abstractText) {
		this.abstractText = abstractText;
	}

	public String toString(){
		String retour = "";
		retour = "PMID - "+pmid+"\n";
		retour = retour+"Title - "+title.toString()+"\n";
		if(abstractText!=null)
			retour = retour+"Abstract - "+abstractText.toString()+"\n";
		return retour;
	}

}
