package com.smash.revolance.ui.tests.smoke;

import com.smash.revolance.ui.materials.CmdlineHelper;
import com.smash.revolance.ui.materials.TestConstants;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ServerLifecycleTest extends TestConstants
{
    private static CmdlineHelper statusAfterStop;
    private static CmdlineHelper start;
    private static CmdlineHelper stop;
    private static CmdlineHelper statusAfterStart;

    @BeforeClass
    public static void setupTests() throws Exception
    {
        start = new CmdlineHelper().dir( DISTRIB_DIR ).cmd( START_SCRIPT );
        statusAfterStart = new CmdlineHelper().dir( DISTRIB_DIR ).cmd( STATUS_SCRIPT );
        stop = new CmdlineHelper().dir( DISTRIB_DIR ).cmd( STOP_SCRIPT );
        statusAfterStop = new CmdlineHelper().dir( DISTRIB_DIR ).cmd( STATUS_SCRIPT );

        // startServerAndCheckStatus();
    }

    @AfterClass
    public static void teardownTests() throws Exception
    {
        // stopServerAndCheckStatus();
    }

    @Test
    public void checkDistribContent()
    {
        assertThat( listDistribFiles(), hasItems(   endsWith("/start.sh"),
                                                    endsWith("/stop.sh"),
                                                    endsWith("/status.sh"),
                                                    endsWith("/config/browsers.xml"),
                                                    containsString("/bin/ui-monitoring-cmdline"),
                                                    containsString("/web-apps/ui-monitoring-server"),
                                                    containsString("/samples/")));
    }

    public static List<String> listDistribFiles()
    {
        List<String> files = new ArrayList<>();
        List<File> distribFiles = (List<File>) FileUtils.listFiles(DISTRIB_DIR, null, true);
        for(File file : distribFiles)
        {
            files.add(file.toString());
        }
        return files;
    }

    public static void stopServerAndCheckStatus() throws Exception
    {
        stop.sync().exec().outContains( "Stopping server [Done]" );
        assertThat( stop.exitValue(), is(0) );

        statusAfterStop.sync().exec().awaitOut( "server is stopped", 10 );
        assertThat( statusAfterStop.exitValue(), is(0) );
    }

    public static void startServerAndCheckStatus() throws Exception
    {
        start.exec().awaitOut("Starting server [Done]", 10);
        assertThat(start.hasExited(), is(false));

        statusAfterStart.sync().exec().awaitOut( "server is started", 10 );
        assertThat( statusAfterStart.exitValue(), is(0) );
    }

}