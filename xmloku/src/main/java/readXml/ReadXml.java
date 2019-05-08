package readXml;

import developer.Developer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadXml {

    private String filePath ;


    public ReadXml(String filePath)
    {
        this.filePath = filePath;
    }

    public List<Developer> read(){

        File xmlFile = new File(filePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        List<Developer> devList = new ArrayList<Developer>();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document =  documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("developer");

            for (int i = 0; i < nodeList.getLength(); i++) {
                devList.add(getDeveloper(nodeList.item(i)));
            }

        }catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

        return devList;
    }

    private Developer getDeveloper(Node node)
    {
        Developer developer = new Developer();
        if(node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element element = (Element) node;
            developer.setName(getTagValue("name" , element));
            developer.setSurName(getTagValue("surname",element));
            developer.setAge(Integer.parseInt(getTagValue("age",element)));
            developer.setGender(getTagValue("gender", element));
            developer.setDepartment(getTagValue("department",element));
        }
        return  developer;
    }

    private String getTagValue(String name, Element element) {
    NodeList nodeList = element.getElementsByTagName(name).item(0).getChildNodes();
    Node node = (Node) nodeList.item(0);

    return node.getNodeValue();
    }
}
