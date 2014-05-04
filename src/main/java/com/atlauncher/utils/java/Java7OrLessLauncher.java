package com.atlauncher.utils.java;

public final class Java7OrLessLauncher extends AbstractJavaLauncher {

    @Override
    public String getPermgenParameter( int sizeFromLauncher, int sizeFromInstance ) {
        return buildPermgenParameter( "PermSize", sizeFromLauncher, sizeFromInstance );
    }

}
