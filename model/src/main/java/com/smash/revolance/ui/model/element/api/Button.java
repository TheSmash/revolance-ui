package com.smash.revolance.ui.model.element.api;

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

import com.smash.revolance.ui.model.helper.BotHelper;
import com.smash.revolance.ui.model.page.api.Page;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


/**
 * User: wsmash
 * Date: 28/01/13
 * Time: 20:33
 */
public class Button extends Element
{
    public Button(Page page, WebElement element)
    {
        super( page, element );
        setText( element.getText().trim() );
        setImplementation( "Button" );
        setType( element.getAttribute( "type" ) );
        setValue( element.getAttribute( "value" ) );
    }

    public Button(ElementBean bean)
    {
        super( bean.getPage(), bean );
    }

    public Button()
    {
        setTag( "button" );
    }

    public static boolean containsButton(List<Element> elements, Button element) throws Exception
    {
        for ( Element button : filterButtons( elements ) )
        {
            if ( button.getContent().contentEquals( element.getContent() ) )
            {
                return true;
            }
        }
        return false;
    }

    public static List<Element> filterButtons(List<Element> elements)
    {
        List<Element> buttons = new ArrayList<Element>();

        if ( elements != null )
        {
            for ( Element element : elements )
            {
                if ( element instanceof Button )
                {
                    buttons.add( element );
                }
            }
        }

        return buttons;
    }

    public static List<Element> getButtons(Page page) throws Exception
    {
        List<Element> buttons = new ArrayList<Element>();

        for ( WebElement element : BotHelper.getRawElements( page.getUser().getBot(), page ) )
        {
            try
            {
                if ( element.isDisplayed()
                        && getImplementation( element ).getClass().getName().contentEquals( Button.class.getName() ) )
                {
                    Button button = new Button( page, element );
                    if ( button.getArea() > 0 )
                    {
                        if ( !Button.containsButton( buttons, button ) )
                        {
                            buttons.add( button );
                        }
                    }
                }
            }
            catch (StaleElementReferenceException e)
            {
                System.err.println( e );
            }
        }

        return buttons;
    }

    public static boolean containsButton(List<Element> elements, String value) throws Exception
    {
        for ( Element element : filterButtons( elements ) )
        {
            if ( element.getContent().contentEquals( value ) )
            {
                return true;
            }
        }
        return false;
    }


}
