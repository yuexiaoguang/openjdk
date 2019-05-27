#include "awt_ole.h"
#include <time.h>
#include <sys/timeb.h>

namespace SUN_DBG_NS{
  //WIN32 debug channel approach
  //inline void DbgOut(LPCTSTR lpStr) { ::OutputDebugString(lpStr); }

  //Java debug channel approach
  inline void DbgOut(LPCTSTR lpStr) { DTRACE_PRINT(_B(lpStr)); }

  LPCTSTR CreateTimeStamp(LPTSTR lpBuffer, size_t iBufferSize)
  {
        struct _timeb tb;
        _ftime(&tb);
        size_t len = _tcsftime(lpBuffer, iBufferSize, _T("%b %d %H:%M:%S"), localtime(&tb.time));
        if (len && len+4 < iBufferSize) {
            if (_sntprintf(lpBuffer+len, iBufferSize-len-1, _T(".%03d"), tb.millitm) < 0) {
                 lpBuffer[iBufferSize-len-1] = 0;
            }
        }
        return lpBuffer;
  }

  #define DTRACE_BUF_LEN 1024
  void snvTrace(LPCTSTR lpszFormat, va_list argList)
  {
        TCHAR szBuffer[DTRACE_BUF_LEN];
        if (_vsntprintf( szBuffer, DTRACE_BUF_LEN, lpszFormat, argList ) < 0) {
            szBuffer[DTRACE_BUF_LEN-1] = 0;
        }
        TCHAR szTime[32];
        CreateTimeStamp(szTime, sizeof(szTime));
        _tcscat(szTime, _T(" "));
        TCHAR szBuffer1[DTRACE_BUF_LEN];
        size_t iFormatLen = _tcslen(lpszFormat);
        BOOL bErrorReport = iFormatLen>6 && _tcscmp(lpszFormat + iFormatLen - 6, _T("[%08x]"))==0;
        size_t iTimeLen = _tcslen(szTime);
        if (_sntprintf(
            szBuffer1 + iTimeLen,
            DTRACE_BUF_LEN - iTimeLen - 1, //reserver for \n
            _T("P:%04d T:%04d ") TRACE_SUFFIX _T("%s%s"),
            ::GetCurrentProcessId(),
            ::GetCurrentThreadId(),
            bErrorReport?_T("Error:"):_T(""),
            szBuffer) < 0)
        {
            _tcscpy_s(szBuffer1 + DTRACE_BUF_LEN - 5, 5, _T("...")); //reserver for \n
        }
        memcpy(szBuffer1, szTime, iTimeLen*sizeof(TCHAR));
        _tcscat(szBuffer1, _T("\n"));
        DbgOut( szBuffer1 );
  }
  void snTrace(LPCTSTR lpszFormat, ... )
  {
        va_list argList;
        va_start(argList, lpszFormat);
        snvTrace(lpszFormat, argList);
        va_end(argList);
  }
}//SUN_DBG_NS namespace end
