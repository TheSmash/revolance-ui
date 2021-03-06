package com.smash.revolance.ui.comparator.application;

/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ui-monitoring-comparator
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright (C) 2012 - 2014 RevoLance
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

import com.smash.revolance.ui.comparator.NoMatchFound;
import com.smash.revolance.ui.comparator.page.*;
import com.smash.revolance.ui.model.page.api.PageBean;
import com.smash.revolance.ui.model.sitemap.SiteMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: wsmash
 * Date: 09/06/13
 * Time: 12:08
 */
@Service
public class ApplicationComparator implements IApplicationComparator
{

    IPageMatchMaker pageMatchMaker = new PageMatchMaker();

    IPageComparator pageComparator = new PageComparator();

    @Override
    public ApplicationComparison compare(SiteMap reference, SiteMap sitemap) throws IllegalArgumentException
    {
        if ( reference == null )
        {
            throw new IllegalArgumentException( "Null sitemap reference passed in." );
        }

        if ( sitemap == null )
        {
            throw new IllegalArgumentException( "Null sitemap passed in." );
        }

        ApplicationComparison comparison = new ApplicationComparison();

        List<PageBean> refPages = new ArrayList<PageBean>();
        for ( PageBean refPage : reference.getPages() )
        {
            refPages.add( refPage );
        }

        List<PageBean> pages = new ArrayList<PageBean>();
        pages.addAll( sitemap.getPages() );

        while ( !refPages.isEmpty() )
        {
            PageBean page = refPages.get( 0 );
            if ( !page.isBroken() && !page.isExternal() )
            {
                try
                {
                    Collection<PageMatch> matches = pageMatchMaker.findMatch( pages, page, PageSearchMethod.URL, PageSearchMethod.TITLE, PageSearchMethod.CONTENT );
                    PageMatch bestMatch = pageMatchMaker.getBestMatch( matches );

                    if ( bestMatch.getReference() != null && bestMatch.getMatch() != null )
                    {
                        PageComparison pageComparison = pageComparator.compare( bestMatch.getMatch(), bestMatch.getReference() );
                        comparison.addPageComparison( pageComparison );

                        refPages.remove( bestMatch.getReference() );
                        pages.remove( bestMatch.getMatch() );
                    }
                }
                catch (NoMatchFound noMatchFound)
                {
                    // Then the page is added
                    comparison.addRemovedPage( page );
                    refPages.remove( page );
                }
            }
            else
            {
                refPages.remove( page );
            }
        }

        for ( PageBean page : pages )
        {
            comparison.addNewPage( page );
        }

        return comparison;
    }
}
