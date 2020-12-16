package view;

import configuration.Exceptions.ActivityNotFoundException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import controller.Services.DepartmentService;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Activity.MaintenanceActivity;
import model.Department.Department;

/**
 *
 * @author Group9
 */
public class UpdateActivity extends javax.swing.JFrame {

    private List<MaintenanceActivity> activityList = new LinkedList<MaintenanceActivity>();
    private ActivityService activity = ActivityService.getActivityService();
    private List<Department> depList = new LinkedList<>();
    private DepartmentService dep = DepartmentService.getDepartmentService();

    /**
     * Creates new form UpdateActivity.
     */
    public UpdateActivity() {
        initComponents();
        
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(890, 590);
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
        jLabelBack = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelExit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableActivities = new javax.swing.JTable();
        jButtonList = new javax.swing.JButton();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldType = new javax.swing.JTextField();
        jTextFieldTime = new javax.swing.JTextField();
        jLabelDescription = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabelUpdate = new javax.swing.JLabel();
        jLabelWeekNum = new javax.swing.JLabel();
        jComboWeek = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDepartment = new javax.swing.JTable();
        jButtonView = new javax.swing.JButton();
        jLabelMinimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

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
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabelTitle.setFont(new java.awt.Font("Impact", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("UPDATE ACTIVITY");

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.setText("jLabel3");
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        jTableActivities.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Area", "Type", "Description", "Time", "Week Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jLabelDescription.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelDescription.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setText("Description");

        jLabelType.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelType.setForeground(new java.awt.Color(255, 255, 255));
        jLabelType.setText("Type");

        jLabelTime.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTime.setText("Time");

        jLabelUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUpdate.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        jLabelUpdate.setText("Update");
        jLabelUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelUpdate.setOpaque(true);
        jLabelUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelUpdateMouseClicked(evt);
            }
        });

        jLabelWeekNum.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelWeekNum.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWeekNum.setText("Week Number");

        jComboWeek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "no_change", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52" }));

        jTableDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Area"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableDepartment);

        jButtonView.setText("View");
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

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabelMinimize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel2Layout.createSequentialGroup()
                .add(40, 40, 40)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextFieldDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelType)
                            .add(jLabelDescription))
                        .add(80, 80, 80)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelWeekNum)
                            .add(jComboWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelTime)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabelUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(41, 41, 41)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 63, Short.MAX_VALUE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 249, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButtonView, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(47, 47, 47))
            .add(jPanel2Layout.createSequentialGroup()
                .add(126, 126, 126)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jButtonList)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 507, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
            .add(jPanel2Layout.createSequentialGroup()
                .add(270, 270, 270)
                .add(jLabelTitle)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelMinimize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(27, 27, 27)
                .add(jLabelTitle)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonList)
                .add(35, 35, 35)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 155, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonView)
                        .addContainerGap(59, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelType)
                            .add(jLabelTime))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextFieldType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(25, 25, 25)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelDescription)
                            .add(jLabelWeekNum))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextFieldDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jComboWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabelUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(26, 26, 26))))
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
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (SQLException | ActivityNotFoundException | InvalidParameterObjectException ex) {
            Logger.getLogger(UpdateActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.showActivities(activityList);
        activityList = null;
    }//GEN-LAST:event_jButtonListMouseClicked

    private void jLabelUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelUpdateMouseClicked

        // Avoid empty selections
        if (jTableActivities.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please, select an activity first");
            return;
        }
        // ID is selected from the table by clicking on a specific row
        int row = jTableActivities.getSelectedRow();
        int ID = Integer.parseInt(jTableActivities.getModel().getValueAt(row, 0).toString());

        // Checking is used for searching an empty update
        ArrayList<String> checking = new ArrayList<>();

        String type = check(jTextFieldType.getText());
        checking.add(type);

        String description = check(jTextFieldDescription.getText());
        checking.add(description);

        String time_interv = check(jTextFieldTime.getText());
        checking.add(time_interv);
        int time;
        if (time_interv == null) {
            time = Integer.parseInt(jTableActivities.getModel().getValueAt(row, 0).toString());
        } else {
            try {
                time = Integer.parseInt(time_interv);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        }

        String week = jComboWeek.getSelectedItem().toString();
        checking.add(null);
        int week_num;
        if (week.equals("no_change")) {
            week_num = Integer.parseInt(jTableActivities.getModel().getValueAt(row, 5).toString());
        } else {
            week_num = Integer.parseInt(week);
        }

        String area = null;
        if (!jTableDepartment.getSelectionModel().isSelectionEmpty()) {
            checking.add(area);
            int rowdep = jTableDepartment.getSelectedRow();
            area = jTableDepartment.getModel().getValueAt(rowdep, 0).toString();
        }

        Department department = new Department(area);

        // Count is used to monitor empty update values
        int count = 0;
        // Increment count for every null String in cheching list
        for(String s: checking){
            if(s == null)
                count++;
        }
 
        try {
            int result = activity.updateActivity(ID, type, description, time, week_num, department);
            if (result > 0) {
                if (count != checking.size()) {
                    JOptionPane.showMessageDialog(null, "Activity updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No activity updated!");
                }
            }
        } catch (SQLException | UnsuccessfulUpdateException | InvalidParameterObjectException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jLabelUpdateMouseClicked

    private void jButtonViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonViewMouseClicked

        try {
            depList = dep.getAllDepartments();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        this.showDepartments(depList);
        depList = null;
    }//GEN-LAST:event_jButtonViewMouseClicked

    private void jLabelMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeMouseClicked
        this.setExtendedState(UpdateActivity.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeMouseClicked

    /**
     * Fill a table with the maintenance activities contained in the list.
     *
     * @param list: a list containing all the maintenance activities
     */
    public void showActivities(List<MaintenanceActivity> list) {

        DefaultTableModel model = (DefaultTableModel) jTableActivities.getModel();
        Object column[] = new Object[6];

        if (model.getRowCount() != 0) {
            for (int i = 0; i < list.size(); i++) {
                model.removeRow(0);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            column[0] = list.get(i).getID();
            column[1] = list.get(i).getDepartment().getArea();
            column[2] = list.get(i).getType();
            column[3] = list.get(i).getDescription();
            column[4] = list.get(i).getTime();
            column[5] = list.get(i).getWeekNum();
            model.addRow(column);
        }
    }

    /**
     * Fill a table with the departments contained in the list.
     *
     * @param list: a list containing all the departments
     */
    public void showDepartments(List<Department> list) {

        DefaultTableModel departments = (DefaultTableModel) jTableDepartment.getModel();

        int length = departments.getRowCount();

        if (length != 0) {
            for (int i = 0; i < length; i++) {
                departments.removeRow(0);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Object column[] = new Object[1];
            column[0] = list.get(i).getArea();

            departments.addRow(column);
        }
    }

    /**
     * Check if a string is blank (empty or composed only by spaces)
     *
     * @param string: a String to check
     * @return the same string given in input if it is not blank
     */
    private String check(String s) {
        if (s.isBlank()) {
            s = null;
        }
        return s;
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
            java.util.logging.Logger.getLogger(UpdateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UpdateActivity().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonList;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboWeek;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelMinimize;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelUpdate;
    private javax.swing.JLabel jLabelWeekNum;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableActivities;
    private javax.swing.JTable jTableDepartment;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldType;
    // End of variables declaration//GEN-END:variables

}
