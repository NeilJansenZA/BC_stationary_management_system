/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationModule.JLayoutForms;

import ApplicationHelper.Helper;
import Authentication.ActiveAccess;
import Authentication.AuthenticationSettings;
import BusinessModule.*;
import BusinessModule.OrderComparators.*;
import BusinessModule.OrderDetailsComparators.*;
import BusinessModule.StaffOrderComparators.*;
import BusinessModule.StockComporators.*;
import PresentationModule.BC_StationaryManagementSystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neil
 */
public class JAdminModule extends javax.swing.JFrame {

    private Staff selectedStaff;
    private StationaryStock selectedStock;
    private List<Staff> registerStaff = new ArrayList<>();
    private List<Staff> users = new ArrayList<>();
    private List<StationaryStock> stationaryStock = new ArrayList<>();
    private List<StationaryCategory> categories = new ArrayList<>();
    private List<Order> allOrders = new ArrayList<>();
    private List<StaffOrder> pendingOrders = new ArrayList<>();
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    private List<Campus> filterCampus = new ArrayList<>();
    private List<Department> filterDeparment = new ArrayList<>();

    private JPanel[] tabPanels;
    private boolean[] loadedTabs;
    private int totalTabs;
    private boolean formLoad = false;

    /**
     * Creates new form AdminModule
     */
    public JAdminModule() {
        initComponents();

        LoadTab();
        LoadBoxCategory();
        formLoad = true;
    }

    // <editor-fold defaultstate="collapsed" desc="Load Combo Boxes">
    private void LoadBoxCategory() {
        try {
            cmbFilterCategory.addItem("None");
            categories = new StationaryCategory().ReadStationaryCategory();

            for (StationaryCategory categoryData : categories) {
                cmbFilterCategory.addItem(categoryData.getName());
            }

            cmbFilterCategory.setSelectedIndex(0);
        } catch (NullPointerException npe) {
            Helper.DisplayError(npe.toString());
        }
    }
    
    private void LoadCampus()
    {
        filterCampus = new Campus().ReadCampus();

        cmbFilterCampus.addItem("None");
        cmbFilterCampusReg.addItem("None");
        
        for (Campus campusData : filterCampus)
        {
            cmbFilterCampus.addItem(campusData.getCampusName());
            cmbFilterCampusReg.addItem(campusData.getCampusName());
        }
    }
    
