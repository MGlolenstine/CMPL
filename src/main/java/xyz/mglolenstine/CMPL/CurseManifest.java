package xyz.mglolenstine.CMPL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.io.Files;
import com.google.gson.Gson;

public class CurseManifest {

    protected static final Gson gson = new Gson();

    public int projectID;
    public List<CurseManifestFile> files = new ArrayList<CurseManifestFile>();

    public static CurseManifest fromFile(File file) throws IOException {
        return fromString(Files.toString(file, Charsets.UTF_8));
    }

    public static CurseManifest fromString(String json) {
        return gson.fromJson(json, CurseManifest.class);
    }

    private CurseManifest() {
    }

    public List<CurseManifestFile> getFiles() {
        return files;
    }

    public static class CurseManifestFile {
        public int projectID;
        public int fileID;
        public boolean required;

        @Override
        public String toString() {
            return "CurseManifestFile [projectID=" + projectID + ", fileID=" + fileID + ", required=" + required + "]";
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("projectID", projectID)
                .add("files", files)
                .toString();
    }

}
