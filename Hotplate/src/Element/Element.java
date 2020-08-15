/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import java.util.ArrayList;

/**
 *
 * @author gracesubianto 16946441
 */
public class Element implements Runnable 
{
    //-------------------------------------------------------------------------- variables
    private ArrayList<Element> neighbours;
    private double currentTemp;
    public static double heatConstant = 0.1;
    private boolean stopRequested;

    //-------------------------------------------------------------------------- methods
    /**
     * Element constructor accepts initial temp and initializes any of its
     * fields
     *
     * @param currentTemp
     */
    public Element(double currentTemp) 
    {
        this.currentTemp = currentTemp;
        this.neighbours = new ArrayList<>();
        this.stopRequested = false;
    }

    /**
     * Starts running an element instance in its own thread
     */
    public void start() 
    {
        Element aElement = new Element(0);
        Thread aThread = new Thread(aElement);
        aThread.start();
    }

    /**
     * Gets the current temp of the element
     * @return currentTemp
     */
    public double getTemperature() 
    {
        return this.currentTemp;
    }

    /**
     * What the program executes when stopRequested is false 
     * Calculates the current temperature of an element from the temperatures of its neighbors
     */
    public void run() 
    {
        stopRequested = false;
        synchronized (this) 
        {
            while (stopRequested == false) 
            {
                double averageTemp = 0;
                double sumTemp = 0;
                
                for(Element element : neighbours) 
                {
                    sumTemp += element.currentTemp;
                }
                averageTemp = sumTemp / neighbours.size();
                this.currentTemp += ((averageTemp - currentTemp) * heatConstant);
                //System.out.println("Temp: " + currentTemp);

                //Stops the program when the thread temperatures are within a certain difference of each other
                if(Math.abs(neighbours.get(0).getTemperature() - currentTemp) < 0.2) 
                {
                    requestStop();
                    System.exit(0);
                }
                try 
                {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        }
    }

    /**
     * When the method is called the program must stop
     */
    public void requestStop() 
    {
        this.stopRequested = true;
    }

    /**
     * adds a neighboring element
     * @param element 
     */
    public synchronized void addNeighbour(Element element) 
    {

        this.neighbours.add(element);
    }

    /**
     * Calculates the current temp to be applied to the element
     * @param appliedTemp 
     */
    public synchronized void applyTemperature(double appliedTemp) 
    {
        this.currentTemp += ((appliedTemp - currentTemp) * heatConstant);
    }

    public static void main(String[] args) 
    {
        //Creates two Element objects that are neighbours to each other
        //First element set at 300, second at 0
        ArrayList<Element> neighbours = new ArrayList<>();
        Element element1 = new Element(0);
        Element element2 = new Element(300);

        //Elements are neighbours to each other
        element1.addNeighbour(element2);
        element2.addNeighbour(element1);

        //Creates two new threads
        Thread thread1 = new Thread(element1);
        Thread thread2 = new Thread(element2);

        //Start both threads by calling each "start" method
        thread1.start();
        thread2.start();

        //Prints out the temperatures of both threads while they are not within a certain difference of each other
        while((Math.abs(element1.getTemperature() - element2.getTemperature())) != 0.1) 
        {
            System.out.println("Element 1 temperature: " + element1.getTemperature());
            System.out.println("Element 2 temperature: " + element2.getTemperature());
            System.out.println("");
            try 
            {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}
