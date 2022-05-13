package Model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
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
import org.apache.commons.io.FilenameUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class AttachedFiles {


    private final String pathToServiceAccountKeyFile = "files/credentialsp12.p12";
    private final String serviceAccountEmail = "bugtracker@autonomous-key-349812.iam.gserviceaccount.com";
    private final String directoryID = "1JWTpxqLvj6nt8H8-I_RXlDW_KXy7LvT-";
    private String uploadFileName;
    private ArrayList<File> list;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();



    public Drive getDriveService() throws GeneralSecurityException, IOException {
        Collection<String> Tickets= new ArrayList<String>();
        Tickets.add("https://www.googleapis.com/auth/drive");

        HttpTransport httpTransport = new NetHttpTransport();
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountScopes(Tickets)
                .setServiceAccountPrivateKeyFromP12File(

                        new java.io.File(pathToServiceAccountKeyFile))
                .build();
        Drive service = new Drive.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("FileListAccessProject")
                .setHttpRequestInitializer(credential).build();

        return service;
    }



    public void getAllFiles (Drive service) throws IOException, GeneralSecurityException {
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

    public String checkIfExist(Drive service, String name) throws IOException {
        String fileID = null;
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        for (File file : files) {
            if (file.getName().equals(name)) {
                fileID = file.getId();
            }
        }
        return fileID;
    }

    public String createDriveFolder(Drive service,String name) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setParents(Collections.singletonList(directoryID));
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        File file = service.files().create(fileMetadata)
                .setFields("id")
                .execute();
        System.out.println("Folder ID: " + file.getId());
        return file.getId();
    }

    public void moveAttachedFile(Drive service, String filePath, String currDirectory) throws IOException {
        String type = FilenameUtils.getExtension(filePath); //Get filetype
        java.io.File file = new java.io.File(filePath); //Create file from path
        String mimetype = Files.probeContentType(Paths.get(filePath));

        File fileMetaData = new File();
        fileMetaData.setParents(Collections.singletonList(currDirectory));  //set TicketID directory
        fileMetaData.setName(file.getName());

        FileContent fileContent = new FileContent(mimetype, file); //create content for drive

        File driveFile = service.files().create(fileMetaData, fileContent)
                .setFields("id")
                .setFields("parents")
                .execute();

    }

    public void downloadFile(Drive service, String id, String savePath, String saveName) throws IOException {
       Path path = Paths.get(savePath, saveName);
        OutputStream outputStream = Files.newOutputStream(path);
                 service.files().get(id)
                .executeMediaAndDownloadTo(outputStream);

    }

    public ArrayList<String> seeAttachedFiles(Drive service, String id) throws IOException {
        ArrayList<String> returnlist = new ArrayList();
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .setDriveId(id)
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                returnlist.add(file.getName());
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }
        return returnlist;
    }
}
