package net.forje.tools.xml.diff;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.*;

public class XPathNodeVisitor implements NodeVisitor {

    private final List<MatchingData> _expressions = new ArrayList<MatchingData>();

    @Override
    public void visit(final Node node) {

        if (Node.ELEMENT_NODE != node.getNodeType()) {
            return;
        }

        NodeWrapper nodeWrapper = new NodeWrapper(node);
        String nodePath = nodeWrapper.buildPath();

        if (node.hasAttributes()) {

            final NamedNodeMap attributes = node.getAttributes();

            AttributeQualifier qualifier = new AttributeQualifier(attributes);
            Collection<Node> nodes = qualifier.process();

            if (nodes.size() > 0) {

                final StringBuilder builder = new StringBuilder(nodePath);
                builder.append("[");

                Iterator<Node> iterator = nodes.iterator();
                while (iterator.hasNext()) {
                    Node next = iterator.next();
                    String s = formatAttributeExpression(next);
                    builder.append(s);
                    if (iterator.hasNext()) {
                        builder.append(" and ");
                    }
                }

                builder.append("]");
                String expression = builder.toString();

                MatchingData matchingData = new MatchingData(expression, node);

                _expressions.add(matchingData);

            }

        }

    }

    public List<MatchingData> getExpressions() {
        return _expressions;
    }

    private String formatAttributeExpression(final Node attributeNode) {

        final String name = attributeNode.getNodeName();
        final String value = attributeNode.getNodeValue();

        return "@" + name + "='" + value + "'";

    }

}
