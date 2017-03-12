import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PubmedArticleSet {

	private List<PubmedArticle> setPubmedArticles = new ArrayList<PubmedArticle>(); 
	
	public void addPubmedArticle(PubmedArticle pubmedArticle) {
		setPubmedArticles.add(pubmedArticle);
	}
	
	public String toString(){
		String retour="";
		for(PubmedArticle pubmedArticle : setPubmedArticles){
			retour = retour + "\n"+ pubmedArticle.toString();
		}
		return retour;
	}

}
