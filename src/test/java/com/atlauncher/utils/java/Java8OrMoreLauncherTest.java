package com.atlauncher.utils.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Java8OrMoreLauncherTest {
    @Test
    public void testGetPermgenParameterEnoughFromLauncher() {
        JavaLauncher subject = new Java8OrMoreLauncher();
        assertEquals( "-XX:MetaspaceSize=1234M", subject.getPermgenParameter( 1234, 2 ));
    }
    
    @Test
    public void testGetPermgenParameterInstanceOverride() {
        JavaLauncher subject = new Java8OrMoreLauncher();
        assertEquals( "-XX:MetaspaceSize=1235M", subject.getPermgenParameter( 2, 1235 ));
    }
}
