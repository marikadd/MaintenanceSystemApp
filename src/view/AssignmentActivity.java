/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javax.swing.ImageIcon;

/**
 *
 * @author Group9
 */
public class AssignmentActivity extends javax.swing.JFrame {

    /** Creates new form AssignmentActivity */
    public AssignmentActivity() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(650,670);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInMaintainer = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableNotInMaintainer = new javax.swing.JTable();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jLabelAdd = new javax.swing.JLabel();
        jLabelTit1 = new javax.swing.JLabel();
        jLabelTit2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setForeground(new java.awt.Color(255, 204, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back_button.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelTitle.setFont(new java.awt.Font("Impact", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("ASSIGN ACTIVITY");

        jTableInMaintainer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Description", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableInMaintainer.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableInMaintainer);

        jTableNotInMaintainer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Description", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableNotInMaintainer.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableNotInMaintainer);

        jTextFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsernameActionPerformed(evt);
            }
        });

        jLabelUsername.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setText("Username");

        jButtonSearch.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonSearch.setText("Search");

        jLabelAdd.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add_button.png"))); // NOI18N
        jLabelAdd.setText("Add");
        jLabelAdd.setOpaque(true);

        jLabelTit1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTit1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTit1.setText("Activities Assigned");

        jLabelTit2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTit2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTit2.setText("Activities Not Assigned");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelUsername)
                            .add(jLabelTit1)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(345, 345, 345)
                                .add(jLabelAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabelTit2)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(41, 41, 41)
                                .add(jButtonSearch))
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(52, 52, 52))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabelTitle)
                        .add(116, 116, 116))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(25, 25, 25)
                .add(jLabelTitle)
                .add(36, 36, 36)
                .add(jLabelUsername)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButtonSearch))
                .add(36, 36, 36)
                .add(jLabelTit1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(39, 39, 39)
                .add(jLabelTit2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabelAdd)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsernameActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        setVisible(false);
        ManagementActivityArea activityArea = new ManagementActivityArea();
        activityArea.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(AssignmentActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignmentActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignmentActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignmentActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignmentActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAdd;
    private javax.swing.JLabel jLabelTit1;
    private javax.swing.JLabel jLabelTit2;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableInMaintainer;
    private javax.swing.JTable jTableNotInMaintainer;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

}
