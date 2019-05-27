package sun.net.ftp.impl;

/**
 * Default FtpClientProvider.
 * Uses sun.net.ftp.FtpCLient.
 */
public class DefaultFtpClientProvider extends sun.net.ftp.FtpClientProvider {

    @Override
    public sun.net.ftp.FtpClient createFtpClient() {
        return sun.net.ftp.impl.FtpClient.create();
    }

}
