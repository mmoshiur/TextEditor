/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mishuk
 */
public class FindAndReplace extends javax.swing.JDialog implements ActionListener{

    
    boolean foundOne, isReplace;
    /**
     * Creates new form FindAndReplace
     */
    public FindAndReplace(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        direction = new javax.swing.ButtonGroup();
        this.isReplace = isReplace;
        north = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        replaceText = new javax.swing.JTextField();
        center = new javax.swing.JPanel();
        east = new javax.swing.JPanel();
        cbCase = new javax.swing.JCheckBox();
        cbWhole = new javax.swing.JCheckBox();
        west = new javax.swing.JPanel();
        up = new javax.swing.JRadioButton();
        down = new javax.swing.JRadioButton();
        south = new javax.swing.JPanel();
        statusInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        north.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Find What: ");

        jLabel2.setText("Replace With: ");

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });
        if(searchText.getText().length()>0){
            NEXT.setEnabled(true);
        }

        replaceText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                replaceTextKeyReleased(evt);
            }
        });

        NEXT.setText("Find Next");
        NEXT.addActionListener(this);
        NEXT.setEnabled(false);

        REPLACE.setText("Replace");
        REPLACE.addActionListener(this);
        REPLACE.setEnabled(false);

        javax.swing.GroupLayout northLayout = new javax.swing.GroupLayout(north);
        north.setLayout(northLayout);
        northLayout.setHorizontalGroup(
            northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(northLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(northLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(replaceText, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NEXT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(REPLACE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        northLayout.setVerticalGroup(
            northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(northLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NEXT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(northLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(replaceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REPLACE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        east.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Option"));

        cbCase.setSelected(true);
        cbCase.setText("Match Case");

        cbWhole.setSelected(true);
        cbWhole.setText("Match Word");

        javax.swing.GroupLayout eastLayout = new javax.swing.GroupLayout(east);
        east.setLayout(eastLayout);
        eastLayout.setHorizontalGroup(
            eastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eastLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCase)
                    .addComponent(cbWhole))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        eastLayout.setVerticalGroup(
            eastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eastLayout.createSequentialGroup()
                .addComponent(cbCase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbWhole)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        west.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Direction"));

        direction.add(up);
        up.setText("Up");

        direction.add(down);
        down.setSelected(true);
        down.setText("Down");

        javax.swing.GroupLayout westLayout = new javax.swing.GroupLayout(west);
        west.setLayout(westLayout);
        westLayout.setHorizontalGroup(
            westLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(westLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(westLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(up)
                    .addComponent(down))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        westLayout.setVerticalGroup(
            westLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(westLayout.createSequentialGroup()
                .addComponent(up)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(down)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout centerLayout = new javax.swing.GroupLayout(center);
        center.setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(east, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(west, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        centerLayout.setVerticalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(west, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(centerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(east, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        south.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        statusInfo.setText("Status Bar");

        javax.swing.GroupLayout southLayout = new javax.swing.GroupLayout(south);
        south.setLayout(southLayout);
        southLayout.setHorizontalGroup(
            southLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(statusInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        southLayout.setVerticalGroup(
            southLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusInfo)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(north, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(south, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(center, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(north, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(center, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(south, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        // searchText keyReleased:
        boolean state = searchText.getDocument().getLength() > 0;
        NEXT.setEnabled(state);
        foundOne = false;
        
    }//GEN-LAST:event_searchTextKeyReleased

    private void replaceTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_replaceTextKeyReleased
        // replaceText keyReleased:
        boolean state = replaceText.getDocument().getLength() > 0;
        NEXT.setEnabled(state);
        REPLACE.setEnabled(state);
        
    }//GEN-LAST:event_replaceTextKeyReleased
    
    @Override
    public void actionPerformed(ActionEvent e) {   // call for replace
        
        if(e.getSource().equals(replaceText) || e.getSource().equals(searchText)){
            validate();
        }
        process();
        if(e.getActionCommand().equals("Replace All")){
            replaceAll();
        }
    }
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    private void process(){
        if(isReplace){
            statusInfo.setText("Replacing with " + searchText.getText());
        }else{
            statusInfo.setText("Searching for " + searchText.getText());
        }
        String text = EditorMain.getArea().getText();
        String word = searchText.getText();
        int caret = EditorMain.getArea().getCaretPosition();
        caret = search(text, word, caret);
        if(caret<0){
            endResult(false, 0);
        }
    }
    private void endResult(boolean isReplaceAll, int tally){  // done
        String message = "";
        if(isReplaceAll){
            if(tally == 0){
                message = searchText.getText()+ " Not Found";
            }else if(tally == 1){
                message = "One change made to " + searchText.getText();
            }else{
                message = "" + tally + " changes was made to "+searchText.getText();
            }
        }else{
            String str = "";
            if(isSearchDown()){
                str = "Search Down";
            }else{
                str = "Search Up";
            }
            if(foundOne && !isReplace){
                message = "End of "+str+" for "+searchText.getText();
            }else if(foundOne && isReplace){
                message = "End of Replacing for "+ searchText.getText()+ " with "+replaceText.getText();
            }
        }
        
    }
    private int search(String text, String word, int caret){  // Done
        boolean found = false;
        int all = text.length();
        int check = word.length();
        
        if(isSearchDown()){
            int add = 0;
            for(int i=(caret+1); i<(all-check); i++){
                String temp = text.substring(i, i+check);
                if(temp.equals(word)){
                    if(wholeWordIsSelected()){
                        if(checkForWholeWord(check, text, add, caret)){
                            found = true;
                            caret = i;
                            break;
                        }
                    }else{
                        found = true;
                        caret = i;
                        break;
                    }
                }
            }
        }else{
            int add = caret;
            for(int i=(caret-1); i>=check ; i--){
                add--;
                String temp = text.substring(i-check, i);
                if(temp.equals(word)){
                    if(wholeWordIsSelected()){
                        if(checkForWholeWord(check, text, add, caret)){
                            found = true;
                            caret = i;
                            break;
                        }
                    }else{
                        found = true;
                        caret = i;
                        break;
                    }
                }
            }
        }
        EditorMain.getArea().setCaretPosition(0);
        if(found){
            EditorMain.getArea().requestFocus();
            if(isSearchDown()){
                EditorMain.getArea().select(caret, caret+check);
            }else{
                EditorMain.getArea().select(caret-check, caret);
            }
            
            if(isReplace){
                String replace = replaceText.getText();
                EditorMain.getArea().replaceSelection(replace);
                if(isSearchDown()){
                    EditorMain.getArea().select(caret, caret+replace.length());
                }else{
                    EditorMain.getArea().select(caret-replace.length(), caret);
                }
            }
            
            foundOne = true;
            return caret;
        }
        return -1;
    }
    
    private String getWord(){
        if(caseNotSelected()){
            return searchText.getText().toLowerCase();
        }
        return searchText.getText();
    }
    private String getAllText(){
        if(caseNotSelected()){
            return EditorMain.getArea().getText().toLowerCase();
        }
        return EditorMain.getArea().getText();
    }
    private boolean isSearchDown(){
        return down.isSelected();
    }
    private boolean caseNotSelected(){
        return !cbCase.isSelected();
    }
    private boolean wholeWordIsSelected(){
        return cbWhole.isSelected();
    }
    private boolean checkForWholeWord(int check, String text, int add, int caret){
        int offsetLeft = (caret+add)-1;
        int offsetRight = (caret+add)+check;
        
        if((offsetLeft<0) || (offsetRight>text.length())){
            return true;
        }
        
        return (!Character.isLetterOrDigit(text.charAt(offsetLeft)) && !Character.isLetterOrDigit(text.charAt(offsetRight)));
    }
    private void replaceAll(){      // For replace
        String word = searchText.getText();
        String insert = replaceText.getText();
        String text = EditorMain.getArea().getText();
        
        int all = text.length();
        int check = word.length();
        int diff = insert.length() - word.length();
        int offset = 0;
        int tally = 0;
        
        StringBuffer sb = new StringBuffer(text);
        for(int i=0; i<(all-check); i++){
            String temp = text.substring(i, i+check);
            if(temp.equals(word) && checkForWholeWord(check, text, 0, i)){
                tally++;
                sb.replace(i+offset, i+offset+check, insert);
                offset += diff;
            }
        }
        EditorMain.getArea().setText(sb.toString());
        endResult(true, tally);
        EditorMain.getArea().setCaretPosition(0);
        
    }
    
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FindAndReplace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindAndReplace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindAndReplace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindAndReplace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FindAndReplace dialog = new FindAndReplace(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton NEXT = new javax.swing.JButton();
    private final javax.swing.JButton REPLACE = new javax.swing.JButton();
    private javax.swing.JCheckBox cbCase;
    private javax.swing.JCheckBox cbWhole;
    private javax.swing.JPanel center;
    private javax.swing.ButtonGroup direction;
    private javax.swing.JRadioButton down;
    private javax.swing.JPanel east;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel north;
    private javax.swing.JTextField replaceText;
    private javax.swing.JTextField searchText;
    private javax.swing.JPanel south;
    private javax.swing.JLabel statusInfo;
    private javax.swing.JRadioButton up;
    private javax.swing.JPanel west;
    // End of variables declaration//GEN-END:variables
}
