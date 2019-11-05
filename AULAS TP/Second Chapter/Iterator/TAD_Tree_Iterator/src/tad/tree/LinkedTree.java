package tad.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Utilizador
 * @param <E>
 * @param
 */
public class LinkedTree<E> implements Tree<E> {

    private TreeNode root;

    public LinkedTree() {
     this.root=null;
    }

   
    public LinkedTree(E root) {
        this.root = new TreeNode(root);

    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return size(root);
        }
    }

    public int size(TreeNode treeNode) {
       
        if (treeNode.children.isEmpty()) {
            return 1;
        } else {
            int s = 1;
            for (TreeNode child : treeNode.children) {
                s += size(child);
            }
            return s;
        }

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public E replace(Position<E> v, E e) throws InvalidPositionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position<E> root() throws EmptyTreeException {
        return root;

    }

    @Override
    public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
        TreeNode node = checkPosition(v);
        return node.parent;

    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
        TreeNode node = checkPosition(v);
        ArrayList<Position<E>> list = new ArrayList<>();
        for (Position<E> pos : node.children) {
            list.add(pos);
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        TreeNode aux = checkPosition(v);
        return !aux.children.isEmpty() && aux != this.root;
    }

    @Override
    public boolean isExternal(Position<E> v) throws InvalidPositionException {
        TreeNode aux = checkPosition(v);
        return aux.children.isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        TreeNode aux = checkPosition(v);
        return this.root == aux;

    }

    @Override
    public Position<E> insert(Position<E> parent, E elem)
            throws BoundaryViolationException, InvalidPositionException {

        TreeNode aux = checkPosition(parent);
        return insert(parent, elem, aux.children.size());
    }

    @Override
    public Position<E> insert(Position<E> parent, E elem, int order)
            throws BoundaryViolationException, InvalidPositionException {
        TreeNode aux = checkPosition(parent);
        if (order < 0 || order > aux.children.size()) {
            throw new BoundaryViolationException("Fora de limites");
        }
        return aux.addChild(elem, order);

    }

    @Override
    public E remove(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        E elem = aux.element;
        aux.parent.removeChild(aux);

        return elem;
    }

    public Iterable<Position<E>> breadthOrder() {
        List<TreeNode> nodeQueue = new LinkedList<>();
        List<Position<E>> elements = new LinkedList<>();
        if (isEmpty()) {
            return elements;
        }
        nodeQueue.add(this.root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove(0);
            elements.add(node);
            for (TreeNode child : node.children) {
                nodeQueue.add(child);
            }
        }

        return elements;
    }

    public Iterable<Position<E>> depthOrder() {
        List<TreeNode> nodeStack = new LinkedList<>();
        List<Position<E>> elements = new LinkedList<>();
        if (isEmpty()) {
            return elements;
        }
        nodeStack.add(0, this.root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.remove(0);
            elements.add(node);
            for (TreeNode child : node.children) {
                nodeStack.add(0, child);
            }
        }
        return elements;
    }

    private TreeNode checkPosition(Position<E> v)
            throws InvalidPositionException {
        if (v == null) {
            throw new InvalidPositionException();
        }

        try {
            TreeNode treeNode = (TreeNode) v;
            if (treeNode.children == null) {
                throw new InvalidPositionException("The position is invalid");
            }
            return treeNode;
        } catch (ClassCastException e) {
            throw new InvalidPositionException();
        }
    }

    @Override
    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> lista = new ArrayList<>();
        if (!isEmpty()) {
            positions(root, lista);
        }
        return lista;
    }

    private void elements(Position<E> v, ArrayList<E> lista) {

        lista.add(lista.size(), v.element()); // visit (v) primeiro, pre-order
        for (Position<E> w : children(v)) {
            elements(w, lista);
        }

    }

    @Override
    public Iterable<E> elements() {
        ArrayList<E> lista = new ArrayList<>();
        if (!isEmpty()) {
            elements(root, lista);
        }
        return lista;
    }

    private void positions(Position<E> v, ArrayList<Position<E>> lista) {

        for (Position<E> w : children(v)) {
            positions(w, lista);
        }
        lista.add(lista.size(), v); // visit (v)
    }

    public int heightS() {
        int h = 0;
        if (!isEmpty()) {
            h = height(root, 1);
        }

        return h;
    }

    private int height(TreeNode treeNode, int nivel) {
        if (isExternal(treeNode)) {
            return nivel;
        }
        int current = nivel;
        for (TreeNode child : treeNode.children) {
            current = Math.max(current, height(child, nivel + 1));
        }
        return current;
    }

    private int height(TreeNode treeNode) {
        if (isExternal(treeNode)) {
            return 1;
        }
        int maximo = 0;
       for (TreeNode child : treeNode.children) {
            int h = height(child);
            if (maximo < h) {
                maximo = h;
            }
        }
        return 1 + maximo;

    }

    public int height() {
        int h = 0;
        if (!isEmpty()) {
            h = height(root);
        }
        return h;
    }

    private String toStringPreOrder(Position<E> v) {
        String str = v.element().toString(); // visit (v)
        for (Position<E> w : children(v)) {
            str += "," + toStringPreOrder(w);
        }
        return str;
    }

    public String toString() {
        String str = "";
        if (!isEmpty()) {
            str = toStringPreOrderLevels(root, 1);
        }

        return str;
    }

    private String toStringPreOrderLevels(Position<E> v, int level) {
        String str = v.element().toString(); // visit (v)
        for (Position<E> w : children(v)) {
            str += "\n" + printLevel(level) + toStringPreOrderLevels(w, level + 1);
        }
        return str;
    }

    private String printLevel(int level) {
        String str = "";
        for (int i = 0; i < level; i++) {
            str += "  ";
        }
        return str + "-";
    }

  
  

    private class TreeNode implements Position<E> {

        private E element;  // element stored at this node
        private TreeNode parent;  // adjacent node
        private List<TreeNode> children;  // children nodes

        TreeNode(E element) {
            this.element = element;
            parent = null;
            children = new ArrayList<>();
        }

        TreeNode(E element, TreeNode parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public E element() {
            if (element == null) {
                throw new InvalidPositionException();
            }
            return element;
        }

        TreeNode addChild(E elem, int order) {
            TreeNode node = new TreeNode(elem, this);
            children.add(order, node);
            return node;
        }

        void removeChild(TreeNode node) {
            if (!children.remove(node)) {
                throw new InvalidPositionException();
            }
        }

    }

}
