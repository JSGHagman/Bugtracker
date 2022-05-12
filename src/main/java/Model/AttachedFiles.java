package Model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.*;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;



import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AttachedFiles {


    private final String pathToServiceAccountKeyFile = "files/credentialsp12.p12";
    private final String serviceAccountEmail = "bugtracker@autonomous-key-349812.iam.gserviceaccount.com";
    private final String directoryID = "1JWTpxqLvj6nt8H8-I_RXlDW_KXy7LvT-";
    private String uploadFileName;
    private ArrayList<File> list;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public AttachedFiles() {
        try {
            Drive service = getDriveService();
            testConnect(service);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public Drive getDriveService() throws GeneralSecurityException, IOException {
        Collection<String> elenco = new ArrayList<String>();
        elenco.add("https://www.googleapis.com/auth/drive");

        HttpTransport httpTransport = new NetHttpTransport();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountScopes(elenco)
                .setServiceAccountPrivateKeyFromP12File(
                        new java.io.File(pathToServiceAccountKeyFile))
                .build();
        Drive service = new Drive.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("FileListAccessProject")
                .setHttpRequestInitializer(credential).build();

        return service;
    }



    public void testConnect (Drive service) throws IOException, GeneralSecurityException {
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }

    }


}
