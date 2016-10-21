package Util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Util {

	public String getValueByName(String nodeName, String elementName,
			String attributeName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(
					"webapps/NoSQLStreamBenchmark/config/config.xml"));

			NodeList nodes = document.getElementsByTagName(nodeName);

			Element element = (Element) nodes.item(0);
			NodeList nodeList = element.getElementsByTagName(nodeName
					.substring(0, nodeName.length() - 1));
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element elementList = (Element) nodeList.item(i);

				NodeList name = elementList.getElementsByTagName("name");

				if (name.item(0).getFirstChild().getTextContent()
						.equals(elementName)) {
					NodeList attr = elementList
							.getElementsByTagName(attributeName);

					return attr.item(0).getFirstChild().getTextContent()
							.toString();
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
