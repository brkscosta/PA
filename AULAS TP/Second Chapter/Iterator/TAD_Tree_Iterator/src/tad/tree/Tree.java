package tad.tree;
import java.util.Iterator;


// * An interface for a tree where nodes can have an arbitrary number of children.

public interface Tree<E> extends Iterable<E> {
  /** Returns the number of nodes in the tree. */
  public int size();
  /** Returns whether the tree is empty. */
  public boolean isEmpty();
  /** Returns an iterable collection of the elements. */
  public Iterable<Position<E>> positions();
   /** Returns an iterable collection of the elements of the tree. */
  public Iterable<E> elements();
  /** Replaces the element stored at a given node. */
  public E replace(Position<E> v, E e)
    throws InvalidPositionException;
  /** Returns the root of the tree. */
  public Position<E> root() throws EmptyTreeException;
  /** Returns the parent of a given node. */
  public Position<E> parent(Position<E> v)
    throws InvalidPositionException, BoundaryViolationException;
  /** Returns an iterable collection of the children of a given node. */
  public Iterable<Position<E>> children(Position<E> v) 
    throws InvalidPositionException;
  /** Returns whether a given node is internal. */
  public boolean isInternal(Position<E> v) 
    throws InvalidPositionException;
  /** Returns whether a given node is external. */
  public boolean isExternal(Position<E> v) 
    throws InvalidPositionException;
  /** Returns whether a given node is the root of the tree. */
  public boolean isRoot(Position<E> v)   throws InvalidPositionException;  
  public Position<E> insert(Position<E> parent, E elem, int order)throws InvalidPositionException,BoundaryViolationException;
  public Position<E> insert(Position<E> parent, E elem) throws InvalidPositionException,BoundaryViolationException;
  public E remove(Position<E> position)throws InvalidPositionException; 
  
  
}

