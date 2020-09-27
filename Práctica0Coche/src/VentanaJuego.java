import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;  
	JPanel ventana;         
	CocheJuego coche;       
	MiRunnable hilo = null;  


	public VentanaJuego() {
		
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

		ventana = new JPanel();
		JPanel botones = new JPanel();
		JButton Acelerar = new JButton( "Acelerar" );
		JButton Frenar = new JButton( "Frenar" );
		JButton GiraIzquierda = new JButton( "Gira izquierda" );
		JButton GiraDerecha = new JButton( "Gira dererecha" );

		ventana.setLayout( null );
		ventana.setBackground( Color.white );
	
		add( ventana, BorderLayout.CENTER );
		botones.add( Acelerar );
		botones.add( Frenar );
		botones.add( GiraIzquierda );
		botones.add( GiraDerecha );
		add( botones, BorderLayout.SOUTH );

		setSize( 700, 500 );
	
		Acelerar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (coche.getVelocidad()==0)
					coche.acelera( +5 );
				else 
					coche.acelera( +5 );
					
				System.out.println( "Nueva velocidad de coche: " +coche.getVelocidad() );
			}
		});
		Frenar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.acelera( -5 );
				System.out.println( "Nueva velocidad de coche: " +coche.getVelocidad() );
			}
		});
		GiraIzquierda.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.gira( +10 );
				System.out.println( "Nueva dirección de coche: " +coche.getDireccionActual() );
			}
		});
		GiraDerecha.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				coche.gira( -10 );
				System.out.println( "Nueva dirección de coche: " +coche.getDireccionActual() );
			}
		});
		
		ventana.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						coche.acelera( +5 );
						break;
					}
					case KeyEvent.VK_DOWN: {
						coche.acelera( -5 );
						break;
					}
					case KeyEvent.VK_LEFT: {
						coche.gira( +10 );
						break;
					}
					case KeyEvent.VK_RIGHT: {
						coche.gira( -10 );
						break;
					}
				}
			}
		});
		ventana.setFocusable(true);
		ventana.requestFocus();
		ventana.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ventana.requestFocus();
			}
		});
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (hilo!=null) hilo.acaba();
			}
		});
	}
	
	public void creaCoche( int posX, int posY ) {
	
		coche = new CocheJuego();
		coche.setPosicion( posX, posY );
		ventana.add( coche.getGrafico() );
	}
	

	public static void main(String[] args) {
		
		VentanaJuego miVentana = new VentanaJuego();
		miVentana.creaCoche( 150, 100 );
		miVentana.setVisible( true );
		miVentana.coche.setPiloto( "Alex Anton" );
		miVentana.hilo = miVentana.new MiRunnable();  
		Thread nuevoHilo = new Thread( miVentana.hilo );
		nuevoHilo.start();
	}
	
	
	class MiRunnable implements Runnable {
		boolean seguir = true;
		@Override
		public void run() {
		
			while (seguir) {
				
				coche.mueve( 0.040 );
			
				if (coche.getPosX() < -JLabelCoche.TAMANYO_COCHE/2 || coche.getPosX()>ventana.getWidth()-JLabelCoche.TAMANYO_COCHE/2 ) {
					
					System.out.println( "rebotax");
					double dir = coche.getDireccionActual();
					dir = 180-dir;   
					if (dir < 0) dir = 360+dir; 
					coche.setDireccionActual( dir );
				}
				
				if (coche.getPosY() < -JLabelCoche.TAMANYO_COCHE/2 || coche.getPosY()>ventana.getHeight()-JLabelCoche.TAMANYO_COCHE/2 ) {

					System.out.println( "rebotay");
					double dir = coche.getDireccionActual();
					dir = 360 - dir;  
					coche.setDireccionActual( dir );
				}
				
				try {
					Thread.sleep( 40 );
				} catch (Exception e) {
				}
			}
		}
	
		public void acaba() {
			seguir = false;
		}
	};
	
}