
package GUI;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class JpanelLoader {
    public void jPanelLoader(JPanel Main, JComponent setComponent) {
        Main.removeAll();

        GroupLayout layout = new GroupLayout(Main);
        Main.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(setComponent, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(setComponent, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        Main.revalidate();
        Main.repaint();
        System.gc();
    }
}
