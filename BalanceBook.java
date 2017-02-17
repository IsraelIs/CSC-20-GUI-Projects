/*
Isaac Israel
CSC20
Section 1
Final Lab
*/
 
 import java.awt.*;
 import java.io.*;
 import java.awt.event.*;
 import javax.swing.*;

 public class BalanceBook extends JPanel implements ActionListener{
       //create buttons
       static JButton NewAcc = new JButton (" Create a New Account");
       static JButton TransLoad = new JButton("Load Transaction");
       static JButton TransAdd = new JButton(" Add New Transactions");
       static JButton TransSearch = new JButton(" Search Transactions");
       static JButton TransSort = new JButton("Sort Transactions");
       static JButton TransViewandDelete = new JButton("View/Delete Transactions");
       static JButton TransBackUp = new JButton("Backup Transactions");
       static JButton QuitWindow = new JButton( "Exit");
       static JButton DelSelect = new JButton("Delete Selected");
       static JButton OkCreate = new JButton("Create");
       static JButton OopsDontCreate = new JButton(" Cancel");
       static JButton Load = new JButton("Load");
       static JButton OopsDontLoad = new JButton ("Cancel"); 
       static JButton TransSaveNew = new JButton("Save New Transaction");
       static JButton TransSort2 = new JButton("Sort Transactions");
       static JButton Delete = new JButton("Delete");
       static JButton TraAddTopMenu = new JButton("Top Menu");
       static JButton SearchTopMenu = new JButton("Top Menu");
       static JButton ViewDelTopMenu = new JButton("Top Menu");
       static JButton SortTTopMenu = new JButton("Top Menu");
       static JButton OkSearch = new JButton("Search");
       static JRadioButton SortDate = new JRadioButton("By Date");
       static JRadioButton SortType = new JRadioButton("By Type");
       static Container panelContent;
       static JScrollPane paneScroll = new JScrollPane();
       static JScrollPane paneScroll2 = new JScrollPane();
       static CardLayout houseofCards;
       //default var
       static String date,checknum,description, payment, deposit, account;
       static float finalBal;
       static float money = 0;
       static int TransNum = 0;
       static int varloadNum = 0;
       static int tempvar = 0;
       static JPanel stackofCards = new JPanel(houseofCards = new CardLayout());
       static JFrame myFrame = new JFrame("Budget Balance");
       static JScrollPane tempPane;
       static String[] infoTransactArr = {"Date","Trans. Type", "Check No.", "Trans. Description", "Payment/Debit(-)", "Deposit/Credit(+)", "Balance"} ;
       static String[][] stats;
       static JTable AddBalTbl;
       static DtndpBal[] balBook = new DtndpBal[999];
       static String[] transactionTypes = new String[] {"Deposit", "Automatic deposit", "ATM Withdrawal", "Check", "Debit Card"};
       static JComboBox transList = new JComboBox(transactionTypes);
       static String selectType;
       static JPanel CreateNewAcc = new JPanel(new BorderLayout());
       static JPanel loadmyTrans = new JPanel(new BorderLayout());
       static JPanel searchtheTrans = new JPanel();
       static JPanel viewTran = new JPanel(new BorderLayout());
       static JTextField TextFieldBal = new JTextField("0.0",10);
       static JTextField accountNameField = new JTextField("",10);      
       static JTextField accountNameText = new JTextField("",15);
       static JTextField initialBal = new JTextField("",15);
       static JTextField loadField = new JTextField("",15);
       static ButtonGroup groupofButtons = new ButtonGroup();
       static JTextField EnterDate = new JTextField(20);
       static JTextField EnterCheckNo = new JTextField(20);
       static JTextField EnterDescr = new JTextField(20);
       static JTextField EnterPayment = new JTextField(20);
       static JTextField EnterDeposit = new JTextField(20);
       static JTextField EnterSearch = new JTextField(20);
       static String nameofPerson = "";
       
       public static void main(String[] args){
          panelContent = myFrame.getContentPane();
          ActionListener ListenButtons = new BalanceBook();
          myFrame.setVisible(true);
          myFrame.setSize(720,240);
          myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JPanel BackBut = new JPanel(new BorderLayout());
          JPanel BackBut2 = new JPanel();
          JLabel BackBut3 = new JLabel("Use the Buttons below to Manage Transactions");
          BackBut3.setFont(new Font("", Font.BOLD, 20));
          BackBut2.add(BackBut3);
          //AL
          JPanel BackBut5 = new JPanel(new GridLayout(2,4,2,2)); 
          BackBut5.add(NewAcc);
          NewAcc.addActionListener(ListenButtons); 
          BackBut5.add(TransLoad);
          TransLoad.addActionListener(ListenButtons);
          BackBut5.add(TransAdd);
          TransAdd.addActionListener(ListenButtons);
          BackBut5.add(TransSearch);
          TransSearch.addActionListener(ListenButtons); 
          BackBut5.add(TransSort2);
          TransSort2.addActionListener(ListenButtons);
          BackBut5.add(TransViewandDelete);
          TransViewandDelete.addActionListener(ListenButtons);
          BackBut5.add(TransBackUp);
          TransBackUp.addActionListener(ListenButtons);
          BackBut5.add(QuitWindow);
          QuitWindow.addActionListener(ListenButtons);
          JPanel TextAccountName = new JPanel(new FlowLayout());
          JLabel TextTypeAccount = new JLabel("Account Name:");
          JLabel TextBalance = new JLabel ("Balance:");
          TextAccountName.add(TextTypeAccount);
          TextAccountName.add(accountNameField);
          TextAccountName.add(TextBalance);
          TextAccountName.add(TextFieldBal);
          TextFieldBal.setHorizontalAlignment(JTextField.RIGHT);
          JLabel cnaText = new JLabel("Create a New Acct",JLabel.CENTER);
          cnaText.setFont(new Font("",Font.BOLD,20));
          JPanel DefPanel = new JPanel();
          JPanel cnaPaneText = new JPanel(new GridLayout(2,2));
          cnaPaneText.add(new JLabel("Account Name:"));
          cnaPaneText.add(accountNameText);
          cnaPaneText.add(new JLabel("Initial Balance:"));
          cnaPaneText.add(initialBal);
          DefPanel. add(cnaPaneText);
          JPanel panel1 = new JPanel(new FlowLayout());
          panel1.add(OkCreate);
          panel1.add(OopsDontCreate);
          accountNameText.addActionListener(ListenButtons);
          initialBal.addActionListener(ListenButtons);
          OkCreate.addActionListener(ListenButtons);
          OopsDontCreate.addActionListener(ListenButtons);
          CreateNewAcc.add(cnaText, BorderLayout.NORTH);
          CreateNewAcc.add(DefPanel, BorderLayout.CENTER);
          CreateNewAcc.add(panel1, BorderLayout.SOUTH);
          JPanel panelforLoad = new JPanel();
          JLabel textloadButton = new JLabel("Load Transactions From a File");
          textloadButton.setFont(new Font("",Font.BOLD,20));
          panelforLoad.add(textloadButton);
          JPanel accountnamePane = new JPanel();
          accountnamePane.add(new JLabel("Account Name:"));
          accountnamePane.add(loadField);
          JPanel groupLoadButns = new JPanel(new FlowLayout());
          groupLoadButns.add(Load);
          groupLoadButns.add(OopsDontLoad);
          loadField.addActionListener(ListenButtons);
          Load.addActionListener(ListenButtons);
          OopsDontLoad.addActionListener(ListenButtons);
          loadmyTrans.add(panelforLoad, BorderLayout.NORTH);
          loadmyTrans.add(accountnamePane,BorderLayout.CENTER);
          loadmyTrans.add(groupLoadButns, BorderLayout.SOUTH);
          
          JPanel depositText = new JPanel();
          depositText.setLayout(new BorderLayout());
          JPanel locaofInfo = new JPanel(new GridLayout(6,2));
          JPanel saveAccountTopMenuPanel = new JPanel(new GridLayout(1,2,2,2));
          JPanel saveAccountTopMenuPanel1 = new JPanel(new FlowLayout());
          JLabel dateInfo = new JLabel("Date", JLabel.RIGHT);
          JLabel labelTypeofTrans = new JLabel("Trans. Type",JLabel.RIGHT);
          JLabel CheckNoLab = new JLabel("Check No.", JLabel.RIGHT);
          JLabel EnterDectext = new JLabel("Trans. Description",JLabel.RIGHT);
          JLabel PaymentText = new JLabel("Payment/Debit(-)",JLabel.RIGHT);
          JLabel DepositTextLabel = new JLabel("Deposit/Credit(+)",JLabel.RIGHT);
 
          locaofInfo.add(dateInfo);
          locaofInfo.add(EnterDate);
          locaofInfo.add(labelTypeofTrans);
          locaofInfo.add(transList);
          locaofInfo.add(CheckNoLab);
          locaofInfo.add(EnterCheckNo);
          locaofInfo.add(EnterDectext);
          locaofInfo.add(EnterDescr);
          locaofInfo.add(PaymentText);
          locaofInfo.add(EnterPayment);
          locaofInfo.add(DepositTextLabel);
          locaofInfo.add(EnterDeposit);
          
          saveAccountTopMenuPanel.add(TransSaveNew);
          TransSaveNew.addActionListener(ListenButtons);
          saveAccountTopMenuPanel.add(TraAddTopMenu);
          TraAddTopMenu.addActionListener(ListenButtons);
          saveAccountTopMenuPanel1.add(saveAccountTopMenuPanel);
          depositText.add(locaofInfo, BorderLayout.EAST);
          depositText.add(saveAccountTopMenuPanel1, BorderLayout.SOUTH);
          searchtheTrans.setLayout(new BorderLayout());         
	       JPanel searchTransactionPanel = new JPanel(new FlowLayout());
          JLabel searchLabel = new JLabel("Search String:");
          JLabel searchTransactionLists = new JLabel("Search Transactions by Transaction Date/Type/Check No./Description", JLabel.CENTER);
          searchTransactionLists.setFont(new Font("", Font.BOLD, 20));
          searchTransactionPanel.add(searchLabel);
          searchTransactionPanel.add(EnterSearch);
          EnterSearch.addActionListener(ListenButtons);
          searchTransactionPanel.add(OkSearch);
          OkSearch.addActionListener(ListenButtons);
          searchTransactionPanel.add(SearchTopMenu);
          SearchTopMenu.addActionListener(ListenButtons);
          searchtheTrans.add(searchTransactionLists, BorderLayout.NORTH);
          searchtheTrans.add(paneScroll2, BorderLayout.CENTER);
          searchtheTrans.add(searchTransactionPanel, BorderLayout.SOUTH);
 
          JLabel currentTList = new JLabel("Transactions Currently In The Checkbook", JLabel.CENTER);
          currentTList.setFont(new Font("", Font.BOLD, 20));
          JPanel panelCTL = new JPanel();
          panelCTL.add(DelSelect);
          panelCTL.add(ViewDelTopMenu);
	       viewTran.add(currentTList, BorderLayout.NORTH);
          viewTran.add(paneScroll, BorderLayout.CENTER);
          viewTran.add(panelCTL, BorderLayout.SOUTH);
          DelSelect.addActionListener(ListenButtons);
          ViewDelTopMenu.addActionListener(ListenButtons);
          JPanel sortTransactionsPanel = new JPanel();
          sortTransactionsPanel.setLayout(new BorderLayout());
          JPanel sortTransInfo = new JPanel(new GridLayout(1,2));
          JPanel sortTransactionTopMenu = new JPanel(new GridLayout(1,2,2,2));
          JPanel sortTransactionTopMenu1 = new JPanel(new FlowLayout());
          JLabel sortTransactionL = new JLabel("Sort", JLabel.CENTER);
          sortTransactionL.setFont(new Font("", Font.BOLD, 20));
          groupofButtons.add(SortDate);
          groupofButtons.add(SortType);
          JPanel panelHTMLBut = new JPanel();
		    sortTransInfo.add(SortDate);
          sortTransInfo.add(SortType);
          panelHTMLBut.add(sortTransInfo);
          sortTransactionTopMenu.add(TransSort);
          TransSort.addActionListener(ListenButtons);
          sortTransactionTopMenu.add(SortTTopMenu);
          SortTTopMenu.addActionListener(ListenButtons);
          sortTransactionTopMenu1.add(sortTransactionTopMenu);
          sortTransactionsPanel.add(sortTransactionL, BorderLayout.NORTH);
          sortTransactionsPanel.add(panelHTMLBut, BorderLayout.CENTER);
          sortTransactionsPanel.add(sortTransactionTopMenu1, BorderLayout.SOUTH);
          BackBut.add(BackBut2, BorderLayout.NORTH);
          BackBut.add(BackBut5, BorderLayout.SOUTH);
          BackBut.add(TextAccountName, BorderLayout.CENTER);

          stackofCards.add("Top Menu", BackBut);
          stackofCards.add("Create a New Account", CreateNewAcc);
          stackofCards.add("Load Transactions From a File", loadmyTrans);
          stackofCards.add("Add New Transactions", depositText);
          stackofCards.add("Search Transactions", searchtheTrans);
          stackofCards.add("View Transactions", viewTran);
          stackofCards.add("Sort Transactions", sortTransactionsPanel);
          houseofCards.show(stackofCards, "Top Menu");
          panelContent.add(stackofCards);
    }
    public static void loadaccount(){ 
       try{
          FileInputStream LoadFileInp = new FileInputStream (accountNameField.getText());
          ObjectInputStream LoadObjectInput = new ObjectInputStream(LoadFileInp);
          while (true){
             balBook[TransNum] = (DtndpBal)LoadObjectInput.readObject();
             TransNum++;
          }
       }
       catch(EOFException e){
       }
       catch(Exception e){
          e.printStackTrace();
       }  
    }      
    public static void backupaccount(){
       try{
          FileOutputStream BackUpAccountOP = new FileOutputStream(accountNameField.getText(), false);
          ObjectOutputStream BackUpObjectOP = new ObjectOutputStream(BackUpAccountOP);
          for(int i = 0; i < TransNum; i++){
             BackUpObjectOP.writeObject(balBook[i]);
          }
          BackUpObjectOP.close();
       }
       catch(FileNotFoundException e){
          
          System.out.println(e.toString());
       }
       
       catch(IOException e){
          e.printStackTrace();
       }
    }
    public void actionPerformed(ActionEvent e){
       
       Object clickbutton = e.getSource();
       if(clickbutton == NewAcc){
          accountNameText.setText("");
          initialBal.setText("");
          houseofCards.show(stackofCards, "Create a New Account");
       }
       if(clickbutton == TransLoad) {
          loadField.setText("");
          houseofCards.show(stackofCards, "Load Transactions From a File");
       }
       if(e.getSource()==OkCreate){
          account = accountNameText.getText();
          money = Float.parseFloat(initialBal.getText());
          TextFieldBal.setText("" + money);
          accountNameField.setText(""+account);
          TransNum = 0;
          varloadNum = 1;
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(e.getSource()==OopsDontCreate){
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(e.getSource()==OopsDontLoad){
          houseofCards.show(stackofCards, "Top Menu");  
       }  
       if(clickbutton == TraAddTopMenu){
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(clickbutton == SearchTopMenu){
          houseofCards.show(stackofCards, "Top Menu");
       } 
       if(clickbutton == ViewDelTopMenu){
          houseofCards.show(stackofCards, "Top Menu");
       }    
       if(clickbutton == SortTTopMenu){
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(e.getSource()==Load){
          
          account = loadField.getText();
          accountNameField.setText(""+account);
          varloadNum++;
          loadaccount();
          
         TextFieldBal.setText(""+balBook[TransNum-1].TextofBalance);
          if(varloadNum==2){
             int defint =0;
             int rcsttempVar = tempvar;
             while(tempvar<TransNum+rcsttempVar){
                balBook[defint]= balBook[tempvar];
                tempvar++;
                defint++;
             } 
             TransNum-=rcsttempVar;  
             varloadNum--;
          }
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(clickbutton == TransAdd){
          houseofCards.show(stackofCards, "Add New Transactions");
       }
       if(clickbutton == TransLoad) {
          houseofCards.show(stackofCards, "Load Transactions From a File");
       }  
       if(clickbutton == TransSearch){
          EnterSearch.setText("");
          houseofCards.show(stackofCards, "Search Transactions");
          
          stats = new String[TransNum][infoTransactArr.length];
          for(int i = 0; i < stats.length; i++){
             stats[i][0] = balBook[i].TextofDate;
             stats[i][1] = balBook[i].TextofType;
             stats[i][2] = balBook[i].TextOfNum;
             stats[i][3] = balBook[i].TextofDescript;
             stats[i][4] = balBook[i].TextofPayment;
             stats[i][5] = balBook[i].TextofDeposit;
             stats[i][6] = balBook[i].TextofBalance;
          }
          AddBalTbl = new JTable(stats, infoTransactArr);
          tempPane = new JScrollPane(AddBalTbl);
          paneScroll2.setViewport(tempPane.getViewport());
       }
       if(clickbutton == OkSearch){ 
          houseofCards.show(stackofCards, "Top Menu");
       }
       if(clickbutton == TransSort){ 
       houseofCards.show(stackofCards, "Top Menu"); 
       }
       if(clickbutton == TransSaveNew){
          date = EnterDate.getText();
          selectType = (String) transList.getSelectedItem();
          checknum = EnterCheckNo.getText();
          description = EnterDescr.getText();
          if(EnterPayment.getText() != null && EnterPayment.getText().equals("")){
             payment = "0";
          } 
          else {   
             payment = EnterPayment.getText();
          }  
          if(EnterDeposit.getText() != null && EnterDeposit.getText().equals("")){
             deposit = "0";
          } 
          else {   
             deposit = EnterDeposit.getText();
          } 
          DtndpBal c = new DtndpBal();
          c.TextofDate = date;
          c.TextofType = selectType;
          c.TextOfNum = checknum;
          c.TextofDescript = description;
          c.TextofPayment = payment;
          c.TextofDeposit = deposit;
          money = money + Float.parseFloat(deposit) - Float.parseFloat(payment);
          c.TextofBalance = Float.toString(money);
          balBook[TransNum] = c;
          TransNum++;
          EnterDate.setText("");
          EnterCheckNo.setText("");
          EnterDescr.setText("");
          EnterPayment.setText("");
          EnterDeposit.setText("");
          TextFieldBal.setText("" + money);
       }
          
          if(clickbutton == TransViewandDelete){
          houseofCards.show(stackofCards, "View Transactions");
          stats = new String[TransNum][infoTransactArr.length];
          for(int i = 0; i < stats.length; i++){
             stats[i][0] = balBook[i].TextofDate;
             stats[i][1] = balBook[i].TextofType;
             stats[i][2] = balBook[i].TextOfNum;
             stats[i][3] = balBook[i].TextofDescript;
             stats[i][4] = balBook[i].TextofPayment;
             stats[i][5] = balBook[i].TextofDeposit;
             stats[i][6] = balBook[i].TextofBalance;
          }
          	AddBalTbl = new JTable(stats, infoTransactArr);
          	tempPane = new JScrollPane(AddBalTbl);
          	paneScroll.setViewport(tempPane.getViewport());
       }
       if(clickbutton == DelSelect){
          int d = AddBalTbl.getSelectedRow();
          for(int defint = d; defint < TransNum; defint++){
             balBook[defint] = balBook[defint+1];
          }
          TransNum--;
         stats = new String[TransNum][infoTransactArr.length];
          for(int i = 0; i < TransNum; i++){
             stats[i][0] = balBook[i].TextofDate;
             stats[i][1] = balBook[i].TextofType;
             stats[i][2] = balBook[i].TextOfNum;
             stats[i][3] = balBook[i].TextofDescript;
             stats[i][4] = balBook[i].TextofPayment;
             stats[i][5] = balBook[i].TextofDeposit;
             stats[i][6] = balBook[i].TextofBalance;
          }  
          TextFieldBal.setText(""+money);
          AddBalTbl = new JTable(stats, infoTransactArr);
          tempPane = new JScrollPane(AddBalTbl);
          paneScroll.setViewport(tempPane.getViewport());
       }
       if(clickbutton == TransSort2){
          houseofCards.show(stackofCards, "Sort Transactions");
       }
       if(clickbutton == TransBackUp){
          backupaccount();
       }
       if(clickbutton == QuitWindow){
          System.exit(0);
       }
    }
 }
 
   class DtndpBal implements Serializable {
    String TextofDate, TextofType, TextOfNum, TextofDescript, TextofPayment, TextofDeposit, TextofBalance;
 
 }