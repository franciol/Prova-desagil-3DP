package br.pro.hashi.ensino.desagil.lucianogic.model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;



// Esta classe representa a interface de uma calculadora de densidade, com
// os dois campos de entrada (peso e raio) e o campo de saida (resultado).
public class GateView<resultField> extends FixedPanel implements ActionListener, ItemListener, MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	// A componente JTextField representa um campo para digitacao de texto.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
	
	private JCheckBox resultField;
	private JCheckBox resultField2;
	

	private Gate gate;

	private JCheckBox in1;
	private JCheckBox in2;
	private JCheckBox in3;
	private Switch a1;
	private Switch a2;
	private Switch a3;
	private Color color;
	private Color color2;
	private LED led;
	private LED led2;

	public GateView(Gate gate) {
		super(270, 150);
		Color color = new Color(0, 0, 0);
		Color color2 = new Color(0, 0, 0);
		
		this.gate = gate;
		image = loadImage(gate.getName());
		JLabel label1 = new JLabel("In1:");
		JLabel label2 = new JLabel("in2:");
		JLabel label3 = new JLabel("select:");
		JLabel resultLabel = new JLabel("Result: ");
		addMouseListener(this);
		
		in1 = new JCheckBox();
		in2 = new JCheckBox();
		in3 = new JCheckBox();
		a1 = new Switch();
		a2 = new Switch();
		a3 = new Switch();
		resultField = new JCheckBox();
		resultField2 = new JCheckBox();

		in1.addItemListener(this);
		in2.addItemListener(this);
		in3.addItemListener(this);
	

		a1.setOn(false);
		a2.setOn(false);
		a3.setOn(false);
		
		
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		led = new LED(r, g, b);
		led2 = new LED(r, g, b);
		
		
		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html		
		if(gate.getInSize() == 1) {
			add(label1,25, 40, 25,25);
			add(in1,  25,  60, 25, 25);
			gate.connect(a1, 0);

			
		}
		
		else if(gate.getInSize() == 2) {
			add(label1, 30, 25, 25, 20);
			add(label2, 30, 65, 25, 20);
			add(in1,  30,  40, 25, 20);
			add(in2,  30,  80, 25, 20);
			gate.connect(a1, 0);
			gate.connect(a2, 1);

			
		}
		
		else{
			add(label1, 30, 25, 25, 20);
			add(label2, 30, 65, 25, 20);
			add(label3, 10, 100, 50, 20);
			add(in1,  30,  40, 25, 20);
			add(in2,  30,  80, 25, 20);
			add(in3,  30,  115, 25, 20);
			gate.connect(a1, 0);
			gate.connect(a2, 1);
			gate.connect(a3, 2);

		}
		
		
		// Esta linha garante que, sempre que o usuario digitar algo
		// em weightField, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?
		

		// Esta linha garante que, sempre que o usuario digitar algo
		// em radiusField, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?

		// Esta linha garante que resultField nao seja digitavel.
		}


	// Necessario para carregar os arquivos de imagem.
//	private Image loadImage(String filename) {
	//	URL url = getClass().getResource("/img/" + filename + ".png");
//		ImageIcon icon = new ImageIcon(url);
	//	return icon.getImage();
	//}


	private Image loadImage(String name) {
		URL url = getClass().getResource("/img/" + name + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 60, 28, 130, 130, null);
		
		if (gate.getOutSize() == 1) {
			
			if(gate.doRead(0)){
				color = new Color(led.getR(), led.getG(), led.getB());
				g.setColor(color);
				g.fillOval(195, 55, 30, 50);
				g.fillRect(192, 90, 38, 15);
				
			}
			else {
				g.setColor(Color.GRAY);
				g.fillOval(195, 55, 30, 50);
				g.fillRect(192, 90, 38, 15);
			}
		}
		else {
			if(gate.doRead(0)){
				color = new Color(led.getR(), led.getG(), led.getB());
				g.setColor(color);
				g.fillOval(195, 55, 30, 50);
				g.fillRect(192, 90, 38, 15);
				
			}
			else {
				g.setColor(Color.GRAY);
				g.fillOval(195, 55, 30, 50);
				g.fillRect(192, 90, 38, 15);
			}
			if(gate.doRead(1)) {
				color2 = new Color(led2.getR(), led2.getG(), led2.getB());
				g.setColor(color2);
				g.fillOval(195, 105, 30, 50);
				g.fillRect(192, 140, 38, 15);
				
			}
			else {
				g.setColor(Color.GRAY);
				g.fillOval(195, 105, 30, 50);
				g.fillRect(192, 140, 38, 15);
			}

		}
		repaint();

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
	
	public void mouseClicked(MouseEvent e) {
	   int x = e.getX();
	   int y = e.getY();
			if (gate.getOutSize()==1){
				if (x >= 195 && x <= 225 && y >= 55 && y <= 90){
					color = JColorChooser.showDialog(this, null, null);
		
					int r = color.getRed();
					int g = color.getGreen();
					int b = color.getBlue();
					led = new LED(r, g, b);
					led.connect(gate, 0);
					repaint();
					getToolkit().sync();
		
				}
			}
				
			
				else{
					if (x >= 195 && x <= 225 && y >= 65 && y <= 100){
						color = JColorChooser.showDialog(this, null, null);
		
						int r = color.getRed();
						int g = color.getGreen();
						int b = color.getBlue();
						led = new LED(r, g, b);
						led.connect(gate, 0);
						
					}
					if (x >= 195 && x <= 225 && y >= 115 && y <= 150){
						color2 = JColorChooser.showDialog(this, null, null);
		
						int r = color2.getRed();
						int g = color2.getGreen();
						int b = color2.getBlue();
						led2 = new LED(r, g, b);
						led.connect(gate, 0);
					}
					repaint();
					getToolkit().sync();
				
			}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent arg0) {
		try {
			if (gate.getInSize() == 1) {
				if (in1.isSelected()) {
					a1.setOn(true);
				}
				if(!in1.isSelected()){
					a1.setOn(false);
				}
				gate.connect(a1, 0);
			}
			else if (gate.getInSize() == 2) {
				if (in1.isSelected()) {
					a1.setOn(true);
				}
				if(!in1.isSelected()){
					a1.setOn(false);
				}
				if (in2.isSelected()) {
					a2.setOn(true);
				}
				if (!in2.isSelected()) {
					a2.setOn(false);
				}
				gate.connect(a1, 0);
				gate.connect(a2, 1);	
			}
			else if (gate.getInSize() == 3) {
				if (in1.isSelected()) {
					a1.setOn(true);
				}
				if(!in1.isSelected()){
					a1.setOn(false);
				}
				if (in2.isSelected()) {
					a2.setOn(true);
				}
				if (!in2.isSelected()) {
					a2.setOn(false);
				}
				if (in3.isSelected()) {
					a3.setOn(true);
				}
				if (!in3.isSelected()) {
					a3.setOn(false);
				}
				gate.connect(a1, 0);
				gate.connect(a2, 1);
				gate.connect(a3, 2);
			}
			
			
		}
		
		finally {
			led.connect(gate, 0);
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
