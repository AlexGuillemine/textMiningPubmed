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
	

	//début du parsing
	public void startDocument() throws SAXException {
		System.out.println("Début du parsing");
	}
	//fin du parsing
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
	}  

	public void startElement(String namespaceURI, String lname,
			String qname, Attributes attrs) throws SAXException {

		//Nous stockons le nom du nœud pour gérer l'endroit ou affecter la valeur du nœud
		node = qname;

		//dès que nous rencontrons un élément, nous créons l'objet associé
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
		//Lorsque nous détectons la fin d'un élément
		//nous l'ajoutons à son objet parent
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
		//Il n'y a des valeurs de nœud que pour ces trois là
		//Dès que nous les rencontrons, nous stockons la valeur dans l'objet ad hoc
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
