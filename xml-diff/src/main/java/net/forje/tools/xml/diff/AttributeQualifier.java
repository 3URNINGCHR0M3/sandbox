package net.forje.tools.xml.diff;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collection;

public class AttributeQualifier {

    private final NamedNodeMap _attributes;

    public AttributeQualifier(final NamedNodeMap attributes) {
        _attributes = attributes;
    }

    public Collection<Node> process() {

        final Collection<Node> validNodes = new ArrayList<Node>();

        final int length = _attributes.getLength();

        for (int i = 0; i < length; i++) {
            Node item = _attributes.item(i);
            if (item.getNodeName().toLowerCase().contains("xmlns")) {
                continue;
            }

            validNodes.add(item);

        }

        return validNodes;

    }

}
