import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import accounts.customer;
import flights.flight;

public class airline {
        public static void main(String[] args) throws IOException, NullPointerException, FontFormatException {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                JFrame fAccounts= new JFrame("Airline");


                JLabel text = new JLabel("Accounts");
                text.setFont(new Font("Algerian", Font.BOLD, 40));
                text.setBounds(940,20,250,250);
                fAccounts.add(text);

                JButton bLogin = new JButton("Login");
                bLogin.setBounds(950,250,200,60);
                JButton bSignup = new JButton("Signup");
                bSignup.setBounds(950,350,200,60);
                fAccounts.add(bLogin);
                fAccounts.add(bSignup);
//------------when signup is pressed
                bSignup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                signupView(fAccounts,false);
                        }
                });
//------------when login is pressed
                bLogin.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                loginView(fAccounts, false);
                        }
                });

                fAccounts.setLayout(new BorderLayout());
                JLabel background=new JLabel(new ImageIcon("src/assets/bg.png"));
                background.setBounds(0,0,screenSize.width,screenSize.height);
                fAccounts.add(background);
                background.setLayout(new FlowLayout());




                fAccounts.setSize(screenSize.width,screenSize.height);
                fAccounts.setVisible(true);


        }


    public static void signupView(JFrame frame, boolean error){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame fsignup= new JFrame("Signup");

        JLabel text = new JLabel("Accounts");
        text.setFont(new Font("Algerian", Font.BOLD, 40));
        text.setBounds(940,20,250,250);
        fsignup.add(text);
        if(error) {
                JLabel err = new JLabel("Invalid Input");
                err.setFont(new Font("Arial", Font.BOLD, 30));
                err.setBounds(970, 520, 200, 100);
                fsignup.add(err);
        }

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(800,250,150,40);
        fsignup.add(nameLabel);
        JTextField name = new JTextField();
        name.setBounds(950,250,200,40);
        fsignup.add(name);


        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(800,300,150,40);
        fsignup.add(usernameLabel);
        JTextField user = new JTextField();
        user.setBounds(950,300,200,40);
        fsignup.add(user);


        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(800,350,150,40);
        fsignup.add(passLabel);
        JTextField pass = new JPasswordField();
        pass.setBounds(950,350,200,40);
        fsignup.add(pass);


        JLabel pass1Label = new JLabel("Confirm Password: ");
        pass1Label.setBounds(800,400,150,40);
        fsignup.add(pass1Label);
        JTextField pass2 = new JPasswordField();
        pass2.setBounds(950,400,200,40);
        fsignup.add(pass2);

        JButton bsignup = new JButton("signup");
        bsignup.setBounds(990,470,100,40);
        fsignup.add(bsignup);

        bsignup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        String nam = name.getText();
                        String username = user.getText();
                        String password = pass.getText();
                        String password2 = pass2.getText();
                        try {
                                if (password.equals(password2)) {
                                        customer obj1 = customer.signup(nam, username, password);
                                        if (obj1!=null){
                                                loginView(fsignup,false);
                                        }
                                }
                                else{
                                        signupView(fsignup,true);
                                }
                        } catch (IOException ioException) {
                                ioException.printStackTrace();
                        }

                }
        });

        fsignup.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        fsignup.add(background);
        background.setLayout(new FlowLayout());


        fsignup.setSize(screenSize.width,screenSize.height);
        fsignup.setVisible(true);
}
public static void loginView(JFrame frame, boolean error){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame fLogin= new JFrame("Login");

        JLabel text = new JLabel("Accounts");
        text.setFont(new Font("Algerian", Font.BOLD, 40));
        text.setBounds(940,20,250,250);
        fLogin.add(text);

        if(error) {
                JLabel err = new JLabel("Invalid Input");
                err.setFont(new Font("Arial", Font.BOLD, 30));
                err.setBounds(970, 520, 200, 100);
                fLogin.add(err);
        }
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(800,250,150,40);
        fLogin.add(userLabel);
        JTextField user = new JTextField();
        user.setBounds(950,250,200,40);
        fLogin.add(user);


        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(800,300,150,40);
        fLogin.add(passLabel);
        JTextField pass = new JPasswordField ();
        pass.setBounds(950,300,200,40);
        fLogin.add(pass);
        JButton bLogin = new JButton("Login");
        bLogin.setBounds(990,370,100,40);
        fLogin.add(bLogin);


        //-----------------when login button is pressed
        bLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        String userna = user.getText();
                        String passw = pass.getText();
                        try {
                                customer obj1 = customer.login(userna, passw);
                                if (obj1!=null){
                                        dashboardView(fLogin,obj1);
                                }
                                else {
                                        loginView(fLogin,true);
                                }
                        } catch (FileNotFoundException | ParseException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                        }
                }
        });

        fLogin.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        fLogin.add(background);
        background.setLayout(new FlowLayout());


        fLogin.setSize(screenSize.width,screenSize.height);
        fLogin.setVisible(true);


        }
