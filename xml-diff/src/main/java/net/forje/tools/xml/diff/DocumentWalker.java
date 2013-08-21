package net.forje.tools.xml.diff;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DocumentWalker {

    private final List<NodeVisitor> _visitors = new ArrayList<NodeVisitor>();

    private final Document _document;

    public DocumentWalker(final Document document) {
        _document = document;
    }

    public void add(NodeVisitor visitor) {
        _visitors.add(visitor);
    }

    public void process() throws Exception {

        final NodeList childNodes = _document.getChildNodes();
        processNodeList(childNodes);

    }

    private void processNodeList(final NodeList childNodes) {

        int length = childNodes.getLength();

        for (int i=0; i<length; i++) {
            Node node = childNodes.item(i);
            processNode(node);
        }

    }

    private void processNode(final Node node) {

        node.normalize();

        short nodeType = node.getNodeType();
        if (Node.ELEMENT_NODE != nodeType) {
            return;
        }

        callVisitors(node);

        processAttributes(node);

        NodeList childNodes = node.getChildNodes();
        processNodeList(childNodes);

    }

    private void callVisitors(final Node node) {

        NodeVisitor visitor;

        for (Iterator<NodeVisitor> iterator = _visitors.iterator(); iterator.hasNext(); ) {
            visitor = iterator.next();
            visitor.visit(node);
        }

    }

    private void processAttributes(final Node node) {

        if (!node.hasAttributes()) {
            return;
        }

        NamedNodeMap attributes = node.getAttributes();
        final int length = attributes.getLength();

        for (int i = 0; i < length; i++) {
            Node attributeNode = attributes.item(i);
            callVisitors(attributeNode);
        }

    }

}
