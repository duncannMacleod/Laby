// import org.apache.commons.collections15.Transformer;
// import org.apache.commons.collections15.functors.ConstantTransformer;
// import edu.uci.ics.jung.algorithms.layout.*;
// import edu.uci.ics.jung.graph.*;
// import edu.uci.ics.jung.visualization.*;
// import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
// import java.awt.*;
// import javax.swing.*;

// public class GraphVisualization {

//     public static void main(String[] args) {
//         int numVertices = 8;

//         // Création du graphe
//         Graph<Integer, String> graph = new SparseMultigraph<>();
//         for (int i = 0; i < numVertices; i++) {
//             graph.addVertex(i);
//         }

//         // Ajout des arêtes pour créer le graphe
//         graph.addEdge("Edge1", 0, 1);
//         graph.addEdge("Edge2", 0, 2);
//         graph.addEdge("Edge3", 1, 3);
//         graph.addEdge("Edge4", 1, 4);
//         graph.addEdge("Edge5", 2, 5);
//         graph.addEdge("Edge6", 2, 6);
//         graph.addEdge("Edge7", 4, 7);

//         // Création du layout pour le graphe
//         Layout<Integer, String> layout = new CircleLayout<>(graph);
//         layout.setSize(new Dimension(300, 300));

//         // Création de la fenêtre graphique
//         VisualizationViewer<Integer, String> vv = new VisualizationViewer<>(layout);
//         vv.setPreferredSize(new Dimension(350, 350));

//         // Personnalisation des vertex labels
//         vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());

//         // Personnalisation des edges labels
//         vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());

//         // Affichage
//         JFrame frame = new JFrame("Graph Visualization");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.getContentPane().add(vv);
//         frame.pack();
//         frame.setVisible(true);
//     }
// }
