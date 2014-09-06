// PGTreeInfo.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"

// ////////////////////////////////////////////////////////
// Utilities

/*
 * Function: jGetString
 * Purpose:	 Given a java string, returns a pointer to a wide string suitable for 
 *           windows API calls or display.
 * Input:	 Java environment pointer, Java string to convert.
 * Output:	 String converted to wide string pointer.
 * Return:	 LPTSTR (Long Pointer to a String)
 */
LPTSTR jGetString(JNIEnv *env, jstring jstr) {
	if (jstr == NULL) {
		return NULL;
	}

	const char *utf = env->GetStringUTFChars(jstr, 0);
	const int length = env->GetStringLength(jstr);

	TCHAR *tstr = new TCHAR[length + 1];
#ifdef  UNICODE
	MultiByteToWideChar(CP_UTF8, 0, utf, -1, tstr, length + 1);
#else
	sprintf(tstr, _T("%s"), utf);
#endif
	env->ReleaseStringUTFChars(jstr, utf);

	return tstr;
} // jGetString

/*
 * Function: GetJString
 * Purpose:	 Given a pointer to a wide string, returns a java string suitable for 
 *           returning to the Java system.
 * Input:	 Java environment pointer, wide string pointer to convert.
 * Output:	 String converted to Java string.
 * Return:	 jstring
 */
jstring GetJString(JNIEnv *env, LPTSTR str) {
	char strUTF[MAX_PATH];

	if (str == NULL) {
		return NULL;
	}

#ifdef  UNICODE
	WideCharToMultiByte(CP_UTF8, 0, str, -1, strUTF, MAX_PATH, NULL, NULL);
#else
	strUTF = str;
#endif
	
	// UTF8 to java String
	return env->NewStringUTF(strUTF);
} // GetJString

/*
 * Function: throwByName
 * Purpose:	 Throws a Java exception to the Java system from a JNI call.
 * Input:	 Java environment pointer, name of exception class to throw and 
 *           message to pass to that class.
 * Output:	 Thrown exception in Java.
 * Return:	 void
 */
void throwByName(JNIEnv *env, const char *name, const char *msg)
{
	jclass cls = env->FindClass(name);
	/* if cls is NULL, an exception has already been thrown (or so the documentation tells me) */
	if (cls != NULL) {
		env->ThrowNew(cls, msg);
	}
	else
	{
	}

	/* free the local ref */
	env->DeleteLocalRef(cls);
}

//
// Takes the First and Second parameters and joins them using the
// standard "\" separator into the Buffer parameter.
//
static LPTSTR FormFilename(LPTSTR Buffer, LPTSTR First, LPTSTR Second)
{
 int LastPos;

	if(First != NULL)
	{
		lstrcpy(Buffer, First);

		LastPos = lstrlen(Buffer) - 1;
		if(Buffer[LastPos] != (TCHAR)'\\')
			lstrcat(Buffer, TEXT("\\"));
	}

	if(Second != NULL)
		if(*Second == (TCHAR)'\\')
			lstrcat(Buffer, (LPTSTR)&Second[1]);
		else
			lstrcat(Buffer, Second);

	return Buffer;
}

#define STANDARDSIZE 255
#define FlagSet(Options, Flags) (((UINT)Flags & (UINT)Options) == (UINT)Options)

JNIEXPORT jboolean JNICALL Java_com_stronans_pilgrim_data_model_StaticData_hasChildFolders
  (JNIEnv *env, jclass jObj, jstring jPath)
{
 LPTSTR szTmp, szPath;
 WIN32_FIND_DATA FindFileData;
 HANDLE hFf;
 jboolean jbResult = JNI_FALSE;

	szTmp = new TCHAR[STANDARDSIZE];

	szPath = jGetString(env, jPath);

	FormFilename(szTmp, szPath, TEXT("*.*"));

	// and now do the search for folders.
	hFf = FindFirstFile(szTmp, &FindFileData);
	if(hFf != INVALID_HANDLE_VALUE)
	{
		do
		{
			// Do we have a directory?
			if(FlagSet(FILE_ATTRIBUTE_DIRECTORY, FindFileData.dwFileAttributes))
				// Ignore the current directory marker,
				// and the directory above marker.
				if(*FindFileData.cFileName != (TCHAR)'.')
					jbResult = JNI_TRUE;
		}
		while((FindNextFile(hFf, &FindFileData) != 0) && (!jbResult));

		FindClose(hFf);
	}

	delete[] szPath;
	delete[] szTmp;

	return jbResult;
}

