package net.forje.tools.xml.diff;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NodeComparator {

    private final Node _lhsNode;
    private final Node _rhsNode;
    private NodeWrapper _lhsWrapper;
    private NodeWrapper _rhsWrapper;

    public NodeComparator(final Node lhs,
                          final Node rhs) {
        _lhsNode = lhs;
        _rhsNode = rhs;
        _lhsWrapper = new NodeWrapper(_lhsNode);
        _rhsWrapper = new NodeWrapper(_rhsNode);
    }

    public void compare() {


        if (_lhsNode.hasChildNodes() && _rhsNode.hasChildNodes()) {

            final NodeList lhsChildren = _lhsNode.getChildNodes();
            final NodeList rhsChildren = _rhsNode.getChildNodes();

            final HashMap<String, Node> lhsMap = buildChildMap(lhsChildren);
            final HashMap<String, Node> rhsMap = buildChildMap(rhsChildren);

            final Set<String> lhsKeys = lhsMap.keySet();

            // check from left to right
            for (Iterator<String> iterator = lhsKeys.iterator(); iterator.hasNext(); ) {

                final String lhsTagName = iterator.next();
                final Node lhsNode = lhsMap.get(lhsTagName);

                if (rhsMap.containsKey(lhsTagName)) {

                    final Node rhsNode = rhsMap.get(lhsTagName);
                    final NodeComparator nodeComparator = new NodeComparator(lhsNode, rhsNode);

                    nodeComparator.compare();

                } else {
                    NodeWrapper nodeWrapper = new NodeWrapper(lhsNode);
                    System.out.println();
                    System.out.println(nodeWrapper.buildPath(true));
                    System.out.println("    tag [" + lhsTagName + "] exists on LHS but not RHS");
                }

            }

            // check for tags on the right which don't exist on the left
            // assume content of tag checked in LHS to RHS evaluation
            final Set<String> rhsKeys = rhsMap.keySet();
            for (Iterator<String> iterator = rhsKeys.iterator(); iterator.hasNext(); ) {
                String rhsTagName = iterator.next();
                Node rhsNode = rhsMap.get(rhsTagName);
                if (!lhsMap.containsKey(rhsTagName)) {
                    NodeWrapper nodeWrapper = new NodeWrapper(rhsNode);
                    System.out.println();
                    System.out.println(nodeWrapper.buildPath(true));
                    System.out.println("    tag [" + rhsTagName + "] exists on RHS but not LHS");
                }
            }

        } else if (!_lhsNode.hasChildNodes() || !_rhsNode.hasChildNodes()) {
            // this will check text nodes
            final String lhsValue = _lhsNode.getTextContent().trim();
            final String rhsValue = _rhsNode.getTextContent().trim();

            if ((lhsValue != null && !lhsValue.equals(rhsValue)) ||
                    (lhsValue == null && rhsValue != null)) {

                System.out.println();
                System.out.println("lhs: " + _lhsWrapper.buildPath(true));
                System.out.println("rhs: " + _rhsWrapper.buildPath(true));
                System.out.println("    " + lhsValue + " : " + rhsValue);

            }

        } else {
            System.out.println("********* unhandled *********");
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
