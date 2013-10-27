package com.smash.revolance.ui.cmdline;

import com.smash.revolance.ui.comparator.application.ApplicationComparator;
import com.smash.revolance.ui.comparator.application.ApplicationComparison;
import com.smash.revolance.ui.explorer.ApplicationExplorer;
import com.smash.revolance.ui.materials.JsonHelper;
import com.smash.revolance.ui.model.application.Application;
import com.smash.revolance.ui.model.application.ApplicationManager;
import com.smash.revolance.ui.model.sitemap.SiteMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * User: wsmash
 * Date: 28/04/13
 * Time: 22:10
 */
public enum Commands
{
    EXPLORE( "explore", "appCfg.xml", "out.txt" ),
    STOP_SERVER( "stop", "httpPort" ),
    STATUS( "status", "httpPort" );

    private String[] opts = new String[]{};
    private final String   cmd;

    private Commands(String cmd, String... opts)
    {
        this.cmd = cmd;
        this.opts = opts;
    }

    public String getCmd()
    {
        return cmd;
    }

    public int getOptsCount()
    {
        return this.opts == null ? 0 : this.opts.length;
    }

    private String getOpts()
    {
        String optsString = "";
        for ( int optIdx = 0; optIdx < getOptsCount(); optIdx++ )
        {
            optsString += opts[optIdx];
        }
        return optsString.trim();
    }

    public String getUsage()
    {
        return String.format( "%s %s", getCmd(), getOpts() );
    }

    public static String getUsages()
    {
        String usage = "";
        for ( Commands cmd : Commands.values() )
        {
            usage += String.format( "Usage: %s\n", cmd.getUsage() );
        }
        return usage;
    }

    public static Commands toCommand(String cmd, int optsCount)
    {
        for ( Commands command : Commands.values() )
        {
            if ( command.getOptsCount() == optsCount && command.getCmd().contentEquals( cmd ) )
            {
                return command;
            }
        }
        return null;
    }

    public int exec(String... opts) throws Exception
    {
        int execStatus = -1;

        if ( getOptsCount() == opts.length )
        {
            File workingDir = new File( "." ).getAbsoluteFile();
            switch ( this )
            {
                case EXPLORE:

                    execStatus = execExploreCmd( workingDir, opts );
                    break;

                case STOP_SERVER:

                    execStatus = execStopServerCmd( workingDir, opts );
                    break;

                case STATUS:

                    execStatus = execStatusCmd( workingDir, opts );
                    break;
            }
        }

        return execStatus;
    }

    private int execStatusCmd(File workingDir, String[] opts)
    {
        try
        {
            System.out.println( "Retrieving server status" );
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://localhost:"+opts[0]+"/ping");
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == 200)
            {
                System.out.println("server is started");
                return 0;
            }
            else
            {
                System.out.println( "server is started" );
                return 0;
            }
        }
        catch (IOException e)
        {
            System.out.println( "server is stopped" );
            return 0;
        }
    }

    private int execStopServerCmd(File workingDir, String[] opts)
    {
        try
        {
            System.out.println( "Stopping server" );
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://localhost:"+opts[0]+"/shutdown");
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() != 200)
            {
                System.out.println("Stopping server [Done]");
                return 0;
            }
            else
            {
                System.err.println( "Stopping server [Failed]" );
                return -1;
            }
        }
        catch (IOException e)
        {
            return 0;
        }
    }

    private void execCompareCmd(File workingDir, String[] opts) throws Exception
    {
        File sitemapFile = new File( workingDir, opts[0] );
        if ( !sitemapFile.exists() )
        {
            throw new FileNotFoundException( "Missing sitemap file: " + sitemapFile );
        }

        File sitemapRefFile = new File( workingDir, opts[0] );
        if ( !sitemapRefFile.exists() )
        {
            throw new FileNotFoundException( "Missing sitemapRef file: " + sitemapRefFile );
        }

        SiteMap sitemap = SiteMap.fromJson( sitemapFile );
        SiteMap sitemapRef = SiteMap.fromJson( sitemapRefFile );
        ApplicationComparator diffMaker = new ApplicationComparator();

        ApplicationComparison comparison = diffMaker.compare( sitemap, sitemapRef );

        File regression = new File( opts[1] );
        JsonHelper.getInstance().map( regression, comparison );
    }

    private int execExploreCmd(File workingDir, String[] opts) throws Exception
    {
        File appCfg = new File( workingDir, opts[0] );
        if ( !appCfg.exists() )
        {
            throw new FileNotFoundException( "Missing appCfg file: " + appCfg );
        }

        ApplicationManager manager = new ApplicationManager( appCfg );
        for ( Application app : manager.getApplications() )
        {
            new ApplicationExplorer( app ).explore( 60 );
        }

        return 0;
    }

}
