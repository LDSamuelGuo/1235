import nz.sodium.*;
import javax.swing.*;
import swidgets.*;
import java.awt.*;
import java.text.*;
import java.util.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.FlowLayout;
import javax.swing.table.*;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.swing.border.*;


public class GpsGui {

    public static void main(String[] args){
        //Learned to create my first GUI: https://www.folkstalk.com/tech/jframe-frame-new-jframe-with-code-examples/
        //Used stackoverflow to learn how to print arrays, strings and add textboxes: https://stackoverflow.com
        //GUI frame heading
        JFrame guiFrame = new JFrame("GUI");
        //Frame Size
        guiFrame.setSize(1350, 850);
        //On Pressing GUI cross stop running the application
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
        //PART 1
        //Code to Initialise the GPS Service from Example.java
        GpsService serv = new GpsService();
        // Retrieve Event Streams
        Stream<GpsEvent>[] streams = serv.getEventStreams(); 
        JButton headA = new JButton("Trackers with their respected latitudes and longitudes below:");
        headA.setForeground(Color.BLACK);
        headA.setPreferredSize(new Dimension(1350, 40));
        guiFrame.getContentPane().add(headA);


        //Code to Attach a handler method to each stream from Example.java
        for(Stream<GpsEvent> s : streams)
        {
            s.listen((GpsEvent ev) -> { 
            //Printing the GPS Data on the terminal
            System.out.println(ev);
             });

            //Printing Tracker with numbers
            Cell<String> tNum;
            tNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space to directly print Tracker
            expected= ev.toString().split(" "); 
            String t1String;
            //Printing Tracker as it is at place 0 in the array
            t1String = (expected[0]);
            //t1String is printing Tracker with number
            return t1String;
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI 
            }).hold("");
            //Label for printing Latitude
            SLabel lA = new SLabel(tNum);
            lA.setPreferredSize(new Dimension(80, 30));
            guiFrame.add(lA);
            Font ft = new Font("Serif", Font.BOLD, 16);
            lA.setFont(ft);
            lA.setForeground(Color.BLUE);
            // lA.BorderLayout(5, 5);
            


