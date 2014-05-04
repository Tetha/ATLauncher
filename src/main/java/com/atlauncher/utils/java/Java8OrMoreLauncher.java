package com.atlauncher.utils.java;

public final class Java8OrMoreLauncher extends AbstractJavaLauncher {

    @Override
    public String getPermgenParameter( int sizeFromLauncher, int sizeFromInstance ) {
        return super.buildPermgenParameter( "MetaspaceSize", sizeFromLauncher, sizeFromInstance );
    }

}
