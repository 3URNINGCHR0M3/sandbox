package net.forje.tools.xml.diff;

import net.forje.common.types.Strings;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VisitingNamespaceContext implements NamespaceContext, NodeVisitor {

    private final Map<String, String> _prefixToURIMap = new HashMap<String, String>(10);
    private final Map<String, String> _uriToPrefixMap = new HashMap<String, String>(10);

    // set to standard defined null name space by default
    private String _defaultNameSpaceURI = XMLConstants.NULL_NS_URI;

    public VisitingNamespaceContext() {
        processNameSpacePair(XMLConstants.XML_NS_URI, XMLConstants.XML_NS_PREFIX);
        processNameSpacePair(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE);
        processNameSpacePair(XMLConstants.NULL_NS_URI, XMLConstants.DEFAULT_NS_PREFIX );
    }

    @Override
    public String getNamespaceURI(final String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("Prefix may not be null.");
        }

        if ("null".equalsIgnoreCase(prefix)) {
            return _defaultNameSpaceURI;
        }

        if (_prefixToURIMap.containsKey(prefix)) {
            return _prefixToURIMap.get(prefix);
        } else {
            return XMLConstants.NULL_NS_URI;
        }

    }

    @Override
    public String getPrefix(final String namespaceURI) {
        System.out.println("VisitingNamespaceContext.getPrefix");
        System.out.println("    [" + namespaceURI + "]");
        return _uriToPrefixMap.get(namespaceURI);
    }

    @Override
    public Iterator getPrefixes(final String namespaceURI) {
        System.out.println("VisitingNamespaceContext.getPrefixes");
        System.out.println("    [" + namespaceURI + "]");
        return null;
    }

    @Override
    public void visit(final Node node) {

        if (Node.ELEMENT_NODE != node.getNodeType() && Node.ATTRIBUTE_NODE != node.getNodeType()) {
            return;
        }

        final String namespaceURI = node.getNamespaceURI();
        final String prefix = node.getPrefix();

        if (!Strings.isEmpty(namespaceURI) && !Strings.isEmpty(prefix)) {

            processNameSpacePair(namespaceURI, prefix);

        } else if (!Strings.isEmpty(namespaceURI) && Strings.isEmpty(prefix)) {

            // default namespace
            if (!_uriToPrefixMap.containsKey(namespaceURI)) {

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

    private void processNameSpacePair(final String namespaceURI, final String prefix) {
        if (!_prefixToURIMap.containsKey(prefix)) {
            System.out.println("found namespace -> [" + prefix + ":" + namespaceURI + "]");
            _prefixToURIMap.put(prefix, namespaceURI);
            _uriToPrefixMap.put(namespaceURI, prefix);
        }
    }

}
