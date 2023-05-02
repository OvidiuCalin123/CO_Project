package Screens.SelectedFunction.SequentialRead;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

public class SequentialReadLogic implements SelectedFunctionLogicHandle {

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

    public boolean getIsCompleted(){
        return isCompleted;
    }

    public void setIsCompleted(boolean value){
        isCompleted = value;
    }

    public double getTime(){ return time/1000; }

    public double getScore(){
        return speed;
    }
}
