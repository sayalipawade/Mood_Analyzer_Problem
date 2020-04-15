package com.moodanalysis;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyzerTestClass
{
    /* TC1.1:Given I am in sad mood message should return sad */
    @Test
    public void givenMeassage_WhenSad_ThenReturnMood() throws MoodAnalysisException
    {
        MoodAnalyzer mood=new MoodAnalyzer("I am in sad mood");
        String reaction=mood.moodAnalyzer("");
        Assert.assertEquals("sad", reaction);
    }

    /* TC1.2:Given I am in Any mood message should return happy */
    @Test
    public void givenMeassage_WhenHappy_ThenReturnMood() throws MoodAnalysisException
    {
        MoodAnalyzer mood=new MoodAnalyzer("I am in happy mood");
        String reaction=mood.moodAnalyzer("");
        Assert.assertEquals("happy",reaction);
    }
    
    /*TC3.1:Given NULL should throw MoodAnalysis Exception */
    @Test
    public void givenNull_WhenNull_ThenThrowException() throws MoodAnalysisException
    {
        MoodAnalyzer mood=new MoodAnalyzer(null);
        try
        {
            String reaction = mood.moodAnalyzer("");
            Assert.assertEquals("happy", reaction);
        }
        catch (MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.NULL,e.type);
        }
    }

    /*TC3.2:Given Empty should throw MoodAnalysis Exception */
    @Test
    public void givenEmpty_WhenEmpty_ThenThrowException() throws MoodAnalysisException
    {
        MoodAnalyzer mood=new MoodAnalyzer("");
        try
        {
            String reaction = mood.moodAnalyzer("");
            Assert.assertEquals("happy", reaction);
        }
        catch (MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.EMPTY,e.type);
        }
    }

    /*TC4.1:Given class name should return MoodAnalyzer object*/
    @Test
    public void givenClassName_DefaultCustructor_WhenProper_ThenReturnObject() throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer();
            MoodAnalyzer analyzemood = MoodAnalyserFactory.createMoodAnalyzer();
            boolean result = analyzemood.equals(mood);
            Assert.assertEquals(true,result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*TC4.2:Given class name when improper should throw MoodAnalysis exception*/
    @Test
    public void givenClassName_WhenImproper_ThenThrowMoodAnalysisException() throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer();
            MoodAnalyserFactory.getConstructor("MoodAnalyzer",String.class);
        }
        catch(MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.CLASS_NOT_FOUND,e.type);
        }
    }

    /*TC4.3:Given class name when constuctor improper then throw exception*/
    @Test
    public void givenClassName_Constructor_Whenimproper_ThenThrowMoodAnalysisException() throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer();
            MoodAnalyserFactory.getConstructor("com.moodanalysis.MoodAnalyzer",Integer.class);
        }
        catch(MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.METHOD_NOT_FOUND,e.type);
        }
    }

    /*TC5.1:Given class name when proper should return MoodAnalyser object*/
    @Test
    public void givenMoodAnalyser_ParameterConstructor_WhenProper_ThenReturnObject()  throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer("i am in happy mood");
            Object returnMood=MoodAnalyserFactory.getParameterizConstructor("com.moodanalysis.MoodAnalyzer",String.class,"i am in happy mood");
            boolean result = returnMood.equals(mood);
            Assert.assertEquals(true,result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*TC5.2:Given class name when improper should throw MoodAnalysis exception*/
    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalysisException() throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer("i am in happy mood");
            Object returnMood=MoodAnalyserFactory.getClassNameImproperWithParameter("com.moodanalysis.MoodAnalyze",String.class,"i am in happy mood");
            boolean result=returnMood.equals(mood);
            Assert.assertEquals(true,result);
        }
        catch(MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.CLASS_NOT_FOUND,e.type);
        }
    }

    /*TC5.3:Given class name when constructor improper should throw MoodAnalysis exception*/
    @Test
    public void givenClassName_WhenConstructorImproper_ThenThrowMoodAnalysisException() throws MoodAnalysisException
    {
        try
        {
            MoodAnalyzer mood = new MoodAnalyzer("i am in happy mood");
            Object returnMood=MoodAnalyserFactory.getParameterizConstructor("com.moodanalysis.MoodAnalyzer",Integer.class,"i am in happy mood");
            boolean result = returnMood.equals(mood);
        }
        catch(MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.METHOD_NOT_FOUND,e.type);
        }
    }

    /*TC6.1:Given message when proper should return happy */
    @Test
    public void givenHappy_WhenProper_ThenReturnHappyMood() throws MoodAnalysisException
    {
        try
        {
            Constructor<?> constructor = MoodAnalyserFactory.getConstructor("MoodAnalyzer",String.class);
            Object instance = constructor.newInstance("i am in happy mood");
            String analyser = MoodAnalyserFactory.moodAnalyzer((MoodAnalyzer) instance ,"moodAnalyzer") ;
            Assert.assertEquals("happy",analyser);
        }
        catch (MoodAnalysisException | InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }

    }

    /*TC6.2:Given message when improper should throw MoodAnalysis Exception */
    @Test
    public void givenHappyMessage_WhenImproperMethod_ShouldThrowMoodAnalysisException()
    {
        try
        {
            Constructor<?> constructor = MoodAnalyserFactory.getConstructor("com.moodanalysis.MoodAnalyzer",Integer.class);
            Object instance = constructor.newInstance("i am in happy mood");
            String analyser = MoodAnalyserFactory.moodAnalyzer ( (MoodAnalyzer) instance ,"moodAnalyzer") ;
            Assert.assertEquals("happy",analyser);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.METHOD_NOT_FOUND,e.type);
        }
    }

    /*TC7.1:Set happy message with reflector should return Happy*/
    @Test
    public void givenSetHappyMessageWithReflector_ShouldReturnHAPPY() throws MoodAnalysisException
    {
        try
        {
            Constructor<?> constructor = MoodAnalyserFactory.getConstructor("MoodAnalyzer",String.class);
            MoodAnalyzer moodAnalyze = MoodAnalyserFactory.createMoodAnalyzer();
            MoodAnalyserFactory.setFieldMoodAnalyser(moodAnalyze,"message","i am in happy mood");
            String analyser = MoodAnalyserFactory.moodAnalyzer ( (MoodAnalyzer)moodAnalyze ,"moodAnalyzer") ;
            Assert.assertEquals("happy",analyser);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*TC7.2:Set field When improper then throw exception*/
    @Test
    public void givenSetField_WhenImproper_ThenThrowException() throws MoodAnalysisException
    {
        try
        {
            Constructor<?> constructor = MoodAnalyserFactory.getConstructor("com.moodanalysis.MoodAnalyzer",String.class);
            MoodAnalyzer moodAnalyze = MoodAnalyserFactory.createMoodAnalyzer();
            MoodAnalyserFactory.setFieldMoodAnalyser(moodAnalyze,"message","i am in happy mood");
            String analyser = MoodAnalyserFactory.moodAnalyzer ( (MoodAnalyzer)moodAnalyze ,"moodAnalyzer") ;
            Assert.assertEquals(null,analyser);
        }
        catch (MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.NO_SUCH_FIELD,e.type);
        }
    }

    /*TC7.3:Setting null message with reflector should throw exception*/
    @Test
    public void givenMessageWithReflector_WhenNull_ShouldThrowException()
    {
        try
        {
            MoodAnalyzer moodAnalyze = MoodAnalyserFactory.createMoodAnalyzer();
            MoodAnalyserFactory.setFieldMoodAnalyser(moodAnalyze,"message",null);
        }
        catch (MoodAnalysisException e)
        {
            Assert.assertEquals(MoodAnalysisException.MyException_Type.NULL,e.type);
        }
    }
}



