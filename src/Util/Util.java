package Util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Util {

	public static void main(String[] args) {
		new Util().getElementConfig("voldemort");
	}

	public String getElementConfig(String name) {
		String xml = ""; // Populated XML String....

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(
					"WebContent/config/config.xml"));
			Element rootElement = document.getDocumentElement();

			List<Element> element = findAllElementsByTagName(rootElement,
					"database");

			for (int i = 0; i < element.size(); i++) {
				// System.out.println("Elem: " +
				// element.get(i).getFirstChild().getTextContent());
				// name
				// host
				// path
				System.out.println("Valor: " + element.get(i).getTextContent());
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xml;
	}

	public static List<Element> findAllElementsByTagName(Element elem,
			String tagName) {
		List<Element> ret = new LinkedList<Element>();
		findAllElementsByTagName(elem, tagName, ret);
		return ret;
	}

	private static void findAllElementsByTagName(Element el, String tagName,
			List<Element> elementList) {

		if (tagName.equals(el.getTagName())) {
			elementList.add(el);
		}
		Element elem = getFirstElement(el);
		while (elem != null) {
			findAllElementsByTagName(elem, tagName, elementList);
			elem = getNextElement(elem);
		}
	}

	private static void findAllElementsByTagNameNS(Element el,
			String nameSpaceURI, String localName, List<Element> elementList) {

		if (localName.equals(el.getLocalName())
				&& nameSpaceURI.contains(el.getNamespaceURI())) {
			elementList.add(el);
		}
		Element elem = getFirstElement(el);
		while (elem != null) {
			findAllElementsByTagNameNS(elem, nameSpaceURI, localName,
					elementList);
			elem = getNextElement(elem);
		}
	}

	public static Element getFirstElement(Node parent) {
		Node n = parent.getFirstChild();
		while (n != null && Node.ELEMENT_NODE != n.getNodeType()) {
			n = n.getNextSibling();
		}
		if (n == null) {
			return null;
		}
		return (Element) n;
	}

	public static Element getNextElement(Element el) {
		Node nd = el.getNextSibling();
		while (nd != null) {
			if (nd.getNodeType() == Node.ELEMENT_NODE) {
				return (Element) nd;
			}
			nd = nd.getNextSibling();
		}
		return null;
	}
}
