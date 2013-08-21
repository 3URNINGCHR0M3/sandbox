package net.forje.tools.xml.diff;

import org.w3c.dom.Node;

import javax.xml.namespace.QName;

/**
* This is an XPath expression to be matched.
*/
public class MatchingData {

    /**
    * The XPath expression as a String
    */
    private final String _expression;

    /**
    * Original node from the LHS document
    */
    private final Node _originalNode;

    /**
    * The matching node from the RHS document
    */
    private Node _matchingNode;

    public MatchingData(final String expression,
                        final Node originalNode) {
        _expression = expression;
        _originalNode = originalNode;
    }

    public void setMatchingNode(final Node matchingNode) {
        _matchingNode = matchingNode;
    }

    public boolean isMatched() {
        return (_matchingNode != null);
    }

    public String getExpression() {
        return _expression;
    }

    public QName getOriginalQName() {

        String namespaceURI = _originalNode.getNamespaceURI();
        String localName = _originalNode.getLocalName();

        QName qName = new QName(namespaceURI, localName);

        return qName;

    }

    public void compare() {

        if (!isMatched()) {
            return;
        }

        NodeComparator comparator = new NodeComparator(_originalNode, _matchingNode);
        comparator.compare();

    }

}
