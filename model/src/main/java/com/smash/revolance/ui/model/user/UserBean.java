package com.smash.revolance.ui.model.user;

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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

/**
 * User: wsmash
 * Date: 21/04/13
 * Time: 11:50
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class UserBean
{
    @JsonIgnore
    private User instance;

    private String id    = "";
    private String login = "";


    private String newPasswd = "";
    private String passwd    = "";
    private boolean followButtonsEnabled;
    private boolean followLinksEnabled;
    private boolean pageScreenshot;
    private boolean pageElementScreenShot;
    private String domain        = "";
    private String home          = "";

    @JsonDeserialize(contentAs = String.class, as = ArrayList.class)
    private List<String> excludedLinks = new ArrayList<String>();

    @JsonDeserialize(contentAs = String.class, as = ArrayList.class)
    private List<String> excludedButtons = new ArrayList<String>();

    private int     browserHeight;
    private int     browserWidth;
    private boolean exploreVariants;

    private String browserType   = "";

    public UserBean(User instance)
    {
        this.instance = instance;
    }

    public UserBean()
    {

    }

    public User getInstance()
    {
        return instance;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setNewPasswd(String newPasswd)
    {
        this.newPasswd = newPasswd;
    }

    public String getNewPasswd()
    {
        return newPasswd;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public boolean isFollowButtonsEnabled()
    {
        return followButtonsEnabled;
    }

    public void setFollowButtonsEnabled(boolean b)
    {
        this.followButtonsEnabled = b;
    }

    public boolean isFollowLinksEnabled()
    {
        return followLinksEnabled;
    }

    public void setFollowLinksEnabled(boolean b)
    {
        this.followLinksEnabled = b;
    }

    public boolean isPageScreenshotEnabled()
    {
        return pageScreenshot;
    }

    public void setPageScreenshotEnabled(boolean b)
    {
        this.pageScreenshot = b;
    }

    public void setPageElementScreenshotEnabled(boolean b)
    {
        this.pageElementScreenShot = b;
    }

    public boolean isPageElementScreenshotEnabled()
    {
        return pageElementScreenShot;
    }

    public void setInstance(User instance)
    {
        this.instance = instance;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public void setHome(String home)
    {
        this.home = home;
    }

    public String getHome()
    {
        return home;
    }

    public List<String> getExcludedLinks()
    {
        return excludedLinks;
    }

    public void setExcludedLinks(List<String> excludedLinks)
    {
        this.excludedLinks = excludedLinks;
    }

    public List<String> getExcludedButtons()
    {
        return excludedButtons;
    }

    public void setExcludedButtons(List<String> excludedButtons)
    {
        this.excludedButtons = excludedButtons;
    }

    public int getBrowserHeight()
    {
        return browserHeight;
    }

    public void setBrowserHeight(int browserHeight)
    {
        this.browserHeight = browserHeight;
    }

    public int getBrowserWidth()
    {
        return browserWidth;
    }

    public void setBrowserWidth(int browserWidth)
    {
        this.browserWidth = browserWidth;
    }

    public void setExploreVariants(boolean exploreVariants)
    {
        this.exploreVariants = exploreVariants;
    }

    public boolean isExploreVariantsEnabled()
    {
        return exploreVariants;
    }

    public void setBrowserType(String browserType)
    {
        this.browserType = browserType;
    }

    public String getBrowserType()
    {
        return browserType;
    }
}
