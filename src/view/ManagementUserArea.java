package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Group9
 */
public class ManagementUserArea extends javax.swing.JFrame {

    /**
     * Creates new form ManagementUserArea.
     */
    public ManagementUserArea() {
        initComponents();
        
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(509, 486);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
            }
        });
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
        jButtonCreate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();
        jLabelExit = new javax.swing.JLabel();
        jButtonView = new javax.swing.JButton();
        jLabelMinimize = new javax.swing.JLabel();
        jLabelBack = new javax.swing.JLabel();
        jLabelIconCreate = new javax.swing.JLabel();
        jLabelIconUpdate = new javax.swing.JLabel();
        jLabelIconDelete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jButtonCreate.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCreate.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jButtonCreate.setText("Create User");
        jButtonCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCreateMouseClicked(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jButtonDelete.setText("Delete User");
        jButtonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDeleteMouseClicked(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jButtonUpdate.setText("Update User");
        jButtonUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUpdateMouseClicked(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("USER MANAGEMENT");

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        jButtonView.setBackground(new java.awt.Color(255, 255, 255));
        jButtonView.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jButtonView.setText("View Users");
        jButtonView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonViewMouseClicked(evt);
            }
        });

        jLabelMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimize.png"))); // NOI18N
        jLabelMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jButtonView, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButtonCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabelTitle)
                .addGap(24, 24, 24)
                .addComponent(jButtonView, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButtonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jLabelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back_button.png"))); // NOI18N
        jLabelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackMouseClicked(evt);
            }
        });

        jLabelIconCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N

        jLabelIconUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update_user.png"))); // NOI18N

        jLabelIconDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIconDelete)
                    .addComponent(jLabelIconUpdate)
                    .addComponent(jLabelIconCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelIconCreate)
                .addGap(46, 46, 46)
                .addComponent(jLabelIconUpdate)
                .addGap(44, 44, 44)
                .addComponent(jLabelIconDelete)
                .addGap(53, 53, 53))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        setVisible(false);
        SystemAdminArea user = new SystemAdminArea();
        user.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jLabelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitMouseClicked

    private void jButtonCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCreateMouseClicked
        setVisible(false);
        CreateUser cUser = new CreateUser();
        cUser.setVisible(true);
    }//GEN-LAST:event_jButtonCreateMouseClicked

    private void jButtonUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUpdateMouseClicked
        setVisible(false);
        UpdateUser uUser = new UpdateUser();
        uUser.setVisible(true);
    }//GEN-LAST:event_jButtonUpdateMouseClicked

    private void jButtonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeleteMouseClicked
        setVisible(false);
        DeleteUser dUser = new DeleteUser();
        dUser.setVisible(true);
    }//GEN-LAST:event_jButtonDeleteMouseClicked

    private void jLabelMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeMouseClicked
        this.setExtendedState(ManagementUserArea.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeMouseClicked

    private void jButtonViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonViewMouseClicked
        setVisible(false);
        ViewUser vUser = new ViewUser();
        vUser.setVisible(true);
    }//GEN-LAST:event_jButtonViewMouseClicked

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
            java.util.logging.Logger.getLogger(ManagementUserArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagementUserArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagementUserArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagementUserArea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ManagementUserArea().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreate;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonView;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelIconCreate;
    private javax.swing.JLabel jLabelIconDelete;
    private javax.swing.JLabel jLabelIconUpdate;
    private javax.swing.JLabel jLabelMinimize;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
