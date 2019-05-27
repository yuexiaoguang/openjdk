package sun.jvm.hotspot.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.ui.tree.*;
import sun.jvm.hotspot.utilities.soql.*;

public class FindByQueryPanel extends SAPanel {
   private JTextArea           queryEditor;
   private JEditorPane         objectsEditor;
   private SOQLEngine          queryEngine;

   public FindByQueryPanel() {
      queryEngine = SOQLEngine.getEngine();
      HyperlinkListener hyperListener = new HyperlinkListener() {
                         public void hyperlinkUpdate(HyperlinkEvent e) {
                            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                               VM vm = VM.getVM();
                               OopHandle handle = vm.getDebugger().parseAddress(e.getDescription()).addOffsetToAsOopHandle(0);
                               showInspector(vm.getObjectHeap().newOop(handle));
                            }
                         }
                      };

      objectsEditor = new JEditorPane();
      objectsEditor.setContentType("text/html");
      objectsEditor.setEditable(false);
      objectsEditor.addHyperlinkListener(hyperListener);

      queryEditor = new JTextArea();
      JButton queryButton = new JButton("Execute");
      queryButton.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent ae) {
                                          final StringBuffer buf = new StringBuffer();
                                          buf.append("<html><body>");
                                          try {
                                             queryEngine.executeQuery(queryEditor.getText(),
                                                        new ObjectVisitor() {
                                                           public void visit(Object o) {
                                                              if (o != null && o instanceof JSJavaObject) {
                                                                 String oopAddr = ((JSJavaObject)o).getOop().getHandle().toString();
                                                                 buf.append("<a href='");
                                                                 buf.append(oopAddr);
                                                                 buf.append("'>");
                                                                 buf.append(oopAddr);
                                                                 buf.append("</a>");
                                                              } else {
                                                                 buf.append((o == null)? "null" : o.toString());
                                                              }
                                                              buf.append("<br>");
                                                           }
                                                        });

                                          } catch (Exception e) {
                                             e.printStackTrace();
                                             buf.append("<b>");
                                             buf.append(e.getMessage());
                                             buf.append("</b>");
                                          }
                                          buf.append("</body></html>");
                                          objectsEditor.setText(buf.toString());
                                       }
                                   });

      JPanel topPanel = new JPanel();
      topPanel.setLayout(new BorderLayout());
      topPanel.add(new JLabel("SOQL Query :"), BorderLayout.WEST);
      topPanel.add(new JScrollPane(queryEditor), BorderLayout.CENTER);
      topPanel.add(queryButton, BorderLayout.EAST);

      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new BorderLayout());
      bottomPanel.add(new JScrollPane(objectsEditor), BorderLayout.CENTER);

      JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
      splitPane.setDividerLocation(0.3);

      setLayout(new BorderLayout());
      add(splitPane, BorderLayout.CENTER);
   }
}
