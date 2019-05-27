package sun.java2d.xr;

public class XRSolidSrcPict {
    XRBackend con;

    XRSurfaceData srcPict;
    XRColor xrCol;
    int curPixVal = -1;

    public XRSolidSrcPict(XRBackend con, int parentXid) {
        this.con = con;

        xrCol = new XRColor();
        int solidPixmap = con.createPixmap(parentXid, 32, 1, 1);
        int solidSrcPictXID = con.createPicture(solidPixmap, XRUtils.PictStandardARGB32);
        con.setPictureRepeat(solidSrcPictXID, XRUtils.RepeatNormal);
        con.renderRectangle(solidSrcPictXID, XRUtils.PictOpSrc, XRColor.FULL_ALPHA, 0, 0, 1, 1);
        srcPict = new XRSurfaceData.XRInternalSurfaceData(con, solidSrcPictXID);
    }

    public XRSurfaceData prepareSrcPict(int pixelVal) {
        if(pixelVal != curPixVal) {
            xrCol.setColorValues(pixelVal, false);
            con.renderRectangle(srcPict.picture, XRUtils.PictOpSrc, xrCol, 0, 0, 1, 1);
            this.curPixVal = pixelVal;
        }

        return srcPict;
    }

}
