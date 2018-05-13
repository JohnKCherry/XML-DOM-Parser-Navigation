import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.w3c.dom.*;

class aNode{

	private Node node;
	private int level;
        private NamedNodeMap allAttributes; 

        public NamedNodeMap getAllAttributes() {
           return allAttributes;
        }

        public void setAllAttributes(NamedNodeMap allAttributes) {
            this.allAttributes = allAttributes;
        }

	public aNode(Node node, int level, NamedNodeMap allAttributes) {
		this.node = node;
		this.level = level;
                this.allAttributes = allAttributes;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static Comparator<aNode> LevelComparator = new Comparator<aNode>() {

		public int compare(aNode one, aNode two){
			int no1 = one.getLevel();
			int no2 = two.getLevel();

			return no1-no2;
		}
	};
}

class myDOMTreeProc {

	private final ArrayList<aNode> q = new ArrayList<>();

	public void processNode(Node el, int currLevel, NamedNodeMap attr){

		aNode tempNode = new aNode(el, currLevel, attr);
		q.add(tempNode);

		NodeList mixedContent = el.getChildNodes();
		int numberOfChildren = mixedContent.getLength();

		currLevel++;

		for(int i = 0; i < numberOfChildren; i++){
			Node currNode = mixedContent.item(i);
			if (currNode.getNodeType() == Node.ELEMENT_NODE){
                                NamedNodeMap allAttr = currNode.getAttributes();
				processNode(currNode, currLevel, allAttr);
		}
	}
        }

	public void printOutput(){

		Collections.sort(q, aNode.LevelComparator);
		int prevLevel = -1;
		for(aNode d : q){
			if(prevLevel != d.getLevel()){
				System.err.println("\n\n");
				System.out.println("------> Level is:\t " + d.getLevel());
				prevLevel = d.getLevel();
			}
			Node curr = d.getNode();

			System.err.println("----------------------------------------");
			System.out.println("Element named '" + curr.getNodeName() +
					"' with parent '" + curr.getParentNode().getNodeName());

			NodeList mixedContent = curr.getChildNodes();
			int numberOfChildren = mixedContent.getLength();

			for(int i = 0; i < numberOfChildren; i++){

				Node currNode = mixedContent.item(i);

				if (currNode.getNodeType() == Node.TEXT_NODE && currNode.getNodeValue().trim().length() != 0)
				{ System.out.println(currNode.getNodeType() + "-Text..............: " + currNode.getNodeValue()); };
				if (currNode.getNodeType() == Node.COMMENT_NODE)
				{ System.out.println(currNode.getNodeType() + "-Comment.......: " + currNode.getNodeValue()); };
				if (currNode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
				{ System.out.println(currNode.getNodeType() + "-PI.....................: " + currNode.getNodeValue()); };
				if (currNode.getNodeType() == Node.CDATA_SECTION_NODE)
				{ System.out.println(currNode.getNodeType() + "-CDATA............: " + currNode.getNodeValue()); };
                                
                        }
                            System.out.println(d.getNode().getNodeType() + "-Element '" + ((Element)(d.getNode())).getTagName() + " has " +
                                    d.getAllAttributes().getLength() + " attribute(s): ");
                            NamedNodeMap allAttr = d.getAllAttributes();
                            for(int i=0; i < d.getAllAttributes().getLength(); i++)
                                System.out.println(allAttr.item(i).getNodeName() + " = " +
						allAttr.item(i).getNodeValue());
        
	}
}
}

