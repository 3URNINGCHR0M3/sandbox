package net.forje.tools.xml.diff;

import org.w3c.dom.Node;

public interface NodeVisitor {

    public void visit(Node node);

}
