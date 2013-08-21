package net.forje.tools.xml.diff;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class App {

    public static void main(String[] args) {

        String lhsFileName = args[0];
        String rhsFileName = args[1];

        File lhsFile = new File(lhsFileName);
        File rhsFile = new File(rhsFileName);

        int totalExpressions = 0;
        int matches = 0;

        try {

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            final DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            final Document lhsDoc = documentBuilder.parse(lhsFile);
            lhsDoc.normalizeDocument();
            preprocessDocument(lhsDoc);

            final Document rhsDoc = documentBuilder.parse(rhsFile);
            rhsDoc.normalizeDocument();
            preprocessDocument(rhsDoc);

            final DocumentWalker lhsWalker = new DocumentWalker(lhsDoc);
            final VisitingNamespaceContext visitingNamespaceContext = new VisitingNamespaceContext();

            final XPathNodeVisitor visitor = new XPathNodeVisitor();
            lhsWalker.add(visitor);
            lhsWalker.add(visitingNamespaceContext);
            lhsWalker.process();

            final XPathFactory xPathFactory = XPathFactory.newInstance();
            final XPath xPath = xPathFactory.newXPath();

            xPath.setNamespaceContext(visitingNamespaceContext);

            final List<MatchingData> expressions = visitor.getExpressions();

            for (Iterator iterator = expressions.iterator(); iterator.hasNext(); ) {

                final MatchingData matchingData = (MatchingData) iterator.next();

                totalExpressions++;

                final String expression = matchingData.getExpression();
                final XPathExpression pathExpression = xPath.compile(expression);
                final Object object = pathExpression.evaluate(rhsDoc, XPathConstants.NODE);

                if (object == null) {
                    System.out.println(expression);
                    System.out.println("no match");
                } else {
                    matches++;
                    Node matchingNode = (Node) object;
                    matchingData.setMatchingNode(matchingNode);
                }

            }

            System.out.println("totalExpressions = " + totalExpressions);
            System.out.println("matches = " + matches);

            for (Iterator<MatchingData> iterator = expressions.iterator(); iterator.hasNext(); ) {
                MatchingData matchingData = iterator.next();
                if (matchingData.isMatched()) {
                    matchingData.compare();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void preprocessDocument(final Document document) throws Exception {

        DocumentWalker documentWalker = new DocumentWalker(document);

        documentWalker.add(new NormalizingVisitor());

        documentWalker.process();

    }
}
