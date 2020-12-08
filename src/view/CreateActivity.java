/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.InvalidPermissionException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import controller.Services.ActivityService;
import controller.Services.CompetenceService;
import controller.Services.DepartmentService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Competences.Competence;
import model.Department.Department;

/**
 *
 * @author Group9
 */
public class CreateActivity extends javax.swing.JFrame {

    private List<Department> depList = new LinkedList<>();
    private DepartmentService dep = DepartmentService.getDepartmentService();
    /**
     * Creates new form CreateActivity
     */
    public CreateActivity() {
        initComponents();
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(1060, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getAllSkills();
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
        jLabelExit = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jTextFieldType = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldTime = new javax.swing.JTextField();
        jLabelType = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabelCreate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableToAssociate = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAssociated = new javax.swing.JTable();
        jLabelTit1 = new javax.swing.JLabel();
        jLabelTit2 = new javax.swing.JLabel();
        jComboWeek = new javax.swing.JComboBox<>();
        jLabelWeeknum = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDepartment = new javax.swing.JTable();
        jButtonRemove1 = new javax.swing.JButton();
        jLabelTit3 = new javax.swing.JLabel();
        jLabelMinimize1 = new javax.swing.JLabel();

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

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.setText("jLabel2");
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("CREATE ACTIVITY");

        jTextFieldType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTypeActionPerformed(evt);
            }
        });

        jTextFieldTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimeActionPerformed(evt);
            }
        });

        jLabelType.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelType.setForeground(new java.awt.Color(255, 255, 255));
        jLabelType.setText("Type");

        jLabelDescription.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelDescription.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setText("Description");

        jLabelTime.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTime.setText("Time");

        jLabelCreate.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCreate.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelCreate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add_button.png"))); // NOI18N
        jLabelCreate.setText("Create");
        jLabelCreate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelCreate.setOpaque(true);
        jLabelCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCreateMouseClicked(evt);
            }
        });

        jTableToAssociate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableToAssociate);

        jButtonAdd.setText("Add");
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddMouseClicked(evt);
            }
        });
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonRemove.setText("Remove");
        jButtonRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRemoveMouseClicked(evt);
            }
        });
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jTableAssociated.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableAssociated);

        jLabelTit1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTit1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTit1.setText("Skill To Assign");

        jLabelTit2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTit2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTit2.setText("Department");

        jComboWeek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52" }));

        jLabelWeeknum.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelWeeknum.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWeeknum.setText("Week Number");

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
        jScrollPane3.setViewportView(jTableDepartment);

        jButtonRemove1.setText("List");
        jButtonRemove1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRemove1MouseClicked(evt);
            }
        });
        jButtonRemove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemove1ActionPerformed(evt);
            }
        });

        jLabelTit3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTit3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTit3.setText("Skill Assigned");

        jLabelMinimize1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimize.png"))); // NOI18N
        jLabelMinimize1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimize1MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(60, 60, 60)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextFieldDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelDescription))
                        .add(60, 60, 60)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelTime)
                            .add(jTextFieldTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabelTit1)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jButtonAdd)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabelTit3)
                                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 257, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(227, 227, 227)
                                .add(jButtonRemove)))))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelTit2)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(197, 197, 197)
                                .add(jButtonRemove1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 257, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(28, 28, 28))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(54, 54, 54)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelWeeknum)
                            .add(jComboWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabelMinimize1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(389, 389, 389)
                        .add(jLabelCreate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .add(jPanel2Layout.createSequentialGroup()
                .add(354, 354, 354)
                .add(jLabelTitle)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelMinimize1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(95, 95, 95)
                                .add(jLabelType))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelTitle)
                                .add(18, 18, 18)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelTime)
                                    .add(jLabelWeeknum))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextFieldType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextFieldTime, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jComboWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(97, 97, 97)
                        .add(jLabelDescription)))
                .add(81, 81, 81)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelTit1)
                    .add(jLabelTit3)
                    .add(jLabelTit2))
                .add(11, 11, 11)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jButtonRemove)
                            .add(jButtonRemove1)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonAdd)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(jLabelCreate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(40, 40, 40))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .add(109, 109, 109)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTypeActionPerformed

    }//GEN-LAST:event_jTextFieldTypeActionPerformed

    private void jTextFieldTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimeActionPerformed

    }//GEN-LAST:event_jTextFieldTimeActionPerformed

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        setVisible(false);
        ManagementActivityArea activityArea = new ManagementActivityArea();
        activityArea.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void jLabelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitMouseClicked


    private void jLabelCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCreateMouseClicked

        ActivityService act = ActivityService.getActivityService();

        String type = jTextFieldType.getText();
        String description = jTextFieldDescription.getText();

        if ("".equals(jTextFieldTime.getText())) {
            JOptionPane.showMessageDialog(null, "Field Time must be filled");
            return;
        }

        if (!jTextFieldTime.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Field Time must be numeric");
            return;
        }

        Integer time = Integer.parseInt(jTextFieldTime.getText());
        Integer week_num = Integer.parseInt(jComboWeek.getSelectedItem().toString());
        
        if (jTableDepartment.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please, select a department first");
            return;
        }
        int row = jTableDepartment.getSelectedRow();
        Department department = new Department(jTableDepartment.getModel().getValueAt(row, 0).toString());
        
        ArrayList<Competence> skills = new ArrayList<Competence>();
        DefaultTableModel model = (DefaultTableModel) jTableAssociated.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            Integer id = Integer.parseInt(model.getValueAt(i, 0).toString());
            String descriptionSkill = model.getValueAt(i, 1).toString();

            Competence c = new Competence(id, descriptionSkill);
            skills.add(c);
        }

        try {
            act.insertActivity(type, description, time, skills, week_num, department);
            JOptionPane.showMessageDialog(null, "Activity created successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database internal error");
        } catch (UnsuccessfulUpdateException ex) {
            JOptionPane.showMessageDialog(null, "Cannot create this activity");
        } catch (InvalidPermissionException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Permission");
        } catch (InvalidParameterObjectException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jLabelCreateMouseClicked

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

    }//GEN-LAST:event_jButtonRemoveActionPerformed


    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseClicked
        int posToAssociate = jTableToAssociate.getSelectedRow();

        if (posToAssociate >= 0) {

            Integer id = Integer.parseInt(jTableToAssociate.getModel().getValueAt(posToAssociate, 0).toString());
            String description = jTableToAssociate.getModel().getValueAt(posToAssociate, 1).toString();

            DefaultTableModel model = (DefaultTableModel) jTableAssociated.getModel();
            Object column[] = new Object[2];

            column[0] = id;
            column[1] = description;

            model.addRow(column);

            DefaultTableModel modelToAssociate = (DefaultTableModel) jTableToAssociate.getModel();
            modelToAssociate.removeRow(posToAssociate);
        }
    }//GEN-LAST:event_jButtonAddMouseClicked

    private void jButtonRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemoveMouseClicked
        int posAssociated = jTableAssociated.getSelectedRow();

        if (posAssociated >= 0) {

            Integer id = Integer.parseInt(jTableAssociated.getModel().getValueAt(posAssociated, 0).toString());
            String description = jTableAssociated.getModel().getValueAt(posAssociated, 1).toString();

            DefaultTableModel model = (DefaultTableModel) jTableToAssociate.getModel();
            Object column[] = new Object[2];

            column[0] = id;
            column[1] = description;

            model.addRow(column);

            DefaultTableModel modelAssociated = (DefaultTableModel) jTableAssociated.getModel();
            modelAssociated.removeRow(posAssociated);
        }
    }//GEN-LAST:event_jButtonRemoveMouseClicked

    private void jButtonRemove1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemove1MouseClicked
        
        try {
            depList = dep.getAllDepartments();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDepartment.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.showDepartments(depList);
        depList = null;
    }//GEN-LAST:event_jButtonRemove1MouseClicked

    public void showDepartments(List<Department> list){
        
        DefaultTableModel departments = (DefaultTableModel) jTableDepartment.getModel();
        
        int length = departments.getRowCount();
        
        if(length != 0){
            for(int i = 0; i < length; i++) {
                departments.removeRow(0);
            }
        }
        
        for(int i=0;i<list.size();i++){
            Object column[] =new Object[1];
            column[0] = list.get(i).getArea();
            
            departments.addRow(column);
        }
    }
    
    private void jButtonRemove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemove1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRemove1ActionPerformed

    private void jLabelMinimize1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimize1MouseClicked
        this.setExtendedState(this.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimize1MouseClicked

    private void getAllSkills() {

        CompetenceService compService = CompetenceService.getCompetenceService();

        try {
            List<Competence> competences = compService.getAllCompetences();
            DefaultTableModel model = (DefaultTableModel) jTableToAssociate.getModel();

            for (Competence c : competences) {

                Object[] column = new Object[2];
                column[0] = c.getId();
                column[1] = c.getDescription();

                model.addRow(column);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in database");
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
            java.util.logging.Logger.getLogger(CreateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonRemove1;
    private javax.swing.JComboBox<String> jComboWeek;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelCreate;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelMinimize1;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelTit1;
    private javax.swing.JLabel jLabelTit2;
    private javax.swing.JLabel jLabelTit3;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelWeeknum;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableAssociated;
    private javax.swing.JTable jTableDepartment;
    private javax.swing.JTable jTableToAssociate;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldType;
    // End of variables declaration//GEN-END:variables

}
