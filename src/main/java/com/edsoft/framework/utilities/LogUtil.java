package com.edsoft.framework.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.DocumentException;
public class LogUtil {

	ZonedDateTime date = ZonedDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
	String fileNameFormat = date.format(formatter);
	ScreenshotUtil tirar = new ScreenshotUtil();

	private BufferedWriter bufferedWriter = null;
	public void CreateLogFile() throws IOException {
		try {
			File dir = new File(Settings.LogPath);
			if (!dir.exists())
				dir.mkdir();
			File logFile = new File(dir + "/" + fileNameFormat + ".log");
			FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
			bufferedWriter = new BufferedWriter(fileWriter);
		} catch (Exception e) {
		}
	}

	public void Write(String message) {
		try {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH_MM_SS");
			String dateFormat = date.format(formatter);
			bufferedWriter.write("[" + dateFormat + "]: " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			System.out.println("[" + dateFormat + "]: " + message);
		} catch (Exception e) {
		}
	}

	public void GravaLog(String message) throws IOException, DocumentException {
		tirar.screenShot();
		try {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH_MM_SS");
			String dateFormat = date.format(formatter);
			bufferedWriter.write("[" + dateFormat + "]: " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			System.out.println("log gravada: [" + dateFormat + "]: " + message);
		} catch (Exception e) {
		}
	}

	public void GravaLogReport(String message) throws IOException, DocumentException {
		tirar.screenShot().report();
		try {
			formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH_MM_SS");
			String dateFormat = date.format(formatter);
			bufferedWriter.write("[" + dateFormat + "]: " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			System.out.println("log gravada: [" + dateFormat + "]: " + message);
		} catch (Exception e) {
		}
	}
}
