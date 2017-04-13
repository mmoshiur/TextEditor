/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editors;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.StringTokenizer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
/**
 *
 * @author mishuk
 */
public class EditorMain extends javax.swing.JFrame {

    ImpLineNumModel line = new ImpLineNumModel();
    
    JFileChooser fileChooser;
    String filename;
    String fileContents;
    
    UndoAction undoAction;
    RedoAction redoAction;
    UndoActionBtn undoActionBtn;
    RedoActionBtn redoActionBtn;
    
    UndoManager undoManager;
    
    FontHelper font;
    
    
    /**
     * Creates new form EditorMain
     */
    public EditorMain() {
        
        
        
        fileChooser = new JFileChooser(".");
        initComponents();
        line.getingLineNumber(mainArea, scroller);
        
        
        
        mainArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
                undoAction.update();
                redoAction.update();
                undoActionBtn.update();
                redoActionBtn.update();
            }
        });
        font.getOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.setFont(font.font());
                font.setVisible(false);
            }
        });
        font.getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                font.setVisible(false);
            }
        });
        
    }

    
    private void saveas() {
        PrintWriter fout = null;
        int option = -1;
        try {
            option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                fout = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()));
            }
            String txt = mainArea.getText();
            StringTokenizer token = new StringTokenizer(txt, System.getProperty("line.separator"));
            while (token.hasMoreElements()) {
                fout.println(token.nextToken());
            }
            filename = fileChooser.getSelectedFile().getName();
            fileContents = mainArea.getText();
            setTitle(filename = fileChooser.getSelectedFile().getName());
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void save() {
        PrintWriter fout = null;
        try {
            if (filename == null) {
                saveas();
            } else {
                fout = new PrintWriter(new FileWriter(filename));
                String txt = mainArea.getText();
                StringTokenizer token = new StringTokenizer(txt, System.getProperty("line.separator"));
                while (token.hasMoreElements()) {
                    fout.println(token.nextToken());
                }
                fileContents = mainArea.getText();
            }
        } catch (Exception e) {
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void open() {

        try {
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                mainArea.setText(null);
                Reader in = new FileReader(fileChooser.getSelectedFile());
                char[] buff = new char[100000];
                int nch;
                while ((nch = in.read(buff, 0, buff.length)) != -1) {
                    mainArea.append(new String(buff, 0, nch));
                }
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }
        filename = fileChooser.getSelectedFile().getName();
        fileContents = mainArea.getText();
        setTitle(filename = fileChooser.getSelectedFile().getName());
        
        
    }

    private void open_new() {
        /*if(filename == null){
            if(!mainArea.getText().equals("")){
                saveas();
                clear();
            }
            else{
                clear();
            }
        }else{
           // fileContents = mainArea.getText();
            if(!mainArea.getText().equals(fileContents)){
                saveas();
                clear();
            }else{
                clear();
            }
        }*/
        if (filename == null) {
            if (!mainArea.getText().equals("")) {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to save the changes?");
                if (option == 0) {
                    saveas();
                    clear();
                } else if (option == 2) {

                } else {
                    clear();
                }
            } else {
                clear();
            }
        } else{
            if (!mainArea.getText().equals(fileContents)) {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to save the changes?");
                if(option == 0){
                    save();
                    clear();
                }else if(option == 2){
                    
                }else{
                    clear();
                }
                
            } else {
                clear();
            }
        }

    }

    private void exitfrom() {
        /*if (filename == null) {
            if (!mainArea.getText().equals("")) {
                saveas();
                System.exit(0);
            } else {
                System.exit(0);
            }

        } else if (!mainArea.getText().equals(fileContents)) {
            saveas();
            System.exit(0);
        } else {
            System.exit(0);
        }*/
        if (filename == null) {
            if (!mainArea.getText().equals("")) {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want save the changes?");
                if(option == 0){
                    saveas();
                    System.exit(0);
                }else if(option == 2){
                    
                }else{
                    System.exit(0);
                }
                
            } else {
                System.exit(0);
            }

        } else if (!mainArea.getText().equals(fileContents)) {
            int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to save the changes?");
            if(option == 0){
                save();
                System.exit(0);
            }else if(option == 2){
                
            }else{
                System.exit(0);
            }
            
        } else {
            System.exit(0);
        }
        
    }

    private void clear() {
        mainArea.setText("");
        setTitle("Untitled - Notepad");
        filename = null;
        fileContents = null;
    }
    
    
    //-------------------- UndoAction and RedoAction class----------------------
    
    class UndoAction extends AbstractAction{
        
        public UndoAction(ImageIcon undoIcon){
            super("Undo", undoIcon);
            setEnabled(false);
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                undoManager.undo();
            } catch (CannotUndoException ee) {
                ee.printStackTrace();
            }
            update();
            redoAction.update();
        }
        protected void update(){
            if(undoManager.canUndo()){
                setEnabled(true);
                putValue(Action.NAME, "Undo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
    class RedoAction extends AbstractAction{
        
        public RedoAction(ImageIcon redoIcon){
            super("Redo", redoIcon);
            setEnabled(false);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                undoManager.redo();
            } catch (CannotRedoException ee) {
                ee.printStackTrace();
            }
            update();
            undoAction.update();
        }
        protected void update(){
            if(undoManager.canRedo()){
                setEnabled(true);
                putValue(Action.NAME, "Redo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
        
    }
    
    //-------------------UndoActionBtn and RedoActionBtn------------------------
    
    class UndoActionBtn extends AbstractAction{
        
        public UndoActionBtn(ImageIcon undoIcon){
            super("Undo", undoIcon);
            setEnabled(false);
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                undoManager.undo();
            } catch (CannotUndoException ee) {
                ee.printStackTrace();
            }
            update();
            redoActionBtn.update();
        }
        protected void update(){
            if(undoManager.canUndo()){
                setEnabled(true);
                putValue(Action.NAME, "Undo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
    class RedoActionBtn extends AbstractAction{
        
        public RedoActionBtn(ImageIcon redoIcon){
            super("Redo", redoIcon);
            setEnabled(false);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                undoManager.redo();
            } catch (CannotRedoException ee) {
                ee.printStackTrace();
            }
            update();
            undoActionBtn.update();
        }
        protected void update(){
            if(undoManager.canRedo()){
                setEnabled(true);
                putValue(Action.NAME, "Redo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
        
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_new = new javax.swing.JButton();
        btn_open = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_saveas = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btn_cut = new javax.swing.JButton();
        btn_copy = new javax.swing.JButton();
        btn_paste = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btn_find = new javax.swing.JButton();
        btn_findAndReplace = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        scroller = new javax.swing.JScrollPane();
        undoManager = new UndoManager();
        ImageIcon undoIcon = new ImageIcon(getClass().getResource("/icons/16/undo.png"));
        ImageIcon redoIcon = new ImageIcon(getClass().getResource("/icons/16/redo1.png"));
        undoAction = new UndoAction(undoIcon);
        redoAction = new RedoAction(redoIcon);
        ImageIcon undoIconbtn = new ImageIcon(getClass().getResource("/icons/24/undo.png"));
        ImageIcon redoIconbtn = new ImageIcon(getClass().getResource("/icons/24/redo1.png"));
        undoActionBtn = new UndoActionBtn(undoIconbtn);
        redoActionBtn = new RedoActionBtn(redoIconbtn);
        mainArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemNew = new javax.swing.JMenuItem();
        itemOpen = new javax.swing.JMenuItem();
        itemSave = new javax.swing.JMenuItem();
        itemSaveas = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemPrint = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemExit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuEdit.add(undoAction);
        menuEdit.add(redoAction);
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemCut = new javax.swing.JMenuItem();
        itemCopy = new javax.swing.JMenuItem();
        itemPaste = new javax.swing.JMenuItem();
        itemDelete = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemFind = new javax.swing.JMenuItem();
        itemReplace = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemSelectAll = new javax.swing.JMenuItem();
        menuFormat = new javax.swing.JMenu();
        itemWordWrap = new javax.swing.JCheckBoxMenuItem();
        itemFont = new javax.swing.JMenuItem();
        itemFontColor = new javax.swing.JMenuItem();
        menuWindow = new javax.swing.JMenu();
        menuView = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Untitled - Notepad");

        jToolBar1.setRollover(true);

        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/new2.png"))); // NOI18N
        btn_new.setFocusable(false);
        btn_new.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_new.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_new);

        btn_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/open1.png"))); // NOI18N
        btn_open.setFocusable(false);
        btn_open.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_open.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_open);

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/save2.png"))); // NOI18N
        btn_save.setFocusable(false);
        btn_save.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_save.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_save);

        btn_saveas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/saveas1.png"))); // NOI18N
        btn_saveas.setFocusable(false);
        btn_saveas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_saveas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveasActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_saveas);

        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/print.png"))); // NOI18N
        btn_print.setFocusable(false);
        btn_print.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_print.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_print);
        jToolBar1.add(jSeparator6);

        btn_cut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/cut1.png"))); // NOI18N
        btn_cut.setFocusable(false);
        btn_cut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cutActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_cut);

        btn_copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/copy2.png"))); // NOI18N
        btn_copy.setFocusable(false);
        btn_copy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_copy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_copyActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_copy);

        btn_paste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/paste1.png"))); // NOI18N
        btn_paste.setFocusable(false);
        btn_paste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_paste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pasteActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_paste);
        jToolBar1.add(jSeparator7);
        jToolBar1.add(undoActionBtn);
        jToolBar1.add(redoActionBtn);
        jToolBar1.add(jSeparator8);

        btn_find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/find.png"))); // NOI18N
        btn_find.setFocusable(false);
        btn_find.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_find.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_findActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_find);

        btn_findAndReplace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24/findAndReplace.png"))); // NOI18N
        btn_findAndReplace.setFocusable(false);
        btn_findAndReplace.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_findAndReplace.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_findAndReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_findAndReplaceActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_findAndReplace);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        font = new FontHelper(null, true);
        mainArea.setColumns(20);
        mainArea.setRows(5);
        scroller.setViewportView(mainArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroller)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroller, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );

        menuFile.setText("File");

        itemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/new2.png"))); // NOI18N
        itemNew.setText("New");
        itemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewActionPerformed(evt);
            }
        });
        menuFile.add(itemNew);

        itemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itemOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/open1.png"))); // NOI18N
        itemOpen.setText("Open");
        itemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOpenActionPerformed(evt);
            }
        });
        menuFile.add(itemOpen);

        itemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itemSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/save2.png"))); // NOI18N
        itemSave.setText("Save");
        itemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveActionPerformed(evt);
            }
        });
        menuFile.add(itemSave);

        itemSaveas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        itemSaveas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/saveas1.png"))); // NOI18N
        itemSaveas.setText("Save As..");
        itemSaveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveasActionPerformed(evt);
            }
        });
        menuFile.add(itemSaveas);
        menuFile.add(jSeparator1);

        itemPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        itemPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/print.png"))); // NOI18N
        itemPrint.setText("Print..");
        itemPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintActionPerformed(evt);
            }
        });
        menuFile.add(itemPrint);
        menuFile.add(jSeparator2);

        itemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/exit.png"))); // NOI18N
        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        menuFile.add(itemExit);

        jMenuBar1.add(menuFile);

        menuEdit.setText("Edit");
        menuEdit.add(jSeparator3);

        itemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itemCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/cut1.png"))); // NOI18N
        itemCut.setText("Cut");
        itemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCutActionPerformed(evt);
            }
        });
        menuEdit.add(itemCut);

        itemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/copy2.png"))); // NOI18N
        itemCopy.setText("Copy");
        itemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCopyActionPerformed(evt);
            }
        });
        menuEdit.add(itemCopy);

        itemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        itemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/paste1.png"))); // NOI18N
        itemPaste.setText("Paste");
        itemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPasteActionPerformed(evt);
            }
        });
        menuEdit.add(itemPaste);

        itemDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        itemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/delete.png"))); // NOI18N
        itemDelete.setText("Delete");
        itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeleteActionPerformed(evt);
            }
        });
        menuEdit.add(itemDelete);
        menuEdit.add(jSeparator4);

        itemFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/find.png"))); // NOI18N
        itemFind.setText("Find..");
        itemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFindActionPerformed(evt);
            }
        });
        menuEdit.add(itemFind);

        itemReplace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/findAndReplace.png"))); // NOI18N
        itemReplace.setText("Replace");
        itemReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReplaceActionPerformed(evt);
            }
        });
        menuEdit.add(itemReplace);
        menuEdit.add(jSeparator5);

        itemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemSelectAll.setText("Select All");
        itemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSelectAllActionPerformed(evt);
            }
        });
        menuEdit.add(itemSelectAll);

        jMenuBar1.add(menuEdit);

        menuFormat.setText("Format");

        itemWordWrap.setText("Word Wrap");
        itemWordWrap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWordWrapActionPerformed(evt);
            }
        });
        menuFormat.add(itemWordWrap);

        itemFont.setText("Font");
        itemFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFontActionPerformed(evt);
            }
        });
        menuFormat.add(itemFont);

        itemFontColor.setText("Font Color..");
        itemFontColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFontColorActionPerformed(evt);
            }
        });
        menuFormat.add(itemFontColor);

        jMenuBar1.add(menuFormat);

        menuWindow.setText("Window");
        jMenuBar1.add(menuWindow);

        menuView.setText("View");
        jMenuBar1.add(menuView);

        menuHelp.setText("Help");

        jMenuItem3.setText("View Help");
        menuHelp.add(jMenuItem3);

        jMenuItem4.setText("About..");
        menuHelp.add(jMenuItem4);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOpenActionPerformed
        // open menuItem:
        open();
    }//GEN-LAST:event_itemOpenActionPerformed

    private void itemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCutActionPerformed
        // cut menuItem:
        mainArea.cut();
    }//GEN-LAST:event_itemCutActionPerformed

    private void itemSaveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveasActionPerformed
        // Saveas menuItem:
        saveas();
    }//GEN-LAST:event_itemSaveasActionPerformed

    private void btn_saveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveasActionPerformed
        // Saveas button:
        saveas();
    }//GEN-LAST:event_btn_saveasActionPerformed

    private void itemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveActionPerformed
        // Save menuItem:
        save();
    }//GEN-LAST:event_itemSaveActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // Save Button:
        save();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openActionPerformed
        // open Button:
        open();
    }//GEN-LAST:event_btn_openActionPerformed

    private void itemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewActionPerformed
        // open new menuItem:
        open_new();
    }//GEN-LAST:event_itemNewActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        // open new button:
        open_new();
    }//GEN-LAST:event_btn_newActionPerformed

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        // Exit menuItem:
        exitfrom();
    }//GEN-LAST:event_itemExitActionPerformed

    private void btn_cutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cutActionPerformed
        // cut button:
        mainArea.cut();
    }//GEN-LAST:event_btn_cutActionPerformed

    private void itemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCopyActionPerformed
        // copy menuItem:
        mainArea.copy();
    }//GEN-LAST:event_itemCopyActionPerformed

    private void btn_copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_copyActionPerformed
        // copy button:
        mainArea.copy();
    }//GEN-LAST:event_btn_copyActionPerformed

    private void itemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPasteActionPerformed
        // paste menuItem:
        mainArea.paste();
    }//GEN-LAST:event_itemPasteActionPerformed

    private void btn_pasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pasteActionPerformed
        // paste button:
        mainArea.paste();
       
    }//GEN-LAST:event_btn_pasteActionPerformed

    private void itemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeleteActionPerformed
        // delete seleted text menuItem:
        mainArea.replaceSelection("");
    }//GEN-LAST:event_itemDeleteActionPerformed

    private void itemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSelectAllActionPerformed
        // Select All menuItem:
        //mainArea.requestFocusInWindow();//<----------
        mainArea.selectAll();
    }//GEN-LAST:event_itemSelectAllActionPerformed

    private void itemWordWrapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWordWrapActionPerformed
        // wordWrap menuItem:
        if (itemWordWrap.isSelected()) {
            mainArea.setLineWrap(true);
            mainArea.setWrapStyleWord(true);
        } else {
            mainArea.setLineWrap(false);
            mainArea.setWrapStyleWord(false);
        }
    }//GEN-LAST:event_itemWordWrapActionPerformed

    private void itemFontColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFontColorActionPerformed
        // Font Color menuItem:
        Color c = JColorChooser.showDialog(rootPane, "Choose Font Color", Color.RED);
        mainArea.setForeground(c);
    }//GEN-LAST:event_itemFontColorActionPerformed

    private void btn_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_findActionPerformed
        // TODO add your handling code here:
        new Find(null, true).setVisible(true);
    }//GEN-LAST:event_btn_findActionPerformed

    private void btn_findAndReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_findAndReplaceActionPerformed
        // TODO add your handling code here:
        new FindAndReplace(this, true).setVisible(true);
    }//GEN-LAST:event_btn_findAndReplaceActionPerformed

    private void itemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFindActionPerformed
        // find MenuItem:
        new Find(null, true).setVisible(true);
    }//GEN-LAST:event_itemFindActionPerformed

    private void itemReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReplaceActionPerformed
        // FindAndReplace MenuItem:
        new FindAndReplace(this, true).setVisible(true);
    }//GEN-LAST:event_itemReplaceActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // print button:
