import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerFrame extends JFrame implements ActionListener {

    ImageIcon icon;

    JButton startbtn;
    JButton pausebtn;
    JButton resetbtn;
    JButton continuebtn;
    JLabel timeLabel;
    int elapsedTime = 0;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime+1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000);
            seconds = (elapsedTime/1000);
            hours_string = String.format("%02d", hours);
            minutes_string = String.format("%02d", minutes);
            seconds_string = String.format("%02d", seconds);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });

    TimerFrame(){
        this.setDefaultCloseOperation(TimerFrame.EXIT_ON_CLOSE);
            this.setTitle("STOPWATCH");
        this.getContentPane().setBackground(Color.DARK_GRAY);

        icon = new ImageIcon("stopwatch.png");

        timeLabel = new JLabel();
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(80,80,240,100);
        timeLabel.setOpaque(true);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,40));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        startbtn = new JButton();
        startbtn.setBounds(220, 240, 100, 50);
        startbtn.addActionListener(this);
        startbtn.setBackground(Color.WHITE);
        startbtn.setText("START");
        startbtn.setFocusable(false);

        continuebtn = new JButton();
        continuebtn.setBounds(220, 240, 100, 50);
        continuebtn.addActionListener(this);
        continuebtn.setBackground(Color.WHITE);
        continuebtn.setText("CONTINUE");
        continuebtn.setFocusable(false);
        continuebtn.setVisible(false);

        pausebtn = new JButton();
        pausebtn.setBounds(80,240,100,50);
        pausebtn.addActionListener(this);
        pausebtn.setBackground(Color.WHITE);
        pausebtn.setText("PAUSE");
        pausebtn.setFocusable(false);

        resetbtn = new JButton();
        resetbtn.setBounds(80,240,100,50);
        resetbtn.addActionListener(this);
        resetbtn.setBackground(Color.WHITE);
        resetbtn.setText("RESET");
        resetbtn.setFocusable(false);
        resetbtn.setVisible(false);

        this.setSize(400,400);
        this.setLayout(null);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
        this.add(timeLabel);
        this.add(continuebtn);
        this.add(resetbtn);
        this.add(pausebtn);
        this.add(startbtn);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startbtn){
            if (!started){
                started = true;
                startcmd();
            }
        }
        if (e.getSource()==pausebtn){
            if (started){
                started = false;
                pausebtn.setVisible(false);
                resetbtn.setVisible(true);
                startbtn.setVisible(false);
                continuebtn.setVisible(true);
                pausecmd();
            }
        }
        if (e.getSource()==continuebtn){
            if (!started){
                started = true;
                startbtn.setVisible(true);
                continuebtn.setVisible(false);
                pausebtn.setVisible(true);
                resetbtn.setVisible(false);
                continuecmd();
            }
        }
        if (e.getSource()==resetbtn){
            if (!started){
                resetbtn.setVisible(false);
                pausebtn.setVisible(true);
                continuebtn.setVisible(false);
                startbtn.setVisible(true);
                resetcmd();
            }
        }
    }

    void startcmd(){
        timer.start();
    }

    void pausecmd(){
        timer.stop();
    }

    void resetcmd(){
        elapsedTime = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        hours_string = String.format("%02d", hours);
        minutes_string = String.format("%02d", minutes);
        seconds_string = String.format("%02d", seconds);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    }

    void continuecmd(){
        timer.start();
    }
}