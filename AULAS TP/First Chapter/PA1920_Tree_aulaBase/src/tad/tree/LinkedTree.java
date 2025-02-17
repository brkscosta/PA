package tad.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author BRKsCosta
 * @param <E> Aceira qualquer tipo de dados genéricos
 */
public class LinkedTree<E> implements Tree<E> {

    private TreeNode root;

    public LinkedTree() {

        this.root = null;
    }

    public LinkedTree(E root) {
        this.root = new TreeNode(root);
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
    public E remove(Position<E> position) throws InvalidPositionException, NonEmptyTreeException {
        TreeNode aux = checkPosition(position);
        E elem = aux.element;
        aux.parent.removeChild(aux);

        return elem;
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

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode treeRoot) {

        if (treeRoot == null) {
            return 0;
        }

        int treeSize = 1;

        for (TreeNode child : treeRoot.children) {
            treeSize += size(child);
        }

        return treeSize;
    }

    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        TreeNode node = checkPosition(v);

        return (node != this.root && !node.children.isEmpty());
    }

    @Override
    public Iterable<E> depthOrder() {

        List<TreeNode> nodeStack = new LinkedList<>();
        List<E> elements = new LinkedList<>();
        if (isEmpty()) {
            return elements;
        }
        nodeStack.add(0, this.root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.remove(0);
            elements.add(node.element());
            for (TreeNode child : node.children) {
                nodeStack.add(0, child);
            }
        }
        return elements;
    }

    @Override
    public Iterable<E> breathOrder() {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<E> elements = new LinkedList<>();

        if (isEmpty()) {
            return elements;
        }

        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {

            TreeNode node = nodeQueue.poll();
            elements.offer(node.element());

            for (TreeNode child : node.children) {
                nodeQueue.offer(child);
            }

        }

        return elements;

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

        @Override
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
            //if( node.children.isEmpty()) 
            //  throw new NonEmptyTreeException("Sub Tree is not empty");
            if (!children.remove(node)) {
                throw new InvalidPositionException();
            }
        }

    }
    
}
