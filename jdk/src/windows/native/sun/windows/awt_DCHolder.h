#ifndef _AWT_DCHolder_H
#define _AWT_DCHolder_H

struct DCHolder
{
    HDC m_hMemoryDC;
    int m_iWidth;
    int m_iHeight;
    BOOL m_bForImage;
    HBITMAP m_hBitmap;
    HBITMAP m_hOldBitmap;
    void *m_pPoints;

    DCHolder();
    ~DCHolder();

    void Create(
        HDC hRelDC,
        int iWidth,
        int iHeght,
        BOOL bForImage);

    operator HDC()
    {
        if (NULL == m_hOldBitmap && NULL != m_hBitmap) {
            m_hOldBitmap = (HBITMAP)::SelectObject(m_hMemoryDC, m_hBitmap);
        }
        return m_hMemoryDC;
    }

    operator HBITMAP()
    {
        if (NULL != m_hOldBitmap) {
            m_hBitmap = (HBITMAP)::SelectObject(m_hMemoryDC, m_hOldBitmap);
            m_hOldBitmap = NULL;
        }
        return m_hBitmap;
    }

    static HBITMAP CreateJavaContextBitmap(
        HDC hdc,
        int iWidth,
        int iHeight,
        void **ppPoints);
};

#endif //_AWT_DCHolder_H