    private void LoadDepartment()
    {
        filterDeparment = new Department().ReadDepartment();
        
        cmbFilterDepartment.addItem("None");
        cmbFilterDepartmentReg.addItem("None");

        for (Department departmentData : filterDeparment)
        {
            cmbFilterDepartment.addItem(departmentData.getDepartmentName());
            cmbFilterDepartmentReg.addItem(departmentData.getDepartmentName());
        }
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Load Tabs">
    private void LoadTab() {
        totalTabs = tpAdminControls.getComponentCount();
        tabPanels = new JPanel[totalTabs];
        loadedTabs = new boolean[totalTabs];
        try {
            for (int i = 0; i < totalTabs; i++) {
                JPanel singlePanel = ((JPanel) tpAdminControls.getComponent(0));
                tabPanels[i] = singlePanel;

                tpAdminControls.remove(0);
                loadedTabs[i] = false;
            }

        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }
    
    private void LoadManageUsersTab()
    {
        if (!loadedTabs[0])
        {
            try
            {
                if (!tpAdminControls.getSelectedComponent().equals(tabPanels[0]))
                {
                    tpAdminControls.addTab("Manage Staff", tabPanels[0]);
                    tpAdminControls.setSelectedComponent(tabPanels[0]);
                    LoadAllUsers();
                    LoadRegisterRequests();
                    PopulateTableUsers();
                    PopulateTableRegister();
                    LoadCampus();
                    LoadDepartment();
                    loadedTabs[0] = true;
                }
            } catch (NullPointerException npe)
            {
                tpAdminControls.addTab("Manage Staff", tabPanels[0]);
                tpAdminControls.setSelectedComponent(tabPanels[0]);
                LoadAllUsers();
                LoadRegisterRequests();
                PopulateTableUsers();
                PopulateTableRegister();
                LoadCampus();
                LoadDepartment();
                loadedTabs[0] = true;
            }
        }
        else
        {
            tpAdminControls.setSelectedComponent(tabPanels[0]);
            LoadAllUsers();
            LoadRegisterRequests();
            PopulateTableUsers();
            PopulateTableRegister();
            LoadCampus();
            LoadDepartment();
        }
    }
    
    private void LoadStationaryTab()
    {
        if (!loadedTabs[1]) {
            try {
                if (!tpAdminControls.getSelectedComponent().equals(tabPanels[1])) {
                    tpAdminControls.addTab("Manage All Stock", tabPanels[1]);
                    tpAdminControls.setSelectedComponent(tabPanels[1]);
                    LoadTableAllStockData();
                    PopulateTableAllStock();
                    loadedTabs[1] = true;
                }
            } catch (NullPointerException npe) {
                tpAdminControls.addTab("Manage All Stock", tabPanels[1]);
                tpAdminControls.setSelectedComponent(tabPanels[1]);
                LoadTableAllStockData();
                PopulateTableAllStock();
                loadedTabs[1] = true;
            }
        }
        else
        {
            tpAdminControls.setSelectedComponent(tabPanels[1]);
            LoadTableAllStockData();
            PopulateTableAllStock();
        }
    }
    
    private void LoadOrdersTab()
    {
        if (!loadedTabs[2]) {
            try {
                if (!tpAdminControls.getSelectedComponent().equals(tabPanels[2])) {
                    tpAdminControls.addTab("View Orders", tabPanels[2]);
                    tpAdminControls.setSelectedComponent(tabPanels[2]);
                    LoadTableAllOrders();
                    LoadTableAllPendingOrders();
                    LoadTablePurchaseOrdersData();
                    PopulateTableAllOrders();
                    PopulateTableAllPendingOrders();
                    PopulateTablePurchaseOrders();
                    loadedTabs[2] = true;
                }
            } catch (NullPointerException npe) {
                tpAdminControls.addTab("View Orders", tabPanels[2]);
                tpAdminControls.setSelectedComponent(tabPanels[2]);
                LoadTableAllOrders();
                LoadTableAllPendingOrders();
                LoadTablePurchaseOrdersData();
                PopulateTableAllOrders();
                PopulateTableAllPendingOrders();
                PopulateTablePurchaseOrders();
                loadedTabs[2] = true;
            }
        }
        else
        {
            tpAdminControls.setSelectedComponent(tabPanels[2]);
            LoadTableAllOrders();
            LoadTableAllPendingOrders();
            LoadTablePurchaseOrdersData();
            PopulateTableAllOrders();
            PopulateTableAllPendingOrders();
            PopulateTablePurchaseOrders();
        }
    }
    
    // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        tpAdminControls = new javax.swing.JTabbedPane();
        pnlManageStaff = new javax.swing.JPanel();
        tpManageStaff = new javax.swing.JTabbedPane();
        pnlAllStaff = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblViewUsers = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFilterNameSurname = new javax.swing.JTextField();
        cmbFilterCampus = new javax.swing.JComboBox<>();
        cmbFilterDepartment = new javax.swing.JComboBox<>();
        pnlRegistrationRequests = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegisterRequests = new javax.swing.JTable();
        btnDeleteRegisterUser = new javax.swing.JButton();
        btnAcceptUser = new javax.swing.JButton();
        pnlAllStaff1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblViewUsers1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtFilterNameSurnameReg = new javax.swing.JTextField();
        cmbFilterCampusReg = new javax.swing.JComboBox<>();
        cmbFilterDepartmentReg = new javax.swing.JComboBox<>();
        btnViewUsersBack = new javax.swing.JButton();
        pnlViewAllStock = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAllStock = new javax.swing.JTable();
        btnViewAllStockBack = new javax.swing.JButton();
        btnPromptUpdate = new javax.swing.JButton();
        btnDeleteStock = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFilterProductName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbFilterCategory = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbPrioritySort = new javax.swing.JComboBox<>();
        btnGenerateStockReport = new javax.swing.JButton();
        pnlViewAllOrders = new javax.swing.JPanel();
        btnCloseViewOrdersTab = new javax.swing.JButton();
        tpOrderControls = new javax.swing.JTabbedPane();
        pnlAllOrders = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAllOrders = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtOrderFilterUsername = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtOrderFilterID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cmdOrderViewByDate = new javax.swing.JComboBox<>();
        cmdOrderSort = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btnViewSelectedOrder = new javax.swing.JButton();
        bntGenerateAllOrderReport = new javax.swing.JButton();
        pnlOrderRequests = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblOrderRequests = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtRequestOrderFilterUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmdOrderPrioritySort = new javax.swing.JComboBox<>();
        cmdOrderRequestViewByDate = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtOrderRequestFilterID = new javax.swing.JTextField();
        btnViewSelectedStaffOrder = new javax.swing.JButton();
        pnlStockOrdeers = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPurchaseOrders = new javax.swing.JTable();
        btnAcceptPurchaseOrder = new javax.swing.JButton();
        jmbAdminModule = new javax.swing.JMenuBar();
        jmAdminApplication = new javax.swing.JMenu();
        jmAdminLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmAdminClose = new javax.swing.JMenuItem();
        jmAccounts = new javax.swing.JMenu();
        jmManageUsers = new javax.swing.JMenuItem();
        jmStationary = new javax.swing.JMenu();
        jmManageAllStock = new javax.swing.JMenuItem();
        jmInserStock = new javax.swing.JMenuItem();
        jmOrders = new javax.swing.JMenu();
        jmViewAllOrders = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Belgium Campus Stationary Management System - Administration Module");

        tblViewUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "ID", "Name", "Surname", "Campus Name", "Department Name", "Cell No", "Email", "Username", "Password"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblViewUsers.setName(""); // NOI18N
        jScrollPane2.setViewportView(tblViewUsers);

        jLabel7.setText("Name / Surname:");

        jLabel8.setText("Campus Name:");

        jLabel9.setText("Department Name:");

        txtFilterNameSurname.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtFilterNameSurnameKeyReleased(evt);
            }
        });

        cmbFilterCampus.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbFilterCampusItemStateChanged(evt);
            }
        });

        cmbFilterDepartment.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbFilterDepartmentItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlAllStaffLayout = new javax.swing.GroupLayout(pnlAllStaff);
        pnlAllStaff.setLayout(pnlAllStaffLayout);
        pnlAllStaffLayout.setHorizontalGroup(
            pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                    .addGroup(pnlAllStaffLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilterNameSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFilterDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAllStaffLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFilterCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAllStaffLayout.setVerticalGroup(
            pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAllStaffLayout.createSequentialGroup()
                .addGroup(pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAllStaffLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cmbFilterDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAllStaffLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFilterNameSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(pnlAllStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbFilterCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpManageStaff.addTab("All Staff", pnlAllStaff);

        tblRegisterRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Request Date", "Name", "Surname", "Campus Name", "Department Name", "Cell No", "Email", "Username", "Password"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblRegisterRequests.setName(""); // NOI18N
        jScrollPane1.setViewportView(tblRegisterRequests);

        btnDeleteRegisterUser.setText("Delete User");
        btnDeleteRegisterUser.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnDeleteRegisterUserMouseClicked(evt);
            }
        });

        btnAcceptUser.setText("Accept User");
        btnAcceptUser.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnAcceptUserMouseClicked(evt);
            }
        });

        tblViewUsers1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "ID", "Name", "Surname", "Campus Name", "Department Name", "Cell No", "Email", "Username", "Password"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblViewUsers1.setName(""); // NOI18N
        jScrollPane7.setViewportView(tblViewUsers1);

        jLabel11.setText("Name / Surname:");

        jLabel12.setText("Campus Name:");

        jLabel13.setText("Department Name:");

        txtFilterNameSurnameReg.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtFilterNameSurnameRegKeyReleased(evt);
            }
        });

        cmbFilterCampusReg.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbFilterCampusRegItemStateChanged(evt);
            }
        });

        cmbFilterDepartmentReg.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbFilterDepartmentRegItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlAllStaff1Layout = new javax.swing.GroupLayout(pnlAllStaff1);
        pnlAllStaff1.setLayout(pnlAllStaff1Layout);
        pnlAllStaff1Layout.setHorizontalGroup(
            pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllStaff1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                    .addGroup(pnlAllStaff1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilterNameSurnameReg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFilterDepartmentReg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAllStaff1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFilterCampusReg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAllStaff1Layout.setVerticalGroup(
            pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAllStaff1Layout.createSequentialGroup()
                .addGroup(pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAllStaff1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cmbFilterDepartmentReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAllStaff1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtFilterNameSurnameReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(pnlAllStaff1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbFilterCampusReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlRegistrationRequestsLayout = new javax.swing.GroupLayout(pnlRegistrationRequests);
        pnlRegistrationRequests.setLayout(pnlRegistrationRequestsLayout);
        pnlRegistrationRequestsLayout.setHorizontalGroup(
            pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrationRequestsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistrationRequestsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeleteRegisterUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAcceptUser)
                        .addGap(25, 25, 25)))
                .addContainerGap())
            .addGroup(pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRegistrationRequestsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAllStaff1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlRegistrationRequestsLayout.setVerticalGroup(
            pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrationRequestsLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteRegisterUser)
                    .addComponent(btnAcceptUser))
                .addContainerGap())
            .addGroup(pnlRegistrationRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRegistrationRequestsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlAllStaff1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tpManageStaff.addTab("Registration Requests", pnlRegistrationRequests);

        btnViewUsersBack.setText("Close Tab");
        btnViewUsersBack.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnViewUsersBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlManageStaffLayout = new javax.swing.GroupLayout(pnlManageStaff);
        pnlManageStaff.setLayout(pnlManageStaffLayout);
        pnlManageStaffLayout.setHorizontalGroup(
            pnlManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpManageStaff)
                    .addGroup(pnlManageStaffLayout.createSequentialGroup()
                        .addComponent(btnViewUsersBack)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlManageStaffLayout.setVerticalGroup(
            pnlManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpManageStaff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewUsersBack)
                .addContainerGap())
        );

        tpManageStaff.getAccessibleContext().setAccessibleName("");

        tpAdminControls.addTab("Manage Staff", pnlManageStaff);

        tblAllStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Stationary Stock ID", "Product Name", "Category Name", "Model", "Price", "Quantity", "Date Of Entry / Update"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAllStock);

        btnViewAllStockBack.setText("Close Tab");
        btnViewAllStockBack.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnViewAllStockBackMouseClicked(evt);
            }
        });

        btnPromptUpdate.setText("Update");
        btnPromptUpdate.setToolTipText("");
        btnPromptUpdate.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnPromptUpdateMouseClicked(evt);
            }
        });

        btnDeleteStock.setText("Delete");
        btnDeleteStock.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnDeleteStockMouseClicked(evt);
            }
        });

        jLabel1.setText("Product Name:");

        txtFilterProductName.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtFilterProductNameKeyReleased(evt);
            }
        });

        jLabel2.setText("Category Name:");

        cmbFilterCategory.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbFilterCategoryItemStateChanged(evt);
            }
        });

        jLabel3.setText("Priority Sort:");

        cmbPrioritySort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Price - High to Low", "Price - Low to High", "Quantity - High to Low", "Quantity - Low to High", "Date - Closest Date to Furthest Date", "Date - Furthest Date to Closest Date" }));
        cmbPrioritySort.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmbPrioritySortItemStateChanged(evt);
            }
        });

        btnGenerateStockReport.setText("Generate Stock Report");
        btnGenerateStockReport.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnGenerateStockReportMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlViewAllStockLayout = new javax.swing.GroupLayout(pnlViewAllStock);
        pnlViewAllStock.setLayout(pnlViewAllStockLayout);
        pnlViewAllStockLayout.setHorizontalGroup(
            pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                    .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                        .addComponent(btnViewAllStockBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPromptUpdate))
                    .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                        .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(txtFilterProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbFilterCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbPrioritySort, 0, 300, Short.MAX_VALUE)
                            .addComponent(btnGenerateStockReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlViewAllStockLayout.setVerticalGroup(
            pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewAllStockLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFilterProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbPrioritySort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbFilterCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateStockReport))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlViewAllStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewAllStockBack)
                    .addComponent(btnPromptUpdate)
                    .addComponent(btnDeleteStock))
                .addContainerGap())
        );

        tpAdminControls.addTab("Manage Stock", pnlViewAllStock);

        btnCloseViewOrdersTab.setText("Close Tab");
        btnCloseViewOrdersTab.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnCloseViewOrdersTabMouseClicked(evt);
            }
        });

        tblAllOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Order ID", "Stock Order ID", "Staff Username", "Order Date", "Approval Date", "Order Total"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblAllOrders);

        jLabel16.setText("Search Username:");

        txtOrderFilterUsername.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtOrderFilterUsernameKeyReleased(evt);
            }
        });

        jLabel17.setText("Order ID:");

        txtOrderFilterID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtOrderFilterIDKeyReleased(evt);
            }
        });

        jLabel18.setText("View Orders By Date:");

        cmdOrderViewByDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Yearly", "Monthly", "Daily" }));
        cmdOrderViewByDate.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmdOrderViewByDateItemStateChanged(evt);
            }
        });

        cmdOrderSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Order Date - Closest to Furthest", "Order Date - Furthest to Closest", "Approval Date - Closest to Furthest", "Approval Date - Furthest to Closest", "Order Total - Highest to Lowest", "Order Total - Lowest to Highest" }));
        cmdOrderSort.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmdOrderSortItemStateChanged(evt);
            }
        });

        jLabel19.setText("Priority Sort:");

        btnViewSelectedOrder.setText("View Selected Order");
        btnViewSelectedOrder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnViewSelectedOrderMouseClicked(evt);
            }
        });

        bntGenerateAllOrderReport.setText("Generate Order Report");
        bntGenerateAllOrderReport.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                bntGenerateAllOrderReportMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAllOrdersLayout = new javax.swing.GroupLayout(pnlAllOrders);
        pnlAllOrders.setLayout(pnlAllOrdersLayout);
        pnlAllOrdersLayout.setHorizontalGroup(
            pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                    .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOrderFilterUsername)
                            .addComponent(txtOrderFilterID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(cmdOrderSort, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAllOrdersLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdOrderViewByDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAllOrdersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bntGenerateAllOrderReport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewSelectedOrder)))
                .addContainerGap())
        );
        pnlAllOrdersLayout.setVerticalGroup(
            pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdOrderSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdOrderViewByDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)))
                    .addGroup(pnlAllOrdersLayout.createSequentialGroup()
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOrderFilterUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtOrderFilterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewSelectedOrder)
                    .addComponent(bntGenerateAllOrderReport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpOrderControls.addTab("All Orders", pnlAllOrders);

        tblOrderRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Stock Order ID", "Staff Username", "Order Date", "Order Total", "Order Status"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblOrderRequests);

        jLabel4.setText("Search Username:");

        txtRequestOrderFilterUsername.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtRequestOrderFilterUsernameKeyReleased(evt);
            }
        });

        jLabel5.setText("Priority Sort:");

        jLabel6.setText("View Orders By Date:");

        cmdOrderPrioritySort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Order Date - Closest to Furthest", "Order Date - Furthest to Closest", "Order Total - Highest to Lowest", "Order Total - Lowest to Highest" }));
        cmdOrderPrioritySort.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmdOrderPrioritySortItemStateChanged(evt);
            }
        });

        cmdOrderRequestViewByDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Yearly", "Monthly", "Daily" }));
        cmdOrderRequestViewByDate.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cmdOrderRequestViewByDateItemStateChanged(evt);
            }
        });

        jLabel10.setText("Stock Order ID:");

        txtOrderRequestFilterID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtOrderRequestFilterIDKeyReleased(evt);
            }
        });

        btnViewSelectedStaffOrder.setText("View Selected Order");
        btnViewSelectedStaffOrder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnViewSelectedStaffOrderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlOrderRequestsLayout = new javax.swing.GroupLayout(pnlOrderRequests);
        pnlOrderRequests.setLayout(pnlOrderRequestsLayout);
        pnlOrderRequestsLayout.setHorizontalGroup(
            pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRequestOrderFilterUsername)
                            .addComponent(txtOrderRequestFilterID, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(cmdOrderPrioritySort, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOrderRequestsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdOrderRequestViewByDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOrderRequestsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnViewSelectedStaffOrder)))
                .addContainerGap())
        );
        pnlOrderRequestsLayout.setVerticalGroup(
            pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdOrderPrioritySort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdOrderRequestViewByDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(pnlOrderRequestsLayout.createSequentialGroup()
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRequestOrderFilterUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlOrderRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtOrderRequestFilterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewSelectedStaffOrder)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpOrderControls.addTab("Order Requests", pnlOrderRequests);

        tblPurchaseOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Purchase Order ID", "Stock Name", "Date", "Quantity", "Price"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblPurchaseOrders);

        btnAcceptPurchaseOrder.setText("Accept Purchase Order Request");
        btnAcceptPurchaseOrder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnAcceptPurchaseOrderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlStockOrdeersLayout = new javax.swing.GroupLayout(pnlStockOrdeers);
        pnlStockOrdeers.setLayout(pnlStockOrdeersLayout);
        pnlStockOrdeersLayout.setHorizontalGroup(
            pnlStockOrdeersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStockOrdeersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStockOrdeersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStockOrdeersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAcceptPurchaseOrder)))
                .addContainerGap())
        );
        pnlStockOrdeersLayout.setVerticalGroup(
            pnlStockOrdeersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStockOrdeersLayout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcceptPurchaseOrder)
                .addContainerGap())
        );

        tpOrderControls.addTab("Purchase Orders", pnlStockOrdeers);

        javax.swing.GroupLayout pnlViewAllOrdersLayout = new javax.swing.GroupLayout(pnlViewAllOrders);
        pnlViewAllOrders.setLayout(pnlViewAllOrdersLayout);
        pnlViewAllOrdersLayout.setHorizontalGroup(
            pnlViewAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewAllOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlViewAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpOrderControls)
                    .addGroup(pnlViewAllOrdersLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnCloseViewOrdersTab)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlViewAllOrdersLayout.setVerticalGroup(
            pnlViewAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlViewAllOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpOrderControls, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCloseViewOrdersTab)
                .addContainerGap())
        );

        tpAdminControls.addTab("Orders", pnlViewAllOrders);

        jmAdminApplication.setText("Application");

        jmAdminLogout.setText("Logout");
        jmAdminLogout.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAdminLogoutActionPerformed(evt);
            }
        });
        jmAdminApplication.add(jmAdminLogout);
        jmAdminApplication.add(jSeparator1);

        jmAdminClose.setText("Close");
        jmAdminClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAdminCloseActionPerformed(evt);
            }
        });
        jmAdminApplication.add(jmAdminClose);

        jmbAdminModule.add(jmAdminApplication);

        jmAccounts.setText("Accounts");

        jmManageUsers.setText("Manage Staff");
        jmManageUsers.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmManageUsersActionPerformed(evt);
            }
        });
        jmAccounts.add(jmManageUsers);

        jmbAdminModule.add(jmAccounts);

        jmStationary.setText("Stationary");

        jmManageAllStock.setText("Manage All Stock");
        jmManageAllStock.setActionCommand("");
        jmManageAllStock.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmManageAllStockActionPerformed(evt);
            }
        });
        jmStationary.add(jmManageAllStock);

        jmInserStock.setText("Insert Stock");
        jmInserStock.setActionCommand("Insert New Stock");
        jmInserStock.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmInserStockActionPerformed(evt);
            }
        });
        jmStationary.add(jmInserStock);

        jmbAdminModule.add(jmStationary);

        jmOrders.setText("Manage Orders");

        jmViewAllOrders.setText("View Orders");
        jmViewAllOrders.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmViewAllOrdersActionPerformed(evt);
            }
        });
        jmOrders.add(jmViewAllOrders);

        jmbAdminModule.add(jmOrders);

        setJMenuBar(jmbAdminModule);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpAdminControls)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpAdminControls)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Load Table Data">
    private void LoadAllUsers()
    {
        users = new Staff().LoadUsers();
    }
    
    private void LoadRegisterRequests()
    {
        registerStaff = new Staff().LoadRegisterRequests();
    }
    
    private void LoadTableAllOrders()
    {
        allOrders = new Order().LoadAllOrders();
    }
    
    private void LoadTableAllPendingOrders()
    {
        pendingOrders = new StaffOrder().LoadAllPendingOrders();
    }
    
    private void LoadTableAllStockData() {
        stationaryStock = new StationaryStock().LoadStationaryStock();
    }
    
    private void LoadTablePurchaseOrdersData()
    {
        purchaseOrders = new PurchaseOrder().LoadAllPurchaseOrders();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Populate Tables">
    private void PopulateTablePurchaseOrders()
    {
        try {
            DefaultTableModel model = (DefaultTableModel) tblPurchaseOrders.getModel();
            model.setRowCount(0);

            for (PurchaseOrder po : purchaseOrders) {
                Object[] o = new Object[5];
                o[0] = po.getPurchaseOrderID();
                o[1] = new StationaryStock().GetProductFromID(po.getStationaryStockID());
                o[2] = po.getPurchaseDate();
                o[3] = po.getQuantity();
                o[4] = po.getPrice();
                model.addRow(o);
            }

            tblPurchaseOrders.setModel(model);
            tblPurchaseOrders.getColumnModel().getColumn(0).setPreferredWidth(15);
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }
    private void PopulateTableUsers() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblViewUsers.getModel();
            model.setRowCount(0);

            for (Staff staffData : users) {
                Object[] o = new Object[9];
                o[0] = staffData.getStaffID();
                o[1] = staffData.getStaffName();
                o[2] = staffData.getStaffSurname();
                o[3] = new Campus().GetCampus(staffData.getStaffCampusID()).getCampusName();
                o[4] = new Department().GetDepartment(staffData.getStaffDepartment()).getDepartmentName();
                o[5] = staffData.getStaffCellNo();
                o[6] = staffData.getStaffEmail();
                o[7] = staffData.getStaffUsername();
                o[8] = staffData.getStaffPassword();
                model.addRow(o);
            }

            tblViewUsers.setModel(model);
            tblViewUsers.getColumnModel().getColumn(0).setPreferredWidth(15);
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }

    private void PopulateTableRegister() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblRegisterRequests.getModel();
            model.setRowCount(0);
            
            for (Staff staffData : registerStaff) {
                Object[] o = new Object[9];
                o[0] = staffData.getRegisterRequestDate();
                o[1] = staffData.getStaffName();
                o[2] = staffData.getStaffSurname();
                o[3] = new Campus().GetCampus(staffData.getStaffCampusID()).getCampusName();
                o[4] = new Department().GetDepartment(staffData.getStaffDepartment()).getDepartmentName();
                o[5] = staffData.getStaffCellNo();
                o[6] = staffData.getStaffEmail();
                o[7] = staffData.getStaffUsername();
                o[8] = staffData.getStaffPassword();
                model.addRow(o);
            }

            tblRegisterRequests.setModel(model);
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }

    private void PopulateTableAllStock() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
            model.setRowCount(0);
            
            for (StationaryStock stockData : stationaryStock) {
                Object[] o = new Object[9];
                o[0] = stockData.getStationaryStockID();
                o[1] = stockData.getProductName();
                o[2] = new StationaryCategory().GetStationaryCategory(stockData.getStationaryCategoryID()).getName();
                o[3] = stockData.getModel();
                o[4] = stockData.getPrice();
                o[5] = stockData.getQuantity();
                o[6] = stockData.getDateOfEntryUpdate();
                model.addRow(o);
            }

            tblAllStock.setModel(model);

            tblAllStock.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblAllStock.getColumnModel().getColumn(4).setPreferredWidth(30);
            tblAllStock.getColumnModel().getColumn(5).setPreferredWidth(30);
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }

    private void PopulateTableAllOrders() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
            model.setRowCount(0);

            for (Order order : allOrders) {
                Object[] o = new Object[6];
                o[0] = order.getOrderID();
                o[1] = order.getStaffOrder().getStaffStockOrderID();
                o[2] = new Staff().ReadStaffMemberOrder(order.getStaffOrder().getStaffID()).getStaffUsername();
                o[3] = order.getStaffOrder().getOrderDate();
                o[4] = order.getApprovalDate();
                o[5] = order.getStaffOrder().getOrderTotal();
                model.addRow(o);

                tblAllOrders.setModel(model);
            }
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }
    
    private void PopulateTableAllPendingOrders()
    {
        try {
            DefaultTableModel model = (DefaultTableModel) tblOrderRequests.getModel();
            model.setRowCount(0);

            for (StaffOrder staffOrder : pendingOrders) {
                Object[] o = new Object[5];
                o[0] = staffOrder.getStaffStockOrderID();
                o[1] = new Staff().ReadStaffMemberOrder(staffOrder.getStaffID()).getStaffUsername();
                o[2] = staffOrder.getOrderDate();
                o[3] = staffOrder.getOrderTotal();
                o[4] = staffOrder.getOrderStatus();
                model.addRow(o);

                tblOrderRequests.setModel(model);
            }
        } catch (Exception ex) {
            Helper.DisplayError(ex.toString());
        }
    }
    // </editor-fold>

    private void jmAdminCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmAdminCloseActionPerformed
    {//GEN-HEADEREND:event_jmAdminCloseActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to close this application?", "User Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jmAdminCloseActionPerformed

    private void jmAdminLogoutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmAdminLogoutActionPerformed
    {//GEN-HEADEREND:event_jmAdminLogoutActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "User Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            AuthenticationSettings.setAdminConnected(false);
            new Thread(new BC_StationaryManagementSystem()).start();
        }
    }//GEN-LAST:event_jmAdminLogoutActionPerformed

    private void SelectRowStock() {
        try {
            for (StationaryStock stock : stationaryStock) {
                if (tblAllStock.getValueAt(tblAllStock.getSelectedRow(), 0).toString().equals(String.valueOf(stock.getStationaryStockID()))) {
                    selectedStock = stock;
                }
            }
            if (selectedStock == null) {
                Helper.DisplayError("An Error Occoured When Selecting Active Row");
            }
        } catch (ArrayIndexOutOfBoundsException aiob) {
            Helper.DisplayError("Please Select A Row First before Making a selection");
        }
    }

    private void SelectRowRegister() {
        try {
            for (Staff staff : registerStaff) {
                if (tblRegisterRequests.getValueAt(tblRegisterRequests.getSelectedRow(), 7).toString().equals(staff.getStaffUsername())) {
                    selectedStaff = staff;
                }
            }
            if (selectedStaff == null) {
                Helper.DisplayError("An Error Occoured When Selecting Active Row");
            }
        } catch (ArrayIndexOutOfBoundsException aiob) {
            Helper.DisplayError("Please Select A Row First before Making a selection");
        }
    }
    private void jmManageAllStockActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmManageAllStockActionPerformed
    {//GEN-HEADEREND:event_jmManageAllStockActionPerformed
        LoadStationaryTab();
    }//GEN-LAST:event_jmManageAllStockActionPerformed

    private void jmInserStockActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmInserStockActionPerformed
    {//GEN-HEADEREND:event_jmInserStockActionPerformed
        StockDialog sd = new StockDialog(null, true);
        sd.setVisible(true);
    }//GEN-LAST:event_jmInserStockActionPerformed

    private void jmViewAllOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmViewAllOrdersActionPerformed
        LoadOrdersTab();
    }//GEN-LAST:event_jmViewAllOrdersActionPerformed

    private void btnCloseViewOrdersTabMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnCloseViewOrdersTabMouseClicked
    {//GEN-HEADEREND:event_btnCloseViewOrdersTabMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tblOrderRequests.getModel();
        model2.setRowCount(0);
        tpAdminControls.remove(tabPanels[2]);
        loadedTabs[2] = false;
    }//GEN-LAST:event_btnCloseViewOrdersTabMouseClicked

    private void cmbPrioritySortItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbPrioritySortItemStateChanged
    {//GEN-HEADEREND:event_cmbPrioritySortItemStateChanged
        switch (cmbPrioritySort.getSelectedIndex())
        {
            case 0:
                Collections.sort(stationaryStock, new StockID());
                break;
            case 1:
            Collections.sort(stationaryStock, new StockPrice_HighToLow());
            break;

            case 2:
            Collections.sort(stationaryStock, new StockPrice_LowToHigh());
            break;

            case 3:
            Collections.sort(stationaryStock, new StockQuantity_HighToLow());
            break;

            case 4:
            Collections.sort(stationaryStock, new StockQuantity_LowToHigh());
            break;

            case 5:
            Collections.sort(stationaryStock, new StockDate_ClosestToFurthest());
            break;

            case 6:
            Collections.sort(stationaryStock, new StockDate_FurthestToClosest());
            break;
        }

        DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
        model.setRowCount(0);
        PopulateTableAllStock();
    }//GEN-LAST:event_cmbPrioritySortItemStateChanged

    private void cmbFilterCategoryItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbFilterCategoryItemStateChanged
    {//GEN-HEADEREND:event_cmbFilterCategoryItemStateChanged
        if (formLoad)
        {
            try
            {
                LoadTableAllStockData();
                List<StationaryStock> stockToRemove = new ArrayList<>();

                for (StationaryStock stock : stationaryStock)
                {
                    for (StationaryCategory category : categories)
                    {
                        if (category.getName().equalsIgnoreCase(cmbFilterCategory.getSelectedItem().toString()))
                        {
                            if (stock.getStationaryCategoryID() != category.getStationaryCategoryID())
                            {
                                stockToRemove.add(stock);
                            }
                        }
                    }
                }

                stationaryStock.removeAll(stockToRemove);

                DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
                model.setRowCount(0);
                PopulateTableAllStock();

                if (stationaryStock.size() <= 0)
                {
                    Helper.DisplayError("There are no categories located in requested filter", "Filter Information");
                }
            } catch (Exception ex)
            {
                if (ex instanceof NullPointerException)
                {
                    LoadTableAllStockData();
                    PopulateTableAllStock();
                } else
                {
                    Helper.DisplayError(ex.toString(), "Error In Filter");
                }
            }
        }
    }//GEN-LAST:event_cmbFilterCategoryItemStateChanged

    private void txtFilterProductNameKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtFilterProductNameKeyReleased
    {//GEN-HEADEREND:event_txtFilterProductNameKeyReleased
        try
        {
            LoadTableAllStockData();
            List<StationaryStock> stockToRemove = new ArrayList<>();

            for (StationaryStock stock : stationaryStock)
            {
                if (!stock.getProductName().toUpperCase().contains(txtFilterProductName.getText().toUpperCase()))
                {
                    stockToRemove.add(stock);
                }
            }

            stationaryStock.removeAll(stockToRemove);

            DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
            model.setRowCount(0);
            PopulateTableAllStock();

            if (stationaryStock.size() <= 0)
            {
                Helper.DisplayError("There are no products that match that filter", "Filter Information");
            }
        } catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtFilterProductNameKeyReleased

    private void btnDeleteStockMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDeleteStockMouseClicked
    {//GEN-HEADEREND:event_btnDeleteStockMouseClicked
        try
        {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this selection?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
                SelectRowStock();
                selectedStock.DeleteStockItem();
                stationaryStock.remove(selectedStock);

                DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
                model.setRowCount(0);

                PopulateTableAllStock();
                JOptionPane.showConfirmDialog(this, "Stock Item Succesfully Updated!", "Update Succesful", JOptionPane.DEFAULT_OPTION);
            }
        } catch (NullPointerException npe)
        {
            Helper.DisplayError("There is no data currently available in the table");
        }
        finally
        {
            LoadTableAllStockData();
            PopulateTableAllStock();
        }
    }//GEN-LAST:event_btnDeleteStockMouseClicked

    private void btnPromptUpdateMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnPromptUpdateMouseClicked
    {//GEN-HEADEREND:event_btnPromptUpdateMouseClicked
        try
        {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this selection?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
                SelectRowStock();
                ActiveAccess.setCurrentStock(selectedStock);
                StockDialog sd = new StockDialog(null, true);
                sd.setVisible(true);

                stationaryStock.remove(selectedStock);

                DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
                model.setRowCount(0);

                PopulateTableAllStock();
            }
        } catch (NullPointerException npe)
        {
            Helper.DisplayError("There is no data currently available in the table");
        }
        finally
        {
            LoadTableAllStockData();
            PopulateTableAllStock();
        }
    }//GEN-LAST:event_btnPromptUpdateMouseClicked

    private void btnViewAllStockBackMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnViewAllStockBackMouseClicked
    {//GEN-HEADEREND:event_btnViewAllStockBackMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblAllStock.getModel();
        model.setRowCount(0);
        tpAdminControls.remove(tabPanels[1]);
        loadedTabs[1] = false;
    }//GEN-LAST:event_btnViewAllStockBackMouseClicked

    private void btnViewUsersBackMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnViewUsersBackMouseClicked
    {//GEN-HEADEREND:event_btnViewUsersBackMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblViewUsers.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tblRegisterRequests.getModel();
        model2.setRowCount(0);
        tpAdminControls.remove(tabPanels[0]);
        loadedTabs[0] = false;
    }//GEN-LAST:event_btnViewUsersBackMouseClicked

    private void btnAcceptUserMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnAcceptUserMouseClicked
    {//GEN-HEADEREND:event_btnAcceptUserMouseClicked
        try
        {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to accept this user registration?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
                SelectRowRegister();
                selectedStaff.InsertStaff();
                registerStaff.remove(selectedStaff);
                tblRegisterRequests.setModel(new DefaultTableModel());
                PopulateTableRegister();
                JOptionPane.showConfirmDialog(this, "User succesfully Accepted", "Registration Completed", JOptionPane.DEFAULT_OPTION);
            }
        } catch (NullPointerException npe)
        {
            Helper.DisplayError("There is no data currently available in the table");
        }
        finally
        {
            LoadAllUsers();
            LoadRegisterRequests();
            PopulateTableUsers();
            PopulateTableRegister();
        }
    }//GEN-LAST:event_btnAcceptUserMouseClicked

    private void btnDeleteRegisterUserMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDeleteRegisterUserMouseClicked
    {//GEN-HEADEREND:event_btnDeleteRegisterUserMouseClicked
        try
        {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user registration??", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
                SelectRowRegister();
                selectedStaff.DeleteRegistry();
                registerStaff.remove(selectedStaff);

                DefaultTableModel model = (DefaultTableModel) tblRegisterRequests.getModel();
                model.setRowCount(0);

                PopulateTableRegister();
                JOptionPane.showConfirmDialog(this, "User succesfully Removed", "Registration Completed", JOptionPane.DEFAULT_OPTION);
            }
        } catch (NullPointerException npe)
        {
            Helper.DisplayError("There is no data currently available in the table");
        }
    }//GEN-LAST:event_btnDeleteRegisterUserMouseClicked

    private void jmManageUsersActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmManageUsersActionPerformed
    {//GEN-HEADEREND:event_jmManageUsersActionPerformed
        LoadManageUsersTab();
    }//GEN-LAST:event_jmManageUsersActionPerformed

    private void txtRequestOrderFilterUsernameKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtRequestOrderFilterUsernameKeyReleased
    {//GEN-HEADEREND:event_txtRequestOrderFilterUsernameKeyReleased
        try
        {
            LoadTableAllPendingOrders();
            List<StaffOrder> staffOrderToRemove = new ArrayList<>();
            
            for (StaffOrder staffOrder : pendingOrders)
            {
                if(!new Staff().ReadStaffMemberOrder(staffOrder.getStaffID()).getStaffUsername().toUpperCase().contains(txtRequestOrderFilterUsername.getText().toUpperCase()))
                {
                    staffOrderToRemove.add(staffOrder);
                }
            }
            
            pendingOrders.removeAll(staffOrderToRemove);
            
            DefaultTableModel model = (DefaultTableModel) tblOrderRequests.getModel();
            model.setRowCount(0);
            PopulateTableAllPendingOrders();

            if (pendingOrders.size() <= 0)
            {
                throw new FilterException("There are no orders that match that filter");
            }
        }
        catch (FilterException fe)
        {
            Helper.DisplayError(fe.getMessage(), "Filter Exception");
        } 
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtRequestOrderFilterUsernameKeyReleased

    private void txtOrderRequestFilterIDKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtOrderRequestFilterIDKeyReleased
    {//GEN-HEADEREND:event_txtOrderRequestFilterIDKeyReleased
        try
        {
            LoadTableAllPendingOrders();
            List<StaffOrder> staffOrderToRemove = new ArrayList<>();
            
            for (StaffOrder staffOrder : pendingOrders)
            {
                if(!staffOrder.getStaffStockOrderID().toUpperCase().contains(txtOrderRequestFilterID.getText().toUpperCase()))
                {
                    staffOrderToRemove.add(staffOrder);
                }
            }
            
            pendingOrders.removeAll(staffOrderToRemove);
            
            DefaultTableModel model = (DefaultTableModel) tblOrderRequests.getModel();
            model.setRowCount(0);
            PopulateTableAllPendingOrders();

            if (pendingOrders.size() <= 0)
            {
                throw new FilterException("There are no orders that match that filter");
            }
        }
        catch (FilterException fe)
        {
            Helper.DisplayError(fe.getMessage(), "Filter Exception");
        } 
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtOrderRequestFilterIDKeyReleased

    private void txtOrderFilterUsernameKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtOrderFilterUsernameKeyReleased
    {//GEN-HEADEREND:event_txtOrderFilterUsernameKeyReleased
        try
        {
            LoadTableAllOrders();
            List<Order> orderToRemove = new ArrayList<>();
            
            for (Order order : allOrders)
            {
                if(!new Staff().ReadStaffMemberOrder(order.getStaffOrder().getStaffID()).getStaffUsername().toUpperCase().contains(txtOrderFilterUsername.getText().toUpperCase()))
                {
                    orderToRemove.add(order);
                }
            }
            
            allOrders.removeAll(orderToRemove);
            
            DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
            model.setRowCount(0);
            PopulateTableAllOrders();

            if (allOrders.size() <= 0)
            {
                throw new FilterException("There are no orders that match that filter");
            }
        }
        catch (FilterException fe)
        {
            Helper.DisplayError(fe.getMessage(), "Filter Exception");
        } 
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtOrderFilterUsernameKeyReleased

    private void txtOrderFilterIDKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtOrderFilterIDKeyReleased
    {//GEN-HEADEREND:event_txtOrderFilterIDKeyReleased
        try
        {
            LoadTableAllOrders();
            List<Order> orderToRemove = new ArrayList<>();
            
            for (Order order : allOrders)
            {
                if(!order.getOrderID().toUpperCase().contains(txtOrderFilterID.getText().toUpperCase()))
                {
                    orderToRemove.add(order);
                }
            }
            
            allOrders.removeAll(orderToRemove);
            
            DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
            model.setRowCount(0);
            PopulateTableAllOrders();

            if (allOrders.size() <= 0)
            {
                throw new FilterException("There are no orders that match that filter");
            }
        }
        catch (FilterException fe)
        {
            Helper.DisplayError(fe.getMessage(), "Filter Exception");
        } 
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtOrderFilterIDKeyReleased

    private void cmdOrderSortItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmdOrderSortItemStateChanged
    {//GEN-HEADEREND:event_cmdOrderSortItemStateChanged
        switch(cmdOrderSort.getSelectedIndex())
        {
            case 0:
                Collections.sort(allOrders, new OrderID());
                break;
                
            case 1:
                Collections.sort(allOrders, new OrderDate_ClosestToFurthest());
                break;
                
            case 2:
                Collections.sort(allOrders, new OrderDate_FurthestToClosest());
                break;
                
            case 3:
                Collections.sort(allOrders, new OrderApprovalDate_ClosestToFurthest());
                break;
                
            case 4:
                Collections.sort(allOrders, new OrderApprovalDate_FurthestToClosest());
                break;
                
            case 5:
                Collections.sort(allOrders, new OrderTotal_HighestToLowest());
                break;
                
            case 6:
                Collections.sort(allOrders, new OrderTotal_LowestToHighest());
                break;    
        }
        
        DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
        model.setRowCount(0);
        PopulateTableAllOrders();
    }//GEN-LAST:event_cmdOrderSortItemStateChanged

    private void cmdOrderPrioritySortItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmdOrderPrioritySortItemStateChanged
    {//GEN-HEADEREND:event_cmdOrderPrioritySortItemStateChanged
        switch (cmdOrderPrioritySort.getSelectedIndex())
        {
            case 0:
                Collections.sort(pendingOrders, new StaffOrderID());
                break;
                        
            case 1:
                Collections.sort(pendingOrders, new StaffOrderDate_ClosestToFurthest());
                break;

            case 2:
                Collections.sort(pendingOrders, new StaffOrderDate_FurthestToClosest());
                break;

            case 3:          
                Collections.sort(pendingOrders, new StaffOrderTotal_HighestToLowest());
                break;

            case 4:             
                Collections.sort(pendingOrders, new StaffOrderTotal_LowestToHighest());
                break;
        }

        DefaultTableModel model = (DefaultTableModel) tblOrderRequests.getModel();
        model.setRowCount(0);
        PopulateTableAllPendingOrders();
    }//GEN-LAST:event_cmdOrderPrioritySortItemStateChanged

    private void cmdOrderRequestViewByDateItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmdOrderRequestViewByDateItemStateChanged
    {//GEN-HEADEREND:event_cmdOrderRequestViewByDateItemStateChanged
        List<StaffOrder> toRemove = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        LoadTableAllPendingOrders();
        
        switch(cmdOrderRequestViewByDate.getSelectedIndex())
        {        
            case 1:
                for (StaffOrder pendingOrder : pendingOrders)
                {
                    cal1.setTime(pendingOrder.getOrderDate());
                    cal2.setTime(new Date());
                    
                    boolean sameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
                    if(!sameYear)
                    {
                        toRemove.add(pendingOrder);
                    }
                }
                break;
                
            case 2:
                for (StaffOrder pendingOrder : pendingOrders)
                {
                    cal1.setTime(pendingOrder.getOrderDate());
                    cal2.setTime(new Date());
                    
                    boolean sameMonth = ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)));
                    if(!sameMonth)
                    {
                        toRemove.add(pendingOrder);
                    }
                }
                break;
                
            case 3:
                for (StaffOrder pendingOrder : pendingOrders)
                {
                    cal1.setTime(pendingOrder.getOrderDate());
                    cal2.setTime(new Date());
                    
                    boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
                    if(!sameDay)
                    {
                        toRemove.add(pendingOrder);
                    }
                }
                break;
        }
        
        pendingOrders.removeAll(toRemove);
        
        DefaultTableModel model = (DefaultTableModel) tblOrderRequests.getModel();
        model.setRowCount(0);
        PopulateTableAllPendingOrders();
    }//GEN-LAST:event_cmdOrderRequestViewByDateItemStateChanged

    private void cmdOrderViewByDateItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmdOrderViewByDateItemStateChanged
    {//GEN-HEADEREND:event_cmdOrderViewByDateItemStateChanged
        List<Order> toRemove = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        LoadTableAllOrders();
        
        switch(cmdOrderViewByDate.getSelectedIndex())
        {        
            case 1:
                for (Order order : allOrders)
                {
                    cal1.setTime(order.getApprovalDate());
                    cal2.setTime(new Date());
                    
                    boolean sameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
                    if(!sameYear)
                    {
                        toRemove.add(order);
                    }
                }
                break;
                
            case 2:
                for (Order order : allOrders)
                {
                    cal1.setTime(order.getApprovalDate());
                    cal2.setTime(new Date());
                    
                    boolean sameMonth = ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) && (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)));
                    if(!sameMonth)
                    {
                        toRemove.add(order);
                    }
                }
                break;
                
            case 3:
                for (Order order : allOrders)
                {
                    cal1.setTime(order.getApprovalDate());
                    cal2.setTime(new Date());
                    
                    boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
                    if(!sameDay)
                    {
                        toRemove.add(order);
                    }
                }
                break;
        }
        
        allOrders.removeAll(toRemove);
        
        DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
        model.setRowCount(0);
        PopulateTableAllOrders();
    }//GEN-LAST:event_cmdOrderViewByDateItemStateChanged

    private void btnViewSelectedOrderMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnViewSelectedOrderMouseClicked
    {//GEN-HEADEREND:event_btnViewSelectedOrderMouseClicked
        try
        {      
            String stockOrderID = tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 1).toString();
            String orderID = tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 0).toString();
            Date approvalDate = (Date)tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 4);
            Date orderDate = (Date)tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 3);
            double orderTotal = (Double)tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 5);
            String username = (String)tblAllOrders.getValueAt(tblAllOrders.getSelectedRow(), 2);
            
            Authentication.ActiveAccess.CurrentOrder = new CurrentOrder();
            Authentication.ActiveAccess.CurrentOrder.setOrderID(orderID);
            Authentication.ActiveAccess.CurrentOrder.setApprovalDate(approvalDate);
            Authentication.ActiveAccess.CurrentOrder.setTotalPrice(orderTotal);
            Authentication.ActiveAccess.CurrentOrder.setCurrentDate(orderDate);
            Authentication.ActiveAccess.CurrentOrder.setStaffUsername(username);
            Authentication.ActiveAccess.CurrentOrder.setStaffStockID(stockOrderID);
            Authentication.ActiveAccess.CurrentOrder.setViewOrder(true);
            
            SelectOrder(stockOrderID);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            Helper.DisplayError("Please ensure you select a table entry before performing this action", "Error in order selection");
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
    }//GEN-LAST:event_btnViewSelectedOrderMouseClicked

    private void btnViewSelectedStaffOrderMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnViewSelectedStaffOrderMouseClicked
    {//GEN-HEADEREND:event_btnViewSelectedStaffOrderMouseClicked
        try
        {      
            String stockOrderID = tblOrderRequests.getValueAt(tblOrderRequests.getSelectedRow(), 0).toString();
            Date orderDate = (Date)tblOrderRequests.getValueAt(tblOrderRequests.getSelectedRow(), 2);
            double orderTotal = (Double)tblOrderRequests.getValueAt(tblOrderRequests.getSelectedRow(), 3);
            String username = (String)tblOrderRequests.getValueAt(tblOrderRequests.getSelectedRow(), 1);
            
            Authentication.ActiveAccess.CurrentOrder = new CurrentOrder();

            Authentication.ActiveAccess.CurrentOrder.setTotalPrice(orderTotal);
            Authentication.ActiveAccess.CurrentOrder.setCurrentDate(orderDate);
            Authentication.ActiveAccess.CurrentOrder.setStaffUsername(username);
            Authentication.ActiveAccess.CurrentOrder.setStaffStockID(stockOrderID);
            Authentication.ActiveAccess.CurrentOrder.setViewStaffOrder(true);
            
            SelectOrder(stockOrderID);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            Helper.DisplayError("Please ensure you select a table entry before performing this action", "Error in order selection");
        }
        catch (Exception ex)
        {
            Helper.DisplayError(ex.toString());
        }
        finally
        {
            LoadTableAllOrders();
            LoadTableAllPendingOrders();
            LoadTablePurchaseOrdersData();
            PopulateTableAllOrders();
            PopulateTableAllPendingOrders();
            PopulateTablePurchaseOrders();
        }
    }//GEN-LAST:event_btnViewSelectedStaffOrderMouseClicked

    private void btnGenerateStockReportMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnGenerateStockReportMouseClicked
    {//GEN-HEADEREND:event_btnGenerateStockReportMouseClicked
        new ApplicationHelper.ReportGenerator(stationaryStock);
        Helper.DisplayError("Report Generated Successfully");
    }//GEN-LAST:event_btnGenerateStockReportMouseClicked

    private void bntGenerateAllOrderReportMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_bntGenerateAllOrderReportMouseClicked
    {//GEN-HEADEREND:event_bntGenerateAllOrderReportMouseClicked
        new ApplicationHelper.ReportGenerator(allOrders);
        Helper.DisplayError("Successfully generated report!");
    }//GEN-LAST:event_bntGenerateAllOrderReportMouseClicked

    private void btnAcceptPurchaseOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcceptPurchaseOrderMouseClicked
        try
        {
            String purchaseOrderID = tblPurchaseOrders.getValueAt(tblPurchaseOrders.getSelectedRow(), 0).toString();
            PurchaseOrder selectOrder;
            for (PurchaseOrder po : purchaseOrders) {
                if(po.getPurchaseOrderID().equalsIgnoreCase(purchaseOrderID))
                {
                    selectOrder = po;
                    selectOrder.UpdateAndRemovePurchaseOrder();
                }
            }
      
            Helper.DisplayError("Successfully processed purchase order");
            DefaultTableModel model = (DefaultTableModel) tblPurchaseOrders.getModel();
            model.setRowCount(0);
            LoadTablePurchaseOrdersData();
            PopulateTablePurchaseOrders();
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            Helper.DisplayError("Please select a row first to complete this action", "Row Selection Error");
        }
        finally
        {
            LoadTablePurchaseOrdersData();
            PopulateTablePurchaseOrders();
        }
    }//GEN-LAST:event_btnAcceptPurchaseOrderMouseClicked

    private void cmbFilterCampusItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbFilterCampusItemStateChanged
    {//GEN-HEADEREND:event_cmbFilterCampusItemStateChanged
        if (formLoad)
        {
            try
            {
                LoadAllUsers();
                List<Staff> staffToRemove = new ArrayList<>();

                for (Staff staff : users)
                {
                    for (Campus campus : filterCampus)
                    {
                        if (campus.getCampusName().equalsIgnoreCase(cmbFilterCampus.getSelectedItem().toString()))
                        {
                            if (campus.getCampusID() != staff.getStaffCampusID())
                            {
                                staffToRemove.add(staff);
                            }
                        }
                    }
                }

                users.removeAll(staffToRemove);

                DefaultTableModel model = (DefaultTableModel) tblViewUsers.getModel();
                model.setRowCount(0);
                PopulateTableUsers();

                if (users.size() <= 0)
                {
                    Helper.DisplayError("There are no users located in requested filter", "Filter Information");
                }
            } catch (Exception ex)
            {
                if (ex instanceof NullPointerException)
                {
                    LoadAllUsers();
                    PopulateTableUsers();
                } else
                {
                    Helper.DisplayError(ex.toString(), "Error In Filter");
                }
            }
        }
    }//GEN-LAST:event_cmbFilterCampusItemStateChanged

    private void cmbFilterDepartmentItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbFilterDepartmentItemStateChanged
    {//GEN-HEADEREND:event_cmbFilterDepartmentItemStateChanged
        if (formLoad)
        {
            try
            {
                LoadAllUsers();
                List<Staff> staffToRemove = new ArrayList<>();

                for (Staff staff : users)
                {
                    for (Department dept : filterDeparment)
                    {
                        if (dept.getDepartmentName().equalsIgnoreCase(cmbFilterDepartment.getSelectedItem().toString()))
                        {
                            if (dept.getDepartmentID() != staff.getStaffDepartment())
                            {
                                staffToRemove.add(staff);
                            }
                        }
                    }
                }

                users.removeAll(staffToRemove);

                DefaultTableModel model = (DefaultTableModel) tblViewUsers.getModel();
                model.setRowCount(0);
                PopulateTableUsers();

                if (users.size() <= 0)
                {
                    Helper.DisplayError("There are no users located in requested filter", "Filter Information");
                }
            } catch (Exception ex)
            {
                if (ex instanceof NullPointerException)
                {
                    LoadAllUsers();
                    PopulateTableUsers();
                } else
                {
                    Helper.DisplayError(ex.toString(), "Error In Filter");
                }
            }
        }
    }//GEN-LAST:event_cmbFilterDepartmentItemStateChanged

    private void txtFilterNameSurnameKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtFilterNameSurnameKeyReleased
    {//GEN-HEADEREND:event_txtFilterNameSurnameKeyReleased
        try
        {
            LoadAllUsers();
            List<Staff> staffToRemove = new ArrayList<>();

            for (Staff staff : users)
            {
                if (!staff.getStaffName().toUpperCase().contains(txtFilterNameSurname.getText().toUpperCase()))
                {
                    if(!staff.getStaffSurname().toUpperCase().contains(txtFilterNameSurname.getText().toUpperCase()))
                    {
                        staffToRemove.add(staff);
                    }
                }
            }

            users.removeAll(staffToRemove);

            DefaultTableModel model = (DefaultTableModel) tblViewUsers.getModel();
            model.setRowCount(0);
            PopulateTableUsers();

            if (users.size() <= 0)
            {
                Helper.DisplayError("There are no users that match that filter", "Filter Information");
            }
        } catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtFilterNameSurnameKeyReleased

    private void txtFilterNameSurnameRegKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtFilterNameSurnameRegKeyReleased
    {//GEN-HEADEREND:event_txtFilterNameSurnameRegKeyReleased
        try
        {
            LoadRegisterRequests();
            List<Staff> staffToRemove = new ArrayList<>();

            for (Staff staff : users)
            {
                if (!staff.getStaffName().toUpperCase().contains(txtFilterNameSurnameReg.getText().toUpperCase()))
                {
                    if(!staff.getStaffSurname().toUpperCase().contains(txtFilterNameSurnameReg.getText().toUpperCase()))
                    {
                        staffToRemove.add(staff);
                    }
                }
            }

            registerStaff.removeAll(staffToRemove);

            DefaultTableModel model = (DefaultTableModel) tblRegisterRequests.getModel();
            model.setRowCount(0);
            PopulateTableRegister();

            if (registerStaff.size() <= 0)
            {
                Helper.DisplayError("There are no users that match that filter", "Filter Information");
            }
        } catch (Exception ex)
        {
            Helper.DisplayError(ex.toString(), "Error In Filter");
        }
    }//GEN-LAST:event_txtFilterNameSurnameRegKeyReleased

    private void cmbFilterCampusRegItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbFilterCampusRegItemStateChanged
    {//GEN-HEADEREND:event_cmbFilterCampusRegItemStateChanged
        if (formLoad)
        {
            try
            {
                LoadRegisterRequests();
                List<Staff> staffToRemove = new ArrayList<>();

                for (Staff staff : registerStaff)
                {
                    for (Campus campus : filterCampus)
                    {
                        if (campus.getCampusName().equalsIgnoreCase(cmbFilterCampusReg.getSelectedItem().toString()))
                        {
                            if (campus.getCampusID() != staff.getStaffCampusID())
                            {
                                staffToRemove.add(staff);
                            }
                        }
                    }
                }

                registerStaff.removeAll(staffToRemove);

                DefaultTableModel model = (DefaultTableModel) tblRegisterRequests.getModel();
                model.setRowCount(0);
                PopulateTableRegister();

                if (registerStaff.size() <= 0)
                {
                    Helper.DisplayError("There are no users located in requested filter", "Filter Information");
                }
            } catch (Exception ex)
            {
                if (ex instanceof NullPointerException)
                {
                    LoadRegisterRequests();
                    PopulateTableRegister();
                } else
                {
                    Helper.DisplayError(ex.toString(), "Error In Filter");
                }
            }
        }
    }//GEN-LAST:event_cmbFilterCampusRegItemStateChanged

    private void cmbFilterDepartmentRegItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cmbFilterDepartmentRegItemStateChanged
    {//GEN-HEADEREND:event_cmbFilterDepartmentRegItemStateChanged
        if (formLoad)
        {
            try
            {
                LoadRegisterRequests();
                List<Staff> staffToRemove = new ArrayList<>();

                for (Staff staff : registerStaff)
                {
                    for (Department dept : filterDeparment)
                    {
                        if (dept.getDepartmentName().equalsIgnoreCase(cmbFilterDepartmentReg.getSelectedItem().toString()))
                        {
                            if (dept.getDepartmentID() != staff.getStaffDepartment())
                            {
                                staffToRemove.add(staff);
                            }
                        }
                    }
                }

                registerStaff.removeAll(staffToRemove);

                DefaultTableModel model = (DefaultTableModel) tblRegisterRequests.getModel();
                model.setRowCount(0);
                PopulateTableRegister();

                if (registerStaff.size() <= 0)
                {
                    Helper.DisplayError("There are no users located in requested filter", "Filter Information");
                }
            } catch (Exception ex)
            {
                if (ex instanceof NullPointerException)
                {
                    LoadRegisterRequests();
                    PopulateTableRegister();
                } else
                {
                    Helper.DisplayError(ex.toString(), "Error In Filter");
                }
            }
        }
    }//GEN-LAST:event_cmbFilterDepartmentRegItemStateChanged

    private void SelectOrder(String stockOrderID)
    {
        Authentication.ActiveAccess.CurrentOrder.setOrderStock(new StaffStockOrder().GetOrderDetails(stockOrderID));
        Authentication.ActiveAccess.CurrentOrderList = Authentication.ActiveAccess.CurrentOrder.getOrderStock();
        OrderDialog od = new OrderDialog(null, true);
        od.setVisible(true);
        
        Authentication.ActiveAccess.CurrentOrder = new CurrentOrder();
        Authentication.ActiveAccess.CurrentOrderList.clear();
        
        DefaultTableModel model = (DefaultTableModel) tblAllOrders.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tblOrderRequests.getModel();
        model2.setRowCount(0);
        
        LoadTableAllOrders();
        LoadTableAllPendingOrders();
        PopulateTableAllOrders();
        PopulateTableAllPendingOrders();
    }
    
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
            java.util.logging.Logger.getLogger(JAdminModule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAdminModule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAdminModule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAdminModule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JAdminModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntGenerateAllOrderReport;
    private javax.swing.JButton btnAcceptPurchaseOrder;
    private javax.swing.JButton btnAcceptUser;
    private javax.swing.JButton btnCloseViewOrdersTab;
    private javax.swing.JButton btnDeleteRegisterUser;
    private javax.swing.JButton btnDeleteStock;
    private javax.swing.JButton btnGenerateStockReport;
    private javax.swing.JButton btnPromptUpdate;
    private javax.swing.JButton btnViewAllStockBack;
    private javax.swing.JButton btnViewSelectedOrder;
    private javax.swing.JButton btnViewSelectedStaffOrder;
    private javax.swing.JButton btnViewUsersBack;
    private javax.swing.JComboBox<String> cmbFilterCampus;
    private javax.swing.JComboBox<String> cmbFilterCampusReg;
    private javax.swing.JComboBox<String> cmbFilterCategory;
    private javax.swing.JComboBox<String> cmbFilterDepartment;
    private javax.swing.JComboBox<String> cmbFilterDepartmentReg;
    private javax.swing.JComboBox<String> cmbPrioritySort;
    private javax.swing.JComboBox<String> cmdOrderPrioritySort;
    private javax.swing.JComboBox<String> cmdOrderRequestViewByDate;
    private javax.swing.JComboBox<String> cmdOrderSort;
    private javax.swing.JComboBox<String> cmdOrderViewByDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu jmAccounts;
    private javax.swing.JMenu jmAdminApplication;
    private javax.swing.JMenuItem jmAdminClose;
    private javax.swing.JMenuItem jmAdminLogout;
    private javax.swing.JMenuItem jmInserStock;
    private javax.swing.JMenuItem jmManageAllStock;
    private javax.swing.JMenuItem jmManageUsers;
    private javax.swing.JMenu jmOrders;
    private javax.swing.JMenu jmStationary;
    private javax.swing.JMenuItem jmViewAllOrders;
    private javax.swing.JMenuBar jmbAdminModule;
    private javax.swing.JPanel pnlAllOrders;
    private javax.swing.JPanel pnlAllStaff;
    private javax.swing.JPanel pnlAllStaff1;
    private javax.swing.JPanel pnlManageStaff;
    private javax.swing.JPanel pnlOrderRequests;
    private javax.swing.JPanel pnlRegistrationRequests;
    private javax.swing.JPanel pnlStockOrdeers;
    private javax.swing.JPanel pnlViewAllOrders;
    private javax.swing.JPanel pnlViewAllStock;
    private javax.swing.JTable tblAllOrders;
    private javax.swing.JTable tblAllStock;
    private javax.swing.JTable tblOrderRequests;
    private javax.swing.JTable tblPurchaseOrders;
    private javax.swing.JTable tblRegisterRequests;
    private javax.swing.JTable tblViewUsers;
    private javax.swing.JTable tblViewUsers1;
    private javax.swing.JTabbedPane tpAdminControls;
    private javax.swing.JTabbedPane tpManageStaff;
    private javax.swing.JTabbedPane tpOrderControls;
    private javax.swing.JTextField txtFilterNameSurname;
    private javax.swing.JTextField txtFilterNameSurnameReg;
    private javax.swing.JTextField txtFilterProductName;
    private javax.swing.JTextField txtOrderFilterID;
    private javax.swing.JTextField txtOrderFilterUsername;
    private javax.swing.JTextField txtOrderRequestFilterID;
    private javax.swing.JTextField txtRequestOrderFilterUsername;
    // End of variables declaration//GEN-END:variables
}
