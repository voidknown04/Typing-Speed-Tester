import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener, KeyListener
{
	String passage=""; 
	String typedPass=""; 
	String message=""; 
	 
	int typed=0; 
	int count=0;
	int WPM;
	
	double start; 
	double end; 
	double elapsed;
	double seconds;
	
	boolean running; 
	boolean ended; 
	
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	final int DELAY=100; 
	
	JButton button; 
	Timer timer;
	JLabel label; 
	
	public Frame()
	{
	this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    SCREEN_WIDTH=720;
    SCREEN_HEIGHT=400;
    this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    this.setVisible(true); 
    this.requestFocusInWindow(); 
    this.setLocationRelativeTo(null); 
    
    button=new JButton("START");
    button.setFont(new Font("Serif",Font.BOLD,30));
    button.setForeground(Color.GREEN);
    button.setVisible(true);
    button.addActionListener(this);
    button.setFocusable(false);
    
    label=new JLabel();
    label.setText("Hit START button to start Typing test");
    label.setFont(new Font("Serif",Font.BOLD,30));
    label.setVisible(true);
    label.setOpaque(true);
    label.setHorizontalAlignment(JLabel.CENTER); 
    label.setBackground(Color.lightGray);
    
    this.add(button, BorderLayout.SOUTH);
    this.add(label, BorderLayout.NORTH);
    this.getContentPane().setBackground(Color.WHITE);
    this.addKeyListener(this);
    this.setFocusable(true); 
    this.setResizable(false);
    this.setTitle("Typing Test");
    this.revalidate(); 
	}

	@Override
	public void paint(Graphics g)	
	{
		super.paint(g);
		draw(g); 
	}
	public void draw(Graphics g)
	{
		g.setFont(new Font("MV Boli", Font.BOLD, 25));
		
		if(running)
		{
			if(passage.length()>1) 
			{
				g.drawString(passage.substring(0,50), g.getFont().getSize(), g.getFont().getSize()*5);
				g.drawString(passage.substring(50,100), g.getFont().getSize(), g.getFont().getSize()*7);
				g.drawString(passage.substring(100,150), g.getFont().getSize(), g.getFont().getSize()*9);
				g.drawString(passage.substring(150,200), g.getFont().getSize(), g.getFont().getSize()*11);
			}
			g.setColor(Color.GREEN);			
			if(typedPass.length()>0)
			{ 
				if(typed<50) 
					g.drawString(typedPass.substring(0,typed), g.getFont().getSize(),g.getFont().getSize()*5); 
				else
					g.drawString(typedPass.substring(0,50), g.getFont().getSize(),g.getFont().getSize()*5); 
			}	
			if(typedPass.length()>50)
			{
				if(typed<100) 
					g.drawString(typedPass.substring(50,typed), g.getFont().getSize(),g.getFont().getSize()*7);
				else
					g.drawString(typedPass.substring(50,100), g.getFont().getSize(),g.getFont().getSize()*7);
			}
			if(typedPass.length()>100)
			{
				if(typed<150) 
					g.drawString(typedPass.substring(100,typed), g.getFont().getSize(),g.getFont().getSize()*9);
				else
					g.drawString(typedPass.substring(100,150), g.getFont().getSize(),g.getFont().getSize()*9);
			}
			if(typedPass.length()>150)
				g.drawString(typedPass.substring(150,typed), g.getFont().getSize(),g.getFont().getSize()*11);
			running=false; 
		}
		if(ended)
		{
			FontMetrics metrics=getFontMetrics(g.getFont());
			g.setColor(Color.BLUE);
			g.drawString("Typing Test Completed!", (SCREEN_WIDTH-metrics.stringWidth("Typing Test Completed!"))/2, g.getFont().getSize()*6);
			
			g.setColor(Color.BLACK);
			g.drawString("Typing Speed: "+WPM+" Words Per Minute", (SCREEN_WIDTH-metrics.stringWidth("Typing Speed: "+WPM+" Words Per Minute"))/2, g.getFont().getSize()*9);			
			timer.stop();
			ended=false; 
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		if(passage.length()>1)
		{
			if(count==0)
				start=LocalTime.now().toNanoOfDay();
			else if(count==200) 
			{
				end=LocalTime.now().toNanoOfDay();
				elapsed=end-start;
				seconds=elapsed/1000000000.0; 
				WPM=(int)(((200.0/5)/seconds)*60); 
				ended=true;
				running=false;
				count++;
			}
			char[] pass=passage.toCharArray();
			if(typed<200)
			{
				running=true;
				if(e.getKeyChar()==pass[typed]) 
				{
					typedPass=typedPass+pass[typed];
					typed++;
					count++; 
				} 
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}

	@Override 
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource()==button)
		{
			passage=getPassage();
			timer=new Timer(DELAY,this);
			timer.start();
			running=true;
			ended=false;
			
			typedPass="";
			message="";
			
			typed=0;
			count=0;
		}
		if(running)
			repaint();
		if(ended)
			repaint();
	}
	public static String getPassage()
	{
		ArrayList<String> Passages=new ArrayList<String>();
		String pas1="Many touch typists also use keyboard shortcuts or hotkeys when typing on a computer. This allows them to edit their document without having to take their hands off the keyboard to use a mouse. An example of a keyboard shortcut is pressing the Ctrl key plus the S key to save a";
		String pas2="A virtual assistant (typically abbreviated to VA) is generally self-employed and provides professional administrative, technical, or creative assistance to clients remotely from a home office. Many touch typists also use keyboard shortcuts or hotkeys when typing on a computer";
		String pas3="Frank Edward McGurrin, a court stenographer from Salt Lake City, Utah who taught typing classes, reportedly invented touch typing in 1888. On a standard keyboard for English speakers the home row keys are: \"ASDF\" for the left hand and \"JKL;\" for the right hand. The keyboar";
		String pas4="Income before securities transactions was up 10.8 percent from $13.49 million in 1982 to $14.95 million in 1983. Earnings per share (adjusted for a 10.5 percent stock dividend distributed on August 26) advanced 10 percent to $2.39 in 1983 from $2.17 in 1982. Earnings may rise ";
		String pas5="Historically, the fundamental role of pharmacists as a healthcare practitioner was to check and distribute drugs to doctors for medication that had been prescribed to patients. In more modern times, pharmacists advise patients and health care providers on the selection, dosage";
		String pas6="Proofreader applicants are tested primarily on their spelling, speed, and skill in finding errors in the sample text. Toward that end, they may be given a list of ten or twenty classically difficult words and a proofreading test, both tightly timed. The proofreading test will o";
		String pas7="In one study of average computer users, the average rate for transcription was 33 words per minute, and 19 words per minute for composition. In the same study, when the group was divided into \"fast\", \"moderate\" and \"slow\" groups, the average speeds were 40 wpm, 35 wpm, an";
		String pas8="A teacher's professional duties may extend beyond formal teaching. Outside of the classroom teachers may accompany students on field trips, supervise study halls, help with the organization of school functions, and serve as supervisors for extracurricular activities. In some e";
		String pas9="Web designers are expected to have an awareness of usability and if their role involves creating mark up then they are also expected to be up to date with web accessibility guidelines. The different areas of web design include web graphic design; interface design; authoring, i";
		String pas10="A data entry clerk is a member of staff employed to enter or update data into a computer system. Data is often entered into a computer from paper documents using a keyboard. The keyboards used can often have special keys and multiple colors to help in the task and speed up th";
		
		Passages.add(pas1);
		Passages.add(pas2);
		Passages.add(pas3);
		Passages.add(pas4);
		Passages.add(pas5);
		Passages.add(pas6);
		Passages.add(pas7);
		Passages.add(pas8);
		Passages.add(pas9); 
		Passages.add(pas10);
		
		Random rand=new Random();
		int place=(rand.nextInt(10));
		
		String toReturn=Passages.get(place).substring(0,200); 
		if(toReturn.charAt(199)==32) 
		{
			toReturn=toReturn.strip(); 
			toReturn=toReturn+".";
		}
		return(toReturn); 
	}
}
