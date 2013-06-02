package com.smash.revolance.ui.explorer.helper;

/*
        This file is part of Revolance UI Suite.

        Revolance UI Suite is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        Revolance UI Suite is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Revolance UI Suite.  If not, see <http://www.gnu.org/licenses/>.
*/

import com.smash.revolance.ui.explorer.element.api.Button;
import com.smash.revolance.ui.explorer.element.api.Element;
import com.smash.revolance.ui.explorer.element.api.Link;
import com.smash.revolance.ui.explorer.page.IPage;
import com.smash.revolance.ui.explorer.page.api.Page;
import com.smash.revolance.ui.explorer.user.User;
import com.smash.revolance.ui.explorer.bot.Bot;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: wsmash
 * Date: 03/05/13
 * Time: 08:15
 */
public class BotHelper
{
    public static boolean rightDomain(IPage page)
    {
        return rightDomain(page.getUser(), page.getUrl());
    }

    public static boolean rightDomain(User user, String url)
    {
        String domain = user.getDomain();
        return rightDomain( domain, url );
    }

    public static boolean rightDomain(String domain, String url)
    {
        if(domain != null && !domain.isEmpty() && url != null && !url.isEmpty())
        {
            return url.startsWith(domain);
        }
        else
        {
            return false;
        }
    }

    public static void sleep(long duration)
    {
        int count = 0;
        while(count < duration)
        {
            try
            {
                Thread.sleep( 1000 );
                count ++;
            }
            catch (InterruptedException e)
            {
                // Ignore gently !
            }
        }
    }

    public static String takeScreenshot(Bot bot) throws Exception
    {
        try
        {
            return ImageHelper.BASE64_IMAGE_PNG + ((TakesScreenshot) bot.getBrowser()).getScreenshotAs(OutputType.BASE64);
        }
        catch (Exception e)
        {
            sleep( 1 );
            return BotHelper.takeScreenshot( bot );
        }
    }

    /**
     * Return the checksum of the new page if the page content has changed or the previous one
     *
     *
     * @param bot
     * @param img
     * @return
     * @throws Exception
     */
    public static String pageContentHasChanged(Bot bot, String img) throws Exception
    {
        String newImg = BotHelper.takeScreenshot(bot);
        if( newImg != img )
        {
            return newImg;
        }
        else
        {
            return img;
        }
    }

    public static List<WebElement> getRawElements(Bot bot, Page page) throws Exception
    {
        UserHelper.browseTo(page);
        WebDriver browser = bot.getBrowser();
        return browser.findElements(By.xpath("//body//*"));
    }

    public static List<WebElement> getRawLinks(Bot bot, Page page) throws Exception
    {
        UserHelper.browseTo(page);
        WebDriver browser = bot.getBrowser();

        List<WebElement> elements = new ArrayList<WebElement>();

        By selector = By.xpath( "//body//a" );
        elements.addAll( browser.findElements( selector) );

        return elements;
    }

    public static List<WebElement> getRawButtons(Bot bot, Page page) throws Exception
    {
        UserHelper.browseTo(page);
        WebDriver browser = bot.getBrowser();

        List<WebElement> elements = new ArrayList<WebElement>();

        By selector = By.xpath("//body//input");
        elements.addAll( browser.findElements(selector) );

        selector = By.xpath("//body//button");
        elements.addAll( browser.findElements(selector) );

        return elements;
    }

    public static WebElement findMatchingElement(Bot bot, Element element) throws Exception
    {
        WebElement webelement = findWebElement( bot, element );
        if(webelement.isDisplayed() && webelement.isDisplayed())
        {
            return webelement;
        }
        else
        {
            throw new ElementNotVisibleException( "Element: " + webelement + " is not visible." );
        }
    }

    public static WebElement findWebElement(Bot bot, Element element) throws Exception
    {
        WebDriver browser = bot.getBrowser();
        if( !element.getId().trim().isEmpty() )
        {
            return browser.findElement( By.xpath("//body//" + element.getTag() + "[@id='" + element.getId() + "']") );
        }
        else if( element instanceof Link || element instanceof Button )
        {
            // Take the longest time (++)
            if(element instanceof Link)
            {
                return Element.filterElementByLocation( getRawLinks( bot, element.getPage() ), element.getLocation() );
            }
            else if( element instanceof Button )
            {
                return Element.filterElementByLocation( getRawButtons( bot, element.getPage() ), element.getLocation() );
            }
            return null;
        }
        else
        {
            System.err.println("There is no way to find back that element: " + element.toJson());
            return null;
        }
    }

    public static void handleApplicationAlertPopup( User user, Alert popup ) throws Exception
    {
        user.getApplication().handleAlert( popup );
    }

    public static boolean isAlertPresent( User user ) throws Exception
    {
        try
        {
            user.getBrowser().switchTo().alert().getText();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

    public static List<WebElement> getRawImages(Bot bot, Page page) throws Exception
    {
        UserHelper.browseTo(page);
        WebDriver browser = bot.getBrowser();

        List<WebElement> elements = new ArrayList<WebElement>();

        By selector = By.xpath( "//body//*" );
        elements.addAll( browser.findElements( selector) );

        return elements;
    }
}
