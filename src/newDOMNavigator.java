import org.w3c.dom.*;
import javax.xml.parsers.*;

public class newDOMNavigator{
	public static void main(String[] args) {
		try{
                    
                    if(args.length != 0){
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(args[0]);

			System.out.print("Name of document element is.. ");
			System.out.println(doc.getDocumentElement().getNodeName());
			
                //      System.out.print(" ... and its value is .. "); //this must be NULL !!
                //      System.out.println(doc.getDocumentElement().getNodeValue());
		/*	System.out.print("No of elements named 'book' anywhere in the doc is .. ");
			System.out.println(doc.getElementsByTagName("book").getLength()); */

			//Iteratively examine every element, starting again from doc element
			myDOMTreeProc dtp = new myDOMTreeProc();
                        dtp.processNode(doc.getDocumentElement(), 0, doc.getDocumentElement().getAttributes());
                        dtp.printOutput();
                    }
                    else{
                        System.out.println("Usage: java newDOMNavigator file.xml");
                    }
		}
		catch (Exception e){
			e.getMessage();
		}
	}
}
