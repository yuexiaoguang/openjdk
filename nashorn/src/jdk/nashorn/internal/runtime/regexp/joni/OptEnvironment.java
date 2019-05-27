package jdk.nashorn.internal.runtime.regexp.joni;

// remove this one in future and pass mmd directly
final class OptEnvironment {
    final MinMaxLen mmd = new MinMaxLen();
    int options;
    int caseFoldFlag;
    ScanEnvironment scanEnv;

    void copy(OptEnvironment other) {
        mmd.copy(other.mmd);
        options = other.options;
        caseFoldFlag = other.caseFoldFlag;
        scanEnv = other.scanEnv;
    }
}
