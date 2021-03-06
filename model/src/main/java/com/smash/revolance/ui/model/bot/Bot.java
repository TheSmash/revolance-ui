package com.smash.revolance.ui.model.bot;

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revolance-UI-Model
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

import com.smash.revolance.ui.model.helper.UserHelper;
import com.smash.revolance.ui.model.user.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * User: wsmash
 * Date: 30/11/12
 * Time: 16:58
 */
public class Bot
{
    private User user;

    private double xScale = 1;
    private double yScale = 1;

    public Bot(User user) throws BrowserFactory.InstanciationError
    {
        BrowserFactory.instanceciateNavigator( user, user.getBrowserType() );
        setUser( user );
    }

    public WebDriver getBrowser() throws Exception
    {
        return getUser().getBrowser();
    }

    public Bot setUser(User user)
    {
        this.user = user;
        return this;
    }

    public User getUser()
    {
        return user;
    }

    public String getCurrentUrl() throws Exception
    {
        UserHelper.handleAlert( getUser() );
        return getBrowser().getCurrentUrl();
    }

    public String getCurrentTitle() throws Exception
    {
        UserHelper.handleAlert( getUser() );
        return getBrowser().getTitle();
    }

    public double getYScale()
    {
        return yScale;
    }

    public double getXScale()
    {
        return xScale;
    }

    public Object runJS(String script) throws Exception
    {
        try
        {
            JavascriptExecutor js = (JavascriptExecutor) getBrowser();
            return js.executeScript( script );
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
