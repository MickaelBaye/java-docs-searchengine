package io.mibay.java.docs.javadocssearchengine.batch.filters;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;

public class CustomFileFilter implements IOFileFilter {

    private static final String HTML_SUFFIX = ".html";

    // Regular expressions

    // "frame" HTML pattern (ie: "allclasses-frame.html" or "compact1-frame.html")
    private static final String FRAME_PATTERN = "^.*-frame.html$";
    // "noframe" HTML pattern (ie: "allclasses-noframe.html")
    private static final String NOFRAME_PATTERN = "^.*-noframe.html$";
    // "summary" HTML pattern (ie: "compact1-summary.html" or "overview-summary.html")
    private static final String SUMMARY_PATTERN = "^.*-summary.html$";
    // "tree" HTML pattern (ie: "overview-tree.html")
    private static final String TREE_PATTERN = "^.*-tree.html$";
    // "use" HTML pattern (ie: "package-use.html")
    private static final String USE_PATTERN = "^.*-use.html$";

    private static final String[] PATTERN_LIST = new String[]{
            FRAME_PATTERN, NOFRAME_PATTERN, SUMMARY_PATTERN, TREE_PATTERN, USE_PATTERN
    };

    // File name blacklist

    private static final String INDEX = "index.html";
    private static final String HELP_DOC = "help-doc.html";
    private static final String SERIAL_FORM = "serialized-form.html";

    private static final String[] BLACKLIST = new String[]{
            INDEX, HELP_DOC, SERIAL_FORM
    };

    @Override
    public boolean accept(File file) {
        // Accepts only HTML files
        if (new SuffixFileFilter(HTML_SUFFIX).accept(file)) {
            // Reject the file if the name of the file match the pattern in the list
            for (String pattern : PATTERN_LIST) {
                if (new RegexFileFilter(pattern).accept(file)) {
                    return Boolean.FALSE;
                }
            }
            // Reject the file if the name of the file match the name in the blacklist
            for (String name : BLACKLIST) {
                if (new NameFileFilter(name).accept(file)) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public boolean accept(File file, String s) {
        // Accepts only HTML files
        if (new SuffixFileFilter(HTML_SUFFIX).accept(file, s)) {
            // Reject the file if the name of the file match the pattern in the list
            for (String pattern : PATTERN_LIST) {
                if (new RegexFileFilter(pattern).accept(file, s)) {
                    return false;
                }
            }
            // Reject the file if the name of the file match the name in the blacklist
            for (String name : BLACKLIST) {
                if (new NameFileFilter(name).accept(file, s)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
