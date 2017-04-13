/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editors;

import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author mishuk
 */
public class ImpLineNumModel {

    private JTextArea textArea = new JTextArea();
    private JScrollPane scroller = new JScrollPane();
    private LineNumberModelImpl lineNumberModel = new LineNumberModelImpl();
    private LineNumberMain lineNumberComponent = new LineNumberMain(lineNumberModel);

    public void getingLineNumber(JTextArea tarea, JScrollPane scrollPane) {
        textArea = tarea;
        scroller = scrollPane;

        scroller.setRowHeaderView(lineNumberComponent);
        lineNumberComponent.setAlignment(LineNumberMain.CENTER_ALIGNMENT);

        tarea.getDocument().addDocumentListener(new DocumentListener() {

            @Override

            public void changedUpdate(DocumentEvent arg0) {
                lineNumberComponent.adjustWidth();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                lineNumberComponent.adjustWidth();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                lineNumberComponent.adjustWidth();
            }
        });
        if(tarea.getText().equals("")){
            tarea.setText(" ");
            tarea.setCaretPosition(0);
        }
        
    }

    class LineNumberModelImpl implements ILineNumModel {

        @Override
        public int getNumberLines() {
            try {
                return textArea.getLineCount();
            } catch (Exception e) {
                //e.printStackTrace();//Null-poniter Exception
                return 0;
            }
        }

        @Override
        public Rectangle getLineRect(int line) {
                //return textArea.modelToView(textArea.getLineStartOffset(line));
            try {
                return textArea.modelToView(textArea.getLineStartOffset(line));
            } catch (BadLocationException e) {
                e.printStackTrace();
                return new Rectangle();
            }
        }
    }
}
