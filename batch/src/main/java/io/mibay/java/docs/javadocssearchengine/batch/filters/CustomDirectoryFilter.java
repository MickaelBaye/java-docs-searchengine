package io.mibay.java.docs.javadocssearchengine.batch.filters;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;

import java.io.File;

public class CustomDirectoryFilter implements IOFileFilter {

    // Dir name blacklist

    private static final String INDEX_FILES = "index-files";
    private static final String CLASS_USE = "class-use";

    private static final String[] BLACKLIST = new String[]{
            INDEX_FILES, CLASS_USE
    };

    @Override
    public boolean accept(File file) {
        // Reject the file if the name of the file match the name in the blacklist
        for (String name : BLACKLIST) {
            if (new NameFileFilter(name).accept(file)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean accept(File file, String s) {
        // Reject the file if the name of the file match the name in the blacklist
        for (String name : BLACKLIST) {
            if (new NameFileFilter(name).accept(file, s)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
