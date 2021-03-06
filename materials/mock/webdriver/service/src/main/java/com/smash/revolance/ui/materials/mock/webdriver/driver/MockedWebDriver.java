package com.smash.revolance.ui.materials.mock.webdriver.driver;

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revolance-UI-Materials-Mock-Webdriver-Service
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright (C) 2012 - 2013 RevoLance
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import com.smash.revolance.ui.materials.TemplateHelper;
import freemarker.template.TemplateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.Killable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.ErrorCodes;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * User: wsmash
 * Date: 28/09/13
 * Time: 11:49
 */
public class MockedWebDriver extends RemoteWebDriver implements TakesScreenshot, Killable
{
    private int                   port;
    private MockedWebDriverServer service;
    private Map<String, Object> context = new HashMap<String, Object>();

    public MockedWebDriver(int port) throws MalformedURLException
    {
        super( new URL( String.format( "http://localhost:%d/hub", port ) ), new DesiredCapabilities( "MockedWebDriver", "", Platform.ANY ) );
    }

    /**
     * Method called before {@link #startSession(org.openqa.selenium.Capabilities) starting a new session}. The default
     * implementation is a no-op, but subtypes should override this method to define custom behavior.
     */
    @Override
    public void startClient()
    {
        try
        {
            service = new MockedWebDriverServer( Integer.parseInt( System.getProperty( "webdriver.remote.server" ) ) );
            service.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method called after executing a {@link #quit()} command. Subtypes
     */
    @Override
    public void stopClient()
    {
        try
        {
            service.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setElementClicked(boolean b)
    {
        context.put( "elementClicked", "ok" );
    }

    public void setElementId(String id)
    {
        context.put( "elementIdAttribute", id );
    }

    public void setElementDisabled(boolean disabled)
    {
        context.put( "elementDisabledAttribute", String.valueOf( disabled ) );
    }

    public void setElementClass(String clazz)
    {
        context.put( "elementClassAttribute", clazz );
    }

    public void setElementHref(String href)
    {
        context.put( "elementHrefAttribute", href );
    }

    public void setElementType(String type)
    {
        context.put( "elementTypeAttribute", type );
    }

    public void setElementLocation(int x, int y)
    {
        context.put( "elementX", String.valueOf( x ) );
        context.put( "elementY", String.valueOf( y ) );
    }

    public void setElementSize(int w, int h)
    {
        context.put( "elementWidth", String.valueOf( w ) );
        context.put( "elementHeight", String.valueOf( h ) );
    }

    public void setElementText(String text)
    {
        context.put( "elementText", text );
    }

    public void setElementTagName(String tagName)
    {
        context.put( "elementTagName", tagName );
    }

    public void setSessionId(String id)
    {
        context.put( "sessionId", id );
    }

    public void setUrl(String url)
    {
        context.put( "currentUrl", url );
    }

    public void setAlertMessage(String msg)
    {
        context.put( "alertMessage", msg );
    }

    public void setAlertStatusCode(ErrorCodes code)
    {
        context.put( "alertStatusCode", String.valueOf( code ) );
    }

    private String buildJsonResponse(Map<String, Object> context, String method, String templatePath)
    {
        try
        {
            templatePath = templatePath + "/" + String.valueOf( method ).toLowerCase();
            return TemplateHelper.processTemplate( templatePath + "/body.json", context );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void kill()
    {

    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException
    {
        // Get the screenshot as base64.
        String base64 = execute( DriverCommand.SCREENSHOT ).getValue().toString();
        // ... and convert it.
        return target.convertFromBase64Png( base64 );
    }
}
