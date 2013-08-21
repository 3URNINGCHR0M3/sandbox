package net.forje.tools.xml.diff;

import org.w3c.dom.Node;

import java.util.Stack;

public class NodeWrapper {

    private final Node _node;

    private boolean _indexCalculated = false;
    private int _index = 0;

    public NodeWrapper(final Node node) {
        _node = node;
    }

    public int getIndex() {

        if (_indexCalculated) {
            return _index;
        }

        int i = 0;
        Node tmp = _node;

        while (tmp.getPreviousSibling() != null) {

            // I haven't dug into what is being skipped here
            // the fix was just intuitive
            if (tmp.getNodeType() == Node.ELEMENT_NODE) {
                i++;
            }

            tmp = tmp.getPreviousSibling();

        }

        _index = i;
        _indexCalculated = true;

        return _index;

    }

    public String buildPath(boolean withIndex) {
        return buildNodePath(_node, withIndex);
    }

    public String buildPath() {
        return buildPath(false);
    }

    private Stack getStack(final Node node) {
        Stack stack = new Stack();
        processNode(node, stack);
        return stack;
    }

    private void processNode(final Node node,
                                    final Stack stack) {

        Node parentNode = node.getParentNode();

        if (parentNode != null) {
            stack.push(parentNode);
            processNode(parentNode, stack);
        }

    }

    private String buildNodePath(final Node node,
                                        final boolean withIndex) {

        Stack stack = getStack(node);
        stack.pop();

        StringBuilder builder = new StringBuilder();

        while (!stack.isEmpty()) {
            Node n = (Node) stack.pop();

            NodeWrapper wrapper = new NodeWrapper(n);
            appendQualifiedName(builder, n);

            if (withIndex) {
                builder.append("{");
                builder.append(wrapper.getIndex());
                builder.append("}");
            }
            if (!stack.isEmpty()) {
                builder.append("/");
            }
        }

        builder.append("/");
        appendQualifiedName(builder, node);

        return builder.toString();

    }

    private void appendQualifiedName(final StringBuilder builder,
                                            final Node n) {

        final String namespaceURI = n.getNamespaceURI();
        final String nsPrefix = n.lookupPrefix(namespaceURI);

        builder.append(nsPrefix);
        builder.append(":");

        final String localName = n.getLocalName();

        builder.append(localName);

    }

    public boolean isElementNode() {
        return _node.getNodeType() == Node.ELEMENT_NODE;
    }

    public boolean isTextNode() {
        return _node.getNodeType() == Node.TEXT_NODE;
    }

    public String getTextContent() {

        if (!isTextNode()) {
            return null;
        } else {
            return _node.getFirstChild().getTextContent();
        }

    }

}
