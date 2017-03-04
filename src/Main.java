import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {

		//	formatToXml(new File("articles_cancer_2016.xml"), "out.xml");


		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			MyXMLHandler myXMLHandler = new MyXMLHandler();
			parser.parse("pubmed1.xml", myXMLHandler);
			System.out.println(myXMLHandler.getPubmedArticleSet());
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void formatToXml(File fileIn, String fileOut){
		BufferedReader br = null;
		Writer out = null;
		try{
			// pour lire dans in
			br = new BufferedReader(new FileReader(fileIn));  
			String line = null;  
			Pattern pattern; 
			// pour écrire dans out 
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileOut), "UTF-8"));

			boolean firstMatch1 = false;
			boolean firstMatch2 = false;
			boolean firstMatch3 = false;
			boolean firstMatch4 = false;
			Pattern pattern1 = Pattern.compile("</PubmedArticleSet>");
			Pattern pattern2 = Pattern.compile("<?xml version");
			Pattern pattern3 = Pattern.compile("<!DOCTYPE");
			Pattern pattern4 = Pattern.compile("<PubmedArticleSet>");

			while ((line = br.readLine()) != null)  
			{ 
				Matcher matcher1 = pattern1.matcher(Pattern.quote(line));
				Matcher matcher2 = pattern2.matcher(Pattern.quote(line));
				Matcher matcher3 = pattern3.matcher(Pattern.quote(line));
				Matcher matcher4 = pattern4.matcher(Pattern.quote(line));
				if(matcher1.find()){

					System.out.println(line);

				}else if(matcher2.find()){

					System.out.println(line);


				}else if(matcher3.find()){
					if(!firstMatch3){
						System.out.println("dedans2");
						out.write(line+"\n");
						firstMatch3=true;
					}else{

						System.out.println(line+"\n");
					}

				}else if(matcher4.find()){
					if(!firstMatch4){
						System.out.println("dedans4");
						out.write(line+"\n");
						firstMatch4=true;
					}else{
						System.out.println(line);
					}

				}else{
					out.write(line+"\n");
				}
			} 
			out.write("</PubmedArticleSet>");
		}catch(Exception e){
			System.out.println("la");
			e.printStackTrace();
		}finally {
			try{
				if(out != null)
					out.close();
				if(br != null)
					br.close();

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
