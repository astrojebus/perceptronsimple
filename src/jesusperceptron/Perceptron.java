/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesusperceptron;

import java.util.Scanner;

/**
 *
 * @author Jesus
 */
public class Perceptron{
        private double X;                       //VALOR X POR DONDE VA A PASAR LA LINEA
        private double Y;                       //VALOR Y POR DONDE VA A PASAR LA LINEA
	private double bias;			// Umbral de activacion de la neurona.
	private double error;			// Error de aprendizaje actual.
	private double salida;			// Salida obtenida de la neurona.
	private double[] pesos;			// Patron de pesos asociados a las entradas.

	public Perceptron(int patrones, int argumentos){
		this.error = 0.0;
		this.salida = 0.0;
                
		do{
			this.bias = (Math.random()*10 + 1)/10.0;			// Inicializacion aleatoria del bias.
		}while(this.bias == 0.0);
                
		this.pesos = new double[argumentos];
		for(int i=0; i<argumentos; i++){
			do{
				this.pesos[i] = (Math.random()*10 + 1)/10.0;	// Inicializacion aleatoria de los pesos.
			}while(this.pesos[i]==0.0);
		}
	}

	public void imprimePesos(){
		for(int i=0; i<this.pesos.length; i++)
			System.out.printf("%f ", this.pesos[i]);
	}

	public void establecerBias(double bias){
		this.bias = bias;
	}

	public void establecerError(double error){
		this.error = error;
	}

	public double obtenerError(){
		return this.error;
	}

	

	public double obtenerBias(){
		return this.bias;
	}

	public double obtenerSalidaCalculada(){
		return this.salida;
	}

	public double obtenerX(){           // SACA EL VALOR DE X
            
            X= -(this.bias)/pesos[0];           //EL PRIMER VALOR DEL VECTOR ES W1
		return this.X;
	}
        
        public double obtenerY(){
            
            Y= -(this.bias)/pesos[1];           //EL SEGUNDO VALOR DEL VECTOR ES W2
		return this.Y;
	}
        
        public double[] obtenerPesos(){
		return this.pesos;
	}

	public void calcularSalida(int indice, double[][] entradas){	// Funcion de Activacion 'Harlims'.
		double salida;
		salida = 0.0;
		for(int i=0; i<this.pesos.length; i++)
			salida = salida + (this.pesos[i] * entradas[indice][i]);
		salida = salida + bias;
		if(salida > 0.0)
			this.salida = 1.0;
		else
			this.salida = 0.0;
	}

	public void actualizarBias(){
		this.bias = this.bias + (this.error);
	}

	public void actualizarPesos(int indice, double[][] entradas){	// Regla para actualizar los pesos sinapticos.
		double[] aux_pesos = new double[this.pesos.length];
		for(int i=0;i<this.pesos.length; i++){
			aux_pesos[i] =  this.error * entradas[indice][i];
			this.pesos[i] = this.pesos[i] + aux_pesos[i];
		}
	}

    void EstablecerPesosManuales(int num_valores) {
        this.pesos = new double[num_valores];
        Scanner entrada = new Scanner(System.in);
        
		for(int i=0; i<num_valores; i++){
			do{
                            System.out.printf("Ingrese el peso número [%d] ", i);
				this.pesos[i] =  entrada.nextDouble();	// Inicializacion manual de los pesos.
			}while(this.pesos[i]==0.0);
		}
    }

  

    
}
