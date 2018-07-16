//Import Here
import java.awt.*;
import java.util.Random;

public class UI extends javax.swing.JFrame {
    public UI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Town = new javax.swing.JPanel();
        Week = new javax.swing.JLabel();
        Forester = new javax.swing.JLabel();
        Pop = new javax.swing.JLabel();
        TownHall = new javax.swing.JLabel();
        Miners = new javax.swing.JLabel();
        Nursery = new javax.swing.JLabel();
        Bar = new javax.swing.JLabel();
        Wall = new javax.swing.JLabel();
        FHLev = new javax.swing.JLabel();
        THLev = new javax.swing.JLabel();
        MCLev = new javax.swing.JLabel();
        NLev = new javax.swing.JLabel();
        BLev = new javax.swing.JLabel();
        WLev = new javax.swing.JLabel();
        FoodDisplay = new javax.swing.JLabel();
        WoodDisplay = new javax.swing.JLabel();
        StoneDisplay = new javax.swing.JLabel();
        ExcExp = new javax.swing.JLabel();
        Buttons = new javax.swing.JPanel();
        Exit = new javax.swing.JButton();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        Button4 = new javax.swing.JButton();
        Button3 = new javax.swing.JButton();
        Event = new javax.swing.JPanel();
        TownName = new javax.swing.JLabel();
        Activity = new javax.swing.JLabel();
        Entry = new javax.swing.JTextField();
        Event1 = new javax.swing.JLabel();
        Event2 = new javax.swing.JLabel();
        Event3 = new javax.swing.JLabel();
        Event4 = new javax.swing.JLabel();
        Event5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        Week.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        Week.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Week.setText("Week: - ");

        Forester.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        Forester.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Forester.setText("Forester's Hut:");

        Pop.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        Pop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Pop.setText("Pop.: - ");

        TownHall.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        TownHall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TownHall.setText("Town Hall:");

        Miners.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        Miners.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Miners.setText("Miner's Camp:");

        Nursery.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        Nursery.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Nursery.setText("Nursery:");

        Bar.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        Bar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Bar.setText("Bar:");

        Wall.setFont(new java.awt.Font("Sylfaen", 1, 15)); // NOI18N
        Wall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Wall.setText("Wall:");

        FHLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        FHLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FHLev.setText("Level:  ");

        THLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        THLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        THLev.setText("Level:  ");

        MCLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        MCLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MCLev.setText("Level:");

        NLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        NLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NLev.setText("Level:");

        BLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        BLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BLev.setText("Level:");

        WLev.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        WLev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        WLev.setText("Level:");

        FoodDisplay.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        FoodDisplay.setText("Food: - ");

        WoodDisplay.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        WoodDisplay.setText("Wood: - ");

        StoneDisplay.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        StoneDisplay.setText("Stone: - ");

        ExcExp.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        ExcExp.setText("Exc: - / Exp: - ");