//        MessageFormat header = new MessageFormat("Report Print");
//        MessageFormat footer = new MessageFormat("Page{0, number, integer}");
//        try {
//            mainArea.print(header, footer);
//        } catch (java.awt.print.PrinterException e) {
//            System.err.format("Cannot print %s%n", e.getMessage());
//        }
        try {
            mainArea.print();
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
            
    }//GEN-LAST:event_btn_printActionPerformed

    private void itemPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintActionPerformed
        // print button:
//        MessageFormat header = new MessageFormat("Report Print");
//        MessageFormat footer = new MessageFormat("Page{0, number, integer}");
//        try {
//            mainArea.print(header, footer);
//        } catch (java.awt.print.PrinterException e) {
//            System.err.format("Cannot print %s%n", e.getMessage());
//        }
        
        try {
            mainArea.print();
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
        
    }//GEN-LAST:event_itemPrintActionPerformed

    private void itemFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFontActionPerformed
        // Font menuItem:
        font.setVisible(true);
    }//GEN-LAST:event_itemFontActionPerformed

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
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorMain().setVisible(true);
            }
        });
    }
    
    public static JTextArea getArea(){
        return mainArea;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_copy;
    private javax.swing.JButton btn_cut;
    private javax.swing.JButton btn_find;
    private javax.swing.JButton btn_findAndReplace;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_open;
    private javax.swing.JButton btn_paste;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_saveas;
    private javax.swing.JMenuItem itemCopy;
    private javax.swing.JMenuItem itemCut;
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemFind;
    private javax.swing.JMenuItem itemFont;
    private javax.swing.JMenuItem itemFontColor;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenuItem itemOpen;
    private javax.swing.JMenuItem itemPaste;
    private javax.swing.JMenuItem itemPrint;
    private javax.swing.JMenuItem itemReplace;
    private javax.swing.JMenuItem itemSave;
    private javax.swing.JMenuItem itemSaveas;
    private javax.swing.JMenuItem itemSelectAll;
    private javax.swing.JCheckBoxMenuItem itemWordWrap;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private static javax.swing.JTextArea mainArea;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuFormat;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuView;
    private javax.swing.JMenu menuWindow;
    private javax.swing.JScrollPane scroller;
    // End of variables declaration//GEN-END:variables
}
