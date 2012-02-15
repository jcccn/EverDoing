package com.senseforce.framework.foundation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class SFFileManager {

	private static SFFileManager defaultManager = null;

	public SFFileManager() {

	}

	public static SFFileManager defaultManager() {
		if (defaultManager == null) {
			defaultManager = new SFFileManager();
		}
		return defaultManager;
	}

	public boolean createFile(Context context, String path, byte[] contents,
			int mode) {
		try {
			FileOutputStream fout = context.openFileOutput(path, mode);
			fout.write(contents);
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public byte[] readFile(Context context, String path) {
		byte[] buffer = null;
		try {
			FileInputStream fin = context.openFileInput(path);
			int length = fin.available();
			if (length <= 0)
				return null;
			buffer = new byte[length];
			fin.read(buffer);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buffer;
	}

	public byte[] readAssetsFile(Context context, String fileName) {
		InputStream in = null;
		byte[] buffer = null;
		try {
			in = context.getResources().getAssets().open(fileName);
			int length = in.available();
			buffer = new byte[length];
			in.read(buffer);
		} catch (Exception e) {
			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return buffer;
	}

}
