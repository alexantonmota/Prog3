
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class JLabelCoche extends JLabel {
	private static final long serialVersionUID = 1L; 
	public static final int TAMANYO_COCHE = 220;  
	

public JLabelCoche() {
	
		try {
			setIcon( new ImageIcon( JLabelCoche.class.getResource( "coche.png" ).toURI().toURL() ) );
		} catch (Exception e) {
			System.err.println( "Error en carga de recurso: coche.png no encontrado" );
			e.printStackTrace();
		}
		setBounds( 0, 0, TAMANYO_COCHE, TAMANYO_COCHE );
		
	}
	
	
	private double miGiro = Math.PI/2;
	
	public void setGiro( double gradosGiro ) {
	
		miGiro = gradosGiro/180*Math.PI;
		miGiro = -miGiro;  
		miGiro = miGiro + Math.PI/2; 
	}

}