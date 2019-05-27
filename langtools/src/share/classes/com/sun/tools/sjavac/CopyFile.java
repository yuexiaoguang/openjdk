package com.sun.tools.sjavac;

import java.io.*;
import java.net.URI;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

/**
 * The copy file transform simply copies a matching file from -src to -d .
 * Such files are typically images, xml documents and other data files.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 */
public class CopyFile implements Transformer {

    public void setExtra(String e) {
    }

    public void setExtra(String[] a) {
    }

    public boolean transform(Map<String,Set<URI>> pkgSrcs,
                             Set<URI> visibleSrcs,
                             Map<URI,Set<String>> visibleClasses,
                             Map<String,Set<String>> oldPackageDependents,
                             URI destRoot,
                             Map<String,Set<URI>>    packageArtifacts,
                             Map<String,Set<String>> packageDependencies,
                             Map<String,String>      packagePubapis,
                             int debugLevel,
                             boolean incremental,
                             int numCores,
                             PrintStream out,
                             PrintStream err)
    {
        boolean rc = true;
        String dest_filename;
        File dest;

        for (String pkgName : pkgSrcs.keySet()) {
            String pkgNameF = Util.toFileSystemPath(pkgName);
            for (URI u : pkgSrcs.get(pkgName)) {
                File src = new File(u);
                File destDir;
                destDir = new File(destRoot.getPath()+File.separator+pkgNameF);
                dest_filename = destRoot.getPath()+File.separator+pkgNameF+File.separator+src.getName();
                dest = new File(dest_filename);

                if (!destDir.isDirectory()) {
                    if (!destDir.mkdirs()) {
                       Log.error("Error: The copier could not create the directory "+
                                           destDir.getPath());
                        return false;
                    }
                }

                Set<URI> as = packageArtifacts.get(pkgName);
                if (as == null) {
                    as = new HashSet<URI>();
                    packageArtifacts.put(pkgName, as);
                }
                as.add(dest.toURI());

                if (dest.exists() && dest.lastModified() > src.lastModified()) {
                    // A copied file exists, and its timestamp is newer than the source.
                    continue;
                }

                Log.info("Copying "+pkgNameF+File.separator+src.getName());

                try (InputStream fin = new FileInputStream(src);
                     OutputStream fout = new FileOutputStream(dest)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = fin.read(buf)) > 0){
                        fout.write(buf, 0, len);
                    }
                }
                catch(IOException e){
                    Log.error("Could not copy the file "+src.getPath()+" to "+dest.getPath());
                    rc = false;
                }
            }
        }
        return rc;
    }
}
