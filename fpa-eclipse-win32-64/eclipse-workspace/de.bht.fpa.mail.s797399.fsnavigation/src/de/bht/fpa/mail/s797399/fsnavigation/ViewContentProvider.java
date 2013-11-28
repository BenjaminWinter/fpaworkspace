package de.bht.fpa.mail.s797399.fsnavigation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ViewContentProvider implements ITreeContentProvider {

  /**
   * This method is called when the user expands a tree node in the View. The
   * parameter of the method is the selected item, and the method is expected to
   * return the direct children of the item.
   * 
   * @param parentElement
   *          the expanded element in the tree, for which the framework requests
   *          the children
   */
  @Override
  public Object[] getChildren(Object parentElement) {
    // For every parentElement, we return an empty array. That means that for
    // every given tree item, we say it has no children. Here you should cast
    // the parentElement to your own class and return its children.
    return ((File) parentElement).listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return new File(dir, name).isDirectory();
      }
    });
  }

  /**
   * This method is called when the user expands a tree node in the View. The
   * framework asks is the given element has any children. The parameter of the
   * method is a tree item, and the method is expected to return
   * <code>true></code> if the item has children, or <code>false</code> if it
   * has no children.
   * 
   * @param element
   *          a tree item, for which the framework wants to know if it has
   *          children
   */
  @Override
  public boolean hasChildren(Object element) {
    // Get the children
    File[] obj = (File[]) getChildren(element);
    if (obj == null) {
      return false;
    }
    boolean onlyfile = true;
    // Return whether the parent has children
    for (File o : obj) {
      if (o.isDirectory()) {
        onlyfile = false;
      }
    }
    return !onlyfile;
  }

  @Override
  public Object[] getElements(Object startdir) {
    File file = new File(System.getProperty("user.home") + "lastdir.txt");
    BufferedReader reader = null;
    String lastdir = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      lastdir = reader.readLine();

    } catch (FileNotFoundException e) {
      System.err.println("coulnt find file lastdir, propably first start");
    } catch (IOException e) {
      System.err.println("IOException while reading file lastdir");
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }
    Object input = lastdir == null ? startdir : lastdir;
    File[] userdirectory = new File((String) input).listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return new File(dir, name).isDirectory();
      }
    });
    return userdirectory;
  }

  @Override
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
  }

  @Override
  public void dispose() {

  }

  @Override
  public Object getParent(Object element) {
    return ((File) element).getParentFile();
  }

}