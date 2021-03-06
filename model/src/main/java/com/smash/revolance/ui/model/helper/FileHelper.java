package com.smash.revolance.ui.model.helper;

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

import java.io.File;
import java.io.IOException;

/**
 * User: wsmash
 * Date: 06/12/12
 * Time: 13:34
 */
public class FileHelper
{
    public static String getRelativePath(File base, File name) throws IOException
    {
        File parent = base.getParentFile();

        if ( parent == null )
        {
            throw new IOException( "No common directory" );
        }

        String bpath = base.getCanonicalPath();
        String fpath = name.getCanonicalPath();

        if ( fpath.startsWith( bpath ) )
        {
            return fpath.substring( bpath.length() + 1 );
        } else
        {
            return ( ".." + File.separator + getRelativePath( parent, name ) );
        }
    }

}
