package com.atlauncher.test;

import com.atlauncher.utils.Utils;
import org.junit.Test;

public class TestImages {
    @Test
    public void test(){
        System.out.println("With Slash: " + (Utils.getIconImage("/icon.png") != null));
    }
}