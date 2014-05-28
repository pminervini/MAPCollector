package com.neuralnoise.listener;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerImpl
{
    private static final Logger logger = Logger.getLogger( MessageListenerImpl.class );

    public void processMessage( String message )
    {
        logger.info( "Received message: " + message );
        System.out.println( "MessageListener::::::Received message: " + message );
    }
}
