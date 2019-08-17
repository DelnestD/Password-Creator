import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Fenetre  extends JFrame implements ActionListener{
	private JPanel pan;
	private JLabel lab,lab2,lab3;
	private JTextField size,in,out;
	private JButton validation;
	private JCheckBox number,letter,maj,special;
	private String alphabet[]= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	String pwd="";
	int sizeuser=10;
	String inuser="";
	public Fenetre() {
		this.setTitle("Password Generator");
		this.setLocationRelativeTo(null);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pan = new JPanel();
		lab = new JLabel();
		lab2 = new JLabel();
		lab3 = new JLabel();
		size = new JTextField(Integer.toString(sizeuser),5);
		in = new JTextField("",25);
		out = new JTextField("",25);
		validation = new JButton("Valider");
		number = new JCheckBox("Chiffre");
		letter= new JCheckBox("Lettre");
		maj= new JCheckBox("Majuscule");
		special= new JCheckBox("Caractères spéciaux");
		
		lab.setText("Taille (10-20) :");
		lab.setVisible(true);
		lab2.setText("Caractères souhaités :");
		lab2.setVisible(true);
		lab3.setText("Votre mot de passe :");
		lab3.setVisible(true);
		validation.setVisible(true);
		number.setVisible(true);
		letter.setVisible(true);
		maj.setVisible(false);
		special.setVisible(false);
		letter.addActionListener(this);
		validation.addActionListener(this);
		
		pan.add(number);
		pan.add(letter);
		pan.add(maj);
		pan.add(special);
		pan.add(lab);
		pan.add(size);
		pan.add(lab2);
		pan.add(in);
		pan.add(lab3);
		pan.add(out);
		pan.add(validation);
		
		this.setContentPane(pan);
		this.setVisible(true);
	}
	private void generate() {
		int lim =0;
		for(int i=0;i<sizeuser;i++) {
			int rand = 0;
			String conv="";
			if(number.isSelected()&&!letter.isSelected()) {
				rand=1;
					}else if(!number.isSelected()&&letter.isSelected()&&!special.isSelected()){
						rand=2;
							}else if(!number.isSelected()&&!letter.isSelected()){
								pwd="Erreur";
									}else if(number.isSelected()&&letter.isSelected()&&special.isSelected()){
										if(lim==0) {
											rand= (int)(Math.random()*3)+1;
										}else {
											rand=(int)(Math.random()*2)+1;;
										}
											}else if(!number.isSelected()&&letter.isSelected()&&special.isSelected()){
												if(lim==0) {
													rand= (int)(Math.random()*2)+2;
												}else {
													rand=2;
												}
													}else {
														rand= (int)(Math.random()*2)+1;
			}
			
			switch(rand) {
			case 1:
				int numb = (int)(Math.random()*9)+1;
				conv = Integer.toString(numb);
				
			break;
			case 2:
				int letterNumber = (int)(Math.random()*26);
				int letterMaj = (int)(Math.random()*2);
				conv = alphabet[letterNumber];
				if(maj.isSelected() && letterMaj==1) {
					conv = conv.toUpperCase();
				}
			break;
			case 3:
				conv = "@";
				lim =1;
			break;
			default:
				pwd = "Erreur";
			break;
			}
			pwd += conv;
		}
	}
	private void change() {
		int lim =0;
		int where = (int)(Math.random()*(sizeuser-inuser.length()));
		for(int i=0;i<sizeuser;i++) {
			int rand = 0;
			String conv="";
			if(i==where){
				rand=4;
				i+=(inuser.length()-1);
			}else{
				if(number.isSelected()&&!letter.isSelected()) {
					rand=1;
				}else if(!number.isSelected()&&letter.isSelected()&&!special.isSelected()){
					rand=2;
				}else if(!number.isSelected()&&!letter.isSelected()){
					pwd="Erreur";
				}else if(number.isSelected()&&letter.isSelected()&&special.isSelected()){
					if(lim==0) {
						rand= (int)(Math.random()*3)+1;
					}else {
						rand=(int)(Math.random()*2)+1;;
					}
				}else if(!number.isSelected()&&letter.isSelected()&&special.isSelected()){
					if(lim==0) {
						rand= (int)(Math.random()*2)+2;
					}else {
						rand=2;
					}
				}else {
					rand= (int)(Math.random()*2)+1;
				}
			}
			
			switch(rand) {
			case 1:
				int numb = (int)(Math.random()*9)+1;
				conv = Integer.toString(numb);
				
			break;
			case 2:
				int letterNumber = (int)(Math.random()*26);
				int letterMaj = (int)(Math.random()*2);
				conv = alphabet[letterNumber];
				if(maj.isSelected() && letterMaj==1) {
					conv = conv.toUpperCase();
				}
			break;
			case 3:
				conv = "@";
				lim =1;
			break;
			case 4:
				conv=inuser;
			break;
			default:
				pwd = "Erreur";
			break;
			}
			pwd += conv;
		}
	}
	public void actionPerformed(ActionEvent e) {
		
		if(letter.isSelected()) {
			maj.setVisible(true);
			special.setVisible(true);
		}else {
			maj.setVisible(false);
			maj.setSelected(false);
			special.setVisible(false);
			special.setSelected(false);
		}
		inuser=in.getText();
		if(e.getSource() == validation) {
			if(Integer.parseInt(size.getText())<10) {
				sizeuser=10;
				size.setText("10");
			}else if(Integer.parseInt(size.getText())>20) {
				sizeuser=20;
				size.setText("20");
			}else {
				sizeuser=Integer.parseInt(size.getText());
			}
			if(inuser.length()>20) {
				inuser=inuser.substring(0,20);
				in.setText(inuser);
				}
			pwd="";
			if(inuser.length()>0) {
				change();
			}else {
				generate();
			}
			out.setText(pwd);
			
		}
	}
}
