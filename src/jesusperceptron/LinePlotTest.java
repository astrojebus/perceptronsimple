/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesusperceptron;

/**
 *
 * @author Jesus
 */
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class LinePlotTest extends JFrame {
    public LinePlotTest(double[][] patrones, int numpatrones,int numcoordenadas,double[][] entradas,double[] salidas, double[] salidasentrenamiento,double valorx,double valory) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        DataTable data2 = new DataTable(Double.class, Double.class);
        DataTable data3 = new DataTable(Double.class, Double.class);
        for (double x = -5.0; x <= 5.0; x+=0.25) {
      double y = ((valory/valorx)*x)+valory; // ecuacion de la recta
      data3.add(x, y); 
       //data3.add(valorx, Double.parseDouble("0"));  
       //data3.add(Double.parseDouble("0"),valory );   
       //double y = 2*valorx+1;
       
       
     
       // dibuja los puntos test
        
        for(int i=0;i<numpatrones;i++){
					
						//System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						//patron[i][j] = (double)(entrada.nextDouble());
                                            
                                             
                                             if (salidasentrenamiento[i]==1) {    
                                             data.add(patrones[i][0],patrones[i][1]);
                                              }
                                              
                                              if (salidasentrenamiento[i]==0) {    
                                             data2.add(patrones[i][0],patrones[i][1]);
                                              }
                                             
					
				}
        
        // dibuja los patrones originales
        
        for(int i=0;i<numpatrones;i++){
					
						//System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						//patron[i][j] = (double)(entrada.nextDouble());
                                              if (salidas[i]==1) {    
                                             data.add(entradas[i][0],entradas[i][1]);
                                              }
                                              
                                              if (salidas[i]==0) {    
                                             data2.add(entradas[i][0],entradas[i][1]);
                                              }
					
				}
        
        
        
        
        
            }
        
    
        
        
        
        XYPlot plot = new XYPlot(data,data2,data3);
    
        getContentPane().add(new InteractivePanel(plot));
         
     LineRenderer lines = new DefaultLineRenderer2D();
    plot.setLineRenderer(data3, lines);
        
        
        Color color3 = new Color(0.0f, 0.0f, 0.0f);     //color linea
       plot.getPointRenderer(data3).setColor(color3);
  plot.getLineRenderer(data3).setColor(color3);
  
  Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderer(data).setColor(color);
        
        Color color2 = new Color(0.0f, 1.0f, 1.0f);
        plot.getPointRenderer(data2).setColor(color2);
        
    }

}