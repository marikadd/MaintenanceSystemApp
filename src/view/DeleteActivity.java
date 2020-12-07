/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Activity.MaintenanceActivity;

/**
 *
 * @author Group9
 */

public class DeleteActivity extends javax.swing.JFrame {
    
    private List<MaintenanceActivity> activityList = new LinkedList<MaintenanceActivity>();
    private ActivityService activity = ActivityService.getActivityService();

    /** Creates new form ActivityDelete */
    public DeleteActivity() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(550,450);
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
        jLabelBack = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelExit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableActivities = new javax.swing.JTable();
        jButtonList = new javax.swing.JButton();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelDelete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setForeground(new java.awt.Color(255, 204, 0));

        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back_button.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelTitle.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("DELETE ACTIVITY");

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.setText("jLabel2");
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        jTableActivities.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Description", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableActivities);

        jButtonList.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonList.setText("List");
        jButtonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonListMouseClicked(evt);
            }
        });

        jLabelId.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelId.setForeground(new java.awt.Color(255, 255, 255));
        jLabelId.setText("ID");

        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        jLabelDelete.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDelete.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/del_button.png"))); // NOI18N
        jLabelDelete.setText("Delete");
        jLabelDelete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelDelete.setOpaque(true);
        jLabelDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDeleteMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabelTitle)
                        .add(119, 119, 119))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelId)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 381, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jTextFieldId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(57, 57, 57)
                                .add(jLabelDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(34, 34, 34))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jButtonList)
                        .add(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34)
                .add(jLabelTitle)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonList)
                .add(21, 21, 21)
                .add(jLabelId)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
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

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed

    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        setVisible(false);
        ManagementActivityArea activityArea = new ManagementActivityArea();
        activityArea.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jLabelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitMouseClicked

    private void jButtonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonListMouseClicked
      
        try {
            activityList = activity.getAllActivities();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ActivityNotFoundException ex) {
            Logger.getLogger(DeleteActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.showActivities(activityList);
        activityList = null;
    }//GEN-LAST:event_jButtonListMouseClicked

    private void jLabelDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDeleteMouseClicked
        int ID= Integer.parseInt(jTextFieldId.getText());
     
        try {
            int result = activity.deleteActivity(ID);
            if(result > 0){ 
                JOptionPane.showMessageDialog(null, "Activity deleted successfully!");
            }
            else{ 
                JOptionPane.showMessageDialog(null, "No activity deleted!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database internal error");
        } catch (UnsuccessfulUpdateException ex) {
            JOptionPane.showMessageDialog(null, "Cannot delete this activity");
        }
    }//GEN-LAST:event_jLabelDeleteMouseClicked

    public void showActivities(List<MaintenanceActivity> list){
        
        DefaultTableModel model = (DefaultTableModel) jTableActivities.getModel();
        int length = model.getRowCount();
        
        if(model.getRowCount()!=0){
            for(int i=0;i<length;i++){
                model.removeRow(0);
            }
        }
        
        for(int i=0;i<list.size();i++){
            Object column[] =new Object[4];
            column[0] = list.get(i).getID();
            column[1] = list.get(i).getType();
            column[2] = list.get(i).getDescription();
            column[3] = list.get(i).getTime();
            model.addRow(column);
        }      
    }
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
            java.util.logging.Logger.getLogger(DeleteActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonList;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDelete;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableActivities;
    private javax.swing.JTextField jTextFieldId;
    // End of variables declaration//GEN-END:variables

}
