package com.atlauncher.utils.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Java7OrLessLauncherTest {
    @Test
    public void testGetPermgenParameterEnoughFromLauncher() {
        JavaLauncher subject = new Java7OrLessLauncher();
        assertEquals( "-XX:PermSize=1234M", subject.getPermgenParameter( 1234, 2 ));
    }
    
    @Test
    public void testGetPermgenParameterInstanceOverride() {
        JavaLauncher subject = new Java7OrLessLauncher();
        assertEquals( "-XX:PermSize=1235M", subject.getPermgenParameter( 2, 1235 ));
    }
}
