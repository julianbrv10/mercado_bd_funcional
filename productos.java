
package com.mycompany.super_bd;

/**
 *
 * @author erick
 */
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

class productos {
    //declaro mis variables a usar como principales
    private float precio;
    private int cantidad;
    private String nombre;
    public productos(float precio, int cantidad, String nombre){ //pongo mi metodo con las variables a usar dentro
            this.precio = precio;
            this.cantidad = cantidad;
            this.nombre = nombre;
    }
    public float getTotal(){
            return precio*cantidad;  // metodo para calcular el total
    }
    public String getNombre(){
            return nombre;    //metodo para escribir los nombres de los productos
    }
    public int getCantidad(){
            return cantidad;       //metodo para poner la cantidad de productos a llevar
    }
    public void setCantidad(int cantidad){
		this.cantidad=cantidad;
	}
}
    /**
     * @param args the command line arguments
     */
class Program extends JFrame{
    public static String salida;
    public static ArrayList<productos> productsList; // agrego las variables y el nombre de la lista que ocupe 
    public static int cont;                            // utilizando el arrayList de su libreria
    public Program(){
        salida="<html><body><p>";
        cont=1;
        productsList = new ArrayList(); // agrego mi instancia que hace referencia al arrayList

        JLabel deleteFieldLabel = new JLabel("Numero de producto a eliminar");
        JLabel deleteFieldCantidadLabel = new JLabel("Cantidad");
        final JTextField deleteFieldCantidad = new JTextField();
        final JTextField deleteField = new JTextField();

        JButton addProduct = new JButton("Añadir");
        JButton deleteProduct = new JButton("Eliminar");    // agrego los botones que utilizare con sus respectivos nombres
        JButton showTotal = new JButton("Ver total");
        final JTextField nombre = new JTextField();
        final JTextField precio = new JTextField();     // escribo lo que guardara cada label que sea = a nuevo campo de texto
        final JTextField cantidad = new JTextField();
        JLabel nombreLabel = new JLabel("Nombre");
        JLabel precioLabel = new JLabel("Precio");      // le asigno el nombre que dira el label correspondiente para que se guarde
        JLabel cantidadLabel = new JLabel("Cantidad");  // en su respectivo lugar de variable
        JLabel title = new JLabel("Super el julito");   // escribo el nombre del titulo que tendra la ventana
        final JLabel products = new JLabel(salida);

        title.setBounds(0, 0, 190, 40);
        title.setFont(new java.awt.Font("Comic Sans", 2, 25));  // le doy formato al titulo, poniendo sus coordenadas
        products.setBounds(0, 20, 190, 80);                     // su tipo de letra y tamaño

        deleteField.setBounds(440, 360, 30, 30);
        deleteFieldLabel.setBounds(200, 360, 280, 30);
        deleteFieldCantidadLabel.setBounds(490, 360, 80, 30);
        deleteFieldCantidad.setBounds(570, 360, 30, 30);

        nombreLabel.setBounds(200, 290, 120, 30);
        precioLabel.setBounds(340, 290, 120, 30);       // en este bloque de codigo le doy formato a los label y botones
        cantidadLabel.setBounds(480, 290, 120, 30);     // los numeros que estan entre parentesis son los ejes de coordenadas

        nombre.setBounds(200, 320, 120, 30);            // los ultimos 2 numeros son el tamaño del label o boton 
        precio.setBounds(340, 320, 120, 30);            // lo que quiere decir el width y el height (ancho y alto)
        cantidad.setBounds(480, 320, 120, 30);

        addProduct.setBounds(200, 400, 120, 30);
        deleteProduct.setBounds(340, 400, 120, 30);
        showTotal.setBounds(480, 400, 120, 30);

        this.add(deleteFieldLabel);
        this.add(deleteField);
        this.add(deleteFieldCantidad);
        this.add(deleteFieldCantidadLabel);

        this.add(title);
        this.add(products);

        this.add(addProduct);
        this.add(deleteProduct);        // con estos this.add agrego todos los label y botones que se muestran
        this.add(showTotal);            // es como si se estuvieran arrastrando cada forma desde la herrramienta formulario

        this.add(nombreLabel);             // cada cosa entre parentesis es el nombre de mi label o boton que necesito 
        this.add(precioLabel);
        this.add(cantidadLabel);

        this.add(nombre);
        this.add(precio);
        this.add(cantidad);

        addProduct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                productos temp = new productos(Float.parseFloat(precio.getText()), Integer.parseInt(cantidad.getText()), nombre.getText());
                // convierto mis variables al tipo de dato que necesito
                productsList.add(temp);
                salida += String.format("%d.- %s<br>", cont, nombre.getText());
                // intercambio los valores por lo que vale mi contador y mi variable nombre
                // para que se vaya mostrando de 1 en 1 el contador y en forma de lista con
                // los productos que va agregando el usuario
                products.setText(salida);
                precio.setText("");
                cantidad.setText("");
                nombre.setText("");
                cont++;
            }
        });
        showTotal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                float total=0;
                int cont = 1;           // el %d sera reemplazado por el valor de cont de tipo int
                String out = "";        // el %s es para string, %f para floats
                for(productos i : productsList){        
                        total+= i.getTotal();
                        salida += String.format("%d.- %s(%d) = %f\n", cont, i.getNombre(), i.getCantidad(), i.getTotal());
                        // con los % serán reeemplazados por los valores que estan a continuación en color blanco 
                        cont++;
                }
                JOptionPane.showMessageDialog(null, String.format("%s\n Total = %f", salida, total)); 
                // muestro el total de los productos cambiando el valor por lo que vale mi variable total
                }
            });
        deleteProduct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int selection=Integer.parseInt(deleteField.getText());
                int num=Integer.parseInt(deleteFieldCantidad.getText());    // hago las conversiones a int con el parseo
                selection-=1;
                if(num<productsList.get(selection).getCantidad() && num>0){
                        productsList.get(selection).setCantidad(productsList.get(selection).getCantidad()-num);
                }       // condicion para que valide si la cantidad a eliminar esta disponible y si pone un numero menor 
                else{   // a la cantidad le va a restar a la cantidad total
                        productsList.remove(selection);    // si es mayor al num de productos, va a eliminar por completo el producto
                }
                cont=1;
                salida = "<html><body><p>";
                for(productos i : productsList){
                        salida += String.format("%d.- %s<br>", cont, i.getNombre()); // muestro el nombre de los productos cambiando el valor 
                                        //por lo que vale mi variable nombre y le agrego tambien al inicio mi variable cont
                                        // para que vaya incrementando el numero del indice de los productos 
                        cont++;
                }
                products.setText(salida);
                deleteField.setText("");
                deleteFieldCantidad.setText("");
                   }
		});
            this.setResizable(false);
            this.setLayout(null);
            this.setDefaultCloseOperation(3);   // agrego formato a la ventana, como tamaño, que sea estatica y tenga un titulo
            this.setTitle("Supermercado");      // como sangria
            this.setSize(640,480);
            this.setVisible(true);
    }
    public static void main(String args[]){
            Program app = new Program();    // mando llamar a mi metodo program, que es donde tengo el programa funcional
    }
}