        javax.swing.GroupLayout TownLayout = new javax.swing.GroupLayout(Town);
        Town.setLayout(TownLayout);
        TownLayout.setHorizontalGroup(
            TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TownLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TownHall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(Forester, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Miners, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(Nursery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(Wall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(Bar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FHLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(THLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MCLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(WLev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FoodDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(WoodDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StoneDisplay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Pop, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Week, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ExcExp, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        TownLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Bar, Forester, Miners, Nursery, TownHall, Wall});

        TownLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BLev, FHLev, MCLev, NLev, THLev, WLev});

        TownLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Pop, Week});

        TownLayout.setVerticalGroup(
            TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TownLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TownHall)
                    .addComponent(THLev)
                    .addComponent(Week, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Forester)
                    .addComponent(FHLev)
                    .addComponent(Pop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Miners)
                    .addComponent(MCLev)
                    .addComponent(FoodDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nursery)
                    .addComponent(NLev)
                    .addComponent(WoodDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bar)
                    .addComponent(BLev)
                    .addComponent(StoneDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Wall)
                    .addComponent(WLev)
                    .addComponent(ExcExp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TownLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Bar, Forester, Miners, Nursery, TownHall, Wall});

        Exit.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        Button1.setText("Give Title");
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2ActionPerformed(evt);
            }
        });

        Button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button4ActionPerformed(evt);
            }
        });

        Button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonsLayout = new javax.swing.GroupLayout(Buttons);
        Buttons.setLayout(ButtonsLayout);
        ButtonsLayout.setHorizontalGroup(
            ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(ButtonsLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        ButtonsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Button1, Button2, Button3, Button4});

        ButtonsLayout.setVerticalGroup(
            ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Button4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Exit))
        );

        ButtonsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Button1, Button2, Button3, Button4});

        TownName.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        TownName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TownName.setText("-");

        Activity.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        Activity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Activity.setText("Activity Log");

        Entry.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Entry.setText("(Name here)");

        Event1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Event1.setText(" - Forsaken from your homeland,");

        Event2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Event2.setText(" - You wander through the wilds.");

        Event3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Event3.setText(" - After weeks of tired traveling,");

        Event4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Event4.setText(" - You come upon a golden land.");

        Event5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Event5.setText(" - A perfect place to call home.");

        javax.swing.GroupLayout EventLayout = new javax.swing.GroupLayout(Event);
        Event.setLayout(EventLayout);
        EventLayout.setHorizontalGroup(
            EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Event1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(Activity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TownName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Entry)
                    .addComponent(Event2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Event3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Event4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Event5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        EventLayout.setVerticalGroup(
            EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TownName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Activity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Event1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Event2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Event3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Event4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Event5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Entry)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Buttons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Event, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Town, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Event, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Town, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Classes and variable and object declaration here    
    boolean unlTH, unlFH, unlMC, unlN, unlB, unlW, unlFd, unlSt, unlWd, unlTm, unlPp, unlM;       //Unlocks for display/availability
    boolean bldTH, bldFH, bldMC, bldN, bldB, bldW, unlBan, unlExp, unlExc, banAtk, banKil;        //Built or not of buildings
    int posit = 0, week = 1, pop = 1, time = 0;                                                   //Displays info and manages placement
    int LevTH, LevFH, LevMC, LevN, LevB, LevW;                                                    //Tracks building level
    int wdGat = 0, stGat = 0, fdGat = 0, levMine = 0, avSt = 0;                                   //Amount of resource gained
    int wdTot = 0, stTot = 0, fdTot = 3;                                                          //Total amounts of resources
    int fhPop = 0, mcPop = 0, nPop = 0, invPop = 0;                                               //Allocated populations
    int wdCost, stCost;                                                                           //Upgrade costs
    int wdCap, stCap, fdCap, ppCap;                                                               //Resource caps
    int exp = 0, exc = 0;
    int temp;                                                                                     //Temporary value for use
    
    Random rand = new Random();                                                                   //Random object created
                                                                                                  //Posit: 0 = Naming Non-Rep
                                                                                                  // 1 = Move In Non-Rep
                                                                                                  // 2 = Town only FH Non-Rep
                                                                                                  // 3 = Forest Options (Forage, Cut, Explore, Return4)
                                                                                                  // 4 = General Town (Forest3, Mines5, Town6, Status)
                                                                                                  // 5 = Mine Options (Expand, Dig, Excavate, Return4)
                                                                                                  // 6 = Town Options (upgrading buildings, Return4/More13)
                                                                                                  // 7 = Town Hall (upgrade, Return4)
                                                                                                  // 8 = Forester's Hut (upgrade, Return4)
                                                                                                  // 9 = Miner's Camp (upgrade, Return4)
                                                                                                  //10 = Nursery (upgrade, Return4)
                                                                                                  //11 = Bar (upgrade, Return4)
                                                                                                  //12 = Wall (upgrade, Return4)
                                                                                                  //13 = Town Options 2 (upgrading buildings, Return4)
                                                                                                  //14 = Death scenario
                                                                                                  //15 = Population assignment
                                                                                                  //16 = Assign Forester
                                                                                                  //17 = Assign Miner
                                                                                                  //18 = Assign Nursery
    @Override
    public void paint(Graphics g){
     g.setColor(Color.black);
     g.fillRect(0,0,600,425);
     Town.setBackground(Color.gray);
     Buttons.setBackground(Color.darkGray);
     Event.setBackground(Color.LIGHT_GRAY);
     Exit.setBackground(Color.red);
     
     
     if (exp == 0 && exc == 0)
         ExcExp.setText("");
     if (exp == 0 && exc != 0)
         ExcExp.setText("Exc: " + exc);
     if (exp != 0 && exc == 0)
         ExcExp.setText("Exp: " + exp);
     if (exp != 0 && exc != 0)
         ExcExp.setText("Exp: " + exp + " /  Exc: " + exc);
     
     if(unlTH != true)
     {
      TownHall.setText("- :");
      THLev.setText("");
     }
     else
     {
      TownHall.setText("Town Hall:");
      THLev.setText("Level: " + LevTH);
     }
     if(unlFH != true)
     {
      Forester.setText("- :");
      FHLev.setText("");
     }
     else
     {
      Forester.setText("Forester's Hut :");
      FHLev.setText("Level: " + LevFH);
     }
     if(unlMC != true)
     {
      Miners.setText("- :");
      MCLev.setText("");
     }
     else
     {
      Miners.setText("Miner's Camp:");
      MCLev.setText("Level: " + LevMC);
     }
     if(unlN != true)
     {
      Nursery.setText("- :");
      NLev.setText("");
     }
     else
     {
      Nursery.setText("Nursery:");
      NLev.setText("Level: " + LevN);
     }
     if(unlB != true)
     {
      Bar.setText("- :");
      BLev.setText("");
     }
     else
     {
      Bar.setText("Bar:");
      BLev.setText("Level: " + LevB);
     }
     if (unlW != true)
     {
      Wall.setText("- :");
      WLev.setText("");
     }
     else
     {
      Wall.setText("Wall:");
      WLev.setText("Level: " + LevW);
     }
     
     if (unlTm != true)
      Week.setText("");
     else
      Week.setText("Week: " + week + " ");
     if (unlPp != true)
      Pop.setText("");
     else
      Pop.setText("Pop.: " + pop + " ");
     if(unlFd != true)
      FoodDisplay.setText("");
     else
      FoodDisplay.setText("Food: " + fdTot + " ");
     if(unlWd != true)
      WoodDisplay.setText("");
     else
      WoodDisplay.setText("Wood: " + wdTot + " ");
     if(unlSt != true)
      StoneDisplay.setText("");
     else
      StoneDisplay.setText("Stone: " + stTot + " ");

    }
    
    public void redraw(){

     if(LevFH >= 3 && unlMC != true)                                //Series of unlocks below
     {
      unlMC = true;
      unlM = true;
      unlSt = true;
      Event1.setText(" - A town is born of more");
      Event2.setText(" - than scraps of wood.");
      Event3.setText(" - Something more will be");
      Event4.setText(" - needed. The very earth");
      Event5.setText(" - itself should aid you.");
     }
     
     if(LevFH >= 5 && LevMC >= 3 && pop >= 10 && unlTH != true)
     {
      unlTH = true;
      Event1.setText(" - Joined by your fellow exiles,");
      Event2.setText(" - it is time to announce your");
      Event3.setText(" - place in this world. With pride,");
      Event4.setText(" - the plans for a Town Hall are");
      Event5.setText(" - begun, and the land staked out.");
     }
     
     if(pop >= 15 && unlN != true)
     {
      unlN = true;
      Event1.setText(" - The scraps you take from");
      Event2.setText(" - the forest will no longer");
      Event3.setText(" - sate the growing hunger of");
      Event4.setText(" - your exiles. Useless seeds");
      Event5.setText(" - shall find their purpose.");
     }
     
     if(LevN >= 5 && pop >= 25 && unlB != true)
     {
      unlB = true;
      Event1.setText(" - Revelry in short supply,");
      Event2.setText(" - a rough idea blooms in the");
      Event3.setText(" - thirsty minds of exiles.");
      Event4.setText(" - Drink shall sate this thirst,");
      Event5.setText(" - and perhaps attract others.");
     }
     
     if(pop >= 5 && banAtk == true && unlW == false)
     {
      unlW = true;
      Event1.setText(" - The fierce attack at the");
      Event2.setText(" - hands of bandits has inspired");
      Event3.setText(" - a desperate plan to fend them");
      Event4.setText(" - off. Flesh may give beneath");
      Event5.setText(" - their blows, but stone cannot.");
     }
     
     if (time >= 10)                                                //Helps for time passage and allows for weekly events
     {
      week +=1;
      time -= 10;
      fdTot -= pop;
      System.out.println("Week " + week);
      
      if (pop >= 5 && ((rand.nextInt(10) + 1) / 4) == 0 && banKil != true)                            //Bandit Attack
      {
        banAtk = true;
        unlExp = true;
        temp = rand.nextInt(10) + 1;
       switch(temp)
       {
           case 1:
           case 2: temp = 2; break;
           case 3:
           case 4:
           case 5: temp = 3; break;
           case 6:
           case 7:
           case 8:
           case 9: 
           case 10: temp = 5; break;
       }
        temp = rand.nextInt(pop)/ (temp + LevW);
        pop -= temp;
        invPop -= temp;
       Event1.setText(" -  In the night you are awoken");
       Event2.setText(" -  by the horrific sounds of");
       Event3.setText(" -  blades slashing through flesh.");
       Event4.setText(" -  Bandits have attacked, killing");
       Event5.setText(" -  " + temp + " of your exiles.");
       
        System.out.println("Bandit" + temp);
      }
      
      if(unlMC == true)                                             //Population increase
      {
       if (((rand.nextInt(10) + 1) / 3) == 0)
       {
        if (pop < 2)
        {
          Event1.setText(" - For months you have not");
          Event2.setText(" - even known of the pain");
          Event3.setText(" - in your chest until");
          Event4.setText(" - a sudden voice freed you");
          Event5.setText(" - from it. A fellow exile.");
        }
        temp = rand.nextInt(pop * (LevB + 1)) + 1;
        pop += temp;
        invPop += temp;
        System.out.println("Pop " + temp);
       }
      }
     }                                                              //End

     if (fdTot < 0)                                                 //Starvation
     {
      temp = rand.nextInt(fdTot*-1) + 1;
      pop -= temp;
      invPop -= temp;
      fdTot = 0;      
     }                                                              //End
     
     
     if (pop <= 0)                                                  //Loss of everyone; fail
     {
      pop = 0;
      posit = 14;
      Button1.setText("Restart");
      Button2.setText(" - ");
      Button3.setText(" - ");
      Button4.setText("Exit");
      Event1.setText(" - Many cities have risen to fall.");
      Event2.setText(" - Perhaps another story, told far");
      Event3.setText(" - away, met a more generous end.");
      Event4.setText(" - Perhaps it did not. Shall we see?");
      Event5.setText(" - Or is this the end for you?");
     }                                                              //End
          
     
     switch(LevTH)                                                  //Population Capacity
     {
         case 0: ppCap = 20; break;
         case 1: ppCap = 30; break;
         case 2: ppCap = 50; break;
         case 3: ppCap = 80; break;
         case 4: ppCap = 125; break;
         case 5: ppCap = 175; break;
         case 6: ppCap = 250; break;
         case 7: ppCap = 350; break;
         case 8: ppCap = 500; break;
         case 9: ppCap = 750; break;
         case 10: ppCap = 1000; break;
     }
      if (pop > ppCap)
      {
       temp = pop - ppCap;
       invPop -= temp;
       pop = ppCap;
      }

      while ((invPop) < 0)
      {
          switch(0)
          {
              case 0: if (fhPop > 0) {fhPop -= 1; invPop += 1; break;}
              case 1: if (mcPop > 0) {mcPop -= 1; invPop += 1; break;}
              case 2: if (nPop > 0) {nPop -= 1; invPop += 1; break;}  
              default: invPop += 1;
          }
      }
      
     switch(LevFH)                                                  //Wood Total Capacity
     {
         case 0: temp = 5; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 1: temp = 15; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 2: temp = 25; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 3: temp = 50; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 4: temp = 100; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 5: temp = 300; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 6: temp = 500; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 7: temp = 750; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 8: temp = 1000; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 9: temp = 1500; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
         case 10: temp = 2000; wdCap = temp + (temp * ((LevTH + 1)/10)); break;
     }
      if (wdTot > wdCap)
       wdTot = wdCap;
      
     switch(LevMC)                                                  //Stone Total Capacity
     {
         case 0: temp = 10; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 1: temp = 50; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 2: temp = 100; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 3: temp = 250; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 4: temp = 400; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 5: temp = 600; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 6: temp = 800; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 7: temp = 1000; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 8: temp = 1250; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 9: temp = 1500; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 10: temp = 2000; stCap = temp + (temp * ((LevTH + 1) / 10)); break;
     }
      if (stTot > stCap)
       stTot = stCap;
      
     switch(LevN)                                                   //Food Total Capacity
     {
         case 0: temp = 40; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 1: temp = 100; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 2: temp = 175; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 3: temp = 300; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 4: temp = 450; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 5: temp = 700; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 6: temp = 1000; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 7: temp = 1250; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 8: temp = 1500; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 9: temp = 1750; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
         case 10: temp = 2000; fdCap = temp + (temp * ((LevTH + 1) / 10)); break;
     }
      if (fdTot > fdCap)
        fdTot = fdCap;
      if (avSt < 0)
        avSt = 0;
     if(posit == 16 || posit == 17 || posit == 18)
     {
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);
     }
     
     
     if(unlTH != true)                                      //Decides whether to display buildings or not to user + Levels
     {TownHall.setText("- :"); THLev.setText("");}
     else
     {TownHall.setText("Town Hall :"); THLev.setText("Level: " + LevTH);}
     if(unlFH != true)
     {Forester.setText("- :"); FHLev.setText("");}
     else
     {Forester.setText("Forester's Hut :"); FHLev.setText("Level: " + LevFH);}
     if(unlMC != true)
     {Miners.setText("- :"); MCLev.setText("");}
     else
     {Miners.setText("Miner's Camp :"); MCLev.setText("Level: " + LevMC);}
     if(unlN != true)
     {Nursery.setText("- :"); NLev.setText("");}
     else
     {Nursery.setText("Nursery :"); NLev.setText("Level: " + LevN);}
     if(unlB != true)
     {Bar.setText("- :"); BLev.setText("");}
     else
     {Bar.setText("Bar :"); BLev.setText("Level: " + LevB);}
     if (unlW != true)
     {Wall.setText("- :"); WLev.setText("");}
     else
     {Wall.setText("Wall :"); WLev.setText("Level: " + LevW);}
     
     if (unlTm != true)                                         //Decides whether or not to display UI + Information
      Week.setText("");
     else
      Week.setText("Week: " + week + " ");
     if (unlPp != true)
      Pop.setText("");
     else
      Pop.setText("Pop.: " + pop + " ");
     if(unlFd != true)
      FoodDisplay.setText("");
     else
      FoodDisplay.setText("Food: " + fdTot + " ");
     if(unlWd != true)
      WoodDisplay.setText("");
     else
      WoodDisplay.setText("Wood: " + wdTot + " ");
     if(unlSt != true)
      StoneDisplay.setText("");
     else
      StoneDisplay.setText("Stone: " + stTot + " ");
     
     if (exc > 1000)
         exc = 1000;
     if (exp > 1000)
         exp = 1000;
     
     if (exp == 0 && exc == 0)
         ExcExp.setText("");
     if (exp == 0 && exc != 0)
         ExcExp.setText("Exc: " + exc);
     if (exp != 0 && exc == 0)
         ExcExp.setText("Exp: " + exp);
     if (exp != 0 && exc != 0)
         ExcExp.setText("Exp: " + exp + " /  Exc: " + exc);


     if (exc == 1000 && exp == 1000)
     {
       posit = 14;
       Button1.setText("Restart");
       Button2.setText(" - ");
       Button3.setText(" - ");
       Button4.setText("Exit");
       Event1.setText(" - In the space of " + week + " weeks,");
       Event2.setText(" - you turned an empty, if pristine,");
       Event3.setText(" - land, into an expansive kingdom,");
       Event4.setText(" - turned a collection of exiles");
       Event5.setText(" - into the citizens of an empire.");
     }
     
     System.out.println(posit);
     System.out.println(invPop + " " + pop);
    }

    
    
//Visual Form Actions    
    
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
    System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1ActionPerformed
    
    switch(posit){ 
        case 0:                         //Give Title
                unlTm = true;

                
                if (Entry.getText().equals("") || Entry.getText().equals("(Name here)"))
                {
                 Event1.setText(" -  Nameless towns come and go.");
                 Event2.setText(" - ");
                 Event3.setText(" -  Give life to this found land.");
                 Event4.setText(" - ");
                 Event5.setText(" -  Free it from being forgotten.");
                 return;
                }
                else
                {
                 TownName.setText(Entry.getText());
                 Entry.setText("");
                 Event1.setText(" -  After several nights in this");
                 Event2.setText(" -  pristine land, You come");
                 Event3.setText(" -  across a simple hut filled");
                 Event4.setText(" -  with axes. It has long begun");
                 Event5.setText(" -  rotting in its disuse.");
                }
                posit = 1;
                Button1.setText("Move In");
                redraw();
                break;
        case 1:                     //Move In
                unlFH = true;
                unlPp = true;
                unlFd = true;
                LevFH = 0;
                Event1.setText(" - What supplies you had are gone.");
                Event2.setText(" - The hut you spent the night in");
                Event3.setText(" - groaned and creaked, threatening");
                Event4.setText(" - to collapse in even gentle winds.");
                Event5.setText(" - Repair it before it is too far gone.");
                posit = 2;
                Button1.setText("Forest");
                Button2.setText(" - ");
                Button3.setText(" - ");
                Button4.setText(" - ");
                redraw();
                break;
        case 2:                         //Forest
                time += 1;
                Event1.setText(" -  You wander into the woods,");
                Event2.setText(" -  mind wandering over all that");
                Event3.setText(" -  needs doing and all that nature");
                Event4.setText(" -  offers to you. What will you do");
                Event5.setText(" -  this day in the kindly forest?");
                
                Button1.setText("Forage");
                Button2.setText("Harvest Wood");
                if (unlExp == true)
                 Button3.setText("Explore");
                else
                 Button3.setText(" - ");
                Button4.setText("Return");
                posit = 3;
                
                unlWd = true;
                redraw();
                break;
        case 3:                             //Forage
               time += 2;
               fdGat = rand.nextInt(1 + fhPop) * (1 + ((1 + fhPop)/100)) + (1 + fhPop);
               if (nPop != 0)
                 fdGat += rand.nextInt(nPop) * (1 + ((nPop/100)) + (nPop));
               if (mcPop != 0)
                 stGat = rand.nextInt(mcPop) * (1 + (mcPop/100)) + mcPop;
               if (fhPop != 0)
                wdGat = rand.nextInt(fhPop) * (1 + (fhPop/100)) + fhPop; 
               
               fdTot += fdGat;
               if (avSt - stGat < 0)
                stGat = 0;
               stTot += stGat;
               wdTot += wdGat;
               avSt -= stGat;
               
               Event1.setText(" - Ranging through the woods in");
               Event2.setText(" - search for all manner of plant and");
               Event3.setText(" - beast, you return with " + fdGat + " to store");
               Event4.setText(" - for the future ahead of you, only");
               Event5.setText(" - able to keep " + fdCap + " in safe reserve.");
               
               fdGat = 0;
               stGat = 0;
               wdGat = 0;

               redraw();
               break;
        case 4:                             //Forest/Town
               time += 1;
               Button1.setText("Forage");
               Button2.setText("Harvest Wood");
               if (unlExp == true)
                Button3.setText("Explore");
               else
                Button3.setText(" - ");
               Button4.setText("Return");
               posit = 3;
               Event1.setText(" -  You wander into the woods,");
               Event2.setText(" -  mind wandering over all that");
               Event3.setText(" -  needs doing and all that nature");
               Event4.setText(" -  offers to you. What will you do");
               Event5.setText(" -  this day in the kindly forest?");
               redraw();
               break;
        case 5:                             //Expand/Mines
               if (avSt <= 0)
               {
                time += 4;
                levMine += 1;
               }
               else
               {
                 redraw();
                 break;
               }
               if (levMine == 5)
               {
                Event1.setText(" -  Deep within the earth, a pick");
                Event2.setText(" -  falls upon something strange.");
                Event3.setText(" -  The uppermost reaches of spires");
                Event4.setText(" -  have been found in the stone.");
                Event5.setText(" -  Excavations have begun.");
                unlExc = true;
                redraw();
                break;
               }
               else
               {
                Event1.setText(" - Standing in the empty quarry,");
                Event2.setText(" - you lift the pick high above you");
                Event3.setText(" - and slam it into the stone beneath");
                Event4.setText(" - your feet. The mine shall not stop");
                Event5.setText(" - producing so long as stone remains.");
                switch(levMine)
                {
                   case 0: avSt = 0; break;
                   case 1: avSt = 20; break;
                   case 2: avSt = 25; break;
                   case 3: avSt = 30; break;
                   case 4: avSt = 50; break;
                   case 5: avSt = 75; break;
                   case 6: avSt = 100; break;
                   case 7: avSt = 150; break;
                   case 8: avSt = 200; break;
                   case 9: avSt = 300; break;
                   case 10: avSt = 450; break;
                   case 11: avSt = 600; break;
                   case 12: avSt = 800; break;
                   case 13: avSt = 1150; break;
                   case 14: avSt = 1750; break;
                   case 15: avSt = 2500; break;
                   case 16: avSt = 3500; break;
                   case 17: avSt = 5000; break;
                   case 18: avSt = 7000; break;
                   case 19: avSt = 10000; break;
                   case 20: avSt = 15000; break;
                   case 21: avSt = 20000; break;
                }                
                redraw();
                break;
               }
        case 6:                             //Upgrade Town Hall/Town
               if (unlTH == true)
               {
                posit = 7;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");               
                if(bldTH == false)
                {
                Event1.setText(" -  Watching over your scattered");
                Event2.setText(" -  homes and workplaces, here you");
                Event3.setText(" -  shall set down your Town Hall.");
                Event4.setText(" -  It shall be costly, but give much");
                Event5.setText(" -  to your town and fellow exiles.");
                }
                else
                {
                Event1.setText(" - Here is your Town Hall, the");
                Event2.setText(" - center of your ever-growing");
                Event3.setText(" - town. From here you watch over");
                Event4.setText(" - all that happens, and ensure");
                Event5.setText(" - there shall be a future for you all.");
                }
               }
               else
                redraw();
               break;
        case 7:                             //Town Hall Construct
               if (unlTH == true)
               {
                if (bldTH == false && (wdTot - 50) >= 0 && (stTot - 75) >= 0)
                {
                 bldTH = true;
                 LevTH = 1;
                 wdTot -= 50;
                 stTot -= 75;
                 Event1.setText(" - Your fellow exiles find peace in");
                 Event2.setText(" - the stout building now watching");
                 Event3.setText(" - over their town and futures.");
                 Event4.setText(" - Taking your place within, you");
                 Event5.setText(" - plan the spread of your town.");
                }
               }
               redraw();
               break;
        case 8:                             //Forester Hut Construct       
              if (bldFH == false && (wdTot - 3) >= 0)
              {
                  bldFH = true;
                  LevFH = 1;
                  wdTot -= 3;
                  Event1.setText(" - Even in these gentle lands,");
                  Event2.setText(" - the cracks and holes in your");
                  Event3.setText(" - impromptu home had put you on");
                  Event4.setText(" - edge. Stout lumber now stands");
                  Event5.setText(" - at your defense. A proud start.");
              }
               redraw();
               break;
        case 9:                             //Miner's Camp Construct
               if (unlMC == true)
               {            
                if (bldMC == false && (wdTot - 10) >= 0)
                {
                 bldMC = true;
                 LevMC = 1;
                 wdTot -= 10;
                 Event1.setText(" - A small collection of tents");
                 Event2.setText(" - stand beside the latticework");
                 Event3.setText(" - built over the ground ready to");
                 Event4.setText(" - be quarried. Picks lie gathering");
                 Event5.setText(" - dust; why do you wait to begin?");
                }
               }
               redraw();
               break;
        case 10:                            //Nursery Construct
               if (unlN == true)
               {            
                if (bldN == false && (wdTot - 75) >= 0 && (stTot - 25) >= 0)
                {
                 bldN = true;
                 LevN = 1;
                 wdTot -= 75;
                 stTot -= 25;
                 Event1.setText(" - As the first green sprouts break");
                 Event2.setText(" - free from the dirt, a cheer rises.");
                 Event3.setText(" - Few stomachs will go empty if");
                 Event4.setText(" - these fields are tended, yet");
                 Event5.setText(" - parched throats are unimpressed.");
                }         
               }
               redraw();
               break;
        case 11:                            //Bar Construct
               if (unlB == true)
               {            
                if (bldB == false && (wdTot - 125) >= 0 && (stTot - 50) >= 0)
                {
                 bldB = true;
                 LevB = 1;
                 wdTot -= 125;
                 stTot -= 50;
                 Event1.setText(" - Revelry fills the air as coin changes");
                 Event2.setText(" - hand, dry tongues and clear minds");
                 Event3.setText(" - muddied by the thick ales brewed to");
                 Event4.setText(" - attract hearts and minds from all over");
                 Event5.setText(" - the land. Joy spreads as drink spills.");
                }
               }
               redraw();
               break;
        case 12:                            //Wall Construct
               if (unlW == true)
               {            
                if (bldW == false && (wdTot - 25) >= 0 && (stTot - 60) >= 0)
                {
                 bldW = true;
                 LevW = 1;
                 wdTot -= 25;
                 stTot -= 60;
                 Event1.setText(" - With bitter hands and furrowed");
                 Event2.setText(" - brows, hammers rise and fall long");
                 Event3.setText(" - into the night. Stone piled high");
                 Event4.setText(" - and arms gripped tight, the exiles");
                 Event5.setText(" - are ready for the Bandits' return.");
                }
               }
               redraw();
               break;
        case 13:                            //Upgrade Nursery
               if (unlN == true)
               {
                posit = 10;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");
                Event1.setText(" - Across miles of forest you may");
                Event2.setText(" - find scraps of food to sate your");
                Event3.setText(" - hunger, but as more exiles join");
                Event4.setText(" - more stomachs need filling. Farmed");
                Event5.setText(" - food will sate that hunger for now.");
               }
               else
                redraw();
               break;
        case 14:                            //Loss of population
               Button1.setText("Give Title");
               Button2.setText("");
               Button3.setText("");
               Button4.setText("");
               Event1.setText(" - Forsaken from your homeland,");
               Event2.setText(" - You wander through the wilds.");
               Event3.setText(" - After weeks of tired traveling,");
               Event4.setText(" - You come upon a golden land.");
               Event5.setText(" - A perfect place to call home.");
               fhPop = 0; mcPop = 0; nPop = 0; wdTot = 0; stTot = 0; fdTot = 3;
               wdGat = 0; stGat = 0; fdGat = 0; posit = 0; week = 1; pop = 1; time = 0;
               unlTH = false; unlFH = false; unlMC = false; unlN = false; unlB = false;
               unlW = false; unlFd = false; unlSt = false; unlWd = false;
               unlPp = false; unlM = false; unlExp = false;
               LevTH = 0; LevFH = 0; LevMC = 0; LevN = 0; LevB = 0; LevW = 0;
               redraw();
               break;
        case 15:                                        //Forester's Hut Population Assignment
            if (bldFH == false)
             break;
               posit = 16;
               Button1.setText("Allocate");
               Button2.setText("Remove");
               Button3.setText("Clear All");
               Button4.setText("Return");            
               redraw();
               break;
        case 16:                                        //Forester Assignment Addition
               if ((invPop) == 0)
               {
                break;
               }
               fhPop += 1;
               invPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);
               redraw();
               break;
        case 17:                                        //Miner Assignment Addition
               if ((invPop) == 0)
                break;
               mcPop += 1;
               invPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
        case 18:                                        //Nursery Assignment Addition
               if ((invPop) == 0)
                break;
               nPop += 1;
               invPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
    }
    }//GEN-LAST:event_Button1ActionPerformed

    private void Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2ActionPerformed
        switch(posit)
        {
        case 3:                             //Fell Tree
               time += 3;
               if (nPop != 0)
                 fdGat = rand.nextInt(nPop) * (1 + ((nPop)/100)) + (nPop);
               if (mcPop != 0)
               {
                 if (avSt != 0)
                 {
                 stGat = rand.nextInt(mcPop) * (1 + (mcPop/100)) + mcPop;
                 }
               }
                wdGat = rand.nextInt(1 + fhPop) * (1 + (1 + fhPop/100)) + (1 + fhPop); 
               if (fhPop != 0 && nPop == 0)
                 fdGat += (rand.nextInt(fhPop) * (1 + (fhPop/100)) + (fhPop)) / 2;
               
                fdTot += fdGat;
                if (avSt - stGat < 0)
                 stGat = 0;                
                stTot += stGat;
                wdTot += wdGat;
                avSt -= stGat;
                
                Event1.setText(" - Hacking into the endless forests,");
                Event2.setText(" - you retrieve " + wdGat + " wood for your");
                Event3.setText(" - town. It can only hold " + wdCap + ", but");
                Event4.setText(" - needs all that it can get. Muscles");
                Event5.setText(" - tiring, you bring your haul home.");
                
                fdGat = 0;
                stGat = 0;
                wdGat = 0;                
                
                
                redraw();
                break;
        case 4:                             //Mines
                if (unlM == false)
                 return;
                else
                {
                 posit = 5;
                 Button1.setText("Expand");
                 Button2.setText("Harvest Stone");
                 if (unlExc == true)
                  Button3.setText("Excavate");
                 else
                  Button3.setText(" - ");
                 Button4.setText("Return");
                }
                redraw();
                break;
        case 5:                             //Harvest Stone
               time += 3;
               
               if (levMine == 0)
               {
                Event1.setText(" - This land is hard and rocky, but");
                Event2.setText(" - promises a strong yield if a steady");
                Event3.setText(" - hand applied itself. Preparations");
                Event4.setText(" - must be made over time, acts that a");
                Event5.setText(" - skilled eye alone must perform.");
                redraw();
                break;
               }
               
               if (avSt <= 0)
               {
                avSt = 0;
                Event1.setText(" - It is no use in mining this");
                Event2.setText(" - emptied quarry. Deeper you must");
                Event3.setText(" - delve if you wish to find more");
                Event4.setText(" - stone, harder you must swing your");
                Event5.setText(" - pick, and fresh hope held close.");
                redraw();
                break;
               }
               if (nPop != 0)
                 fdGat = rand.nextInt(nPop) * (1 + ((nPop)/100)) + (nPop);
                stGat = rand.nextInt(1 + mcPop) * (1 + (1 + mcPop/100)) + (1 + mcPop);
               if (fhPop != 0)
                wdGat = rand.nextInt(fhPop) * (1 + (fhPop/100)) + (fhPop); 
               if (fhPop != 0 && nPop == 0)
                 fdGat += (rand.nextInt(fhPop) * (1 + (fhPop/100)) + (fhPop)) / 2;
               
                fdTot += fdGat;
                stTot += stGat;
                wdTot += wdGat;
                
                avSt -= stGat;
                if (avSt < 0)
                 avSt = 0;
                
                Event1.setText(" - Digging your pick deep into the");
                Event2.setText(" - stone, you haul " + stGat + " stone from the");
                Event3.setText(" - quarry to the camp, able to store");
                Event4.setText(" - " + stCap + " stone in total. The quarry is");
                Event5.setText(" - on Level " + levMine + ", with " + avSt + " more to mine.");
                
                fdGat = 0;
                stGat = 0;
                wdGat = 0;                   
               redraw();
               break;
        case 6:                             //Forester's Hut
               if (unlFH == true)
               {
                posit = 8;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");
                Event1.setText(" - The Forester's Hut, giving");
                Event2.setText(" - sanctuary in a pristine land yet");
                Event3.setText(" - filled with dangers of nature. Wood");
                Event4.setText(" - is obtained here, and food is foraged");
                Event5.setText(" - with no other means to obtain it.");
               }
               else
                redraw();
               break;
        case 7:                             //Town Hall Upgrade
            if (bldTH == false)
            {
                Event1.setText(" - From the ground your town has");
                Event2.setText(" - risen, now it is time to watch");
                Event3.setText(" - it spread from high above. Stand");
                Event4.setText(" - apart from your town to watch it");
                Event5.setText(" - rise high with 50 Wood & 75 Stone.");
                redraw();
                break;
            }
               switch(LevTH)
               {
                   case 0: wdCost = 50; stCost = 75; break;
                   case 1: wdCost = 65; stCost = 100; break;
                   case 2: wdCost = 90; stCost = 125; break;
                   case 3: wdCost = 125; stCost = 150; break;
                   case 4: wdCost = 150; stCost = 200; break;
                   case 5: wdCost = 200; stCost = 250; break;
                   case 6: wdCost = 250; stCost = 300; break;
                   case 7: wdCost = 300; stCost = 350; break;
                   case 8: wdCost = 400; stCost = 450; break;
                   case 9: wdCost = 500; stCost = 500; break;
                   case 10: wdCost = 750; stCost = 750; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevTH += 1;
               }
               redraw();
               break;
        case 8:                             //Forester's Hut Upgrade
            if (bldFH == false)
            {
                Event1.setText(" - Much of the hut needs replacing,");
                Event2.setText(" - the rotted wood irreperable and");
                Event3.setText(" - in dire need of a steady hand to");
                Event4.setText(" - bring this lonely place back to life.");
                Event5.setText(" - 3 Wood should do the job this day.");
                redraw();
                break;
            }
               switch(LevFH)
               {
                   case 0: wdCost = 3; stCost = 0; break;
                   case 1: wdCost = 5; stCost = 0; break;
                   case 2: wdCost = 10; stCost = 0; break;
                   case 3: wdCost = 20; stCost = 0; break;
                   case 4: wdCost = 30; stCost = 5; break;
                   case 5: wdCost = 50; stCost = 15; break;
                   case 6: wdCost = 80; stCost = 30; break;
                   case 7: wdCost = 125; stCost = 50; break;
                   case 8: wdCost = 200; stCost = 80; break;
                   case 9: wdCost = 350; stCost = 125; break;
                   case 10: wdCost = 500; stCost = 200; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevFH += 1;
                 bldFH = true;
               }
               redraw();
               break;
        case 9:                             //Miner's Camp Upgrade
            if (bldMC == false)
            {
                Event1.setText(" - With no organized camp to");
                Event2.setText(" - store and keep track of stone,");
                Event3.setText(" - it would be a long, hard journey");
                Event4.setText(" - towing lone stones through the");
                Event5.setText(" - country. 10 Wood will establish.");
                redraw();
                break;
            }
               switch(LevMC)
               {
                   case 0: wdCost = 10; stCost = 0; break;
                   case 1: wdCost = 15; stCost = 15; break;
                   case 2: wdCost = 25; stCost = 30; break;
                   case 3: wdCost = 50; stCost = 60; break;
                   case 4: wdCost = 80; stCost = 100; break;
                   case 5: wdCost = 125; stCost = 150; break;
                   case 6: wdCost = 175; stCost = 225; break;
                   case 7: wdCost = 250; stCost = 300; break;
                   case 8: wdCost = 350; stCost = 400; break;
                   case 9: wdCost = 475; stCost = 525; break;
                   case 10: wdCost = 600; stCost = 800; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevMC += 1;
               }            
               redraw();
               break;
        case 10:                            //Nursery Upgrade
            if(bldN == false)
            {
                Event1.setText(" - Hungry bellies need constant filling,");
                Event2.setText(" - and what scraps harvested from the");
                Event3.setText(" - forest will no longer do. 75 Wood");
                Event4.setText(" - and 25 Stone will set the grounds");
                Event5.setText(" - for a long future of productivity.");
                redraw();
                break;
            }
               switch(LevN)
               {
                   case 0: wdCost = 75; stCost = 25; break;
                   case 1: wdCost = 100; stCost = 40; break;
                   case 2: wdCost = 125; stCost = 65; break;
                   case 3: wdCost = 175; stCost = 80; break;
                   case 4: wdCost = 240; stCost = 110; break;
                   case 5: wdCost = 315; stCost = 135; break;
                   case 6: wdCost = 430; stCost = 170; break;
                   case 7: wdCost = 550; stCost = 215; break;
                   case 8: wdCost = 700; stCost = 275; break;
                   case 9: wdCost = 900; stCost = 350; break;
                   case 10: wdCost = 1250; stCost = 450; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevN += 1;
               }            
               redraw();
               break;
        case 11:                            //Bar Upgrade
            if (bldB == false)
            {
                Event1.setText(" - Only so long can be spent away");
                Event2.setText(" - from civilization before its");
                Event3.setText(" - amenities become dearly missed.");
                Event4.setText(" - Thirsty mouths don't turn away from");
                Event5.setText(" - the 125 Wood and 60 Stone cost.");
                redraw();
                break;
            }
               switch(LevB)
               {
                   case 0: wdCost = 125; stCost = 60; break;
                   case 1: wdCost = 175; stCost = 90; break;
                   case 2: wdCost = 250; stCost = 125; break;
                   case 3: wdCost = 350; stCost = 200; break;
                   case 4: wdCost = 475; stCost = 315; break;
                   case 5: wdCost = 635; stCost = 475; break;
                   case 6: wdCost = 800; stCost = 675; break;
                   case 7: wdCost = 950; stCost = 800; break;
                   case 8: wdCost = 1125; stCost = 975; break;
                   case 9: wdCost = 1350; stCost = 1250; break;
                   case 10: wdCost = 1500; stCost = 1500; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevB += 1;
               }            
               redraw();
               break;
        case 12:                            //Wall Upgrade
            if (bldW == false)
            {
                Event1.setText(" - Legs roam easily over steady");
                Event2.setText(" - ground, no care given for the");
                Event3.setText(" - threat inherent there. Let the");
                Event4.setText(" - earth itself rise to guard you,");
                Event5.setText(" - for 25 Wood and 60 Stone.");
                redraw();
                break;                        
            }
               switch(LevW)
               {
                   case 0: wdCost = 25; stCost = 60; break;
                   case 1: wdCost = 45; stCost = 125; break;
                   case 2: wdCost = 75; stCost = 200; break;
                   case 3: wdCost = 125; stCost = 325; break;
                   case 4: wdCost = 235; stCost = 500; break;
                   case 5: wdCost = 350; stCost = 700; break;
                   case 6: wdCost = 425; stCost = 950; break;
                   case 7: wdCost = 600; stCost = 1175; break;
                   case 8: wdCost = 725; stCost = 1425; break;
                   case 9: wdCost = 875; stCost = 1650; break;
                   case 10: wdCost = 1000; stCost = 1800; break;
                   default: redraw(); return;
               }
               if (wdTot >= wdCost && stTot >= stCost)
               {
                 wdTot -= wdCost;
                 stTot -= stCost;
                 LevW += 1;
               }           
               redraw();
               break;
        case 13:                            //Upgrade Bar
               if (unlB == true)
               {
                posit = 11;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");
                Event1.setText(" - So far from the exiles' home,");
                Event2.setText(" - culture is rarely found. While");
                Event3.setText(" - still missing here, the sight of");
                Event4.setText(" - full tankards is a comfort in the");
                Event5.setText(" - wilds, sure to attract all sorts.");
               }
               else
                redraw();
               break;
        case 14:                            //Loss of population

               redraw();
               break;        
        case 15:                            //Mining Camp assignment
            if (bldMC == false)
              break;
               posit = 17;
               Button1.setText("Allocate");
               Button2.setText("Remove");
               Button3.setText("Clear All");
               Button4.setText("Return");            
               redraw();
               break;
        case 16:                            //Forester Assignment Reduction
               if (fhPop == 0)
                break;
               invPop += 1;
               fhPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
        case 17:                            //Miner Assignment Reduction
               if (mcPop == 0)
                break;
               invPop += 1;
               mcPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
        case 18:                            //Nursery Assignment Reduction
               if (nPop == 0)
                break;
               invPop += 1;
               nPop -= 1;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;               
        }
    }//GEN-LAST:event_Button2ActionPerformed

    private void Button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3ActionPerformed
        switch(posit)
        {
        case 3:                                 //Explore/Forest3
               time += 10;
               if (unlExp == true)
               {
                exp += (rand.nextInt(pop) + 1);
                 if(exp >= 1000)
                 {
                  exp = 1000;
                  banKil = true;
                  unlExp = false;
                  Event1.setText(" - Hacking through the woods");
                  Event2.setText(" - with a fire burning in your");
                  Event3.setText(" - veins, you come upon the");
                  Event4.setText(" - bandit camp that has long");
                  Event5.setText(" - plagued your town. No more.");
                 }
                 else
                 {
                Event1.setText(" - Remembering the fallen exiles,");
                Event2.setText(" - you hunt through the woods after");
                Event3.setText(" - the cutthroats and brigands that");
                Event4.setText(" - hound after your town.");
                Event5.setText(" - Mapped " + exp + " of 1000 acres.");
                 }
               }
               else
                return;
               redraw();
               break;
        case 4:                                 //Town Hub Area
               if (unlTH == true)
                Button1.setText("Town Hall");
               else
                Button1.setText(" - ");
               if (unlFH == true)
                Button2.setText("Forester's Hut");
               else
                Button2.setText(" - ");
               if (unlMC == true)
                Button3.setText("Miner's Camp");
               else
                Button3.setText(" - ");
               if (unlN == true || unlB == true || unlW == true)
                Button4.setText("More");
               else
                Button4.setText("Return");
               posit = 6;
               Event1.setText(" - Ensuring the strength of the");
               Event2.setText(" - town is essential to its survival.");
               Event3.setText(" - Construct new buildings, upgrade");
               Event4.setText(" - those that you already have, and");
               Event5.setText(" - ensure that all is as it should be.");
               redraw();
               break;
        case 5:                                 //Excavate/Mine5
               time += 10;
               if (unlExc == true)
               {
                exc += (rand.nextInt(pop) + 1);
                 if(exc >= 1000)
                 {
                  exc = 1000;
                  unlExc = false;
                  Event1.setText(" - In awe and wonder, you look upon");
                  Event2.setText(" - the sprawling, once-buried kingdom");
                  Event3.setText(" - now revealed to the light of sun");
                  Event4.setText(" - again. Ancient secrets lie within,");
                  Event5.setText(" - the key to a glorious future.");
                 }
                 else
                 {
                  Event1.setText(" - Delicately, the stone is removed");
                  Event2.setText(" - from the spreading form of an");
                  Event3.setText(" - ancient city, each excavation");
                  Event4.setText(" - revealing a new expanse to the");
                  Event5.setText(" - kingdom. " + exc + " feet of 1000.");
                 }
               }
               else
                return;
               redraw();
               break;
        case 6:                                 //Miner's Camp/Town4
               if(unlMC == true)
               {
                posit = 9;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");            
                Event1.setText(" - An orderly array of picks and");
                Event2.setText(" - numerous tents arranged in orderly");
                Event3.setText(" - lines fills you with satisfaction.");
                Event4.setText(" - The sound of picks striking stone");
                Event5.setText(" - can be heard; the sound of progress.");
               }
               else
                redraw();
               break;
        case 7:                                 //Town Hall Option 3/Town Hall7
                   switch(LevTH)
               {
                   case 0: wdCost = 50; stCost = 75; break;
                   case 1: wdCost = 65; stCost = 100; break;
                   case 2: wdCost = 90; stCost = 125; break;
                   case 3: wdCost = 125; stCost = 150; break;
                   case 4: wdCost = 150; stCost = 200; break;
                   case 5: wdCost = 200; stCost = 250; break;
                   case 6: wdCost = 250; stCost = 300; break;
                   case 7: wdCost = 300; stCost = 350; break;
                   case 8: wdCost = 400; stCost = 450; break;
                   case 9: wdCost = 500; stCost = 500; break;
                   case 10: wdCost = 750; stCost = 750; break;
               }
                   Event1.setText(" - The Town Hall");
                   Event2.setText(" - Level: " + LevTH);
                   Event3.setText(" - To Upgrade to Level " + (LevTH + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);            
               redraw();
               break;
        case 8:                                 //Forester's Hut Option 3/Forester's Hut8
                   switch(LevFH)
               {
                   case 0: wdCost = 3; stCost = 0; break;
                   case 1: wdCost = 5; stCost = 0; break;
                   case 2: wdCost = 10; stCost = 0; break;
                   case 3: wdCost = 20; stCost = 0; break;
                   case 4: wdCost = 30; stCost = 5; break;
                   case 5: wdCost = 50; stCost = 15; break;
                   case 6: wdCost = 80; stCost = 30; break;
                   case 7: wdCost = 125; stCost = 50; break;
                   case 8: wdCost = 200; stCost = 80; break;
                   case 9: wdCost = 350; stCost = 125; break;
                   case 10: wdCost = 500; stCost = 200; break;
               }
                   Event1.setText(" - The Forester's Hut");
                   Event2.setText(" - Level: " + LevFH);
                   Event3.setText(" - To Upgrade to Level " + (LevFH + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);
               redraw();
               break;
        case 9:                                 //Miner's Camp Option 3/Miner's Camp9
                   switch(LevMC)
               {
                   case 0: wdCost = 10; stCost = 0; break;
                   case 1: wdCost = 15; stCost = 15; break;
                   case 2: wdCost = 25; stCost = 30; break;
                   case 3: wdCost = 50; stCost = 60; break;
                   case 4: wdCost = 80; stCost = 100; break;
                   case 5: wdCost = 125; stCost = 150; break;
                   case 6: wdCost = 175; stCost = 225; break;
                   case 7: wdCost = 250; stCost = 300; break;
                   case 8: wdCost = 350; stCost = 400; break;
                   case 9: wdCost = 475; stCost = 525; break;
                   case 10: wdCost = 600; stCost = 800; break;
               }
                   Event1.setText(" - The Miner's Camp");
                   Event2.setText(" - Level: " + LevMC);
                   Event3.setText(" - To Upgrade to Level " + (LevMC + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);            
               redraw();
               break;
        case 10:                                //Nursery Option 3/Nursery10
                   switch(LevN)
               {
                   case 0: wdCost = 75; stCost = 25; break;
                   case 1: wdCost = 100; stCost = 40; break;
                   case 2: wdCost = 125; stCost = 65; break;
                   case 3: wdCost = 175; stCost = 80; break;
                   case 4: wdCost = 240; stCost = 110; break;
                   case 5: wdCost = 315; stCost = 135; break;
                   case 6: wdCost = 430; stCost = 170; break;
                   case 7: wdCost = 550; stCost = 215; break;
                   case 8: wdCost = 700; stCost = 275; break;
                   case 9: wdCost = 900; stCost = 350; break;
                   case 10: wdCost = 1250; stCost = 450; break;
               }
                   Event1.setText(" - The Nursery");
                   Event2.setText(" - Level: " + LevN);
                   Event3.setText(" - To Upgrade to Level " + (LevN + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);            
               redraw();
               break;
        case 11:                                //Bar Option 3/Bar11
                   switch(LevB)
               {
                   case 0: wdCost = 125; stCost = 60; break;
                   case 1: wdCost = 175; stCost = 90; break;
                   case 2: wdCost = 250; stCost = 125; break;
                   case 3: wdCost = 350; stCost = 200; break;
                   case 4: wdCost = 475; stCost = 315; break;
                   case 5: wdCost = 635; stCost = 475; break;
                   case 6: wdCost = 800; stCost = 675; break;
                   case 7: wdCost = 950; stCost = 800; break;
                   case 8: wdCost = 1125; stCost = 975; break;
                   case 9: wdCost = 1350; stCost = 1250; break;
                   case 10: wdCost = 1500; stCost = 1500; break;
               }
                   Event1.setText(" - The Bar");
                   Event2.setText(" - Level: " + LevB);
                   Event3.setText(" - To Upgrade to Level " + (LevB + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);            
               redraw();
               break;
        case 12:                                //Wall Option3/Wall11
                   switch(LevW)
               {
                   case 0: wdCost = 25; stCost = 60; break;
                   case 1: wdCost = 45; stCost = 125; break;
                   case 2: wdCost = 75; stCost = 200; break;
                   case 3: wdCost = 125; stCost = 325; break;
                   case 4: wdCost = 235; stCost = 500; break;
                   case 5: wdCost = 350; stCost = 700; break;
                   case 6: wdCost = 425; stCost = 950; break;
                   case 7: wdCost = 600; stCost = 1175; break;
                   case 8: wdCost = 725; stCost = 1425; break;
                   case 9: wdCost = 875; stCost = 1650; break;
                   case 10: wdCost = 1000; stCost = 1800; break;
               }
                   Event1.setText(" - The Wall");
                   Event2.setText(" - Level: " + LevW);
                   Event3.setText(" - To Upgrade to Level " + (LevW + 1));
                   Event4.setText(" - Wood: " + wdCost);
                   Event5.setText(" - Stone: " + stCost);           
               redraw();
               break;
        case 13:                                //Upgrade Wall/Town Upgrade 1 6
               if (unlW == true)
               {
                posit = 12;
                Button1.setText("Construct");
                Button2.setText("Upgrade");
                Button3.setText("Status");
                Button4.setText("Return");            
                Event1.setText(" - The claustrophobic feeling of the");
                Event2.setText(" - dense treeline is put at ease with");
                Event3.setText(" - the structure surrounding the town.");
                Event4.setText(" - Any attack will have to breach this");
                Event5.setText(" - great wall if they wish to succeed.");
               }
               else
                redraw();
               break;
        case 14:                            //Loss of population
                
               redraw();
               break;            
        case 15:                            //Nursery Assignment
             if (bldN == false)
              break;
               posit = 18;
               Button1.setText("Allocate");
               Button2.setText("Remove");
               Button3.setText("Clear All");
               Button4.setText("Return");
               redraw();
               break;
        case 16:                            //Forester Assignment Clear All
               invPop += fhPop;
               fhPop = 0;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
        case 17:                            //Miner Assignment Clear All
               invPop += mcPop;
               mcPop = 0;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;
        case 18:                            //Nursery Assignment Clear All
               invPop += nPop;
               nPop = 0;
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);               
               redraw();
               break;               
        }
    }//GEN-LAST:event_Button3ActionPerformed

    private void Button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button4ActionPerformed
        switch(posit)
        {
        case 6:                                 //Toggle through upgrade buildings
            if (unlN == true || unlB == true || unlW == true)
            {
             if (unlN == true)
              Button1.setText("Nursery");
             else
              Button1.setText(" - ");
             if (unlB == true)
              Button2.setText("Bar");
             else
              Button2.setText(" - ");
             if (unlW == true)
              Button3.setText("Wall");
             else
              Button3.setText("");
             Button4.setText("Return");
             posit = 13;
            }
            else
            {
             posit = 4;
             Button1.setText("Forest");
             if (unlM == true)
              Button2.setText("Mines");
             else
              Button2.setText(" - ");
             Button3.setText("Town");
             Button4.setText("Status");
            }
            redraw();
            break;
        case 4:                                 //Status
            Event1.setText(" - Population: " + pop + "/" + ppCap);
            Event2.setText(" - Food: " + fdTot + "/" + fdCap);
            Event3.setText(" - Wood: " + wdTot + "/" + wdCap);
            Event4.setText(" - Stone: " + stTot + "/" + stCap);
            Event5.setText(" - FH: " + fhPop + "    MC: " + mcPop + "    N: " + nPop);
            Button1.setText("Foresters");
            Button2.setText("Miners");
            Button3.setText("Nursery");
            Button4.setText("Return");
            posit = 15;
            redraw();
            break;
        case 3:                                 //Return

        case 5:

        case 7:

        case 8:

        case 9:

        case 10:

        case 11:

        case 12:
            
        case 15:
            
        case 16:

        case 17:

        case 18:       

        case 13:
               time += 1;
               posit = 4;
               Button1.setText("Forest");
               if (bldMC == true)
                Button2.setText("Mine");
               else
                Button2.setText(" - ");
               Button3.setText("Town");
               Button4.setText("Status");
                    
               Event1.setText(" - Staggering back to town, flush");
               Event2.setText(" - with satisfaction at all that");
               Event3.setText(" - you have done, you turn to more");
               Event4.setText(" - domestic affairs before setting");
               Event5.setText(" - back out to the grinder of life.");
                    
               redraw();
               break;
        case 14:                            //Loss of population Exit
               System.exit(0);
               redraw();
               break;               
        }
    }//GEN-LAST:event_Button4ActionPerformed
    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Activity;
    private javax.swing.JLabel BLev;
    private javax.swing.JLabel Bar;
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JPanel Buttons;
    private javax.swing.JTextField Entry;
    private javax.swing.JPanel Event;
    private javax.swing.JLabel Event1;
    private javax.swing.JLabel Event2;
    private javax.swing.JLabel Event3;
    private javax.swing.JLabel Event4;
    private javax.swing.JLabel Event5;
    private javax.swing.JLabel ExcExp;
    private javax.swing.JButton Exit;
    private javax.swing.JLabel FHLev;
    private javax.swing.JLabel FoodDisplay;
    private javax.swing.JLabel Forester;
    private javax.swing.JLabel MCLev;
    private javax.swing.JLabel Miners;
    private javax.swing.JLabel NLev;
    private javax.swing.JLabel Nursery;
    private javax.swing.JLabel Pop;
    private javax.swing.JLabel StoneDisplay;
    private javax.swing.JLabel THLev;
    private javax.swing.JPanel Town;
    private javax.swing.JLabel TownHall;
    private javax.swing.JLabel TownName;
    private javax.swing.JLabel WLev;
    private javax.swing.JLabel Wall;
    private javax.swing.JLabel Week;
    private javax.swing.JLabel WoodDisplay;
    // End of variables declaration//GEN-END:variables
}
