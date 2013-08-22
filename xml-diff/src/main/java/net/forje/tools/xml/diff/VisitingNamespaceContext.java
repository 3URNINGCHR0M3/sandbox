package net.forje.tools.xml.diff;

import net.forje.common.types.Strings;
import org.w3c.dom.Node;

import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VisitingNamespaceContext implements NamespaceContext, NodeVisitor {

    private final Map<String, String> _prefixToURIMap = new HashMap<String, String>(10);
    private final Map<String, String> _uriToPrefixMap = new HashMap<String, String>(10);

    private String _defaultNameSpaceURI;

    @Override
    public String getNamespaceURI(final String prefix) {

        if (prefix == null || "null".equalsIgnoreCase(prefix)) {
            return _defaultNameSpaceURI;
        }

        String s = _prefixToURIMap.get(prefix);
        return s;
    }

    @Override
    public String getPrefix(final String namespaceURI) {
        return _uriToPrefixMap.get(namespaceURI);
    }

    @Override
    public Iterator getPrefixes(final String namespaceURI) {
        return _prefixToURIMap.keySet().iterator();
    }

    @Override
    public void visit(final Node node) {

        if (Node.ELEMENT_NODE != node.getNodeType() && Node.ATTRIBUTE_NODE != node.getNodeType()) {
            return;
        }

        final String namespaceURI = node.getNamespaceURI();
        final String prefix = node.getPrefix();

        if (!Strings.isEmpty(namespaceURI) && !Strings.isEmpty(prefix)) {

            if (!_prefixToURIMap.containsKey(prefix)) {
                System.out.println("found namespace -> [" + prefix + ":" + namespaceURI + "]");
                _prefixToURIMap.put(prefix, namespaceURI);
                _uriToPrefixMap.put(namespaceURI, prefix);
            }

        } else if (!Strings.isEmpty(namespaceURI) && Strings.isEmpty(prefix)) {
            // default namespace
            if (Strings.isEmpty(_defaultNameSpaceURI)) {
                System.out.println("found default namespace -> [" + namespaceURI + "]");
                _defaultNameSpaceURI = namespaceURI;
            } else {
                if (!_defaultNameSpaceURI.equals(namespaceURI)) {
                    System.out.println("found ANOTHER default namespace -> [" + namespaceURI + "]");
                }

            }

        }

    }

}
