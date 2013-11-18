package net.forje.tools.xml.diff;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class App {

    private final File _lhsFile;
    private final File _rhsFile;
    private Document _lhsDoc;
    private Document _rhsDoc;

    public App(final File lhsFile,
               final File rhsFile) {
        _lhsFile = lhsFile;
        _rhsFile = rhsFile;
    }

    public static void main(String[] args) {

        String lhsFileName = args[0];
        String rhsFileName = args[1];

        File lhsFile = new File(lhsFileName);
        File rhsFile = new File(rhsFileName);

        App app = new App(lhsFile, rhsFile);

        app.diffFiles();

    }

    private void diffFiles() {


        try {

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            final DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            _lhsDoc = documentBuilder.parse(_lhsFile);
            _lhsDoc.normalizeDocument();
            preprocessDocument(_lhsDoc);

            _rhsDoc = documentBuilder.parse(_rhsFile);
            _rhsDoc.normalizeDocument();
            preprocessDocument(_rhsDoc);

            final DocumentWalker lhsWalker = new DocumentWalker(_lhsDoc);

            final VisitingNamespaceContext visitingNamespaceContext = new VisitingNamespaceContext();

            final XPathNodeVisitor visitor = new XPathNodeVisitor();
            lhsWalker.add(visitor);
            lhsWalker.add(visitingNamespaceContext);
            lhsWalker.process();


            final List<MatchingData> expressions = visitor.getExpressions();
            if (expressions.size() > 0) {
                executeXpathExpressions(expressions, visitingNamespaceContext);
            }

            descendTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void descendTree() {

        final Node lhsRoot = _lhsDoc.getFirstChild();
        final Node rhsRoot = _lhsDoc.getFirstChild();

        NodeComparator nodeComparator = new NodeComparator(lhsRoot, rhsRoot);
        nodeComparator.compare();

    }

    private void executeXpathExpressions(final List<MatchingData> expressions,
                                         final VisitingNamespaceContext visitingNamespaceContext)
            throws XPathExpressionException {

        System.out.println("processing [" + expressions.size() + "] xpath expressions");

        int totalExpressions = 0;
        int matches = 0;

        for (Iterator iterator = expressions.iterator(); iterator.hasNext(); ) {

            final XPathFactory xPathFactory = XPathFactory.newInstance();
            final XPath xPath = xPathFactory.newXPath();
            xPath.setNamespaceContext(visitingNamespaceContext);

            final MatchingData matchingData = (MatchingData) iterator.next();

            totalExpressions++;

            final String expression = matchingData.getExpression();
            final XPathExpression pathExpression = xPath.compile(expression);
            final Object object = pathExpression.evaluate(_rhsDoc, XPathConstants.NODE);

            if (object == null) {
                System.out.println();
                System.out.println(expression);
                System.out.println("no match");
            } else {
                matches++;
                Node matchingNode = (Node) object;
                matchingData.setMatchingNode(matchingNode);
            }

        }

        System.out.println();
        System.out.println("totalExpressions = " + totalExpressions);
        System.out.println("matches = " + matches);

        for (Iterator<MatchingData> iterator = expressions.iterator(); iterator.hasNext(); ) {
            MatchingData matchingData = iterator.next();
            if (matchingData.isMatched()) {
                matchingData.compare();
            }
        }
    }

    private static void preprocessDocument(final Document document) throws Exception {

        DocumentWalker documentWalker = new DocumentWalker(document);

        documentWalker.add(new NormalizingVisitor());

        documentWalker.process();

    }
}
