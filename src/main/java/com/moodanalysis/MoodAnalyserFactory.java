package com.moodanalysis;

import java.lang.reflect.Constructor;

public class MoodAnalyserFactory
{
    //Return class object
    public static Constructor<?> getConstructor(String className, Class methodName) throws MoodAnalysisException {
        try
        {
            Class <?> moodAnalyzer=Class.forName(className);
            return moodAnalyzer.getConstructor(methodName);
        }
        catch (ClassNotFoundException e)
        {
            throw new MoodAnalysisException(MoodAnalysisException.MyException_Type.CLASS_NOT_FOUND,"Please enter valid class name");
        }
        catch (NoSuchMethodException e)
        {
            throw new MoodAnalysisException(MoodAnalysisException.MyException_Type.METHOD_NOT_FOUND,"Please enter valid method name");
        }
    }

    //Return the constructor instance
    public static MoodAnalyzer createMoodAnalyzer() throws MoodAnalysisException
    {
        try
        {
            Class<?> moodAnalyzer = Class.forName("com.moodanalysis.MoodAnalyzer");
            Constructor<?> constructor = moodAnalyzer.getConstructor();
            Object instance = moodAnalyzer.newInstance();
            return (MoodAnalyzer) instance;
        }
        catch (ClassNotFoundException e)
        {
            throw new MoodAnalysisException(MoodAnalysisException.MyException_Type.CLASS_NOT_FOUND,"Please enter valid class name");
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
           throw new MoodAnalysisException(MoodAnalysisException.MyException_Type.METHOD_NOT_FOUND,"please enter valid method");
        }
        return null;
    }
}