public static void dashboardView(JFrame frame, customer obj) throws FileNotFoundException, ParseException {

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                JFrame fDashboard= new JFrame("Dashboard");

//all function of manager are in this if condtion and else have all for cutomers
                if (obj.isManager){
                    JButton bAddFlight = new JButton("Add Flight");
                    bAddFlight.setFont(new Font("Algerian", Font.BOLD, 40));
                    bAddFlight.setOpaque(false);
                    bAddFlight.setContentAreaFilled(false);
                    bAddFlight.setBorderPainted(false);
                    bAddFlight.setBounds(10,250,300,200);
                    fDashboard.add(bAddFlight);
                    bAddFlight.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                addFlightView(fDashboard);
                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                            }
                        }
                    });


                    JButton bAllFlight = new JButton("All Flights");
                    bAllFlight.setFont(new Font("Algerian", Font.BOLD, 40));
                    bAllFlight.setOpaque(false);
                    bAllFlight.setContentAreaFilled(false);
                    bAllFlight.setBorderPainted(false);
                    bAllFlight.setBounds(10,350,300,200);
                    fDashboard.add(bAllFlight);
                    bAllFlight.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            allFlightsView(fDashboard);
                        }
                    });

                    JButton bLogout = new JButton("Logout");
                    bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
                    bLogout.setOpaque(false);
                    bLogout.setContentAreaFilled(false);
                    bLogout.setBorderPainted(false);
                    bLogout.setBounds(10,450,300,200);
                    fDashboard.add(bLogout);

                    bLogout.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loginView(fDashboard,false);
                        }
                    });
                }
                else{
                    JButton bBook = new JButton("Book Ticket");
                    bBook.setFont(new Font("Algerian", Font.BOLD, 30));
                    bBook.setOpaque(false);
                    bBook.setContentAreaFilled(false);
                    bBook.setBorderPainted(false);
                    bBook.setBounds(10,250,300,200);
                    fDashboard.add(bBook);
                    bBook.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                bookingView(fDashboard,obj);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                        }
                    });

                    JButton bSearchFlight = new JButton("Search Flight");
                    bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
                    bSearchFlight.setOpaque(false);
                    bSearchFlight.setContentAreaFilled(false);
                    bSearchFlight.setBorderPainted(false);
                    bSearchFlight.setBounds(10,100,300,200);
                    fDashboard.add(bSearchFlight);

                    JButton bCancel = new JButton("Cancel Ticket");
                    bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
                    bCancel.setOpaque(false);
                    bCancel.setContentAreaFilled(false);
                    bCancel.setBorderPainted(false);
                    bCancel.setBounds(10,350,300,200);
                    fDashboard.add(bCancel);
                    bCancel.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cancelView(fDashboard,obj);
                        }
                    });

                    JButton bExisting = new JButton("View Existing");
                    bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
                    bExisting.setOpaque(false);
                    bExisting.setContentAreaFilled(false);
                    bExisting.setBorderPainted(false);
                    bExisting.setBounds(10,450,300,200);
                    fDashboard.add(bExisting);
                    bExisting.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            existingView(fDashboard,obj);
                        }
                    });

                    JButton bLogout = new JButton("Logout");
                    bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
                    bLogout.setOpaque(false);
                    bLogout.setContentAreaFilled(false);
                    bLogout.setBorderPainted(false);
                    bLogout.setBounds(10,550,300,200);
                    fDashboard.add(bLogout);

                    bLogout.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loginView(fDashboard,false);
                        }
                    });

                    String[] allFlights = flight.allFlights();
                    String[] fromAll = new String[allFlights.length];
                    String[] toAll = new String[allFlights.length];
                    for(int i=0; i<allFlights.length;i++){
                        fromAll[i]="";
                        toAll[i]="";
                    }
                    int counterFromAll = 0;
                    int counterToAll = 0;
                    for(int i=0; i<allFlights.length;i++){
                        String fromString = allFlights[i].split(",")[1];
                        boolean fromStringAdd= true;
                        boolean toStringAdd= true;
                        String toString = allFlights[i].split(",")[2];
                        for(int j=0; j<allFlights.length;j++){
                            if(fromString.toLowerCase().equals(fromAll[j].toLowerCase())){
                                fromStringAdd=false;
                            }
                            if(toString.toLowerCase().equals(toAll[j].toLowerCase())){
                                toStringAdd=false;
                            }
                        }
                        if(fromStringAdd){
                            fromAll[counterFromAll]=fromString;
                            counterFromAll++;
                        }
                        if(toStringAdd){
                            toAll[counterToAll]=toString;
                            counterToAll++;
                        }
                    }
                    JLabel from_Label = new JLabel("From City: ");
                    from_Label.setBounds(500,100,100,50);
                    fDashboard.add(from_Label);
                    JComboBox cFrom = new JComboBox(fromAll);
                    cFrom.setBounds(600,100,400,50);
                    fDashboard.add(cFrom);

                    JLabel to_Label = new JLabel("Destination City: ");
                    to_Label.setBounds(500,200,100,50);
                    fDashboard.add(to_Label);
                    JComboBox cTo = new JComboBox(toAll);
                    cTo.setBounds(600,200,400,50);
                    fDashboard.add(cTo);

                    MaskFormatter mask1 = new MaskFormatter("##-##-####");//the # is for numeric values
                    mask1.setPlaceholderCharacter('#');
                    JFormattedTextField txtDate = new JFormattedTextField(mask1);
                    txtDate.setBounds(730,300,200,30);
                    fDashboard.add(txtDate);
                    JLabel dateName = new JLabel("Date(MM-DD-YYYY):");
                    dateName.setBounds(620,300,700,30);
                    fDashboard.add(dateName);

                    txtDate.addKeyListener(new KeyAdapter() {
                        public void keyTyped(KeyEvent e) {
                            char c = e.getKeyChar();
                            if (!((c >= '0') && (c <= '9') ||
                                    (c == KeyEvent.VK_BACK_SPACE) ||
                                    (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                            {
                                JOptionPane.showMessageDialog(null, "Please Enter Valid");
                                e.consume();
                            }
                        }
                    });


                    MaskFormatter mask = new MaskFormatter("##:##");//the # is for numeric values
                    mask.setPlaceholderCharacter('#');
                    JFormattedTextField timeField = new JFormattedTextField(mask);
                    timeField.setBounds(730,400,200,30);
                    fDashboard.add(timeField);
                    JLabel timeName = new JLabel("Time(HH-MM):");
                    timeName.setBounds(620,400,200,30);
                    fDashboard.add(timeName);

                    JButton bSearch = new JButton("Seach");
                    bSearch.setBounds(700,500,100,30);
                    fDashboard.add(bSearch);
                    bSearch.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String selectedFrom = String.valueOf(cFrom.getSelectedItem());
                            String selectedTo = String.valueOf(cTo.getSelectedItem());
                            String selectedDate = txtDate.getText();
                            String selectedTime = timeField.getText();
                            try {
                                showFights(fDashboard,selectedFrom,selectedTo,selectedDate,selectedTime,obj);
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }


                        }
                    });

                }



                fDashboard.setLayout(new BorderLayout());
                JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
                background.setBounds(0,0,screenSize.width,screenSize.height);
                fDashboard.add(background);
                background.setLayout(new FlowLayout());


                fDashboard.setSize(screenSize.width,screenSize.height);
                fDashboard.setVisible(true);

        }
    public static void allFlightsView(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");

        JLabel text = new JLabel("All Flights");
        text.setFont(new Font("Arial", Font.BOLD, 30));
        text.setBounds(770,0,350,150);
        frame1.add(text);

        JButton bAddFlight = new JButton("Add Flight");
        bAddFlight.setFont(new Font("Algerian", Font.BOLD, 40));
        bAddFlight.setOpaque(false);
        bAddFlight.setContentAreaFilled(false);
        bAddFlight.setBorderPainted(false);
        bAddFlight.setBounds(10,250,300,200);
        frame1.add(bAddFlight);
        bAddFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addFlightView(frame1);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });

        JButton bAllFlight = new JButton("All Flights");
        bAllFlight.setFont(new Font("Algerian", Font.BOLD, 40));
        bAllFlight.setOpaque(false);
        bAllFlight.setContentAreaFilled(false);
        bAllFlight.setBorderPainted(false);
        bAllFlight.setBounds(10,100,300,200);
        frame1.add(bAllFlight);

        String[] allList= new String[0];
        try {
            allList = flight.allFlights();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        String[] header = {"Flight ID", "From", "To","Date/Time", "Total Seats"};
        String[][] doubleData = new String[allList.length][5];
        for (int i=0; i<allList.length;i++){
            doubleData[i] = allList[i].split(",");
        }

        JTable toDoTable = new JTable(doubleData, header);
        JScrollPane jpane = new JScrollPane(toDoTable);
        JPanel panel = new JPanel();
        panel.add(jpane);
        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBounds(600,140,490,455);
        frame1.add(scrollPanel);

        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,350,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });

        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }
    public static void addFlightView(JFrame frame) throws ParseException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");

        JLabel text = new JLabel("Add Flights");
        text.setFont(new Font("Arial", Font.BOLD, 30));
        text.setBounds(740,0,350,150);
        frame1.add(text);

        JButton bAddFlight = new JButton("Add Flight");
        bAddFlight.setFont(new Font("Algerian", Font.BOLD, 40));
        bAddFlight.setOpaque(false);
        bAddFlight.setContentAreaFilled(false);
        bAddFlight.setBorderPainted(false);
        bAddFlight.setBounds(10,100,300,200);
        frame1.add(bAddFlight);

        JButton bAllFlight = new JButton("All Flights");
        bAllFlight.setFont(new Font("Algerian", Font.BOLD, 40));
        bAllFlight.setOpaque(false);
        bAllFlight.setContentAreaFilled(false);
        bAllFlight.setBorderPainted(false);
        bAllFlight.setBounds(10,250,300,200);
        frame1.add(bAllFlight);
        bAllFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allFlightsView(frame1);
            }
        });

        JTextField fromCity = new JTextField();
        fromCity.setBounds(730,200,200,30);
        frame1.add(fromCity);

        JLabel cityName = new JLabel("From city: ");
        cityName.setBounds(620,200,700,30);
        frame1.add(cityName);

        JTextField destinationCity = new JTextField();
        destinationCity.setBounds(730,250,200,30);
        frame1.add(destinationCity);
        JLabel destinationName = new JLabel("Destination city: ");
        destinationName.setBounds(620,250,700,30);
        frame1.add(destinationName);

        JTextField totalSeats = new JTextField();
        totalSeats.setBounds(730,300,200,30);
        frame1.add(totalSeats);
        JLabel seatsName = new JLabel("Seats in Flight: ");
        seatsName.setBounds(620,300,700,30);
        frame1.add(seatsName);

        MaskFormatter mask1 = new MaskFormatter("##-##-####");//the # is for numeric values
        mask1.setPlaceholderCharacter('#');
        JFormattedTextField txtDate = new JFormattedTextField(mask1);
        txtDate.setBounds(730,350,200,30);
        frame1.add(txtDate);
        JLabel dateName = new JLabel("Date(MM-DD-YYYY):");
        dateName.setBounds(620,350,700,30);
        frame1.add(dateName);

        txtDate.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });


        MaskFormatter mask = new MaskFormatter("##:##");//the # is for numeric values
        mask.setPlaceholderCharacter('#');
        JFormattedTextField timeField = new JFormattedTextField(mask);
        timeField.setBounds(730,400,200,30);
        frame1.add(timeField);
        JLabel timeName = new JLabel("Time(HH-MM):");
        timeName.setBounds(620,400,700,30);
        frame1.add(timeName);


        //Add ActionListener for when enter is pressed
        timeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object source = ae.getSource();
                if (source == timeField) {
                    //parse to a valid time here
//                    System.out.println(timeField.getText());

                }
            }
        });

        JButton bAdd = new JButton("Add");
        bAdd.setBounds(780,500,100,40);
        frame1.add(bAdd);
        bAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromCity.getText();
                String to = destinationCity.getText();
                String dateInput = txtDate.getText();
                String timeInput = timeField.getText();
                int seats = Integer.parseInt(totalSeats.getText());
                try {
                    flight fliobj = flight.createFlight(from,to,dateInput+"__"+timeInput, seats);
                    if(fliobj!=null){
                        allFlightsView(frame1);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });




        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,350,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });

        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }


    public static void existingView(JFrame frame, customer customer_obj){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");
        JButton bBook = new JButton("Book Ticket");
        bBook.setFont(new Font("Algerian", Font.BOLD, 30));
        bBook.setOpaque(false);
        bBook.setContentAreaFilled(false);
        bBook.setBorderPainted(false);
        bBook.setBounds(10,250,300,200);
        frame1.add(bBook);
        bBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookingView(frame1,customer_obj);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


        JButton bCancel = new JButton("Cancel Ticket");
        bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
        bCancel.setOpaque(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setBorderPainted(false);
        bCancel.setBounds(10,350,300,200);
        frame1.add(bCancel);
        bCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelView(frame1,customer_obj);
            }
        });

        JButton bExisting = new JButton("View Existing");
        bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
        bExisting.setOpaque(false);
        bExisting.setContentAreaFilled(false);
        bExisting.setBorderPainted(false);
        bExisting.setBounds(10,100,300,200);
        frame1.add(bExisting);


        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,450,300,200);
        frame1.add(bSearchFlight);

        bSearchFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dashboardView(frame1, customer_obj);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });

        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,550,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });




        JLabel tick_Label=new JLabel("Enter Ticket Number: ");
        tick_Label.setBounds(500, 290, 200, 50);
        frame1.add(tick_Label);

        JTextField ticket_No = new JTextField();
        ticket_No.setBounds(650,300,200,30);
        frame1.add(ticket_No);

        JButton bToView = new JButton("View Ticket");
        bToView.setBounds(625,400,200,40);
        frame1.add(bToView);
        bToView.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int ticketNoToCancel = Integer.parseInt(ticket_No.getText());
                try {
                    viewExistingTicket(frame1,ticketNoToCancel,customer_obj);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }


    public static void cancelView(JFrame frame, customer customer_obj){

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            JFrame frame1= new JFrame("Dashboard");
            JButton bBook = new JButton("Book Ticket");
            bBook.setFont(new Font("Algerian", Font.BOLD, 30));
            bBook.setOpaque(false);
            bBook.setContentAreaFilled(false);
            bBook.setBorderPainted(false);
            bBook.setBounds(10,250,300,200);
            frame1.add(bBook);
            bBook.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        bookingView(frame1,customer_obj);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            });


            JButton bCancel = new JButton("Cancel Ticket");
            bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
            bCancel.setOpaque(false);
            bCancel.setContentAreaFilled(false);
            bCancel.setBorderPainted(false);
            bCancel.setBounds(10,100,300,200);
            frame1.add(bCancel);

            JButton bExisting = new JButton("View Existing");
            bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
            bExisting.setOpaque(false);
            bExisting.setContentAreaFilled(false);
            bExisting.setBorderPainted(false);
            bExisting.setBounds(10,350,300,200);
            frame1.add(bExisting);
            bExisting.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    existingView(frame1,customer_obj);
                }
            });
        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,450,300,200);
        frame1.add(bSearchFlight);

        bSearchFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dashboardView(frame1, customer_obj);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });

        JButton bLogout = new JButton("Logout");
            bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
            bLogout.setOpaque(false);
            bLogout.setContentAreaFilled(false);
            bLogout.setBorderPainted(false);
            bLogout.setBounds(10,550,300,200);
            frame1.add(bLogout);

            bLogout.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    loginView(frame1,false);
                }
        });




        JLabel tickLabel=new JLabel("Enter Ticket Number: ");
        tickLabel.setBounds(500, 290, 200, 50);
        frame1.add(tickLabel);

        JTextField ticketNo = new JTextField();
        ticketNo.setBounds(650,300,200,30);
        frame1.add(ticketNo);

        JButton bToCancel = new JButton("Cancel Ticket");
        bToCancel.setBounds(625,400,200,40);
        frame1.add(bToCancel);
        bToCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int ticketNoToCancel = Integer.parseInt(ticketNo.getText());
                try {
                    flight.cancelBooking(ticketNoToCancel);
                    bookingView(frame1,customer_obj);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
}


    public static void bookingView(JFrame frame, customer obj_customer) throws FileNotFoundException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");
        JButton bBook = new JButton("Book Ticket");
        bBook.setFont(new Font("Algerian", Font.BOLD, 30));
        bBook.setOpaque(false);
        bBook.setContentAreaFilled(false);
        bBook.setBorderPainted(false);
        bBook.setBounds(10,100,300,200);
        frame1.add(bBook);


        JButton bCancel = new JButton("Cancel Ticket");
        bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
        bCancel.setOpaque(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setBorderPainted(false);
        bCancel.setBounds(10,350,300,200);
        frame1.add(bCancel);
        bCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelView(frame1, obj_customer);
            }
        });

        JButton bExisting = new JButton("View Existing");
        bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
        bExisting.setOpaque(false);
        bExisting.setContentAreaFilled(false);
        bExisting.setBorderPainted(false);
        bExisting.setBounds(10,250,300,200);
        frame1.add(bExisting);
        bExisting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                existingView(frame1, obj_customer);
            }
        });

        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,450,300,200);
        frame1.add(bSearchFlight);

        bSearchFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dashboardView(frame1, obj_customer);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });


        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,550,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });

        String[] s1 = flight.allFlights();
        for (int i=0;i<s1.length;i++){
            s1[i] = s1[i].replaceAll("__",",");
            s1[i] = s1[i].replaceAll(","," , ");
        }

        JLabel c1_Label = new JLabel("Select Flight: ");
        c1_Label.setBounds(500,100,100,50);
        frame1.add(c1_Label);

        JComboBox c1 = new JComboBox(s1);
        c1.setBounds(600,100,400,50);
        frame1.add(c1);

        JLabel c1Label = new JLabel("FlightID , From , To , Date , Time , Total Seats ");
        c1Label.setBounds(600,50,400,50);
        frame1.add(c1Label);

        JTextField noOfSeats = new JTextField();
        noOfSeats.setBounds(700,300,200,40);
        frame1.add(noOfSeats);

        JLabel seatsLabel = new JLabel("Enter Number of to book: ");
        seatsLabel.setBounds(500,300,400,50);
        frame1.add(seatsLabel);

        JButton doBook = new JButton("Book");
        doBook.setBounds(730,400,100,40);
        frame1.add(doBook);

        doBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int noOfSeats1 = Integer.parseInt(noOfSeats.getText());
                    selectingSeatsView(frame1, noOfSeats1,Integer.parseInt(String.valueOf(c1.getSelectedItem()).split(",")[0].replaceAll(" ","")),obj_customer);
                } catch (FileNotFoundException | ParseException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });


        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }
    public static void selectingSeatsView(JFrame frame,int noOfSeatsToBook,int flightNo, customer obj_customer) throws FileNotFoundException, ParseException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");
        JButton bBook = new JButton("Book Ticket");
        bBook.setFont(new Font("Algerian", Font.BOLD, 30));
        bBook.setOpaque(false);
        bBook.setContentAreaFilled(false);
        bBook.setBorderPainted(false);
        bBook.setBounds(10,100,300,200);
        frame1.add(bBook);


        JButton bCancel = new JButton("Cancel Ticket");
        bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
        bCancel.setOpaque(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setBorderPainted(false);
        bCancel.setBounds(10,350,300,200);
        frame1.add(bCancel);
        bCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelView(frame1, obj_customer);
            }
        });

        JButton bExisting = new JButton("View Existing");
        bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
        bExisting.setOpaque(false);
        bExisting.setContentAreaFilled(false);
        bExisting.setBorderPainted(false);
        bExisting.setBounds(10,250,300,200);
        frame1.add(bExisting);
        bExisting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                existingView(frame1, obj_customer);
            }
        });

        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,450,300,200);
        frame1.add(bSearchFlight);

        bSearchFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dashboardView(frame1, obj_customer);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });


        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,550,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });
        File myObj = new File("src/database/flights/"+flightNo+".txt");
        Scanner myReader = new Scanner(myObj);
        String[] unbooked_list = null;
        int counterForUnbooked=0;
        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] allSeats = data.split(",");
            unbooked_list = new String[allSeats.length];
            for (int i=0; i<allSeats.length;i++){
                if(allSeats[i].split("--").length==1){
                    unbooked_list[counterForUnbooked] = allSeats[i];
                    counterForUnbooked++;
                    }
                }
        }
        String[][] doubleData = new String[unbooked_list.length][1];
        for (int i=0;i<counterForUnbooked;i++){
            doubleData[i][0]=unbooked_list[i];
        }
        String[] header = {"Available Seats"};
        JTable toDoTable = new JTable(doubleData, header);
        JScrollPane jpane = new JScrollPane(toDoTable);
        JPanel panel = new JPanel();
        panel.add(jpane);
        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setBounds(600,10,490,455);
        frame1.add(scrollPanel);

        String formatString = "";
        for (int i=0;i<noOfSeatsToBook;i++){
            formatString+="##,";
        }
        MaskFormatter mask1 = new MaskFormatter(formatString);//the # is for numeric values
        mask1.setPlaceholderCharacter('#');
        JFormattedTextField txtDate = new JFormattedTextField(mask1);
        txtDate.setBounds(800,500,200,30);
        frame1.add(txtDate);
        JLabel dateName = new JLabel("Enter Tickets No.(No,No...):");
        dateName.setBounds(600,500,700,30);
        frame1.add(dateName);

        txtDate.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });

        JButton bConfirm = new JButton("Confirm");
        bConfirm.setBounds(775,575,100,30);
        frame1.add(bConfirm);
        bConfirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String seatNos__ = txtDate.getText();
                String[] listSeatNos = seatNos__.split(",");
                int duplicate=0;
                for(int i=0;i<listSeatNos.length;i++){
                    for (int j=0;j<listSeatNos.length;j++){
                        if(i!=j){
                            if(listSeatNos[i]==listSeatNos[j]) {
                                duplicate++;
                            }
                            }
                    }
                }
                if(duplicate==0 && listSeatNos.length>0){
                    try {
                        int id_booking = flight.doBookSeat(obj_customer,flightNo,seatNos__);
                        viewExistingTicket(frame1,id_booking,obj_customer);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }
    public static void viewExistingTicket(JFrame frame,int ticketNo ,customer objectCustomer) throws FileNotFoundException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");
        JButton bBook = new JButton("Book Ticket");
        bBook.setFont(new Font("Algerian", Font.BOLD, 30));
        bBook.setOpaque(false);
        bBook.setContentAreaFilled(false);
        bBook.setBorderPainted(false);
        bBook.setBounds(10,450,300,200);
        frame1.add(bBook);
        bBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookingView(frame1, objectCustomer);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


        JButton bCancel = new JButton("Cancel Ticket");
        bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
        bCancel.setOpaque(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setBorderPainted(false);
        bCancel.setBounds(10,350,300,200);
        frame1.add(bCancel);
        bCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelView(frame1, objectCustomer);
            }
        });



        JButton bExisting = new JButton("View Existing");
        bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
        bExisting.setOpaque(false);
        bExisting.setContentAreaFilled(false);
        bExisting.setBorderPainted(false);
        bExisting.setBounds(10,100,300,200);
        frame1.add(bExisting);
        bExisting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                existingView(frame1, objectCustomer);
            }
        });
        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,250,300,200);
        frame1.add(bSearchFlight);

        bSearchFlight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dashboardView(frame1, objectCustomer);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });


        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,550,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });


        File myObj_ = new File("src/database/bookings.txt");
        Scanner myReader_ = new Scanner(myObj_);
        int flightNo=0;
        boolean flag = false;
        String seats = "";
        int counter=0;
        while (myReader_.hasNextLine()) {
            String data = myReader_.nextLine();
            if (counter!=0){
                int ticNo = Integer.parseInt(data.split(",")[0]);
                if (ticNo==ticketNo){
                    flag=true;
                    flightNo = Integer.parseInt(data.split(",")[1]);
                    seats = data.split(",")[2];
                }
            }
            counter=1;
        }
        if (flag){
            flight objFlight = flight.search(flightNo);
            JLabel topLabel = new JLabel("Ticket Details");
            topLabel.setFont(new Font("Algerian", Font.BOLD, 30));
            topLabel.setBounds(650,0,300,100);
            frame1.add(topLabel);

            JLabel tick_number = new JLabel("Ticket Number: ");
            tick_number.setFont(new Font("Arial", Font.BOLD, 15));
            tick_number.setBounds(640,150,300,100);
            frame1.add(tick_number);
            JLabel tick_number_ = new JLabel(String.valueOf(ticketNo));
            tick_number_.setFont(new Font("Arial", Font.BOLD, 15));
            tick_number_.setBounds(850,150,300,100);
            frame1.add(tick_number_);

            JLabel seat_number = new JLabel("Seat Numbers: ");
            seat_number.setFont(new Font("Arial", Font.BOLD, 15));
            seat_number.setBounds(640,190,300,100);
            frame1.add(seat_number);
            JLabel seat_number_ = new JLabel(seats.replaceAll("--",","));
            seat_number_.setFont(new Font("Arial", Font.BOLD, 15));
            seat_number_.setBounds(850,190,300,100);
            frame1.add(seat_number_);

            JLabel flight_number = new JLabel("Flight Number: ");
            flight_number.setFont(new Font("Arial", Font.BOLD, 15));
            flight_number.setBounds(640,230,300,100);
            frame1.add(flight_number);
            JLabel flight_number_ = new JLabel(String.valueOf(objFlight.id));
            flight_number_.setFont(new Font("Arial", Font.BOLD, 15));
            flight_number_.setBounds(850,230,300,100);
            frame1.add(flight_number_);

            JLabel from = new JLabel("From City: ");
            from.setFont(new Font("Arial", Font.BOLD, 15));
            from.setBounds(640,270,300,100);
            frame1.add(from);
            JLabel from_ = new JLabel(objFlight.from);
            from_.setFont(new Font("Arial", Font.BOLD, 15));
            from_.setBounds(850,270,300,100);
            frame1.add(from_);

            JLabel fdate = new JLabel("Date/Time: ");
            fdate.setFont(new Font("Arial", Font.BOLD, 15));
            fdate.setBounds(640,310,300,100);
            frame1.add(fdate);
            JLabel fdate_ = new JLabel(objFlight.departure.replaceAll("__"," "));
            fdate_.setFont(new Font("Arial", Font.BOLD, 15));
            fdate_.setBounds(850,310,300,100);
            frame1.add(fdate_);

            JLabel tseats = new JLabel("Total Seats in Flight: ");
            tseats.setFont(new Font("Arial", Font.BOLD, 15));
            tseats.setBounds(640,350,300,100);
            frame1.add(tseats);
            JLabel tseats_ = new JLabel(objFlight.to);
            tseats_.setFont(new Font("Arial", Font.BOLD, 15));
            tseats_.setBounds(850,350,300,100);
            frame1.add(tseats_);


        }
        else{

        }
        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }
    public static void showFights(JFrame frame,String selectedFrom,String selectedTo,String selectedDate,String selectedTime, customer objectCustomer) throws FileNotFoundException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        JFrame frame1= new JFrame("Dashboard");
        JButton bBook = new JButton("Book Ticket");
        bBook.setFont(new Font("Algerian", Font.BOLD, 30));
        bBook.setOpaque(false);
        bBook.setContentAreaFilled(false);
        bBook.setBorderPainted(false);
        bBook.setBounds(10,450,300,200);
        frame1.add(bBook);
        bBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookingView(frame1, objectCustomer);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });


        JButton bCancel = new JButton("Cancel Ticket");
        bCancel.setFont(new Font("Algerian", Font.BOLD, 30));
        bCancel.setOpaque(false);
        bCancel.setContentAreaFilled(false);
        bCancel.setBorderPainted(false);
        bCancel.setBounds(10,350,300,200);
        frame1.add(bCancel);
        bCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelView(frame1, objectCustomer);
            }
        });

        JButton bExisting = new JButton("View Existing");
        bExisting.setFont(new Font("Algerian", Font.BOLD, 30));
        bExisting.setOpaque(false);
        bExisting.setContentAreaFilled(false);
        bExisting.setBorderPainted(false);
        bExisting.setBounds(10,250,300,200);
        frame1.add(bExisting);
        bExisting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                existingView(frame1, objectCustomer);
            }
        });
        JButton bSearchFlight = new JButton("Search Flight");
        bSearchFlight.setFont(new Font("Algerian", Font.BOLD, 30));
        bSearchFlight.setOpaque(false);
        bSearchFlight.setContentAreaFilled(false);
        bSearchFlight.setBorderPainted(false);
        bSearchFlight.setBounds(10,100,300,200);
        frame1.add(bSearchFlight);



        JButton bLogout = new JButton("Logout");
        bLogout.setFont(new Font("Algerian", Font.BOLD, 40));
        bLogout.setOpaque(false);
        bLogout.setContentAreaFilled(false);
        bLogout.setBorderPainted(false);
        bLogout.setBounds(10,550,300,200);
        frame1.add(bLogout);

        bLogout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loginView(frame1,false);
            }
        });



        try {
            String[] allFlightToFilter = flight.allFlights();
            String[] flightsFiltered = new String[allFlightToFilter.length];
            for(int i=0; i<allFlightToFilter.length;i++){
                flightsFiltered[i] = "";
            }
            int filteredCounter = 0;
            for(int i=0; i<allFlightToFilter.length;i++){
                String[] currentFlight = allFlightToFilter[i].split(",");
                boolean checkingFrom = currentFlight[1].toLowerCase().equals(selectedFrom.toLowerCase());
                boolean checkingTo = currentFlight[2].toLowerCase().equals(selectedTo.toLowerCase());
                boolean checkingDate = (selectedDate+"__"+selectedTime).toLowerCase().equals(currentFlight[3].toLowerCase());
                if(checkingFrom && checkingTo && checkingDate){
                    flightsFiltered[filteredCounter]=allFlightToFilter[i];
                    filteredCounter++;
                }
            }


            String[] header = {"Flight ID", "From", "To","Date/Time", "Total Seats"};
            String[][] doubleData = new String[flightsFiltered.length][5];
            for (int i=0; i<flightsFiltered.length;i++){
                if(flightsFiltered[i].length()>1) {
                    doubleData[i] = flightsFiltered[i].split(",");
                }
            }

            JTable toDoTable = new JTable(doubleData, header);
            JScrollPane jpane = new JScrollPane(toDoTable);
            JPanel panel = new JPanel();
            panel.add(jpane);
            JScrollPane scrollPanel = new JScrollPane(panel);
            scrollPanel.setBounds(600,10,490,455);
            frame1.add(scrollPanel);




        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }



        frame1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("src/assets/bg2.png"));
        background.setBounds(0,0,screenSize.width,screenSize.height);
        frame1.add(background);
        background.setLayout(new FlowLayout());


        frame1.setSize(screenSize.width,screenSize.height);
        frame1.setVisible(true);
    }

}

