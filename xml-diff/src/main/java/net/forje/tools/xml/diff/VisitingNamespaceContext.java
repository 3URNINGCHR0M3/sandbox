package net.forje.tools.xml.diff;

import net.forje.common.types.Strings;
import org.w3c.dom.Node;

import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VisitingNamespaceContext implements NamespaceContext, NodeVisitor {

    private final Map<String,String> _prefixToURIMap = new HashMap<String,String>(10);
    private final Map<String,String> _uriToPrefixMap = new HashMap<String,String>(10);

    @Override
    public String getNamespaceURI(final String prefix) {
        String s = _prefixToURIMap.get(prefix);
        return s;
    }

    @Override
    public String getPrefix(final String namespaceURI) {
        return _uriToPrefixMap.get(namespaceURI);
    }

    @Override
    public Iterator getPrefixes(final String namespaceURI) {
        return null;
    }

    @Override
    public void visit(final Node node) {

        if (Node.ELEMENT_NODE != node.getNodeType() && Node.ATTRIBUTE_NODE != node.getNodeType()) {
            return;
        }

        final String namespaceURI = node.getNamespaceURI();
        final String prefix = node.getPrefix();

        if (!Strings.isEmpty(namespaceURI) & !Strings.isEmpty(prefix)) {

            if (!_prefixToURIMap.containsKey(prefix)) {
                _prefixToURIMap.put(prefix, namespaceURI);
                _uriToPrefixMap.put(namespaceURI, prefix);
            }

        }

    }

}
