package de.bht.fpa.mail.s797399.fsnavigation;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class ViewLabelProvider extends LabelProvider {
  private Image file;

  private Image dir;

  public ViewLabelProvider() {

    try {
      file = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file_small.png").createImage();
      dir = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder_small.png").createImage();
    } catch (NullPointerException e) {
      MessageDialog.openError(new Shell(), "Error", "Could not find File");
    }

  }

  @Override
  public String getText(Object element) {
    // here you decide for each tree item which text to show. You usually do a
    // bunch on instanceof checks for every possible type in your tree.
    // Get the name of the file

    return ((File) element).getName();
  }

  @Override
  public Image getImage(Object element) {
    // here you decide for each tree item which icon to show. You usually do a
    // bunch on instanceof checks for every possible type in your tree.
    return ((File) element).isDirectory() ? dir : file;
  }

  @Override
  public void dispose() {
    // Dispose the images
    if (dir != null) {
      dir.dispose();
    }
    if (file != null) {
      file.dispose();
    }
  }
}
