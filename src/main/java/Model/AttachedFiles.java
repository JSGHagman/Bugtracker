package Model;
/**
 * @author Patrik Brandell
 * Google Drive API Class
 */

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.commons.io.FilenameUtils;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class AttachedFiles {


    private final String pathToServiceAccountKeyFile = "files/credentialsp12.p12"; //Certificate file to authorize service account
    private final String serviceAccountEmail = "bugtracker@autonomous-key-349812.iam.gserviceaccount.com"; //Service account email
    private final String directoryID = "1JWTpxqLvj6nt8H8-I_RXlDW_KXy7LvT-"; //Ticket directory ID
    private String uploadFileName;
    private ArrayList<File> folders, files;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     *
     * @return Google drive service
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public Drive getDriveService() throws GeneralSecurityException, IOException {
        Collection<String> Tickets = new ArrayList<String>();
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

    /**
     *
     * @param service Google drive service
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public void getAllFiles(Drive service) throws IOException, GeneralSecurityException {
        FileList result = service.files().list()
                .setQ("mimeType != 'application/vnd.google-apps.folder'")
                .setFields("nextPageToken, files(id, name, parents)")
                .execute();
        files = (ArrayList<File>) result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        }
    }

    /**
     *
     * @param service Google drive service
     * @throws IOException
     */
    public void getAllFolders(Drive service) throws IOException {
        FileList result = service.files().list()
                .setQ("mimeType = 'application/vnd.google-apps.folder'")
                .setFields("nextPageToken, files(id, name)")
                .execute();
        folders = (ArrayList<File>) result.getFiles();
    }

    /**
     *
     * @param id  int ticked ID
     * @return File ArrayList - All files contained withing folder
     */
    public ArrayList<File> getFilesFromID(int id) {
        String idString = String.valueOf(id);
        File folder = null;
        ArrayList<File> finalFiles = new ArrayList<>();
        for (File f : folders) {
            if (f.getName().equals(idString)) {
                folder = f;
                break;
            }
        }
        if (folder != null) {
            for (File file : files) {
                if (file.getParents().contains(folder.getId())) {
                    finalFiles.add(file);
                }
            }
        }
        return finalFiles;
    }

    /**
     *
     * @param service Google drive service
     * @param name String name of file
     * @return String fileID if exist else null
     * @throws IOException
     */
    public String checkIfExist(Drive service, String name) throws IOException {
        String fileID = null;
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name, parents)")
                .execute();
        List<File> files = result.getFiles();
        for (File file : files) {
            //System.out.println("Checking...");
            if (file.getName().equals(name)) {
                fileID = file.getId();
                //System.out.println("MATCH");
            }
        }
        return fileID;
    }

    /**
     *
     * @param service Google drive service
     * @param name String name of ticket(ID)
     * @return String google ID of folder
     * @throws IOException
     */
    public String createDriveFolder(Drive service, String name) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setParents(Collections.singletonList(directoryID));
        fileMetadata.setName(name);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        File file = service.files().create(fileMetadata)
                .setFields("id")
                .execute();
        //System.out.println("Folder ID: " + file.getId());
        return file.getId();
    }

    /**
     *
     * @param service Google drive service
     * @param filePath String where to save file
     * @param currDirectory String
     * @throws IOException
     */
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
}