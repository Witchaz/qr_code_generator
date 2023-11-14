package si.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.concurrent.Task;
import org.apache.commons.io.FileUtils;
import si.models.SeriesList;
import si.models.Series;

public class QRGenerator extends Task<Integer> {
	
	private SeriesList seriesList;
	public QRGenerator(){
		seriesList = Data.getData().getProject();
	}
	
	
	private void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);
		
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}
	
	@Override
	protected Integer call() throws Exception {
		int size = 0;
		int count = 0;
		
		for (Series temp : seriesList.getSeriesList()){
			size += temp.getEndNumber() - temp.getStartNumber() + 1;
		}
		
		
		String path = (System.getProperty("user.dir"));
		path = path + '\\' + "data";
		Path filePath = Path.of(path);
		
		if (Files.notExists(filePath)){
			try {
				Files.createDirectories(filePath);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		for (Series tempSeries : seriesList.getSeriesList()){
			
			
			ArrayList<String> series=  tempSeries.getSeries();
			for (String temp : series){
				count++;
				updateProgress(count,size);
				String thisPath = "%s\\%s.png".formatted(path, temp);
				File qrFile = new File(thisPath);
				try {
					createQRImage(qrFile,temp,256,"png");
				} catch (WriterException | IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return 0;
	}
}