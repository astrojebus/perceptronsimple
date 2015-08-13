/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesusperceptron;

/******************************************************************************
 * Proyecto: Red Neuronal Artificial Perceptron 
 ******************************************************************************/


import java.util.Scanner;

public class Entrenamiento{
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int opcion=-1, num_neu, num_patrones, num_valores, funcion, epocas, errores;
		double  clase, salidas[],salidasentrenamiento[],  patron[][], entradas[][];
		String tipo_red, tipo_entrada;
		Perceptron neurona;

		do{
			System.out.printf("\n\n\tRed Neuronal Artificial Perceptron...\n\n");
			System.out.println("0) Salir de la aplicacion.");
			System.out.println("1) Perceptron Simple.");
			System.out.printf("\n\tElija su opción: ");
			opcion = entrada.nextInt();

			if(opcion == 1){
				System.out.printf("\nNumero de puntos de entrenamiento: ");
				num_patrones = entrada.nextInt();

				System.out.printf("\nNumero de caracteristicas/dimensiones por patron: ");
				num_valores = entrada.nextInt();

				salidas = new double[num_patrones];
                                
				entradas = new double[num_patrones][num_valores];

				System.out.printf("\nIngresar datos para el entrenamiento.");	
				for(int i=0;i<num_patrones;i++){
					for(int j=0;j<num_valores;j++){
						System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						entradas[i][j] = (double)(entrada.nextDouble());
					}
					System.out.printf("\n Salida asociada-Patron[%d]: ", i);
                                        salidas[i] = (double)(entrada.nextDouble());
                                  
                                        if ((salidas[i]!=1)&&(salidas[i]!=0)){
                                        do{
                                           
                                            System.out.printf("\n Valor incorrecto. Ingrese de nuevo la Salida asociada-Patron[%d]: ", i);
                                        
					    salidas[i] = (double)(entrada.nextDouble());
                                        }while ((salidas[i]!=1)&&(salidas[i]!=0)); 
                                        }
                                        
				}

				

				neurona = new Perceptron(num_patrones, num_valores);
                                
                                System.out.println("\n PESOS:");
                                System.out.println("\n¿DESEA PESOS AUTOMÁTICOS O MANUALES?");
                                System.out.printf("\n\tElija su opción: \n");
                                System.out.println("0) Automáticos");
                                System.out.println("1) Manuales");
                        
			int opcionpesos = entrada.nextInt();
                        
                        if(opcionpesos == 0){
                        System.out.println("\nLos pesos están automáticos");
                        }
                        
                        if(opcionpesos == 1){
                        System.out.println("\nPESOS MANUALES: Escriba los pesos");
                                               
                        neurona.EstablecerPesosManuales(num_valores);
                        }
                        
                        
                        
                        
                        
                           System.out.println("\n BIAS:");
                                System.out.println("\n¿DESEA BIAS AUTOMÁTICOS O MANUALES?");
                                System.out.printf("\n\tElija su opción: \n");
                                System.out.println("0) Automáticos");
                                System.out.println("1) Manuales");
                                
                           int opcionbias = entrada.nextInt();
                        
                            if(opcionbias == 0){
                            System.out.println("\nBias generado automaticamente");
                            }
                        
                            if(opcionpesos == 1){
                             System.out.println("\nBIAS MANUALES: Ingrese el bias");
                              double bias1 =  entrada.nextDouble();           
                            neurona.establecerBias(bias1);
                            }   

                            
                            
                            
                            
                            
                            
				epocas = 0;
				do{
					errores = 0;
					System.out.printf("\n<DATOS>...\n");
					for(int j=0; j<num_patrones; j++){
						neurona.calcularSalida(j, entradas);
						neurona.establecerError(salidas[j] - neurona.obtenerSalidaCalculada());
						System.out.printf("\nNeurona.");
						System.out.printf("\n\tIteración actual: %d", epocas);
						System.out.printf("\n\tError de la red: %f", neurona.obtenerError());
						System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
					
						System.out.printf("\n\tPesos: ");
						neurona.imprimePesos();
						System.out.println();
						neurona.actualizarPesos(j, entradas);
						neurona.actualizarBias();
						if(neurona.obtenerError() != 0.0)
							errores++;
					}
					epocas++;
				}while(errores != 0);

				System.out.printf("\n<DATOS FINALES>...\n");
				System.out.printf("\n\tEpocas de entrenamiento: %d", epocas);
				System.out.printf("\nNeurona.");
				System.out.printf("\n\tBias de la neurona: %f", neurona.obtenerBias());
			
				System.out.printf("\n\tPesos: ");
				neurona.imprimePesos();
				System.out.println();
			
                                System.out.printf("\nIngresar patrones para clasificar.");
				System.out.printf("\nNumero de patrones: ");
				num_patrones = entrada.nextInt();
                                salidasentrenamiento = new double[num_patrones]; // inicializo la dimension del vectro de salidas de acuerdo a los puntos que quiero ingresar
				patron = new double[num_patrones][num_valores];
				for(int i=0;i<num_patrones;i++){
					for(int j=0;j<num_valores;j++){
						System.out.printf("\nPatron[%d]-valor[%d]: ", i ,j);
						patron[i][j] = (double)(entrada.nextDouble());
					}
				}
				for(int i=0; i<num_patrones; i++){
					neurona.calcularSalida(i, patron);
					System.out.printf("\n\nClase del patron(%d): %.1f.\n", i, neurona.obtenerSalidaCalculada());
                                        salidasentrenamiento[i] = neurona.obtenerSalidaCalculada(); //guardo en vector las salidas de los entrenamiento para difencias su valores por colores
				}

				 LinePlotTest frame = new LinePlotTest(patron,num_patrones,num_valores,entradas,salidas,salidasentrenamiento,neurona.obtenerX(),neurona.obtenerY());
                                frame.setVisible(true);
			}

		}while(opcion != 0);
	}
}
