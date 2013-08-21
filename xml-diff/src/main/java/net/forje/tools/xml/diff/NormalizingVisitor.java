package net.forje.tools.xml.diff;

import org.w3c.dom.Node;

public class NormalizingVisitor implements NodeVisitor {

    @Override
    public void visit(final Node node) {

        if (Node.ATTRIBUTE_NODE != node.getNodeType()) {
            return;
        }

        String nodeValue = node.getNodeValue();
        nodeValue = nodeValue.trim();
        node.setNodeValue(nodeValue);

    }

}
