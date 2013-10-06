package com.smash.revolance.ui.materials;

import org.apache.commons.exec.OS;

import java.io.File;

/**
 * User: wsmash
 * Date: 23/09/13
 * Time: 19:44
 */
public class TestConstants
{
    public static String MAIN_RESOURCES              = "src/main/resources";
    public static String TEST_RESOURCES              = "src/test/resources";
    public static String TARGET                      = new File( new File( "" ).getAbsolutePath(), "target" ).getAbsolutePath();
    public static String MATERIALS                   = new File( TARGET, "materials" ).getAbsolutePath();
    public static File   MATERIALS_WEBDRIVER_RES_DIR = new File( MATERIALS, "ui-materials-mock-webdriver-commons" );

    // Name of the materials
    public static String REF_PAGE_DIR         = "page-ref";
    public static String NEW_PAGE_URL_DIR     = "page-new-url";
    public static String NEW_PAGE_LOOK_DIR    = "page-new-look";
    public static String NEW_PAGE_LAYOUT_DIR  = "page-new-layout";
    public static String NEW_PAGE_CONTENT_DIR = "page-new-content";

    // Ref Page materials
    private static String REF_PAGE_FOLDER  = new File( MATERIALS, "ui-materials-resources-page-ref" ).getAbsolutePath();
    public static  String REF_PAGE_HOME    = "file://" + new File( REF_PAGE_FOLDER, "index.html" ).getAbsolutePath();
    public static  String REF_PAGE         = "file://" + REF_PAGE_FOLDER;
    public static  File   REF_PAGE_SITEMAP = new File( MATERIALS_WEBDRIVER_RES_DIR, REF_PAGE_DIR + "/sitemap.json" );
    public static  File   MOCK_REF_PAGE    = new File( MATERIALS_WEBDRIVER_RES_DIR, REF_PAGE_DIR + "/mock.json" );

    // New Page Url materials
    private static String NEW_PAGE_URL_FOLDER  = new File( MATERIALS, "ui-materials-resources-page-new-url" ).getAbsolutePath();
    public static  String NEW_PAGE_URL         = "file://" + NEW_PAGE_URL_FOLDER;
    public static  String NEW_PAGE_URL_HOME    = "file://" + new File( NEW_PAGE_URL_FOLDER, "home.html" ).getAbsolutePath();
    public static  File   NEW_PAGE_URL_SITEMAP = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_URL_DIR + "sitemap.json" );
    public static  File   MOCK_NEW_PAGE_URL    = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_URL_DIR + "/mock.json" );

    // New Page Look materials
    private static String NEW_PAGE_LOOK_FOLDER  = new File( MATERIALS, "ui-materials-resources-page-new-look" ).getAbsolutePath();
    public static  String NEW_PAGE_LOOK_HOME    = "file://" + new File( NEW_PAGE_LOOK_FOLDER, "index.html" ).getAbsolutePath();
    public static  String NEW_PAGE_LOOK         = "file://" + NEW_PAGE_LOOK_FOLDER;
    public static  File   NEW_PAGE_LOOK_SITEMAP = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_LOOK_DIR + "/sitemap.json" );
    public static  File   MOCK_NEW_PAGE_LOOK    = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_LOOK_DIR + "/mock.json" );

    // New Page Content materials
    private static String NEW_PAGE_CONTENT_FOLDER  = new File( MATERIALS, "ui-materials-resources-page-new-content" ).getAbsolutePath();
    public static  String NEW_PAGE_CONTENT         = "file://" + NEW_PAGE_CONTENT_FOLDER;
    public static  String NEW_PAGE_CONTENT_HOME    = "file://" + new File( NEW_PAGE_CONTENT_FOLDER, "index.html" ).getAbsolutePath();
    public static  File   NEW_PAGE_CONTENT_SITEMAP = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_CONTENT_DIR + "/sitemap.json" );
    public static  File   MOCK_NEW_PAGE_CONTENT    = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_CONTENT_DIR + "/mock.json" );

    // New Page Layout materials
    private static String NEW_PAGE_LAYOUT_FOLDER  = new File( MATERIALS, "ui-materials-resources-page-new-layout" ).getAbsolutePath();
    public static  String NEW_PAGE_LAYOUT         = "file://" + NEW_PAGE_LAYOUT_FOLDER;
    public static  String NEW_PAGE_LAYOUT_HOME    = "file://" + new File( NEW_PAGE_LAYOUT_FOLDER, "index.html" ).getAbsolutePath();
    public static  File   NEW_PAGE_LAYOUT_SITEMAP = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_LAYOUT_DIR + "/sitemap.json" );
    public static  File   MOCK_NEW_PAGE_LAYOUT    = new File( MATERIALS_WEBDRIVER_RES_DIR, NEW_PAGE_LAYOUT_DIR + "/mock.json" );

    private static String REF_WEBSITE = "file://" + new File( MATERIALS, "ui-materials-resources-website-ref/index.html" ).getAbsolutePath();
    public static  String NEW_WEBSITE = "file://" + new File( MATERIALS, "ui-materials-resources-website-new/index.html" ).getAbsolutePath();

    public static String WEBSITE = new File( new File( "" ).getAbsolutePath(), "src/test/resources" ).getAbsolutePath();

    // Revolance UI test architecture constants
    public static String APP_CFG  = new File( new File( "" ).getAbsoluteFile(), "src/test/config/cfg-app.xml" ).getAbsolutePath();
    public static String USER_CFG = new File( new File( "" ).getAbsoluteFile(), "src/test/config/cfg-users.xml" ).getAbsolutePath();

    public static String CHROME_DRIVER = new File( new File( "" ).getAbsoluteFile(), "src/test/driver/" + ( OS.isFamilyUnix() ? "unix" : "win" ) + "/chromedriver" + ( OS.isFamilyUnix() ? "" : ".exe" ) ).getAbsolutePath();


    public static File getMockedPage(String url) throws Exception
    {
        if ( url.contains( REF_PAGE_DIR ) )
        {
            return MOCK_REF_PAGE;
        }
        else if ( url.contains( NEW_PAGE_URL_DIR ) )
        {
            return MOCK_NEW_PAGE_URL;
        }
        else if ( url.contains( NEW_PAGE_LOOK_DIR ) )
        {
            return MOCK_NEW_PAGE_LOOK;
        }
        else if ( url.contains( NEW_PAGE_LAYOUT_DIR ) )
        {
            return MOCK_NEW_PAGE_LAYOUT;
        }
        else if ( url.contains( NEW_PAGE_CONTENT_DIR ) )
        {
            return MOCK_NEW_PAGE_CONTENT;
        }
        throw new Exception( "Unable to find any mock resourse matching url: "+ url );
    }
}
