/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import configuration.Exceptions.ActivityAlreadyAssignedException;
import configuration.Exceptions.InvalidParameterObjectException;
import configuration.Exceptions.TimeExpiredException;
import configuration.Exceptions.UnsuccessfulUpdateException;
import configuration.Exceptions.UsernotFoundException;
import controller.Services.ActivityService;
import controller.Services.CompetenceService;
import controller.Services.UserManagementService;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Activity.ActivityInterface;
import model.Competences.Competence;
import model.Users.UserModel;

/**
 *
 * @author Group9
 */
public class AssignmentActivity extends javax.swing.JFrame {

    private final int ID;
    private final double time;
    private final int week;
    private final String area;
    private final String type;

    private List<ActivityInterface> list = new ArrayList<>();
    private List<UserModel> listMaintainers = new LinkedList<>();
    private CompetenceService competence = CompetenceService.getCompetenceService();
    private UserManagementService user = UserManagementService.getUserManagementService();
    private List<Competence> skills = new LinkedList<>();
    private Map<String, List<Integer>> mapIdDays = new HashMap<>();
    private ActivityService activityService = ActivityService.getActivityService();

    /**
     * Creates new form AssignmentActivity
     */
    public AssignmentActivity(int ID, String area, String type, double time, int week) throws SQLException {
        initComponents();
        this.ID = ID;
        this.week = week;
        this.area = area;
        this.type = type;
        this.time = time;
        jLabelInfo.setText(this.showInfo());
        jLabelWeek.setText(String.valueOf(week));
        jLabelCurrentWeek.setText(this.getCurrentWeek());
        ImageIcon icon = new ImageIcon("src/icons/app_icon.png");
        setIconImage(icon.getImage());
        setTitle("Maintenance System App");
        setSize(1180, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showSkillsNeeded();
        getMaintainers();
        initMapIdDays();
        
        jTableMaintainersAvail.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        System.out.println(jTableMaintainersAvail.getValueAt(1, 3).toString());
        
        for(int i=2;i<jTableMaintainersAvail.getColumnCount();i++){
            this.changeTable(jTableMaintainersAvail, i);
        }
        
    }
    
    private void changeTable(JTable table, int column) {
        table.getColumnModel().getColumn(column).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                int st_val = Integer.parseInt(table.getValueAt(row, column).toString());

                if (st_val < 40) {
                    c.setBackground(Color.RED);
                    String s = ""+ st_val+"%";
                    table.setValueAt(s, row, column);
                } else if (st_val >= 40 && st_val <= 70) {
                    c.setBackground(Color.YELLOW);
                    String s = ""+ st_val+"%";
                    table.setValueAt(s, row, column);
                } else {
                    c.setBackground(Color.GREEN);
                    String s = ""+ st_val+"%";
                    table.setValueAt(s, row, column);
                }

                return c;
            }
        });
    }
    
    class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Color getBackground() {
            return super.getBackground();
        }
    }

    private String getCurrentWeek() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    private void initMapIdDays() {

        for (UserModel user : listMaintainers) {
            mapIdDays.put(user.getUsername(), new ArrayList<Integer>());
        }

    }

    private String showInfo() {

        return ID + "-" + area + "-" + type + "-" + time;
    }

    private void getMaintainers() throws SQLException, SQLException, SQLException, SQLException {

        try {
            listMaintainers = user.getAllMaintainers();
        } catch (SQLException | UsernotFoundException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.showUsers(listMaintainers);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor tprivate String
     * showInfo(){
     *
     * }o initialize the form. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelExit = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMaintainersAvail = new javax.swing.JTable();
        jLabelMaintainersAvail = new javax.swing.JLabel();
        jButtonAssign = new javax.swing.JButton();
        jLabelMinimize = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSkills = new javax.swing.JTable();
        jLabelInfo = new javax.swing.JLabel();
        jLabelCurrentWeek = new javax.swing.JLabel();
        jLabelActivityInfo = new javax.swing.JLabel();
        jLabelCurrentWeekNum = new javax.swing.JLabel();
        jCheckBoxMon = new javax.swing.JCheckBox();
        jCheckBoxTue = new javax.swing.JCheckBox();
        jCheckBoxWed = new javax.swing.JCheckBox();
        jCheckBoxThu = new javax.swing.JCheckBox();
        jCheckBoxFri = new javax.swing.JCheckBox();
        jCheckBoxSat = new javax.swing.JCheckBox();
        jCheckBoxSun = new javax.swing.JCheckBox();
        jLabelMon = new javax.swing.JLabel();
        jLabelTue = new javax.swing.JLabel();
        jLabelWed = new javax.swing.JLabel();
        jLabelFri = new javax.swing.JLabel();
        jLabelThu = new javax.swing.JLabel();
        jLabelSat = new javax.swing.JLabel();
        jLabelSun = new javax.swing.JLabel();
        jLabelSkills = new javax.swing.JLabel();
        jLabelTitleAct = new javax.swing.JLabel();
        jLabelTitleCheck = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelBack = new javax.swing.JLabel();
        jLabelWeek = new javax.swing.JLabel();
        jLabelWeekNum1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabelTitle.setFont(new java.awt.Font("Impact", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("ASSIGN ACTIVITY");

        jLabelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        jLabelExit.setText("jLabel2");
        jLabelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelExitMouseClicked(evt);
            }
        });

        jTableMaintainersAvail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Skills", "Availab Mon", "Availab Tue", "Availab Wed", "Availab Thu", "Availab Fri", "Availab Sat", "Availab Sun"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableMaintainersAvail);

        jLabelMaintainersAvail.setBackground(new java.awt.Color(255, 204, 0));
        jLabelMaintainersAvail.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelMaintainersAvail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMaintainersAvail.setText("Availability Maintainer");
        jLabelMaintainersAvail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelMaintainersAvail.setOpaque(true);

        jButtonAssign.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButtonAssign.setText("Assign");
        jButtonAssign.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAssignMouseClicked(evt);
            }
        });

        jLabelMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimize.png"))); // NOI18N
        jLabelMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinimizeMouseClicked(evt);
            }
        });

        jTableSkills.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jTableSkills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Skills"
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
        jScrollPane2.setViewportView(jTableSkills);

        jLabelInfo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInfo.setFont(new java.awt.Font("Impact", 0, 17)); // NOI18N
        jLabelInfo.setOpaque(true);

        jLabelCurrentWeek.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCurrentWeek.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabelCurrentWeek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCurrentWeek.setOpaque(true);

        jLabelActivityInfo.setBackground(new java.awt.Color(255, 204, 0));
        jLabelActivityInfo.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelActivityInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelActivityInfo.setText("Activity To Assign");
        jLabelActivityInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelActivityInfo.setOpaque(true);

        jLabelCurrentWeekNum.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelCurrentWeekNum.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCurrentWeekNum.setText("Current Week N°");

        jCheckBoxMon.setText("jCheckBox1");
        jCheckBoxMon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxMonStateChanged(evt);
            }
        });

        jCheckBoxTue.setText("jCheckBox2");
        jCheckBoxTue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxTueStateChanged(evt);
            }
        });

        jCheckBoxWed.setText("jCheckBox3");
        jCheckBoxWed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxWedStateChanged(evt);
            }
        });

        jCheckBoxThu.setText("jCheckBox4");
        jCheckBoxThu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxThuStateChanged(evt);
            }
        });

        jCheckBoxFri.setText("jCheckBox5");
        jCheckBoxFri.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxFriStateChanged(evt);
            }
        });

        jCheckBoxSat.setText("jCheckBox6");
        jCheckBoxSat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxSatStateChanged(evt);
            }
        });

        jCheckBoxSun.setText("jCheckBox7");
        jCheckBoxSun.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxSunStateChanged(evt);
            }
        });

        jLabelMon.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelMon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMon.setText("Mon");

        jLabelTue.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTue.setText("Tue");

        jLabelWed.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelWed.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWed.setText("Wed");

        jLabelFri.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelFri.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFri.setText("Fri");

        jLabelThu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelThu.setForeground(new java.awt.Color(255, 255, 255));
        jLabelThu.setText("Thu");

        jLabelSat.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelSat.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSat.setText("Sat");

        jLabelSun.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelSun.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSun.setText("Sun");

        jLabelSkills.setBackground(new java.awt.Color(255, 204, 0));
        jLabelSkills.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelSkills.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSkills.setText("Skills Needed");
        jLabelSkills.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelSkills.setOpaque(true);

        jLabelTitleAct.setBackground(new java.awt.Color(255, 204, 0));
        jLabelTitleAct.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTitleAct.setText("To Assign Activity");
        jLabelTitleAct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelTitleAct.setOpaque(true);

        jLabelTitleCheck.setBackground(new java.awt.Color(255, 204, 0));
        jLabelTitleCheck.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelTitleCheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleCheck.setText("Check Day");
        jLabelTitleCheck.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 0), new java.awt.Color(255, 204, 0), null, null));
        jLabelTitleCheck.setOpaque(true);

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
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelBack)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelWeek.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWeek.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabelWeek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWeek.setOpaque(true);

        jLabelWeekNum1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelWeekNum1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWeekNum1.setText("Activity Week N°");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabelMinimize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(50, 50, 50)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabelCurrentWeekNum)
                                .add(224, 224, 224)
                                .add(jLabelTitle)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 205, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelActivityInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelSkills, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 113, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelCurrentWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(jLabelWeekNum1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                        .add(181, 181, 181)))
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jButtonAssign)
                                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelMaintainersAvail, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)))
                                        .add(29, 29, 29))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                            .add(jLabelTitleAct, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .add(jLabelTitleCheck, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .add(39, 39, 39)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jCheckBoxMon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelMon))
                                        .add(50, 50, 50)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabelTue)
                                            .add(jCheckBoxTue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(41, 41, 41)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jCheckBoxWed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelWed))
                                        .add(45, 45, 45)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabelThu)
                                            .add(jCheckBoxThu, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(39, 39, 39)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabelFri, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jCheckBoxFri, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(47, 47, 47)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jLabelSat)
                                            .add(jCheckBoxSat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(44, 44, 44)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jCheckBoxSun, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabelSun, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(33, 33, 33)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(66, 66, 66)
                        .add(jLabelTitle)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(57, 57, 57)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelMon)
                                    .add(jLabelTue)
                                    .add(jLabelWed)
                                    .add(jLabelThu)
                                    .add(jLabelFri)
                                    .add(jLabelSat)
                                    .add(jLabelSun))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jCheckBoxMon)
                                    .add(jCheckBoxTue)
                                    .add(jCheckBoxWed)
                                    .add(jCheckBoxThu)
                                    .add(jCheckBoxFri)
                                    .add(jCheckBoxSat)
                                    .add(jCheckBoxSun))
                                .add(27, 27, 27))
                            .add(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jLabelTitleCheck)
                                .add(0, 0, 0)
                                .add(jLabelTitleAct)
                                .add(25, 25, 25)))
                        .add(6, 6, 6))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabelExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelMinimize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabelCurrentWeekNum)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelCurrentWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabelWeekNum1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelActivityInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(0, 0, 0)
                        .add(jLabelMaintainersAvail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonAssign, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(79, 79, 79))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(jLabelInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabelSkills)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(83, 83, 83))))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackMouseClicked
        setVisible(false);
        SelectActivity sActivity = new SelectActivity();
        sActivity.setVisible(true);
    }//GEN-LAST:event_jLabelBackMouseClicked

    private void showSkillsNeeded() {

        try {
            skills = competence.getAllSkills(ID);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.showActivitySkills(skills);
    }

    private void showActivitySkills(List<Competence> list) {

        DefaultTableModel skills = (DefaultTableModel) jTableSkills.getModel();

        for (int i = 0; i < list.size(); i++) {
            Object column[] = new Object[1];
            column[0] = list.get(i).getDescription();
            skills.addRow(column);
        }
    }

    private void jLabelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelExitMouseClicked

    private void showUsers(List<UserModel> list) throws SQLException {

        DefaultTableModel users = (DefaultTableModel) jTableMaintainersAvail.getModel();
        Object column[] = new Object[9];

        for (int i = 0; i < list.size(); i++) {
            column[0] = list.get(i).getUsername();
            column[1] = competence.getCommonSkills(skills, list.get(i).getUsername());
            column[2] = activityService.getDailyAvailability(list.get(i).getUsername(), 1, time);
            column[3] = activityService.getDailyAvailability(list.get(i).getUsername(), 2, time);
            column[4] = activityService.getDailyAvailability(list.get(i).getUsername(), 3, time);
            column[5] = activityService.getDailyAvailability(list.get(i).getUsername(), 4, time);
            column[6] = activityService.getDailyAvailability(list.get(i).getUsername(), 5, time);
            column[7] = activityService.getDailyAvailability(list.get(i).getUsername(), 6, time);
            column[8] = activityService.getDailyAvailability(list.get(i).getUsername(), 7, time);

            users.addRow(column);
        }
    }

    private void leftFromUserTable() {

        DefaultTableModel users = (DefaultTableModel) jTableMaintainersAvail.getModel();

        int length = users.getRowCount();

        for (int i = 0; i < length; i++) {
            users.removeRow(0);
        }

    }

    private void jButtonAssignMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAssignMouseClicked

        if (jTableMaintainersAvail.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please, select a Maintainer first");
            return;
        }

        if (!jCheckBoxMon.isSelected() && !jCheckBoxTue.isSelected()
                && !jCheckBoxWed.isSelected() && !jCheckBoxThu.isSelected()
                && !jCheckBoxFri.isSelected() && !jCheckBoxSat.isSelected()
                && !jCheckBoxSun.isSelected()) {

            JOptionPane.showMessageDialog(null, "Please, select a day first");
            return;
        }

        try {
            int selectedIndex = jTableMaintainersAvail.getSelectedRow();
            DefaultTableModel users = (DefaultTableModel) jTableMaintainersAvail.getModel();

            String username = users.getValueAt(selectedIndex, 0).toString();

            List<Integer> listDays = new ArrayList<Integer>();

            if (jCheckBoxMon.isSelected()) {
                listDays.add(1);
            }
            if (jCheckBoxTue.isSelected()) {
                listDays.add(2);
            }
            if (jCheckBoxWed.isSelected()) {
                listDays.add(3);
            }
            if (jCheckBoxThu.isSelected()) {
                listDays.add(4);
            }
            if (jCheckBoxFri.isSelected()) {
                listDays.add(5);
            }
            if (jCheckBoxSat.isSelected()) {
                listDays.add(6);
            }
            if (jCheckBoxSun.isSelected()) {
                listDays.add(7);
            }

            int result = activityService.assignActivity(username, ID, listDays, time);

            leftFromUserTable();
            showUsers(listMaintainers);

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Activity assigned successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No activity assigned!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database internal error");
        } catch (UsernotFoundException ex) {
            JOptionPane.showMessageDialog(null, "User not found");
        } catch (UnsuccessfulUpdateException ex) {
            JOptionPane.showMessageDialog(null, "Cannot assign activity to Maintainer");
        } catch (InvalidParameterObjectException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (TimeExpiredException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ActivityAlreadyAssignedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jButtonAssignMouseClicked

    private void checkUncheckDay(int day, boolean value) {

        switch (day) {
            case 1: {
                this.jCheckBoxMon.setSelected(value);
                break;
            }
            case 2: {
                this.jCheckBoxTue.setSelected(value);
                break;
            }
            case 3: {
                this.jCheckBoxWed.setSelected(value);
                break;
            }
            case 4: {
                this.jCheckBoxThu.setSelected(value);
                break;
            }
            case 5: {
                this.jCheckBoxFri.setSelected(value);
                break;
            }
            case 6: {
                this.jCheckBoxSat.setSelected(value);
                break;
            }
            case 7: {
                this.jCheckBoxSun.setSelected(value);
                break;
            }

        }

    }

    private void uncheckWeekDays(int day) {
        if (day != 1) {
            jCheckBoxMon.setSelected(false);
        }
        if (day != 2) {
            jCheckBoxTue.setSelected(false);
        }
        if (day != 3) {
            jCheckBoxWed.setSelected(false);
        }
        if (day != 4) {
            jCheckBoxThu.setSelected(false);
        }
        if (day != 5) {
            jCheckBoxFri.setSelected(false);
        }
        if (day != 6) {
            jCheckBoxSat.setSelected(false);
        }
        if (day != 7) {
            jCheckBoxSun.setSelected(false);
        }
    }

    private void jLabelMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinimizeMouseClicked
        this.setExtendedState(this.ICONIFIED);
    }//GEN-LAST:event_jLabelMinimizeMouseClicked

    private void jCheckBoxMonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxMonStateChanged
        uncheckWeekDays(1);
    }//GEN-LAST:event_jCheckBoxMonStateChanged

    private void jCheckBoxTueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxTueStateChanged
        uncheckWeekDays(2);
    }//GEN-LAST:event_jCheckBoxTueStateChanged

    private void jCheckBoxWedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxWedStateChanged
        uncheckWeekDays(3);
    }//GEN-LAST:event_jCheckBoxWedStateChanged

    private void jCheckBoxThuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxThuStateChanged
        uncheckWeekDays(4);
    }//GEN-LAST:event_jCheckBoxThuStateChanged

    private void jCheckBoxFriStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxFriStateChanged
        uncheckWeekDays(5);
    }//GEN-LAST:event_jCheckBoxFriStateChanged

    private void jCheckBoxSatStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxSatStateChanged
        uncheckWeekDays(6);
    }//GEN-LAST:event_jCheckBoxSatStateChanged

    private void jCheckBoxSunStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxSunStateChanged
        uncheckWeekDays(7);
    }//GEN-LAST:event_jCheckBoxSunStateChanged

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
                try {
                    new AssignmentActivity(ID, area, type, time, week).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AssignmentActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAssign;
    private javax.swing.JCheckBox jCheckBoxFri;
    private javax.swing.JCheckBox jCheckBoxMon;
    private javax.swing.JCheckBox jCheckBoxSat;
    private javax.swing.JCheckBox jCheckBoxSun;
    private javax.swing.JCheckBox jCheckBoxThu;
    private javax.swing.JCheckBox jCheckBoxTue;
    private javax.swing.JCheckBox jCheckBoxWed;
    private javax.swing.JLabel jLabelActivityInfo;
    private javax.swing.JLabel jLabelBack;
    private javax.swing.JLabel jLabelCurrentWeek;
    private javax.swing.JLabel jLabelCurrentWeekNum;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelFri;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JLabel jLabelMaintainersAvail;
    private javax.swing.JLabel jLabelMinimize;
    private javax.swing.JLabel jLabelMon;
    private javax.swing.JLabel jLabelSat;
    private javax.swing.JLabel jLabelSkills;
    private javax.swing.JLabel jLabelSun;
    private javax.swing.JLabel jLabelThu;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleAct;
    private javax.swing.JLabel jLabelTitleCheck;
    private javax.swing.JLabel jLabelTue;
    private javax.swing.JLabel jLabelWed;
    private javax.swing.JLabel jLabelWeek;
    private javax.swing.JLabel jLabelWeekNum1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMaintainersAvail;
    private javax.swing.JTable jTableSkills;
    // End of variables declaration//GEN-END:variables

}
