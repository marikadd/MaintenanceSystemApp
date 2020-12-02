/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */

public class DeleteUser extends javax.swing.JFrame {

    private List<UserModel> userList = new LinkedList<UserModel>();
    /**
     * Creates new form DeleteUser
     */
    public DeleteUser() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(600,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextUsername = new javax.swing.JTextField();
        jLabelUsername = new javax.swing.JLabel();
        jButtonList = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jLabelTitle = new javax.swing.JLabel();
        jLabelDelete = new javax.swing.JLabel();
        jLabelExit = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jTextUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextUsernameActionPerformed(evt);
            }
        });

        jLabelUsername.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setText("Username");

        jButtonList.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButtonList.setText("List");
        jButtonList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListActionPerformed(evt);
            }
        });

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Name", "Surname", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableUsers);
        if (jTableUsers.getColumnModel().getColumnCount() > 0) {
            jTableUsers.getColumnModel().getColumn(0).setResizable(false);
            jTableUsers.getColumnModel().getColumn(1).setResizable(false);
            jTableUsers.getColumnModel().getColumn(2).setResizable(false);
            jTableUsers.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabelTitle.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("DELETE USER");

        jLabelDelete.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDelete.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
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

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonList, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsername)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabelDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(152, 152, 152))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabelTitle)
                .addGap(154, 154, 154))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabelTitle)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120))
        );

        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back_button.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelBack, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**/
    private void jButtonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListActionPerformed
        
        UserManagementService user = UserManagementService.getUserManagementService();
        try {
            userList = user.getAllUsers();
        } catch (SQLException | UsernotFoundException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.showUsers(userList);
        userList = null;
    }//GEN-LAST:event_jButtonListActionPerformed

    private void jTextUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextUsernameActionPerformed

    }//GEN-LAST:event_jTextUsernameActionPerformed

    private void jLabelDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDeleteMouseClicked
        String username= jTextUsername.getText();
        UserManagementService user= UserManagementService.getUserManagementService();
        
        try {
            int result=user.deleteUser(username);
            if(result!=0){
                JOptionPane.showMessageDialog(null, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "This user cannot be delete!");
            }
        } catch (InvalidParameterObjectException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database internal error");
        } catch (UnsuccessfulUpdateException ex) {
            JOptionPane.showMessageDialog(null, "Cannot delete this user");
        }
    }//GEN-LAST:event_jLabelDeleteMouseClicked

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        setVisible(false);
        ManagementUserArea dUser = new ManagementUserArea();
        dUser.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jLabelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitMouseClicked

    public void showUsers(List<UserModel> list){
        
        DefaultTableModel model = (DefaultTableModel) jTableUsers.getModel();
        int length = model.getRowCount();
        
        if(model.getRowCount()!=0){
            for(int i=0;i<length;i++){
                model.removeRow(0);
            }
        }
        for(int i=0;i<list.size();i++){
            Object column[] =new Object[4];
            column[0] = list.get(i).getUsername();
            column[1] = list.get(i).getName();
            column[2] = list.get(i).getSurname();
            column[3] = list.get(i).getRole();
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
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonList;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDelete;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTextField jTextUsername;
    // End of variables declaration//GEN-END:variables
}
