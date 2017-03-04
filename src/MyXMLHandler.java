import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLHandler extends DefaultHandler {
	//Nous nous servirons de cette variable plus tard
	private String node = null;
	private PubmedArticleSet pubmedArticleSet;
	private PubmedArticle pubmedArticle;
	private Title title;
	private Abstract asbstractText;
	private PMID pmid;
	private String baliseConteneur = "resultList";
	private String baliseArticle = "result";
	private String balisePMID = "pmid";
	private String baliseTitle = "title";
	private String baliseAbstract = "abstractText";
	

	//d�but du parsing
	public void startDocument() throws SAXException {
		System.out.println("D�but du parsing");
	}
	//fin du parsing
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
	}  

	public void startElement(String namespaceURI, String lname,
			String qname, Attributes attrs) throws SAXException {

		//Nous stockons le nom du n�ud pour g�rer l'endroit ou affecter la valeur du n�ud
		node = qname;

		//d�s que nous rencontrons un �l�ment, nous cr�ons l'objet associ�
		if(qname.equals(baliseConteneur)){
			pubmedArticleSet = new PubmedArticleSet();
		}
		else if(qname.equals(baliseArticle)){
			pubmedArticle = new PubmedArticle();
		}
		else if(qname.equals(baliseTitle)){
			title = new Title();
		}else if(qname.equals(baliseAbstract)){
			asbstractText = new Abstract();
		}else if(qname.equals(balisePMID)){
			pmid = new PMID();
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		//Lorsque nous d�tectons la fin d'un �l�ment
		//nous l'ajoutons � son objet parent
		if(qName.equals(baliseArticle)){
			this.pubmedArticleSet.addPubmedArticle(this.pubmedArticle);
		}
		else if(qName.equals(baliseTitle)){
			this.pubmedArticle.setTitle(this.title);
		}else if(qName.equals(baliseAbstract)){
			this.pubmedArticle.setAbstractText(this.asbstractText);
		}else if(qName.equals(balisePMID)){
			this.pubmedArticle.setPmid(this.pmid);;
		}
	}

	public void characters(char[] data, int start, int end){

		String str = new String(data, start, end);
		//Il n'y a des valeurs de n�ud que pour ces trois l�
		//D�s que nous les rencontrons, nous stockons la valeur dans l'objet ad hoc
		System.out.println("---"+node+" : "+str+"___");
		if(node.equals(baliseTitle)){
			title.setTitle(str);
		}else if(node.equals(baliseAbstract)){
			asbstractText.setAbstractText(str);
		}else if(node.equals(balisePMID)){
			pmid.setPMID(str);
		}     
	} 

	public PubmedArticleSet getPubmedArticleSet(){
		return this.pubmedArticleSet;
	}



}
