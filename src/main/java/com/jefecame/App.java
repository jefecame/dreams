package com.jefecame;

import com.jefecame.dreams.view.Dreams;

/**
 * Clase principal de la aplicación para iniciar el programa Dreams.
 */
public class App 
{
    public static void main( String[] args )
    {
        Dreams dreams = new Dreams();
        dreams.mostrarMenu();
    }
}