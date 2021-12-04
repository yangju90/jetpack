package com.newegg.logistics.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;

import com.symbol.emdk.EMDKManager;
import com.symbol.emdk.EMDKManager.EMDKListener;
import com.symbol.emdk.EMDKManager.FEATURE_TYPE;
import com.symbol.emdk.EMDKResults;
import com.symbol.emdk.ProfileManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.StringReader;

public class EMDKAPIWrapper implements EMDKListener {
    //Assign the profile name used in EMDKConfig.xml
    private String profileName = "MyEMDKProfile";
    //Declare a variable to store ProfileManager object
    private ProfileManager profileManager = null;
    //Declare a variable to store EMDKManager object
    private EMDKManager emdkManager = null;
    // Contains the parm-error name (sub-feature that has error)
    private String errorName = "";
    // Contains the characteristic-error type (Root feature that has error)
    private String errorType = "";
    // contains the error description for parm or characteristic error.
    private String errorDescription = "";
    // contains status of the profile operation
    private String status = "";

    public void getEMDKManager(Context context) {
        EMDKResults results = EMDKManager.getEMDKManager(context, this);

        if (results.statusCode != EMDKResults.STATUS_CODE.SUCCESS) {
            //Failed to request the EMDKManager
        }
    }

    @Override
    public void onOpened(EMDKManager emdkManager) {
        this.emdkManager = emdkManager;
        // Get the ProfileManager object to process the profiles
        profileManager = (ProfileManager) emdkManager
                .getInstance(FEATURE_TYPE.PROFILE);
        if (profileManager != null) {
            String[] modifyData = new String[1];
            // Call processPrfoile with profile name and SET flag to create the profile. The modifyData can be null.
            EMDKResults results = profileManager.processProfile(profileName,
                    ProfileManager.PROFILE_FLAG.SET, modifyData);
            if (results.statusCode == EMDKResults.STATUS_CODE.CHECK_XML) {
                String statusXMLResponse = results.getStatusString();
                try {
                    // Create instance of XML Pull Parser to parse the response
                    XmlPullParser parser = Xml.newPullParser();
                    // Provide the string response to the String Reader that reads
                    // for the parser
                    parser.setInput(new StringReader(statusXMLResponse));
                    // Call method to parse the response
                    parseXML(parser);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                displayResults();
            } else {
                /*// Show dialog of Failure
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Failure");
                builder.setMessage("Failed to apply profile...")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick  (DialogInterface dialog,int id) {
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();*/
            }

        }
    }

    @Override
    public void onClosed() {
    }



    public void displayResults() {
        // Alert Dialog to display the status of the Profile creation
        // operation of MX features
    }


    public String buildFailureMessage() {
        String failureMessage = "";
        if (!TextUtils.isEmpty(errorName) && !TextUtils.isEmpty(errorType))
            failureMessage = errorName + " :" + "\n" + errorType + " :" + "\n"
                    + errorDescription;
        else if (!TextUtils.isEmpty(errorName))
            failureMessage = errorName + " :" + "\n" + errorDescription;
        else
            failureMessage = errorType + " :" + "\n" + errorDescription;
        return failureMessage;

    }

    public void parseXML(XmlPullParser myParser) {
        int event;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        // Get Status, error name and description in case of
                        // parm-error
                        if (name.equals("parm-error")) {
                            status = "Failure";
                            errorName = myParser.getAttributeValue(null, "name");
                            errorDescription = myParser.getAttributeValue(null,
                                    "desc");

                            // Get Status, error type and description in case of
                            // parm-error
                        } else if (name.equals("characteristic-error")) {
                            status = "Failure";
                            errorType = myParser.getAttributeValue(null, "type");
                            errorDescription = myParser.getAttributeValue(null,
                                    "desc");
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        break;
                }
                event = myParser.next();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
