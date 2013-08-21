package net.forje.tools.xml.diff;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NodeComparator {

    private final Node _lhsNode;
    private final Node _rhsNode;

    public NodeComparator(final Node lhs,
                          final Node rhs) {
        _lhsNode = lhs;
        _rhsNode = rhs;
    }

    public void compare() {

        NodeWrapper lhsWrapper = new NodeWrapper(_lhsNode);
        NodeWrapper rhsWrapper = new NodeWrapper(_rhsNode);

        if (_lhsNode.hasChildNodes() && _rhsNode.hasChildNodes()) {

            final NodeList lhsChildren = _lhsNode.getChildNodes();
            final NodeList rhsChildren = _rhsNode.getChildNodes();

            final HashMap<String, Node> lhsMap = buildChildMap(lhsChildren);
            final HashMap<String, Node> rhsMap = buildChildMap(rhsChildren);

            final Set<String> lhsKeys = lhsMap.keySet();

            for (Iterator<String> iterator = lhsKeys.iterator(); iterator.hasNext(); ) {

                final String name = iterator.next();
                final Node lhsNode = lhsMap.get(name);

                if (rhsMap.containsKey(name)) {

                    final Node rhsNode = rhsMap.get(name);
                    final NodeComparator nodeComparator = new NodeComparator(lhsNode, rhsNode);

                    nodeComparator.compare();

                } else {
                    System.out.println("no match for [" + name + "]");
                }

            }

        } else if (!_lhsNode.hasChildNodes() && !_rhsNode.hasChildNodes()) {

            final String lhsValue = _lhsNode.getTextContent().trim();
            final String rhsValue = _rhsNode.getTextContent().trim();

            if ((lhsValue != null && !lhsValue.equals(rhsValue)) ||
                    (lhsValue == null && rhsValue != null)) {

                System.out.println("lhs: " + lhsWrapper.buildPath(true));
                System.out.println("rhs: " + rhsWrapper.buildPath(true));

                System.out.println(lhsValue + " : " + rhsValue);

            }

        }

    }

    private HashMap<String, Node> buildChildMap(final NodeList lhsChildren) {
        int length = lhsChildren.getLength();
        HashMap<String, Node> lhsMap = new HashMap<String, Node>(length);
        for (int i = 0; i < length; i++) {
            Node child = lhsChildren.item(i);
            short nodeType = child.getNodeType();
            if (Node.ELEMENT_NODE == nodeType) {
                String nodeName = child.getLocalName();
                lhsMap.put(nodeName, child);
            } else if (Node.TEXT_NODE == nodeType) {
                lhsMap.put("text", child);
            }
        }
        return lhsMap;
    }

}
