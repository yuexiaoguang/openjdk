package sun.security.provider;

import java.io.IOException;

/**
 * Native seed generator for Unix systems. Inherit everything from
 * URLSeedGenerator.
 */
class NativeSeedGenerator extends SeedGenerator.URLSeedGenerator {

    NativeSeedGenerator(String seedFile) throws IOException {
        super(seedFile);
    }

}
