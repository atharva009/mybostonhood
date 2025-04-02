package util.ADTTreeMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Neighborhood;

public class NeighborhoodSorter implements INeighborhoodSorter {
	
	// Internal BST Node
    private class TreeNode {
        Neighborhood neighborhood;
        TreeNode left, right;

        TreeNode(Neighborhood neighborhood) {
            this.neighborhood = neighborhood;
        }
    }

    private final Comparator<Neighborhood> comparator;
    private TreeNode root;

    public NeighborhoodSorter(Comparator<Neighborhood> comparator) {
        this.comparator = comparator;
    }

    /**
     * Inserts a neighborhood into the BST based on the provided comparator.
     */
    public void insertNeighborhood(Neighborhood neighborhood) {
        root = insertRecursive(root, neighborhood);
    }

    /**
     * Returns all neighborhoods in sorted order based on the comparator.
     */
    public List<Neighborhood> getSortedNeighborhoods() {
        List<Neighborhood> sortedList = new ArrayList<>();
        inOrderTraversal(root, sortedList);
        return sortedList;
    }

    /**
     * Prints all neighborhoods in sorted order to the console (for debugging).
     */
    public void displaySortedNeighborhoods() {
        for (Neighborhood n : getSortedNeighborhoods()) {
            System.out.println(n);
        }
    }

    // ---------- Internal Recursive Methods ----------

    private TreeNode insertRecursive(TreeNode node, Neighborhood n) {
        if (node == null) return new TreeNode(n);

        if (comparator.compare(n, node.neighborhood) < 0) {
            node.left = insertRecursive(node.left, n);
        } else {
            node.right = insertRecursive(node.right, n);
        }

        return node;
    }

    private void inOrderTraversal(TreeNode node, List<Neighborhood> result) {
        if (node == null) return;
        inOrderTraversal(node.left, result);
        result.add(node.neighborhood);
        inOrderTraversal(node.right, result);
    }

}