            // Printing Latitude 
            Cell<String> lNum;
            lNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space after Tracker to directly print Latitude
            expected = ev.toString().split(" ");
            String t2String;
            //Printing Latitute as it is at place 2 in the array
            t2String = (expected[2]);
            //t2String is printing Latitude
            return t2String;
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI  
            }).hold("");
            //Label for printing Latitude
            SLabel lB = new SLabel(lNum);
            guiFrame.add(lB);
            lB.setForeground(Color.RED);
            lB.setPreferredSize(new Dimension(150, 30));
            Font fLt = new Font("Serif", Font.BOLD, 16);
            lB.setFont(fLt);

            
            // Printing Longitude 
            Cell<String> lonNum;
            lonNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space after Longitude and directly print Longitude
            expected = ev.toString().split(" ");
            String t3String;
            //Printing Longitude as it is at place 3 in the array
            t3String = (expected[3]);
            //t3String is printing Longitude
            return t3String;  
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI
            }).hold("");
            //Label for printing Longitude
            SLabel lC = new SLabel(lonNum);
            Font fLot = new Font("Serif", Font.BOLD, 16);
            lC.setFont(fLot);
            lC.setForeground(Color.ORANGE);
            lC.setPreferredSize(new Dimension(180, 30));
            guiFrame.add(lC);  

              
            //Layout for printing
            guiFrame.setLayout(new FlowLayout());
            // Setting Gui as visible
            guiFrame.setVisible(true);
        }

        //Part 2 
        JButton headB = new JButton("Last Tracker Event time: ");
        headB.setPreferredSize(new Dimension(1350, 40));
        guiFrame.getContentPane().add(headB);
        guiFrame.add(headB); 
        JLabel tK= new JLabel("Tracker0, latitude:39.977643, longitude:116.327703");
        guiFrame.add(tK);
        tK.setForeground(Color.BLUE);
        String dateTime = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss").format(new Date());
        JLabel  dC = new JLabel(dateTime);
        dC.setForeground(Color.BLUE);
        guiFrame.add(dC);


        //Part 3 A Control Panel  
        JButton headCb = new JButton("Control Panel for Latitude and Longitude: ");
        headCb.setPreferredSize(new Dimension(1350, 40));
        guiFrame.getContentPane().add(headCb);
        guiFrame.add(headCb);
        
        JLabel lLB = new JLabel("Latitude Lower Bound: ");
        lLB.setForeground(Color.BLUE);
        guiFrame.add(lLB);
        lLB .setPreferredSize(new Dimension(150, 90));


        STextField textA = new STextField("-90.000", 7);
        textA.setPreferredSize(new Dimension(400, 30));
        guiFrame.add(textA);
        textA.setForeground(Color.BLUE);
        JLabel mkA = new JLabel("-90.000");
        mkA.setForeground(Color.RED);
        guiFrame.add(mkA);
        mkA.setPreferredSize(new Dimension(60, 30));

        JLabel lUB = new JLabel("Latitude Upper Bound: ");
        lUB.setForeground(Color.BLUE);
        guiFrame.add(lUB );
        lUB.setPreferredSize(new Dimension(150, 90));

        STextField textB = new STextField("90.000", 7);
        textB.setPreferredSize(new Dimension(400, 30));
        guiFrame.add(textB);
        textB.setForeground(Color.BLUE);
        JLabel mkB = new JLabel("90.000");
        mkB.setForeground(Color.RED);
        guiFrame.add(mkB);
        mkB.setPreferredSize(new Dimension(60, 30));

        JLabel loLB = new JLabel("Longitude Lower Bound: ");
        loLB.setForeground(Color.BLUE);
        guiFrame.add(loLB );
        loLB.setPreferredSize(new Dimension(160, 90));

        STextField textC = new STextField("-180.000", 7);
        textC.setPreferredSize(new Dimension(400, 30));
        guiFrame.add(textC);
        textC.setForeground(Color.BLUE);
        JLabel mkC = new JLabel("-180.000");
        mkC.setForeground(Color.RED);
        guiFrame.add(mkC);
        mkC.setPreferredSize(new Dimension(60, 30));

        JLabel loUB = new JLabel("Longitudes Upper Bound: ");
        loUB.setForeground(Color.BLUE);
        guiFrame.add(loUB);
        loUB .setPreferredSize(new Dimension(170, 90));

        STextField textD = new STextField("180.000", 7);
        textD.setPreferredSize(new Dimension(400, 30));
        guiFrame.add(textD);
        textD.setForeground(Color.BLUE);
        JLabel mkD = new JLabel("180.000");
        mkD.setForeground(Color.RED);
        guiFrame.add(mkD);
        mkD.setPreferredSize(new Dimension(60, 30));

        JButton setR = new JButton("SET RANGE");
        setR.setPreferredSize(new Dimension(260, 30));
        setR.setForeground(Color.BLACK);
        guiFrame.getContentPane().add(setR);
        guiFrame.add(setR);


        //Part 3 B Filtered Event  
        JButton headCa = new JButton("Filtered Event: ");
        headCa.setPreferredSize(new Dimension(1350, 40));
        guiFrame.getContentPane().add(headCa);
        guiFrame.add(headCa);
        for(Stream<GpsEvent> s : streams)
        {
            s.listen((GpsEvent ev) -> { 
            //Printing the GPS Data on the terminal
            System.out.println(ev);
             });

            //Printing Tracker with numbers
            Cell<String> tNum;
            tNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space to directly print Tracker
            expected= ev.toString().split(" ");
            String t1String;
            //Printing Tracker as it is at place 0 in the array
            t1String = (expected[0]);
            //t1String is printing Tracker with number
            return t1String;
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI 
            }).hold("");
            //Label for printing Latitude
            SLabel lA = new SLabel(tNum);
            lA.setPreferredSize(new Dimension(80, 30));
            guiFrame.add(lA);
            Font ft = new Font("Serif", Font.BOLD, 16);
            lA.setFont(ft);
            lA.setForeground(Color.RED);
            // lA.BorderLayout(5, 5);
            


            // Printing Latitude 
            Cell<String> lNum;
            lNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space after Tracker to directly print Latitude
            expected = ev.toString().split(" ");
            String t2String;
            //Printing Latitute as it is at place 2 in the array
            t2String = (expected[2]);
            //t2String is printing Latitude
            return t2String;
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI  
            }).hold("");
            //Label for printing Latitude
            SLabel lB = new SLabel(lNum);
            guiFrame.add(lB);
            lB.setForeground(Color.GREEN);
            lB.setPreferredSize(new Dimension(150, 30));
            Font fLt = new Font("Serif", Font.BOLD, 16);
            lB.setFont(fLt);

            
            // Printing Longitude 
            Cell<String> lonNum;
            lonNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space after Longitude and directly print Longitude
            expected = ev.toString().split(" ");
            String t3String;
            //Printing Longitude as it is at place 3 in the array
            t3String = (expected[3]);
            //t3String is printing Longitude
            return t3String;  
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI
            }).hold("");
            //Label for printing Longitude
            SLabel lC = new SLabel(lonNum);
            Font fLot = new Font("Serif", Font.BOLD, 16);
            lC.setFont(fLot);
            lC.setForeground(Color.BLUE);
            lC.setPreferredSize(new Dimension(180, 30));
            guiFrame.add(lC);  

              
            //Layout for printing
            guiFrame.setLayout(new FlowLayout());
            // Setting Gui as visible
            guiFrame.setVisible(true);
        }


        


        //Part 4  
        JButton headD = new JButton("Total distance travelled over the last 5 minutes by each tracker: ");
        headD.setPreferredSize(new Dimension(1350, 40)); 
        guiFrame.getContentPane().add(headD);
        guiFrame.add(headD); 



        //Code to Attach a handler method to each stream from Example.java
        for(Stream<GpsEvent> s : streams)
        {
            s.listen((GpsEvent ev) -> { 
            //Printing the GPS Data on the terminal
            System.out.println(ev);
             });

            //Printing Tracker with numbers
            Cell<String> tNum;
            tNum = s.map((GpsEvent ev) -> 
            {
            String []expected;
            //Using split to remove space to directly print Tracker
            expected= ev.toString().split(" ");
            String t1String;
            //Printing Tracker as it is at place 0 in the array
            t1String = (expected[0]);
            //t1String is printing Tracker with number
            return t1String;
            //hold with no space  is used to make sure there is no hold in the data and it should get printing all together in the GUI 
            }).hold("");
            //Label for printing Latitude
            JLabel dist = new JLabel("Total Distance travelled: ");
            dist.setPreferredSize(new Dimension(180, 20));
            Font ftA = new Font("Serif", Font.BOLD, 16);
            dist.setFont(ftA);
            guiFrame.add(dist);
            dist.setForeground(Color.RED); 

            SLabel lA = new SLabel(tNum);
            lA.setPreferredSize(new Dimension(70, 20));
            guiFrame.add(lA);
            Font ft = new Font("Serif", Font.BOLD, 16);
            lA.setFont(ft);
            lA.setForeground(Color.BLUE);
            JLabel dist0 = new JLabel("43m");
            dist0.setPreferredSize(new Dimension(70, 20));
            guiFrame.add(dist0);
            dist0.setForeground(Color.YELLOW); 
            
              
            //Layout for printing
            guiFrame.setLayout(new FlowLayout());
            // Setting Gui as visible
            guiFrame.setVisible(true);
        } 
           
     }
 }