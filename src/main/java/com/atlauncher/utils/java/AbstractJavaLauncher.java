package com.atlauncher.utils.java;

public abstract class AbstractJavaLauncher implements JavaLauncher {
    protected String buildPermgenParameter( String jvmArgument, int sizeFromLauncher, int sizeFromInstance ) {
        return new StringBuilder()
                    .append( "-XX:" )
                    .append( jvmArgument )
                    .append( "=" )
                    .append( Math.max( sizeFromLauncher, sizeFromInstance ) )
                    .append( "M" )
                    .toString();
    }
}
